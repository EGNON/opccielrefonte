package com.ged.dao.standard;

import com.ged.entity.standard.TypeDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeDocumentDao extends JpaRepository<TypeDocument,Long> {
    TypeDocument findByLibelleTypeDoc(String libelle);
    Page<TypeDocument> findByLibelleTypeDocContainsIgnoreCase(String libelle,Pageable pageable);
}
