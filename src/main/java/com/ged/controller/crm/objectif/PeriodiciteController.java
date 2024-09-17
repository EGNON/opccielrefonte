package com.ged.controller.crm.objectif;

import com.ged.dao.standard.PeriodiciteDao;
import com.ged.mapper.crm.PeriodiciteMapper;
import com.ged.service.crm.PeriodiciteService;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PeriodiciteDto;
import com.ged.entity.standard.Periodicite;
import jakarta.annotation.PostConstruct;
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
@RequestMapping("/periodicites")
public class PeriodiciteController {
    private final PeriodiciteService periodiciteService;
    private final PeriodiciteDao periodiciteDao;
    private final PeriodiciteMapper periodiciteMapper;

    public PeriodiciteController(PeriodiciteService periodiciteService, PeriodiciteDao periodiciteDao, PeriodiciteMapper periodiciteMapper) {
        this.periodiciteService = periodiciteService;
        this.periodiciteDao = periodiciteDao;
        this.periodiciteMapper = periodiciteMapper;
    }

    @GetMapping
    public Page<PeriodiciteDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return periodiciteService.afficherPeriodicites(page,size);
    }
    @GetMapping("/liste")
    public List<PeriodiciteDto> afficherTous(){
        return periodiciteService.afficherPeriodicites();
    }

    @GetMapping("/{idPeriodicite}")
    public PeriodiciteDto afficher(@PathVariable("idPeriodicite") long idPeriodicite) {
        return periodiciteService.afficherPeriodicite(idPeriodicite);
    }

    @PostMapping("/datatable/list")
    public DataTablesResponse<PeriodiciteDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<Periodicite> periodiciteDtoPage;
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            periodiciteDtoPage = periodiciteDao.findByLibelleContainsIgnoreCase(datatableParameters.getSearch().getValue(), pageable);
        }
        else {
            periodiciteDtoPage = periodiciteDao.findAll(pageable);
        }
        List<PeriodiciteDto> content = periodiciteDtoPage.getContent().stream().map(periodiciteMapper::dePeriodicite).collect(Collectors.toList());
        DataTablesResponse<PeriodiciteDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) periodiciteDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) periodiciteDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }

    @PostMapping
    public PeriodiciteDto ajouter(@RequestBody PeriodiciteDto periodiciteDto)
    {
        return periodiciteService.creerPeriodicite(periodiciteDto);
    }

    @PostConstruct
    public void generate()
    {
        String[] periodicites = {"Hebdomadaire", "Mensuelle", "Quotidienne"};
        for (String periodicite : periodicites) {
            if(!periodiciteService.existByLibelle(periodicite))
            {
                PeriodiciteDto periodiciteDto = new PeriodiciteDto();
                periodiciteDto.setLibelle(periodicite);
                periodiciteService.creerPeriodicite(periodiciteDto);
            }
        }
    }

    @PutMapping("/{id}")
    public PeriodiciteDto modifier(@PathVariable long id, @RequestBody PeriodiciteDto periodiciteDto){
        periodiciteDto.setIdPeriodicite(id);
        return periodiciteService.modifierPeriodicite(periodiciteDto);
    }

    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        periodiciteService.supprimerPeriodicite(id);
    }
}
