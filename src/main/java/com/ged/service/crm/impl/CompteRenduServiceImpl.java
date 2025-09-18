package com.ged.service.crm.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.crm.CompteRenduDao;
import com.ged.dao.security.UtilisateurDao;
import com.ged.dto.crm.CompteRenduDto;
import com.ged.dto.crm.CompteRenduEtatDto;
import com.ged.dto.opcciel.comptabilite.Operation2Dto;
import com.ged.dto.standard.CrStateRequest;
import com.ged.dto.standard.DocumentDto;
import com.ged.dto.crm.RDVDto;
import com.ged.entity.crm.CompteRendu;
import com.ged.entity.security.Utilisateur;
import com.ged.mapper.crm.CompteRenduMapper;
import com.ged.mapper.standard.DocumentMapper;
import com.ged.service.crm.CompteRenduService;
import com.ged.service.standard.FileService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompteRenduServiceImpl implements CompteRenduService {
    @Value("${media.upload.dir}")
    public String PATH;
    @PersistenceContext
    private EntityManager emOpcciel;
    private final CompteRenduDao compteRenduDao;
    private final UtilisateurDao utilisateurDao;
    private final DocumentMapper documentMapper;
    private final ModelMapper modelMapper;
    private final CompteRenduMapper compteRenduMapper;
    private final FileService fileService;

    public CompteRenduServiceImpl(
            CompteRenduDao compteRenduDao,
            UtilisateurDao utilisateurDao, DocumentMapper documentMapper, ModelMapper modelMapper, CompteRenduMapper compteRenduMapper, FileService fileService) {
        this.compteRenduDao = compteRenduDao;
        this.utilisateurDao = utilisateurDao;
        this.modelMapper = modelMapper;
        this.documentMapper = documentMapper;
        this.compteRenduMapper = compteRenduMapper;
        this.fileService = fileService;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public Page<CompteRenduDto> afficherCompteRendus(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<CompteRendu> compteRenduPage = compteRenduDao.findAll(pageRequest);
        ModelMapper modelMapper = new ModelMapper();
        return new PageImpl<>(compteRenduPage.getContent().stream().map(compteRenduMapper::deCompteRendu)
                .collect(Collectors.toList()), pageRequest, compteRenduPage.getTotalElements());
    }

    @Override
    public List<CompteRenduDto> afficherTous() {
        return compteRenduDao.findAll().stream().map(compteRendu -> {
            CompteRenduDto compteRenduDto = compteRenduMapper.deCompteRendu(compteRendu);
            RDVDto rdvDto = modelMapper.map(compteRendu.getRdv(), RDVDto.class);
            compteRenduDto.setRdv(rdvDto);
            return compteRenduDto;
        }).collect(Collectors.toList());
    }

    @Override
    public List<CompteRenduEtatDto> afficherEtat(String idUtilisateur, HttpServletResponse response) throws IOException, JRException, ParseException {
        List<Object[]>  compteRendu;
        List<CompteRenduEtatDto> compteRenduDtos =new ArrayList<>();
//        emOpcciel = emOpcciel.getEntityManagerFactory().createEntityManager();

        StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[RDV].[PS_CompteRendu_SP]");
        //Déclarer les différents paramètres
        query.registerStoredProcedureParameter("idUtilisateur", String.class, ParameterMode.IN);


        //Fournir les différents paramètres
        if(idUtilisateur.equalsIgnoreCase("null"))
            query.setParameter("idUtilisateur", null);
        else
            query.setParameter("idUtilisateur", idUtilisateur);

        query.execute();

        compteRendu= query.getResultList();
        System.out.println(idUtilisateur);
        System.out.println("taille1="+compteRendu.size());
        for(Object[] o:compteRendu)
        {
            CompteRenduEtatDto ope=new CompteRenduEtatDto();
            ope.setIdCR(Long.valueOf(o[0].toString()));
            ope.setObjetCR(o[1].toString());
            ope.setAppreciation((o[2].toString()));
            ope.setDateCR(LocalDateTime.parse(o[3].toString().replace(' ', 'T')));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);

            Date date = formatter.parse(o[4].toString().substring(0,10));
            ope.setDateProchainRDV(date);
            ope.setDescription((o[5].toString()));
            ope.setHeureDebCR((o[6].toString()));
            ope.setHeureFinCR((o[7].toString()));
            ope.setDateEffectivePromesse(LocalDateTime.parse(o[8].toString().replace(' ','T')));
            ope.setRealisation(o[9]==null?"":o[9].toString());
            ope.setMontantRealisation(Double.valueOf(o[10]==null?"0" :o[10].toString()));
            ope.setPromesse(o[11]==null?"":o[11].toString());
            ope.setMontantPromesse(Double.valueOf(o[12]==null?"":o[12].toString()));

            compteRenduDtos.add(ope);
        }

//        Map<String, Object> parameters = new HashMap<>();
//        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        String letterDate = dateFormatter.format(new Date());
//        parameters.put("letterDate", letterDate);
//        File file = ResourceUtils.getFile("classpath:"+"compteRendu.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(compteRenduDtos);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);

        // Utilisation d'un InputStream pour accéder à la ressource dans le .jar
        InputStream inputStream = getClass().getResourceAsStream("/compteRendu.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(compteRenduDtos);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

//        System.out.println("total="+compteRenduDtos.size());

        return compteRenduDtos;
    }

    @Override
    public List<CompteRenduDto> afficherTousEtat() {
        return compteRenduDao.findAll().stream().map(compteRenduMapper::deCompteRendu).collect(Collectors.toList());
    }

    @Override
    public List<CompteRenduDto> afficherCompteRenduSelonUtilisateur(Long idUtilisateur) {
        //Utilisateur utilisateur=utilisateurDao.findById(idUtilisateur);
        //return  null;
        return compteRenduDao.findByIdCreateur(idUtilisateur).stream().map(compteRenduMapper::deCompteRendu).collect(Collectors.toList());
    }

    @Override
    public List<CompteRenduDto> afficherCompteRenduSelonRealisation(Long idUtilisateur, LocalDateTime dateDeb, LocalDateTime dateFin) {
        if(idUtilisateur==0)
            return compteRenduDao.afficherCompteRenduSelonRealisationAll(dateDeb,dateFin).stream().map((compteRenduMapper::deCompteRenduProjection)).collect(Collectors.toList());
        else
           return compteRenduDao.afficherCompteRenduSelonRealisation(idUtilisateur,dateDeb,dateFin).stream().map((compteRenduMapper::deCompteRenduProjection)).collect(Collectors.toList());
    }

    @Override
    public CompteRenduDto afficherCompteRendu(Long id) {
        CompteRendu compteRendu = afficherCompteRenduSelonId(id);
//        RDVDto rdvDto = modelMapper.map(compteRendu.getRdv(), RDVDto.class);
        //        compteRenduDto.setRdv(rdvDto);
//        System.out.println(rdvDto);
        return compteRenduMapper.deCompteRendu(compteRendu);
    }

    @Override
    public CompteRendu afficherCompteRenduSelonId(Long idCompteRendu) {
        return compteRenduDao.findById(idCompteRendu).orElseThrow(() -> new EntityNotFoundException(CompteRendu.class, idCompteRendu.toString()));
    }

    @Override
    public CompteRenduDto creerCompteRendu(List<MultipartFile> files, CompteRenduDto compteRenduDto) throws Throwable {
        CompteRendu compteRendu = compteRenduMapper.deCompteRenduDto(compteRenduDto);
        Set<DocumentDto> documents = compteRenduDto.getDocuments();
        compteRendu.getDocuments().clear();
        for (DocumentDto doc : documents) {
            compteRendu.ajouterDocument(documentMapper.deDocumentDto(doc));
        }
        CompteRenduDto compteRenduSaved = compteRenduMapper.deCompteRendu(compteRenduDao.save(compteRendu));

        if (files != null) {
            Set<DocumentDto> docs = new HashSet<>();
            CompteRendu compteRendu1=compteRenduDao.findById(compteRenduSaved.getIdCR()).orElseThrow();
            for(DocumentDto doc:compteRenduSaved.getDocuments()){
                doc.setCompteRendu(compteRenduMapper.deCompteRendu(compteRendu1));
                docs.add(doc);
            }
            Set<DocumentDto> newDocs = fileService.uploadMedia(PATH, files, docs);
            compteRenduSaved.setDocuments(newDocs);
        }
        return compteRenduSaved;
    }

    @Override
    public CompteRenduDto modifierCompteRendu(List<MultipartFile> files, CompteRenduDto compteRenduDto) throws Throwable {
        CompteRendu compteRendu = compteRenduMapper.deCompteRenduDto(compteRenduDto);
        Set<DocumentDto> documents = compteRenduDto.getDocuments();
        compteRendu.getDocuments().clear();
        for (DocumentDto doc : documents) {
            compteRendu.ajouterDocument(documentMapper.deDocumentDto(doc));
        }
        CompteRenduDto compteRenduSaved = compteRenduMapper.deCompteRendu(compteRenduDao.save(compteRendu));
        //System.out.println("size"+compteRenduSaved.getDocuments().size());
        if (files != null) {

            Set<DocumentDto> docs = new HashSet<>();
            CompteRendu compteRendu1=compteRenduDao.findById(compteRenduSaved.getIdCR()).orElseThrow();
            for(DocumentDto doc:compteRenduSaved.getDocuments()){
                doc.setCompteRendu(compteRenduMapper.deCompteRendu(compteRendu1));
                docs.add(doc);
            }
            Set<DocumentDto> newDocs = fileService.uploadMedia(PATH, files, docs);
            compteRenduSaved.setDocuments(newDocs);
        }
        return compteRenduSaved;
    }

    @Override
    public void supprimerCompteRendu(Long id) {
        compteRenduDao.deleteById(id);
    }

    @Override
    public CompteRenduDto validateCR(Long id, CrStateRequest crStateRequest) {
        CompteRendu compteRendu = afficherCompteRenduSelonId(id);
        compteRendu.setEstValide(crStateRequest.getEstValide());
        return compteRenduMapper.deCompteRendu(compteRendu);
    }
}
