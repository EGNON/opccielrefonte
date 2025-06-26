package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.opcciel.OperationDetachementDto;
import com.ged.dto.request.SousRachRequest;
import com.ged.service.opcciel.OperationDetachementService;
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
@RequestMapping("/operationdetachements")
public class OperationDetachementController {
    private final OperationDetachementService operationDetachementService;

    public OperationDetachementController(OperationDetachementService operationDetachementService) {
        this.operationDetachementService = operationDetachementService;
    }

    @GetMapping("tous/{idOpcvm}/{estPaye}/{typeEvenement}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOpcvm,
                                               @PathVariable boolean estPaye,
                                               @PathVariable String typeEvenement)
    {
        return operationDetachementService.afficherTous(idOpcvm,estPaye,typeEvenement);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return operationDetachementService.afficher(id);
    }

    @GetMapping("/{idOpcvm}/{dateEstimation}/{typeEvenement}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherTitre(@PathVariable Long idOpcvm,
                                                @PathVariable LocalDateTime dateEstimation,
                                                @PathVariable String typeEvenement)
    {
        return operationDetachementService.afficherTitre(idOpcvm, dateEstimation, typeEvenement);
    }

    @PostMapping("/datatable/list/{idOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) throws JsonProcessingException {
        return operationDetachementService.afficherTous(params,idOpcvm);
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationDetachementDto operationDetachementDto)
    {
        return operationDetachementService.creer(operationDetachementDto);
    }
    @PostMapping("valeurouqte")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> valeurOuQte(@RequestBody OperationDetachementDto operationDetachementDto)
    {
        return operationDetachementService.valeurOuQte(operationDetachementDto);
    }
    @PutMapping()
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody OperationDetachementDto operationDetachementDto)
    {
//        operationDetachementDto.setIdOperation(id);
        return operationDetachementService.modifier(operationDetachementDto);
    }

    @DeleteMapping("/{userLogin}/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String userLogin,
                                            @PathVariable Long id)
    {
        return operationDetachementService.supprimer(userLogin,id);
    }
}
