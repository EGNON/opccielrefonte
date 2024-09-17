package com.ged.controller.lab;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.CritereAlerteDto;
import com.ged.service.lab.CritereAlerteService;
import com.ged.service.lab.TransactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/criterealertes")
public class CritereAlerteController {
    private final CritereAlerteService critereAlerteService;
    private final TransactionService transactionService;

    public CritereAlerteController(CritereAlerteService critereAlerteService, TransactionService transactionService) {
        this.critereAlerteService = critereAlerteService;
        this.transactionService = transactionService;
    }

//    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_CritereAlerte')")
//    public ResponseEntity<Object> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
//                                      @RequestParam(value = "size", defaultValue = "10") int size)
//    {
//        return CritereAlerteService.afficherCritereAlertes(page, size);
//    }
    @GetMapping("/liste")
    public List<CritereAlerteDto> afficherListe(){
        return critereAlerteService.afficherListe();
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return critereAlerteService.afficherTous();
    }

    @PostMapping("/datatable")
    public ResponseEntity<Object> afficherTransaction(@RequestBody DatatableParameters params,
                                                      @RequestParam long critere)  {
//        String url =  critere;//"http://localhost:8080/example2?param1=test1&test2";
//        System.out.println(url);
//        RestTemplate restTemplate = new RestTemplate();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
//        map.add("value1","test1&test2");
//
//        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
//        System.out.println(restTemplate.postForObject(url, request, String.class));
//        return transactionService.afficherTous(params,restTemplate.postForObject(url, request, String.class));
//        critere= URLDecoder.decode(critere, "UTF-8");
//        critere=critere.replaceAll("%27","'").replaceAll("%20"," ").
//                replaceAll("%3E",">");
//        System.out.println(critere);
        return transactionService.afficherTous(params,critere);
    }

    @GetMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_CritereAlerte')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return critereAlerteService.afficherCritereAlerte(id);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_CritereAlerte')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) throws JsonProcessingException {
        return critereAlerteService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_CritereAlerte')")
    public ResponseEntity<Object> ajouter(@Valid @RequestBody CritereAlerteDto CritereAlerteDto)
    {
        return critereAlerteService.creerCritereAlerte(CritereAlerteDto);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_CritereAlerte')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody CritereAlerteDto CritereAlerteDto, @Positive @PathVariable("id") Long id)
    {
        CritereAlerteDto.setIdCritereAlerte(id);
        return critereAlerteService.modifierCritereAlerte(CritereAlerteDto);
    }

    @DeleteMapping("/{id}")
//    @PreAuthorize("hasAuthority('ROLE_CritereAlerte')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable("id") Long id)
    {
        return critereAlerteService.supprimerCritereAlerte(id);
    }
}
