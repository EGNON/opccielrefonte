package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TableauAmortissementDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TableauAmortissementDto;
import com.ged.entity.titresciel.CleTableauAmortissement;
import com.ged.entity.titresciel.TableauAmortissement;
import com.ged.mapper.titresciel.TableauAmortissementMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.TableauAmortissementService;
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
public class TableauAmortissementServiceImpl implements TableauAmortissementService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final TableauAmortissementDao TableauAmortissementDao;
    private final TableauAmortissementMapper TableauAmortissementMapper;

    public TableauAmortissementServiceImpl(TableauAmortissementDao TableauAmortissementDao, TableauAmortissementMapper TableauAmortissementMapper){
        this.TableauAmortissementDao = TableauAmortissementDao;

        this.TableauAmortissementMapper = TableauAmortissementMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomTableauAmortissement");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<TableauAmortissement> TableauAmortissementPage;
            TableauAmortissementPage = TableauAmortissementDao.findAll(pageable);
            List<TableauAmortissementDto> content = TableauAmortissementPage.getContent().stream().map(TableauAmortissementMapper::deTableauAmortissement).collect(Collectors.toList());
            DataTablesResponse<TableauAmortissementDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)TableauAmortissementPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)TableauAmortissementPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des TableauAmortissements par page datatable",
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
    public Page<TableauAmortissementDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomTableauAmortissement");
            List<TableauAmortissementDto> TableauAmortissements = TableauAmortissementDao.findAll().stream().map(TableauAmortissementMapper::deTableauAmortissement).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des TableauAmortissements",
                    HttpStatus.OK,
                    TableauAmortissements);
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
    public TableauAmortissement afficherSelonId(CleTableauAmortissement id) {
        return TableauAmortissementDao.findById(id).orElseThrow(() -> new EntityNotFoundException(TableauAmortissement.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(CleTableauAmortissement id) {
        try {
            return ResponseHandler.generateResponse(
                    "TableauAmortissement dont ID = " + id,
                    HttpStatus.OK,
                    TableauAmortissementMapper.deTableauAmortissement(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(TableauAmortissementDto TableauAmortissementDto) {
        try {
            TableauAmortissement TableauAmortissement = TableauAmortissementMapper.deTableauAmortissementDto(TableauAmortissementDto);
            CleTableauAmortissement cleTableauAmortissement=new CleTableauAmortissement();
            cleTableauAmortissement.setIdTitre(TableauAmortissementDto.getTitre().getIdTitre());
            cleTableauAmortissement.setDateEcheance(TableauAmortissementDto.getDateEcheance());
            TableauAmortissement.setIdTabAmortissement(cleTableauAmortissement);
            
            TableauAmortissement = TableauAmortissementDao.save(TableauAmortissement);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    TableauAmortissementMapper.deTableauAmortissement(TableauAmortissement));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TableauAmortissementDto TableauAmortissementDto) {
        try {
            TableauAmortissement TableauAmortissement = TableauAmortissementMapper.deTableauAmortissementDto(TableauAmortissementDto);
            CleTableauAmortissement cleTableauAmortissement=new CleTableauAmortissement();
            cleTableauAmortissement.setIdTitre(TableauAmortissementDto.getTitre().getIdTitre());
            cleTableauAmortissement.setDateEcheance(TableauAmortissementDto.getDateEcheance());
            TableauAmortissement.setIdTabAmortissement(cleTableauAmortissement);
            TableauAmortissement = TableauAmortissementDao.save(TableauAmortissement);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    TableauAmortissementMapper.deTableauAmortissement(TableauAmortissement));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleTableauAmortissement id) {
        try {
            TableauAmortissementDao.deleteById(id);
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
