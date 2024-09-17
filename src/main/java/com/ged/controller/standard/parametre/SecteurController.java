package com.ged.controller.standard.parametre;

import com.ged.dao.standard.SecteurDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.SecteurDto;
import com.ged.entity.standard.Secteur;
import com.ged.mapper.standard.SecteurMapper;
import com.ged.service.standard.SecteurService;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/secteurs")
public class SecteurController {
    private final SecteurService secteurService;
    private final SecteurDao secteurDao;
    private final SecteurMapper secteurMapper;

    public SecteurController(SecteurService secteurService, SecteurDao secteurDao, SecteurMapper secteurMapper) {
        this.secteurService = secteurService;
        this.secteurDao = secteurDao;
        this.secteurMapper = secteurMapper;
    }

    @GetMapping
    public Page<SecteurDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "size",defaultValue = "10") int size){
        return secteurService.afficherSecteurs(page,size);
    }
    @GetMapping("/liste")
    public List<SecteurDto> afficherTous(){
        return secteurService.afficherSecteurs();
    }

    @GetMapping("/{id}")
    public SecteurDto afficher(@PathVariable(name = "id") Long id)
    {
        return secteurService.afficherSecteur(id);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<SecteurDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<Secteur> secteurDtoPage;
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleSecteur");
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            secteurDtoPage = secteurDao.findByLibelleSecteurContainsIgnoreCase(datatableParameters.getSearch().getValue(), pageable);
        }
        else {
            secteurDtoPage = secteurDao.findAll(pageable);
        }
        List<SecteurDto> content = secteurDtoPage.getContent().stream().map(secteurMapper::deSecteur).collect(Collectors.toList());
        DataTablesResponse<SecteurDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) secteurDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) secteurDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PostMapping
    public SecteurDto ajouter(@RequestBody SecteurDto secteurDto)
    {
        return secteurService.creerSecteur(secteurDto);
    }
    @PutMapping("/{id}")
    public SecteurDto modifier(@PathVariable long id, @RequestBody SecteurDto secteurDto){
        secteurDto.setIdSecteur(id);
        return secteurService.modifierSecteur(secteurDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        secteurService.supprimerSecteur(id);
    }
}
