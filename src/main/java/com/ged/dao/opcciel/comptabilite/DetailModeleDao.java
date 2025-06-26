package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.*;
import com.ged.projection.DetailModeleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DetailModeleDao extends JpaRepository<DetailModele, CleDetailModele> {
    void deleteByModeleEcriture(ModeleEcriture modeleEcriture);
//    @Query("select d from DetailModele d " +
//            "where d.modeleEcriture.codeModeleEcriture=:code and d.supprimer=false " +
//            "order by d.modeleEcriture.codeModeleEcriture asc,d.sensMvt desc,d.numeroOrdre asc")
//    List<DetailModele> liste();
    @Query("select d from DetailModele d " +
            "where d.modeleEcriture.codeModeleEcriture=:code and d.supprimer=false " +
            "order by d.modeleEcriture.codeModeleEcriture asc,d.sensMvt desc,d.numeroOrdre asc")
    List<DetailModele> liste(String code);
    @Query("select d.numCompteComptable as numCompteComptable," +
            "c.libelleCompteComptable as libelleCompteComptable," +
            "d.sensMvt as sensMvt," +
            "c.type as type," +
            "d.formule.idFormule as idFormule," +
            "d.formule.libelleFormule as libelleFormule," +
            "0 as credit," +
            "0 as debit " +
            " from DetailModele d inner join CompteComptable c on c.idCompteComptable.numCompteComptable=d.numCompteComptable " +
            "where d.modeleEcriture.codeModeleEcriture=:code and d.supprimer=false " +
            "order by d.modeleEcriture.codeModeleEcriture asc,d.numeroOrdre asc")
    List<DetailModeleProjection> listeParProjection(String code);

}
