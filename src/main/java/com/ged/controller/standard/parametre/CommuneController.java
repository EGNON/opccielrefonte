package com.ged.controller.standard.parametre;

import com.ged.dto.standard.CommuneDto;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.CommuneService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/communes")
@PreAuthorize("hasAuthority('ROLE_COMMUNE')")
public class CommuneController {
    private final CommuneService communeService;

    public CommuneController(CommuneService communeService) {
        this.communeService = communeService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                               @RequestParam(value = "size", defaultValue = "10") int size)
    {
        try {
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    communeService.afficherCommunes(page, size));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    null);
        }

    }
    @GetMapping("/liste")
    public List<CommuneDto> afficherListe()
    {
        return communeService.afficherCommunesListe();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_COMMUNE')")
    public CommuneDto ajouter(@RequestBody CommuneDto communeDto)
    {
        return communeService.creerCommune(communeDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_COMMUNE')")
    public CommuneDto modifier(@RequestBody CommuneDto communeDto, @PathVariable Long id)
    {
        communeDto.setIdCommune(id);
        return communeService.modifierCommune(communeDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_COMMUNE')")
    public void supprimer(@PathVariable Long id)
    {
        communeService.supprimerCommune(id);
    }
}
