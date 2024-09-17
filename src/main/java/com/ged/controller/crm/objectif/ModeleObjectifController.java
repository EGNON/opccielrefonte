package com.ged.controller.crm.objectif;

import com.ged.dao.crm.ModeleObjectifDao;
import com.ged.mapper.crm.ModeleObjectifMapper;
import com.ged.service.crm.ModeleObjectifService;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.ModeleObjectifDto;
import com.ged.entity.crm.ModeleObjectif;
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
@RequestMapping("/modeleobjectifs")
//@PreAuthorize("hasRole('ROLE_OBJECTIF_A_AFFECTER')")
public class ModeleObjectifController {
    private final ModeleObjectifService modeleObjectifService;
    private final ModeleObjectifDao modeleObjectifDao;
    private final ModeleObjectifMapper modeleObjectifMapper;

    public ModeleObjectifController(ModeleObjectifService modeleObjectifService, ModeleObjectifDao modeleObjectifDao, ModeleObjectifMapper modeleObjectifMapper) {
        this.modeleObjectifService = modeleObjectifService;
        this.modeleObjectifDao = modeleObjectifDao;
        this.modeleObjectifMapper = modeleObjectifMapper;
    }

    @GetMapping
    public List<ModeleObjectifDto> afficherTous()
    {
        return modeleObjectifService.afficherTous();
    }

    @GetMapping("{id}")
    public ModeleObjectifDto afficher(@PathVariable("id") Long id)
    {
        return modeleObjectifService.afficher(id);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<ModeleObjectifDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Sort sort=Sort.by(Sort.Direction.ASC,"nomModele");
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        Page<ModeleObjectif> modeleObjectifDtoPage;
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            modeleObjectifDtoPage = modeleObjectifDao.findByNomModeleContainsIgnoreCase(datatableParameters.getSearch().getValue(), pageable);
        }
        else {
            modeleObjectifDtoPage = modeleObjectifDao.findAll(pageable);
        }
        List<ModeleObjectifDto> content = modeleObjectifDtoPage.getContent().stream().map(modeleObjectifMapper::deModeleObjectif).collect(Collectors.toList());
        DataTablesResponse<ModeleObjectifDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) modeleObjectifDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) modeleObjectifDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PostMapping
    public ModeleObjectifDto ajouter(@RequestBody ModeleObjectifDto modeleObjectifDto)
    {
        return modeleObjectifService.creer(modeleObjectifDto);
    }

    @PutMapping("/{id}")
    public ModeleObjectifDto modifier(
            @RequestBody ModeleObjectifDto modeleObjectifDto,
            @PathVariable("id") Long id)
    {
        modeleObjectifDto.setIdModelObj(id);
        return modeleObjectifService.modifier(modeleObjectifDto);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable("id") Long id)
    {
        modeleObjectifService.supprimer(id);
    }
}
