package com.ged.controller.standard.parametre;

import com.ged.dao.standard.ProfessionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ProfessionDto;
import com.ged.entity.standard.Profession;
import com.ged.mapper.standard.ProfessionMapper;
import com.ged.service.standard.ProfessionService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@Validated
@RequestMapping("/professions")
public class ProfessionController {
    private final ProfessionService professionService;
    private final ProfessionDao professionDao;
    private final ProfessionMapper professionMapper;

    public ProfessionController(ProfessionService professionService, ProfessionDao professionDao, ProfessionMapper professionMapper) {
        this.professionService = professionService;
        this.professionDao = professionDao;
        this.professionMapper = professionMapper;
    }

    @GetMapping
    public Page<ProfessionDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return professionService.afficherProfessions(page,size);
    }
    @GetMapping("/liste")
    public List<ProfessionDto> afficherTous(){
        return professionService.afficherProfessions();
    }

    @GetMapping("/{id}")
    public ProfessionDto afficher(@PathVariable("id") Long id)
    {
        return professionService.afficher(id);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<ProfessionDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<Profession> professionDtoPage;
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleProfession");
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            professionDtoPage = professionDao.findByLibelleProfessionContainsIgnoreCase(datatableParameters.getSearch().getValue(), pageable);
        }
        else {
            professionDtoPage = professionDao.findAll(pageable);
        }
        List<ProfessionDto> content = professionDtoPage.getContent().stream().map(professionMapper::deProfession).collect(Collectors.toList());
        DataTablesResponse<ProfessionDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) professionDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) professionDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PostMapping
    public ProfessionDto ajouter(@Valid @RequestBody ProfessionDto professionDto)
    {
        return professionService.creerProfession(professionDto);
    }
    @PutMapping("/{id}")
    public ProfessionDto modifier(@Positive @PathVariable long id,
                                  @Valid @RequestBody ProfessionDto professionDto) {
        professionDto.setIdProf(id);
        return professionService.modifierProfession(professionDto);
    }

    @PostConstruct
    public List<Object> createProfessionFromOpcciel1() {
        return professionService.createProfessionFromOppciel1();
    }

    @DeleteMapping("/{id}")
    public void Supprimer(@Positive @PathVariable long id){
        professionService.supprimerProfession(id);
    }
}
