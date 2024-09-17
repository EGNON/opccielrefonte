package com.ged.controller.standard.parametre;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.TypeModeleMessageDto;
import com.ged.service.standard.TypeModeleMessageService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typemodelemessages")
public class TypeModeleMessageController {
    private final TypeModeleMessageService TypeModeleMessageService;

    public TypeModeleMessageController(TypeModeleMessageService TypeModeleMessageService) {

        this.TypeModeleMessageService = TypeModeleMessageService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return TypeModeleMessageService.afficherTous();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable Long id)
    {
        return TypeModeleMessageService.afficher(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return TypeModeleMessageService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody TypeModeleMessageDto TypeModeleMessageDto)
    {
        return TypeModeleMessageService.creer(TypeModeleMessageDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody TypeModeleMessageDto TypeModeleMessageDto,
                                           @PathVariable Long id)
    {
        TypeModeleMessageDto.setIdTypeModeleMessage(id);
        return TypeModeleMessageService.modifier(TypeModeleMessageDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable Long id)
    {
        return TypeModeleMessageService.supprimer(id);
    }
}
