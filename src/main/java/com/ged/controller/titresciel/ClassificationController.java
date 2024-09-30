package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ClassificationOPCDto;
import com.ged.service.titresciel.ClassificationService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/classifications")
public class ClassificationController {
    private final ClassificationService classificationService;

    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return classificationService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_CLASSIFICATION')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") String codeClassification)
    {
        return classificationService.afficher(codeClassification);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_CLASSIFICATION')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return classificationService.afficherTous(params);
    }

    /*@PostConstruct
    public List<Object> createClassificationFromOpcciel1() {
        return classificationService.createClassificationFromOppciel1();
    }*/

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_CLASSIFICATION')")
    public ResponseEntity<Object> creer(@Valid @RequestBody ClassificationOPCDto classificationOPCDto)
    {
        return classificationService.creer(classificationOPCDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_CLASSIFICATION')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody ClassificationOPCDto classificationOPCDto,
            @Positive @PathVariable("id") String codeClassification)
    {
        classificationOPCDto.setCodeClassification(codeClassification);
        return classificationService.modifier(classificationOPCDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_CLASSIFICATION')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable("id") String codeClassification)
    {
        return classificationService.supprimer(codeClassification);
    }
}
