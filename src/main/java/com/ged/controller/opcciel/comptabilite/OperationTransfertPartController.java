package com.ged.controller.opcciel.comptabilite;

import com.ged.dto.request.TransfertPartAddRequest;
import com.ged.dto.request.TransfertPartListeRequest;
import com.ged.service.opcciel.OperationTransfertPartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("transfertparts")
public class OperationTransfertPartController {
    private final OperationTransfertPartService service;

    @PostMapping("/datatable/list")
    public ResponseEntity<?> afficherTous(@RequestBody TransfertPartListeRequest request) {
        return service.afficherTous(request);
    }

    @PostMapping("/transfert/parts/save")
    public ResponseEntity<?> creerTransfert(@RequestBody @Valid TransfertPartAddRequest request) {
        return service.creerTransfert(request);
    }
}
