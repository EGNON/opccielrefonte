package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailModeleDao extends JpaRepository<DetailModele, CleDetailModele> {
    void deleteByModeleEcriture(ModeleEcriture modeleEcriture);
    List<DetailModele> findByModeleEcritureAndSupprimerOrderByModeleEcritureAscSensMvtDescNumeroOrdreAsc(ModeleEcriture modeleEcriture,boolean supprimer);

}
