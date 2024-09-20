package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.JournalDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.TypeOperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.entity.opcciel.comptabilite.Journal;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.TypeOperation;
import com.ged.mapper.opcciel.NatureOperationMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.NatureOperationService;
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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NatureOperationServiceImpl implements NatureOperationService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final NatureOperationDao natureOperationDao;
    private final NatureOperationMapper natureOperationMapper;
    private final TypeOperationDao typeOperationDao;
    private final JournalDao journalDao;

    public NatureOperationServiceImpl(NatureOperationDao natureOperationDao, NatureOperationMapper natureOperationMapper, TypeOperationDao typeOperationDao, JournalDao journalDao){
        this.natureOperationDao = natureOperationDao;

        this.natureOperationMapper = natureOperationMapper;
        this.typeOperationDao = typeOperationDao;
        this.journalDao = journalDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleNatureOperation");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<NatureOperation> natureOperationPage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                natureOperationPage = natureOperationDao.rechercher(
                        parameters.getSearch().getValue(),
                        pageable);
            } else {
                natureOperationPage = natureOperationDao.findBySupprimer(false,pageable);
            }
            List<NatureOperationDto> content = natureOperationPage.getContent().stream().map(natureOperationMapper::deNatureOperation).collect(Collectors.toList());
            DataTablesResponse<NatureOperationDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)natureOperationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)natureOperationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des natures opérations par page datatable",
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
    public Page<NatureOperationDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleNatureOperation");
            List<NatureOperationDto> natureOperations = natureOperationDao.findBySupprimerOrderByLibelleNatureOperationAsc(false).stream().map(natureOperationMapper::deNatureOperation).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des natures opérations ",
                    HttpStatus.OK,
                    natureOperations);
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
    public NatureOperation afficherSelonId(String codeNatureOperation) {
        return natureOperationDao.findById(codeNatureOperation).orElseThrow(() -> new EntityNotFoundException(NatureOperation.class, "code",codeNatureOperation));
    }

    @Override
    public ResponseEntity<Object>afficherSelonTypeOperation(String codeTypeOperation) {
        try {
            TypeOperation typeOperation=typeOperationDao.findById(codeTypeOperation).orElseThrow();
            return ResponseHandler.generateResponse(
                    "Liste des natures opérations selon type opération= " + codeTypeOperation,
                    HttpStatus.OK,
                    natureOperationDao.findByTypeOperationAndSupprimer(typeOperation,false).stream().map(natureOperationMapper::deNatureOperation).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficher(String codeNatureOperation) {
        try {
            return ResponseHandler.generateResponse(
                    "NatureOperation dont CODE = " + codeNatureOperation,
                    HttpStatus.OK,
                    natureOperationMapper.deNatureOperation(afficherSelonId(codeNatureOperation)));
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
    public ResponseEntity<Object> creer(NatureOperationDto natureOperationDto) {
        try {
            natureOperationDto.setSupprimer(false);
            NatureOperation natureOperation = natureOperationMapper.deNatureOperationDto(natureOperationDto);
            Journal journal=new Journal();
            TypeOperation typeOperation=new TypeOperation();
            if(natureOperationDto.getJournal()!=null){
                journal=journalDao.findById(natureOperationDto.getJournal().getCodeJournal()).orElseThrow();
            }
            if(natureOperationDto.getTypeOperation()!=null){
                typeOperation=typeOperationDao.findById(natureOperationDto.getTypeOperation().getCodeTypeOperation()).orElseThrow();
            }
            natureOperation.setTypeOperation(typeOperation);
            natureOperation.setJournal(journal);
            natureOperation = natureOperationDao.save(natureOperation);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    natureOperationMapper.deNatureOperation(natureOperation));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(NatureOperationDto natureOperationDto) {
        try {
            if(!natureOperationDao.existsById(natureOperationDto.getCodeNatureOperation()))
                throw  new EntityNotFoundException(NatureOperation.class, "code", natureOperationDto.getCodeNatureOperation().toString());
            natureOperationDto.setSupprimer(false);
            NatureOperation natureOperation = natureOperationMapper.deNatureOperationDto(natureOperationDto);
            Journal journal=new Journal();
            TypeOperation typeOperation=new TypeOperation();
            if(natureOperationDto.getJournal()!=null){
                journal=journalDao.findById(natureOperationDto.getJournal().getCodeJournal()).orElseThrow();
            }
            if(natureOperationDto.getTypeOperation()!=null){
                typeOperation=typeOperationDao.findById(natureOperationDto.getTypeOperation().getCodeTypeOperation()).orElseThrow();
            }
            natureOperation.setTypeOperation(typeOperation);
            natureOperation.setJournal(journal);
            natureOperation = natureOperationDao.save(natureOperation);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    natureOperationMapper.deNatureOperation(natureOperation));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String codeNatureOperation) {
        try {
            natureOperationDao.deleteById(codeNatureOperation);
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
