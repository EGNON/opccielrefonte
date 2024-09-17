package com.ged.controller.standard.notification;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ModeleMsgAlerteDto;
import com.ged.service.standard.ModeleMsgAlerteService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/modelemsgalertes")
public class ModeleMsgAlerteController {
    private final ModeleMsgAlerteService modeleMsgAlerteService;

    public ModeleMsgAlerteController(ModeleMsgAlerteService ModeleMsgAlerteService) {
        this.modeleMsgAlerteService = ModeleMsgAlerteService;
    }

    @GetMapping
    public Page<ModeleMsgAlerteDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return modeleMsgAlerteService.afficherModeleMsgAlertes(page,size);
    }
    @GetMapping("/{idModele}")
    public ModeleMsgAlerteDto afficher(@PathVariable("idModele") long idModele) {
        return modeleMsgAlerteService.afficherModeleMsgAlerte(idModele);
    }
    @GetMapping("/defaut/{libelle}")
    public ModeleMsgAlerteDto afficherSelonTypeModeleEtDefaut(@PathVariable String libelle) {
        return modeleMsgAlerteService.afficherModeleMsgAlerteSelonTypeModeleEtDefaut(libelle);
    }
    @GetMapping("/typemodele/{libelle}")
    public List<ModeleMsgAlerteDto> afficherSelonTypeModele(@PathVariable String libelle) {
        return modeleMsgAlerteService.afficherModeleMsgAlerteSelonTypeModele(libelle);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<ModeleMsgAlerteDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<ModeleMsgAlerteDto> modeleMsgAlerteDtoPage = modeleMsgAlerteService.afficherModeleMsgAlertes(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength());
        List<ModeleMsgAlerteDto> content = modeleMsgAlerteDtoPage.getContent().stream().collect(Collectors.toList());
        DataTablesResponse<ModeleMsgAlerteDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) modeleMsgAlerteDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) modeleMsgAlerteDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PostMapping
    public ModeleMsgAlerteDto ajouter(@RequestBody ModeleMsgAlerteDto modeleMsgAlerteDto)
    {
        return modeleMsgAlerteService.creerModeleMsgAlerte(modeleMsgAlerteDto);
    }
    @PutMapping("/{id}")
    public ModeleMsgAlerteDto modifier(@PathVariable long id, @RequestBody ModeleMsgAlerteDto modeleMsgAlerteDto){
        modeleMsgAlerteDto.setIdModele(id);
        return modeleMsgAlerteService.modifierModeleMsgAlerte(modeleMsgAlerteDto);
    }
    @PutMapping("/defaut/{id}")
    public int modifier_Defaut(@PathVariable long id,@RequestBody ModeleMsgAlerteDto modeleMsgAlerteDto){
        return modeleMsgAlerteService.modifier(id);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        modeleMsgAlerteService.supprimerModeleMsgAlerte(id);
    }
}
