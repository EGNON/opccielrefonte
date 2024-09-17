package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Droit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface DroitDao extends JpaRepository<Droit, Long>, JpaSpecificationExecutor<Droit> {
    Optional<Droit> findByTypeVMAndIdOcc(String type, Long id);
    @Query("SELECT d FROM Droit d WHERE d.idTitre = :idTitre")
    Optional<Droit> findByIdTitre(@Param("idTitre") Long idTitre);
}
