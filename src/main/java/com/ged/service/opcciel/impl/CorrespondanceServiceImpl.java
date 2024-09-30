package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.CorrespondanceDao;
import com.ged.dao.opcciel.comptabilite.IbDao;
import com.ged.dao.opcciel.comptabilite.PlanDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.CorrespondanceDto;
import com.ged.entity.opcciel.comptabilite.CleCorrespondance;
import com.ged.entity.opcciel.comptabilite.Correspondance;
import com.ged.entity.opcciel.comptabilite.Ib;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.mapper.opcciel.CorrespondanceMapper;
import com.ged.projection.CorrespondanceProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.CorrespondanceService;
import org.apache.commons.lang3.StringUtils;
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
public class CorrespondanceServiceImpl implements CorrespondanceService {
    private final CorrespondanceDao correspondanceDao;
    private final PlanDao planDao;
    private final IbDao ibDao;
    private final CorrespondanceMapper correspondanceMapper;

    public CorrespondanceServiceImpl(CorrespondanceDao correspondanceDao, PlanDao planDao, IbDao ibDao, CorrespondanceMapper correspondanceMapper) {
        this.correspondanceDao = correspondanceDao;
        this.planDao = planDao;
        this.ibDao = ibDao;
        this.correspondanceMapper = correspondanceMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCorrespondance");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<CorrespondanceProjection> correspondancePage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                correspondancePage = correspondanceDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                correspondancePage = correspondanceDao.afficherCorrespondance(pageable);
            }
            List<CorrespondanceDto> content = correspondancePage.getContent().stream().map(correspondanceMapper::deCorrespondanceProjection).collect(Collectors.toList());
            DataTablesResponse<CorrespondanceDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)correspondancePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)correspondancePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des correspondances par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"numCorrespondance");
            return ResponseHandler.generateResponse(
                    "Liste de tous les comptes comptables",
                    HttpStatus.OK,
                    correspondanceDao.findAll().stream().map(correspondanceMapper::deCorrespondance).collect(Collectors.toList()));
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
    public Correspondance afficherSelonId(CleCorrespondance idCorrespondance) {
        return correspondanceDao.findById(idCorrespondance).orElseThrow(() -> new EntityNotFoundException(Correspondance.class, "code", idCorrespondance.getNumeroCompteComptable()+";"+idCorrespondance.getCodePlan()));
    }

    @Override
    public ResponseEntity<Object> afficher(CleCorrespondance codeCorrespondance) {
        try {
            return ResponseHandler.generateResponse(
                    "Correspondance  dont id = " + codeCorrespondance,
                    HttpStatus.OK,
                    correspondanceMapper.deCorrespondance(afficherSelonId(codeCorrespondance)));
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
    public ResponseEntity<Object> creer(CorrespondanceDto correspondanceDto) {
        try {
            Correspondance correspondance = correspondanceMapper.deCorrespondanceDto(correspondanceDto);
            CleCorrespondance cleCorrespondance=new CleCorrespondance();
            cleCorrespondance.setNumeroCompteComptable(correspondanceDto.getNumeroCompteComptable());
            cleCorrespondance.setCodeRubrique(correspondanceDto.getCodeRubrique());
            cleCorrespondance.setCodePosition(correspondanceDto.getCodePosition());
            cleCorrespondance.setCodePlan(correspondanceDto.getPlan().getCodePlan());
            cleCorrespondance.setCodeIB(correspondanceDto.getIb().getCodeIB());
            correspondance.setIdCorrespondance(cleCorrespondance);

            if(correspondanceDto.getPlan()!=null) {
                Plan plan = planDao.findById(correspondanceDto.getPlan().getCodePlan()).orElseThrow();
                correspondance.setPlan(plan);
            }
            if(correspondanceDto.getIb()!=null) {
                Ib ib = ibDao.findById(correspondanceDto.getIb().getCodeIB()).orElseThrow();
                correspondance.setIb(ib);
            }
            correspondance.setSupprimer(false);
            correspondance = correspondanceDao.save(correspondance);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    correspondanceMapper.deCorrespondance(correspondance));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(CorrespondanceDto correspondanceDto) {
        try {

            Correspondance correspondance = correspondanceMapper.deCorrespondanceDto(correspondanceDto);
            correspondance.setSupprimer(false);
            CleCorrespondance cleCorrespondance=new CleCorrespondance();
            cleCorrespondance.setNumeroCompteComptable(correspondanceDto.getNumeroCompteComptable());
            cleCorrespondance.setCodeRubrique(correspondanceDto.getCodeRubrique());
            cleCorrespondance.setCodePosition(correspondanceDto.getCodePosition());
            cleCorrespondance.setCodePlan(correspondanceDto.getPlan().getCodePlan());
            cleCorrespondance.setCodeIB(correspondanceDto.getIb().getCodeIB());
            correspondance.setIdCorrespondance(cleCorrespondance);

            if(correspondanceDto.getPlan()!=null) {
                Plan plan = planDao.findById(correspondanceDto.getPlan().getCodePlan()).orElseThrow();
                correspondance.setPlan(plan);
            }
            if(correspondanceDto.getIb()!=null) {
                Ib ib = ibDao.findById(correspondanceDto.getIb().getCodeIB()).orElseThrow();
                correspondance.setIb(ib);
            }

            correspondance = correspondanceDao.save(correspondance);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    correspondanceMapper.deCorrespondance(correspondance));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleCorrespondance codeCorrespondance) {
        try {
            correspondanceDao.deleteById(codeCorrespondance);
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
