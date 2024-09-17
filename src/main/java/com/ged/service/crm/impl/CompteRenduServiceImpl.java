package com.ged.service.crm.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.crm.CompteRenduDao;
import com.ged.dao.security.UtilisateurDao;
import com.ged.dto.crm.CompteRenduDto;
import com.ged.dto.standard.CrStateRequest;
import com.ged.dto.standard.DocumentDto;
import com.ged.dto.crm.RDVDto;
import com.ged.entity.crm.CompteRendu;
import com.ged.entity.security.Utilisateur;
import com.ged.mapper.crm.CompteRenduMapper;
import com.ged.mapper.standard.DocumentMapper;
import com.ged.service.crm.CompteRenduService;
import com.ged.service.standard.FileService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompteRenduServiceImpl implements CompteRenduService {
    @Value("${media.upload.dir}")
    public String PATH;
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
    public List<CompteRenduDto> afficherTousEtat() {
        return compteRenduDao.findAll().stream().map(compteRenduMapper::deCompteRendu).collect(Collectors.toList());
    }

    @Override
    public List<CompteRenduDto> afficherCompteRenduSelonUtilisateur(long idUtilisateur) {
        Utilisateur utilisateur=utilisateurDao.findById(idUtilisateur);
        return compteRenduDao.findByCreateur(utilisateur).stream().map(compteRenduMapper::deCompteRendu).collect(Collectors.toList());
    }

    @Override
    public List<CompteRenduDto> afficherCompteRenduSelonRealisation(long idUtilisateur, LocalDateTime dateDeb, LocalDateTime dateFin) {
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
