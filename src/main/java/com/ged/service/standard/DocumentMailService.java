package com.ged.service.standard;

import com.ged.dto.standard.DocumentMailDto;
import com.ged.entity.standard.CleDocumentMail;
import com.ged.entity.standard.DocumentMail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DocumentMailService {
    Page<DocumentMailDto> afficherDocumentMails(int page, int size);
    DocumentMail afficherDocumentMailSelonId(CleDocumentMail idDocumentMail);
    List<DocumentMail> afficherDocumentMailSelonMail(long idMail);
    DocumentMailDto creerDocumentMail(DocumentMailDto documentMailDto);
    DocumentMailDto modifierDocumentMail(DocumentMailDto documentMailDto);
    void supprimerDocumentMail(CleDocumentMail idDocumentMail);
}
