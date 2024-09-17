package com.ged.dao.crm;

import com.ged.entity.crm.Indicateur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
public interface IndicateurDao extends JpaRepository<Indicateur,Long> {
    Indicateur findByLibelle(String libelle);
    Page<Indicateur> findByCodeContainsIgnoreCaseOrLibelleContainsIgnoreCase(String code,String libelle, Pageable pageable);
}
