package com.ged.controller;

import com.ged.dao.LibraryDao;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.request.*;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.service.AppService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("libraries")
public class LibraryController {
    private final LibraryDao libraryDao;
    private final AppService service;

    public LibraryController(LibraryDao libraryDao, AppService service) {
        this.libraryDao = libraryDao;
        this.service = service;
    }

    @PostMapping("/{idActionnaire}/{idOpcvm}")
    public BigDecimal solde(
            @PathVariable("idActionnaire") Long idActionnaire,
            @PathVariable("idOpcvm") Long idOpcvm) {
        return libraryDao.solde(idActionnaire, idOpcvm);
    }

    @PostMapping("/{idOpcvm}")
    public SeanceOpcvm currentSeance(@PathVariable("idOpcvm") Long id) {
        return libraryDao.currentSeance(id);
    }

    @PostMapping("/opcvm/solde/tout/compte")
    public ResponseEntity<?> soldeToutCompte(@RequestBody @Valid SoldeToutCompteRequest request) {
        return service.soldeToutCompte(request);
    }
//    @PostMapping("/opcvm/etats/portefeuille")
    @PostMapping("/opcvm/etats/portefeuille")
    public ResponseEntity<Object> porteFeuille(@RequestBody @Valid DifferenceEstimationRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=portefeuille" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherPortefeuille(request,response);
    }
    @PostMapping("/opcvm/portefeuille")
    public ResponseEntity<?> porteFeuille(@RequestBody @Valid ConstatationChargeListeRequest request) {
        return service.afficherPortefeuille(request);
    }
    @PostMapping("/opcvm/portefeuille/liste")
    public ResponseEntity<?> porteFeuilleListe(@RequestBody @Valid ConstatationChargeListeRequest request) {
        return service.afficherPortefeuilleListe(request);
    }


    //relevepart fcp
    @PostMapping("/opcvm/etats/relevepartfcp")
    public ResponseEntity<Object> relevepartfcp(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Releve de part FCP" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherReleveDePartFCP(request,response);
    }
    @PostMapping("/opcvm/relevepartfcp")
    public ResponseEntity<?> relevepartfcp(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.afficherReleveDePartFCP(request);
    }
    @PostMapping("/opcvm/relevepartfcp/liste")
    public ResponseEntity<?> relevepartfcpListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.afficherReleveDePartFCPListe(request);
    }

    //relevePartActionnaire
    @PostMapping("/opcvm/etats/relevepartactionnaire")
    public ResponseEntity<Object> relevepartactionnaire(@RequestBody @Valid ReleveDePartActionnaireRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Releve de part Actionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherReleveDePartActionnaire(request,response);
    }
    @PostMapping("/opcvm/relevepartactionnaire")
    public ResponseEntity<?> relevepartactionnaire(@RequestBody @Valid ReleveDePartActionnaireRequest request) {
        return service.afficherReleveDePartActionnaire(request);
    }
    @PostMapping("/opcvm/relevepartactionnaire/liste")
    public ResponseEntity<?> relevepartactionnaireListe(@RequestBody @Valid ReleveDePartActionnaireRequest request) {
        return service.afficherReleveDePartActionnaire(request);
    }

    //journal
    @PostMapping("/opcvm/etats/journal")
    public ResponseEntity<Object> journal(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Journal" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherJournal(request,response);
    }

    //balance
    @PostMapping("/opcvm/etats/balance")
    public ResponseEntity<Object> balance(@RequestBody @Valid BalanceRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Balance" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherBalance(request,response);
    }
    @PostMapping("/opcvm/balance")
    public ResponseEntity<?> balance(@RequestBody @Valid BalanceRequest request) {
        return service.afficherBalance(request);
    }
    @PostMapping("/opcvm/balance/liste")
    public ResponseEntity<?> balanceListe(@RequestBody @Valid BalanceRequest request) {
        return service.afficherBalance(request);
    }

    //grandLivre
    @PostMapping("/opcvm/etats/grandlivre")
    public ResponseEntity<Object> grandlivre(@RequestBody @Valid GrandLivreRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Grand Livre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherGrandLivre(request,response);
    }
    @PostMapping("/opcvm/grandlivre")
    public ResponseEntity<?> grandlivre(@RequestBody @Valid GrandLivreRequest request) {
        return service.afficherGrandLivre(request);
    }
    @PostMapping("/opcvm/grandlivre/liste")
    public ResponseEntity<?> grandlivreListe(@RequestBody @Valid GrandLivreRequest request) {
        return service.afficherGrandLivre(request);
    }

    //soldedescomptescomptable
    @PostMapping("/opcvm/etats/soldedescomptescomptables")
    public ResponseEntity<Object> soldedescomptescomptables(@RequestBody @Valid SoldeDesComptesComptablesRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Solde des comptes comptables" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherSoldeDesComptesComptables(request,response);
    }
    @PostMapping("/opcvm/soldedescomptescomptables")
    public ResponseEntity<?> soldedescomptescomptables(@RequestBody @Valid SoldeDesComptesComptablesRequest request) {
        return service.afficherSoldeDesComptesComptables(request);
    }
    @PostMapping("/opcvm/soldedescomptescomptables/liste")
    public ResponseEntity<?> soldedescomptescomptablesListe(@RequestBody @Valid SoldeDesComptesComptablesRequest request) {
        return service.afficherSoldeDesComptesComptables(request);
    }

    //pointsouscriptiondetaille
    @PostMapping("/opcvm/etats/pointsouscriptiondetaille")
    public ResponseEntity<Object> pointsouscriptiondetaille(@RequestBody @Valid PointSouscriptionDetailleRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Point des souscriptions detaillees" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherPointSouscriptionDetaille(request,response);
    }
    @PostMapping("/opcvm/pointsouscriptiondetaille")
    public ResponseEntity<?> pointsouscriptiondetaille(@RequestBody @Valid PointSouscriptionDetailleRequest request) {
        return service.afficherPointSouscriptionDetaille(request);
    }
    @PostMapping("/opcvm/pointsouscriptiondetaille/liste")
    public ResponseEntity<?> pointsouscriptiondetailleListe(@RequestBody @Valid PointSouscriptionDetailleRequest request) {
        return service.afficherPointSouscriptionDetaille(request);
    }

    //pointsouscriptionglobal
    @PostMapping("/opcvm/etats/pointsouscriptionglobal")
    public ResponseEntity<Object> pointsouscriptionglobal(@RequestBody @Valid PointSouscriptionGlobalRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Point des souscriptions global" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherPointSouscriptionGlobal(request,response);
    }
    @PostMapping("/opcvm/pointsouscriptionglobal")
    public ResponseEntity<?> pointsouscriptionglobal(@RequestBody @Valid PointSouscriptionGlobalRequest request) {
        return service.afficherPointSouscriptionGlobal(request);
    }
    @PostMapping("/opcvm/pointsouscriptionglobal/liste")
    public ResponseEntity<?> pointsouscriptionglobalListe(@RequestBody @Valid PointSouscriptionGlobalRequest request) {
        return service.afficherPointSouscriptionGlobal(request);
    }

    //pointrachatglobal
    @PostMapping("/opcvm/etats/pointrachatglobal")
    public ResponseEntity<Object> pointrachatglobal(@RequestBody @Valid PointRachatGlobalRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Point des rachats global" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherPointRachatGlobal(request,response);
    }
    @PostMapping("/opcvm/pointrachatglobal")
    public ResponseEntity<?> pointrachatglobal(@RequestBody @Valid PointRachatGlobalRequest request) {
        return service.afficherPointRachatGlobal(request);
    }
    @PostMapping("/opcvm/pointrachatglobal/liste")
    public ResponseEntity<?> pointrachatglobalListe(@RequestBody @Valid PointRachatGlobalRequest request) {
        return service.afficherPointRachatGlobal(request);
    }

    //pointrachatdetaille
    @PostMapping("/opcvm/etats/pointrachatdetaille")
    public ResponseEntity<Object> pointrachatdetaille(@RequestBody @Valid PointRachatDetailleRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Point des rachats detaille" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherPointRachatDetaille(request,response);
    }
    @PostMapping("/opcvm/pointrachatdetaille")
    public ResponseEntity<?> pointrachatdetaille(@RequestBody @Valid PointRachatDetailleRequest request) {
        return service.afficherPointRachatDetaille(request);
    }
    @PostMapping("/opcvm/pointrachatdetaille/liste")
    public ResponseEntity<?> pointrachatdetailleListe(@RequestBody @Valid PointRachatDetailleRequest request) {
        return service.afficherPointRachatDetaille(request);
    }

    //etatfinancierannuelf1bilan
    @PostMapping("/opcvm/etats/etatfinancierannuelf1bilan")
    public ResponseEntity<Object> etatfinancierannuelf1bilan(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 bilan" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF1Bilan(request,response);
    }

    //etatfinancierannuelf1resultat
    @PostMapping("/opcvm/etats/etatfinancierannuelf1resultat")
    public ResponseEntity<Object> etatfinancierannuelf1resultat(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 resultat" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF1Resultat(request,response);
    }

    //etatfinancierannuelf1etatvariationactifnet
    @PostMapping("/opcvm/etats/etatfinancierannuelf1etatvariationactifnet")
    public ResponseEntity<Object> etatfinancierannuelf1etatvariationactifnet(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 resultat" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF1EtatVariationActifNet(request,response);
    }

    //etatfinancierannuelf1notesrevenusportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinancierannuelf1notesrevenusportefeuilletitre")
    public ResponseEntity<Object> etatfinancierannuelf1notesrevenusportefeuilletitre(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 note sur les revenus portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF1NotesRevenusPortefeuilleTitre(request,response);
    }

    //etatfinancierannuelf1notesrevenusplacementsmonetaires
    @PostMapping("/opcvm/etats/etatfinancierannuelf1notesrevenusplacementsmonetaires")
    public ResponseEntity<Object> etatfinancierannuelf1notesrevenusplacementsmonetaires(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 note sur les revenus placements monetaires" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF1NotesRevenusPlacementsMonetaires(request,response);
    }

    //etatfinancierannuelf1notessommesdistribuables
    @PostMapping("/opcvm/etats/etatfinancierannuelf1notessommesdistribuables")
    public ResponseEntity<Object> etatfinancierannuelf1notessommesdistribuables(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 note sur les sommes distribuables" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF1NotesSommesDistribuables(request,response);
    }

    //etatfinancierannuelf1donneesactionratiospertinents
    @PostMapping("/opcvm/etats/etatfinancierannuelf1donneesactionratiospertinents")
    public ResponseEntity<Object> etatfinancierannuelf1donneesactionratiospertinents(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 donnees par action et ratios pertinents" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF1DonneesActionRatiosPertinents(request,response);
    }

    //etatfinancierannuelf1engagementhorsbilan
    @PostMapping("/opcvm/etats/etatfinancierannuelf1engagementhorsbilan")
    public ResponseEntity<Object> etatfinancierannuelf1engagementhorsbilan(@RequestBody @Valid EtatFinancierAnnuelF1EngagementHorsBilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 engagement hors bilan" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF1EngagementHorsBilan(request,response);
    }

    //etatfinancierannuelf2bilan
    @PostMapping("/opcvm/etats/etatfinancierannuelf2bilan")
    public ResponseEntity<Object> etatfinancierannuelf2bilan(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 bilan" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF2Bilan(request,response);
    }

    //etatfinancierannuelf2resultat
    @PostMapping("/opcvm/etats/etatfinancierannuelf2resultat")
    public ResponseEntity<Object> etatfinancierannuelf2resultat(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 resultat" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF2Resultat(request,response);
    }

    //etatfinancierannuelf2etatvariationactifnet
    @PostMapping("/opcvm/etats/etatfinancierannuelf2etatvariationactifnet")
    public ResponseEntity<Object> etatfinancierannuelf2etatvariationactifnet(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 etat de variation actif net" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF2EtatVariationActifNet(request,response);
    }

    //etatfinancierannuelf2notesrevenusportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinancierannuelf2notesrevenusportefeuilletitre")
    public ResponseEntity<Object> etatfinancierannuelf2notesrevenusportefeuilletitre(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 note sur les revenus portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF2NotesRevenusPortefeuilleTitre(request,response);
    }

    //etatfinancierannuelf2notesrevenusplacementsmonetaires
    @PostMapping("/opcvm/etats/etatfinancierannuelf2notesrevenusplacementsmonetaires")
    public ResponseEntity<Object> etatfinancierannuelf2notesrevenusplacementsmonetaires(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 note sur les revenus placements monetaires" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF2NotesRevenusPlacementsMonetaires(request,response);
    }

    //etatfinancierannuelf2notessommesdistribuables
    @PostMapping("/opcvm/etats/etatfinancierannuelf2notessommesdistribuables")
    public ResponseEntity<Object> etatfinancierannuelf2notessommesdistribuables(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 note sur les sommes distribuables" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF2NotesSommesDistribuables(request,response);
    }

    //etatfinancierannuelf2donneesactionratiospertinents
    @PostMapping("/opcvm/etats/etatfinancierannuelf2donneesactionratiospertinents")
    public ResponseEntity<Object> etatfinancierannuelf2donneesactionratiospertinents(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 donnees par action et ratios pertinents" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnuelF2DonneesActionRatiosPertinents(request,response);
    }

    //etatfinancierannexesetatsentreesportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinancierannexesetatsentreesportefeuilletitre")
    public ResponseEntity<Object> etatfinancierannexesetatsentreesportefeuilletitre(@RequestBody @Valid EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes etats des entrees en portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnexesEtatsEntreesPortefeuilleTitre(request,response);
    }

    //etatfinancierannexesetatsortiesportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinancierannexesetatsortiesportefeuilletitre")
    public ResponseEntity<Object> etatfinancierannexesetatsortiesportefeuilletitre(@RequestBody @Valid EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes etats des sorties en portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnexesEtatSortiesPortefeuilleTitre(request,response);
    }

    //etatfinancierannexesnoteportefeuilletitresannuel
    @PostMapping("/opcvm/etats/etatfinancierannexesnoteportefeuilletitresannuel")
    public ResponseEntity<Object> etatfinancierannexesnoteportefeuilletitresannuel(@RequestBody @Valid EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes note sur le portefeuille titre annuel" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnexesNotePortefeuilleTitresAnnuel(request,response);
    }

    //etatfinancierannexesnoteplacementsmonetairesannuel
    @PostMapping("/opcvm/etats/etatfinancierannexesnoteplacementsmonetairesannuel")
    public ResponseEntity<Object> etatfinancierannexesnoteplacementsmonetairesannuel(@RequestBody @Valid EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes note sur les placements monetaires annuel" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnexesNotePlacementsMonetairesAnnuel(request,response);
    }

    //etatfinancierannexesnotesurlecapital
    @PostMapping("/opcvm/etats/etatfinancierannexesnotesurlecapital")
    public ResponseEntity<Object> etatfinancierannexesnotesurlecapital(@RequestBody @Valid EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes note sur le capital" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnexesNotesurleCapital(request,response);
    }

    //etatfinancierannexesactionadmisecote
    @PostMapping("/opcvm/etats/etatfinancierannexesactionadmisecote")
    public ResponseEntity<Object> etatfinancierannexesactionadmisecote(@RequestBody @Valid EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes action admise cote" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnexesActionAdmiseCote(request,response);
    }

    //etatfinancierannexesremunerationgestionnairedepositaire
    @PostMapping("/opcvm/etats/etatfinancierannexesremunerationgestionnairedepositaire")
    public ResponseEntity<Object> etatfinancierannexesremunerationgestionnairedepositaire(@RequestBody @Valid EtatFinancierAnnexesRemunerationGestionnaireDepositaireRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes remuneration gestionnaire depositaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierAnnexesRemunerationGestionnaireDepositaire(request,response);
    }

    //etatfinanciertrimestrielbilantrimestriel
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielbilantrimestriel")
    public ResponseEntity<Object> etatfinanciertrimestrielbilantrimestriel(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel bilan trimestriel" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielBilanTrimestriel(request,response);
    }

    //etatfinanciertrimestrielcompteresultat
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielcompteresultat")
    public ResponseEntity<Object> etatfinanciertrimestrielcompteresultat(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel compte resultat" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielCompteResultat(request,response);
    }

    //etatfinanciertrimestrielvariationactifnet
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielvariationactifnet")
    public ResponseEntity<Object> etatfinanciertrimestrielvariationactifnet(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel variation de l'actif net" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielVariationActifNet(request,response);
    }

    //etatfinanciertrimestrielnotesrevenusplacementsmonetaires
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnotesrevenusplacementsmonetaires")
    public ResponseEntity<Object> etatfinanciertrimestrielnotesrevenusplacementsmonetaires(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel notes sur les revenus placements monetaires" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielNotesRevenusPlacementsMonetaires(request,response);
    }

    //etatfinanciertrimestrielnoterevenusportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnoterevenusportefeuilletitre")
    public ResponseEntity<Object> etatfinanciertrimestrielnoterevenusportefeuilletitre(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel notes sur les revenus portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielNoteRevenusPortefeuilleTitre(request,response);
    }

    //etatfinanciertrimestrieltableauanalysevl
    @PostMapping("/opcvm/etats/etatfinanciertrimestrieltableauanalysevl")
    public ResponseEntity<Object> etatfinanciertrimestrieltableauanalysevl(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel  tableau analyse VL" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielTableauAnalyseVL(request,response);
    }

    //etatfinanciertrimestrielnoteportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnoteportefeuilletitre")
    public ResponseEntity<Object> etatfinanciertrimestrielnoteportefeuilletitre(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel note sur le portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielNotePortefeuilleTitre(request,response);
    }

    //etatfinanciertrimestrielnoteplacementsmonetaires
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnoteplacementsmonetaires")
    public ResponseEntity<Object> etatfinanciertrimestrielnoteplacementsmonetaires(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel note sur les placements monetaires" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielNotePlacementsMonetaires(request,response);
    }

    //etatfinanciertrimestrielactionsadmisescote
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielactionsadmisescote")
    public ResponseEntity<Object> etatfinanciertrimestrielactionsadmisescote(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel action admises cote" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielActionsAdmisesCote(request,response);
    }

    //etatfinanciertrimestrielnotecapital
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnotecapital")
    public ResponseEntity<Object> etatfinanciertrimestrielnotecapital(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel note sur le capital" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielNoteCapital(request,response);
    }

    //etatfinanciertrimestrieletatmensuelsouscriptions
    @PostMapping("/opcvm/etats/etatfinanciertrimestrieletatmensuelsouscriptions")
    public ResponseEntity<Object> etatfinanciertrimestrieletatmensuelsouscriptions(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel etat mensuel des souscriptions" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherEtatFinancierTrimestrielEtatMensuelSouscriptions(request,response);
    }

    @PostMapping("/etats/pointtresorerie")
    public ResponseEntity<Object> pointTresorerie(@RequestBody @Valid BeginEndDateParameter request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pointTresorerie" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.pointTresorerie(request,response);
    }
    @PostMapping("/etats/ficheclient")
    public ResponseEntity<Object> ficheClient(@RequestBody FicheClientRequest request,
                                              HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=fiche_client" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.ficheClientEtat(request,response);
    }
    @PostMapping("/etats/historiqueactionnaire")
    public ResponseEntity<Object> historiqueActionnaire(@RequestBody @Valid BeginEndDateParameter request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=historiqueActionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.historiqueActionnaire(request,response);
    }
    @PostMapping("/historiqueactionnaire")
    public ResponseEntity<?> historiqueActionnaire(@RequestBody @Valid HistoriqueActionnaireRequest request){

        return service.historiqueActionnaire(request);
    }
    @PostMapping("/historiqueactionnaire/liste")
    public ResponseEntity<?> historiqueActionnaireListe(@RequestBody @Valid HistoriqueActionnaireRequest request){

        return service.historiqueActionnaireListe(request);
    }
    @PostMapping("/etats/releveactionnaire")
    public ResponseEntity<Object> releveActionnaire(@RequestBody @Valid HistoriqueActionnaireRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=releveActionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.releveActionnaire(request,response);
    }
    @PostMapping("/releveactionnaire/liste")
    public ResponseEntity<?> releveActionnaire(@RequestBody @Valid HistoriqueActionnaireRequest request){

        return service.releveActionnaire(request);
    }
    @GetMapping("/personnephysiquemorale")
    public ResponseEntity<?> afficherPersonnePhysiqueMorale(){

        return service.afficherPersonnePhysiqueMorale(null,null,null);
    }
    @GetMapping("/personnephysiquemoraleselontype/{type}")
    public ResponseEntity<?> afficherPersonnePhysiqueMoraleSelonType(@PathVariable String type){

        return service.afficherPersonnePhysiqueMoraleSelonType(null,null,null,type);
    }
    @GetMapping("/personnephysiquemoraleselontype/{type}/{valeur}")
    public ResponseEntity<?> rechercherPersonnePhysiqueMoraleSelonType(@PathVariable String type,
                                                                       @PathVariable String valeur){

        return service.rechercherPersonnePhysiqueMoraleSelonType(null,null,null,type,valeur);
    }
    @GetMapping("/personnephysiquemorale/{valeur}")
    public ResponseEntity<?> afficherPersonnePhysiqueMorale(@PathVariable String valeur){

        return service.afficherPersonnePhysiqueMorale(null,null,null,valeur);
    }
    @GetMapping("/etats/procedurecomptable")
    public ResponseEntity<Object> procedureComptable(HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=procedureComptable" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.procedureComptable(response);
    }
    @PostMapping("/opcvm/etats/relevetitrefcp")
    public ResponseEntity<Object> releveTitreFCP(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=releveTitreFCP" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.afficherReleveTitreFCP(request,response);
    }
    @PostMapping("/opcvm/relevetitrefcp")
    public ResponseEntity<?> releveTitreFCP(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.afficherReleveTitreFcp(request);
    }
    @PostMapping("/etatsuiviactionnaire/liste")
    public ResponseEntity<?> etatSuiviActionnaireListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.etatSuiviActionnaireListe(request);
    }
    @PostMapping("/etats/etatsuiviactionnaire")
    public ResponseEntity<Object> etatSuiviActionnaire(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=etatSuiviActionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.etatSuiviActionnaire(request,response);
    }
    @PostMapping("/etatsuiviactionnaire")
    public ResponseEntity<?> etatSuiviActionnaire(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.etatSuiviActionnaire(request);
    }
    @PostMapping("/etatfraisfonctionnement/liste")
    public ResponseEntity<?> etatFraisFonctionnementListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.etatFraisFonctionnementListe(request);
    }
    @PostMapping("/etats/etatfraisfonctionnement")
    public ResponseEntity<Object> etatFraisFonctionnement(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=etatFraisFonctionnement" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.etatFraisFonctionnement(request,response);
    }
    @PostMapping("/etatfraisfonctionnement")
    public ResponseEntity<?> etatFraisFonctionnement(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.etatFraisFonctionnement(request);
    }
    @PostMapping("/evolutionvl/liste")
    public ResponseEntity<?> evolutionvlListe(@RequestBody @Valid EvolutionVLRequest request) {
        return service.evolutionVLListe(request);
    }
    @PostMapping("/etats/evolutionvl")
    public ResponseEntity<Object> evolutionvl(@RequestBody @Valid EvolutionVLRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=evolutionVL" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.evolutionVL(request,response);
    }
    @PostMapping("/evolutionvl")
    public ResponseEntity<?> evolutionvl(@RequestBody @Valid EvolutionVLRequest request) {
        return service.evolutionVL(request);
    }
    @PostMapping("/pointsouscriptionrachat/liste")
    public ResponseEntity<?> pointSousriptionRachatListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.pointSouscriptionRachatListe(request);
    }
    @GetMapping("/soldecompteclient/{idActionnaire}/{idOpcvm}")
    public ResponseEntity<?> soldeCompteClient(@PathVariable  Long idActionnaire,
                                               @PathVariable Long idOpcvm) {
        return service.afficherSoldeCompteClient(idActionnaire, idOpcvm);
    }
    @PostMapping("/etats/pointsouscription")
    public ResponseEntity<Object> pointsouscription(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pointSousriptionParTypePersonne" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.pointSousriptionRachat(request,response);
    }
    @PostMapping("/etats/pointrachat")
    public ResponseEntity<Object> pointrachat(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pointRachatParTypePersonne" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.pointSousriptionRachat(request,response);
    }
    @PostMapping("/pointsouscriptionrachat")
    public ResponseEntity<?> pointsouscriptionrachat(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.pointSouscriptionRachat(request);
    }
    @PostMapping("/pointrepartitionportefeuille")
    public ResponseEntity<?> pointrepartitionportefeuille(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.pointRepartitionPortefeuille(request);
    }
    @PostMapping("/pointrepartitionportefeuille/liste")
    public ResponseEntity<?> pointrepartitionportefeuilleListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.pointRepartitionPortefeuilleListe(request);
    }
    @PostMapping("/etats/pointrepartitionportefeuille")
    public ResponseEntity<Object> pointrepartitionportefeuille(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pointRepartitionPortefeuille" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.pointRepartitionPortefeuille(request,response);
    }
    @PostMapping("/evolutionactifnet")
    public ResponseEntity<?> evolutionactifnet(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.evolutionActifNet(request);
    }
    @PostMapping("/evolutionactifnet/liste")
    public ResponseEntity<?> evolutionactifnetListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.evolutionActifNetListe(request);
    }
    @PostMapping("/etats/evolutionactifnet")
    public ResponseEntity<Object> evolutionactifnet(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pointRepartitionPortefeuille" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.evolutionActifNet(request,response);
    }
    @PostMapping("/etatsuiviclient/liste")
    public ResponseEntity<?> etatSuiviClientListe(@RequestBody @Valid HistoriqueActionnaireRequest request) {
        return service.suiviClient(request);
    }
    @PostMapping("/etats/etatsuiviclient")
    public ResponseEntity<Object> etatSuiviClient(@RequestBody @Valid HistoriqueActionnaireRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=SuiviClient" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.suiviClient(request,response);
    }
    @PostMapping("/etats/performanceportefeuilleactionnaire")
    public ResponseEntity<Object> performancePortefeuilleActionnaire(@RequestBody @Valid HistoriqueActionnaireRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=PerformancePortefeuilleActionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.perfomancePortefeuilleAcctionnaire(request,response);
    }
    @PostMapping("/opcvm/relevetitrefcp/liste")
    public ResponseEntity<?> releveTitreFCPListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.afficherReleveTitreFCPListe(request);
    }
    @Order(1)
    @PostMapping("/registre/actionnaire/opcvm")
    public ResponseEntity<?> registreActionnaire(@RequestBody RegistreActionnaireRequest request) {
        return service.registreActionnaire(request);
    }

    @PostMapping("/etats/registre/actionnaire/opcvm/xxxxxxx")
    public ResponseEntity<?> registreActionnaires(@RequestBody RegistreActionnaireRequest request) {
        return service.registreActionnaires(request);
    }

    @Order(2)
    @PostMapping("/cump/actionnaire/opcvm")
    public ResponseEntity<?> cumpActionnaire(@RequestBody CumpRequest request) {
        return service.cumpActionnaire(request);
    }

    @PostMapping("jasperpdf/export/registre/actionnaire/xxxxxxx/yyyy")
    public ResponseEntity<Object> registreActionnairePDF(
            HttpServletResponse response,
            @RequestBody RegistreActionPDFRequest request) {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=registre_actionnaire_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return service.registreActionnaireExportJasperReport(
            response,
            request
        );
    }
}
