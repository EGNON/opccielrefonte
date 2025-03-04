package com.ged.dao;

import com.ged.entity.BaseEntity;
import com.ged.projection.*;
import com.ged.entity.opcciel.SeanceOpcvm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface LibraryDao extends JpaRepository<BaseEntity, Long> {
    @Query(value = "select [Comptabilite].[FS_SoldeCompteClient](:idActionnaire, :idOpcvm)", nativeQuery = true)
    BigDecimal solde(@Param("idActionnaire") Long idActionnaire, @Param("idOpcvm") Long idOpcvm);
    @Query(value = "SELECT * FROM [Operation].[FT_NbrePart](:idActionnaire, :idOpcvm, :estLevee," +
            ":estVerifie1, :estVerifie2, :dateEstimation)", nativeQuery = true)
    List<NbrePartProjection> afficherNbrePart(@Param("idActionnaire") Long idActionnaire,
                                              @Param("idOpcvm") Long idOpcvm,
                                              @Param("estLevee") boolean estLevee,
                                              @Param("estVerifie1") boolean estVerifie1,
                                              @Param("estVerifie2") boolean estVerifie2,
                                              @Param("dateEstimation") LocalDateTime dateEstimation
                                          );
    @Query(value = "SELECT * FROM [Parametre].[FT_DepotRachat2](:IdSeance," +
            ":IdPersonne,:IdOpcvm,:codeNatureOperation,:niveau1" +
            ",:niveau2)",nativeQuery = true)
    List<FT_DepotRachatProjection> afficherFT_DepotRachat(@Param("IdSeance") Long IdSeance,
                                                    @Param("IdPersonne") Long IdPersonne,
                                                    @Param("IdOpcvm") Long IdOpcvm,
                                                    @Param("codeNatureOperation") String codeNatureOperation,
                                                    @Param("niveau1") boolean niveau1,
                                                    @Param("niveau2") boolean niveau2
                                          );
    @Query(value = "select s from SeanceOpcvm s join s.opcvm o where s.opcvm.idOpcvm = :idOpcvm and s.estEnCours = true")
    SeanceOpcvm currentSeance(@Param("idOpcvm") Long idOpcvm);

    @Query(value = "SELECT * FROM [Comptabilite].[FT_ChargerLigneMvt] (:codeNatureOperation, " +
            ":valeurCodeAnalytique, :valeurFormule, :idOpcvm, :idActionnaire, :idTitre)", nativeQuery = true)
    List<LigneMvtClotureProjection> chargerLigneMvt(
            @Param("codeNatureOperation") String codeNatureOperation,
            @Param("valeurCodeAnalytique") String valeurCodeAnalytique,
            @Param("valeurFormule") String valeurFormule,
            @Param("idOpcvm") Long idOpcvm,
            @Param("idActionnaire") Long idActionnaire,
            @Param("idTitre") Long idTitre
    );

    @Query(value = "SELECT * FROM [Parametre].[PrecalculRachat](:idSeance," +
            ":idOpcvm,:idPersonne)",nativeQuery = true)
    List<PrecalculRachatProjection> afficherPrecalculRachat(@Param("idSeance") Long idSeance,
                                                            @Param("idOpcvm") Long idOpcvm,
                                                            @Param("idPersonne") Long idPersonne);


    @Query(value = "SELECT * FROM [Comptabilite].[SoldeCompteFormule](:idOpcvm," +
            ":numCompte,:codeplan,:idTitre,:date)",nativeQuery = true)
    List<SoldeCompteFormuleProjection> afficherSoldeCompteFormule(@Param("idOpcvm") Long idOpcvm,
                                                                  @Param("numCompte") String numCompte,
                                                                  @Param("codeplan") String codeplan,
                                                                  @Param("idTitre") Long idTitre,
                                                                  @Param("date") Date date);
    @Query(value = "SELECT * FROM [Comptabilite].[VerificationMiseAffectationEnAttente](:idOpcvm)",nativeQuery = true)
    List<Object[]> verificationMiseAffectationEnAttente(@Param("idOpcvm") Long idOpcvm);

    @Query(value = "SELECT * FROM [Operation].[FT_AvisOpere](:idOperation)",nativeQuery = true)
    List<AvisOperationProjection> avisOper(@Param("idOperation") String idOperation);

    @Query(value = "select * from [Parametre].[PrecalculSouscription](:idSeance, :idOpcvm, :idPersonne)", nativeQuery = true)
    Page<PrecalculSouscriptionProjection> precalculSouscription(@Param("idSeance") Long idSeance,
                                                                @Param("idOpcvm") Long idOpcvm,
                                                                @Param("idPersonne") Long idPersonne, Pageable pageable);
    @Query(value = "select * from [Comptabilite].[FT_AfficherEcriture](:idOperation)", nativeQuery = true)
    List<AfficherDetailsEcritureProjection> afficherDetailsEcriture(
      @Param("idOperation") Long idOperation
    );

    @Query(value = "select * from [Impressions].[FT_RegistreActionnaireReduit](:idOpcvm, :idActionnaire, :dateEstimation)", nativeQuery = true)
    List<RegistreActionnaireProjection> registreActionnaire(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idActionnaire") Long idActionnaire,
            @Param("dateEstimation") LocalDateTime dateEstimation
    );

    @Query(value = "select [Comptabilite].[FS_CUMP_ACT](:idOpcvm, :idActionnaire, :dateEstimation)", nativeQuery = true)
    BigDecimal cumpActionnaire(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idActionnaire") Long idActionnaire,
            @Param("dateEstimation") LocalDateTime dateEstimation
    );

    @Query(value = "select * from [Comptabilite].[FT_SoldeToutCompte](:codePlan, :idOpcvm, :numCompteComptable, :dateEstimation)", nativeQuery = true)
    List<SoldeToutCompteProjection> soldeToutCompte(
        @Param("codePlan") String codePlan,
        @Param("idOpcvm") Long idOpcvm,
        @Param("numCompteComptable") String numCompteComptable,
        @Param("dateEstimation") LocalDateTime dateEstimation
    );
}
