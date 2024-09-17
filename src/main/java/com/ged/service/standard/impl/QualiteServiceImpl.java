package com.ged.service.standard.impl;

import com.ged.dao.standard.QualiteDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.QualiteDto;
import com.ged.entity.standard.Qualite;
import com.ged.mapper.standard.QualiteMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.QualiteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QualiteServiceImpl implements QualiteService {
    private final QualiteDao qualiteDao;
    private final QualiteMapper qualiteMapper;

    public QualiteServiceImpl(QualiteDao qualiteDao, QualiteMapper qualiteMapper) {
        this.qualiteDao = qualiteDao;
        this.qualiteMapper = qualiteMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(Boolean phOrPm) {
        try {
            return ResponseHandler.generateResponse(
                    "Liste de toutes les qualités",
                    HttpStatus.OK,
                    phOrPm ?
                      qualiteDao.findAllByEstPHIsOrderByLibelleQualiteAsc(true).stream().map(qualiteMapper::deQualite).collect(Collectors.toList()) :
                      qualiteDao.findAllByEstPMIsOrderByLibelleQualiteAsc(true).stream().map(qualiteMapper::deQualite).collect(Collectors.toList()));
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public Boolean existByLibelle(String libelle) {
        return qualiteDao.existsByLibelleQualiteIgnoreCase(libelle).orElse(false);
    }

    @Override
    public DataTablesResponse<QualiteDto> afficherTous(DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());
        Page<Qualite> qualitePage = qualiteDao.findAll(pageable);

        List<QualiteDto> content = qualitePage.getContent().stream().map(qualiteMapper::deQualite).collect(Collectors.toList());
        DataTablesResponse<QualiteDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)qualitePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)qualitePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public ResponseEntity<Object> afficherTousPh(Long idPersonne) {
        try {
            return ResponseHandler.generateResponse(
                    "Liste de toutes les qualités",
                    HttpStatus.OK,
                    qualiteDao.findAllByEstPhExceptSomeQualities(idPersonne).stream().map(qualiteMapper::deQualite).collect(Collectors.toList()));
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherTousPm(Long idPersonne) {
        try {
            return ResponseHandler.generateResponse(
                    "Liste de toutes les qualités",
                    HttpStatus.OK,
                    qualiteDao.findAllByEstPmExceptSomeQualities(idPersonne).stream().map(qualiteMapper::deQualite).collect(Collectors.toList()));
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public Page<QualiteDto> afficherQualites(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleQualite");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Qualite> qualitePage=qualiteDao.findAll(pageRequest);
        return new PageImpl<>(qualitePage.getContent().stream().map(qualiteMapper::deQualite).collect(Collectors.toList()),pageRequest,qualitePage.getTotalElements());
    }

    @Override
    public QualiteDto afficherSelonLibelle(String keyword) {
        return qualiteMapper.deQualite(qualiteDao.findByLibelleQualiteContains(keyword));
    }

    @Override
    public QualiteDto rechercherQualiteParLibelle(String libelle) {
        Qualite qualite=qualiteDao.findByLibelleQualiteIgnoreCase(libelle).orElse(null);
        if(qualite!=null)
            return qualiteMapper.deQualite(qualite);
        else
            return null;
    }

    @Override
    public QualiteDto afficherQualite(long idQualite) {
        return qualiteMapper.deQualite(afficherQualiteSelonId(idQualite));
    }
    @Override
    public Qualite afficherQualiteSelonId(long idQualite) {
        return qualiteDao.findById(idQualite).orElseThrow(()-> new EntityNotFoundException("Qualité avec ID "+idQualite+" est introuvable"));
    }

    @Override
    public QualiteDto creerQualite(QualiteDto qualiteDto) {
        Qualite qualite=qualiteMapper.deQualiteDto(qualiteDto);
        Qualite qualiteSave=qualiteDao.save(qualite);
        return qualiteMapper.deQualite(qualiteSave);
    }

    @Override
    public QualiteDto modifierQualite(QualiteDto qualiteDto) {
        Qualite qualite=qualiteMapper.deQualiteDto(qualiteDto);
        Qualite qualitMaj=qualiteDao.save(qualite);
        return qualiteMapper.deQualite(qualitMaj);
    }

    @Override
    public void supprimerQualite(long idQualite) {
        qualiteDao.deleteById(idQualite);
    }
}
