package com.ged.controller.crm;

import com.ged.dao.crm.IndicateurDao;
import com.ged.mapper.crm.IndicateurMapper;
import com.ged.service.crm.IndicateurService;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.IndicateurDto;
import com.ged.entity.crm.Indicateur;
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
@RequestMapping("/indicateurs")
public class IndicateurController {
    private final IndicateurService indicateurService;
    private final IndicateurDao indicateurDao;
    private final IndicateurMapper indicateurMapper;

    public IndicateurController(IndicateurService IndicateurService, IndicateurDao indicateurDao, IndicateurMapper indicateurMapper) {
        this.indicateurService = IndicateurService;
        this.indicateurDao = indicateurDao;
        this.indicateurMapper = indicateurMapper;
    }

    @GetMapping
    public Page<IndicateurDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return indicateurService.afficherIndicateurs(page,size);
    }
    @GetMapping("/liste")
    public List<IndicateurDto> afficherTous(){
        return indicateurService.afficherIndicateurs();
    }
    @GetMapping("/{idIndicateur}")
    public IndicateurDto afficher(@PathVariable("idIndicateur") long idIndicateur) {
        return indicateurService.afficherIndicateur(idIndicateur);
    }
    @PostMapping
    public IndicateurDto ajouter(@RequestBody IndicateurDto indicateurDto)
    {
        return indicateurService.creerIndicateur(indicateurDto);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<IndicateurDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        Page<Indicateur> indicateurDtoPage;
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            indicateurDtoPage = indicateurDao.findByCodeContainsIgnoreCaseOrLibelleContainsIgnoreCase(datatableParameters.getSearch().getValue(),datatableParameters.getSearch().getValue(), pageable);
        }
        else {
            indicateurDtoPage = indicateurDao.findAll(pageable);
        }
        List<IndicateurDto> content = indicateurDtoPage.getContent().stream().map(indicateurMapper::deIndicateur).collect(Collectors.toList());
        DataTablesResponse<IndicateurDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) indicateurDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) indicateurDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PutMapping("/{id}")
    public IndicateurDto modifier(@PathVariable long id, @RequestBody IndicateurDto indicateurDto){
        indicateurDto.setIdIndicateur(id);
        return indicateurService.modifierIndicateur(indicateurDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        indicateurService.supprimerIndicateur(id);
    }
}
