package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dao.opcciel.DepotRachatDao;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.ImportationDepotDto;
import com.ged.dto.opcciel.comptabilite.VerifDepSouscriptionIntRachatDto;
import com.ged.dto.request.DownloadRequest;
import com.ged.dto.request.VerificationListeDepotRequest;
import com.ged.dto.titresciel.CoursTitreDto;
import com.ged.entity.opcciel.DepotRachat;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.mapper.opcciel.DepotRachatMapper;
import com.ged.projection.FT_DepotRachatProjection;
import com.ged.projection.NbrePartProjection;
import com.ged.reporting.services.PdfGeneratorService;
import com.ged.response.ResponseHandler;
import com.ged.projection.PrecalculRachatProjection;
import com.ged.service.opcciel.DepotRachatService;
import com.ged.service.opcciel.OpcvmService;
import com.ged.service.opcciel.SeanceOpcvmService;
import com.ged.validator.opcciel.DepotRachatValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/depotrachats")
public class DepotRachatController {
    @Autowired
    private TemplateEngine templateEngine;
    private final OpcvmService opcvmService;
    private final DepotRachatService depotRachatService;
    private final PdfGeneratorService pdfGeneratorService;
    private final SeanceOpcvmService seanceService;
    private final DepotRachatDao depotRachatDao;
    private final DepotRachatMapper depotRachatMapper;

    public DepotRachatController(OpcvmService opcvmService, DepotRachatService DepotRachatService, PdfGeneratorService pdfGeneratorService, SeanceOpcvmService seanceService, DepotRachatDao depotRachatDao, DepotRachatMapper depotRachatMapper) {
        this.opcvmService = opcvmService;
        this.depotRachatService = DepotRachatService;
        this.pdfGeneratorService = pdfGeneratorService;
        this.seanceService = seanceService;
        this.depotRachatDao = depotRachatDao;
        this.depotRachatMapper = depotRachatMapper;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return depotRachatService.afficherTous();
    }

    @PostMapping("/datatable/list/{idOpcvm}/{idSeance}")
    public ResponseEntity<Object> afficherTousLesDepots(@RequestBody DatatableParameters params,
                                                        @PathVariable("idOpcvm") Long idOpcvm,
                                                        @PathVariable("idSeance") Long idSeance)
    {
        return depotRachatService.afficherTousLesDepots(params, idOpcvm, idSeance);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return depotRachatService.afficher(id);
    }
    @GetMapping("/{idOpcvm}/{idActionnaire}")
    public List<NbrePartProjection> afficherNbrePart(@PathVariable Long idOpcvm,
                                                     @PathVariable Long idActionnaire) {
        return depotRachatService.afficherNbrePart(idOpcvm,idActionnaire);
    }
    @GetMapping("/{idOpcvm}/{idSeance}/{codeNatureOperation}/{estVerifier}/{estVerifie1}/{estVerifie2}")
    public ResponseEntity<Object> afficherTous( @PathVariable long idOpcvm,
                                                @PathVariable long idSeance,
                                                @PathVariable String codeNatureOperation,
                                                @PathVariable boolean estVerifier,
                                                @PathVariable boolean estVerifie1,
                                                @PathVariable boolean estVerifie2) throws JsonProcessingException {
        return depotRachatService.afficherTous(idOpcvm,idSeance,codeNatureOperation,estVerifier,estVerifie1,estVerifie2);
    }
    @GetMapping("depotrachat/{idOpcvm}/{niveau1}/{niveau2}")
    public List<FT_DepotRachatProjection> afficherFT_DepotRachat(
                                                           @PathVariable Long idOpcvm,
                                                           @PathVariable boolean niveau1,
                                                           @PathVariable boolean niveau2) {
        return depotRachatService.afficherFT_DepotRachat(idOpcvm,niveau1,niveau2);
    }
    @GetMapping("precalculrachat/{idSeance}/{idOpcvm}/{idPersonne}")
    public List<PrecalculRachatProjection> afficherPrecalculRachat(
                                                           @PathVariable Long idSeance,
                                                           @PathVariable Long idOpcvm,
                                                           @PathVariable Long idPersonne) {
        return depotRachatService.afficherPrecalculRachat(idSeance,idOpcvm,idPersonne);
    }
    @PostMapping("/datatable/list/{idOpcvm}/{idSeance}/{codeNatureOperation}")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable long idOpcvm,
                                                @PathVariable long idSeance,
                                                @PathVariable String codeNatureOperation) throws JsonProcessingException {
        return depotRachatService.afficherTous(params,idOpcvm,idSeance,codeNatureOperation);
    }

    @PostMapping
    public ResponseEntity<Object> ajouter(@Valid @RequestBody DepotRachatDto DepotRachatDto)
    {
        return depotRachatService.creer(DepotRachatDto);
    }

    @PostMapping("/{type}")
    public ResponseEntity<Object> creer(
            @Valid @RequestBody DepotRachatDto depotRachatDto, @PathVariable("type") String type)
    {
        return depotRachatService.creer(depotRachatDto, type);
    }

    @PutMapping("/modifier/{type}/{id}")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody DepotRachatDto depotRachatDto,
            @PathVariable("type") String type,
            @PathVariable("id") Long id)
    {
        return depotRachatService.modifier(depotRachatDto, type, id);
    }

    @PostMapping("/creer")
    public ResponseEntity<Object> creer(@RequestBody VerifDepSouscriptionIntRachatDto verifDepSouscriptionIntRachatDto)
    {
        return depotRachatService.creer(verifDepSouscriptionIntRachatDto);
    }

    @PostMapping("/creer/{id}/{userLogin}")
    public ResponseEntity<Object> creer(@PathVariable Long[] id,
                                        @PathVariable String userLogin)
    {
        return depotRachatService.creer(id,userLogin);
    }

    @PostMapping("/importation/depot")
    public ResponseEntity<Object> importerDepot(@Valid @RequestBody ImportationDepotDto importationDepotDto) {
        System.out.println("Importation === " + importationDepotDto.toString());
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> modifier(@Valid @RequestBody DepotRachatDto DepotRachatDto,
                                           @PathVariable Long id)
    {
        DepotRachatDto.setIdDepotRachat(id);
        return depotRachatService.modifier(DepotRachatDto);
    }
    @PutMapping("/{id}/{userLogin}")
    public ResponseEntity<Object> modifier(@PathVariable Long[] id,
                                           @PathVariable String userLogin)
    {
        return depotRachatService.modifier(id,userLogin);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return depotRachatService.supprimer(id);
    }

    @PostMapping(value = {"/verification/liste/depots"})
    public ResponseEntity<Object> verificationListeDepotReporting(
            @RequestBody VerificationListeDepotRequest verificationListeDepotRequest) {
        return depotRachatService.listeDepotAVerifier(verificationListeDepotRequest);
    }

    @PostMapping("/download/liste/verification/depot")
    public ResponseEntity<Object> downloadListeVerifDepot(@RequestBody DownloadRequest downloadRequest) {
        Context context = new Context();
        Opcvm opcvm = opcvmService.afficherSelonId(downloadRequest.getIdOpcvm());
        SeanceOpcvm seanceOpcvm = seanceService.afficherSeanceEnCours(downloadRequest.getIdOpcvm());
        String templateName = "verifDepotsouscription";
        List<DepotRachatDto> depots;
        if(downloadRequest.getNiveau() == 0 || downloadRequest.getNiveau() == 1)
        {
            if(downloadRequest.getNiveau() == 1)
                templateName = "verifNiv1DepotSouscription";
            depots = depotRachatDao.listeVerifDepotSeance(downloadRequest.getIdOpcvm(), downloadRequest.getIdSeance())
                    .stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
        }
        else
        {
            depots = depotRachatDao.tousLesDepotsSouscription(
                downloadRequest.getIdOpcvm(),
                downloadRequest.getIdSeance(),
                null,
                    Boolean.TRUE,
                    Boolean.FALSE
            ).stream().map(depotRachatMapper::deDepotRachat).toList();
            templateName = "verifNiv2DepotSouscription";
        }
        String dateVerification1 = "";
        String userVerification1 = "";
        String dateVerification2 = "";
        String userVerification2 = "";
        if(depots.size() > 0) {
            DepotRachatDto depot = depots.get(0);
            if(depot != null) {
                if(depot.getDateVerification1() != null  && depot.getUserLoginVerificateur1() != null) {
                    dateVerification1 = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(depot.getDateVerification1());
                    userVerification1 = depots.get(0).getUserLoginVerificateur1();
                }
                if(depot.getDateVerification2() != null  && depot.getUserLoginVerificateur2() != null) {
                    dateVerification2 = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(depot.getDateVerification2());
                    userVerification2 = depot.getUserLoginVerificateur2();
                }
            }
        }
        BigDecimal totalDepot = depots.stream()
                .map(x -> x.getMontant().add(BigDecimal.valueOf(0)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal totalSouscrit = depots.stream()
                .map(x -> x.getMontantSouscrit().add(BigDecimal.valueOf(0)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        context.setVariables(Map.of(
                "depots", depots,
                "opcvm", opcvm,
                "seance", seanceOpcvm,
                "totalDepot", totalDepot,
                "totalSouscrit", totalSouscrit,
                "user", downloadRequest.getUser(),
                "dateVerification1", dateVerification1,
                "userVerification1", userVerification1,
                "dateVerification2", dateVerification2,
                "userVerification2", userVerification2
        ));
        context.setVariable("df", DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String html2Convert = templateEngine.process(templateName, context);
        String encoded = pdfGeneratorService.htmlToPdf(html2Convert);

        return ResponseHandler.generateResponse(
                "LISTE DE VERIFICATION DES SOUSCRIPTIONS",
                HttpStatus.OK,
                encoded
        );
    }

    @PostMapping("/confirmer/liste/verification/depots/tous")
    public ResponseEntity<Object> addAll(@Valid @RequestBody List<DepotRachatDto> depotRachatDtos) {
        return depotRachatService.confirmerListeVerifDepot(depotRachatDtos);
    }

    @PostMapping("/confirmer/liste/verification/niveau1/depots/tous")
    public ResponseEntity<Object> confirmationNiv2(@Valid @RequestBody List<DepotRachatDto> depotRachatDtos) {
        return depotRachatService.confirmerListeVerifNiv2Depot(depotRachatDtos);
    }
}
