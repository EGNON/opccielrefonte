package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.CleTableauAmortissementDto;
import com.ged.dto.titresciel.TableauAmortissementDto;
import com.ged.entity.titresciel.CleTableauAmortissement;
import com.ged.service.titresciel.TableauAmortissementService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/tableauamortissements")
public class TableauAmortissementController {
    private final TableauAmortissementService TableauAmortissementService;

    public TableauAmortissementController(TableauAmortissementService TableauAmortissementService) {
        this.TableauAmortissementService = TableauAmortissementService;
    }

    @GetMapping("/{idTitre}/{dateEcheance}")
//    @PreAuthorize("hasAuthority('ROLE_TableauAmortissement')")
    public ResponseEntity<Object> afficher(@PathVariable Long idTitre,
                                           @PathVariable LocalDateTime dateEcheance)
    {
        CleTableauAmortissement cleTableauAmortissement=new CleTableauAmortissement();
        cleTableauAmortissement.setDateEcheance(dateEcheance);
        cleTableauAmortissement.setIdTitre(idTitre);
        return TableauAmortissementService.afficher(cleTableauAmortissement);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_TableauAmortissement')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return TableauAmortissementService.afficherTous(params);
    }


    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_TableauAmortissement')")
    public ResponseEntity<Object> creer(@Valid @RequestBody TableauAmortissementDto TableauAmortissementDto)
    {
        return TableauAmortissementService.creer(TableauAmortissementDto);
    }

    @PutMapping("/{idTitre}/{dateEcheance}")
//    @PreAuthorize("hasAuthority('ROLE_TableauAmortissement')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody TableauAmortissementDto TableauAmortissementDto,
            @Positive @PathVariable Long idTitre,
            @PathVariable LocalDateTime dateEcheance)
    {
        CleTableauAmortissementDto cleTableauAmortissement = new CleTableauAmortissementDto();
        cleTableauAmortissement.setIdTitre(idTitre);
        cleTableauAmortissement.setDateEcheance(dateEcheance);
        TableauAmortissementDto.setIdTabAmortissement(cleTableauAmortissement);
        return TableauAmortissementService.modifier(TableauAmortissementDto);
    }

    @DeleteMapping("/{idTitre}/{dateEcheance}")
//    @PreAuthorize("hasAuthority('ROLE_TableauAmortissement')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long idTitre,
                                            @PathVariable LocalDateTime dateEcheance)
    {
        CleTableauAmortissement cleTableauAmortissement=new CleTableauAmortissement();
        cleTableauAmortissement.setDateEcheance(dateEcheance);
        cleTableauAmortissement.setIdTitre(idTitre);
        return TableauAmortissementService.supprimer(cleTableauAmortissement);
    }
}
