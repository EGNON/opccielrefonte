package com.ged.service.standard.impl;

import com.ged.dao.standard.*;
import com.ged.dto.standard.DocumentDto;
import com.ged.dto.standard.DocumentMailDto;
import com.ged.dto.standard.EnvoiMailDto;
import com.ged.dto.standard.MailDto;
import com.ged.entity.standard.*;
import com.ged.mapper.standard.MailMapper;
import com.ged.service.standard.DocumentService;
import com.ged.service.standard.MailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class MailServiceImpl implements MailService {
    private final MailDao mailDao;
    private final DocumentMailDao documentMailDao;
    private final EnvoiMailDao envoiMailDao;
    private final PersonneDao personneDao;
    private final DocumentDao documentDao;
    private final DocumentService documentService;
    private final ModeleMsgAlerteDao modeleMsgAlerteDao;
    private final MailMapper mailMapper;
    @Value("${media.upload.dir}")
    public String PATH;

    public MailServiceImpl(MailDao mailDao, DocumentMailDao documentMailDao, EnvoiMailDao envoiMailDao, PersonneDao personneDao, DocumentDao documentDao, DocumentService documentService, ModeleMsgAlerteDao modeleMsgAlerteDao, MailMapper mailMapper) {
        this.mailDao = mailDao;
        this.documentMailDao = documentMailDao;
        this.envoiMailDao = envoiMailDao;
        this.personneDao = personneDao;
        this.documentDao = documentDao;
        this.documentService = documentService;
        this.modeleMsgAlerteDao = modeleMsgAlerteDao;
        this.mailMapper = mailMapper;
    }

    @Override
    public Page<MailDto> afficherMails(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"dateEnvoi").and(Sort.by(Sort.Direction.DESC,"heureEnvoi"));
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Mail> mailPage=mailDao.findAll(pageRequest);
        return new PageImpl<>(mailPage.getContent().stream().map(mailMapper::deMail).collect(Collectors.toList()),pageRequest,mailPage.getTotalElements());
    }

    @Override
    public Mail afficherMailSelonId(long idMail) {
        return mailDao.findById(idMail).orElseThrow(()-> new EntityNotFoundException("Mail avec ID "+idMail+" est introuvable"));
    }

    @Override
    public MailDto creerMail(MailDto mailDto) throws Throwable {
        Mail mail=mailMapper.deMailDto(mailDto);
        Mail mailSave=mailDao.save(mail);


        if(mailDto.getDocumentMailDtos()!=null){
            for(DocumentMailDto documentMailDto:mailDto.getDocumentMailDtos()){
                DocumentDto documentDto=new DocumentDto();

                DocumentMail documentMail=new DocumentMail();

                documentMail.setMail(mailSave);
                if(documentMailDto.getDocumentDto()!=null){
                    documentDto=documentService.creerDocument(documentMailDto.getDocumentDto());
                    Document document=documentDao.findById(documentDto.getIdDoc()).orElseThrow();
                    documentMail.setDocument(document);
                }
                CleDocumentMail cleDocumentMail=new CleDocumentMail();
                cleDocumentMail.setIdMail(mailSave.getIdMail());
                cleDocumentMail.setIdDoc(documentDto.getIdDoc());
                documentMail.setId(cleDocumentMail);

                documentMailDao.save(documentMail);
            }
        }

        if(mailDto.getEnvoiMailDtos()!=null){
            for(EnvoiMailDto envoiMailDto:mailDto.getEnvoiMailDtos()){
                EnvoiMail envoiMail=new EnvoiMail();

                CleEnvoiMail cleEnvoiMail=new CleEnvoiMail();
                cleEnvoiMail.setIdMail(mailSave.getIdMail());
                cleEnvoiMail.setIdPersonne(envoiMailDto.getPersonneDto().getIdPersonne());
                envoiMail.setIdEnvoi(cleEnvoiMail);

                envoiMail.setMail(mailSave);
                if(envoiMailDto.getPersonneDto()!=null){
                    Personne personne=personneDao.findById(envoiMailDto.getPersonneDto().getIdPersonne()).orElseThrow();
                    envoiMail.setPersonne(personne);
                }
                envoiMailDao.save(envoiMail);
            }
        }
        return mailMapper.deMail(mailSave);
    }

    @Override
    public MailDto modifierMail(MailDto mailDto) throws Throwable {
        Mail mail=mailMapper.deMailDto(mailDto);
        Mail mailMaj=mailDao.save(mail);

        if(mailDto.getDocumentMailDtos()!=null){
            for(DocumentMailDto documentMailDto:mailDto.getDocumentMailDtos()){
                DocumentDto documentDto=new DocumentDto();

                DocumentMail documentMail=new DocumentMail();

                documentMail.setMail(mailMaj);
                if(documentMailDto.getDocumentDto()!=null){
                    documentDto=documentService.creerDocument(documentMailDto.getDocumentDto());
                    Document document=documentDao.findById(documentDto.getIdDoc()).orElseThrow();
                    documentMail.setDocument(document);
                }
                CleDocumentMail cleDocumentMail=new CleDocumentMail();
                cleDocumentMail.setIdMail(mailMaj.getIdMail());
                cleDocumentMail.setIdDoc(documentDto.getIdDoc());
                documentMail.setId(cleDocumentMail);

                documentMailDao.save(documentMail);
            }
        }

        if(mailDto.getEnvoiMailDtos()!=null){
            for(EnvoiMailDto envoiMailDto:mailDto.getEnvoiMailDtos()){
                EnvoiMail envoiMail=new EnvoiMail();

                CleEnvoiMail cleEnvoiMail=new CleEnvoiMail();
                cleEnvoiMail.setIdMail(mailMaj.getIdMail());
                cleEnvoiMail.setIdPersonne(envoiMailDto.getPersonneDto().getIdPersonne());
                envoiMail.setIdEnvoi(cleEnvoiMail);

                envoiMail.setMail(mailMaj);
                if(envoiMailDto.getPersonneDto()!=null){
                    Personne personne=personneDao.findById(envoiMailDto.getPersonneDto().getIdPersonne()).orElseThrow();
                    envoiMail.setPersonne(personne);
                }
                envoiMailDao.save(envoiMail);
            }
        }
        return mailMapper.deMail(mailMaj);
    }

    @Override
    public void supprimerMail(long idMail) {
//        Mail mail=mailDao.findById(idMail).orElseThrow(()->new EntityNotFoundException("Element introuvable"));
//        envoiMailDao.deleteByMail(mail);
//        documentMailDao.deleteByMail(mail);
        mailDao.deleteById(idMail);
    }

    @Override
    public void supprimerMailSelonModeleMsgAlerte(long idModeleMsgAlerte) {
        ModeleMsgAlerte modeleMsgAlerte=modeleMsgAlerteDao.findById(idModeleMsgAlerte).orElseThrow();
        mailDao.deleteByModeleMsgAlerte(modeleMsgAlerte);
    }


}
