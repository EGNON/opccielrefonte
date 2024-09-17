package com.ged.service.standard.impl;

import com.ged.dao.crm.CompteRenduDao;
import com.ged.dao.standard.DocumentDao;
import com.ged.dao.crm.RDVDao;
import com.ged.dao.standard.DocumentMailDao;
import com.ged.dao.standard.MailDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dto.standard.DocumentDto;
import com.ged.dto.standard.DocumentMailDto;
import com.ged.entity.crm.CompteRendu;
import com.ged.entity.standard.*;
import com.ged.entity.crm.RDV;
import com.ged.mapper.standard.DocumentMapper;
import com.ged.mapper.standard.MailMapper;
import com.ged.service.standard.DocumentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@Component
public class DocumentServiceImpl implements DocumentService {
    private final DocumentDao documentDao;
    private final DocumentMailDao documentMailDao;
    private final RDVDao rdvDao;
    private final MailDao mailDao;
    private final MailMapper mailMapper;
    private final CompteRenduDao compteRenduDao;
    private final PersonneDao personneDao;
    private final DocumentMapper documentMapper;
    @Value("${file.upload-dir}")
    private String chemin;
    @Value("${media.upload.dir}")
    public String PATH;
    private final FileServiceImpl fileService;
    @Autowired
    private  CustomMultipartFile customMultipartFile;

    public DocumentServiceImpl(DocumentDao documentDao, DocumentMailDao documentMailDao, RDVDao rdvDao, MailDao mailDao, MailMapper mailMapper, CompteRenduDao compteRenduDao, PersonneDao personneDao, DocumentMapper documentMapper, FileServiceImpl fileService) {
        this.documentDao = documentDao;
        this.documentMailDao = documentMailDao;

        this.rdvDao = rdvDao;
        this.mailDao = mailDao;
        this.mailMapper = mailMapper;
        this.compteRenduDao = compteRenduDao;
        this.personneDao = personneDao;
        this.documentMapper = documentMapper;

        this.fileService = fileService;
    }

    @Override
    public Page<DocumentDto> afficherDocuments(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Document> documentPage = documentDao.findAll(pageRequest);
        return new PageImpl<>(documentPage.getContent().stream().map(documentMapper::deDocument).collect(Collectors.toList()), pageRequest, documentPage.getTotalElements());
    }

    @Override
    public List<DocumentDto> afficherTous() {
        return documentDao.findAll().stream().map(documentMapper::deDocument).collect(Collectors.toList());
    }

    @Override
    public List<DocumentDto> afficherDocumentSelonRDV(long idRdv) {
        RDV rdv=rdvDao.findById(idRdv);
        return documentDao.findByRdv(rdv).stream().map(documentMapper::deDocument).collect(Collectors.toList());
    }

    @Override
    public List<DocumentDto> afficherDocumentSelonCR(long idCr) {
        CompteRendu compteRendu=compteRenduDao.findById(idCr);
        return documentDao.findByCompteRendu(compteRendu).stream().map(documentMapper::deDocument).collect(Collectors.toList());
    }

    @Override
    public List<DocumentDto> afficherDocumentSelonPersonne(long idPersonne) {
        Personne personne=personneDao.findById(idPersonne).orElseThrow();
        return documentDao.findByPersonne(personne).stream().map(documentMapper::deDocument).collect(Collectors.toList());
    }

    @Override
    public Document afficherDocumentSelonId(long idDocument) {
        return documentDao.findById(idDocument).orElseThrow(() -> new EntityNotFoundException("Document avec ID " + idDocument + " introuvable"));
    }

    @Override
    public byte[] uploadDocument(long idDocument) throws IOException {
        DocumentDto documentDto=documentMapper.deDocument(afficherDocumentSelonId(idDocument));
        File file;

        byte[] tab=null;//new byte[2];
        if(documentDto!=null) {
             file = new File(documentDto.getChemin());
            tab = Files.readAllBytes(Paths.get(file.toURI()));
//            System.out.println(Arrays.toString(tab));
        }

        return tab;
    }

    @Override
    public DocumentDto creerDocument(DocumentDto documentDto) throws Throwable {
        Document document = documentMapper.deDocumentDto(documentDto);
//        System.out.println(documentDto.getfToByte());
        document.setChemin(chemin+document.getNomDoc()+"."+document.getExtensionDoc());
        Document documentSaved = documentDao.save(document);
        File f = new File(PATH);

//            customMultipartFile.transferTo(document.getChemin(),documentDto.getfToByte());
//            System.out.println("Le fichier n'existe pas");
//        }
//        else
//        {
////            System.out.println("le fichier existe");
//        }
        DocumentDto documentDto1=documentMapper.deDocument(documentSaved);
        Set<DocumentDto> documentDtoList=new HashSet<>();
        documentDto1.setfToByte(documentDto.getfToByte());
        documentDtoList.add(documentDto1);
//        if(!f.exists() && !f.isDirectory())
//        {
        //C'est un fichier
        if(documentDto.getfToByte()!=null)
            fileService.uploadMedia(PATH,documentDtoList);

        return documentDto1;

    }

    @Override
    public void creerDocument(DocumentDto[] documentDto,Long idMail) throws Throwable {
        for(DocumentDto o:documentDto){
            DocumentDto documentDto1= creerDocument(o);
            DocumentMail documentMail=new DocumentMail();
            CleDocumentMail cleDocumentMail=new CleDocumentMail();
            cleDocumentMail.setIdDoc(o.getIdDoc());
            cleDocumentMail.setIdMail(idMail);
            documentMail.setId(cleDocumentMail);
            documentMail.setDocument(documentMapper.deDocumentDto(documentDto1));
            Mail mail=mailDao.findById(idMail).orElseThrow();
            documentMail.setMail(mail);
            documentMailDao.save(documentMail);
        }
    }

    @Override
    public DocumentDto modifierDocument(DocumentDto documentDto) {
        Document document = documentMapper.deDocumentDto(documentDto);
        document.setChemin(chemin+document.getNomDoc()+"."+document.getExtensionDoc());
        Document documentMaj = documentDao.save(document);
        return documentMapper.deDocument(documentMaj);
    }

    @Override
    public void supprimerDocument(long id) {
        documentDao.deleteById(id);
    }

    @Override
    public void supprimerDocumentSelonRDV(long idRdv) {
        RDV rdv=rdvDao.findById(idRdv);
        documentDao.deleteByRdv(rdv);
    }
}
