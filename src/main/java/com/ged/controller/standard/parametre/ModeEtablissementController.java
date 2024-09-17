package com.ged.controller.standard.parametre;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dao.standard.ModeEtablissementDao;
import com.ged.mapper.standard.ModeEtablissementMapper;
import com.ged.service.standard.ModeEtablissementService;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ModeEtablissementDto;
import com.ged.entity.standard.ModeEtablissement;
import jakarta.annotation.PostConstruct;
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
@RequestMapping("/modeetablissements")
@PreAuthorize("hasAuthority('ROLE_MODE_ETABLISSEMENT')")
public class ModeEtablissementController {
    private final ModeEtablissementService modeEtablissementService;
    private final ModeEtablissementDao modeEtablissementDao;
    private final ModeEtablissementMapper modeEtablissementMapper;

    public ModeEtablissementController(ModeEtablissementService ModeEtablissementService, ModeEtablissementDao modeEtablissementDao, ModeEtablissementMapper modeEtablissementMapper) {
        this.modeEtablissementService = ModeEtablissementService;
        this.modeEtablissementDao = modeEtablissementDao;
        this.modeEtablissementMapper = modeEtablissementMapper;
    }

    @GetMapping
    public Page<ModeEtablissementDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return modeEtablissementService.afficherModeEtablissements(page,size);
    }
    @GetMapping("/liste")
    public List<ModeEtablissementDto> afficherTous(){
        return modeEtablissementService.afficherModeEtablissements();
    }

    @GetMapping("/{idModeEtablissement}")
    public ModeEtablissementDto afficher(@PathVariable("idModeEtablissement") long idModeEtablissement) {
        return modeEtablissementService.afficher(idModeEtablissement);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<ModeEtablissementDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<ModeEtablissement> modeEtablissementDtoPage;
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            modeEtablissementDtoPage = modeEtablissementDao.findByLibelleContainsIgnoreCase(datatableParameters.getSearch().getValue(), pageable);
        }
        else {
            modeEtablissementDtoPage = modeEtablissementDao.findAll(pageable);
        }
        List<ModeEtablissementDto> content = modeEtablissementDtoPage.getContent().stream().map(modeEtablissementMapper::deModeEtablissement).collect(Collectors.toList());
        DataTablesResponse<ModeEtablissementDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) modeEtablissementDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) modeEtablissementDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PostMapping
    public ModeEtablissementDto ajouter(@RequestBody ModeEtablissementDto modeEtablissementDto) throws JsonProcessingException {
        return modeEtablissementService.creerModeEtablissement(modeEtablissementDto);
    }
    @PostConstruct
    public void generate() throws JsonProcessingException {
        String[] modeetablissements = {
                "RECOMMANDATION", "PROSPECTION"
        };
        for (String modeetablissement: modeetablissements) {
            if(!modeEtablissementService.existByLibelle(modeetablissement))
            {
                ModeEtablissementDto modeEtablissementDto = new ModeEtablissementDto(modeetablissement);
                modeEtablissementService.creerModeEtablissement(modeEtablissementDto);
            }
        }
    }
    @PutMapping("/{id}")
    public ModeEtablissementDto modifier(@PathVariable long id, @RequestBody ModeEtablissementDto modeEtablissementDto){
        modeEtablissementDto.setIdModeEtablissement(id);
        return modeEtablissementService.modifierModeEtablissement(modeEtablissementDto);
    }

    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        modeEtablissementService.supprimerModeEtablissement(id);
    }
}
