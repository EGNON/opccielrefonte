package com.ged.controller.standard.parametre;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.DetailProfilDto;
import com.ged.entity.standard.CleDetailProfil;
import com.ged.service.standard.DetailProfilService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/detailprofils")
public class DetailProfilController {
    private final DetailProfilService DetailProfilService;

    public DetailProfilController(DetailProfilService DetailProfilService) {
        this.DetailProfilService = DetailProfilService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return DetailProfilService.afficherTous();
    }
    @GetMapping("/{codeProfil}/{idOpcvm}/{borneInferieur}")
    //@PreAuthorize("hasAuthority('ROLE_DetailProfil')")
    public ResponseEntity<Object> afficher(@PathVariable String codeProfil,
                                           @PathVariable Long idOpcvm,
                                           @PathVariable BigDecimal borneInferieur) {
        CleDetailProfil idDetailProfil = new CleDetailProfil();
        idDetailProfil.setCodeProfil(codeProfil);
        idDetailProfil.setIdOpcvm(idOpcvm);
        idDetailProfil.setBorneInferieur(borneInferieur);
        return DetailProfilService.afficher(idDetailProfil);
    }
    @GetMapping("/{codeProfil}/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_DetailProfil')")
    public ResponseEntity<Object> afficher(@PathVariable String codeProfil,
                                           @PathVariable Long idOpcvm) {
        return DetailProfilService.afficher(codeProfil,idOpcvm);
    }
    @PostMapping("/datatable/list/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_DetailProfil')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) {
        return DetailProfilService.afficherTous(params,idOpcvm);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DetailProfil')")
    public ResponseEntity<Object> creer(@Valid @RequestBody DetailProfilDto DetailProfilDto)
    {
        return DetailProfilService.creer(DetailProfilDto);
    }

    @PutMapping("/{codeProfil}/{idOpcvm}/{borneInferieur}")
    //  @PreAuthorize("hasAuthority('ROLE_DetailProfil')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody DetailProfilDto DetailProfilDto,
            @PathVariable String codeProfil,
            @PathVariable Long idOpcvm,
            @PathVariable BigDecimal borneInferieur)
    {
        DetailProfilDto.setCodeProfil(codeProfil);
        DetailProfilDto.setBorneInferieur(borneInferieur);
        DetailProfilDto.getOpcvm().setIdOpcvm(idOpcvm);
        return DetailProfilService.modifier(DetailProfilDto);
    }

    @DeleteMapping("/{codeProfil}/{idOpcvm}/{borneInferieur}")
    public ResponseEntity<Object> supprimer(@PathVariable String codeProfil,
                                            @PathVariable Long idOpcvm,
                                            @PathVariable BigDecimal borneInferieur)
    {
        CleDetailProfil idDetailProfil=new CleDetailProfil();
        idDetailProfil.setIdOpcvm(idOpcvm);
        idDetailProfil.setCodeProfil(codeProfil);
        idDetailProfil.setBorneInferieur(borneInferieur);
        return DetailProfilService.supprimer(idDetailProfil);
    }
    @DeleteMapping("/{codeProfil}/{idOpcvm}")
    public ResponseEntity<Object> supprimer(@PathVariable String codeProfil,
                                            @PathVariable Long idOpcvm)
    {
        return DetailProfilService.supprimer(codeProfil,idOpcvm);
    }
}
