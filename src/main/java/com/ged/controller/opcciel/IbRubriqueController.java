package com.ged.controller.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.IbRubriqueDto;
import com.ged.entity.opcciel.comptabilite.CleIbRubrique;
import com.ged.service.opcciel.IbRubriqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/ibrubriques")
public class IbRubriqueController {
    private final IbRubriqueService IbRubriqueService;

    public IbRubriqueController(IbRubriqueService IbRubriqueService) {
        this.IbRubriqueService = IbRubriqueService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return IbRubriqueService.afficherTous();
    }
    @GetMapping("/{codeIB}/{codeRubrique}")
    //@PreAuthorize("hasAuthority('ROLE_IbRubrique')")
    public ResponseEntity<Object> afficher(@PathVariable String codeIB,
                                           @PathVariable String codeRubrique)
    {
        CleIbRubrique idIbRubrique=new CleIbRubrique();
        idIbRubrique.setCodeIB(codeIB);
        idIbRubrique.setCodeRubrique(codeRubrique);
        return IbRubriqueService.afficher(idIbRubrique);
    }
    @PostMapping("/datatable/list")
    //@PreAuthorize("hasAuthority('ROLE_IbRubrique')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return IbRubriqueService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_IbRubrique')")
    public ResponseEntity<Object> creer(@Valid @RequestBody IbRubriqueDto IbRubriqueDto)
    {
        return IbRubriqueService.creer(IbRubriqueDto);
    }

    @PutMapping("/{codeIb}/{codeRubrique}")
  //  @PreAuthorize("hasAuthority('ROLE_IbRubrique')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody IbRubriqueDto IbRubriqueDto,
            @PathVariable String codeIb,
            @PathVariable String codeRubrique)
    {
        IbRubriqueDto.setCodeRubrique(codeRubrique);
        IbRubriqueDto.getIb().setCodeIB(codeIb);
        return IbRubriqueService.modifier(IbRubriqueDto);
    }

    @DeleteMapping("/{codeIb}/{codeRubrique}")
    public ResponseEntity<Object> supprimer(@PathVariable String codeIb,
                                            @PathVariable String codeRubrique)
    {
        CleIbRubrique idIbRubrique=new CleIbRubrique();
        idIbRubrique.setCodeIB(codeIb);
        idIbRubrique.setCodeRubrique(codeRubrique);
        return IbRubriqueService.supprimer(idIbRubrique);
    }
}
