package com.ged.service.standard;

import com.ged.dto.standard.TypeDocumentDto;
import com.ged.entity.standard.TypeDocument;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TypeDocumentService  {
    Page<TypeDocumentDto> afficherTypeDocuments(int page, int size);
    List<TypeDocumentDto> afficherTypeDocumentsTous();
    TypeDocument afficherTypeDocumentSelonId(long idTypeDocument);
    TypeDocumentDto afficherTypeDocument(long idTypeDocument);
    TypeDocumentDto rechercherTypeDocumentParLibelle(String libelle);
    TypeDocumentDto creerTypeDocument(TypeDocumentDto typeDocumentDto);
    TypeDocumentDto modifierTypeDocument(TypeDocumentDto typeDocumentDto);
    void supprimerTypeDocument(long idTypeDocument);
}
