package com.ged.service.standard.impl;

import com.ged.dao.standard.DocumentMailDao;
import com.ged.dao.standard.MailDao;
import com.ged.dto.standard.DocumentMailDto;
import com.ged.entity.standard.CleDocumentMail;
import com.ged.entity.standard.DocumentMail;
import com.ged.entity.standard.Mail;
import com.ged.mapper.standard.DocumentMailMapper;
import com.ged.mapper.standard.DocumentMapper;
import com.ged.mapper.standard.MailMapper;
import com.ged.service.standard.DocumentMailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentMailServiceImpl implements DocumentMailService {
    private final DocumentMailDao documentMailDao;
    private final MailDao mailDao;
    private final DocumentMailMapper documentMailMapper;
    private final MailMapper mailMapper;
    private final DocumentMapper documentMapper;

    public DocumentMailServiceImpl(DocumentMailDao documentMailDao, MailDao mailDao, DocumentMailMapper documentMailMapper, MailMapper mailMapper, DocumentMapper documentMapper) {
        this.documentMailDao = documentMailDao;
        this.mailDao = mailDao;
        this.documentMailMapper = documentMailMapper;
        this.mailMapper = mailMapper;
        this.documentMapper = documentMapper;
    }

    @Override
    public Page<DocumentMailDto> afficherDocumentMails(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DocumentMail> documentMailPage = documentMailDao.findAll(pageRequest);
        return new PageImpl<>(documentMailPage.getContent().stream().map(documentMailMapper::deDocumentMail).collect(Collectors.toList()), pageRequest, documentMailPage.getTotalElements());
    }

    @Override
    public DocumentMail afficherDocumentMailSelonId(CleDocumentMail idDocumentMail) {
        return documentMailDao.findById(idDocumentMail).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
    }

    @Override
    public List<DocumentMail> afficherDocumentMailSelonMail(long idMail) {
//        System.out.println("idMail=="+idMail);
        Mail mail=mailDao.findById(idMail).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
//        System.out.println(mail.getIdMail());
        List<DocumentMail> documentMails=documentMailDao.findByMail(mail);
//        System.out.println("documentsMAil=="+documentMails.size());
        return documentMails;
    }

    @Override
    public DocumentMailDto creerDocumentMail(DocumentMailDto documentMailDto) {
        DocumentMail documentMail = documentMailMapper.deDocumentMailDto(documentMailDto);
        CleDocumentMail cleDocumentMail= new CleDocumentMail();
        cleDocumentMail.setIdDoc(documentMailDto.getDocumentDto().getIdDoc());
        cleDocumentMail.setIdMail(documentMailDto.getMailDto().getIdMail());
        documentMail.setId(cleDocumentMail);
        if(documentMailDto.getDocumentDto() != null)
        {
            documentMail.setDocument(documentMapper.deDocumentDto(documentMailDto.getDocumentDto()));
        }
        if(documentMailDto.getMailDto() != null)
        {
            documentMail.setMail(mailMapper.deMailDto(documentMailDto.getMailDto()));
        }
        DocumentMail documentMailSaved = documentMailDao.save(documentMail);
//        System.out.println("chemin=="+documentMailSaved.getDocument().getChemin());
        return documentMailMapper.deDocumentMail(documentMailSaved);
    }

    @Override
    public DocumentMailDto modifierDocumentMail(DocumentMailDto documentMailDto) {
        CleDocumentMail cleDocumentMail = new CleDocumentMail();
        cleDocumentMail.setIdMail(documentMailDto.getMailDto().getIdMail());
        cleDocumentMail.setIdDoc(documentMailDto.getDocumentDto().getIdDoc());
        DocumentMail documentMail = afficherDocumentMailSelonId(cleDocumentMail);
        if(documentMail.getId() == null)
            return documentMailDto;
        documentMail = documentMailMapper.deDocumentMailDto(documentMailDto);
        documentMail = documentMailDao.save(documentMail);
        return documentMailMapper.deDocumentMail(documentMail);
    }

    @Override
    public void supprimerDocumentMail(CleDocumentMail cleDocumentMail) {
        documentMailDao.deleteById(cleDocumentMail);
    }
}
