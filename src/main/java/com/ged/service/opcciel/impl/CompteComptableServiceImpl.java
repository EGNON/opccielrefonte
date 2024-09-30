package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.CompteComptableDao;
import com.ged.dao.opcciel.comptabilite.PlanDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.CompteComptableDto;
import com.ged.entity.opcciel.comptabilite.CleCompteComptable;
import com.ged.entity.opcciel.comptabilite.CompteComptable;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.mapper.opcciel.CompteComptableMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.CompteComptableService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CompteComptableServiceImpl implements CompteComptableService {
    private final CompteComptableDao compteComptableDao;
    private final PlanDao planDao;
    private final CompteComptableMapper compteComptableMapper;

    public CompteComptableServiceImpl(CompteComptableDao compteComptableDao, PlanDao planDao, CompteComptableMapper compteComptableMapper) {
        this.compteComptableDao = compteComptableDao;
        this.planDao = planDao;
        this.compteComptableMapper = compteComptableMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCompteComptable");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<CompteComptable> compteComptablePage = null;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                compteComptablePage = compteComptableDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                compteComptablePage = compteComptableDao.findBySupprimerOrderByNumCompteComptableAsc(false,pageable);
            }
            List<CompteComptableDto> content = compteComptablePage.getContent().stream().map(compteComptableMapper::deCompteComptable).collect(Collectors.toList());
            DataTablesResponse<CompteComptableDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)compteComptablePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)compteComptablePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des comptes comptables par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"numCompteComptable");
            return ResponseHandler.generateResponse(
                    "Liste de tous les comptes comptables",
                    HttpStatus.OK,
                    compteComptableDao.findBySupprimerOrderByNumCompteComptableAsc(false).stream().map(compteComptableMapper::deCompteComptable).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficherSelonNumCompteComptable(String numCompteComptable) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"numCompteComptable");
            return ResponseHandler.generateResponse(
                    "compte comptable dont numero="+numCompteComptable,
                    HttpStatus.OK,
                   compteComptableMapper.deCompteComptable(compteComptableDao.findByNumCompteComptable(numCompteComptable)));
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
    public ResponseEntity<Object> afficherSelonPlan(DatatableParameters parameters,String codePlan) {
        try {
            //Sort sort=Sort.by(Sort.Direction.ASC,"numCompteComptable");
            Plan plan=planDao.findById(codePlan).orElseThrow();
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<CompteComptable> compteComptablePage;
             if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
              compteComptablePage = compteComptableDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
            compteComptablePage = compteComptableDao.findByPlanAndSupprimerOrderByNumCompteComptableAsc(plan,false,pageable);
            }
            List<CompteComptableDto> content = compteComptablePage.getContent().stream().map(compteComptableMapper::deCompteComptable).collect(Collectors.toList());
            DataTablesResponse<CompteComptableDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)compteComptablePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)compteComptablePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des comptes comptables selon plan",
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
    public ResponseEntity<Object> afficherSelonPlan(String codePlan) {
        try {
            Plan plan=planDao.findById(codePlan).orElseThrow();
            return ResponseHandler.generateResponse(
                    "Compte Comptable selon plan = " + codePlan,
                    HttpStatus.OK,
                    compteComptableDao.findByPlanAndSupprimerOrderByNumCompteComptableAsc(plan,false).stream().map(compteComptableMapper::deCompteComptable).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficherSelonPlanEtEstMvt(String codePlan, boolean estMvt) {
        try {
            Plan plan=planDao.findById(codePlan).orElseThrow();
            return ResponseHandler.generateResponse(
                    "Compte Comptable selon plan = " + codePlan,
                    HttpStatus.OK,
                    compteComptableDao.findByPlanAndEstMvtAndSupprimerOrderByNumCompteComptableAsc(plan,estMvt,false).stream().map(compteComptableMapper::deCompteComptable).collect(Collectors.toList()));

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
    public CompteComptable afficherSelonId(CleCompteComptable idCompteComptable) {
        return compteComptableDao.findById(idCompteComptable).orElseThrow(() -> new EntityNotFoundException(CompteComptable.class, "code", idCompteComptable.getNumCompteComptable()+";"+idCompteComptable.getCodePlan()));
    }

    @Override
    public ResponseEntity<Object> afficher(CleCompteComptable codeCompteComptable) {
        try {
            return ResponseHandler.generateResponse(
                    "Compte Comptable dont id = " + codeCompteComptable,
                    HttpStatus.OK,
                    compteComptableMapper.deCompteComptable(afficherSelonId(codeCompteComptable)));
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
    public ResponseEntity<Object> creer(CompteComptableDto compteComptableDto) {
        try {
            CompteComptable compteComptable = compteComptableMapper.deCompteComptableDto(compteComptableDto);
            CleCompteComptable cleCompteComptable=new CleCompteComptable();
            cleCompteComptable.setNumCompteComptable(compteComptableDto.getNumCompteComptable());
            cleCompteComptable.setCodePlan(compteComptableDto.getPlan().getCodePlan());
            compteComptable.setIdCompteComptable(cleCompteComptable);
            if(compteComptableDto.getPlan()!=null) {
                Plan plan = planDao.findById(compteComptableDto.getPlan().getCodePlan()).orElseThrow();
                compteComptable.setPlan(plan);
            }
            compteComptable.setSupprimer(false);
            compteComptable = compteComptableDao.save(compteComptable);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    compteComptableMapper.deCompteComptable(compteComptable));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(CompteComptableDto compteComptableDto) {
        try {
            CompteComptable compteComptable = compteComptableMapper.deCompteComptableDto(compteComptableDto);

            CleCompteComptable cleCompteComptable=new CleCompteComptable();
            cleCompteComptable.setNumCompteComptable(compteComptableDto.getNumCompteComptable());
            cleCompteComptable.setCodePlan(compteComptableDto.getPlan().getCodePlan());
            compteComptable.setIdCompteComptable(cleCompteComptable);
            compteComptable.setIdCompteComptable(cleCompteComptable);
            compteComptable.setSupprimer(false);
            if(compteComptableDto.getPlan()!=null) {
                Plan plan = planDao.findById(compteComptableDto.getPlan().getCodePlan()).orElseThrow();
                compteComptable.setPlan(plan);
            }
            compteComptable = compteComptableDao.save(compteComptable);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    compteComptableMapper.deCompteComptable(compteComptable));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleCompteComptable codeCompteComptable) {
        try {
            compteComptableDao.deleteById(codeCompteComptable);
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
