package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.PlanDao;
import com.ged.dao.opcciel.comptabilite.PosteComptableDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.PosteComptableDto;
import com.ged.entity.opcciel.comptabilite.ClePosteComptable;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.entity.opcciel.comptabilite.PosteComptable;
import com.ged.mapper.opcciel.PlanMapper;
import com.ged.mapper.opcciel.PosteComptableMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.PosteComptableService;
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
public class PosteComptableServiceImpl implements PosteComptableService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final PosteComptableDao posteComptableDao;
    private final PlanDao planDao;
    private final PosteComptableMapper posteComptableMapper;
    private final PlanMapper planMapper;

    public PosteComptableServiceImpl(PosteComptableDao posteComptableDao, PlanDao planDao, PosteComptableMapper posteComptableMapper, PlanMapper planMapper){
        this.posteComptableDao = posteComptableDao;
        this.planDao = planDao;
        this.posteComptableMapper = posteComptableMapper;
        this.planMapper = planMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePosteComptable");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<PosteComptable> posteComptablePage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                posteComptablePage = posteComptableDao.rechercher(
                        parameters.getSearch().getValue(),
                        pageable);
            } else {
                posteComptablePage = posteComptableDao.findBySupprimer(false,pageable);
            }

            List<PosteComptableDto> content = posteComptablePage.getContent().stream().map(posteComptableMapper::dePosteComptable).collect(Collectors.toList());
            DataTablesResponse<PosteComptableDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)posteComptablePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)posteComptablePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des postes comptables par page datatable",
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
    public Page<PosteComptableDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePosteComptable");
            List<PosteComptableDto> posteComptableDtos = posteComptableDao.findBySupprimerOrderByLibellePosteComptableAsc(false).stream().map(posteComptableMapper::dePosteComptable).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des postes comptables ",
                    HttpStatus.OK,
                    posteComptableDtos);
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
    public PosteComptable afficherSelonId(ClePosteComptable clePosteComptable) {
        return posteComptableDao.findById(clePosteComptable).orElseThrow(() -> new EntityNotFoundException(PosteComptable.class, "code",clePosteComptable.getCodePosteComptable()+"-"+clePosteComptable.getCodePlan()));
    }

    @Override
    public ResponseEntity<Object> afficher(ClePosteComptable clePosteComptable) {
        try {
            return ResponseHandler.generateResponse(
                    "Poste Comptable dont CODE = " + clePosteComptable,
                    HttpStatus.OK,
                    posteComptableMapper.dePosteComptable(afficherSelonId(clePosteComptable)));
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
    public ResponseEntity<Object> afficherSelonCodePosteCOmptable(String codePosteComptable) {
        try {
            return ResponseHandler.generateResponse(
                    "Poste Comptable dont CODE = " + codePosteComptable,
                    HttpStatus.OK,
                    posteComptableMapper.dePosteComptable(posteComptableDao.findByCodePosteComptable(codePosteComptable)));
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
    public ResponseEntity<Object> creer(PosteComptableDto posteComptableDto) {
        try {
            posteComptableDto.setSupprimer(false);
            PosteComptable  posteComptable =posteComptableMapper.dePosteComptableDto(posteComptableDto);
            ClePosteComptable clePosteComptable=new ClePosteComptable();
            clePosteComptable.setCodePosteComptable(posteComptableDto.getCodePosteComptable());
            clePosteComptable.setCodePlan(posteComptableDto.getPlan().getCodePlan());
            posteComptable.setIdPosteComptable(clePosteComptable);
            if(posteComptableDto.getPlan()!=null)
            {
                Plan plan=planDao.findById(posteComptableDto.getPlan().getCodePlan()).orElseThrow();
                posteComptable.setPlan(plan);
            }
            posteComptable = posteComptableDao.save(posteComptable);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    posteComptableMapper.dePosteComptable(posteComptable));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(PosteComptableDto posteComptableDto) {
        try {
            posteComptableDto.setSupprimer(false);
            PosteComptable posteComptable =posteComptableMapper.dePosteComptableDto(posteComptableDto);

            ClePosteComptable clePosteComptable=new ClePosteComptable();
            clePosteComptable.setCodePosteComptable(posteComptableDto.getCodePosteComptable());
            clePosteComptable.setCodePlan(posteComptableDto.getPlan().getCodePlan());
            posteComptable.setIdPosteComptable(clePosteComptable);

            if(posteComptableDto.getPlan()!=null)
            {
                Plan plan=planDao.findById(posteComptableDto.getPlan().getCodePlan()).orElseThrow();
                posteComptable.setPlan(plan);
            }
            posteComptable=posteComptableDao.save(posteComptable);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    posteComptableMapper.dePosteComptable(posteComptable));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(ClePosteComptable clePosteComptable) {
        try {
            posteComptableDao.deleteById(clePosteComptable);
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
