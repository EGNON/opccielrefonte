package com.ged.controller.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationRestitutionReliquatDto;
import com.ged.service.opcciel.OperationRestitutionReliquatService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/restitutionreliquats")
public class OperationRestitutionReliquatController {
    private final OperationRestitutionReliquatService restitutionReliquatService;

    public OperationRestitutionReliquatController(OperationRestitutionReliquatService restitutionReliquatService) {
        this.restitutionReliquatService = restitutionReliquatService;
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
}
