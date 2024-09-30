package com.ged.controller.standard.parametre;

import com.ged.dao.standard.ProduitDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ProduitDto;
import com.ged.entity.standard.Produit;
import com.ged.mapper.standard.ProduitMapper;
import com.ged.service.standard.ProduitService;
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
@RequestMapping("/produits")
public class ProduitController {
    private final ProduitService produitService;
    private final ProduitDao produitDao;
    private final ProduitMapper produitMapper;

    public ProduitController(ProduitService produitService, ProduitDao produitDao, ProduitMapper produitMapper) {
        this.produitService = produitService;
        this.produitDao = produitDao;
        this.produitMapper = produitMapper;
    }

    @GetMapping
    public Page<ProduitDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return produitService.afficherProduits(page,size);
    }

    @GetMapping("/liste")
    public List<ProduitDto> afficherTous(){
        return produitService.afficherProduits();
    }

    @GetMapping("/{idProduit}")
    public ProduitDto afficherProduit(@PathVariable("idProduit") long idProduit){
        return produitService.afficherProduit(idProduit);
    }

    @PostMapping("/datatable/list")
    public DataTablesResponse<ProduitDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<Produit> produitDtoPage;
        Sort sort=Sort.by(Sort.Direction.ASC,"designation");
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            produitDtoPage = produitDao.findByDesignationContainsIgnoreCase(datatableParameters.getSearch().getValue(), pageable);
        }
        else {
            produitDtoPage = produitDao.findAll(pageable);
        }
        List<ProduitDto> content = produitDtoPage.getContent().stream().map(produitMapper::deProduit).collect(Collectors.toList());
        DataTablesResponse<ProduitDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) produitDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) produitDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }

    @PostMapping()
    public ProduitDto ajouter(@RequestBody ProduitDto produitDto)
    {
        return produitService.creerProduit(produitDto);
    }

    @PutMapping("/{id}")
    public ProduitDto modifier(@PathVariable long id, @RequestBody ProduitDto produitDto){
        produitDto.setIdProd(id);
        return produitService.modifierProduit(produitDto);
    }

    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        produitService.supprimerProduit(id);
    }
}
