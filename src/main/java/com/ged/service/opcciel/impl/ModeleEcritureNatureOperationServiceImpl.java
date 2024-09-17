package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.ModeleEcritureDao;
import com.ged.dao.opcciel.comptabilite.ModeleEcritureNatureOperationDao;
import com.ged.dao.titresciel.TypeTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureNatureOperationDto;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.ModeleEcriture;
import com.ged.entity.opcciel.comptabilite.ModeleEcritureNatureOperation;
import com.ged.entity.titresciel.TypeTitre;
import com.ged.mapper.opcciel.ModeleEcritureNatureOperationMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.ModeleEcritureNatureOperationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModeleEcritureNatureOperationServiceImpl implements ModeleEcritureNatureOperationService {
    private final ModeleEcritureNatureOperationDao ModeleEcritureNatureOperationDao;
    private final ModeleEcritureDao modeleEcritureDao;
    private final NatureOperationDao NatureOperationDao;
    private final TypeTitreDao typeTitreDao;
    private final ModeleEcritureNatureOperationMapper ModeleEcritureNatureOperationMapper;

    public ModeleEcritureNatureOperationServiceImpl(ModeleEcritureNatureOperationDao ModeleEcritureNatureOperationDao, ModeleEcritureDao modeleEcritureDao, NatureOperationDao NatureOperationDao, TypeTitreDao typeTitreDao, ModeleEcritureNatureOperationMapper ModeleEcritureNatureOperationMapper) {
        this.ModeleEcritureNatureOperationDao = ModeleEcritureNatureOperationDao;
        this.modeleEcritureDao = modeleEcritureDao;
        this.NatureOperationDao = NatureOperationDao;
        this.typeTitreDao = typeTitreDao;
        this.ModeleEcritureNatureOperationMapper = ModeleEcritureNatureOperationMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCompteComptable");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ModeleEcritureNatureOperation> ModeleEcritureNatureOperationPage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                ModeleEcritureNatureOperationPage = ModeleEcritureNatureOperationDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                ModeleEcritureNatureOperationPage = ModeleEcritureNatureOperationDao.findAll(pageable);
            }
            List<ModeleEcritureNatureOperationDto> content = ModeleEcritureNatureOperationPage.getContent().stream().map(ModeleEcritureNatureOperationMapper::deModeleEcritureNatureOperation).collect(Collectors.toList());
            DataTablesResponse<ModeleEcritureNatureOperationDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)ModeleEcritureNatureOperationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)ModeleEcritureNatureOperationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des modèles natures operations par page datatable",
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
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"numModeleEcritureNatureOperation");
            return ResponseHandler.generateResponse(
                    "Liste de tous les modèles NatureOperations",
                    HttpStatus.OK,
                    ModeleEcritureNatureOperationDao.findAll().stream().map(ModeleEcritureNatureOperationMapper::deModeleEcritureNatureOperation).collect(Collectors.toList()));
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
    public ModeleEcritureNatureOperation afficherSelonId(Long idModeleEcritureNatureOperation) {
        return ModeleEcritureNatureOperationDao.findById(idModeleEcritureNatureOperation).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(Long codeModeleEcritureNatureOperation) {
        try {
            return ResponseHandler.generateResponse(
                    "Modele nature operation dont id = " + codeModeleEcritureNatureOperation,
                    HttpStatus.OK,
                    ModeleEcritureNatureOperationMapper.deModeleEcritureNatureOperation(afficherSelonId(codeModeleEcritureNatureOperation)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    @Override
    public ResponseEntity<Object> creer(ModeleEcritureNatureOperationDto ModeleEcritureNatureOperationDto) {
        try {
            ModeleEcritureNatureOperationDto.setSupprimer(false);
            ModeleEcritureNatureOperation ModeleEcritureNatureOperation = ModeleEcritureNatureOperationMapper.deModeleEcritureNatureOperationDto(ModeleEcritureNatureOperationDto);
            ModeleEcriture modeleEcriture=new ModeleEcriture();
            NatureOperation NatureOperation=new NatureOperation();
            TypeTitre typeTitre=new TypeTitre();
            if(ModeleEcritureNatureOperationDto.getModeleEcriture()!=null){
                modeleEcriture=modeleEcritureDao.findById(ModeleEcritureNatureOperationDto.getModeleEcriture().getCodeModeleEcriture()).orElseThrow();
                ModeleEcritureNatureOperation.setModeleEcriture(modeleEcriture);
            }
            if(ModeleEcritureNatureOperationDto.getNatureOperation()!=null){
                NatureOperation=NatureOperationDao.findById(ModeleEcritureNatureOperationDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
                ModeleEcritureNatureOperation.setNatureOperation(NatureOperation);
            }
            /*if(ModeleEcritureNatureOperationDto.getTypeTitre()!=null){
                typeTitre=typeTitreDao.findById(ModeleEcritureNatureOperationDto.getTypeTitre().getCodeTypeTitre()).orElseThrow();
                ModeleEcritureNatureOperation.setTypeTitre(typeTitre);
            }*/
            ModeleEcritureNatureOperation = ModeleEcritureNatureOperationDao.save(ModeleEcritureNatureOperation);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    ModeleEcritureNatureOperationMapper.deModeleEcritureNatureOperation(ModeleEcritureNatureOperation));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ModeleEcritureNatureOperationDto ModeleEcritureNatureOperationDto) {
        try {
            ModeleEcritureNatureOperationDto.setSupprimer(false);
            ModeleEcritureNatureOperation ModeleEcritureNatureOperation = ModeleEcritureNatureOperationMapper.deModeleEcritureNatureOperationDto(ModeleEcritureNatureOperationDto);
            ModeleEcriture modeleEcriture=new ModeleEcriture();
            NatureOperation NatureOperation=new NatureOperation();
            TypeTitre typeTitre=new TypeTitre();
            if(ModeleEcritureNatureOperationDto.getModeleEcriture()!=null){
                modeleEcriture=modeleEcritureDao.findById(ModeleEcritureNatureOperationDto.getModeleEcriture().getCodeModeleEcriture()).orElseThrow();
                ModeleEcritureNatureOperation.setModeleEcriture(modeleEcriture);
            }
            if(ModeleEcritureNatureOperationDto.getNatureOperation()!=null){
                NatureOperation=NatureOperationDao.findById(ModeleEcritureNatureOperationDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
                ModeleEcritureNatureOperation.setNatureOperation(NatureOperation);
            }
            /*if(ModeleEcritureNatureOperationDto.getTypeTitre()!=null){
                typeTitre=typeTitreDao.findById(ModeleEcritureNatureOperationDto.getTypeTitre().getCodeTypeTitre()).orElseThrow();
                ModeleEcritureNatureOperation.setTypeTitre(typeTitre);
            }*/
            ModeleEcritureNatureOperation = ModeleEcritureNatureOperationDao.save(ModeleEcritureNatureOperation);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    ModeleEcritureNatureOperationMapper.deModeleEcritureNatureOperation(ModeleEcritureNatureOperation));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long codeModeleEcritureNatureOperation) {
        try {
            ModeleEcritureNatureOperationDao.deleteById(codeModeleEcritureNatureOperation);
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
