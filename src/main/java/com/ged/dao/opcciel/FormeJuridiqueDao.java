package com.ged.dao.opcciel;

import com.ged.entity.standard.FormeJuridique;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FormeJuridiqueDao extends JpaRepository<FormeJuridique,String> {
    boolean existsByCodeFormeJuridiqueContainsIgnoreCase(String codeFormeJuridique);
    Optional<FormeJuridique> findById(String code);
    Page<FormeJuridique> findByLibelleFormeJuridiqueContainsIgnoreCaseAndSupprimer(String libelle,boolean supprimer, Pageable pageable);
    Page<FormeJuridique> findBySupprimer(boolean supprimer, Pageable pageable);
    List<FormeJuridique> findBySupprimerOrderByLibelleFormeJuridiqueAsc(boolean supprimer);
}
