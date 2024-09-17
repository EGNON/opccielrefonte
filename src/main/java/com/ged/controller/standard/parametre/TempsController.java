package com.ged.controller.standard.parametre;

import com.ged.dto.standard.TempsDto;
import com.ged.service.standard.TempsService;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/temps")
public class TempsController {
    private final TempsService tempsService;

    public TempsController(TempsService tempsService) {
        this.tempsService = tempsService;
    }

    @GetMapping
    public Page<TempsDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                              @RequestParam(value = "size",defaultValue = "10") int size){
        return tempsService.afficherTempss(page,size);
    }

    @GetMapping("/tous")
    public List<TempsDto> afficherTous(){
        return tempsService.afficherTous();
    }

    @GetMapping("/{idTemps}")
    public TempsDto afficherTemps(@PathVariable("idTemps") long idTemps){
        return tempsService.afficherTemps(idTemps);
    }

    @PostConstruct
    public void generer() {
        String[] temps = {"Heure", "Minute", "Seconde"};
        for (String temp: temps) {
            if(!tempsService.existsByLibelle(temp))
            {
                TempsDto tempsDto = new TempsDto(temp);
                tempsService.creerTemps(tempsDto);
            }
        }
    }

    @PostMapping
    public TempsDto ajouter(@RequestBody TempsDto tempsDto)
    {
        return tempsService.creerTemps(tempsDto);
    }

    @PutMapping("/{id}")
    public TempsDto modifier(@PathVariable long id, @RequestBody TempsDto tempsDto){
        tempsDto.setIdTemps(id);
        return tempsService.modifierTemps(tempsDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        tempsService.supprimerTemps(id);
    }
}
