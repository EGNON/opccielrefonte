package com.ged.service.standard.impl;

import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.standard.ProfilCommissionSousRachDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.titresciel.TypeTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ProfilCommissionSousRachDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.ProfilCommissionSousRach;
import com.ged.entity.standard.CleProfilCommissionSousRach;
import com.ged.entity.standard.Personne;
import com.ged.mapper.standard.ProfilCommissionSousRachMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.ProfilCommissionSousRachService;
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
public class ProfilCommissionSousRachServiceImpl implements ProfilCommissionSousRachService {
    private final ProfilCommissionSousRachDao ProfilCommissionSousRachDao;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final TypeTitreDao typeTitreDao;
    private final ProfilCommissionSousRachMapper ProfilCommissionSousRachMapper;

    public ProfilCommissionSousRachServiceImpl(ProfilCommissionSousRachDao ProfilCommissionSousRachDao, PersonneDao personneDao, OpcvmDao opcvmDao, TypeTitreDao typeTitreDao, ProfilCommissionSousRachMapper ProfilCommissionSousRachMapper) {
        this.ProfilCommissionSousRachDao = ProfilCommissionSousRachDao;
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.typeTitreDao = typeTitreDao;
        this.ProfilCommissionSousRachMapper = ProfilCommissionSousRachMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCompte");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ProfilCommissionSousRach> ProfilCommissionSousRachPage;
//            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
//            {
//                ProfilCommissionSousRachPage = ProfilCommissionSousRachDao.rechercher(parameters.getSearch().getValue(), pageable);
//            }
//            else {
                Opcvm opcvm=new Opcvm();
//                System.out.println("idOpcvm="+idOpcvm);
                opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                ProfilCommissionSousRachPage = ProfilCommissionSousRachDao.findByOpcvm(opcvm,pageable);
//            }
            List<ProfilCommissionSousRachDto> content = ProfilCommissionSousRachPage.getContent().stream().map(ProfilCommissionSousRachMapper::deProfilCommissionSousRach).collect(Collectors.toList());
            DataTablesResponse<ProfilCommissionSousRachDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)ProfilCommissionSousRachPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)ProfilCommissionSousRachPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des profils commission par opcvm par page datatable",
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
                    "Liste de tous les profils commissions par opcvm",
                    HttpStatus.OK,
                    ProfilCommissionSousRachDao.findAll().stream().map(ProfilCommissionSousRachMapper::deProfilCommissionSousRach).collect(Collectors.toList()));
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
    public ProfilCommissionSousRach afficherSelonId(CleProfilCommissionSousRach idProfilCommissionSousRach) {
        return ProfilCommissionSousRachDao.findById(idProfilCommissionSousRach).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficherSelonTypeCommissionOpcvm(String typeCommission, Long idOpcvm) {
        try {
            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            Sort sort=Sort.by(Sort.Direction.ASC,"numCompte");
            return ResponseHandler.generateResponse(
                    "Liste des  profils commissions par opcvm et type commission",
                    HttpStatus.OK,
                    ProfilCommissionSousRachDao.findByTypeCommissionAndOpcvm(typeCommission,opcvm)
                            .stream().map(ProfilCommissionSousRachMapper::deProfilCommissionSousRach).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficher(CleProfilCommissionSousRach codeProfilCommissionSousRach) {
        try {
            return ResponseHandler.generateResponse(
                    "Personne opcvm dont id = " + codeProfilCommissionSousRach,
                    HttpStatus.OK,
                    ProfilCommissionSousRachMapper.deProfilCommissionSousRach(afficherSelonId(codeProfilCommissionSousRach)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    @Override
    public ResponseEntity<Object> creer(ProfilCommissionSousRachDto ProfilCommissionSousRachDto) {
        try {
            ProfilCommissionSousRachDto.setSupprimer(false);
            ProfilCommissionSousRach ProfilCommissionSousRach = ProfilCommissionSousRachMapper.deProfilCommissionSousRachDto(ProfilCommissionSousRachDto);
            Personne Personne=new Personne();
            Opcvm opcvm=new Opcvm();
            CleProfilCommissionSousRach cleProfilCommissionSousRach=new CleProfilCommissionSousRach();
            cleProfilCommissionSousRach.setIdOpcvm(ProfilCommissionSousRachDto.getOpcvm().getIdOpcvm());
            cleProfilCommissionSousRach.setCodeProfil(ProfilCommissionSousRachDto.getCodeProfil());
            ProfilCommissionSousRach.setCleProfilCommissionSousRach(cleProfilCommissionSousRach);

            if(ProfilCommissionSousRachDto.getOpcvm()!=null){
                opcvm=opcvmDao.findById(ProfilCommissionSousRachDto.getOpcvm().getIdOpcvm()).orElseThrow();
                ProfilCommissionSousRach.setOpcvm(opcvm);
            }
            ProfilCommissionSousRach = ProfilCommissionSousRachDao.save(ProfilCommissionSousRach);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    ProfilCommissionSousRachMapper.deProfilCommissionSousRach(ProfilCommissionSousRach));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ProfilCommissionSousRachDto ProfilCommissionSousRachDto) {
        try {
            ProfilCommissionSousRachDto.setSupprimer(false);
            ProfilCommissionSousRach ProfilCommissionSousRach = ProfilCommissionSousRachMapper.deProfilCommissionSousRachDto(ProfilCommissionSousRachDto);
            Personne Personne=new Personne();
            Opcvm opcvm=new Opcvm();
            CleProfilCommissionSousRach cleProfilCommissionSousRach=new CleProfilCommissionSousRach();
            cleProfilCommissionSousRach.setIdOpcvm(ProfilCommissionSousRachDto.getOpcvm().getIdOpcvm());
            cleProfilCommissionSousRach.setCodeProfil(ProfilCommissionSousRachDto.getCodeProfil());
            ProfilCommissionSousRach.setCleProfilCommissionSousRach(cleProfilCommissionSousRach);

            if(ProfilCommissionSousRachDto.getOpcvm()!=null){
                opcvm=opcvmDao.findById(ProfilCommissionSousRachDto.getOpcvm().getIdOpcvm()).orElseThrow();
                ProfilCommissionSousRach.setOpcvm(opcvm);
            }
            ProfilCommissionSousRach = ProfilCommissionSousRachDao.save(ProfilCommissionSousRach);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    ProfilCommissionSousRachMapper.deProfilCommissionSousRach(ProfilCommissionSousRach));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleProfilCommissionSousRach codeProfilCommissionSousRach) {
        try {
            ProfilCommissionSousRachDao.deleteById(codeProfilCommissionSousRach);
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
