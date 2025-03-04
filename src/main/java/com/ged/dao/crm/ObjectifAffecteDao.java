package com.ged.dao.crm;

import com.ged.entity.crm.CleObjectifAffecte;
import com.ged.entity.crm.ObjectifAffecte;
import com.ged.projection.ObjectifAffecteProjection;
import com.ged.projection.StatistiqueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ObjectifAffecteDao extends JpaRepository<ObjectifAffecte, CleObjectifAffecte> {
    @Query(value = "select oa from ObjectifAffecte as oa " +
            "inner join Affectation as a on a.idAffectation= oa.idObjectifAffecte.idAffectation " +
            "where a.dateAffectation between :dateDebut and :dateFin " +
            "and a.personnel.idPersonne=:idPersonne")
    List<ObjectifAffecte> afficherSelonPersonnelEtPeriodicite(long idPersonne, LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select " +
            "a.dateAffectation  as dateAffectation," +
            "denomination as denomination," +
            "ca.libelle as libelleCategorie," +
            "i.libelle as libelleIndicateur," +
            "pe.libelle as libellePeriodicite," +
            "oa.valeurAffecte as valeurAffecte," +
            "oa.valeurReelle as valeurReelle " +
            "from ObjectifAffecte as oa " +
            "inner join Affectation as a on a.idAffectation= oa.idObjectifAffecte.idAffectation " +
            "inner join CategoriePersonne ca on ca.idCategorie=oa.categoriePersonne.idCategorie "+
            "inner join Indicateur i on i.idIndicateur=oa.indicateur.idIndicateur "+
            "inner join Periodicite pe on pe.idPeriodicite=oa.periodicite.idPeriodicite "+
            "inner join Personne p on p.idPersonne=a.personnel.idPersonne "+
            "where a.dateAffectation between :dateDebut and :dateFin " +
            "and a.personnel.idPersonne=:idPersonne")
    List<ObjectifAffecteProjection> afficherSelonPersonnelEtPeriodiciteEtat(long idPersonne, LocalDateTime dateDebut,LocalDateTime dateFin);

    @Query(value = "select sum (oa.valeurReelle)*100/sum(oa.valeurAffecte) as  valeurReelle, " +
            "p.denomination as denomination  " +
            "from ObjectifAffecte  oa " +
            "inner join Personnel p on p.idPersonne=oa.affectation.personnel.idPersonne " +
            "where substring(cast( oa.affectation.dateAffectation as string ),1,4)=:annee " +
            "or :annee is null " +
            "group by denomination")
    List<StatistiqueProjection> niveauEvolutionObjectif(String annee);
}
