package com.ged.service.lab.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.lab.SanctionONUDao;
import com.ged.dto.lab.SanctionsONUDto;
import com.ged.entity.lab.SanctionsONU;
import com.ged.mapper.lab.SanctionONUMapper;
import com.ged.projection.SanctionsONUProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.lab.SanctionONUService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SanctionONUServiceImpl implements SanctionONUService {
    private final SanctionONUDao SanctionsONUDao;
    private final LibraryDao libraryDao;
    private final SanctionONUMapper SanctionsONUMapper;

    public SanctionONUServiceImpl(SanctionONUDao SanctionsONUDao, LibraryDao libraryDao, SanctionONUMapper SanctionsONUMapper) {
        this.SanctionsONUDao = SanctionsONUDao;
        this.libraryDao = libraryDao;
        this.SanctionsONUMapper = SanctionsONUMapper;
    }

   

    @Override
    public ResponseEntity<Object> listeSanctionONU(String[] noms) {
        SanctionsONUDao.deleteAll();
        for(String o:noms){

            SanctionsONU SanctionsONU = new SanctionsONU();
            SanctionsONU.setNom(o);
            SanctionsONU SanctionsONUSaved = SanctionsONUDao.save(SanctionsONU);
        }

        List<SanctionsONUProjection> list=libraryDao.listeSanctionOnu();
        return ResponseHandler.generateResponse(
                "Liste des personnes sanctionnées " ,
                HttpStatus.OK,
                list);
    }
}
