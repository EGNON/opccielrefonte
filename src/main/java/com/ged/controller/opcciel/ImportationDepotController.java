package com.ged.controller.opcciel;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/importdepots")
public class ImportationDepotController {
    @GetMapping
    public void importationDepot() {

    }
}
