package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureDto;
import com.ged.service.opcciel.MiseEnAffectationService;
import com.ged.service.opcciel.ModeleEcritureService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/miseenaffectations")
public class MiseEnAffectationController {
    private final MiseEnAffectationService miseEnAffectationService;

    public MiseEnAffectationController(MiseEnAffectationService miseEnAffectationService) {
        this.miseEnAffectationService = miseEnAffectationService;
        ;
    }

    @GetMapping("/{idOpcvm}")
    public ResponseEntity<Object> verifierMiseAffectationEnAttente(@PathVariable Long idOpcvm)
    {
        return miseEnAffectationService.verifierMiseEnAffectationEnAttente(idOpcvm);
    }


}
