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
    }@PostMapping("/pointsouscriptionrachat/liste")
    public ResponseEntity<?> pointSousriptionRachatListe(@RequestBody @Valid ReleveTitreFCPRequest request) {
        return service.pointSouscriptionRachatListe(request);
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
