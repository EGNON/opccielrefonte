package com.ged.service.standard.impl;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.standard.ActionnaireOpcvmDao;
import com.ged.dao.standard.ProfilCommissionSousRachDao;
import com.ged.dao.titresciel.TypeTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ActionnaireCommissionDto;
import com.ged.dto.standard.ActionnaireOpcvmDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.*;
import com.ged.mapper.standard.ActionnaireOpcvmMapper;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.ActionnaireCommissionService;
import com.ged.service.standard.ActionnaireOpcvmService;
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
public class ActionnaireOpcvmServiceImpl implements ActionnaireOpcvmService {
    private final ActionnaireOpcvmDao ActionnaireOpcvmDao;
    private final ActionnaireCommissionService actionnaireCommissionService;
    private final PersonneDao personneDao;
    private final ProfilCommissionSousRachDao profilCommissionSousRachDao;
    private final PersonneMapper personneMapper;
    private final OpcvmDao opcvmDao;
    private final TypeTitreDao typeTitreDao;
    private final ActionnaireOpcvmMapper ActionnaireOpcvmMapper;

    public ActionnaireOpcvmServiceImpl(ActionnaireOpcvmDao ActionnaireOpcvmDao, ActionnaireCommissionService actionnaireCommissionService, PersonneDao personneDao, ProfilCommissionSousRachDao profilCommissionSousRachDao, PersonneMapper personneMapper, OpcvmDao opcvmDao, TypeTitreDao typeTitreDao, ActionnaireOpcvmMapper ActionnaireOpcvmMapper) {
        this.ActionnaireOpcvmDao = ActionnaireOpcvmDao;
        this.actionnaireCommissionService = actionnaireCommissionService;
        this.personneDao = personneDao;
        this.profilCommissionSousRachDao = profilCommissionSousRachDao;
        this.personneMapper = personneMapper;
        this.opcvmDao = opcvmDao;
        this.typeTitreDao = typeTitreDao;
        this.ActionnaireOpcvmMapper = ActionnaireOpcvmMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCompte");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ActionnaireOpcvm> ActionnaireOpcvmPage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                ActionnaireOpcvmPage = ActionnaireOpcvmDao.rechercher(idOpcvm,parameters.getSearch().getValue(), pageable);
            }
            else {
                /*Opcvm opcvm=new Opcvm();
                System.out.println("idOpcvm="+idOpcvm);
                opcvm=opcvmDao.findById(idOpcvm).orElseThrow();*/
                ActionnaireOpcvmPage = ActionnaireOpcvmDao.afficherParOpcvm(idOpcvm,pageable);
           }
            List<ActionnaireOpcvmDto> content = ActionnaireOpcvmPage.getContent().stream().map(ActionnaireOpcvmMapper::deActionnaireOpcvm).collect(Collectors.toList());
            DataTablesResponse<ActionnaireOpcvmDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)ActionnaireOpcvmPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)ActionnaireOpcvmPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des actionnaires par opcvm par page datatable",
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
                    "Liste de toutes les personnes par opcvm",
                    HttpStatus.OK,
                    ActionnaireOpcvmDao.findAll().stream().map(ActionnaireOpcvmMapper::deActionnaireOpcvm).collect(Collectors.toList()));
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
    public ActionnaireOpcvm afficherSelonId(CleActionnaireOpcvm idActionnaireOpcvm) {
        return ActionnaireOpcvmDao.findById(idActionnaireOpcvm).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(CleActionnaireOpcvm codeActionnaireOpcvm) {
        try {
            return ResponseHandler.generateResponse(
                    "Personne opcvm dont id = " + codeActionnaireOpcvm,
                    HttpStatus.OK,
                    ActionnaireOpcvmMapper.deActionnaireOpcvm(afficherSelonId(codeActionnaireOpcvm)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    @Override
    public ResponseEntity<Object> creer(ActionnaireOpcvmDto[] ActionnaireOpcvmDto) {
        try {

            for(ActionnaireOpcvmDto o:ActionnaireOpcvmDto) {
                o.setSupprimer(false);
                ActionnaireOpcvm ActionnaireOpcvm = ActionnaireOpcvmMapper.deActionnaireOpcvmDto(o);
                Personne Personne;
                Opcvm opcvm=new Opcvm();
                CleActionnaireOpcvm cleActionnaireOpcvm=new CleActionnaireOpcvm();
                cleActionnaireOpcvm.setIdOpcvm(o.getOpcvm().getIdOpcvm());
                cleActionnaireOpcvm.setIdPersonne(o.getPersonne().getIdPersonne());
                ActionnaireOpcvm.setCleActionnaireOpcvm(cleActionnaireOpcvm);

                if(o.getPersonne()!=null){
                    Personne=personneDao.afficherPersonneSelonId(o.getPersonne().getIdPersonne());
                    //Personne personne=personneMapper.dePersonnePhysiqueMoraleDto(Personne);
                    Personne personne=new Personne();
                    personne.setIdPersonne(Personne.getIdPersonne());
                    ActionnaireOpcvm.setPersonne(personne);
                    //System.out.println("personne="+Personne.getIdPersonne());
                }
                if(o.getOpcvm()!=null){
                    opcvm=opcvmDao.findById(o.getOpcvm().getIdOpcvm()).orElseThrow();
                    Opcvm opcvm1=new Opcvm();
                    opcvm1.setIdOpcvm(opcvm.getIdOpcvm());
                    ActionnaireOpcvm.setOpcvm(opcvm1);
                    //System.out.println("opcvm="+opcvm1.getIdOpcvm());
                }
                ActionnaireOpcvm = ActionnaireOpcvmDao.save(ActionnaireOpcvm);
                List<ProfilCommissionSousRach> profilCommissionSousRaches=profilCommissionSousRachDao.findByOpcvmAndStandard(ActionnaireOpcvm.getOpcvm(),true);
                for(ProfilCommissionSousRach p:profilCommissionSousRaches){
                    ActionnaireCommissionDto actionnaireCommissionDto=new ActionnaireCommissionDto();
                    actionnaireCommissionDto.setOpcvm(o.getOpcvm());
                    actionnaireCommissionDto.setPersonne(o.getPersonne());
                    actionnaireCommissionDto.setCodeProfil(p.getCodeProfil());
                    actionnaireCommissionDto.setTypeCommission(p.getTypeCommission());
                    actionnaireCommissionDto.setLibelleProfil(p.getLibelleProfil());
                    actionnaireCommissionService.creer(actionnaireCommissionDto);
                }

            }

            /*for(ActionnaireOpcvmDto o:ActionnaireOpcvmDto) {
                ActionnaireOpcvmDao.enregistrer(o.getPersonne().getIdPersonne(),
                        o.getOpcvm().getIdOpcvm(), false);
            }*/
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ActionnaireOpcvmDto ActionnaireOpcvmDto) {
        try {
            ActionnaireOpcvmDto.setSupprimer(false);
            ActionnaireOpcvm ActionnaireOpcvm = ActionnaireOpcvmMapper.deActionnaireOpcvmDto(ActionnaireOpcvmDto);
            Personne Personne=new Personne();
            Opcvm opcvm=new Opcvm();
            CleActionnaireOpcvm cleActionnaireOpcvm=new CleActionnaireOpcvm();
            cleActionnaireOpcvm.setIdOpcvm(ActionnaireOpcvmDto.getOpcvm().getIdOpcvm());
            cleActionnaireOpcvm.setIdPersonne(ActionnaireOpcvmDto.getPersonne().getIdPersonne());
            ActionnaireOpcvm.setCleActionnaireOpcvm(cleActionnaireOpcvm);

            if(ActionnaireOpcvmDto.getPersonne()!=null){
                Personne=personneDao.findById(ActionnaireOpcvmDto.getPersonne().getIdPersonne()).orElseThrow();
                ActionnaireOpcvm.setPersonne(Personne);
            }
            if(ActionnaireOpcvmDto.getOpcvm()!=null){
                opcvm=opcvmDao.findById(ActionnaireOpcvmDto.getOpcvm().getIdOpcvm()).orElseThrow();
                ActionnaireOpcvm.setOpcvm(opcvm);
            }
            ActionnaireOpcvm = ActionnaireOpcvmDao.save(ActionnaireOpcvm);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    ActionnaireOpcvmMapper.deActionnaireOpcvm(ActionnaireOpcvm));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleActionnaireOpcvm codeActionnaireOpcvm) {
        try {
            ActionnaireOpcvmDao.deleteById(codeActionnaireOpcvm);
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
