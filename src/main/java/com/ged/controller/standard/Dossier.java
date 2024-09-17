package com.ged.controller.standard;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/dossiers")
@Component
public class Dossier {
    @Value("${file.upload-dir}")
    private String chemin;
    @GetMapping
    public String retournerChemin(){
        return chemin;
    }
}
