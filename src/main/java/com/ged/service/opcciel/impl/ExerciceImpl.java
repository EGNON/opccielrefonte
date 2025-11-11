package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.ExerciceDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.FormeJuridiqueDto;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.entity.opcciel.comptabilite.CleExercice;
import com.ged.entity.opcciel.comptabilite.Exercice;
import com.ged.entity.standard.FormeJuridique;
import com.ged.mapper.opcciel.ExerciceMapper;
import com.ged.projection.ExerciceProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.ExerciceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExerciceImpl implements ExerciceService {
    private final ExerciceDao exerciceDao;
    private final ExerciceMapper exerciceMapper;

    public ExerciceImpl(ExerciceDao exerciceDao, ExerciceMapper exerciceMapper) {
        this.exerciceDao = exerciceDao;
        this.exerciceMapper = exerciceMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(Long idOpcvm,DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.DESC,"codeExercice");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Exercice> exercicePage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
//                ObjectMapper mapper = new ObjectMapper();
//                System.out.println("PARAMS -> " + mapper.writeValueAsString(parameters.getSearch()));
                exercicePage = exerciceDao.exerciceOpcvm(parameters.getSearch().getValue(),idOpcvm, pageable);
            }
            else {
                exercicePage = exerciceDao.exerciceOpcvm(idOpcvm,pageable);
            }
            List<ExerciceDto> content = exercicePage.getContent().stream().map(exerciceMapper::deExercice).collect(Collectors.toList());
            DataTablesResponse<ExerciceDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)exercicePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)exercicePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des exercice par page datatable",
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
    public List<ExerciceDto> afficherTous() {
        return exerciceDao.afficherExercice().stream().map(exerciceMapper::deExerciceProjection).collect(Collectors.toList());
    }

    @Override
    public List<ExerciceDto> afficherParOPcvm(Long idOpcvm) throws SQLException {
        return exerciceDao.exerciceOpcvm(idOpcvm).stream().map(exerciceMapper::deExercice).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<Object> exerciceCourant(Long idOpcvm) {
        try {
            Exercice exercice = exerciceDao.exerciceCourant(idOpcvm);
            return ResponseHandler.generateResponse(
                    "Exercice courant",
                    HttpStatus.OK,
                    exercice);
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
    public ResponseEntity<Object> exerciceParOpcvmEtCode(Long idOpcvm, String code) {
        try {
            ExerciceDto exercice = exerciceMapper.deExercice(exerciceDao.exerciceOpcvmCode(idOpcvm,code));
            return ResponseHandler.generateResponse(
                    "Exercice opcvm code",
                    HttpStatus.OK,
                    exercice);
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
    public ResponseEntity<Object> exerciceEnCours(Long idOpcvm) {
        try {
            ExerciceProjection exercice = exerciceDao.exerciceEnCours(idOpcvm);

            return ResponseHandler.generateResponse(
                    "Exercice courant",
                    HttpStatus.OK,
                    exercice);
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
    public ResponseEntity<Object> creerExercice(ExerciceDto exerciceDto) {
        try {
            Exercice exercice = exerciceMapper.deExerciceDto(exerciceDto);
            Exercice exerciceSaved=exerciceDao.save(exercice);

            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès.",
                    HttpStatus.OK,
                    exerciceSaved);
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
    public ResponseEntity<Object> modifierExercice(ExerciceDto exerciceDto) {
        try {
            Exercice exercice = exerciceMapper.deExerciceDto(exerciceDto);
            Exercice exerciceSaved=exerciceDao.save(exercice);

            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès.",
                    HttpStatus.OK,
                    exerciceSaved);
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
    public ResponseEntity<Object> supprimerExercice(Long idOpcvm,String codeExercice) {
        try {
            exerciceDao.supprimer(idOpcvm, codeExercice);

            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès.",
                    HttpStatus.OK,
                    null);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
