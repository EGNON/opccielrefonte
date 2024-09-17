package com.ged.mapper.standard;

import com.ged.dto.standard.DocumentMailDto;
import com.ged.dto.standard.EnvoiMailDto;
import com.ged.dto.standard.MailDto;
import com.ged.entity.standard.DocumentMail;
import com.ged.entity.standard.EnvoiMail;
import com.ged.entity.standard.Mail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class MailMapper {
    private final ModeleMsgAlerteMapper modeleMsgAlerteMapper;
    private final DocumentMapper documentMapper;
    private final PersonneMapper personneMapper;

    public MailMapper(ModeleMsgAlerteMapper modeleMsgAlerteMapper, DocumentMapper documentMapper, PersonneMapper personneMapper) {
        this.modeleMsgAlerteMapper = modeleMsgAlerteMapper;
        this.documentMapper = documentMapper;
        this.personneMapper = personneMapper;
    }

    public MailDto deMail(Mail mail)
    {
        MailDto mailDto = new MailDto();
        BeanUtils.copyProperties(mail, mailDto);
        if(mail.getModeleMsgAlerte()!=null){
            mailDto.setModeleMsgAlerteDto(modeleMsgAlerteMapper.deModeleMsgAlerte(mail.getModeleMsgAlerte()));
        }
        if(mail.getDocumentMails() != null)
        {
            Set<DocumentMailDto> documentMailDtoSet=new HashSet<>();
            mail.getDocumentMails().forEach(documentMail -> {
                DocumentMailDto documentMailDto=new DocumentMailDto();
                if(documentMail.getDocument()!=null) {
                    documentMailDto.setDocumentDto(documentMapper.deDocument(documentMail.getDocument()));
                }
                if(documentMail.getMail()!=null) {
                    MailDto mailDto1=new MailDto();
                    BeanUtils.copyProperties(documentMail.getMail(), mailDto1);
                    documentMailDto.setMailDto(mailDto1);
                }
                documentMailDtoSet.add(documentMailDto);
            });
            mailDto.setDocumentMailDtos(documentMailDtoSet);
        }

        if(mail.getEnvoiMails() != null)
        {
            Set<EnvoiMailDto> envoiMailDtoSet=new HashSet<>();
            mail.getEnvoiMails().forEach(envoiMail -> {
                EnvoiMailDto envoiMailDto=new EnvoiMailDto();
                if(envoiMail.getPersonne()!=null) {
                    envoiMailDto.setPersonneDto(personneMapper.dePersonne(envoiMail.getPersonne()));
                }
                if(envoiMail.getMail()!=null) {
                    MailDto mailDto1=new MailDto();
                    BeanUtils.copyProperties(envoiMail.getMail(), mailDto1);
                    envoiMailDto.setMailDto(mailDto1);
                }
                envoiMailDtoSet.add(envoiMailDto);
            });
            mailDto.setEnvoiMailDtos(envoiMailDtoSet);
        }
        return mailDto;
    }

    public Mail deMailDto(MailDto mailDto)
    {
        Mail mail = new Mail();
        BeanUtils.copyProperties(mailDto, mail);
        if(mailDto.getModeleMsgAlerteDto()!=null){
            mail.setModeleMsgAlerte(modeleMsgAlerteMapper.deModeleMsgAlerteDto(mailDto.getModeleMsgAlerteDto()));
        }

        if(mailDto.getDocumentMailDtos() != null)
        {
            Set<DocumentMail> documentMailSet=new HashSet<>();
            mailDto.getDocumentMailDtos().forEach(documentMailDto -> {
                DocumentMail documentMail=new DocumentMail();
                if(documentMailDto.getDocumentDto()!=null) {
                    documentMail.setDocument(documentMapper.deDocumentDto(documentMailDto.getDocumentDto()));
                }
                if(documentMailDto.getMailDto()!=null) {
                    Mail mail1=new Mail();
                    BeanUtils.copyProperties(documentMailDto.getMailDto(), mail1);
                    documentMail.setMail(mail1);
                }
                documentMailSet.add(documentMail);
            });
            mail.setDocumentMails(documentMailSet);
        }

        if(mailDto.getEnvoiMailDtos() != null)
        {
            Set<EnvoiMail> envoiMailSet=new HashSet<>();
            mailDto.getEnvoiMailDtos().forEach(envoiMailDto -> {
                EnvoiMail envoiMail=new EnvoiMail();
                if(envoiMailDto.getPersonneDto()!=null) {
                    envoiMail.setPersonne(personneMapper.dePersonneDto(envoiMailDto.getPersonneDto()));
                }
                if(envoiMailDto.getMailDto()!=null) {
                    Mail mail1=new Mail();
                    BeanUtils.copyProperties(envoiMailDto.getMailDto(), mail1);
                    envoiMail.setMail(mail1);
                }
                envoiMailSet.add(envoiMail);
            });
            mail.setEnvoiMails(envoiMailSet);
        }
        return mail;
    }
}
