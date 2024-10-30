package com.ged.controller;

import com.ged.dao.LibraryDao;
import com.ged.entity.opcciel.SeanceOpcvm;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/libraries")
public class LibraryController {
    private final LibraryDao libraryDao;

    public LibraryController(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
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
}
