package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.ImportationDepotDto;
import com.ged.service.opcciel.DepotRachatService;
import com.ged.service.opcciel.NantissementService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/nantissements")
public class NantissementController {
    private final NantissementService nantissementService;

    public NantissementController(NantissementService nantissementService) {
        this.nantissementService = nantissementService;
    }

    @GetMapping("/{idOpcvm}/{idActionnaire}")
    public Object afficherPartNanti(@PathVariable Long idOpcvm,
                                           @PathVariable Long idActionnaire) {
        return nantissementService.afficherPartNanti(idOpcvm,idActionnaire);
    }
}
