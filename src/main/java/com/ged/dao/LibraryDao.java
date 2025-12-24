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
    @Query(value = "select * from [Impressions].[FT_PortefeuilleOPCVM_New2](:idOpcvm ,:dateEstimation)", nativeQuery = true)
    Page<PortefeuilleOpcvmProjection> portefeuilleOPCVM(Long idOpcvm,LocalDateTime dateEstimation,Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_RelevePartFCP_New] (:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    List<ReleveDePartFCPProjection> releveDePartFCP(Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_RelevePartFCP_New] (:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    Page<ReleveDePartFCPProjection> releveDePartFCP(Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_RelevePartActionnaire_New] (:idActionnaire,:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    List<ReleveDePartActionnaireProjection> releveDePartActionnaire(Long idActionnaire,Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_RelevePartActionnaire_New] (:idActionnaire,:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    Page<ReleveDePartActionnaireProjection> releveDePartActionnaire(Long idActionnaire,Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);

    @Query(value = "select * from [Comptabilite].[FT_Journal_New](:idOpcvm,:codeJournal,:dateDebut,:dateFin)", nativeQuery = true)
    List<JournalProjection2> journal(Long idOpcvm,String codeJournal,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Comptabilite].[FT_Journal_New](:idOpcvm,:codeJournal,:dateDebut,:dateFin)", nativeQuery = true)
    Page<JournalProjection2> journal(Long idOpcvm,String codeJournal,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);

    @Query(value = "select * from [Comptabilite].[FT_SoldeToutCompte_New] (:codePlan,:idOpcvm,:numCompteComptable,:dateEstimation)", nativeQuery = true)
    List<SoldeDesComptesComptablesProjection> soldeDesComptesComptables(String codePlan,Long idOpcvm,String numCompteComptable,LocalDateTime dateEstimation);
    @Query(value = "select * from [Comptabilite].[FT_SoldeToutCompte_New] (:codePlan,:idOpcvm,:numCompteComptable,:dateEstimation)", nativeQuery = true)
    Page<SoldeDesComptesComptablesProjection> soldeDesComptesComptables(String codePlan,Long idOpcvm,String numCompteComptable,LocalDateTime dateEstimation,Pageable pageable);

    @Query(value = "select * from [Comptabilite].[FT_BalanceAvantInventaire_New] (:codePlan,:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    List<BalanceProjection> balanceAvantInventaire(String codePlan,Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Comptabilite].[FT_BalanceAvantInventaire_New] (:codePlan,:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    Page<BalanceProjection> balanceAvantInventaire(String codePlan,Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);
    @Query(value = "select * from [Comptabilite].[FT_Balance_New] (:codePlan,:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    List<BalanceProjection> balance(String codePlan,Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Comptabilite].[FT_Balance_New] (:codePlan,:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    Page<BalanceProjection> balance(String codePlan,Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_GrandLivre_New] (:idOpcvm,:codePlan,:numCompteComptable,:codeAnalytique,:typeAnalytique,:dateDebut,:dateFin)", nativeQuery = true)
    List<GrandLivreProjection> grandLivre(Long idOpcvm,String codePlan,String numCompteComptable,String codeAnalytique,String typeAnalytique,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_GrandLivre_New] (:idOpcvm,:codePlan,:numCompteComptable,:codeAnalytique,:typeAnalytique,:dateDebut,:dateFin)", nativeQuery = true)
    Page<GrandLivreProjection> grandLivre(Long idOpcvm,String codePlan,String numCompteComptable,String codeAnalytique,String typeAnalytique,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);

    @Query(value = "select * from [Operation].[FT_PointGlobalPeriodiqueSouscription_New] (:idOpcvm,:idSeance,:idPersonne,:dateDebut,:dateFin)", nativeQuery = true)
    List<PointSouscriptionGlobalProjection> pointSouscriptionGlobal(Long idOpcvm,Long idSeance,Long idPersonne,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Operation].[FT_PointGlobalPeriodiqueSouscription_New] (:idOpcvm,:idSeance,:idPersonne,:dateDebut,:dateFin)", nativeQuery = true)
    Page<PointSouscriptionGlobalProjection> pointSouscriptionGlobal(Long idOpcvm,Long idSeance,Long idPersonne,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);

    @Query(value = "select * from [Operation].[FT_PointPeriodiqueRachat_New] (:idOpcvm,:idSeance,:idActionnaire,:idPersonne,:dateDebut,:dateFin)", nativeQuery = true)
    List<PointRachatDetailleProjection> pointRachatDetaille(Long idOpcvm,Long idSeance,Long idActionnaire,Long idPersonne,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Operation].[FT_PointPeriodiqueRachat_New] (:idOpcvm,:idSeance,:idActionnaire,:idPersonne,:dateDebut,:dateFin)", nativeQuery = true)
    Page<PointRachatDetailleProjection> pointRachatDetaille(Long idOpcvm,Long idSeance,Long idActionnaire,Long idPersonne,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);

    @Query(value = "select * from [Operation].[FT_PointGlobalPeriodiqueRachat2_New] (:idOpcvm,:idSeance,:idPersonne,:dateDebut,:dateFin,:libelleTypePersonne)", nativeQuery = true)
    List<PointRachatGlobalProjection> pointRachatGlobal2(Long idOpcvm,Long idSeance,Long idPersonne,LocalDateTime dateDebut,LocalDateTime dateFin,String libelleTypePersonne);
   @Query(value = "select * from [Operation].[FT_PointGlobalPeriodiqueRachat_New] (:idOpcvm,:idSeance,:idPersonne,:dateDebut,:dateFin)", nativeQuery = true)
    List<PointRachatGlobalProjection> pointRachatGlobal(Long idOpcvm,Long idSeance,Long idPersonne,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Operation].[FT_PointGlobalPeriodiqueRachat_New] (:idOpcvm,:idSeance,:idPersonne,:dateDebut,:dateFin)", nativeQuery = true)
    Page<PointRachatGlobalProjection> pointRachatGlobal(Long idOpcvm,Long idSeance,Long idPersonne,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_BilanAn_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF1BilanProjection> etatFinancierAnnuelF1Bilan(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_VariationActifNet_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF1EtatVariationActifNetProjection> etatFinancierAnnuelF1EtatVariationActifNet(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_NotesSurRevenusPortfeuilleTitre_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF1NotesRevenusPortefeuilleTitreProjection> etatFinancierAnnuelF1NotesRevenusPortefeuilleTitre(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_ResultatAn_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF1ResultatProjection> etatFinancierAnnuelF1Resultat(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_NotesSurRevPlacementsMonetaires_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF1NotesRevenusPlacementsMonetairesProjection> etatFinancierAnnuelF1NotesRevenusPlacementsMonetaires(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_NotesSommesDistribuables_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF1NotesSommesDistribuablesProjection> etatFinancierAnnuelF1NotesSommesDistribuables(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_DonneesParActionEtRatioPertinents_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF1DonneesActionRatiosPertinentsProjection> etatFinancierAnnuelF1DonneesActionRatiosPertinents(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_EngagementHorsBilan_New](:idOpcvm,:dateEstimation )", nativeQuery = true)
    List<EtatFinancierAnnuelF1EngagementHorsBilanProjection> etatFinancierAnnuelF1EngagementHorsBilan(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_BilanAn_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF2BilanProjection> etatFinancierAnnuelF2Bilan(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_ResultatAn_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF2ResultatProjection> etatFinancierAnnuelF2Resultat(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_VariationActifNet_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF2EtatVariationActifNetProjection> etatFinancierAnnuelF2EtatVariationActifNet(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_NotesSurRevenusPortfeuilleTitre_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF2NotesRevenusPortefeuilleTitreProjection> etatFinancierAnnuelF2NotesRevenusPortefeuilleTitre(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_NotesSurRevPlacementsMonetaires_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF2NotesRevenusPlacementsMonetairesProjection> etatFinancierAnnuelF2NotesRevenusPlacementsMonetaires(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_NotesSommesDistribuables_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF2NotesSommesDistribuablesProjection> etatFinancierAnnuelF2NotesSommesDistribuables(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_DonneesParActionEtRatioPertinents_New](:idOpcvm,:annee)", nativeQuery = true)
    List<EtatFinancierAnnuelF2DonneesActionRatiosPertinentsProjection> etatFinancierAnnuelF2DonneesActionRatiosPertinents(Long idOpcvm,Integer annee);

    @Query(value = "select * from [Impressions].[FT_EntreeEnPortefeuilleTitre_New](:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    List<EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreProjection> etatFinancierAnnexesEtatsEntreesPortefeuilleTitre(Long idOpcvm,LocalDateTime dateDebut, LocalDateTime dateFin);

    @Query(value = "select * from [Impressions].[FT_SortieEnPortefeuilleTitre_New](:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    List<EtatFinancierAnnexesEtatSortiesPortefeuilleTitreProjection> etatFinancierAnnexesEtatSortiesPortefeuilleTitre(Long idOpcvm,LocalDateTime dateDebut, LocalDateTime dateFin);

    @Query(value = "select * from [Impressions].[FT_NotesSurLePortefeuilleTitreTrimestriel_New] " +
            "(:idOpcvm,:dateEstimation) order by groupe1 asc,group2 asc,libelleTypeTitre asc", nativeQuery = true)
    List<EtatFinancierAnnexesNotePortefeuilleTitresAnnuelProjection> etatFinancierAnnexesNotePortefeuilleTitresAnnuel(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_NotesSurPlacementsMonetairesTrimestriel_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierAnnexesNotePlacementsMonetairesAnnuelProjection> etatFinancierAnnexesNotePlacementsMonetairesAnnuel(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_NotesSurLeCapital_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierAnnexesNotesurleCapitalProjection> etatFinancierAnnexesNotesurleCapital(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_ActionsAdmisesAlaCote_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierAnnexesActionAdmiseCoteProjection> etatFinancierAnnexesActionAdmiseCote(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_NotesSurLesFraisDeGestionTrimestriel_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierAnnexesRemunerationGestionnaireDepositaireProjection> etatFinancierAnnexesRemunerationGestionnaireDepositaire(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_BilanTrimestriel_New](:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielBilanTrimestrielProjection> etatFinancierTrimestrielBilanTrimestriel(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_NotesSurLesFraisDeGestionTrimestriel_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielMontantFraisGestionProjection> etatFinancierTrimestrielMontantFraisGestion(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Operation].[FT_OperationChargeAEtaler](:idSeance,:idOpcvm,:supprimer,:estVerifie1,:estVerifie2)", nativeQuery = true)
    List<DocumentSeanceListeVerificationChargeProjection> documentSeanceListeVerificationCharge(Long idSeance,Long idOpcvm, Boolean supprimer, Boolean estVerifie1, Boolean estVerifie2);

    @Query(value = "select * from [Comptabilite].[FT_PosteComptableSeanceOpcvm](:idOpcvm,:idSeance,:estVerifie1,:estVerifie2)", nativeQuery = true)
    List<DocumentSeanceListeVerificationCodePosteProjection> documentSeanceListeVerificationCodePoste(Long idOpcvm,Long idSeance,Boolean estVerifie1,Boolean estVerifie2);

    @Query(value = "select * from [Comptabilite].[FT_ListeVerificationEcriture_Jasper](:idOpcvm,:codeTypeOperation,:dateDebut,:dateFin,:estVerifie1,:estVerifie2,:idOperation)", nativeQuery = true)
    List<DocumentSeanceListeVerificationEcritureChargeProjection> documentSeanceListeVerificationEcritureCharge(Long idOpcvm, String codeTypeOperation, LocalDateTime dateDebut, LocalDateTime dateFin, Boolean estVerifie1, Boolean estVerifie2, String idOperation);

    @Query(value = "select * from [Comptabilite].[FT_ListeVerificationEcriture_Jasper](:idOpcvm,:codeTypeOperation,:dateDebut,:dateFin,:estVerifie1,:estVerifie2,:idOperation)", nativeQuery = true)
    List<DocumentSeanceListeVerificationEcritureVdeProjection> documentSeanceListeVerificationEcritureVde(Long idOpcvm, String codeTypeOperation, LocalDateTime dateDebut, LocalDateTime dateFin, Boolean estVerifie1, Boolean estVerifie2, String idOperation);

    @Query(value = "select * from [Comptabilite].[FT_ListeVerificationEcriture_Jasper](:idOpcvm,:codeTypeOperation,:dateDebut,:dateFin,:estVerifie1,:estVerifie2,:idOperation)", nativeQuery = true)
    List<DocumentSeanceListeVerificationEcritureProjection> documentSeanceListeVerificationEcriture(Long idOpcvm, String codeTypeOperation, LocalDateTime dateDebut, LocalDateTime dateFin, Boolean estVerifie1, Boolean estVerifie2, String idOperation);

    @Query(value = "select * from [Parametre].[FT_DepotRachat_New](:idSeance,:idPersonne,:idOpcvm,:codeNatureOperation,:niveau1,:niveau2)", nativeQuery = true)
    List<DocumentSeanceListeVerificationRachatsProjection> documentSeanceListeVerificationRachats(Long idSeance,Long idPersonne,Long idOpcvm,String codeNatureOperation,Boolean niveau1,Boolean niveau2);

    @Query(value = "select * from [Parametre].[FT_DepotRachat_New] (:idSeance,:idPersonne,:idOpcvm,:codeNatureOperation,:niveau1,:niveau2)", nativeQuery = true)
    List<DocumentSeanceListeVerificationSouscriptionProjection> documentSeanceListeVerificationSouscription(Long idSeance, Long idPersonne, Long idOpcvm, String codeNatureOperation, Boolean niveau1, Boolean niveau2);

    @Query(value = "select * from [Operation].[FT_OperationDifferenceEstimation_New](:idSeance,:idOpcvm,:estVerifie1,:estVerifie2,:supprimer)", nativeQuery = true)
    List<DocumentSeanceListeVerificationVdeProjection> documentSeanceListeVerificationVde(Long idSeance, Long idOpcvm, Boolean estVerifie1, Boolean estVerifie2, Boolean supprimer);

    @Query(value = "select * from [Parametre].[FT_HistoriqueVL_New] (:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    Page<HistoriqueVLProjection> historiqueVL(Long idOpcvm, LocalDateTime dateDebut, LocalDateTime dateFin, Pageable pageable);

    @Query(value = "select * from [Parametre].[FT_HistoriqueVL_New] (:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    List<HistoriqueVLProjection> historiqueVL(Long idOpcvm, LocalDateTime dateDebut, LocalDateTime dateFin);

    @Query(value = "select * from Impressions.FT_CompositionDetailleActif_New (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<CompositionDetailleActifProjection> compositionDetailleActif(@Param("idOpcvm") Long idOpcvm, @Param("dateEstimation") LocalDateTime dateEstimation);

    @Query(value = "select * from [Operation].[FT_OperationSouscriptionRachat_New](:idOpcvm,:dateOuverture,:dateFermeture)", nativeQuery = true)
    List<PointPeriodiqueTAFAProjection> pointPeriodiqueTAFA(Long idOpcvm,LocalDateTime dateOuverture,LocalDateTime dateFermeture);
    @Query(value = "select * from [Operation].[FT_OperationSouscriptionRachat_New](:idOpcvm,:dateOuverture,:dateFermeture)", nativeQuery = true)
    Page<PointPeriodiqueTAFAProjection> pointPeriodiqueTAFA(Long idOpcvm, LocalDateTime dateOuverture, LocalDateTime dateFermeture, Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_ActifVlPart_New] (:idOpcvm)", nativeQuery = true)
    List<PointActifNetPartVlProjection> pointActifNetPartVl(@Param("idOpcvm") Long idOpcvm);
    @Query(value = "select * from [Impressions].[FT_ActifVlPart_New] (:idOpcvm)", nativeQuery = true)
    Page<PointActifNetPartVlProjection> pointActifNetPartVl(@Param("idOpcvm") Long idOpcvm, Pageable pageable);

    @Query(value = "select * from [EvenementSurValeur].[FT_OperationEvenementSurValeur](:idOpcvm, :dateDebut, :dateFin)", nativeQuery = true)
    List<PointRemboursementEffectuePeriodeProjection> pointRemboursementEffectuePeriode(@Param("idOpcvm") Long idOpcvm, @Param("dateDebut") LocalDateTime dateDebut, @Param("dateFin") LocalDateTime dateFin);
    @Query(value = "select * from [EvenementSurValeur].[FT_OperationEvenementSurValeur](:idOpcvm, :dateDebut, :dateFin)", nativeQuery = true)
    Page<PointRemboursementEffectuePeriodeProjection> pointRemboursementEffectuePeriode(@Param("idOpcvm") Long idOpcvm, @Param("dateDebut") LocalDateTime dateDebut, @Param("dateFin") LocalDateTime dateFin, Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_ResultatTrimestriel_New](:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielCompteResultatProjection> etatFinancierTrimestrielCompteResultat(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_VariationActifNetTrimestriel_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielVariationActifNetProjection> etatFinancierTrimestrielVariationActifNet(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_NotesSurRevPlacementsMonetairesTrimestriel_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielNotesRevenusPlacementsMonetairesProjection> etatFinancierTrimestrielNotesRevenusPlacementsMonetaires(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_NotesSurRevenusPortfeuilleTitreTrimestriel_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielNoteRevenusPortefeuilleTitreProjection> etatFinancierTrimestrielNoteRevenusPortefeuilleTitre(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_TableauAnalyseVLTrimestriel_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielTableauAnalyseVLProjection> etatFinancierTrimestrielTableauAnalyseVL(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_NotesSurLePortefeuilleTitreTrimestrielNew] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielNotePortefeuilleTitreProjection> etatFinancierTrimestrielNotePortefeuilleTitre(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_NotesSurPlacementsMonetairesTrimestrielNew] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielNotePlacementsMonetairesProjection> etatFinancierTrimestrielNotePlacementsMonetaires(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_ActionsAdmisesAlaCoteNew] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielActionsAdmisesCoteProjection> etatFinancierTrimestrielActionsAdmisesCote(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_NotesSurLeCapitalNew] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<EtatFinancierTrimestrielNoteCapitalProjection> etatFinancierTrimestrielNoteCapital(Long idOpcvm,LocalDateTime dateEstimation);

    @Query(value = "select * from [Impressions].[FT_EtatMensuelSouscriptions_New](:idOpcvm,:dateDebut,:dateFin)", nativeQuery = true)
    List<EtatFinancierTrimestrielEtatMensuelSouscriptionsProjection> etatFinancierTrimestrielEtatMensuelSouscriptions(Long idOpcvm,LocalDateTime dateDebut, LocalDateTime dateFin);

    @Query(value = "select * from [Impressions].[FT_PointPeriodiqueSouscription_New] (:idOpcvm,:idSeance,:idActionnaire,:idPersonne,:dateDebut,:dateFin)", nativeQuery = true)
    List<PointSouscriptionDetailleProjection> pointSouscriptionDetaille(Long idOpcvm,Long idSeance,Long idActionnaire,Long idPersonne,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_PointPeriodiqueSouscription_New] (:idOpcvm,:idSeance,:idActionnaire,:idPersonne,:dateDebut,:dateFin)", nativeQuery = true)
    Page<PointSouscriptionDetailleProjection> pointSouscriptionDetaille(Long idOpcvm,Long idSeance,Long idActionnaire,Long idPersonne,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_DeclarationComSurActifs_New] (:idOpcvm,:dateDebut,:dateFin,:trimestre,:anneeExo,:taux)", nativeQuery = true)
    List<DeclarationCommissionActifProjection> declarationCommissionActif(Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin,String trimestre,Long anneeExo,BigDecimal taux);
    @Query(value = "select * from [Impressions].[FT_DeclarationComSurActifs_New] (:idOpcvm,:dateDebut,:dateFin,:trimestre,:anneeExo,:taux)", nativeQuery = true)
    Page<DeclarationCommissionActifProjection> declarationCommissionActif(Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin,String trimestre,Long anneeExo,BigDecimal taux,Pageable pageable);

    @Query(value = "select * from [Operation].[FT_InvestissementPeriodique_New](:dateDeb,:dateFin,:idOpcvm,:typeOp)", nativeQuery = true)
    List<PointInvestissementProjection> pointInvestissement(LocalDateTime dateDeb,LocalDateTime dateFin,Long idOpcvm,String typeOp);
    @Query(value = "select * from [Operation].[FT_InvestissementPeriodique_New](:dateDeb,:dateFin,:idOpcvm,:typeOp)", nativeQuery = true)
    Page<PointInvestissementProjection> pointInvestissement(LocalDateTime dateDeb,LocalDateTime dateFin,Long idOpcvm,String typeOp,Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_PrevisionnelRembourement_New](:idOpcvm,:echue,:detache,:traiter,:datedebut,:datefin)", nativeQuery = true)
    List<PrevisionnelRemboursementsProjection> previsionnelRemboursements(Long idOpcvm,Boolean echue,Boolean detache,Boolean traiter,LocalDateTime datedebut,LocalDateTime datefin);
    @Query(value = "select * from [Impressions].[FT_PrevisionnelRembourement_New](:idOpcvm,:echue,:detache,:traiter,:datedebut,:datefin)", nativeQuery = true)
    Page<PrevisionnelRemboursementsProjection> previsionnelRemboursements(Long idOpcvm,Boolean echue,Boolean detache,Boolean traiter,LocalDateTime datedebut,LocalDateTime datefin,Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_SuiviEcheanceTitres_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    List<SuiviEcheanceTitreProjection> suiviEcheanceTitre(Long idOpcvm,LocalDateTime dateEstimation);
    @Query(value = "select * from [Impressions].[FT_SuiviEcheanceTitres_New] (:idOpcvm,:dateEstimation)", nativeQuery = true)
    Page<SuiviEcheanceTitreProjection> suiviEcheanceTitre(Long idOpcvm,LocalDateTime dateEstimation,Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_AvisTransfertPart](:idOperation,:idOpcvm,:dateDeb,:dateFin)", nativeQuery = true)
    List<AvisTransfertPartProjection> avisTransfertPart(String idOperation,Long idOpcvm,LocalDateTime dateDeb,LocalDateTime dateFin);

    @Query(value = "select * from [Operation].[FT_OperationTransfertDePart_New](:idOpcvm, :dateOuverture, :dateFermeture)", nativeQuery = true)
    Page<OperationTransfertDePartProjection> operationTransfertPart(Long idOpcvm,LocalDateTime dateOuverture,LocalDateTime dateFermeture,Pageable pageable);

    @Query(value = "select * from [Operation].[FT_OperationTransfertDePart_New](:idOpcvm, :dateOuverture, :dateFermeture)", nativeQuery = true)
    List<OperationTransfertDePartProjection> operationTransfertPart(Long idOpcvm,LocalDateTime dateOuverture,LocalDateTime dateFermeture);
    @Query(value = "select * from [Impressions].[FT_ReleveTitreFCP_New] (:idOpcvm,:dateDebut,:dateFin)" +
            " order by idTitre asc", nativeQuery = true)
    Page<ReleveTitreFCPProjection> releveTitreFCP(Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_ReleveTitreFCP_New] (:idOpcvm,:dateDebut,:dateFin)" +
            " order by idTitre asc", nativeQuery = true)
    List<ReleveTitreFCPProjection> releveTitreFCP(Long idOpcvm,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_PointTresorerie] (:dateEstimation)", nativeQuery = true)
    List<PointTresorerieProjection> pointTresorerie(LocalDateTime dateEstimation);
    @Query(value = "select * from [Comptabilite].[FT_ModeleEcriture_New]()", nativeQuery = true)
    List<ProcedureComptableProjection> procedureComptable();
    @Query(value = "select * from [Parametre].[FT_PersonnePhysiqueMorale_SP2_New](:idOpcvm,:libelleQualite,:statutCompte)", nativeQuery = true)
    List<PersonnePhysiqueMoraleProjection> afficherPersonnePhysiqueMorale(Long idOpcvm,String libelleQualite,String statutCompte);
    @Query(value = "select * from [Parametre].[FT_PersonnePhysiqueMorale_SP2_New](:idOpcvm,:libelleQualite,:statutCompte) " +
            "where typepersonne=:typePersonne", nativeQuery = true)
    List<PersonnePhysiqueMoraleProjection> afficherPersonnePhysiqueMoraleSelonType(Long idOpcvm,String libelleQualite,String statutCompte,String typePersonne);
    @Query(value = "select * from [Parametre].[FT_PersonnePhysiqueMorale_SP2_New](:idOpcvm,:libelleQualite,:statutCompte) " +
            "where typepersonne=:typePersonne and concat(nomSigle,' ',prenomRaison,' ',numCompteDepositaire) like %:valeur%", nativeQuery = true)
    List<PersonnePhysiqueMoraleProjection> rechercherPersonnePhysiqueMoraleSelonType(Long idOpcvm,String libelleQualite,String statutCompte,String typePersonne,String valeur);
    @Query(value = "select * from [Parametre].[FT_PersonnePhysiqueMorale_SP2_New](:idOpcvm,:libelleQualite,:statutCompte) " +
            "where concat(nomSigle,' ',prenomRaison,' ',numCompteDepositaire) like %:valeur%", nativeQuery = true)
    List<PersonnePhysiqueMoraleProjection> afficherPersonnePhysiqueMorale(Long idOpcvm,String libelleQualite,String statutCompte,String valeur);
    @Query(value = "select * from [Impressions].[FT_EtatRecapInfosClients_New] (:idPersonne) ", nativeQuery = true)
    List<FicheClientProjection> afficherFicheClient(String idPersonne);
    @Query(value = "select * from [Impressions].[FT_EtatSuiviActionnaires_New] (:idOpcvm,:dateDeb,:dateFin) " +
            "", nativeQuery = true)
    List<EtatsSuiviActionnaireProjection> etatSuiviActionnaire(Long idOpcvm,LocalDateTime dateDeb,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_EtatSuiviClients](:idActionnaire,:idOpcvm,:dateEddition) " +
            "order by idactionnaire asc,idopcvm asc,titre asc", nativeQuery = true)
    List<EtatsSuiviClientProjection> etatSuiviClient(String idActionnaire,Long idOpcvm,LocalDateTime dateEddition);
    @Query(value = "select * from [Impressions].[FT_EtatSuiviActionnaires_New] (:idOpcvm,:dateDeb,:dateFin) " +
            "", nativeQuery = true)
    Page<EtatsSuiviActionnaireProjection> etatSuiviActionnaire(Long idOpcvm,LocalDateTime dateDeb,LocalDateTime dateFin,Pageable pageable);
    @Query(value = "select * from [Titre].[FT_CoursTitre_New](:codePlace,:date,:estVerifie1,:estVerifie2) " +
            " order by libellesecteurboursier asc,idtitre asc", nativeQuery = true)
    List<CoursTitreProjection> coursTitreNew(String codePlace,LocalDateTime date,Boolean estVerifie1,Boolean estVerifie2);
    @Query(value = "select * from [Titre].[FT_PlaceCoursTitre_New](:codePlace,:dateDebut,:dateFin)" +
            "", nativeQuery = true)
    Page<PlaceCoursTitreProjection> placeCoursTitre(String codePlace,LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);
    @Query(value = "select * from [Impressions].[FT_PointDesFraisDeFonctionnement_New](:idOpcvm,:dateDeb,:dateFin) " +
            "", nativeQuery = true)
    List<EtatFraisFonctionnementProjection> etatPointFraisFonctionnement(Long idOpcvm,LocalDateTime dateDeb,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_PointDesFraisDeFonctionnement_New] (:idOpcvm,:dateDeb,:dateFin) " +
            "", nativeQuery = true)
    Page<EtatFraisFonctionnementProjection> etatPointFraisFonctionnement(Long idOpcvm,LocalDateTime dateDeb,LocalDateTime dateFin,Pageable pageable);
    @Query(value = "select * from [Impressions].[FT_RepartitionPortefeuilleFonds_New] (:idOpcvm ,:dateEstimation) " +
            "", nativeQuery = true)
    List<PointRepartitionPortefeuilleProjection> pointRepartitionPortefeuille(Long idOpcvm,LocalDateTime dateEstimation);
    @Query(value = "select * from [Impressions].[FT_RepartitionPortefeuilleFonds_New] (:idOpcvm ,:dateEstimation)" +
            "", nativeQuery = true)
    Page<PointRepartitionPortefeuilleProjection> pointRepartitionPortefeuille(Long idOpcvm,LocalDateTime dateEstimation,Pageable pageable);
   @Query(value = "select * from [Impressions].[FT_EvolutionActifPart_New] (:dateEstimation) " +
            "", nativeQuery = true)
    List<EvolutionActifNetProjection> evolutionAtifNet(LocalDateTime dateEstimation);
    @Query(value = "select * from [Impressions].[FT_EvolutionActifPart_New] (:dateEstimation) " +
            "", nativeQuery = true)
    Page<EvolutionActifNetProjection> evolutionAtifNet(LocalDateTime dateEstimation,Pageable pageable);
    @Query(value = "select * from [Impressions].[FT_EcvolutionVL_New] (:idOpcvm, :mois1,:mois2) " +
            "", nativeQuery = true)
    List<EvolutionVLProjection> evolutionVL(Long idOpcvm,Long mois1,Long mois2);
    @Query(value = "select * from [Impressions].[FT_EcvolutionVL_New] (:idOpcvm, :mois1,:mois2)" +
            "", nativeQuery = true)
    Page<EvolutionVLProjection> evolutionVL(Long idOpcvm,Long mois1,Long mois2,Pageable pageable);
    @Query(value = "select * from [Impressions].[FT_PointSousRach_New](:idOpcvm,:dateDeb,:dateFin,:type) " +
            "", nativeQuery = true)
    List<PointSouscriptionRachatProjection> pointSouscriptionRachat(Long idOpcvm,LocalDateTime dateDeb,LocalDateTime dateFin,String type);
    @Query(value = "select * from [Impressions].[FT_PointSousRach_New](:idOpcvm,:dateDeb,:dateFin,:type)" +
            "", nativeQuery = true)
    Page<PointSouscriptionRachatProjection> pointSouscriptionRachat(Long idOpcvm,LocalDateTime dateDeb,LocalDateTime dateFin,String type,Pageable pageable);

    @Query(value = "select * from [Impressions].[FT_ReleveActionnaireImpression_New] (:idActionnaire,:dateDebut,:dateFin) " +
            "order by idactionnaire asc,dateoperation asc", nativeQuery = true)
    List<ReleveActionnaireProjection> releveActionnaire(String idActionnaire,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_PerformancePortefeuilleActionnaire](:idOpcvm,:idActionnaire," +
            ":dateDebut,:dateFin) " +
            "order by idactionnaire asc,idopcvm asc", nativeQuery = true)
    List<PerformancePortefeuilleActionnaireProjection> performancePortefeuilleActionnaire(Long idOpcvm,String idActionnaire,LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_PortefeuilleActionnaire_New_JAVA](:idOpcvm,:idActionnaire,:dateDebutEstimation,:dateEstimation) " +
            "order by idopcvm asc", nativeQuery = true)
    Page<PortefeuilleActionnaireProjection> portefeuilleActionnaire(Long idOpcvm,String idActionnaire,LocalDateTime dateDebutEstimation,LocalDateTime dateEstimation,Pageable pageable);
     @Query(value = "select * from [Impressions].[FT_PortefeuilleActionnaire_New_JAVA](:idOpcvm,:idActionnaire,:dateDebutEstimation,:dateEstimation) " +
            "order by idopcvm asc", nativeQuery = true)
    List<PortefeuilleActionnaireProjection> portefeuilleActionnaire(Long idOpcvm,String idActionnaire,LocalDateTime dateDebutEstimation,LocalDateTime dateEstimation);
    @Query(value = "select * from [Impressions].[FT_RegistreActionnaire_New](:dateDebut,:dateFin)", nativeQuery = true)
    List<HistoriqueActionnaireProjection> historiqueActionnaire(LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_RegistreActionnaire_New](:dateDebut,:dateFin)", nativeQuery = true)
    List<HistoriqueActionnaireProjection> historiqueActionnaireListe(LocalDateTime dateDebut,LocalDateTime dateFin);
    @Query(value = "select * from [Impressions].[FT_RegistreActionnaire_New](:dateDebut,:dateFin)", nativeQuery = true)
    Page<HistoriqueActionnaireProjection> historiqueActionnaire(LocalDateTime dateDebut,LocalDateTime dateFin,Pageable pageable);
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
    @Query(value = "SELECT * FROM [Parametre].[FT_DepotRachat_New](:IdSeance," +
            ":IdPersonne,:IdOpcvm,:codeNatureOperation,:niveau1" +
            ",:niveau2)",nativeQuery = true)
    List<FT_DepotRachatProjection> afficherFT_DepotRachat(@Param("IdSeance") Long IdSeance,
                                                    @Param("IdPersonne") Long IdPersonne,
                                                    @Param("IdOpcvm") Long IdOpcvm,
                                                    @Param("codeNatureOperation") String codeNatureOperation,
                                                    @Param("niveau1") boolean niveau1,
                                                    @Param("niveau2") boolean niveau2
                                          );
    @Query(value = "SELECT * FROM [Parametre].[FT_DepotRachat_New_Sous_Trans_Titre](:IdSeance," +
            ":IdPersonne,:IdOpcvm,:codeNatureOperation,:niveau1" +
            ",:niveau2,:estVerifier)",nativeQuery = true)
    List<FT_DepotRachatProjection> afficherFT_DepotRachat(@Param("IdSeance") Long IdSeance,
                                                    @Param("IdPersonne") Long IdPersonne,
                                                    @Param("IdOpcvm") Long IdOpcvm,
                                                    @Param("codeNatureOperation") String codeNatureOperation,
                                                    @Param("niveau1") boolean niveau1,
                                                    @Param("niveau2") boolean niveau2,
                                                    boolean estVerifier
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
            ":estVerifie1,:estVerifie2) order by codeClasseTitre asc,symbolTitre asc",
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
    @Query(value = "select * from [EvenementSurValeur].[FT_OperationDetachement_Attente_Reglement](:idOpcvm" +
            ",:dateOperation) order by dateOperation asc",
            nativeQuery = true
    )
    List<OperationDetachementProjection> operationDetachementListe(
            Long idOpcvm,
            Date dateOperation
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
    @Query(value = "select * from [EvenementSurValeur].[FT_OperationEvenementSurValeur_New](:idOpcvm) order by dateOperation desc",
                nativeQuery = true
        )
    Page<OperationEvenementSurValeurProjection> operationEvenementSurValeurListe(
                Long idOpcvm,
                Pageable pageable
        );
    @Query(value = "select * from [EvenementSurValeur].[FT_OperationEvenementSurValeur_New](:idOpcvm)" +
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
    @Query(value = "select * from [Comptabilite].[FT_PosteComptableSeanceOpcvm](:idOpcvm,:idSeance," +
            ":estVerifie1,:estVerifie2)", nativeQuery = true)
    List<CodePosteComptableProjection> afficherCodePosteComptable(
        Long idOpcvm,Long idSeance,Boolean estVerifie1,Boolean estVerifie2
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
