package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto2;
import com.ged.dto.opcciel.PlanDto;
import com.ged.dto.request.SousRachRequest;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.service.opcciel.OperationSouscriptionRachatService;
import com.ged.service.opcciel.PlanService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationsouscriptionrachats")
public class OperationSouscriptionRachatController {
    private final OperationSouscriptionRachatService operationSouscriptionRachatService;

    public OperationSouscriptionRachatController(OperationSouscriptionRachatService operationSouscriptionRachatService) {
        this.operationSouscriptionRachatService = operationSouscriptionRachatService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return operationSouscriptionRachatService.afficherTous();
    }
    @GetMapping("/avisoperation/{idOperation}")
    public ResponseEntity<Object> avisOperation(@PathVariable String idOperation)
    {
        return operationSouscriptionRachatService.avisOperation(idOperation);
    }
    @GetMapping("/jasperpdf/avisoperation/{idOperation}")
    public void avisOperation(@PathVariable String idOperation,
                                                HttpServletResponse response)
    {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=avis_Rachat" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         operationSouscriptionRachatService.avisOperation(idOperation,response);
    }
    @GetMapping("/jasperpdf/avisoperation2/{idOperation}")
    public ResponseEntity<Object> avisOperation2(@PathVariable String idOperation) throws JRException, FileNotFoundException {
//        response.setContentType("application/pdf");
//        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
//        String currentDateTime = dateFormatter.format(new Date());
//
//        String headerKey = "Content-Disposition";
//        String headerValue = "attachment; filename=avis_Rachat" + currentDateTime + ".pdf";
//        response.setHeader(headerKey, headerValue);

        return operationSouscriptionRachatService.avisOperation2(idOperation);
    }
    @PostMapping("/{idOpcvm}/{codeNatureOperation}")
    public ResponseEntity<Object> listeOperationSouscriptionRachat(@PathVariable Long idOpcvm,
                                                                   @PathVariable String codeNatureOperation,
                                                                   @RequestBody BeginEndDateParameter beginEndDateParameter)
    {
        return operationSouscriptionRachatService.listeOperationSouscriptionRachat(idOpcvm,
                codeNatureOperation, beginEndDateParameter.getStartDate(),beginEndDateParameter.getEndDate());
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return operationSouscriptionRachatService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return operationSouscriptionRachatService.afficherTous(params);
    }

    @PostMapping("liste/opsousrach/datatable")
    public ResponseEntity<Object> listeOpSousRach(@RequestBody SousRachRequest sousRachRequest) {
        return operationSouscriptionRachatService.listeOpSouscriptionRachat(sousRachRequest);
    }

    @PostMapping("jasperpdf/export/avis/souscription")
    public void avisSouscriptionPDF(
            HttpServletResponse response,
            @RequestBody List<OperationSouscriptionRachatDto> operationSouscriptionRachatDtoList) {
        System.out.println(operationSouscriptionRachatDtoList.size());
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=avis_souscription_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

         operationSouscriptionRachatService.avisSouscriptionExportJasperReport(
                response,
                operationSouscriptionRachatDtoList);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationSouscriptionRachatDto operationSouscriptionRachatDto)
    {
        return operationSouscriptionRachatService.creer(operationSouscriptionRachatDto);
    }
    @PostMapping("/creer")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationSouscriptionRachatDto2[] operationSouscriptionRachatDto)
    {
        return operationSouscriptionRachatService.creer(operationSouscriptionRachatDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody OperationSouscriptionRachatDto operationSouscriptionRachatDto,
                                           @PathVariable Long id)
    {
        operationSouscriptionRachatDto.setIdOperation(id);
        return operationSouscriptionRachatService.modifier(operationSouscriptionRachatDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return operationSouscriptionRachatService.supprimer(id);
    }
}
