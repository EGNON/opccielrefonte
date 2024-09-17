package com.ged.controller.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.IbRubriquePositionDto;
import com.ged.entity.opcciel.comptabilite.CleIbRubriquePosition;
import com.ged.service.opcciel.IbRubriquePositionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ibrubriquepositions")
public class IbRubriquePositionController {
    private final IbRubriquePositionService IbRubriquePositionService;

    public IbRubriquePositionController(IbRubriquePositionService IbRubriquePositionService) {
        this.IbRubriquePositionService = IbRubriquePositionService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return IbRubriquePositionService.afficherTous();
    }
    @GetMapping("/{codeIB}/{codeRubrique}/{codePosition}")
    //@PreAuthorize("hasAuthority('ROLE_IbRubriquePosition')")
    public ResponseEntity<Object> afficher(@PathVariable String codeIB,
                                           @PathVariable String codeRubrique,
                                           @PathVariable String codePosition)
    {
        CleIbRubriquePosition idIbRubriquePosition=new CleIbRubriquePosition();
        idIbRubriquePosition.setCodeIB(codeIB);
        idIbRubriquePosition.setCodeRubrique(codeRubrique);
        idIbRubriquePosition.setCodePosition(codePosition);
        return IbRubriquePositionService.afficher(idIbRubriquePosition);
    }
    @PostMapping("/datatable/list")
    //@PreAuthorize("hasAuthority('ROLE_IbRubriquePosition')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return IbRubriquePositionService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_IbRubriquePosition')")
    public ResponseEntity<Object> creer(@Valid @RequestBody IbRubriquePositionDto IbRubriquePositionDto)
    {
        return IbRubriquePositionService.creer(IbRubriquePositionDto);
    }

    @PutMapping("/{codeIb}/{codeRubrique}/{codePosition}")
  //  @PreAuthorize("hasAuthority('ROLE_IbRubriquePosition')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody IbRubriquePositionDto IbRubriquePositionDto,
            @PathVariable String codeIb,
            @PathVariable String codeRubrique,
            @PathVariable String codePosition)
    {
        IbRubriquePositionDto.setCodeRubrique(codeRubrique);
        IbRubriquePositionDto.setCodePosition(codePosition);
        IbRubriquePositionDto.getIb().setCodeIB(codeIb);
        return IbRubriquePositionService.modifier(IbRubriquePositionDto);
    }

    @DeleteMapping("/{codeIb}/{codeRubrique}/{codePosition}")
    public ResponseEntity<Object> supprimer(@PathVariable String codeIb,
                                            @PathVariable String codeRubrique,
                                            @PathVariable String codePosition)
    {
        CleIbRubriquePosition idIbRubriquePosition=new CleIbRubriquePosition();
        idIbRubriquePosition.setCodeIB(codeIb);
        idIbRubriquePosition.setCodeRubrique(codeRubrique);
        idIbRubriquePosition.setCodePosition(codePosition);
        return IbRubriquePositionService.supprimer(idIbRubriquePosition);
    }
}
