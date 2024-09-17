package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.TableRequest;
import com.ged.dto.titresciel.CleCoursTitreDto;
import com.ged.dto.titresciel.CoursTitreDto;
import com.ged.entity.titresciel.CleCoursTitre;
import com.ged.service.titresciel.CoursTitreService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/courstitres")
public class CoursTitreController {
    private final CoursTitreService CoursTitreService;

    public CoursTitreController(CoursTitreService CoursTitreService) {
        this.CoursTitreService = CoursTitreService;
    }

    @PostMapping("/dernier/cours/{idTitre}")
    public ResponseEntity<Object> getLastCoursTitre(@PathVariable Long idTitre) {
        return CoursTitreService.getLastCoursTitre(idTitre);
    }

    @PostMapping("/place/{codePlace}")
    public ResponseEntity<Object> getAllDateCours(
            @PathVariable String codePlace,
            @RequestBody TableRequest tableRequest) {
        return CoursTitreService.getAllDateCours(codePlace, tableRequest);
    }

    @PostMapping("/maj/cours/{codePlace}")
    public ResponseEntity<Object> majCoursTitrePlace(
            @PathVariable("codePlace") String codePlace,
            @RequestBody TableRequest tableRequest
    ) {
        return CoursTitreService.getAllCoursTitreMaj(codePlace, tableRequest);
    }

    @GetMapping("/{idTitre}/{dateCours}")
    public ResponseEntity<Object> afficher(@PathVariable Long idTitre,
                                           @PathVariable LocalDateTime dateCours)
    {
        CleCoursTitre cleCoursTitre=new CleCoursTitre();
        cleCoursTitre.setDateCours(dateCours);
        cleCoursTitre.setIdTitre(idTitre);
        return CoursTitreService.afficher(cleCoursTitre);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_CoursTitre')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return CoursTitreService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_CoursTitre')")
    public ResponseEntity<Object> creer(@Valid @RequestBody CoursTitreDto CoursTitreDto)
    {
        return CoursTitreService.creer(CoursTitreDto);
    }

    @PostMapping("/add/collection/all{codePlace}")
    public ResponseEntity<Object> addAll(
            @Valid @RequestBody List<CoursTitreDto> coursTitreDtos,
            @PathVariable(name = "codePlace") String codePlace) {
        return CoursTitreService.addAll(coursTitreDtos, codePlace);
    }

    @PutMapping("/{idTitre}/{dateCours}")
//    @PreAuthorize("hasAuthority('ROLE_CoursTitre')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody CoursTitreDto CoursTitreDto,
            @Positive @PathVariable Long idTitre,
            @PathVariable LocalDateTime dateCours)
    {
        CleCoursTitreDto cleCoursTitre=new CleCoursTitreDto();
        cleCoursTitre.setIdTitre(idTitre);
        cleCoursTitre.setDateCours(dateCours);
        CoursTitreDto.setIdCoursTitre(cleCoursTitre);
        return CoursTitreService.modifier(CoursTitreDto);
    }

    @DeleteMapping("/{idTitre}/{dateCours}")
//    @PreAuthorize("hasAuthority('ROLE_CoursTitre')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long idTitre,
                                            @PathVariable LocalDateTime dateCours)
    {
        CleCoursTitre cleCoursTitre=new CleCoursTitre();
        cleCoursTitre.setDateCours(dateCours);
        cleCoursTitre.setIdTitre(idTitre);
        return CoursTitreService.supprimer(cleCoursTitre);
    }
}
