package com.ged.dao.crm;

import com.ged.entity.crm.ModeleObjectif;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModeleObjectifDao extends JpaRepository<ModeleObjectif,Long> {
    Page<ModeleObjectif> findByNomModeleContainsIgnoreCase(String nomModele, Pageable pageable);
}
