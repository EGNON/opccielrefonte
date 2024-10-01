package com.ged.controller.standard.parametre;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PersonnelDto;
import com.ged.service.standard.PersonnelService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/personnels")
//@PreAuthorize("hasAuthority('ROLE_PERSONNEL')")
public class PersonnelController {
    private final PersonnelService personnelService;

    public PersonnelController(PersonnelService personnelService) {
        this.personnelService = personnelService;
    }

    @GetMapping
    public Page<PersonnelDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                           @RequestParam(value = "size",defaultValue = "10") int size){
        return personnelService.afficherPersonnels(page,size);
    }

    @PostMapping("/datatable/list")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return personnelService.afficherTous(params);
    }

    @GetMapping("/{idPersonne}")
    public ResponseEntity<Object> afficher(@PathVariable("idPersonne") long idPersonne) {
        return personnelService.afficherPersonnel(idPersonne);
    }

    @GetMapping("/liste")
    public List<PersonnelDto> afficherTous(){
        return personnelService.afficherPersonnelListe();
    }

    @GetMapping("/estcommercial")
    public List<PersonnelDto> afficherSelonEstCommercial(){
        return personnelService.afficherPersonnelSelonEstCommercil(true);
    }

    @PostMapping
    public PersonnelDto ajouter(@RequestBody PersonnelDto personnelDto)
    {
        return personnelService.creerPersonnel(personnelDto);
    }

    @PutMapping("/{id}")
    public PersonnelDto modifier(@PathVariable Long id, @RequestBody PersonnelDto personnelDto){
        personnelDto.setIdPersonne(id);
        return personnelService.modifierPersonnel(personnelDto);
    }

    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        personnelService.supprimerPersonnel(id);
    }
}
