package com.ged.controller.titresciel;

import com.ged.service.titresciel.FormeJuridiqueOpcService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping("/formejuridiqueopc")
public class FormeJuridiqueOpcController {
    private final FormeJuridiqueOpcService formeJuridiqueOpcService;

    public FormeJuridiqueOpcController(FormeJuridiqueOpcService formeJuridiqueOpcService) {
        this.formeJuridiqueOpcService = formeJuridiqueOpcService;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous() {
        return formeJuridiqueOpcService.afficherTous();
    }
}
