package com.ged.controller.crm.rdv;

import com.ged.dao.crm.RDVDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.RDVDto;
import com.ged.mapper.crm.RDVMapper;
import com.ged.service.crm.AgentConcerneService;
import com.ged.service.crm.RDVService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rdvs")
public class RDVController {
    private final RDVService rdvService;
    private final RDVDao rdvDao;
    private final RDVMapper rdvMapper;

    public RDVController(RDVService rdvService, AgentConcerneService agentConcerneService, RDVDao rdvDao, RDVMapper rdvMapper) {
        this.rdvService = rdvService;
        this.rdvDao = rdvDao;
        this.rdvMapper = rdvMapper;
    }

    @GetMapping
    public Page<RDVDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                     @RequestParam(value = "size",defaultValue = "10") int size){
        return rdvService.afficherRDVs(page,size);
    }
    @GetMapping("/listerdvs")
    public List<RDVDto> afficherTous(){
        return rdvService.afficherRDVsSurCR();
    }

    @GetMapping("/modelemsgalerte/{id}")
    public List<RDVDto> afficherRDVSelonModeleMsgAlerte(@PathVariable Long id){
        return rdvService.afficherRDVSelonModeleMsgAlerte(id);
    }

    @GetMapping("/liste")
    public Page<RDVDto> afficherRDVListe(@RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "size",defaultValue = "10") int size){
//        PageRequest pageRequest=PageRequest.of(page,size);
        return rdvService.afficherRDVs2(page,size);
    }
    @GetMapping("/{idRdv}")
    public RDVDto afficher(@PathVariable("idRdv") long idRdv) {
        return rdvService.afficherRDVSelonIdParProjection(idRdv);
    }
//    @GetMapping("/personne/{idRdv}")
//    public RDVDto afficherRdvSelonPersonne(@PathVariable("idPersonne") long idPersonne) {
//        return rdvService.afficherRDVSelonPersonne(idPersonne);
//    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<RDVDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        /*Page<RDVDto> rdvDtoPage = rdvService.afficherRDVs2(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength());
        List<RDVDto> content = rdvDtoPage.getContent().stream().collect(Collectors.toList());
        DataTablesResponse<RDVDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) rdvDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) rdvDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);*/
        Page<RDVDto> rdvPage;

        String search="";
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength());

        /*if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {*/
            /*search=datatableParameters.getSearch().getValue();
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
        else {*/
            rdvPage =rdvDao.afficherRDV(pageable).map(rdvMapper::deRDVProjection);
        //}
        List<RDVDto> content = rdvPage.getContent().stream().collect(Collectors.toList());
        DataTablesResponse<RDVDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) rdvPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) rdvPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PostMapping
    public RDVDto ajouter(@RequestBody RDVDto rdvDto)
    {
        return rdvService.creerRDV(rdvDto);
    }
    @PutMapping("/{id}")
    public RDVDto modifier(@PathVariable long id, @RequestBody RDVDto rdvDto){
        rdvDto.setIdRdv(id);
        return rdvService.modifierRDV(rdvDto);
    }
    @PutMapping("/reelle/{id}")
    public int modifierUnePartieDeRDV(@PathVariable long id, @RequestBody RDVDto rdvDto){
        rdvDto.setIdRdv(id);
        return rdvService.modifierUnePartieDeRDV(rdvDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){

//        agentConcerneService.supprimerAgentConcerneSelonRDV(id);
        rdvService.supprimerRDV(id);
    }
}
