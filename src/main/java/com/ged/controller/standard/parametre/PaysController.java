package com.ged.controller.standard.parametre;

import com.ged.dao.standard.PaysDao;
import com.ged.mapper.standard.PaysMapper;
import com.ged.service.standard.PaysService;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PaysDto;
import com.ged.entity.standard.Pays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pays")
public class PaysController {
    private final PaysService paysService;
    private final PaysMapper paysMapper;
    private final PaysDao paysDao;

    public PaysController(PaysService paysService, PaysMapper paysMapper, PaysDao paysDao) {
        this.paysService = paysService;
        this.paysMapper = paysMapper;
        this.paysDao = paysDao;
    }

    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    public List<PaysDto> afficherTous(){
        return paysService.afficherListePays();
        //afficher pays
    }
    @GetMapping("/{id}")
    public PaysDto afficher(@PathVariable("id") long id) {
        return paysService.afficherPays(id);
    }

    @GetMapping("/liste")
    public List<PaysDto> afficherListeTous(){
        return paysService.afficherListePays();
    }

    @GetMapping("/liste/{estGafi}")
    public List<PaysDto> afficherListeTous(@PathVariable boolean estGafi){
        return paysService.afficherPaysSelonEstGafi(estGafi);

    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<PaysDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<Pays> paysDtoPage;
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleFr");
        String search="";
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        System.out.println(datatableParameters.getSearch().getValue());
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            search=datatableParameters.getSearch().getValue();
            System.out.println(search);
            if(search.equalsIgnoreCase("oui"))
            {
                paysDtoPage = paysDao.findByEstGafi(true, pageable);
            }
            else
                if(search.equalsIgnoreCase("non")) {
                    paysDtoPage = paysDao.findByEstGafi(false, pageable);
                }
                else
                {
                    paysDtoPage = paysDao.rechercherPays(datatableParameters.getSearch().getValue(), pageable);
                }
        }
        else {
            paysDtoPage = paysDao.findAll(pageable);
        }
        List<PaysDto> content = paysDtoPage.getContent().stream().map(paysMapper::dePays).collect(Collectors.toList());
        DataTablesResponse<PaysDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) paysDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) paysDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }
    @PostMapping("/datatable-{estGafi}/list")
    public DataTablesResponse<PaysDto> datatableList(
            @RequestBody DatatableParameters params,
            @PathVariable Boolean estGafi)
    {
        return paysService.afficherPaysSelonEstGafi(estGafi, params);
    }
    @PostMapping
    public PaysDto ajouter(@RequestBody PaysDto paysDto)
    {
        return paysService.creerPays(paysDto);
    }
    @PutMapping("/{id}")
    public PaysDto modifier(@PathVariable long id, @RequestBody PaysDto paysDto){
        paysDto.setIdPays(id);
        return paysService.modifierPays(paysDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        paysService.supprimerPays(id);
    }
}
