package com.ged.service.standard.impl;

import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.standard.ActionnaireCommissionDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.titresciel.TypeTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ActionnaireCommissionDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.ActionnaireCommission;
import com.ged.entity.standard.CleActionnaireCommission;
import com.ged.entity.standard.Personne;
import com.ged.mapper.standard.ActionnaireCommissionMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.ActionnaireCommissionService;
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
public class ActionnaireCommissionServiceImpl implements ActionnaireCommissionService {
    private final ActionnaireCommissionDao ActionnaireCommissionDao;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final TypeTitreDao typeTitreDao;
    private final ActionnaireCommissionMapper ActionnaireCommissionMapper;

    public ActionnaireCommissionServiceImpl(ActionnaireCommissionDao ActionnaireCommissionDao, PersonneDao personneDao, OpcvmDao opcvmDao, TypeTitreDao typeTitreDao, ActionnaireCommissionMapper ActionnaireCommissionMapper) {
        this.ActionnaireCommissionDao = ActionnaireCommissionDao;
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.typeTitreDao = typeTitreDao;
        this.ActionnaireCommissionMapper = ActionnaireCommissionMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCompte");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ActionnaireCommission> ActionnaireCommissionPage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                ActionnaireCommissionPage = ActionnaireCommissionDao.rechercher(idOpcvm,parameters.getSearch().getValue(), pageable);
            }
            else {
                /*Opcvm opcvm=new Opcvm();
                //System.out.println("idOpcvm="+idOpcvm);
                opcvm=opcvmDao.findById(idOpcvm).orElseThrow();*/
                ActionnaireCommissionPage = ActionnaireCommissionDao.afficherSelonOpcvm(idOpcvm,pageable);
            }
            List<ActionnaireCommissionDto> content = ActionnaireCommissionPage.getContent().stream().map(ActionnaireCommissionMapper::deActionnaireCommission).collect(Collectors.toList());
            DataTablesResponse<ActionnaireCommissionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)ActionnaireCommissionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)ActionnaireCommissionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des actionnaires commissions par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"numCompte");
            return ResponseHandler.generateResponse(
                    "Liste de toutes les personnes commissions",
                    HttpStatus.OK,
                    ActionnaireCommissionDao.findAll().stream().map(ActionnaireCommissionMapper::deActionnaireCommission).collect(Collectors.toList()));
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
    public ActionnaireCommission afficherSelonId(CleActionnaireCommission idActionnaireCommission) {
        return ActionnaireCommissionDao.findById(idActionnaireCommission).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(CleActionnaireCommission codeActionnaireCommission) {
        try {
            return ResponseHandler.generateResponse(
                    "Personne commission dont id = " + codeActionnaireCommission,
                    HttpStatus.OK,
                    ActionnaireCommissionMapper.deActionnaireCommission(afficherSelonId(codeActionnaireCommission)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    @Override
    public ResponseEntity<Object> creer(ActionnaireCommissionDto ActionnaireCommissionDto) {
        try {
            ActionnaireCommissionDto.setSupprimer(false);
            ActionnaireCommission ActionnaireCommission = ActionnaireCommissionMapper.deActionnaireCommissionDto(ActionnaireCommissionDto);
            Personne Personne=new Personne();
            Opcvm opcvm=new Opcvm();
            CleActionnaireCommission cleActionnaireCommission=new CleActionnaireCommission();
            cleActionnaireCommission.setIdOpcvm(ActionnaireCommissionDto.getOpcvm().getIdOpcvm());
            cleActionnaireCommission.setTypeCommission(ActionnaireCommissionDto.getTypeCommission());
            cleActionnaireCommission.setIdPersonne(ActionnaireCommissionDto.getPersonne().getIdPersonne());
            ActionnaireCommission.setCleActionnaireCommission(cleActionnaireCommission);

            if(ActionnaireCommissionDto.getPersonne()!=null){
                Personne=personneDao.findById(ActionnaireCommissionDto.getPersonne().getIdPersonne()).orElseThrow();
                ActionnaireCommission.setPersonne(Personne);
            }
            if(ActionnaireCommissionDto.getOpcvm()!=null){
                opcvm=opcvmDao.findById(ActionnaireCommissionDto.getOpcvm().getIdOpcvm()).orElseThrow();
                ActionnaireCommission.setOpcvm(opcvm);
            }
            ActionnaireCommission = ActionnaireCommissionDao.save(ActionnaireCommission);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    ActionnaireCommissionMapper.deActionnaireCommission(ActionnaireCommission));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ActionnaireCommissionDto ActionnaireCommissionDto) {
        try {
            ActionnaireCommissionDto.setSupprimer(false);
            ActionnaireCommission ActionnaireCommission = ActionnaireCommissionMapper.deActionnaireCommissionDto(ActionnaireCommissionDto);
            Personne Personne=new Personne();
            Opcvm opcvm=new Opcvm();
            CleActionnaireCommission cleActionnaireCommission=new CleActionnaireCommission();
            cleActionnaireCommission.setIdOpcvm(ActionnaireCommissionDto.getOpcvm().getIdOpcvm());
            cleActionnaireCommission.setTypeCommission(ActionnaireCommissionDto.getTypeCommission());
            cleActionnaireCommission.setIdPersonne(ActionnaireCommissionDto.getPersonne().getIdPersonne());
            ActionnaireCommission.setCleActionnaireCommission(cleActionnaireCommission);

            if(ActionnaireCommissionDto.getPersonne()!=null){
                Personne=personneDao.findById(ActionnaireCommissionDto.getPersonne().getIdPersonne()).orElseThrow();
                ActionnaireCommission.setPersonne(Personne);
            }
            if(ActionnaireCommissionDto.getOpcvm()!=null){
                opcvm=opcvmDao.findById(ActionnaireCommissionDto.getOpcvm().getIdOpcvm()).orElseThrow();
                ActionnaireCommission.setOpcvm(opcvm);
            }
            ActionnaireCommission = ActionnaireCommissionDao.save(ActionnaireCommission);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    ActionnaireCommissionMapper.deActionnaireCommission(ActionnaireCommission));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleActionnaireCommission codeActionnaireCommission) {
        try {
            ActionnaireCommissionDao.deleteById(codeActionnaireCommission);
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
