package com.ged.controller.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.DetailModeleDto;
import com.ged.entity.opcciel.comptabilite.CleDetailModele;
import com.ged.service.opcciel.DetailModeleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/detailmodeles")
public class DetailModeleController {
    private final DetailModeleService DetailModeleService;

    public DetailModeleController(DetailModeleService DetailModeleService) {
        this.DetailModeleService = DetailModeleService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return DetailModeleService.afficherTous();
    }
    @GetMapping("/{codeModeleEcriture}/{numOrdre}/{numCompteComptable}")
    //@PreAuthorize("hasAuthority('ROLE_DetailModele')")
    public ResponseEntity<Object> afficher(@PathVariable String codeModeleEcriture,
                                           @PathVariable int numOrdre,
                                           @PathVariable String numCompteComptable)
    {
        CleDetailModele idDetailModele=new CleDetailModele();
        idDetailModele.setCoodeModeleEcriture(codeModeleEcriture);
        idDetailModele.setNumeroOrdre(numOrdre);
        idDetailModele.setNumCompteComptable(numCompteComptable);
        return DetailModeleService.afficher(idDetailModele);
    }
    @GetMapping("/{codeModeleEcriture}")
    //@PreAuthorize("hasAuthority('ROLE_DetailModele')")
    public ResponseEntity<Object> afficher(@PathVariable String codeModeleEcriture)
    {
        return DetailModeleService.afficherSelonModeleEcriture(codeModeleEcriture);
    }
    @PostMapping("/datatable/list")
    //@PreAuthorize("hasAuthority('ROLE_DetailModele')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return DetailModeleService.afficherTous(params);
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DetailModele')")
    public ResponseEntity<Object> creer(@Valid @RequestBody DetailModeleDto DetailModeleDto)
    {
        return DetailModeleService.creer(DetailModeleDto);
    }
    @PostMapping("/group")
//    @PreAuthorize("hasAuthority('ROLE_DetailModele')")
    public ResponseEntity<Object> creer(@Valid @RequestBody DetailModeleDto[] DetailModeleDto)
    {
        return DetailModeleService.creer(DetailModeleDto);
    }

    @PutMapping("/{codeModeleEcriture}/{numOrdre}/{numCompteComptable}")
  //  @PreAuthorize("hasAuthority('ROLE_DetailModele')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody DetailModeleDto DetailModeleDto,
            @PathVariable String codeModeleEcriture,
            @PathVariable int numOrdre,
            @PathVariable String numCompteComptable)
    {
        DetailModeleDto.setNumeroOrdre(numOrdre);
        DetailModeleDto.setNumCompteComptable(numCompteComptable);
        DetailModeleDto.getModeleEcriture().setCodeModeleEcriture(codeModeleEcriture);
        return DetailModeleService.modifier(DetailModeleDto);
    }

    @DeleteMapping("/{codeModeleEcriture}/{numOrdre}/{numCompteComptable}")
    public ResponseEntity<Object> supprimer(@PathVariable String codeModeleEcriture,
                                            @PathVariable int numOrdre,
                                            @PathVariable String numCompteComptable)
    {
        CleDetailModele idDetailModele=new CleDetailModele();
        idDetailModele.setCoodeModeleEcriture(codeModeleEcriture);
        idDetailModele.setNumeroOrdre(numOrdre);
        idDetailModele.setNumCompteComptable(numCompteComptable);
        return DetailModeleService.supprimer(idDetailModele);
    }
    @DeleteMapping("/{codeModeleEcriture}")
    public void supprimer(@PathVariable String codeModeleEcriture)
    {
         DetailModeleService.supprimerSelonModeleEcriture(codeModeleEcriture);
    }
}
