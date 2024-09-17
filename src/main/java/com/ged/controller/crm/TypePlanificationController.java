package com.ged.controller.crm;

import com.ged.dto.standard.TypePlanificationDto;
import com.ged.service.standard.TypePlanificationService;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typeplanifications")
public class TypePlanificationController {
    private final TypePlanificationService typePlanificationService;

    public TypePlanificationController(TypePlanificationService typePlanificationService) {
        this.typePlanificationService = typePlanificationService;
    }

    @GetMapping
    public Page<TypePlanificationDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                              @RequestParam(value = "size",defaultValue = "10") int size){
        return typePlanificationService.afficherTypePlanifications(page,size);
    }

    @GetMapping("/tous")
    public List<TypePlanificationDto> afficherTypePlanificationsTous(){
        return typePlanificationService.afficherTypePlanificationsTous();
    }

    @GetMapping("/{idTypePlanification}")
    public TypePlanificationDto afficherTypePlanification(@PathVariable("idTypePlanification") long idTypePlanification){
        return typePlanificationService.afficherTypePlanification(idTypePlanification);
    }

    @PostMapping
    public TypePlanificationDto ajouter(@RequestBody TypePlanificationDto typePlanificationDto)
    {
        return typePlanificationService.creerTypePlanification(typePlanificationDto);
    }

    @PostConstruct
    public void generate()
    {
        String[] typeplanifications = {"Une fois", "Périodique"};
        for (String typeplanification : typeplanifications) {
            if(!typePlanificationService.existByLibelle(typeplanification))
            {
                TypePlanificationDto typePlanificationDto = new TypePlanificationDto();
                typePlanificationDto.setLibelleTypePlanification(typeplanification);
                typePlanificationService.creerTypePlanification(typePlanificationDto);
            }
        }
    }

    @PutMapping("/{id}")
    public TypePlanificationDto modifier(@PathVariable long id, @RequestBody TypePlanificationDto typePlanificationDto){
        typePlanificationDto.setIdTypePlanification(id);
        return typePlanificationService.modifierTypePlanification(typePlanificationDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        typePlanificationService.supprimerTypePlanification(id);
    }
}
