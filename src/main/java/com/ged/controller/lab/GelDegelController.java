package com.ged.controller.lab;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.GelDegelDto;
import com.ged.mapper.lab.GelDegelMapper;
import com.ged.service.lab.GelDegelService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/geldegels")
public class GelDegelController {
    private final GelDegelService gelDegelService;
    private final GelDegelMapper gelDegelMapper;

    public GelDegelController(GelDegelService gelDegelService, GelDegelMapper gelDegelMapper) {
        this.gelDegelService = gelDegelService;
        this.gelDegelMapper = gelDegelMapper;
    }

    @GetMapping
    public Page<GelDegelDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                          @RequestParam(value = "size", defaultValue = "0") int size)
    {
        return gelDegelService.afficherGelDegels(page, size);
    }
    @GetMapping("/{id}")
    public GelDegelDto afficherSelonId(@PathVariable long id)
    {
        return gelDegelMapper.deGelDegel(gelDegelService.afficherGelDegelSelonId(id));
    }
    @GetMapping("/estGele/{id}")
    public GelDegelDto afficherSelonEstGeleEtPersonne(@PathVariable long id)
    {
        return gelDegelMapper.deGelDegel(gelDegelService.afficherGelDegelSelonEstGeleEtPersonne(true,id));
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<GelDegelDto> datatableList(
            @RequestBody DatatableParameters params)
    {
        return gelDegelService.afficherGelDegels(params);
    }
    @PostMapping
    public GelDegelDto ajouter(@RequestBody GelDegelDto gelDegelDto)
    {
        return gelDegelService.creerGelDegel(gelDegelDto);
    }

    @PutMapping("/{id}")
    public GelDegelDto modifier(@RequestBody GelDegelDto gelDegelDto, @PathVariable("id") Long id)
    {
        gelDegelDto.setIdGelDegel(id);
        return gelDegelService.modifierGelDegel(gelDegelDto);
    }
    @PutMapping("/estgele/{id}")
    public int modifier(@PathVariable("id") Long id)
    {
        LocalDateTime dateServeur=LocalDateTime.now();
        return gelDegelService.modifierGelDegel(id,dateServeur);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable("id") Long id)
    {
        gelDegelService.supprimerGelDegel(id);
    }
}
