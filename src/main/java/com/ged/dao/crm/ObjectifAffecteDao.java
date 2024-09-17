package com.ged.dao.crm;

import com.ged.entity.crm.CleObjectifAffecte;
import com.ged.entity.crm.ObjectifAffecte;
import com.ged.projection.StatistiqueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObjectifAffecteDao extends JpaRepository<ObjectifAffecte, CleObjectifAffecte> {
    @Query(value = "select oa from ObjectifAffecte as oa " +
            "inner join Affectation as a on a.idAffectation= oa.idObjectifAffecte.idAffectation " +
            "where oa.periodicite.idPeriodicite = :idPeriodicite " +
            "and a.personnel.idPersonne=:idPersonne")
    List<ObjectifAffecte> afficherSelonPersonnelEtPeriodicite(long idPersonne,long idPeriodicite);

    @Query(value = "select sum (oa.valeurReelle)*100/sum(oa.valeurAffecte) as  valeurReelle, " +
            "p.denomination as denomination  " +
            "from ObjectifAffecte  oa " +
            "inner join Personnel p on p.idPersonne=oa.affectation.personnel.idPersonne " +
            "where substring(cast( oa.affectation.dateAffectation as string ),1,4)=:annee " +
            "or :annee is null " +
            "group by denomination")
    List<StatistiqueProjection> niveauEvolutionObjectif(String annee);
}
