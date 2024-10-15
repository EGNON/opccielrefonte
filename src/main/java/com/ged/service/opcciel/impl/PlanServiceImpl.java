package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.PlanDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.PlanDto;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.mapper.opcciel.PlanMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.PlanService;
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
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlanServiceImpl implements PlanService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final PlanDao planDao;
    private final PlanMapper planMapper;

    public PlanServiceImpl(PlanDao planDao, PlanMapper planMapper){
        this.planDao = planDao;
        this.planMapper = planMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Plan> planPage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                planPage = planDao.rechercher(
                        parameters.getSearch().getValue(),
                        pageable);
            } else {
                planPage = planDao.findBySupprimer(false,pageable);
            }

            List<PlanDto> content = planPage.getContent().stream().map(planMapper::dePlan).collect(Collectors.toList());
            DataTablesResponse<PlanDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)planPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)planPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des plans par page datatable",
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
    public Page<PlanDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            List<PlanDto> planDtos = planDao.findBySupprimerOrderByLibellePlanAsc(false).stream().map(planMapper::dePlan).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des plans ",
                    HttpStatus.OK,
                    planDtos);
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
    public Plan afficherSelonId(String codePlan) {
        return planDao.findById(codePlan).orElseThrow(() -> new EntityNotFoundException(Plan.class, "code",codePlan));
    }

    @Override
    public ResponseEntity<Object> afficher(String codePlan) {
        try {
            return ResponseHandler.generateResponse(
                    "Plan dont CODE = " + codePlan,
                    HttpStatus.OK,
                    planMapper.dePlan(afficherSelonId(codePlan)));
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
    public ResponseEntity<Object> creer(PlanDto planDto) {
        try {
            planDto.setSupprimer(false);
            Plan  plan =planMapper.dePlanDto(planDto);
            plan = planDao.save(plan);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    planMapper.dePlan(plan));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(PlanDto planDto) {
        try {
            if(!planDao.existsById(planDto.getCodePlan()))
                throw  new EntityNotFoundException(Plan.class, "code", planDto.getCodePlan().toString());
            planDto.setSupprimer(false);
            Plan plan =planMapper.dePlanDto(planDto);
            plan=planDao.save(plan);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    planMapper.dePlan(plan));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String codePlan) {
        try {
            planDao.deleteById(codePlan);
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
