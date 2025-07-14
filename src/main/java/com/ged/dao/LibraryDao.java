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
    @Query(value = "select [Titre].[FS_DeniereEcheance_New](:idTitre,:dateEvaluation,:idOpcvm)", nativeQuery = true)
    Date derniereEcheance(Long idTitre, Date dateEvaluation, Long idOpcvm);
    @Query(value = "select [Titre].[FS_NbrePeriode_New](:idTitre,:idOpcvm)", nativeQuery = true)
    BigDecimal nbrePeriode(Long idTitre,Long idOpcvm);
    @Query(value = "select [Comptabilite].[FS_VerifOperationDesequilibre](:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    String verifOperationDesequilibre(Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin);

    @Query(value = "select [Titre].[FS_LastDayAmortissement_New](:idTitre)", nativeQuery = true)
    LocalDateTime derniereEcheance(Long idTitre);

    @Query(value = "select * from [OrdreDeBourse].[FT_OrdrePourImpression_New](:numeroOrdre)", nativeQuery = true)
    List<OrdreProjection> listeOrdreApercu(@Param("numeroOrdre") String numeroOrdre);

    @Query(value = "select * from [Impressions].[FT_PortefeuilleOPCVM_New2](:idOpcvm ,:dateEstimation)", nativeQuery = true)
    List<PortefeuilleOpcvmProjection> portefeuilleOPCVM(Long idOpcvm,LocalDateTime dateEstimation);
    @Query(value = "select * from [Impressions].[FT_PortefeuilleOPCVM_New2](:idOpcvm ,:dateEstimation) " +
            "where codeTypeTitre='ACTION'", nativeQuery = true)
    List<PortefeuilleOpcvmProjection> portefeuilleOPCVMAction(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_PortefeuilleOPCVM_New2](:idOpcvm ,:dateEstimation) " +
            "where codeTypeTitre='OBLIGATI' or codeTypeTitre='BOT' or codeTypeTitre='BIT' or codeTypeTitre='CED' " +
            "or codeTypeTitre='BEFI' or codeTypeTitre='OBLIGATN' ", nativeQuery = true)
    List<PortefeuilleOpcvmProjection> portefeuilleOPCVMAutres(Long idOpcvm,LocalDateTime dateEstimation);
    @Query(value = "select [Comptabilite].[FS_QuantiteReelTitre](:idOpcvm,:idTitre,:date)", nativeQuery = true)
    BigDecimal quantiteReelTitre(@Param("idOpcvm") Long idOpcvm,Long idTitre,LocalDateTime date);
    @Query(value = "select [Titre].[FS_InteretsCourrus_New](:idTitre,:dateEvaluation,:CalculerParPeriodicite,:idOpcvm)", nativeQuery = true)
    BigDecimal interetCouru(@Param("idTitre") Long idTitre,
                            Date dateEvaluation,
                            Boolean CalculerParPeriodicite,
                            Long idOpcvm);
    @Query(value = "select [Parametre].[FS_ControleGenerationSousRach]" +
            "(:idOpcvm,:idSeance,:typeOp)", nativeQuery = true)
    Boolean controlGenerationSousRach( Long idOpcvm,
                            Long idSeance,
                            String typeOp);
    @Query(value = "select [Operation].[FS_VerifOpExtourneN1N2](:typeOp,:idSeance,:idOpcvm,:niveau1,:niveau2)", nativeQuery = true)
    Boolean verifOpExtourneN1N2(String typeOp ,
                                Long idSeance,
                                Long idOpcvm ,
                                Boolean niveau1 ,
                                Boolean niveau2);

    @Query(value = "SELECT * FROM [Operation].[FT_NbrePart](:idActionnaire, :idOpcvm, :estLevee," +
            ":estVerifie1, :estVerifie2, :dateEstimation)", nativeQuery = true)
    List<NbrePartProjection> afficherNbrePart(@Param("idActionnaire") Long idActionnaire,
                                              @Param("idOpcvm") Long idOpcvm,
                                              @Param("estLevee") boolean estLevee,
                                              @Param("estVerifie1") boolean estVerifie1,
                                              @Param("estVerifie2") boolean estVerifie2,
                                              @Param("dateEstimation") LocalDateTime dateEstimation
                                          );
    @Query(value = "SELECT * FROM [Operation].[FT_GenerationDifferenceEstimation_New]" +
            "(:idSeance,:idOpcvm,:dateSeance)", nativeQuery = true)
    Page<DifferenceEstimationProjection> precalculDifferenceEstimation(Long idSeance,
                                                                       Long idOpcvm,
                                                                       LocalDateTime dateSeance,
                                                                       Pageable pageable
                                                                  );
    @Query(value = "SELECT * FROM [Operation].[FT_genererCharge_New](:idOpcvm,:idSeance,:actifBrut,:nbreJour,:usance)", nativeQuery = true)
    Page<FT_GenererChargeProjection> genererCharge(Long idOpcvm,
                                                       Long idSeance,
                                                       BigDecimal actifBrut,
                                                        Long nbreJour,
                                                        Long usance,
                                                       Pageable pageable
                                                  );
    @Query(value = "SELECT * FROM [Operation].[FT_genererCharge_New](:idOpcvm,:idSeance,:actifBrut,:nbreJour,:usance)", nativeQuery = true)
    List<FT_GenererChargeProjection> genererCharge(Long idOpcvm,
                                                       Long idSeance,
                                                       BigDecimal actifBrut,
                                                        Long nbreJour,
                                                        Long usance
                                                  );
    @Query(value = "SELECT * FROM [Operation].[FT_GenerationDifferenceEstimation_New]" +
            "(:idSeance,:idOpcvm,:dateSeance)", nativeQuery = true)
    List<DifferenceEstimationProjection> precalculDifferenceEstimation(Long idSeance,
                                                                       Long idOpcvm,
                                                                       LocalDateTime dateSeance
                                                                  );

    @Query(value = "SELECT * FROM [Operation].[FT_OperationDifferenceEstimation]" +
            "(:idSeance,:idOpcvm,:estVerifie1,:estVerifie2,:supprimer)", nativeQuery = true)
    List<OperationDifferenceEstimationProjection> operationDifferenceEstimation(Long idSeance,
                                                                       Long idOpcvm,
                                                                       Boolean estVerifie1,
                                                                       Boolean estVerifie2,
                                                                       Boolean supprimer
                                                                  );
    @Query(value = "SELECT * FROM [Impressions].[FT_PrevisionnelRembourement_New](:idOpcvm,:echue,:detache" +
            ",:traiter,:datedebut,:datefin)", nativeQuery = true)
    List<Object[]> previsionnelRemboursement(Long idOpcvm,
                                              Boolean echue,
                                              Boolean detache,
                                              Boolean traiter,
                                              LocalDateTime datedebut,
                                              LocalDateTime datefin
                                          );
    @Query(value = "SELECT * FROM [Titre].[FT_CoursTitre1](:codePlace,:date,:estVerifie1,:estVerifie2)", nativeQuery = true)
    List<CoursTitreProjection> coursTitre(String codePlace,
                                              LocalDateTime date,
                                              Boolean estVerifie1,
                                              Boolean  estVerifie2
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
    @Query(value = "select * from [Parametre].[PrecalculSouscription](:idSeance, :idOpcvm, :idPersonne)", nativeQuery = true)
    List<PrecalculSouscriptionProjection> precalculSouscription(@Param("idSeance") Long idSeance,
                                                                @Param("idOpcvm") Long idOpcvm,
                                                                @Param("idPersonne") Long idPersonne);
    @Query(value = "select * from [Comptabilite].[FT_AfficherEcriture](:idOperation)", nativeQuery = true)
    List<AfficherDetailsEcritureProjection> afficherDetailsEcriture(
      @Param("idOperation") Long idOperation
    );
    @Query(value = "select * from [Comptabilite].[FT_ListeVerificationEcriture_New](:idOpcvm," +
            ":idSeance,:codeTypeOperation,:dateDebut,:dateFin,:estVerifie1,:estVerifie2)", nativeQuery = true)
    Page<ListeVerificationEcritureProjection> listeVerificationEcriture(
      Long idOpcvm,Long idSeance,String codeTypeOperation,
      LocalDateTime dateDebut,LocalDateTime dateFin,Boolean estVerifie1,Boolean estVerifie2,Pageable pageable
    );
    @Query(value = "select * from [Comptabilite].[FT_ListeVerificationEcriture_New](:idOpcvm," +
            ":idSeance,:codeTypeOperation,:dateDebut,:dateFin,:estVerifie1,:estVerifie2)", nativeQuery = true)
    List<ListeVerificationEcritureProjection> listeVerificationEcriture(
      Long idOpcvm,Long idSeance,String codeTypeOperation,
      LocalDateTime dateDebut,LocalDateTime dateFin,Boolean estVerifie1,Boolean estVerifie2
    );
    @Query(value = "select * from [Comptabilite].[FT_ListeVerificationEcriture_Jasper](:idOpcvm," +
            ":codeTypeOperation,:dateDebut,:dateFin,:estVerifie1,:estVerifie2,:idOperation)", nativeQuery = true)
    List<ListeVerificationEcritureProjection> listeVerificationEcriture(
      Long idOpcvm,String codeTypeOperation,
      LocalDateTime dateDebut,LocalDateTime dateFin,Boolean estVerifie1,
      Boolean estVerifie2,String idOperation
    );

    @Query(value = """
                select * 
                from [Impressions].[FT_RegistreActionnaireReduit](:idOpcvm, :idActionnaire, :dateEstimation)
                order by typePersonne asc
            """,
            nativeQuery = true
    )
    List<RegistreActionnaireProjection> registreActionnaire(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idActionnaire") Long idActionnaire,
            @Param("dateEstimation") LocalDateTime dateEstimation
    );

    @Query(value = """
                select * 
                from [Impressions].[FT_RegistreActionnaireReduit](:idOpcvm, :idActionnaire, :dateEstimation)
                order by typePersonne asc
            """,
            nativeQuery = true
    )
    Page<RegistreActionnaireProjection> registreActionnaires(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idActionnaire") Long idActionnaire,
            @Param("dateEstimation") LocalDateTime dateEstimation,
            Pageable pageable
    );
    @Query(value = "select * from [EvenementSurValeur].[FT_OperationDetachement](:idOpcvm" +
            ",:estPaye) order by dateOperation desc",
            nativeQuery = true
    )
    Page<OperationDetachementProjection> operationDetachementListe(
            Long idOpcvm,
            Boolean estPaye,
            Pageable pageable
    );
    @Query(value = "select * from OperationCapital.[FT_OperationDetachementDroit](:idOpcvm" +
            ",:idTitre) order by dateOperation desc",
            nativeQuery = true
    )
   OperationDetachementDroitProjection operationDetachementListe(
            Long idOpcvm,
            Long idTitre
    );
    @Query(value = "select * from [Operation].[FT_OperationExtourneVDE](:idSeance,:idOpcvm,:estVerifie," +
            ":estVerifie1,:estVerifie2)",
            nativeQuery = true
    )
   List<OperationExtourneVDEProjection> operationExtourneVDE(
            Long idSeance,Long idOpcvm,Boolean estVerifie,Boolean estVerifie1,Boolean estVerifie2
    );
    @Query(value = "select * from [Comptabilite].[VerificationMiseAffectationEnAttente](:idOpcvm)",
            nativeQuery = true
    )
   List<VerificationMiseEnAffectationEnAttenteProjection> verificationMiseEnAffectationEnAttente(Long idOpcvm);
    @Query(value = "select * from [Comptabilite].[FT_SoldeCompteExtourne](:idOpcvm,:numCompteComptable," +
            ":dateEstimation)",
            nativeQuery = true
    )
   List<SoldeCompteExtourneProjection> soldeCompteExtourne(
            Long idOpcvm,String numCompteComptable ,LocalDateTime dateEstimation
    );
    @Query(value = "select * from [EvenementSurValeur].[FT_OperationDetachement](:idOpcvm" +
            ",:estPaye) where typeevenement=:typeEvenement order by dateOperation desc",
            nativeQuery = true
    )
    List<OperationDetachementProjection> operationDetachementListe(
            Long idOpcvm,
            Boolean estPaye,
            String typeEvenement
    );
    @Query(value = "select * from [EvenementSurValeur].[FT_OperationDetachement](:idOpcvm" +
            ",:estPaye) where concat(dateOperation,symboleTitre,libelleOperation) like %:valeur%" +
            " order by dateOperation desc",
            nativeQuery = true
    )
    Page<OperationDetachementProjection> operationDetachementListeRecherche(
            Long idOpcvm,
            Boolean estPaye,
            String valeur,
            Pageable pageable
    );
    @Query(value = "select * from [EvenementSurValeur].[FT_OperationEvenementSurValeur](:idOpcvm) order by dateOperation desc",
                nativeQuery = true
        )
    Page<OperationEvenementSurValeurProjection> operationEvenementSurValeurListe(
                Long idOpcvm,
                Pageable pageable
        );
    @Query(value = "select * from [EvenementSurValeur].[FT_OperationEvenementSurValeur](:idOpcvm)" +
            " where concat(dateOperation,symboleTitre,libelleOperation) like %:valeur%" +
                " order by dateOperation desc",
                nativeQuery = true
        )
    Page<OperationEvenementSurValeurProjection> operationEvenementSurValeurListeRecherche(
                Long idOpcvm,
                String valeur,
                Pageable pageable
        );
    @Query(value = "select * from [Operation].[FT_OperationRegulEcartSolde](:idOpcvm,:supprimer)" +
            "",
                nativeQuery = true
        )
    Page<OperationRegulEcartSoldeProjection> operationRegulEcartSolde(
                Long idOpcvm,
                Boolean supprimer,
                Pageable pageable
        );
    @Query(value = "select * from [Comptabilite].[FT_Operation_Ecriture](:idOpcvm,:idOperation,:idTransaction," +
            "            :code,:dateDebut,:dateFin) o order by o.idOperation desc ",nativeQuery = true)
    Page<ConsultationEcritureProjection> listeOperationsFiltree(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idOperation") Long idOperation,
            @Param("idTransaction") Long idTransaction,
            @Param("code") String code,
            @Param("dateDebut") String dateDebut,
            @Param("dateFin") String dateFin,
            Pageable pageable
    );
    @Query(value = "select * from [Operation].[FT_OperationTransfertDePart](:idOpcvm,:supprimer)" +
            "",
                nativeQuery = true
        )
    Page<OperationTransfertDePartProjection> operationTransfertDePart(
                Long idOpcvm,
                Boolean supprimer,
                Pageable pageable
        );

    @Query(value = "select * from [EvenementSurValeur].[FT_OperationDetachement](:idOpcvm" +
            ",:estPaye) where idOperation=:id" +
            " order by dateOperation desc",
            nativeQuery = true
    )
   OperationDetachementProjection operationDetachementSelonId(Long idOpcvm,Boolean estPaye,Long id);

    @Query(value = "select [Comptabilite].[FS_CUMP_ACT](:idOpcvm, :idActionnaire, :dateEstimation)", nativeQuery = true)
    BigDecimal cumpActionnaire(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idActionnaire") Long idActionnaire,
            @Param("dateEstimation") LocalDateTime dateEstimation
    );

    @Query(value = "select [Comptabilite].[FS_CUMP](:idOpcvm, :idTitre, :dateEstimation)", nativeQuery = true)
    BigDecimal cump(
            @Param("idOpcvm") Long idOpcvm,
            @Param("idTitre") Long idTitre,
            @Param("dateEstimation") LocalDateTime dateEstimation
    );
    @Query(value = "select [Titre].[FS_qteAmortie_new](:idTitre,:date,:qteDetenue)", nativeQuery = true)
    BigDecimal qteAmortie(Long idTitre,LocalDateTime date,Long qteDetenue);
    @Query(value = "select [Titre].[FS_NominalRembourse_new](:idTitre,:date)", nativeQuery = true)
    BigDecimal nominalRembourse(Long idTitre,Date date);
    @Query(value = "select [Comptabilite].[FS_FormuleDunCodePoste](:codePoste)", nativeQuery = true)
    String formuleDunCodePoste(String codePoste);

    @Query(value = "select * from [Comptabilite].[FT_SoldeToutCompte](:codePlan, :idOpcvm, :numCompteComptable, :dateEstimation)", nativeQuery = true)
    List<SoldeToutCompteProjection> soldeToutCompte(
        @Param("codePlan") String codePlan,
        @Param("idOpcvm") Long idOpcvm,
        @Param("numCompteComptable") String numCompteComptable,
        @Param("dateEstimation") LocalDateTime dateEstimation
    );
    @Query(value = "select * from [Operation].[FT_OperationChargeAEtaler](:idSeance,:idOpcvm,:supprimer," +
            ":estVerifie1,:estVerifie2)", nativeQuery = true)
    Page<FT_GenererChargeProjection> afficherChargeAEtaler(
        Long idSeance,Long idOpcvm, Boolean supprimer,Boolean estVerifie1,Boolean estVerifie2,Pageable pageable
    );
    @Query(value = "select * from [Operation].[FT_OperationChargeAEtaler](:idSeance,:idOpcvm,:supprimer," +
            ":estVerifie1,:estVerifie2)", nativeQuery = true)
    List<FT_GenererChargeProjection> afficherChargeAEtaler(
        Long idSeance,Long idOpcvm, Boolean supprimer,Boolean estVerifie1,Boolean estVerifie2
    );
    @Query(value = "select * from [Parametre].[FT_Charge] (:idSeance,:idOpcvm)", nativeQuery = true)
    List<FT_GenererChargeProjection> verifierChargeAEtaler(
        Long idSeance,Long idOpcvm
    );
    @Query(value = "select * from [Comptabilite].[FT_SoldeCompte](:ib, :rubrique, :position, :date)", nativeQuery = true)
    List<SoldeCompteProjection> soldeCompte(
            String ib, String rubrique, String position, LocalDateTime date
    );
}
