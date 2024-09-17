package com.ged.dao.standard;

import com.ged.entity.standard.ModeleMsgAlerte;
import com.ged.entity.standard.TypeModele;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ModeleMsgAlerteDao extends JpaRepository<ModeleMsgAlerte,Long> {
    @Modifying()
    @Query(value = "update ModeleMsgAlerte m " +
            "set m.defaut=false " +
            "where m.typeModele.idTypeModele=:idModele")
    int modifier(Long idModele);

    @Query(value = "select m " +
            "from ModeleMsgAlerte m " +
            "where m.defaut=true " +
            "and m.typeModele.libelleTypeModele=:libelle")
    ModeleMsgAlerte recherherSelonTypeModeleEtDefaut(String libelle);

    @Query(value = "select m " +
            "from ModeleMsgAlerte m " +
            "where m.typeModele.libelleTypeModele=:libelle")
    List<ModeleMsgAlerte> rechercherSelonTypeModele(String libelle);
}
