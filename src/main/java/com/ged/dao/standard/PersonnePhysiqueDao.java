package com.ged.dao.standard;

import com.ged.entity.standard.PersonnePhysique;
import com.ged.projection.FicheKYCProjection;
import com.ged.projection.NumOrdreProjection;
import com.ged.projection.PersonnePhysiqueProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PersonnePhysiqueDao extends JpaRepository<PersonnePhysique, Long> {
    @Query(value = "select ph from PersonnePhysique as ph where concat(ph.nom, ' ', ph.prenom) like %:nom%")
    Page<PersonnePhysique> rechercherPersonneParNom(@Param("nom") String nom, Pageable pageable);

    @Query(value = "select max(p.numOrdre) as numOrdre" +
                    " " +
                    "from PersonnePhysique p " +
                    "inner join SousTypeClient s on s.idSousTypeClient=p.sousTypeClient.idSousTypeClient " +
                    "inner join TypeClient t on t.idTypeClient=s.typeClient.idTypeClient " +
                    "where t.intitule like '%personne physique%'")
    NumOrdreProjection maxNumordre();

    @Query(value = "select ph from PersonnePhysique as ph inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = ph.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite where q.libelleQualite = :qualite " +
            "order by ph.nom asc,ph.prenom asc")
    List<PersonnePhysique> afficherPersonnePhysiqueSelonQualite(@Param("qualite") String qualite);
    @Query(value = "select ph from PersonnePhysique as ph left outer join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = ph.idPersonne left outer join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite " +
            "inner join Personne p on p.idPersonne=ph.idPersonne " +
            "where (q.libelleQualite = :qualite) or (p.estJuge=true) or(p.estExpose=true) " +
            "order by ph.nom asc,ph.prenom asc")
    List<PersonnePhysique> afficherPersonnePhysiqueSelonQualiteLab(@Param("qualite") String qualite);

    @Query(value = "select ph.nom as nom," +
            "ph.prenom as prenom,ph.Sexe as sexe,ph.mobile1 as mobile1 " +
            "from PersonnePhysique as ph inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = ph.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite where q.libelleQualite = :qualite " +
            "order by ph.nom asc,ph.prenom asc")
    List<PersonnePhysiqueProjection> afficherPersonnePhysiqueSelonQualiteEtat(@Param("qualite") String qualite);

    @Query(value = "select   ph.autresRevenus as autresRevenus," +
            "ph.periodicite as periodicite," +
            "ph.statutMatrimonial as statutMatrimonial," +
            "ph.nbrEnfant as nbrEnfant," +
            "ph.nbrPersonneACharge as nbrPersonneACharge," +
            "ph.nomEmployeur as nomEmployeur," +
            "ph.adressePostaleEmp as adressePostaleEmp," +
            "ph.adresseGeoEmp as adresseGeoEmp," +
            "s.libelleSecteur as libelleSecteur," +
            "ph.telEmp as telEmp," +
            "ph.emailEmp as emailEmp," +
            "ph.nomPere as nomPere," +
            "ph.prenomsPere as prenomsPere," +
            "ph.dateNaissancePere as dateNaissancePere," +
            "pa1.libelleFr as paysPere," +
            "ph.nomMere as nomMere," +
            "ph.prenomsMere as prenomsMere," +
            "ph.dateNaissanceMere as dateNaissanceMere," +
            "pa2.libelleFr as paysMere," +
            "ph.nomConjoint as nomConjoint," +
            "ph.prenomConjoint as prenomConjoint," +
            "ph.dateNaissanceConjoint as dateNaissanceConjoint," +
            "pa3.libelleFr as paysConjoint," +
            "ph.origineFonds as origineFonds," +
            "ph.transactionEnvisagee as transactionEnvisagee," +
            "ph.immobilier as immobilier," +
            "ph.autresBiens as autresBiens," +
            "ph.surfaceTotale as surfaceTotale," +
            "ph.salaire as salaire," +
            "ph.nom as nom," +
            "ph.prenom as prenom " +
            "from PersonnePhysique ph " +
            "left outer join Secteur s on s.idSecteur=ph.secteurEmp.idSecteur " +
            "left outer join Pays pa1 on pa1.idPays=ph.paysPere.idPays " +
            "left outer join Pays pa2 on pa2.idPays=ph.paysMere.idPays " +
            "left outer join Pays pa3 on pa3.idPays=ph.paysConjoint.idPays " +
            "where ph.idPersonne=:idPersonne")
    List<FicheKYCProjection> afficherFicheKYC(Long idPersonne);

    @Query(value = "select ph from PersonnePhysique as ph inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = ph.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite where q.libelleQualite = :qualite " +
            "order by ph.nom asc,ph.prenom asc")
    Page<PersonnePhysique> afficherTousSelonQualite(@Param("qualite") String qualite, Pageable pageable);

//    @Query(value = "select ph from PersonnePhysique as ph inner join StatutPersonne as sp " +
//            "on sp.idStatutPersonne.idPersonne = ph.idPersonne inner join Qualite as q " +
//            "on q.idQualite = sp.qualite.idQualite where q.libelleQualite = :qualite " +
////            "and (concat(ph.denomination, ' ', coalesce(ph.Civilite, ' '), ' ', coalesce(ph.Sexe, ' '), ' ', coalesce(ph.mobile1, ' ')) like %:valeur% " +
//            "and (coalesce(ph.denomination,'') like %:valeur% " +
//            "or coalesce(ph.profession.libelleProfession,'') like %:valeur% " +
//            "or coalesce(ph.secteur.libelleSecteur,'') like %:valeur% " +
//            "or coalesce(ph.numeroCpteDeposit,'') like %:valeur%) " +
//            "order by ph.nom asc,ph.prenom asc")
    @Query("""
        select ph
          from PersonnePhysique ph          
          inner join StatutPersonne sp on sp.personne.idPersonne=ph.idPersonne
          join sp.qualite q          
          left join ph.profession prof
          left join ph.secteur sec
          where q.libelleQualite = :qualite
            and (
                 lower(coalesce(ph.denomination, '')) like lower(concat('%', :valeur, '%'))
              or lower(coalesce(prof.libelleProfession, '')) like lower(concat('%', :valeur, '%'))
              or lower(coalesce(sec.libelleSecteur, '')) like lower(concat('%', :valeur, '%'))
              or lower(coalesce(ph.numeroCpteDeposit, '')) like lower(concat('%', :valeur, '%'))
            )
          order by ph.nom asc, ph.prenom asc
    """)
    Page<PersonnePhysique> rechercherSelonQualite(@Param("qualite") String qualite, @Param("valeur") String valeur, Pageable pageable);

    /*@Query(value = "select ph from PersonnePhysique as ph inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = ph.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite where q.libelleQualite = :qualite " +
            "and (ph.denomination like %:valeur% " +
            "or ph.profession.libelleProfession like %:valeur% " +
            "or ph.secteur.libelleSecteur like %:valeur% " +
            "or ph.numeroCpteDeposit like %:valeur%) " +
            "order by ph.nom asc,ph.prenom asc")
    Page<PersonnePhysique> rechercherSelonQualite(String qualite,String valeur, Pageable pageable);*/

    @Query(value = "select ph from PersonnePhysique as ph inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = ph.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite where q.libelleQualite = :qualite " +
            "order by ph.nom asc,ph.prenom asc")
    List<PersonnePhysique> afficherTousSelonQualite(@Param("qualite") String qualite);

    @Query(value = "select ph, sp from PersonnePhysique as ph inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = ph.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite where q.libelleQualite = :qualite and " +
            "sp.idStatutPersonne.idPersonne = :id")
    PersonnePhysique afficherSelonIdQualite(@Param("id") Long id, @Param("qualite") String qualite);

    @Query(value = "select ph from PersonnePhysique as ph " +
            "inner join StatutPersonne as sp on sp.idStatutPersonne.idPersonne = ph.idPersonne " +
            "inner join Qualite as q on q.idQualite = sp.idStatutPersonne.idQualite " +
            "where q.libelleQualite = :qualite " +
            "and ph.idPersonne in(select r.personne.idPersonne " +
            "from RDV r " +
            "inner join CompteRendu cr on cr.rdv.idRdv=r.idRdv " +
            "where cr.dateCR between :dateDebut and :dateFin " +
            "group by r.personne.idPersonne " +
            "having  (sum(cr.montantRealisation) =0)) " +
            "order by ph.nom asc,ph.prenom asc")
    List<PersonnePhysique> afficherPersonnePhysiqueNayantPasInsvesti(String qualite, LocalDateTime dateDebut, LocalDateTime dateFin);

    @Query(value = "select ph.nom as nom," +
            "ph.prenom as prenom,ph.Sexe as sexe,ph.mobile1 as mobile1 " +
            "from PersonnePhysique as ph " +
            "inner join StatutPersonne as sp on sp.idStatutPersonne.idPersonne = ph.idPersonne " +
            "inner join Qualite as q on q.idQualite = sp.idStatutPersonne.idQualite " +
            "where q.libelleQualite = :qualite " +
            "and ph.idPersonne in(select r.personne.idPersonne " +
            "from RDV r " +
            "inner join CompteRendu cr on cr.rdv.idRdv=r.idRdv " +
            "where cr.dateCR between :dateDebut and :dateFin " +
            "group by r.personne.idPersonne " +
            "having  (sum(cr.montantRealisation) =0)) " +
            "order by ph.nom asc,ph.prenom asc")
    List<PersonnePhysiqueProjection> afficherPersonnePhysiqueNayantPasInsvestiEtat(@Param("qualite") String qualite, LocalDateTime dateDebut, LocalDateTime dateFin);
    @Query(value = "select ph from PersonnePhysique as ph " +
            "inner join StatutPersonne as sp on sp.idStatutPersonne.idPersonne = ph.idPersonne " +
            "inner join Qualite as q on q.idQualite = sp.idStatutPersonne.idQualite " +
            "inner join Personne p on p.idPersonne=ph.idPersonne "+
            "where q.libelleQualite = :qualite " +
            "and p.estExpose=true "+
            "order by ph.nom asc,ph.prenom asc")
    Page<PersonnePhysique> afficherPersonnePhysiquePolitiquementExpose(@Param("qualite") String qualite, Pageable pageable);

    @Query(value = "select ph from PersonnePhysique as ph " +
            "inner join StatutPersonne as sp on sp.idStatutPersonne.idPersonne = ph.idPersonne " +
            "inner join Qualite as q on q.idQualite = sp.idStatutPersonne.idQualite " +
            "inner join Personne p on p.idPersonne=ph.idPersonne "+
            "where q.libelleQualite = :qualite " +
            "and p.estJuge=true "+
            "order by ph.nom asc,ph.prenom asc")
    Page<PersonnePhysique> afficherPersonnePhysiqueJuge(@Param("qualite") String qualite, Pageable pageable);
//    Boolean existsByNumCompteDepositaire(String numCompteDepositaire);
    Boolean existsByNumeroCpteDepositIgnoreCase(String numCompteDepositaire);
//    Optional<PersonnePhysique> findByNumCompteDepositaireIgnoreCase(String numCompteDepositaire);
    Optional<PersonnePhysique> findByNumeroCpteDepositIgnoreCase(String numCompteDepositaire);

    Optional<PersonnePhysique> findByNomAndPrenomContainsIgnoreCase(String nom, String prenom);

    @Query(value = "select ph from PersonnePhysique as ph "+
            "where ph.estExpose = true " +
            "order by ph.nom asc,ph.prenom asc")
    Page<PersonnePhysique> afficherPersonneExpose(Pageable pageable);
    @Query(value = "select ph from PersonnePhysique as ph "+
            "where ph.estJuge=true " +
            "order by ph.nom asc,ph.prenom asc")
    Page<PersonnePhysique> afficherPersonneJuge(Pageable pageable);
}
