package com.ged.mapper.standard;

import com.ged.dto.standard.DocumentMailDto;
import com.ged.entity.standard.DocumentMail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DocumentMailMapper {
    private final DocumentMapper documentMapper;
    private final MailMapper mailMapper;

    public DocumentMailMapper(DocumentMapper documentMapper, MailMapper mailMapper) {
        this.documentMapper = documentMapper;
        this.mailMapper = mailMapper;
    }

    public DocumentMailDto deDocumentMail(DocumentMail documentMail)
    {
        DocumentMailDto documentMailDto = new DocumentMailDto();
        BeanUtils.copyProperties(documentMail, documentMailDto);
        if(documentMail.getDocument()!=null)
        {
            documentMailDto.setDocumentDto(documentMapper.deDocument(documentMail.getDocument()));
        }
        if(documentMail.getMail()!=null)
        {
            documentMailDto.setMailDto(mailMapper.deMail(documentMail.getMail()));
        }
        return documentMailDto;
    }

    public DocumentMail deDocumentMailDto(DocumentMailDto documentMailDto)
    {
        DocumentMail documentMail = new DocumentMail();
//        BeanUtils.copyProperties(documentMailDto, documentMail);
        if(documentMailDto!=null)
        {
            BeanUtils.copyProperties(documentMailDto, documentMail);
            documentMail.setDocument(documentMapper.deDocumentDto(documentMailDto.getDocumentDto()));
            documentMail.setMail(mailMapper.deMailDto(documentMailDto.getMailDto()));
        }
        return documentMail;
    }
}
