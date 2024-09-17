package com.ged.controller.standard.parametre;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.security.UtilisateurDto;
import com.ged.service.standard.UtilisateurService;
import org.springframework.data.domain.Page;
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
    public List<UtilisateurDto> afficherTous(){
        return utilisateurService.afficherTous();
    }

    @GetMapping("/{id}")
    public UtilisateurDto afficherUtilisateur(@PathVariable("id") Long id)
    {
        return utilisateurService.afficherUtilisateur(id);
    }

    @PostMapping
    public UtilisateurDto ajouter(@RequestBody UtilisateurDto utilisateurDto)
    {
        return utilisateurService.creerUtilisateur(utilisateurDto);
    }

    @PostMapping("/datatable/list")
    public DataTablesResponse<UtilisateurDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<UtilisateurDto> utilisateurDtoPage = utilisateurService.afficherUtilisateurs(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength());
        List<UtilisateurDto> content = utilisateurDtoPage.getContent().stream().collect(Collectors.toList());
        DataTablesResponse<UtilisateurDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) utilisateurDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) utilisateurDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @PutMapping("/{id}")
    public UtilisateurDto modifier(@PathVariable long id, @RequestBody UtilisateurDto utilisateurDto){
        utilisateurDto.setIdPersonne(id);
        return utilisateurService.modifierUtilisateur(utilisateurDto);
    }

    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        utilisateurService.supprimerUtilisateur(id);
    }
}
