package com.ged.controller.risque;

import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.risque.*;
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
    @PostMapping("/risque/correlation/{idOpcvm}")
    public List<CorrelationDto> afficherCorrelation(@PathVariable long idOpcvm,
                                                    @RequestBody BeginEndDateParameter beginEndDateParamet)
    {
        return risqueService.afficherCorrelation(idOpcvm,beginEndDateParamet);
    }
    @PostMapping("/risque/covariance/{idOpcvm}")
    public List<CovarianceDto> afficherCovariance(@PathVariable long idOpcvm,
                                                  @RequestBody BeginEndDateParameter beginEndDateParamet)
    {
        return risqueService.afficherCovariance(idOpcvm, beginEndDateParamet);
    }
    @PostMapping("/risque/beta/{idOpcvm}")
    public List<BetaDto> afficherBeta(@PathVariable long idOpcvm,
                                      @RequestBody BeginEndDateParameter beginEndDateParamet)
    {
        return risqueService.afficherBeta(idOpcvm, beginEndDateParamet);
    }
    @GetMapping("/risque/ratiotreynor/{idOpcvm}/{annee}/{rf}")
    public RatioTreynorDto afficherRatioTreynor(@PathVariable long idOpcvm,
                                                  @PathVariable String annee,
                                                  @PathVariable double rf)
    {
        return risqueService.afficherRatioTreynor(idOpcvm, annee, rf);
    }
}
