package com.ged.controller.opcciel;

import com.ged.dao.opcciel.SocieteDeGestionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.SocieteDeGestionDto;
import com.ged.mapper.opcciel.SocieteDeGestionMapper;
import com.ged.service.opcciel.SocieteDeGestionService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/societedegestions")
public class SocieteDeGestionController {
    private final SocieteDeGestionService societeDeGestionService;
    private final SocieteDeGestionDao societeDeGestionDao;
    private final SocieteDeGestionMapper societeDeGestionMapper;

    public SocieteDeGestionController(SocieteDeGestionService societeDeGestionService, SocieteDeGestionDao societeDeGestionDao, SocieteDeGestionMapper societeDeGestionMapper) {
        this.societeDeGestionService = societeDeGestionService;
        this.societeDeGestionDao = societeDeGestionDao;
        this.societeDeGestionMapper = societeDeGestionMapper;
    }

    @GetMapping
    public Page<SocieteDeGestionDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                                  @RequestParam(value = "size",defaultValue = "10") int size){
        return null;//societeDeGestionService.afficherSocieteDeGestions(page,size);
    }
    @GetMapping("/liste")
    public List<SocieteDeGestionDto> afficherTous(){
        return null;//societeDeGestionService.afficherSocieteDeGestions();
    }

    @GetMapping("/{id}")
    public SocieteDeGestionDto afficher(@PathVariable(name = "id") Long id)
    {
        return societeDeGestionService.afficher(id);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<SocieteDeGestionDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        return societeDeGestionService.afficherTous(datatableParameters);
    }
    @PostMapping
    public SocieteDeGestionDto ajouter(@RequestBody SocieteDeGestionDto societeDeGestionDto)
    {
        return societeDeGestionService.creer(societeDeGestionDto);
    }
    @PutMapping("/{id}")
    public SocieteDeGestionDto modifier(@PathVariable long id, @RequestBody SocieteDeGestionDto societeDeGestionDto){
        societeDeGestionDto.setIdPersonne(id);
        return societeDeGestionService.modifier(societeDeGestionDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        societeDeGestionService.supprimer(id);
    }
}
