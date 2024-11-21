package com.ged.controller.opcciel;

import com.ged.dto.opcciel.comptabilite.Operation2Dto;
import com.ged.service.lab.OperationService;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/operationsupcinqmillions/{annee}")
    public List<Operation2Dto> afficherOperationSupCinqMillions(@PathVariable long annee) {
        return operationService.afficherOperationSuperieurACinqMillions(annee);
    }
    @GetMapping("/nouvellerelation/{annee}")
    public List<Operation2Dto> afficherOperationConstituantDeNouvelleRelation(@PathVariable long annee) {
        return operationService.afficherOperationConstituantDeNouvelleRelation(annee);
    }
    @GetMapping("/transactioninhabituelle/{annee}")
    public List<Operation2Dto> afficherTransactionInhabituelle(@PathVariable long annee) {
        return operationService.afficherTransactionConditionInhabituel(annee);
    }
    @GetMapping("/transactionnormale/{annee}")
    public List<Operation2Dto> afficherTransactionNormale(@PathVariable long annee) {
        return operationService.afficherTransactionConditionNormale(annee);
    }
}
