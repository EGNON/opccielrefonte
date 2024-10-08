package com.ged.controller.standard.parametre;

import com.ged.projection.PersonneProjection;
import com.ged.service.standard.PersonneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/personnephysiquemorales")
public class PersonnePhysiqueMoraleController {
    private final PersonneService personneService;

    public PersonnePhysiqueMoraleController(PersonneService personneService) {
        this.personneService = personneService;
    }

    @GetMapping
    public List<PersonneProjection> afficherTous(){
        return personneService.afficherPersonnePhysiqueMorales();
    }

    @GetMapping("/tous")
    public List<PersonneProjection> afficherListe(){
        return personneService.afficherPersonnePhysiqueMoralesListe();
    }

    @GetMapping("/{id}")
    public PersonneProjection afficherSelonId(@PathVariable("id") long id){
        return personneService.afficherPersonnePhysiqueMoraleSelonId(id);
    }
}
