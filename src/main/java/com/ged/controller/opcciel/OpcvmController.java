package com.ged.controller.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.service.opcciel.OpcvmService;
import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/opcvms")
public class OpcvmController {
    @Autowired
    ServletContext context;
    private final OpcvmService opcvmService;

    public OpcvmController(OpcvmService opcvmService) {
        this.opcvmService = opcvmService;
    }

    /*@PostConstruct
    public List<Object> createOpcvmFromOpcciel1() {
        return opcvmService.createOpcvmFromOpcciel1();
    }*/

    @GetMapping("/liste")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherListe()
    {
        return opcvmService.afficherListe();
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params)
    {
        return opcvmService.afficherTous(params);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_OPCVM')")
    public ResponseEntity<Object> afficher(@PathVariable("id") Long id) {
        return opcvmService.afficher(id);
    }

    @PostMapping
    public ResponseEntity<Object> creer(@RequestBody OpcvmDto opcvmDto) {
        return opcvmService.creer(opcvmDto);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> modifier(@RequestBody OpcvmDto opcvmDto) {
        return opcvmService.modifier(opcvmDto);
    }
}
