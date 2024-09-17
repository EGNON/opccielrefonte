package com.ged.controller.titresciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.PlaceDto;
import com.ged.service.titresciel.PlaceService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/places")
public class PlaceController {
    private final PlaceService PlaceService;

    public PlaceController(PlaceService PlaceService) {

        this.PlaceService = PlaceService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return PlaceService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return PlaceService.afficher(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return PlaceService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody PlaceDto PlaceDto)
    {
        return PlaceService.creer(PlaceDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody PlaceDto PlaceDto,
                                           @PathVariable String code)
    {
        PlaceDto.setCodePlace(code);
        return PlaceService.modifier(PlaceDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return PlaceService.supprimer(code);
    }
}
