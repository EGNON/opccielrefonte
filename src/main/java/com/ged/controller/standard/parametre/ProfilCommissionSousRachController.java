package com.ged.controller.standard.parametre;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ProfilCommissionSousRachDto;
import com.ged.entity.standard.CleProfilCommissionSousRach;
import com.ged.service.standard.ProfilCommissionSousRachService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/profilcommissionsousrachs")
public class ProfilCommissionSousRachController {
    private final ProfilCommissionSousRachService ProfilCommissionSousRachService;

    public ProfilCommissionSousRachController(ProfilCommissionSousRachService ProfilCommissionSousRachService) {
        this.ProfilCommissionSousRachService = ProfilCommissionSousRachService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return ProfilCommissionSousRachService.afficherTous();
    }
    @GetMapping("/{codeProfil}/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_ProfilCommissionSousRach')")
    public ResponseEntity<Object> afficher(@PathVariable String codeProfil,
                                           @PathVariable Long idOpcvm) {
        CleProfilCommissionSousRach idProfilCommissionSousRach = new CleProfilCommissionSousRach();
        idProfilCommissionSousRach.setCodeProfil(codeProfil);
        idProfilCommissionSousRach.setIdOpcvm(idOpcvm);
        return ProfilCommissionSousRachService.afficher(idProfilCommissionSousRach);
    }
    @GetMapping("liste/{typeCommission}/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_ProfilCommissionSousRach')")
    public ResponseEntity<Object> afficherSelonTypeCommissionOpcvm(@PathVariable String typeCommission,
                                           @PathVariable Long idOpcvm) {
        return ProfilCommissionSousRachService.afficherSelonTypeCommissionOpcvm(
                typeCommission,idOpcvm);
    }
    @PostMapping("/datatable/list/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_ProfilCommissionSousRach')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) {
        return ProfilCommissionSousRachService.afficherTous(params,idOpcvm);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_ProfilCommissionSousRach')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ProfilCommissionSousRachDto ProfilCommissionSousRachDto)
    {
        return ProfilCommissionSousRachService.creer(ProfilCommissionSousRachDto);
    }

    @PutMapping("/{codeProfil}/{idOpcvm}")
    //  @PreAuthorize("hasAuthority('ROLE_ProfilCommissionSousRach')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ProfilCommissionSousRachDto ProfilCommissionSousRachDto,
            @PathVariable String codeProfil,
            @PathVariable Long idOpcvm)
    {
        ProfilCommissionSousRachDto.setCodeProfil(codeProfil);
        ProfilCommissionSousRachDto.getOpcvm().setIdOpcvm(idOpcvm);
        return ProfilCommissionSousRachService.modifier(ProfilCommissionSousRachDto);
    }

    @DeleteMapping("/{codeProfil}/{idOpcvm}")
    public ResponseEntity<Object> supprimer(@PathVariable String codeProfil,
                                            @PathVariable Long idOpcvm)
    {
        CleProfilCommissionSousRach idProfilCommissionSousRach=new CleProfilCommissionSousRach();
        idProfilCommissionSousRach.setIdOpcvm(idOpcvm);
        idProfilCommissionSousRach.setCodeProfil(codeProfil);
        return ProfilCommissionSousRachService.supprimer(idProfilCommissionSousRach);
    }
}
