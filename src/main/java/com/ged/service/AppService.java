package com.ged.service;

import com.ged.dao.LibraryDao;
import com.ged.entity.opcciel.SeanceOpcvm;
import org.springframework.stereotype.Service;

@Service
public class AppService {
    private final LibraryDao libraryDao;

    public AppService(LibraryDao libraryDao) {
        this.libraryDao = libraryDao;
    }

    public SeanceOpcvm currentSeance(Long idOpcvm) {
        return libraryDao.currentSeance(idOpcvm);
    }
}
