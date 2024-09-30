package com.ged.controller.standard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dao.standard.CategoriePersonneDao;
import com.ged.mapper.standard.CategoriePersonneMapper;
import com.ged.service.standard.CategoriePersonneService;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.CategoriePersonneDto;
import com.ged.entity.standard.CategoriePersonne;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
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
@RequestMapping("/categories")
public class CategoriePersonneController {
    private final CategoriePersonneService categoriePersonneService;
    private final CategoriePersonneDao categoriePersonneDao;
    private final CategoriePersonneMapper categoriePersonneMapper;

    public CategoriePersonneController(CategoriePersonneService categoriePersonneService, CategoriePersonneDao categoriePersonneDao, CategoriePersonneMapper categoriePersonneMapper) {
        this.categoriePersonneService = categoriePersonneService;
        this.categoriePersonneDao = categoriePersonneDao;
        this.categoriePersonneMapper = categoriePersonneMapper;
    }

    @GetMapping("/recuperer/total/categorie")
//    @PreAuthorize("hasAuthority('ROLE_CATEGORIE')")
    public Object[] getTotalCat() {
        return this.categoriePersonneService.get();
    }

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_CATEGORIE')")
    public Page<CategoriePersonneDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return categoriePersonneService.afficherCategoriePersonnes(page, size);
    }
    @GetMapping("/liste")
//    @PreAuthorize("hasAuthority('ROLE_CATEGORIE')")
    public List<CategoriePersonneDto> afficherTous()
    {
        return categoriePersonneService.afficherCategoriePersonnes();
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_CATEGORIE')")
    public CategoriePersonneDto afficher(@PathVariable("id") Long id)
    {
        return categoriePersonneService.afficher(id);
    }

    @GetMapping("/recherche/{libelle}")
//    @PreAuthorize("hasAuthority('ROLE_CATEGORIE')")
    public CategoriePersonneDto recherherCategoriePersonneParLibelle(@PathVariable("libelle") String libelle)
    {
        return categoriePersonneService.rechercherCategoriePersonneParLibelle(libelle);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_CATEGORIE')")
    public CategoriePersonneDto ajouter(@Valid @RequestBody CategoriePersonneDto categoriePersonneDto) throws JsonProcessingException {
        return categoriePersonneService.creerCategoriePersonne(categoriePersonneDto);
    }
    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_CATEGORIE')")
    public DataTablesResponse<CategoriePersonneDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<CategoriePersonne> categoriePersonneDtoPage;
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            categoriePersonneDtoPage = categoriePersonneDao.findByLibelleContainsIgnoreCase(datatableParameters.getSearch().getValue(), pageable);
        }
        else {
            categoriePersonneDtoPage = categoriePersonneDao.findAll(pageable);
        }
        List<CategoriePersonneDto> content = categoriePersonneDtoPage.getContent().stream().map(categoriePersonneMapper::deCatPersonne).collect(Collectors.toList());
        DataTablesResponse<CategoriePersonneDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) categoriePersonneDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) categoriePersonneDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_CATEGORIE')")
    public CategoriePersonneDto modifier(@Valid @RequestBody CategoriePersonneDto categoriePersonneDto,
                                         @Positive @PathVariable("id") Long id)
    {
        categoriePersonneDto.setIdCategorie(id);
        return categoriePersonneService.modifierCategoriePersonne(categoriePersonneDto);
    }

    /*@PostConstruct
    public List<Object> createCategorieFromOpcciel1() {
        return categoriePersonneService.createCategorieFromOppciel1();
    }*/

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_CATEGORIE')")
    public void supprimer(@Positive @PathVariable("id") Long id)
    {
        categoriePersonneService.supprimerCategoriePersonne(id);
    }
}
