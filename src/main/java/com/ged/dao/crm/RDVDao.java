package com.ged.dao.crm;

import com.ged.entity.crm.RDV;
import com.ged.entity.standard.ModeleMsgAlerte;
import com.ged.projection.RDVProjection;
import com.ged.projection.StatistiqueProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public interface RDVDao extends JpaRepository<RDV,Long> {
    List<RDV> findByModeleMsgAlerte(ModeleMsgAlerte modeleMsgAlerte);

    @Query(value = "SELECT  " +
            "COALESCE(pm.raisonSociale, CONCAT(pp.nom, ' ', pp.prenom)) AS denomination," +
            "r.idRdv as idRdv," +
            "r.dateDebRdv as dateDebRdv," +
            "r.dateFinRdv as dateFinRdv," +
            "r.heureFinRdv as heureFinRdv," +
            "r.heureDebutRdv as heureDebutRdv," +
            "r.dateDebReelle as dateDebReelle," +
            "r.dateFinReelle as dateFinReelle," +
            "r.heureFinReelle as heureFinReelle," +
            "r.heureDebReelle as heureDebReelle," +
            "r.objetRdv as objetRdv," +
            "c as commune,"+
            "r.pays as pays, "+
            "r.personne as personne, "+
            "r.quartier as quartier " +
            "from RDV as r " +
            "left outer join PersonnePhysique pp ON r.personne.idPersonne=pp.idPersonne " +
            "left outer join PersonneMorale pm on r.personne.idPersonne=pm.idPersonne "+
            "inner join Pays p on p.idPays=r.pays.idPays "+
            "inner join Quartier q on q.idQuartier=r.quartier.idQuartier "+
            "inner join Arrondissement a on a.idArrondissement=q.arrondissement.idArrondissement "+
            "inner join Commune c on c.idCommune=a.commune.idCommune " +
            "order by r.dateDebRdv desc,r.dateFinRdv desc,r.heureDebutRdv desc,r.heureFinRdv desc,r.objetRdv asc")
    Page<RDVProjection> afficherRDV(Pageable pageRequest);
    @Query(value = "SELECT  " +
            "COALESCE(pm.raisonSociale, CONCAT(pp.nom, ' ', pp.prenom)) AS denomination," +
            "r.idRdv as idRdv," +
            "r.dateDebRdv as dateDebRdv," +
            "r.dateFinRdv as dateFinRdv," +
            "r.heureFinRdv as heureFinRdv," +
            "r.heureDebutRdv as heureDebutRdv," +
            "r.dateDebReelle as dateDebReelle," +
            "r.dateFinReelle as dateFinReelle," +
            "r.heureFinReelle as heureFinReelle," +
            "r.heureDebReelle as heureDebReelle," +
            "r.objetRdv as objetRdv," +
            "c as commune,"+
            "r.pays as pays, "+
            "r.personne as personne, "+
            "r.quartier as quartier " +
            "from RDV as r " +
            "left outer join PersonnePhysique pp ON r.personne.idPersonne=pp.idPersonne " +
            "left outer join PersonneMorale pm on r.personne.idPersonne=pm.idPersonne "+
            "inner join Pays p on p.idPays=r.pays.idPays "+
            "inner join Quartier q on q.idQuartier=r.quartier.idQuartier "+
            "inner join Arrondissement a on a.idArrondissement=q.arrondissement.idArrondissement "+
            "inner join Commune c on c.idCommune=a.commune.idCommune " +
            "where r.dateDebReelle is not null "+
            "order by r.dateDebRdv desc,r.dateFinRdv desc,r.heureDebutRdv desc,r.heureFinRdv desc,r.objetRdv asc")
    List<RDVProjection> afficherRDV();

    @Query(value = "SELECT  " +
            "COALESCE(pm.raisonSociale, CONCAT(pp.nom, ' ', pp.prenom)) AS denomination," +
            "r.idRdv as idRdv," +
            "r.dateDebRdv as dateDebRdv," +
            "r.dateFinRdv as dateFinRdv," +
            "r.heureFinRdv as heureFinRdv," +
            "r.heureDebutRdv as heureDebutRdv," +
            "r.dateDebReelle as dateDebReelle," +
            "r.dateFinReelle as dateFinReelle," +
            "r.heureFinReelle as heureFinReelle," +
            "r.heureDebReelle as heureDebReelle," +
            "r.objetRdv as objetRdv," +
            "r.modeleMsgAlerte as modeleMsgAlerte,"+
            "c as commune,"+
            "r.pays as pays, "+
            "r.personne as personne, "+
            "r.quartier as quartier " +
            "from RDV as r " +
            "left outer join PersonnePhysique pp ON r.personne.idPersonne=pp.idPersonne " +
            "left outer join PersonneMorale pm on r.personne.idPersonne=pm.idPersonne "+
            "inner join Pays p on p.idPays=r.pays.idPays "+
            "inner join Quartier q on q.idQuartier=r.quartier.idQuartier "+
            "inner join Arrondissement a on a.idArrondissement=q.arrondissement.idArrondissement "+
            "inner join Commune c on c.idCommune=a.commune.idCommune " +
            "where r.idRdv=:idRdv")
    RDVProjection afficherRDVSelonId(long idRdv);
    @Query(value = "update RDV set "+
                    "dateDebReelle=:mDateDebReelle," +
                    "dateFinReelle=:mDateFinReelle," +
                    "heureDebReelle=:mHeureDebReelle," +
                    "heureFinReelle=:mHeureFinReelle " +
                    "where idRdv=:mIdRdv")
    @Modifying
    int updateRDV(long mIdRdv,Date mDateDebReelle, Time mHeureDebReelle,Date mDateFinReelle,Time mHeureFinReelle);
    RDV findById(long id);

    /*@Query(value = "SELECT  count(r.idRdv) as nbre, " +
            "substring(cast(r.dateDebRdv as string ) ,6,2) as mois "+
            "from RDV as r "+
            "where substring(cast( r.dateDebRdv as string ),1,4)=:annee " +
//            "and r.dateDebReelle  is null "+
            "group by mois")
    List<StatistiqueProjection> afficherNbreRDVParMois(String annee);*/
    @Query(value = "SELECT  count(r.idRdv) as nbre, " +
            "EXTRACT(MONTH FROM r.dateDebRdv) as mois "+
            "from RDV as r "+
            "where year(r.dateDebRdv) = ?1 " +
            "group by month(r.dateDebRdv)")
    List<StatistiqueProjection> afficherNbreRDVParMois(String annee);

    @Query(value = "SELECT count(r.idRdv) as nbre " +
            "from RDV as r "+
            "where r.dateDebReelle  is null ")
    List<StatistiqueProjection> afficherNbreRDVEnCours();

    Long countByDateDebReelleIsNull();
}
