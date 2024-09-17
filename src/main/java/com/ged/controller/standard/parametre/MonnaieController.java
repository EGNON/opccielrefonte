package com.ged.controller.standard.parametre;

import com.ged.dao.standard.MonnaieDao;
import com.ged.mapper.standard.MonnaieMapper;
import com.ged.service.standard.MonnaieService;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.MonnaieDto;
import com.ged.entity.standard.Monnaie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/monnaies")
@PreAuthorize("hasAuthority('ROLE_MONNAIE')")
public class MonnaieController {
    private final MonnaieService monnaieService;
    private final MonnaieDao monnaieDao;
    private final MonnaieMapper monnaieMapper;

    public MonnaieController(MonnaieService MonnaieService, MonnaieDao monnaieDao, MonnaieMapper monnaieMapper) {
        this.monnaieService = MonnaieService;
        this.monnaieDao = monnaieDao;
        this.monnaieMapper = monnaieMapper;
    }

    @GetMapping
    public Page<MonnaieDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return monnaieService.afficherMonnaies(page,size);
    }
    @GetMapping("/liste")
    public List<MonnaieDto> afficherTous(){
        return monnaieService.afficherMonnaies();
    }

    @GetMapping("/{codeMonnaie}")
    public MonnaieDto afficher(@PathVariable("codeMonnaie") String codeMonnaie) {
        return monnaieService.afficherMonnaie(codeMonnaie);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<MonnaieDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<Monnaie> monnaieDtoPage;
        Sort sort=Sort.by(Sort.Direction.ASC,"nom");
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            monnaieDtoPage = monnaieDao.findByCodeMonnaieContainsIgnoreCaseOrNomContainsIgnoreCase(datatableParameters.getSearch().getValue(),datatableParameters.getSearch().getValue(), pageable);
        }
        else {
            monnaieDtoPage = monnaieDao.findAll(pageable);
        }
        List<MonnaieDto> content = monnaieDtoPage.getContent().stream().map(monnaieMapper::deMonnaie).collect(Collectors.toList());
        DataTablesResponse<MonnaieDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) monnaieDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) monnaieDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PostMapping
    public MonnaieDto ajouter(@RequestBody MonnaieDto monnaieDto)
    {
        return monnaieService.creerMonnaie(monnaieDto);
    }

    @PutMapping("/{id}")
    public MonnaieDto modifier(@PathVariable String id, @RequestBody MonnaieDto monnaieDto){
        monnaieDto.setCodeMonnaie(id);
        return monnaieService.modifierMonnaie(monnaieDto);
    }

    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable String id){
        monnaieService.supprimerMonnaie(id);
    }
}
