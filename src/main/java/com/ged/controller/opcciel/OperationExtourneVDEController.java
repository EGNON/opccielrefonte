package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.OperationExtourneVDEDto;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ExtourneVDERequest;
import com.ged.dto.request.SoldeToutCompteRequest;
import com.ged.entity.opcciel.CleOperationExtourneVDE;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.mapper.opcciel.SeanceOpcvmMapper;
import com.ged.projection.OperationExtourneVDEProjection;
import com.ged.service.opcciel.OperationExtourneVDEService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationextournevdes")
public class OperationExtourneVDEController {
    private final OperationExtourneVDEService operationExtourneVDEService;

    private final OpcvmDao opcvmDao;
    private final OpcvmMapper opcvmMapper;
    private final SeanceOpcvmDao seanceOpcvmDao;
    private final SeanceOpcvmMapper seanceOpcvmMapper;
    private final LibraryDao libraryDao;

    public OperationExtourneVDEController(OperationExtourneVDEService operationExtourneVDEService, OpcvmDao opcvmDao, OpcvmMapper opcvmMapper, SeanceOpcvmDao seanceOpcvmDao, SeanceOpcvmMapper seanceOpcvmMapper, LibraryDao libraryDao) {
        this.operationExtourneVDEService = operationExtourneVDEService;
        this.opcvmDao = opcvmDao;
        this.opcvmMapper = opcvmMapper;
        this.seanceOpcvmDao = seanceOpcvmDao;
        this.seanceOpcvmMapper = seanceOpcvmMapper;
        this.libraryDao = libraryDao;
    }
    @GetMapping("/jasperpdf/vde/{idSeance}/{idOpcvm}/{estVerifie}/{estVerifie1}/{estVerifie2}/{niveau}")
    public ResponseEntity<Object> ordreDeBourseApercu(@PathVariable Long idSeance,
                                                      @PathVariable Long idOpcvm,
                                                      @PathVariable Boolean estVerifie,
                                                      @PathVariable Boolean estVerifie1,
                                                      @PathVariable Boolean estVerifie2,
                                                      @PathVariable Long niveau,
                                                      HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=verificationExtourneVDE_Niveau"+niveau+"_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return operationExtourneVDEService.jaspertReportVDE(idSeance,idOpcvm,estVerifie,estVerifie1,estVerifie2,niveau,response);
    }
    @GetMapping("/excel/vde/{idSeance}/{idOpcvm}/{estVerifie}/{estVerifie1}/{estVerifie2}/{niveau}")
    public ResponseEntity<byte[]> excelVDE(@PathVariable Long idSeance,
                                                      @PathVariable Long idOpcvm,
                                                      @PathVariable Boolean estVerifie,
                                                      @PathVariable Boolean estVerifie1,
                                                      @PathVariable Boolean estVerifie2,
                                                      @PathVariable Long niveau,
                                                      HttpServletResponse response) throws JRException, IOException {
        List<OperationExtourneVDEProjection> list=libraryDao.operationExtourneVDE(idSeance, idOpcvm, estVerifie, estVerifie1, estVerifie2);
        InputStream reportStream = getClass().getResourceAsStream("/verificationVDEN1.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//        DateFormat dateFormatter2 = new SimpleDateFormat("dd/MM/yyyy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
//        String dateFormatee = date.format(formatter);
        String letterDate = dateFormatter.format(new Date());
        SeanceOpcvmDto seanceOpcvmDto=seanceOpcvmMapper.deSeanceOpcvm(seanceOpcvmDao.afficherSeance(idOpcvm,idSeance));
        parameters.put("letterDate", letterDate);
        parameters.put("niveau", niveau.toString());
        parameters.put("dateOuverture", seanceOpcvmDto.getDateOuverture().format(formatter).toString());
        parameters.put("dateFermeture", seanceOpcvmDto.getDateFermeture().format(formatter).toString());

        OpcvmDto opcvmDto=opcvmMapper.deOpcvm(opcvmDao.findById(idOpcvm).orElseThrow());
        parameters.put("VL", opcvmDto.getValeurLiquidativeActuelle().toString());
        parameters.put("designationOpcvm", opcvmDto.getDenominationOpcvm());

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
        headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=verificationExtourneVDE_Niveau"+niveau+"_" + currentDateTime +".xlsx");
//        byte[] excelBytes=operationExtourneVDEService.exportExcelVDE(idSeance,idOpcvm,estVerifie,estVerifie1,estVerifie2,niveau,response);
        headers.setContentLength(excelBytes.length);
        return new ResponseEntity<>(excelBytes, headers, HttpStatus.OK);
    }
    @PostMapping("/jasperpdf/vde/soldecompteextourne")
    public ResponseEntity<Object> soldeCompteExtourne(@RequestBody SoldeToutCompteRequest soldeToutCompteRequest,
                                                      HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=soldeCompteExtourne_Niveau_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
//        SeanceOpcvmDto seanceOpcvmDto=seanceOpcvmMapper.deSeanceOpcvm(seanceOpcvmDao.afficherSeanceEnCours(soldeToutCompteRequest.getIdOpcvm()));
        return operationExtourneVDEService.soldeCompteExtourne(soldeToutCompteRequest.getIdOpcvm(),
                soldeToutCompteRequest.getNumCompteComptable(),soldeToutCompteRequest.getDateEstimation(),response);
    }
    @GetMapping("tous/{idOpcvm}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOpcvm)
    {
        return operationExtourneVDEService.afficherTous(idOpcvm);
    }

    @GetMapping("/{idTitre}/{idOpcvm}/{idSeance}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long idTitre,
                                           @PathVariable Long idOpcvm,
                                           @PathVariable Long idSeance)
    {
        CleOperationExtourneVDE cleOperationExtourneVDE=new CleOperationExtourneVDE();
        cleOperationExtourneVDE.setIdOpcvm(idOpcvm);
        cleOperationExtourneVDE.setIdSeance(idSeance);
        cleOperationExtourneVDE.setIdTitre(idTitre);
        return operationExtourneVDEService.afficher(cleOperationExtourneVDE);
    }

    @GetMapping("/{idOpcvm}/{dateEstimation}/{typeEvenement}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherTitre(@PathVariable Long idOpcvm,
                                                @PathVariable LocalDateTime dateEstimation,
                                                @PathVariable String typeEvenement)
    {
        return operationExtourneVDEService.afficherTitre(idOpcvm, dateEstimation, typeEvenement);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody ConstatationChargeListeRequest params) throws JsonProcessingException {
        return operationExtourneVDEService.afficherTous(params);
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationExtourneVDEDto operationExtourneVDEDto)
    {
        return operationExtourneVDEService.creer(operationExtourneVDEDto);
    }
    @PutMapping("creerverif/{niveau}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ExtourneVDERequest extourneVDERequest,
                                        @PathVariable Long niveau)
    {
        if(niveau==1)
            return operationExtourneVDEService.creerNiveau1(extourneVDERequest);
        else
            return operationExtourneVDEService.creerNiveau2(extourneVDERequest);
    }
    @PutMapping()
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody OperationExtourneVDEDto operationExtourneVDEDto)
    {
//        operationExtourneVDEDto.setIdOperation(id);
        return operationExtourneVDEService.modifier(operationExtourneVDEDto);
    }

    @DeleteMapping("/{userLogin}/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String userLogin,
                                            @PathVariable Long id)
    {
        return operationExtourneVDEService.supprimer(userLogin,id);
    }
}
