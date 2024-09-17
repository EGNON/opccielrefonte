package com.ged.dao.standard;

import com.ged.entity.standard.PersonneMorale;
import com.ged.entity.standard.PersonnePhysique;
import com.ged.projection.NumOrdreProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface PersonneMoraleDao extends JpaRepository<PersonneMorale, Long> {
    @Query(value = "select max(p.numOrdre) as numOrdre" +
            " " +
            "from PersonneMorale p " +
            "inner join SousTypeClient s on s.idSousTypeClient=p.sousTypeClient.idSousTypeClient " +
            "inner join TypeClient t on t.idTypeClient=s.typeClient.idTypeClient " +
            "where t.libelleTypeClient =:valeur")
    NumOrdreProjection maxNumordre(String valeur);
    @Query(value = "select pm from PersonneMorale as pm inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = pm.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite where q.libelleQualite = :qualite " +
            "order by pm.raisonSociale asc")
    List<PersonneMorale> afficherPersonneMoraleSelonQualite(@Param("qualite") String qualite);

    @Query(value = "select pm from PersonneMorale as pm inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = pm.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite where q.libelleQualite = :qualite " +
            "order by pm.raisonSociale asc")
    Page<PersonneMorale> afficherTousSelonQualite(@Param("qualite") String qualite, Pageable pageable);

    @Query(value = "select pm from PersonneMorale as pm " +
            "inner join StatutPersonne as sp on sp.idStatutPersonne.idPersonne = pm.idPersonne " +
            "inner join Qualite as q on q.idQualite = sp.idStatutPersonne.idQualite " +
            "where (q.libelleQualite = :qualite) " +
            "and pm.idPersonne in(select r.personne.idPersonne " +
            "from RDV r " +
            "inner join CompteRendu cr on cr.rdv.idRdv=r.idRdv " +
            "where cr.dateCR between :dateDebut and :dateFin " +
            "group by r.personne.idPersonne " +
            "having  (sum(cr.montantRealisation) =0)) " +
            "order by pm.raisonSociale asc")
    List<PersonneMorale> afficherPersonneMoraleNayantPasInvesti(String qualite, LocalDateTime dateDebut, LocalDateTime dateFin);


    @Query(value = "select pm from PersonneMorale as pm " +
            "inner join StatutPersonne as sp on sp.idStatutPersonne.idPersonne = pm.idPersonne " +
            "inner join Qualite as q on q.idQualite = sp.idStatutPersonne.idQualite " +
            "inner join Personne p on p.idPersonne=pm.idPersonne "+
            "where q.libelleQualite = :qualite " +
            "and p.estExpose=true "+
            "order by pm.raisonSociale asc")
    Page<PersonneMorale> afficherPersonneMoralePolitiquementExpose(@Param("qualite") String qualite, Pageable pageable);

    @Query(value = "select pm from PersonneMorale as pm " +
            "inner join StatutPersonne as sp on sp.idStatutPersonne.idPersonne = pm.idPersonne " +
            "inner join Qualite as q on q.idQualite = sp.idStatutPersonne.idQualite " +
            "inner join Personne p on p.idPersonne=pm.idPersonne "+
            "where q.libelleQualite = :qualite " +
            "and p.estJuge=true "+
            "order by pm.raisonSociale asc")
    Page<PersonneMorale> afficherPersonneMoraleJuge(@Param("qualite") String qualite, Pageable pageable);

    @Query(value = "select pm from PersonneMorale as pm inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = pm.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite where q.libelleQualite = :qualite " +
            "and (concat(pm.denomination, ' ', coalesce(pm.mobile1, ' ')) like %:valeur% " +
            "or pm.secteur.libelleSecteur like %:valeur% " +
            "or pm.numeroCpteDeposit like %:valeur%) " +
            "order by pm.denomination asc")
    Page<PersonneMorale> rechercherSelonQualite(String qualite, String valeur, Pageable pageable);

    Boolean existsByNumCompteDepositaire(String numCompte);
    Boolean existsByNumeroCpteDepositIgnoreCase(String numCompte);
    Optional<PersonneMorale> findByNumCompteDepositaireIgnoreCase(String numCompteDeposit);
    Optional<PersonneMorale> findByNumeroCpteDepositIgnoreCase(String numCompteDeposit);
    PersonneMorale findByNumeroAgrementPersonneMorale(String numAgrement);
    Optional<PersonneMorale> findBySiglePersonneMoraleIgnoreCase(String sigle);
    Optional<PersonneMorale> findByTypePersonneAndIdOcc(String type, Long id);
    Optional<PersonneMorale> findByLibelleTypePersonneContainsAndSigleContains(String libelleTypePersonne, String sigle);
    List<PersonneMorale> findBySigleContainsIgnoreCase(String sigle);
    List<PersonneMorale> findBySigleContainsIgnoreCaseOrRaisonSocialeContainsIgnoreCase(String sigle, String raisonSociale);
    Optional<PersonneMorale> findByLibelleTypePersonneContainsAndIdOcc(String libelle, Long id);
    Optional<PersonneMorale> findByLibelleTypePersonneContainsAndIdOccAndSigleIgnoreCase(String libelleTypePersonne, Long idOcc, String sigle);
    @Query(value = "select pm from PersonneMorale as pm "+
            "where pm.estJuge = true or pm.estExpose=true " +
            "order by pm.raisonSociale asc")
    Page<PersonneMorale> afficherPersonneSanctionnee(Pageable pageable);
}
