package com.ged.service.standard;

import com.ged.dto.standard.DocumentDto;
import com.ged.entity.standard.Document;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public interface DocumentService  {
    Page<DocumentDto> afficherDocuments(int page, int size);
    List<DocumentDto> afficherTous();
    List<DocumentDto> afficherDocumentSelonRDV(long idRdv);
    List<DocumentDto> afficherDocumentSelonCR(long idCr);
    List<DocumentDto> afficherDocumentSelonPersonne(long idPersonne);
    Document afficherDocumentSelonId(long idDocument);
    byte[] uploadDocument(long idDocument) throws IOException;
    DocumentDto creerDocument(DocumentDto documentDto) throws Throwable;
    void creerDocument(DocumentDto[] documentDto,Long idMail) throws Throwable;
    DocumentDto modifierDocument(DocumentDto documentDto);
    void supprimerDocument(long idDocument);
    void supprimerDocumentSelonRDV(long idRdv);

}
