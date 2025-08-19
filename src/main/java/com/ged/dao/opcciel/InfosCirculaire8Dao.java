package com.ged.dao.opcciel;

import com.ged.entity.opcciel.InfosCirculaire8;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.Ordre;
import com.ged.projection.OrdreProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfosCirculaire8Dao extends JpaRepository<InfosCirculaire8, Long> {
    @Query(value = "select i from InfosCirculaire8 i " +
            "where i.opcvm.idOpcvm=:idOpcvm and i.supprimer=false")
    Page<InfosCirculaire8> afficherListe(Long idOpcvm,Pageable pageable);

    @Query(value = "select i from InfosCirculaire8 i " +
            "where i.opcvm.idOpcvm=:idOpcvm and i.supprimer=false")
    List<InfosCirculaire8> afficherListe(Long idOpcvm);
}
