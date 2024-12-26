package com.ged.controller.opcciel;

import com.ged.dao.opcciel.OperationRestitutionReliquatDao;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationRestitutionReliquatDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.mapper.opcciel.OperationRestitutionReliquatMapper;
import com.ged.reporting.services.PdfGeneratorService;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OpcvmService;
import com.ged.service.opcciel.OperationRestitutionReliquatService;
import com.ged.service.opcciel.SeanceOpcvmService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/restitutionreliquats")
public class OperationRestitutionReliquatController {
    @Autowired
    private TemplateEngine templateEngine;
    private final OpcvmService opcvmService;
    private final SeanceOpcvmService seanceService;
    private final OperationRestitutionReliquatService restitutionReliquatService;
    private final OperationRestitutionReliquatDao restitutionReliquatDao;
    private final OperationRestitutionReliquatMapper restitutionReliquatMapper;
    private final PdfGeneratorService pdfGeneratorService;

    public OperationRestitutionReliquatController(OpcvmService opcvmService, SeanceOpcvmService seanceService, OperationRestitutionReliquatService restitutionReliquatService, OperationRestitutionReliquatDao restitutionReliquatDao, OperationRestitutionReliquatMapper restitutionReliquatMapper, PdfGeneratorService pdfGeneratorService) {
        this.opcvmService = opcvmService;
        this.seanceService = seanceService;
        this.restitutionReliquatService = restitutionReliquatService;
        this.restitutionReliquatDao = restitutionReliquatDao;
        this.restitutionReliquatMapper = restitutionReliquatMapper;
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @PostMapping("liste/{idOpcvm}-{idSeance}")
    public ResponseEntity<Object> listeOpRestitutionReliquat(
            @Valid @RequestBody DatatableParameters params,
            @PathVariable("idOpcvm") Long idOpcvm,
            @PathVariable("idSeance") Long idSeance) {
        return restitutionReliquatService.listeOpRestitutionReliquat(params, idOpcvm, idSeance);
    }

    @PostMapping("precalcul/restitution/{idOpcvm}-{idSeance}")
    public ResponseEntity<Object> precalculOpRestitutionReliquat(
            @Valid @RequestBody DatatableParameters params,
            @PathVariable("idOpcvm") Long idOpcvm,
            @PathVariable("idSeance") Long idSeance) {
        return restitutionReliquatService.precalculRestitutionReliquat(params, idOpcvm, idSeance);
    }

    @PostMapping("enregistrer/restitution/reliquat")
    public ResponseEntity<Object> enregistrerOpRestitutionReliquat(@Valid @RequestBody List<OperationRestitutionReliquatDto> restitutionReliquatDtos) {
        return restitutionReliquatService.enregisterTous(restitutionReliquatDtos);
    }

    @PostMapping("verification/liste/paiement-{idOpcvm}/reliquat-{idSeance}")
    public ResponseEntity<Object> verificationListePaiementReliquat(
            @PathVariable("idOpcvm") Long idOpcvm, @PathVariable("idSeance") Long idSeance) {
        Context context = new Context();
        Opcvm opcvm = opcvmService.afficherSelonId(idOpcvm);
        SeanceOpcvm seanceOpcvm = seanceService.afficherSeanceEnCours(idOpcvm);
        String templateName = "listePaiementReliquat";
        List<OperationRestitutionReliquatDto> paiements = restitutionReliquatDao.listeOpRestitutionReliquat(
                idOpcvm,
                seanceOpcvm.getIdSeanceOpcvm().getIdSeance()
        ).stream().map(restitutionReliquatMapper::aEntite).toList();
        BigDecimal total = paiements.stream()
                .map(x -> x.getMontant().add(BigDecimal.valueOf(0)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        context.setVariables(Map.of(
        "paiements", paiements,
        "opcvm", opcvm,
        "seance", seanceOpcvm,
        "total", total
        ));
        context.setVariable("df", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String html2Convert = templateEngine.process(templateName, context);
        String encoded = pdfGeneratorService.htmlToPdf(html2Convert);
        return ResponseHandler.generateResponse(
                "LISTE DE PAIEMENTS DES RELIQUATS",
                HttpStatus.OK,
                encoded
        );
    }
}
