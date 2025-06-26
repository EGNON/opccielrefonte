package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationEvenementSurValeurDto;
import com.ged.service.opcciel.OperationEvenementSurValeurService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/operationevenementsurvaleurs")
public class OperationEvenementSurValeurController {
    private final OperationEvenementSurValeurService operationEvenementSurValeurService;

    public OperationEvenementSurValeurController(OperationEvenementSurValeurService operationEvenementSurValeurService) {
        this.operationEvenementSurValeurService = operationEvenementSurValeurService;
    }

    @GetMapping("tous/{idOpcvm}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOpcvm)
    {
        return operationEvenementSurValeurService.afficherTous(idOpcvm);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return operationEvenementSurValeurService.afficher(id);
    }

    @GetMapping("/{idOpcvm}/{dateEstimation}/{typeEvenement}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherTitre(@PathVariable Long idOpcvm,
                                                @PathVariable LocalDateTime dateEstimation,
                                                @PathVariable String typeEvenement)
    {
        return operationEvenementSurValeurService.afficherTitre(idOpcvm, dateEstimation, typeEvenement);
    }

    @PostMapping("/datatable/list/{idOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) throws JsonProcessingException {
        return operationEvenementSurValeurService.afficherTous(params,idOpcvm);
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody OperationEvenementSurValeurDto[] operationEvenementSurValeurDto)
    {
        return operationEvenementSurValeurService.creer(operationEvenementSurValeurDto);
    }
    @PostMapping("valeurouqte")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> valeurOuQte(@RequestBody OperationEvenementSurValeurDto operationEvenementSurValeurDto)
    {
        return operationEvenementSurValeurService.valeurOuQte(operationEvenementSurValeurDto);
    }
    @PutMapping()
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody OperationEvenementSurValeurDto operationEvenementSurValeurDto)
    {
//        operationEvenementSurValeurDto.setIdOperation(id);
        return operationEvenementSurValeurService.modifier(operationEvenementSurValeurDto);
    }

    @DeleteMapping("/{userLogin}/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String userLogin,
                                            @PathVariable Long id)
    {
        return operationEvenementSurValeurService.supprimer(userLogin,id);
    }
}
