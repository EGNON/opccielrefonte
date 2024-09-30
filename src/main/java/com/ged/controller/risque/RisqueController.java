package com.ged.controller.risque;

import com.ged.dto.risque.AlphaDto;
import com.ged.dto.risque.RatioSharpDto;
import com.ged.service.risque.RisqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/reportings")
public class RisqueController {
    private final RisqueService risqueService;

    public RisqueController(RisqueService risqueService) {
        this.risqueService = risqueService;
    }

    @GetMapping("/risque/alpha/{idOpcvm}/{anneeDebut}/{anneeFin}")
    public List<AlphaDto> afficherAlpha(@PathVariable long idOpcvm,
                                        @PathVariable String anneeDebut,
                                        @PathVariable String anneeFin)
    {
        return risqueService.afficherAlpha(idOpcvm, anneeDebut, anneeFin);
    }
    @GetMapping("/risque/ratiosharp/{idOpcvm}/{anneeDebut}/{anneeFin}")
    public List<RatioSharpDto> afficherRatioSharp(@PathVariable long idOpcvm,
                                                  @PathVariable String anneeDebut,
                                                  @PathVariable String anneeFin)
    {
        return risqueService.afficherRatioSharp(idOpcvm, anneeDebut, anneeFin);
    }
}
