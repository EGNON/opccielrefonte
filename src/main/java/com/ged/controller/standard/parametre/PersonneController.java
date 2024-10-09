package com.ged.controller.standard.parametre;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PersonneDto;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.projection.PersonneProjection;
import com.ged.service.standard.PersonneService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/personnes")
public class PersonneController {
    private final PersonneService personneService;
    private final PersonneMapper personneMapper;

    public PersonneController(PersonneService personneService, PersonneMapper personneMapper) {
        this.personneService = personneService;
        this.personneMapper = personneMapper;
    }

    @PostMapping("existance/{num}/compte")
    public Boolean existsByNumeroCpteDeposit(@PathVariable String num) {
        return personneService.existsByNumeroCpteDeposit(num);
    }

    @GetMapping
    public Map<String, List<PersonneDto>> afficherPersonnes()
    {
        Map<String, List<PersonneDto>> response = new HashMap<>();
        response.put("items", personneService.afficherPersonneTous());
        return response;
    }
    @GetMapping("/verifiernumerocptedepositaire/{numero}")
    public List<PersonneDto> verifierNumeroCompteDepositaire(@PathVariable String numero)
    {
        return personneService.recherherNumeroCompteDepositaire(numero);
    }
    @GetMapping("qualite/{qualite}")
    public List<PersonneDto> afficherSelonQualite(@PathVariable("qualite") String qualite)
    {
        return personneService.afficherSelonQualite(qualite);
    }
    @GetMapping("personnenotinopcvm/{idOpcvm}")
    public List<PersonneProjection> afficherPersonne(@PathVariable Long idOpcvm)
    {
        return personneService.afficherPersonneNotInOpcvm(idOpcvm);
    }
    @GetMapping("personneinopcvm/{idOpcvm}")
    public List<PersonneProjection> afficherPersonneInOpcvm(@PathVariable Long idOpcvm)
    {
        return personneService.afficherPersonneInOpcvm(idOpcvm);
    }
    @GetMapping("/gele")
    public List<PersonneDto> afficherCompteGeleNonGele()
    {
        return personneService.afficherCompteGeleNonGele();
    }
    @GetMapping("/gele/{estGele}")
    public List<PersonneDto> afficherCompteGeleNonGele(@PathVariable boolean estGele)
    {
        return personneService.afficherCompteGeleNonGele(estGele);
    }
    @GetMapping("/exposejuge")
    public List<PersonneDto> afficherSelonQualite()
    {
        return personneService.afficherSelonQualite();
    }

    @GetMapping("/{idPersonne}")
    public PersonneDto afficherPersonneSelonId(@PathVariable("idPersonne") long idPersonne) {
        return personneMapper.dePersonne(personneService.afficherPersonneSelonId(idPersonne));
    }

    @PostMapping("/datatable/list/gele")
    public DataTablesResponse<PersonneDto> datatableListCompteGele(
            @RequestBody DatatableParameters params)
    {
        return personneService.afficherCompteGele(params);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<PersonneDto> afficherSelonQualite(
            @RequestBody DatatableParameters params)
    {
        return personneService.afficherSelonQualite(params);
    }
    @PostMapping("/datatable/list/nongele")
    public DataTablesResponse<PersonneDto> datatableListCompteNonGele(
            @RequestBody DatatableParameters params)
    {
        return personneService.afficherCompteNonGele(params);
    }
    @PutMapping("/{id}")
    public PersonneDto modifier(@PathVariable long id, @RequestBody PersonneDto personneDto){
        personneDto.setIdPersonne(id);
        return personneService.modifierPersonne(personneDto);
    }
}
