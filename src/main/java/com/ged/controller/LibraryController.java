package com.ged.controller;

import com.ged.dao.LibraryDao;
import com.ged.dto.request.OperationTransfertPartRequest;
import com.ged.dto.request.SoldeToutCompteRequest;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.service.AppService;
import jakarta.validation.Valid;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("libraries")
public class LibraryController {
    private final LibraryDao libraryDao;
    private final AppService service;

    public LibraryController(LibraryDao libraryDao, AppService service) {
        this.libraryDao = libraryDao;
        this.service = service;
    }

    @PostMapping("/{idActionnaire}/{idOpcvm}")
    public BigDecimal solde(
            @PathVariable("idActionnaire") Long idActionnaire,
            @PathVariable("idOpcvm") Long idOpcvm) {
        return libraryDao.solde(idActionnaire, idOpcvm);
    }

    @PostMapping("/{idOpcvm}")
    public SeanceOpcvm currentSeance(@PathVariable("idOpcvm") Long id) {
        return libraryDao.currentSeance(id);
    }

    @PostMapping("/opcvm/solde/tout/compte")
    public ResponseEntity<?> soldeToutCompte(@RequestBody @Valid SoldeToutCompteRequest request) {
        return service.soldeToutCompte(request);
    }

    @Order(1)
    @PostMapping("/registre/actionnaire/opcvm")
    public ResponseEntity<?> registreActionnaire(@RequestBody OperationTransfertPartRequest request) {
        return service.registreActionnaire(request);
    }

    @Order(2)
    @PostMapping("/cump/actionnaire/opcvm")
    public ResponseEntity<?> cumpActionnaire(@RequestBody OperationTransfertPartRequest request) {
        return service.cumpActionnaire(request);
    }
}
