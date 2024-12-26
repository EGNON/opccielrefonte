package com.ged.dao.opcciel;

import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.projection.PrecalculRestitutionReliquatProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationSouscriptionRachatDao extends JpaRepository<OperationSouscriptionRachat,Long>, JpaSpecificationExecutor<OperationSouscriptionRachat> {
    Page<OperationSouscriptionRachat> findBySupprimer(boolean valeur,Pageable pageable);
    List<OperationSouscriptionRachat> findBySupprimer(boolean valeur);
    @Query(value = "select o.idOpcvm, op.idSeance, a.idPersonne as idActionnaire, a.denomination as nomSigle, a.denomination as prenomRaisonSociale, op.reste as montant " +
            "from OperationSouscriptionRachat op join op.opcvm o join op.actionnaire a " +
            "where o.idOpcvm = :idOpcvm and op.idSeance = :idSeance and op.resteRembourse = false and op.reste > 0")
    Page<PrecalculRestitutionReliquatProjection> precalculRestitutionReliquat(
            @Param("idOpcvm") Long idOpcvm, @Param("idSeance") Long idSeance, Pageable pageable);
    @Query(value = "select o.idOpcvm as idOpcvm, op.idSeance as idSeance, a.idPersonne as idActionnaire, a.denomination as nomSigle, '' as prenomRaisonSociale, op.reste as montant " +
            "from OperationSouscriptionRachat op join op.opcvm o join op.actionnaire a " +
            "where o.idOpcvm = :idOpcvm and op.idSeance = :idSeance and op.resteRembourse = false and op.reste > 0")
    List<PrecalculRestitutionReliquatProjection> precalculRestitutionReliquat(@Param("idOpcvm") Long idOpcvm, @Param("idSeance") Long idSeance);
    @Query(value = "select op from OperationSouscriptionRachat op join op.actionnaire a join op.opcvm o " +
            "where o.idOpcvm = :idOpcvm and op.idSeance = :idSeance and a.idPersonne = :idActionnaire")
    List<OperationSouscriptionRachat> recupererListeSouscriptionAMettreAJour(
        @Param("idOpcvm") Long idOpcvm,
        @Param("idSeance") Long idSeance,
        @Param("idActionnaire") Long idActionnaire
    );
    @Query(value = "select op from OperationSouscriptionRachat op join fetch op.natureOperation n " +
            "where op.idOpcvm = :idOpcvm and op.dateOperation between :dateDebut and :dateFin and n.codeNatureOperation = :code")
    Page<OperationSouscriptionRachat> listeOpSouscriptionRachat(
            @Param("idOpcvm") Long idOpcvm,
            @Param("code") String code,
            @Param("dateDebut") LocalDateTime dateDebut,
            @Param("dateFin") LocalDateTime dateFin,
            Pageable pageable);
}
