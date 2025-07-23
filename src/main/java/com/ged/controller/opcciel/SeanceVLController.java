package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.entity.opcciel.CleSeanceOpcvm;
import com.ged.service.opcciel.SeanceOpcvmService;
import com.ged.service.opcciel.SeanceVLService;
import jakarta.annotation.Priority;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/seancevls")
public class SeanceVLController {
    private final SeanceVLService seanceVLService;

    public SeanceVLController( SeanceVLService seanceVLService) {
        this.seanceVLService = seanceVLService;
    }

    @GetMapping("/{idOpcvm}")
    public ResponseEntity<Object> afficherTous(@PathVariable Long idOpcvm)
    {
        return seanceVLService.afficherTous(idOpcvm);
    }
    @PostMapping("/{userLogin}/{idOpcvm}")
    public ResponseEntity<Object> creer(@PathVariable String userLogin
                                        ,@PathVariable Long idOpcvm)
    {
        return seanceVLService.cloturerSeance(userLogin,idOpcvm);
    }


}
