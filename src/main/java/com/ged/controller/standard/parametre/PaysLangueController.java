package com.ged.controller.standard.parametre;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PaysLangueDto;
import com.ged.entity.standard.ClePaysLangue;
import com.ged.service.standard.PaysLangueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/payslangues")
public class PaysLangueController {
    private final PaysLangueService paysLangueService;

    public PaysLangueController(PaysLangueService PaysLangueService) {
        this.paysLangueService = PaysLangueService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return paysLangueService.afficherTous();
    }
    @GetMapping("/{idPays}/{idLangue}")
    //@PreAuthorize("hasAuthority('ROLE_PaysLangue')")
    public ResponseEntity<Object> afficher(@PathVariable Long idPays,
                                           @PathVariable Long idLangue)
    {
        ClePaysLangue idPaysLangue=new ClePaysLangue();
        idPaysLangue.setIdPays(idPays);
        idPaysLangue.setIdLangue(idLangue);
        return paysLangueService.afficher(idPaysLangue);
    }
    @GetMapping("/{idLangue}")
    //@PreAuthorize("hasAuthority('ROLE_PaysLangue')")
    public ResponseEntity<Object> afficherSelonLangue(@PathVariable Long idLangue)
    {
        return paysLangueService.afficherSelonLangue(idLangue);
    }
    @GetMapping("/pays/{idPays}")
    //@PreAuthorize("hasAuthority('ROLE_PaysLangue')")
    public ResponseEntity<Object> afficherSelonPays(@PathVariable Long idPays)
    {
        return paysLangueService.afficherSelonPays(idPays);
    }

    @PostMapping("/datatable/list")
    //@PreAuthorize("hasAuthority('ROLE_PaysLangue')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return paysLangueService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_PaysLangue')")
    public ResponseEntity<Object> creer(@Valid @RequestBody PaysLangueDto PaysLangueDto)
    {
        return paysLangueService.creer(PaysLangueDto);
    }

    @PutMapping("/{idPays}/{idLangue}")
  //  @PreAuthorize("hasAuthority('ROLE_PaysLangue')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody PaysLangueDto PaysLangueDto,
            @PathVariable Long idLangue,
            @PathVariable Long idPays)
    {
        PaysLangueDto.getPays().setIdPays(idPays);
        PaysLangueDto.getLangue().setIdLangue(idLangue);
        return paysLangueService.modifier(PaysLangueDto);
    }

    @DeleteMapping("/{idPays}/{idLangue}")
    public ResponseEntity<Object> supprimer(@PathVariable Long idLangue,
                                            @PathVariable Long idPays)
    {
        ClePaysLangue idPaysLangue=new ClePaysLangue();
        idPaysLangue.setIdLangue(idLangue);
        idPaysLangue.setIdPays(idPays);
        return paysLangueService.supprimer(idPaysLangue);
    }
    @DeleteMapping("/{idLangue}")
    public ResponseEntity<Object> supprimerSelonLangue(@PathVariable Long idLangue)
    {
        return paysLangueService.supprimerSelonLangue(idLangue);
    }
}
