package com.ged.controller.standard.notification;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.AlerteDto;
import com.ged.service.standard.AlerteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/alertes")
public class AlerteController {
    private final AlerteService alerteService;
    //construteur
    public AlerteController(AlerteService alerteService) {
        this.alerteService = alerteService;
    }

    @GetMapping
    public Page<AlerteDto> ajouter(@RequestParam(value = "page", defaultValue = "0") int page,
                                   @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return alerteService.afficherAlertes(page, size);
    }
    @GetMapping("/{id}")
    public AlerteDto afficher(@PathVariable("id") Long id)
    {
        return alerteService.afficherAlerte(id);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<AlerteDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<AlerteDto> alerteDtoPage = alerteService.afficherAlertes(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength());
        List<AlerteDto> content = alerteDtoPage.getContent().stream().collect(Collectors.toList());
        DataTablesResponse<AlerteDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) alerteDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) alerteDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PostMapping
    public AlerteDto ajouter(@Valid @RequestBody AlerteDto alerteDto)
    {
        return alerteService.creerAlerte(alerteDto);
    }

    @PutMapping("/{id}")
    public AlerteDto modifier(@Valid @RequestBody AlerteDto alerteDto,
                              @Positive @PathVariable("id") Long id)
    {
        alerteDto.setIdAlerte(id);
        return alerteService.modifierAlerte(alerteDto);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@Positive @PathVariable("id") Long id)
    {
        alerteService.supprimerAlerte(id);
    }
}
