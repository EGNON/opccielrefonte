package com.ged.controller.standard.parametre;

import com.ged.dao.standard.TarificationOrdinaireDao;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.TarificationOrdinaireDto;
import com.ged.mapper.standard.TarificationOrdinaireMapper;
import com.ged.projection.TarificationProjection;
import com.ged.service.standard.TarificationOrdinaireService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tarificationordinaires")
public class TarificationOrdinaireController {
    private final TarificationOrdinaireService TarificationOrdinaireService;

    public TarificationOrdinaireController(TarificationOrdinaireService tarificationOrdinaireService) {
        this.TarificationOrdinaireService = tarificationOrdinaireService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return TarificationOrdinaireService.afficherTous();
    }
    @GetMapping("/{id}")
    //@PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> afficher(
                                           @PathVariable Long id) {
        return TarificationOrdinaireService.afficher(id);
    }
    @GetMapping("sgi/{id}")
    //@PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> afficherRegistraireSelonId(
                                           @PathVariable Long id) {
        return TarificationOrdinaireService.afficherRegistraireSelonId(id);
    }
    @GetMapping("depositaire/{id}")
    //@PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> afficherDeposiataireSelonId(
                                           @PathVariable Long id) {
        return TarificationOrdinaireService.afficherDepositaireSelonId(id);
    }
    @GetMapping("place/{id}")
    //@PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> afficherPlaceSelonId(
                                           @PathVariable Long id) {
        return TarificationOrdinaireService.afficherPlaceSelonId(id);
    }

    @PostMapping("/datatable/list/sgi/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> datatableList_SGI(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) {
        return TarificationOrdinaireService.afficherTousSGI(params,idOpcvm);
    }
    @PostMapping("/datatable/list/depositaire/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> datatableList_Depositaire(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) {
        return TarificationOrdinaireService.afficherTousDepositaire(params,idOpcvm);
    }
    @PostMapping("/datatable/list/place/{idOpcvm}")
    //@PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> datatableList_Place(@RequestBody DatatableParameters params,
                                                @PathVariable Long idOpcvm) {
        return TarificationOrdinaireService.afficherTousPlace(params,idOpcvm);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> creer(@Valid @RequestBody TarificationOrdinaireDto TarificationOrdinaireDto)
    {
        return TarificationOrdinaireService.creer(TarificationOrdinaireDto);
    }

    @PutMapping("/sgi/{id}")
    //  @PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody TarificationOrdinaireDto TarificationOrdinaireDto,
            @PathVariable Long id)
    {
        /*TarificationProjection tarificationProjection=TarificationOrdinaireDao.rechercherRegistraireSelonId(id);
        TarificationOrdinaireDto tarificationOrdinaireDto=new TarificationOrdinaireDto();
        tarificationOrdinaireDto=tarificationOrdinaireMapper.deTarificationProjection(tarificationProjection);
        tarificationOrdinaireDto.setIdTarificationOrdinaire(id);*/
        TarificationOrdinaireDto.setIdTarificationOrdinaire(id);
        return TarificationOrdinaireService.modifier(TarificationOrdinaireDto,"sgi");
    }
    @PutMapping("/depositaire/{id}")
    //  @PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> modifierDepositaire(
            @Valid @RequestBody TarificationOrdinaireDto TarificationOrdinaireDto,
            @PathVariable Long id)
    {

        TarificationOrdinaireDto.setIdTarificationOrdinaire(id);
        return TarificationOrdinaireService.modifier(TarificationOrdinaireDto,"depositaire");
    }
    @PutMapping("/place/{id}")
    //  @PreAuthorize("hasAuthority('ROLE_TarificationOrdinaire')")
    public ResponseEntity<Object> modifierPlace(
            @Valid @RequestBody TarificationOrdinaireDto TarificationOrdinaireDto,
            @PathVariable Long id)
    {

        TarificationOrdinaireDto.setIdTarificationOrdinaire(id);
        return TarificationOrdinaireService.modifier(TarificationOrdinaireDto,"place");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> supprimer(
                                            @PathVariable Long id)
    {
        return TarificationOrdinaireService.supprimer(id);
    }
}
