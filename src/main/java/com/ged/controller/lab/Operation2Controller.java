package com.ged.controller.lab;

import com.ged.dto.crm.RDVEtatDto;
import com.ged.dto.opcciel.comptabilite.Operation2Dto;
import com.ged.service.lab.OperationService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/operations")
public class Operation2Controller {
    private final OperationService operationService;

    public Operation2Controller(OperationService operationService) {

        this.operationService = operationService;
    }

    @GetMapping("/depotsurannee/{annee}")
    public List<Operation2Dto> afficherDepotRecenseSurAnnee(@PathVariable long annee) {
        return operationService.afficherDepotSurAnnee(annee);
    }
    @GetMapping("/operationsupdixmillions/{annee}")
    public List<Operation2Dto> afficherOperationSupDixMillions(@PathVariable long annee) {
        return operationService.afficherDepotSuperieurADixMillionsSurAnnee(annee);
    }
    @GetMapping("/operationsupdixmillionsetat/{annee}")
    public void afficherOperationSupDixMillionsEtat(@PathVariable long annee, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=opérations_sup_10_millions" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         operationService.afficherDepotSuperieurADixMillionsSurAnneeEtat(annee,response);
    }
    @GetMapping("/operationsupcinqmillions/{annee}")
    public List<Operation2Dto> afficherOperationSupCinqMillions(@PathVariable long annee) {
        return operationService.afficherOperationSuperieurACinqMillions(annee);
    }
    @GetMapping("/operationsupcinqmillionsetat/{annee}")
    public void afficherOpeSupCinqMillions(@PathVariable long annee, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=opérations_sup_5_millions" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        operationService.afficherOperationSuperieurACinqMillionsEtat(annee,response);
    }

    @GetMapping("/depotsuranneeetat/{annee}")
    public void afficherDepotSurAnnee(@PathVariable long annee, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=solde-detenu-par-un-meme-client" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        operationService.afficherDepotSurAnneeEtat(annee,response);
    }
    @GetMapping("/nouvellerelation/{annee}")
    public List<Operation2Dto> afficherOperationConstituantDeNouvelleRelation(@PathVariable long annee) {
        return operationService.afficherOperationConstituantDeNouvelleRelation(annee);
    }
    @GetMapping("/nouvellerelationetat/{annee}")
    public void afficherOperationConstituantDeNouvelleRelationEtat(@PathVariable long annee, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=opérations_sup_10_millions_constituants_nouvelle_relations" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        operationService.afficherOperationConstituantDeNouvelleRelationEtat(annee,response);
    }
    @GetMapping("/transactioninhabituelle/{annee}")
    public List<Operation2Dto> afficherTransactionInhabituelle(@PathVariable long annee) {
        return operationService.afficherTransactionConditionInhabituel(annee);
    }
    @GetMapping("/transactioninhabituelleetat/{annee}")
    public void afficherTransactionInhabituelleEtat(@PathVariable long annee, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Transactions_Inhabituelles_sup_10_millions" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        operationService.afficherTransactionConditionInhabituelEtat(annee,response);
    }
    @GetMapping("/transactionnormale/{annee}")
    public List<Operation2Dto> afficherTransactionNormale(@PathVariable long annee) {
        return operationService.afficherTransactionConditionNormale(annee);
    }
    @GetMapping("/transactionnormaleetat/{annee}")
    public void afficherTransactionNormaleEtat(@PathVariable long annee, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Transactions_Normales_sup_50_millions" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        operationService.afficherTransactionConditionNormaleEtat(annee,response);
    }
}
