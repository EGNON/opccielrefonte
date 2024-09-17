package com.ged.dao.crm;

import com.ged.entity.crm.CompteRendu;
import com.ged.entity.security.Utilisateur;
import com.ged.projection.CompteRenduProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface CompteRenduDao extends JpaRepository<CompteRendu,Long> {
    CompteRendu findById(long id);
    List<CompteRendu> findByCreateur(Utilisateur utilisateur);
    @Query(value = "SELECT  " +
            "cr.idCR as idCR," +
            "cr.dateCR as dateCR," +
            "cr.heureDebCR as heureDebCR," +
            "cr.heureFinCR as heureFinCR," +
            "cr.objetCR as objetCR," +
            "cr.appreciation as appreciation," +
            "cr.description as description," +
            "cr.dateProchainRDV as dateProchainRDV," +
            "cr.montantPromesse as montantPromesse," +
            "cr.montantRealisation as montantRealisation," +
            "cr.promesse as promesse," +
            "cr.realisation as realisation," +
            "cr.dateEffectivePromesse as dateEffectivePromesse," +
            "cr.opcvmASouscrire as opcvmASouscrire," +
            "cr.opcvmSouscrit as opcvmSouscrit," +
            "r as rdv " +
            "from CompteRendu as cr " +
            "left outer join Opcvm op1 ON op1.idOpcvm=cr.opcvmASouscrire.idOpcvm " +
            "left outer join Opcvm op2 ON op2.idOpcvm=cr.opcvmSouscrit.idOpcvm " +
            "inner join RDV r on r.idRdv=cr.rdv.idRdv "+
            "where cr.realisation is not null "+
            "and cr.createur.idPersonne=:idUtilisateur "+
            "and cr.dateCR between :dateDeb and :dateFin " +
            "order by cr.dateCR desc,cr.objetCR asc")
    List<CompteRenduProjection> afficherCompteRenduSelonRealisation(long idUtilisateur, LocalDateTime dateDeb, LocalDateTime dateFin);
    @Query(value = "SELECT  " +
            "cr.idCR as idCR," +
            "cr.dateCR as dateCR," +
            "cr.heureDebCR as heureDebCR," +
            "cr.heureFinCR as heureFinCR," +
            "cr.objetCR as objetCR," +
            "cr.appreciation as appreciation," +
            "cr.description as description," +
            "cr.dateProchainRDV as dateProchainRDV," +
            "cr.montantPromesse as montantPromesse," +
            "cr.montantRealisation as montantRealisation," +
            "cr.promesse as promesse," +
            "cr.realisation as realisation," +
            "cr.dateEffectivePromesse as dateEffectivePromesse," +
            "cr.opcvmASouscrire as opcvmASouscrire," +
            "cr.opcvmSouscrit as opcvmSouscrit," +
            "r as rdv " +
            "from CompteRendu as cr " +
            "left outer join Opcvm op1 ON op1.idOpcvm=cr.opcvmASouscrire.idOpcvm " +
            "left outer join Opcvm op2 ON op2.idOpcvm=cr.opcvmSouscrit.idOpcvm " +
            "inner join RDV r on r.idRdv=cr.rdv.idRdv "+
            "where cr.realisation is not null "+
            "and cr.dateCR between :dateDeb and :dateFin " +
            "order by cr.dateCR desc,cr.objetCR asc")
    List<CompteRenduProjection> afficherCompteRenduSelonRealisationAll(LocalDateTime dateDeb, LocalDateTime dateFin);
}
