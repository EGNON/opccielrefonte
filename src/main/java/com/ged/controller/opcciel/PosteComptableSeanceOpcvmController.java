package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.PosteComptableSeanceOpcvmDto;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.entity.opcciel.comptabilite.ClePosteComptableSeanceOpcvm;
import com.ged.service.opcciel.PosteComptableSeanceOpcvmService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/postecomptableseanceopcvms")
public class PosteComptableSeanceOpcvmController {
    private final PosteComptableSeanceOpcvmService PosteComptableSeanceOpcvmService;

    public PosteComptableSeanceOpcvmController(PosteComptableSeanceOpcvmService PosteComptableSeanceOpcvmService) {

        this.PosteComptableSeanceOpcvmService = PosteComptableSeanceOpcvmService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return PosteComptableSeanceOpcvmService.afficherTous();
    }

     @GetMapping("/{codePosteComptableSeanceOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherSelonCodePosteComptableSeanceOpcvm(
                                           @PathVariable String codePosteComptableSeanceOpcvm)
    {
        return PosteComptableSeanceOpcvmService.afficherSelonCodePosteComptableSeanceOpcvm(codePosteComptableSeanceOpcvm);
    }

    @PostMapping("/liste")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DifferenceEstimationRequest params) {
        return PosteComptableSeanceOpcvmService.valoriser(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@RequestBody List<PosteComptableSeanceOpcvmDto> PosteComptableSeanceOpcvmDto)
    {
        return PosteComptableSeanceOpcvmService.creer(PosteComptableSeanceOpcvmDto);
    }

}
