package com.ged.controller;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.dto.request.*;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.projection.GrandLivreProjection;
import com.ged.projection.OperationExtourneVDEProjection;
import com.ged.service.AppService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("libraries")
public class LibraryController {
    private final LibraryDao libraryDao;
    private final AppService service;
    private final OpcvmMapper opcvmMapper;
    private final OpcvmDao opcvmDao;
    public LibraryController(LibraryDao libraryDao, AppService service, OpcvmMapper opcvmMapper, OpcvmDao opcvmDao) {
        this.libraryDao = libraryDao;
        this.service = service;
        this.opcvmMapper = opcvmMapper;
        this.opcvmDao = opcvmDao;
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
    public void porteFeuille(@RequestBody @Valid DifferenceEstimationRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=portefeuille" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherPortefeuille(request,response);
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
    public void relevepartfcp(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Releve de part FCP" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherReleveDePartFCP(request,response);
    }
    @PostMapping("/opcvm/relevepartfcp")
    public ResponseEntity<?> relevepartfcp(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.afficherReleveDePartFCP(request);
    }
    @PostMapping("/opcvm/relevepartfcp/liste")
    public ResponseEntity<?> relevepartfcpListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.afficherReleveDePartFCPListe(request);
    }
    //portefeuille actionnaire
    @PostMapping("/etats/portefeuilleactionnaire")
    public void portefeuilleActionnaireF1(@RequestBody @Valid PortefeuilleActionnaireRequest request, HttpServletResponse response) throws JRException, IOException, SQLException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Portefeuille actionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.portefeuilleActionnaire(request,response);
    }
    @PostMapping("/portefeuilleactionnaire/liste")
    public ResponseEntity<?> portefeuilleactionnaireF1(@RequestBody @Valid PortefeuilleActionnaireRequest request) {
        return service.portefeuilleActionnaire(request);
    }

    //relevePartActionnaire
    @PostMapping("/opcvm/etats/relevepartactionnaire")
    public void relevepartactionnaire(@RequestBody @Valid ReleveDePartActionnaireRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Releve de part Actionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherReleveDePartActionnaire(request,response);
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
    public void journal(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Journal" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherJournal(request,response);
    }

    //balance avant inventaire
    @PostMapping("/opcvm/etats/balanceavantinventaire")
    public void balanceAvantInventaire(@RequestBody @Valid BalanceRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Balance" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherBalanceAvantInventaire(request,response);
    }
    @PostMapping("/opcvm/balanceavantinventaire")
    public ResponseEntity<?> balanceAvantInventaire(@RequestBody @Valid BalanceRequest request) {
        return service.afficherBalanceAvantInventaire(request);
    }
    @PostMapping("/opcvm/balanceavantinventaire/liste")
    public ResponseEntity<?> balanceAvantInventaireListe(@RequestBody @Valid BalanceRequest request) {
        return service.afficherBalanceAvantInventaireListe(request);
    }
    //balance
    @PostMapping("/opcvm/etats/balance")
    public void balance(@RequestBody @Valid BalanceRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Balance" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherBalance(request,response);
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
    public void grandlivre(@RequestBody @Valid GrandLivreRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Grand Livre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherGrandLivre(request,response);
    }
    @PostMapping("/opcvm/etats/excel/grandlivre")
    public ResponseEntity<byte[]> excelVDE(@RequestBody @Valid GrandLivreRequest request,
                                           HttpServletResponse response) throws JRException, IOException {
        List<GrandLivreProjection> grandLivreProjections = libraryDao.grandLivre(
                request.getIdOpcvm(), request.getCodePlan(), request.getNumCompteComptable(), request.getCodeAnalytique(), request.getTypeAnalytique(), request.getDateDebut(), request.getDateFin());
        InputStream reportStream = getClass().getResourceAsStream("/Grand_Livre.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(grandLivreProjections);
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        DateFormat dateFormatter2 = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String dateFormatee = date.format(formatter);
        String letterDate = dateFormatter.format(new Date());
        //SeanceOpcvmDto seanceOpcvmDto=seanceOpcvmMapper.deSeanceOpcvm(seanceOpcvmDao.afficherSeance(idOpcvm,idSeance));
        parameters.put("letterDate", letterDate);
        OpcvmDto opcvm = opcvmMapper.deOpcvm(opcvmDao.findById(request.getIdOpcvm()).orElseThrow());
        parameters.put("description", opcvm.getDenominationOpcvm());

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        ByteArrayOutputStream out = new ByteArrayOutputStream();

        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));

        SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
        configuration.setOnePagePerSheet(false);  // toutes les données sur une seule feuille
        configuration.setDetectCellType(true);   // détecte automatiquement les types de données
        configuration.setCollapseRowSpan(false);

        exporter.setConfiguration(configuration);
        exporter.exportReport();

        // Récupération en bytes
        byte[] excelBytes = out.toByteArray();

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        DateFormat dateFormatter2 = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter2.format(new Date());
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=grand_livre"+"_" + currentDateTime +".xlsx");
//        byte[] excelBytes=operationExtourneVDEService.exportExcelVDE(idSeance,idOpcvm,estVerifie,estVerifie1,estVerifie2,niveau,response);
        headers.setContentLength(excelBytes.length);
        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
    }
    @PostMapping("/opcvm/grandlivre")
    public ResponseEntity<?> grandlivre(@RequestBody @Valid GrandLivreRequest request) {
        return service.afficherGrandLivre(request);
    }
    @PostMapping("/opcvm/grandlivre/liste")
    public ResponseEntity<?> grandlivreListe(@RequestBody @Valid GrandLivreRequest request) {
        return service.afficherGrandLivreListe(request);
    }

    //soldedescomptescomptable
    @PostMapping("/opcvm/etats/soldedescomptescomptables")
    public void soldedescomptescomptables(@RequestBody @Valid SoldeDesComptesComptablesRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Solde des comptes comptables" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherSoldeDesComptesComptables(request,response);
    }
    @PostMapping("/opcvm/soldedescomptescomptables")
    public ResponseEntity<?> soldedescomptescomptables(@RequestBody @Valid SoldeDesComptesComptablesRequest request) {
        return service.afficherSoldeDesComptesComptables(request);
    }
    @PostMapping("/opcvm/soldedescomptescomptables/liste")
    public ResponseEntity<?> soldedescomptescomptablesListe(@RequestBody @Valid SoldeDesComptesComptablesRequest request) {
        return service.afficherSoldeDesComptesComptablesListe(request);
    }

    //pointsouscriptiondetaille
    @PostMapping("/opcvm/etats/pointsouscriptiondetaille")
    public void pointsouscriptiondetaille(@RequestBody @Valid PointSouscriptionDetailleRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Point des souscriptions detaillees" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherPointSouscriptionDetaille(request,response);
    }
    @PostMapping("/opcvm/pointsouscriptiondetaille")
    public ResponseEntity<?> pointsouscriptiondetaille(@RequestBody @Valid PointSouscriptionDetailleRequest request) {
        return service.afficherPointSouscriptionDetaille(request);
    }
    @PostMapping("/opcvm/pointsouscriptiondetaille/liste")
    public ResponseEntity<?> pointsouscriptiondetailleListe(@RequestBody @Valid PointSouscriptionDetailleRequest request) {
        return service.afficherPointSouscriptionDetailleListe(request);
    }

    //pointsouscriptionglobal
    @PostMapping("/opcvm/etats/pointsouscriptionglobal")
    public void pointsouscriptionglobal(@RequestBody @Valid PointSouscriptionGlobalRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Point des souscriptions global" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherPointSouscriptionGlobal(request,response);
    }
    @PostMapping("/opcvm/pointsouscriptionglobal")
    public ResponseEntity<?> pointsouscriptionglobal(@RequestBody @Valid PointSouscriptionGlobalRequest request) {
        return service.afficherPointSouscriptionGlobal(request);
    }
    @PostMapping("/opcvm/pointsouscriptionglobal/liste")
    public ResponseEntity<?> pointsouscriptionglobalListe(@RequestBody @Valid PointSouscriptionGlobalRequest request) {
        return service.afficherPointSouscriptionGlobalListe(request);
    }

    //pointrachatglobal
    @PostMapping("/opcvm/etats/pointrachatglobal")
    public void pointrachatglobal(@RequestBody @Valid PointRachatGlobalRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Point des rachats global" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherPointRachatGlobal(request,response);
    }
    @PostMapping("/opcvm/pointrachatglobal")
    public ResponseEntity<?> pointrachatglobal(@RequestBody @Valid PointRachatGlobalRequest request) {
        return service.afficherPointRachatGlobal(request);
    }
    @PostMapping("/opcvm/pointrachatglobal/liste")
    public ResponseEntity<?> pointrachatglobalListe(@RequestBody @Valid PointRachatGlobalRequest request) {
        return service.afficherPointRachatGlobalListe(request);
    }

    //pointrachatdetaille
    @PostMapping("/opcvm/etats/pointrachatdetaille")
    public void pointrachatdetaille(@RequestBody @Valid PointRachatDetailleRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Point des rachats detaille" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherPointRachatDetaille(request,response);
    }
    @PostMapping("/opcvm/pointrachatdetaille")
    public ResponseEntity<?> pointrachatdetaille(@RequestBody @Valid PointRachatDetailleRequest request) {
        return service.afficherPointRachatDetaille(request);
    }
    @PostMapping("/opcvm/pointrachatdetaille/liste")
    public ResponseEntity<?> pointrachatdetailleListe(@RequestBody @Valid PointRachatDetailleRequest request) {
        return service.afficherPointRachatDetailleListe(request);
    }

    //etatfinancierannuelf1bilan
    @PostMapping("/opcvm/etats/etatfinancierannuelf1bilan")
    public void etatfinancierannuelf1bilan(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 bilan" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF1Bilan(request,response);
    }

    //etatfinancierannuelf1resultat
    @PostMapping("/opcvm/etats/etatfinancierannuelf1resultat")
    public void etatfinancierannuelf1resultat(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 resultat" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF1Resultat(request,response);
    }

    //etatfinancierannuelf1etatvariationactifnet
    @PostMapping("/opcvm/etats/etatfinancierannuelf1etatvariationactifnet")
    public void etatfinancierannuelf1etatvariationactifnet(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 resultat" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        service.afficherEtatFinancierAnnuelF1EtatVariationActifNet(request,response);
    }

    //etatfinancierannuelf1notesrevenusportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinancierannuelf1notesrevenusportefeuilletitre")
    public void etatfinancierannuelf1notesrevenusportefeuilletitre(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 note sur les revenus portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF1NotesRevenusPortefeuilleTitre(request,response);
    }

    //etatfinancierannuelf1notesrevenusplacementsmonetaires
    @PostMapping("/opcvm/etats/etatfinancierannuelf1notesrevenusplacementsmonetaires")
    public void etatfinancierannuelf1notesrevenusplacementsmonetaires(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 note sur les revenus placements monetaires" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF1NotesRevenusPlacementsMonetaires(request,response);
    }

    //etatfinancierannuelf1notessommesdistribuables
    @PostMapping("/opcvm/etats/etatfinancierannuelf1notessommesdistribuables")
    public void etatfinancierannuelf1notessommesdistribuables(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 note sur les sommes distribuables" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF1NotesSommesDistribuables(request,response);
    }

    //etatfinancierannuelf1donneesactionratiospertinents
    @PostMapping("/opcvm/etats/etatfinancierannuelf1donneesactionratiospertinents")
    public void etatfinancierannuelf1donneesactionratiospertinents(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 donnees par action et ratios pertinents" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF1DonneesActionRatiosPertinents(request,response);
    }

    //etatfinancierannuelf1engagementhorsbilan
    @PostMapping("/opcvm/etats/etatfinancierannuelf1engagementhorsbilan")
    public void etatfinancierannuelf1engagementhorsbilan(@RequestBody @Valid EtatFinancierAnnuelF1EngagementHorsBilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format1 engagement hors bilan" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF1EngagementHorsBilan(request,response);
    }

    //etatfinancierannuelf2bilan
    @PostMapping("/opcvm/etats/etatfinancierannuelf2bilan")
    public void etatfinancierannuelf2bilan(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 bilan" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF2Bilan(request,response);
    }

    //etatfinancierannuelf2resultat
    @PostMapping("/opcvm/etats/etatfinancierannuelf2resultat")
    public void etatfinancierannuelf2resultat(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 resultat" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF2Resultat(request,response);
    }

    //etatfinancierannuelf2etatvariationactifnet
    @PostMapping("/opcvm/etats/etatfinancierannuelf2etatvariationactifnet")
    public void etatfinancierannuelf2etatvariationactifnet(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 etat de variation actif net" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF2EtatVariationActifNet(request,response);
    }

    //etatfinancierannuelf2notesrevenusportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinancierannuelf2notesrevenusportefeuilletitre")
    public void etatfinancierannuelf2notesrevenusportefeuilletitre(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 note sur les revenus portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF2NotesRevenusPortefeuilleTitre(request,response);
    }

    //etatfinancierannuelf2notesrevenusplacementsmonetaires
    @PostMapping("/opcvm/etats/etatfinancierannuelf2notesrevenusplacementsmonetaires")
    public void etatfinancierannuelf2notesrevenusplacementsmonetaires(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 note sur les revenus placements monetaires" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF2NotesRevenusPlacementsMonetaires(request,response);
    }

    //etatfinancierannuelf2notessommesdistribuables
    @PostMapping("/opcvm/etats/etatfinancierannuelf2notessommesdistribuables")
    public void etatfinancierannuelf2notessommesdistribuables(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 note sur les sommes distribuables" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF2NotesSommesDistribuables(request,response);
    }

    //etatfinancierannuelf2donneesactionratiospertinents
    @PostMapping("/opcvm/etats/etatfinancierannuelf2donneesactionratiospertinents")
    public void etatfinancierannuelf2donneesactionratiospertinents(@RequestBody @Valid EtatFinancierAnnuelF1BilanRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annuel format2 donnees par action et ratios pertinents" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnuelF2DonneesActionRatiosPertinents(request,response);
    }

    //afficherEtatFinancierAnnexesNotesEtatsFinanaciers
    @PostMapping("/opcvm/etats/etatfinancierannexesnotesetatsfinanciers")
    public void afficherEtatFinancierAnnexesNotesEtatsFinanaciers(@RequestBody @Valid EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes etats des entrees en portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnexesNotesEtatsFinanaciers(request,response);
    }
    //etatfinancierannexesetatsentreesportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinancierannexesetatsentreesportefeuilletitre")
    public void etatfinancierannexesetatsentreesportefeuilletitre(@RequestBody @Valid EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes etats des entrees en portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnexesEtatsEntreesPortefeuilleTitre(request,response);
    }

    //etatfinancierannexesetatsortiesportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinancierannexesetatsortiesportefeuilletitre")
    public void etatfinancierannexesetatsortiesportefeuilletitre(@RequestBody @Valid EtatFinancierAnnexesEtatsEntreesPortefeuilleTitreRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes etats des sorties en portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnexesEtatSortiesPortefeuilleTitre(request,response);
    }

    //etatfinancierannexesnoteportefeuilletitresannuel
    @PostMapping("/opcvm/etats/etatfinancierannexesnoteportefeuilletitresannuel")
    public void etatfinancierannexesnoteportefeuilletitresannuel(@RequestBody @Valid EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes note sur le portefeuille titre annuel" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnexesNotePortefeuilleTitresAnnuel(request,response);
    }

    //etatfinancierannexesnoteplacementsmonetairesannuel
    @PostMapping("/opcvm/etats/etatfinancierannexesnoteplacementsmonetairesannuel")
    public void etatfinancierannexesnoteplacementsmonetairesannuel(@RequestBody @Valid EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes note sur les placements monetaires annuel" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnexesNotePlacementsMonetairesAnnuel(request,response);
    }

    //etatfinancierannexesnotesurlecapital
    @PostMapping("/opcvm/etats/etatfinancierannexesnotesurlecapital")
    public void etatfinancierannexesnotesurlecapital(@RequestBody @Valid EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes note sur le capital" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnexesNotesurleCapital(request,response);
    }

    //etatfinancierannexesactionadmisecote
    @PostMapping("/opcvm/etats/etatfinancierannexesactionadmisecote")
    public void etatfinancierannexesactionadmisecote(@RequestBody @Valid EtatFinancierAnnexesNotePortefeuilleTitresAnnuelRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes action admise cote" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnexesActionAdmiseCote(request,response);
    }

    //etatfinancierannexesremunerationgestionnairedepositaire
    @PostMapping("/opcvm/etats/etatfinancierannexesremunerationgestionnairedepositaire")
    public void etatfinancierannexesremunerationgestionnairedepositaire(@RequestBody @Valid EtatFinancierAnnexesRemunerationGestionnaireDepositaireRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier annexes remuneration gestionnaire depositaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierAnnexesRemunerationGestionnaireDepositaire(request,response);
    }

    //etatfinanciertrimestrielbilantrimestriel
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielbilantrimestriel")
    public void etatfinanciertrimestrielbilantrimestriel(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel bilan trimestriel" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielBilanTrimestriel(request,response);
    }

    //etatfinanciertrimestrielcompteresultat
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielcompteresultat")
    public void etatfinanciertrimestrielcompteresultat(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel compte resultat" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielCompteResultat(request,response);
    }

    //etatfinanciertrimestrielvariationactifnet
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielvariationactifnet")
    public void etatfinanciertrimestrielvariationactifnet(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel variation de l'actif net" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielVariationActifNet(request,response);
    }

    //etatfinanciertrimestrielnotesrevenusplacementsmonetaires
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnotesrevenusplacementsmonetaires")
    public void etatfinanciertrimestrielnotesrevenusplacementsmonetaires(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel notes sur les revenus placements monetaires" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielNotesRevenusPlacementsMonetaires(request,response);
    }

    //etatfinanciertrimestrielnoterevenusportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnoterevenusportefeuilletitre")
    public void etatfinanciertrimestrielnoterevenusportefeuilletitre(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel notes sur les revenus portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielNoteRevenusPortefeuilleTitre(request,response);
    }

    //etatfinanciertrimestrieltableauanalysevl
    @PostMapping("/opcvm/etats/etatfinanciertrimestrieltableauanalysevl")
    public void etatfinanciertrimestrieltableauanalysevl(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel  tableau analyse VL" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielTableauAnalyseVL(request,response);
    }

    //etatfinanciertrimestrielnoteportefeuilletitre
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnoteportefeuilletitre")
    public void etatfinanciertrimestrielnoteportefeuilletitre(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel note sur le portefeuille titre" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielNotePortefeuilleTitre(request,response);
    }

    //etatfinanciertrimestrielnoteplacementsmonetaires
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnoteplacementsmonetaires")
    public void etatfinanciertrimestrielnoteplacementsmonetaires(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel note sur les placements monetaires" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielNotePlacementsMonetaires(request,response);
    }

    //etatfinanciertrimestrielactionsadmisescote
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielactionsadmisescote")
    public void etatfinanciertrimestrielactionsadmisescote(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel action admises cote" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielActionsAdmisesCote(request,response);
    }

    //etatfinanciertrimestrielnotecapital
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnotecapital")
    public void etatfinanciertrimestrielnotecapital(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel note sur le capital" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielNoteCapital(request,response);
    }


    //etatfinanciertrimestrielmontantfraisgestion
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielnoteetatsfinanciers")
    public void etatfinanciertrimestrielnotesetatsfinanciers(@RequestBody @Valid EtatFinancierTrimestrielMontantFraisGestionRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel note etats financiers" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        service.afficherEtatFinancierTrimestrielNoteAuxEtatsFinanciers(request,response);
    }
    //etatfinanciertrimestrielmontantfraisgestion
    @PostMapping("/opcvm/etats/etatfinanciertrimestrielmontantfraisgestion")
    public void etatfinanciertrimestrielmontantfraisgestion(@RequestBody @Valid EtatFinancierTrimestrielMontantFraisGestionRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel frais de gestion" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        service.afficherEtatFinancierTrimestrielMontantFraisGestion(request,response);
    }

    //documentseancelisteverificationcharge
    @PostMapping("/opcvm/etats/documentseancelisteverificationcharge")
    public void documentseancelisteverificationcharge(@RequestBody @Valid DocumentSeanceListeVerificationChargeRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Document seance liste de verification des charges" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        service.afficherDocumentSeanceListeVerificationCharge(request,response);
    }

    //documentseancelisteverificationecriturecharge
    @PostMapping("/opcvm/etats/documentseancelisteverificationecriturecharge")
    public void documentseancelisteverificationecriturecharge(@RequestBody @Valid DocumentSeanceListeVerificationEcritureChargeRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Document seance liste de verification des ecritures des charges" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        service.afficherDocumentSeanceListeVerificationEcritureCharge(request, response);
    }

    //documentseancelisteverificationecriturevde
    @PostMapping("/opcvm/etats/documentseancelisteverificationecriturevde")
    public void documentseancelisteverificationecriturevde(@RequestBody @Valid DocumentSeanceListeVerificationEcritureChargeRequest request, HttpServletResponse response) throws JRException, IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMYYYY:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=Document seance liste de verifications des ecritures de VDE"+currentDateTime+ ".pdf";
        response.setHeader(headerKey,headerValue);

        service.afficherDocumentSeanceListeVerificationEcritureVde(request, response);
    }

    //documentseancelisteverificationecriture
    @PostMapping("/opcvm/etats/documentseancelisteverificationecriture")
    public void documentseancelisteverificationecriture(@RequestBody @Valid DocumentSeanceListeVerificationEcritureChargeRequest request, HttpServletResponse response) throws JRException,IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=Document seance liste de verification des écritures"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        service.afficherDocumentSeanceListeVerificationEcriture(request,response);
    }

    //documentseancelisteverificationrachats
    @PostMapping("/opcvm/etats/documentseancelisteverificationrachats")
    public void documentseancelisteverificationrachats(@RequestBody @Valid DocumentSeanceListeVerificationRachatsRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=Document seance liste de verification des rachats";
        response.setHeader(headerKey,headerValue);

        service.afficherDocumentSeanceListeVerificationRachats(request,response);
    }

    //documentseancelisteverificationsouscription
    @PostMapping("/opcvm/etats/documentseancelisteverificationsouscription")
    public void documentseancelisteverificationsouscription(@RequestBody @Valid DocumentSeanceListeVerificationRachatsRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormattter = new SimpleDateFormat();
        String currentDateTime = dateFormattter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=Document seance liste de verification des souscriptions"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        service.afficherDocumentSeanceListeVerificationSouscription(request,response);
    }

    //documentseancelisteverificationvde
    @PostMapping("/opcvm/etats/documentseancelisteverificationvde")
    public void documentseancelisteverification(@RequestBody @Valid DocumentSeanceListeVerificationVdeRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=Document seance liste de verification des VDE"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        service.afficherDocumentSeanceListeVerificationVde(request,response);
    }

    //compositiondetailleactif
    @PostMapping("/opcvm/etats/compositiondetailleactif")
    public void compositiondetailleactif(@RequestBody @Valid CompositionDetailleActifRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd/MMMM/yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=Composition detaillee de l'Actif"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        service.afficherCompositionDetailleActif(request,response);
    }

    //pointactifnetpartvl
    @PostMapping("/opcvm/pointactifnetpartvl/liste")
    public ResponseEntity<?> pointactifnetpartvlListe(@RequestBody @Valid PointActifNetPartVlRequest request) {
        return service.afficherPointActifNetPartVlListe(request);
    }

    //pointremboursementeffectueperiode
    @PostMapping("/opcvm/etats/pointremboursementeffectueperiode")
    public void pointremboursementeffectueperiode (@RequestBody @Valid PointRemboursementEffectuePeriodeRequest request, HttpServletResponse response) throws JRException, IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement;filename=Point des remboursements effectues sur une periode"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        service.afficherPointRemboursementEffectuePeriode(request, response);
    }
    @PostMapping("/opcvm/pointremboursementeffectueperiode")
    public ResponseEntity<?> pointremboursementeffectueperiode(@RequestBody @Valid PointRemboursementEffectuePeriodeRequest request) {

       return service.afficherPointRemboursementEffectuePeriode(request);
    }
    @PostMapping("/opcvm/pointremboursementeffectueperiode/liste")
    public ResponseEntity<?> pointremboursementeffectueperiodeListe(@RequestBody @Valid PointRemboursementEffectuePeriodeRequest request) {

        return service.afficherPointRemboursementEffectuePeriodeListe(request);
    }


    //pointperiodiquetafa
    @PostMapping("/opcvm/etats/pointperiodiquetafa")
    public void pointperiodiquetafa(@RequestBody @Valid PointPeriodiqueTAFARequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=Point periodique de la TAFA"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        service.afficherPointPeriodiqueTAFA(request,response);
    }
    @PostMapping("/opcvm/pointperiodiquetafa")
    public ResponseEntity<?> pointperiodiquetafa(@RequestBody @Valid PointPeriodiqueTAFARequest request) {
        return service.afficherPointPeriodiqueTAFA(request);
    }
    @PostMapping("/opcvm/pointperiodiquetafa/liste")
    public ResponseEntity<?> pointperiodiquetafaListe(@RequestBody @Valid PointPeriodiqueTAFARequest request) {
        return service.afficherPointPeriodiqueTAFAListe(request);
    }

    //documentseancelisteverificationcodeposte
    @PostMapping("/opcvm/etats/documentseancelisteverificationcodeposte")
    public void documentseancelisteverificationcodeposte(@RequestBody @Valid DocumentSeanceListeVerificationCodePosteRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Document seance liste de verification des codes poste" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        service.afficherDocumentSeanceListeVerificationCodePoste(request,response);
    }

    //historiquevl
//    @PostMapping("/opcvm/historiquevl")
//    public ResponseEntity<?> afficherhistoriquevl(@RequestBody @Valid HistoriqueVLRequest request){
//        return service.afficherHistoriqueVL(request);
//    }
    @PostMapping("/opcvm/historiquevl/liste")
    public ResponseEntity<?> afficherhistoriquevlliste(@RequestBody @Valid HistoriqueVLRequest request){
        return service.afficherHistoriqueVLListe(request);
    }

    //portefeuilleactionnaireformat2
    @PostMapping("/etats/portefeuilleactionnaireformat2")
    public void afficherportefeuilleactionnaire (@RequestBody @Valid PortefeuilleActionnaireRequest request, HttpServletResponse response) throws JRException, IOException, SQLException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=Portefeuille actionnaire format 2"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        service.afficherPortefeuilleActionnaireF2(request,response);
    }
    @PostMapping("/portefeuilleactionnaireformat2/list")
    public ResponseEntity<?> afficherportefeuilleactionnaireformat2(@RequestBody @Valid PortefeuilleActionnaireRequest request) {
        return service.afficherPortefeuilleActionnaireF2(request);
    }

    //portefeuilleactionnairefinannee
    @PostMapping("/etats/portefeuilleactionnairefinannee")
    public void portefeuilleactionnairefinannee(@RequestBody @Valid PortefeuilleActionnaireRequest request, HttpServletResponse response) throws JRException,IOException {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachement; filename=Portefeuille actionnaire fin annee"+currentDateTime+".pdf";
        response.setHeader(headerKey,headerValue);

        service.afficherPortefeuilleActionnaireFinAnnee(request,response);
    }
    @PostMapping("/portefeuilleactionnairefinannee/list")
    public ResponseEntity<?> portefeuilleactionnairefinannee(@RequestBody @Valid PortefeuilleActionnaireRequest request) {
        return service.afficherPortefeuilleActionnaireFinAnnee(request);
    }

    //etatfinanciertrimestrieletatmensuelsouscriptions
    @PostMapping("/opcvm/etats/etatfinanciertrimestrieletatmensuelsouscriptions")
    public void etatfinanciertrimestrieletatmensuelsouscriptions(@RequestBody @Valid EtatFinancierTrimestrielBilanTrimestrielRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Etat financier trimestriel etat mensuel des souscriptions" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherEtatFinancierTrimestrielEtatMensuelSouscriptions(request,response);
    }

    //declarationcommissionactif
    @PostMapping("/opcvm/etats/declarationcommissionactif")
    public void declarationcommissionactif(@RequestBody @Valid DeclarationCommissionActifRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Declaration-Commission sur Actif" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherDeclarationCommissionActif(request,response);
    }
    @PostMapping("/opcvm/declarationcommissionactif")
    public ResponseEntity<?> declarationcommissionactif(@RequestBody @Valid DeclarationCommissionActifRequest request) {
        return service.afficherDeclarationCommissionActif(request);
    }
    @PostMapping("/opcvm/declarationcommissionactif/liste")
    public ResponseEntity<?> declarationcommissionactifListe(@RequestBody @Valid DeclarationCommissionActifRequest request) {
        return service.afficherDeclarationCommissionActifListe(request);
    }

    //pointinvestissement
    @PostMapping("/opcvm/etats/pointinvestissement")
    public void pointinvestissement(@RequestBody @Valid PointInvestissementRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Point des investissements / désinvestissements sur une période" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherPointInvestissement(request,response);
    }
    @PostMapping("/opcvm/pointinvestissement")
    public ResponseEntity<?> pointinvestissement(@RequestBody @Valid PointInvestissementRequest request) {
        return service.afficherPointInvestissement(request);
    }
    @PostMapping("/opcvm/pointinvestissement/liste")
    public ResponseEntity<?> pointinvestissementListe(@RequestBody @Valid PointInvestissementRequest request) {
        return service.afficherPointInvestissementListe(request);
    }

    //previsionnelremboursements
    @PostMapping("/opcvm/etats/previsionnelremboursements")
    public void previsionnelremboursements(@RequestBody @Valid PointInvestissementRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Previsionnel des Remboursements" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherPrevisionnelRemboursements(request,response);
    }
    @PostMapping("/opcvm/previsionnelremboursements")
    public ResponseEntity<?> previsionnelremboursements(@RequestBody @Valid PointInvestissementRequest request) {
        return service.afficherPrevisionnelRemboursements(request);
    }
    @PostMapping("/opcvm/previsionnelremboursements/liste")
    public ResponseEntity<?> previsionnelremboursementListe(@RequestBody @Valid PointInvestissementRequest request) {
        return service.afficherPrevisionnelRemboursementsListe(request);
    }

    //suiviecheancetitre
    @PostMapping("/opcvm/etats/suiviecheancetitre")
    public void suiviecheancetitre(@RequestBody @Valid PointInvestissementRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Suivi echeance des Titres" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherSuiviEcheanceTitre(request,response);
    }
    @PostMapping("/opcvm/suiviecheancetitre")
    public ResponseEntity<?> suiviecheancetitre(@RequestBody @Valid PointInvestissementRequest request) {
        return service.afficherSuiviEcheanceTitre(request);
    }
    @PostMapping("/opcvm/suiviecheancetitre/liste")
    public ResponseEntity<?> suiviecheancetitreListe(@RequestBody @Valid PointInvestissementRequest request) {
        return service.afficherSuiviEcheanceTitreListe(request);
    }
    //ClotureExercice
    @PostMapping("/opcvm/lignemvtclotureexercice")
    public ResponseEntity<?> ligneMvtClotureExercice(@RequestBody @Valid LigneMvtClotureExerciceRequest request) {
        return service.afficherLigneMvtClotureExercice(request);
    }
    @PostMapping("/opcvm/clotureexercice/{userLogin}")
    public ResponseEntity<?> clotureExercice(@RequestBody @Valid LigneMvtClotureExerciceRequest request,
                                             @PathVariable String userLogin) {
        return service.cloturerExercice(request,userLogin);
    }

    //avistransfertpart
    @PostMapping("/opcvm/etats/avistransfertpart")
    public void avistransfertpart(@RequestBody @Valid AvisTransfertPartRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Avis de transfert de parts" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherAvisTransfertPart(request,response);
    }
    @PostMapping("/opcvm/operationtransfertpart")
    public ResponseEntity<?> avistransfertpart(@RequestBody @Valid OperationTransfertDePartRequest request) {
        return service.afficherOperationTransfertPartListe(request);
    }
    @PostMapping("/opcvm/avistransfertpart/liste")
    public ResponseEntity<?> avistransfertpartListe(@RequestBody @Valid OperationTransfertDePartRequest request) {
        return service.afficherAvisTransfertPartListe(request);
    }

    @PostMapping("/etats/pointtresorerie")
    public void pointTresorerie(@RequestBody @Valid BeginEndDateParameter request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pointTresorerie" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.pointTresorerie(request,response);
    }
    @PostMapping("/etats/ficheclient")
    public void ficheClient(@RequestBody FicheClientRequest request,
                                              HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=fiche_client" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.ficheClientEtat(request,response);
    }
    @PostMapping("/etats/historiqueactionnaire")
    public void historiqueActionnaire(@RequestBody @Valid BeginEndDateParameter request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=historiqueActionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.historiqueActionnaire(request,response);
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
    public void releveActionnaire(@RequestBody @Valid HistoriqueActionnaireRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=releveActionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.releveActionnaire(request,response);
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
    public void procedureComptable(HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=procedureComptable" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.procedureComptable(response);
    }
    @PostMapping("/opcvm/etats/relevetitrefcp")
    public void releveTitreFCP(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=releveTitreFCP" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.afficherReleveTitreFCP(request,response);
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
    public void etatSuiviActionnaire(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=etatSuiviActionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.etatSuiviActionnaire(request,response);
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
    public void etatFraisFonctionnement(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=etatFraisFonctionnement" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.etatFraisFonctionnement(request,response);
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
    public void evolutionvl(@RequestBody @Valid EvolutionVLRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=evolutionVL" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.evolutionVL(request,response);
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
    public void pointsouscription(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pointSousriptionParTypePersonne" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.pointSousriptionRachat(request,response);
    }
    @PostMapping("/etats/pointrachat")
    public void pointrachat(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pointRachatParTypePersonne" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.pointSousriptionRachat(request,response);
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
    public void pointrepartitionportefeuille(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pointRepartitionPortefeuille" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.pointRepartitionPortefeuille(request,response);
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
    public void evolutionactifnet(@RequestBody @Valid ReleveTitreFCPRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pointRepartitionPortefeuille" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.evolutionActifNet(request,response);
    }
    @PostMapping("/etatsuiviclient/liste")
    public ResponseEntity<?> etatSuiviClientListe(@RequestBody @Valid HistoriqueActionnaireRequest request) {
        return service.suiviClient(request);
    }
    @PostMapping("/etats/etatsuiviclient")
    public void etatSuiviClient(@RequestBody @Valid HistoriqueActionnaireRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=SuiviClient" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.suiviClient(request,response);
    }
    @PostMapping("/etats/performanceportefeuilleactionnaire")
    public void performancePortefeuilleActionnaire(@RequestBody @Valid HistoriqueActionnaireRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=PerformancePortefeuilleActionnaire" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.perfomancePortefeuilleAcctionnaire(request,response);
    }
    @PostMapping("/opcvm/relevetitrefcp/liste")
    public ResponseEntity<?> releveTitreFCPListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.afficherReleveTitreFCPListe(request);
    }
    @PostMapping("/opcvm/portefeuilleactionnaire")
    public ResponseEntity<?> portefeuilleactionnaire(@RequestBody @Valid PortefeuilleActionnaireRequest request) {
        return service.portefeuilleActionnaire(request);
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
    public void registreActionnairePDF(
            HttpServletResponse response,
            @RequestBody RegistreActionPDFRequest request) {

        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=registre_actionnaire_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         service.registreActionnaireExportJasperReport(
            response,
            request
        );
    }
}
