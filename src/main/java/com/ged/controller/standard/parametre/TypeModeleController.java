package com.ged.controller.standard.parametre;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dao.standard.TypeModeleDao;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.TypeModeleDto;
import com.ged.service.standard.TypeModeleService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typemodeles")
public class TypeModeleController {
    private final TypeModeleService TypeModeleService;
    private final TypeModeleDao typeModeleDao;

    public TypeModeleController(TypeModeleService TypeModeleService, TypeModeleDao typeModeleDao) {

        this.TypeModeleService = TypeModeleService;
        this.typeModeleDao = typeModeleDao;
    }
    @PostConstruct
    @EventListener(ApplicationReadyEvent.class)
    public void generate()
    {
        String[] typeModeles = {
                "Notifications", "Rendez-vous"};
        for (String typeModele: typeModeles) {
            if(!typeModeleDao.existsByLibelleTypeModele(typeModele))
            {
                TypeModeleDto typeModeleDto = new TypeModeleDto(typeModele);
                typeModeleDto.setSupprimer(false);
                TypeModeleService.creer(typeModeleDto);
            }
        }
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeModeleService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return TypeModeleService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeModeleService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeModeleDto TypeModeleDto)
    {
        return TypeModeleService.creer(TypeModeleDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeModeleDto TypeModeleDto,
                                           @PathVariable Long id)
    {
        TypeModeleDto.setIdTypeModele(id);
        return TypeModeleService.modifier(TypeModeleDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return TypeModeleService.supprimer(id);
    }
}
