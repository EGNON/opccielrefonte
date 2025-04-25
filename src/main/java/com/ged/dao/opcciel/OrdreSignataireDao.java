package com.ged.dao.opcciel;

import com.ged.entity.opcciel.CleOrdre;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.Ordre;
import com.ged.entity.opcciel.OrdreSignataire;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdreSignataireDao extends JpaRepository<OrdreSignataire, CleOrdre> {
    List<OrdreSignataire> findByOrdre(Ordre ordre);
    void deleteByOrdre(Ordre ordre);
}
