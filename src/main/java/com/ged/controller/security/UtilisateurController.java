package com.ged.controller.security;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.security.Utilisateur2Dto;
import com.ged.dto.security.UtilisateurDto;
import com.ged.service.standard.UtilisateurService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/utilisateurs")
public class UtilisateurController {
    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous(){
        return utilisateurService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> afficherUtilisateur(@PathVariable("id") Long id)
    {
        return utilisateurService.afficherUtilisateur(id);
    }

    @PostMapping
    public ResponseEntity<Object> ajouter(@RequestBody UtilisateurDto utilisateurDto)
    {
        return utilisateurService.creerUtilisateur(utilisateurDto);
    }

    @PostMapping("/datatable/list")
    public DataTablesResponse<Utilisateur2Dto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<Utilisateur2Dto> utilisateurDtoPage = utilisateurService.afficherUsers(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength());
        List<Utilisateur2Dto> content = utilisateurDtoPage.getContent().stream().collect(Collectors.toList());
        DataTablesResponse<Utilisateur2Dto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) utilisateurDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) utilisateurDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> modifier(@PathVariable long id, @RequestBody UtilisateurDto utilisateurDto){
        utilisateurDto.setIdPersonne(id);
        return utilisateurService.modifierUtilisateur(utilisateurDto);
    }

    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        utilisateurService.supprimerUtilisateur(id);
    }
}
