package com.ged.controller.opcciel;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.JournalDto;
import com.ged.service.opcciel.JournalService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/journals")
public class JournalController {
    private final JournalService journalService;

    public JournalController(JournalService JournalService) {

        this.journalService = JournalService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return journalService.afficherTous();
    }

    @GetMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "code") String code)
    {
        return journalService.afficher(code);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return journalService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody JournalDto JournalDto)
    {
        return journalService.creer(JournalDto);
    }

    @PutMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody JournalDto JournalDto,
                                           @PathVariable String code)
    {
        JournalDto.setCodeJournal(code);
        return journalService.modifier(JournalDto);
    }

    @DeleteMapping("/{code}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> supprimer(@PathVariable String code)
    {
        return journalService.supprimer(code);
    }
}
