package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.QualiteTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.QualiteTitreDto;
import com.ged.entity.titresciel.QualiteTitre;
import com.ged.mapper.titresciel.QualiteTitreMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.QualiteTitreService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QualiteTitreServiceImpl implements QualiteTitreService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final QualiteTitreDao QualiteTitreDao;
    private final QualiteTitreMapper QualiteTitreMapper;

    public QualiteTitreServiceImpl(QualiteTitreDao QualiteTitreDao, QualiteTitreMapper QualiteTitreMapper){
        this.QualiteTitreDao = QualiteTitreDao;

        this.QualiteTitreMapper = QualiteTitreMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleQualite");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<QualiteTitre> QualiteTitrePage;
            QualiteTitrePage = QualiteTitreDao.findAll(pageable);
            List<QualiteTitreDto> content = QualiteTitrePage.getContent().stream().map(QualiteTitreMapper::deQualiteTitre).collect(Collectors.toList());
            DataTablesResponse<QualiteTitreDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)QualiteTitrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)QualiteTitrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des QualiteTitres par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
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
    public Page<QualiteTitreDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleQualite");
            List<QualiteTitreDto> QualiteTitres = QualiteTitreDao.findAll(sort).stream().map(QualiteTitreMapper::deQualiteTitre).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des QualiteTitres",
                    HttpStatus.OK,
                    QualiteTitres);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }

    }

    @Override
    public QualiteTitre afficherSelonId(Long id) {
        return QualiteTitreDao.findById(id).orElseThrow(() -> new EntityNotFoundException(QualiteTitre.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "QualiteTitre dont ID = " + id,
                    HttpStatus.OK,
                    QualiteTitreMapper.deQualiteTitre(afficherSelonId(id)));
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }

    }

    @Override
    public ResponseEntity<Object> creer(QualiteTitreDto QualiteTitreDto) {
        try {
            QualiteTitre QualiteTitre = QualiteTitreMapper.deQualiteTitreDto(QualiteTitreDto);
            QualiteTitre = QualiteTitreDao.save(QualiteTitre);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    QualiteTitreMapper.deQualiteTitre(QualiteTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(QualiteTitreDto QualiteTitreDto) {
        try {
            if(!QualiteTitreDao.existsById(QualiteTitreDto.getIdQualite()))
                throw  new EntityNotFoundException(QualiteTitre.class, "ID", QualiteTitreDto.getIdQualite().toString());
            QualiteTitre QualiteTitre = QualiteTitreMapper.deQualiteTitreDto(QualiteTitreDto);
            QualiteTitre = QualiteTitreDao.save(QualiteTitre);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    QualiteTitreMapper.deQualiteTitre(QualiteTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long idQualite) {
        try {
            QualiteTitreDao.deleteById(idQualite);
            return ResponseHandler.generateResponse(
                    "Suppression effectuée avec succès",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
