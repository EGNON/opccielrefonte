package com.ged.dao.standard;

import com.ged.entity.standard.ModeleMsgAlerte;
import com.ged.entity.standard.TypeModele;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeModeleDao extends JpaRepository<TypeModele,Long> {
    boolean existsByLibelleTypeModele(String libelle);
}
