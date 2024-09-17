package com.ged.service.standard.impl;

import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.standard.DetailProfilDao;
import com.ged.dao.titresciel.TypeTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.DetailProfilDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.CleDetailProfil;
import com.ged.entity.standard.Personne;
import com.ged.entity.standard.DetailProfil;
import com.ged.mapper.standard.DetailProfilMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.DetailProfilService;
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
public class DetailProfilServiceImpl implements DetailProfilService {
    private final DetailProfilDao DetailProfilDao;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final TypeTitreDao typeTitreDao;
    private final DetailProfilMapper DetailProfilMapper;

    public DetailProfilServiceImpl(DetailProfilDao DetailProfilDao, PersonneDao personneDao, OpcvmDao opcvmDao, TypeTitreDao typeTitreDao, DetailProfilMapper DetailProfilMapper) {
        this.DetailProfilDao = DetailProfilDao;
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.typeTitreDao = typeTitreDao;
        this.DetailProfilMapper = DetailProfilMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCompte");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<DetailProfil> DetailProfilPage;
//            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
//            {
//                DetailProfilPage = DetailProfilDao.rechercher(parameters.getSearch().getValue(), pageable);
//            }
//            else {
                Opcvm opcvm=new Opcvm();
//                System.out.println("idOpcvm="+idOpcvm);
                opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                DetailProfilPage = DetailProfilDao.findByOpcvm(opcvm,pageable);
//            }
            List<DetailProfilDto> content = DetailProfilPage.getContent().stream().map(DetailProfilMapper::deDetailProfil).collect(Collectors.toList());
            DataTablesResponse<DetailProfilDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)DetailProfilPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)DetailProfilPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des details profils commissions par page datatable",
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
                    "Liste de tous les details profils commissions.",
                    HttpStatus.OK,
                    DetailProfilDao.findAll().stream().map(DetailProfilMapper::deDetailProfil).collect(Collectors.toList()));
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
    public DetailProfil afficherSelonId(CleDetailProfil idDetailProfil) {
        return DetailProfilDao.findById(idDetailProfil).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(CleDetailProfil codeDetailProfil) {
        try {
            return ResponseHandler.generateResponse(
                    "Personne opcvm dont id = " + codeDetailProfil,
                    HttpStatus.OK,
                    DetailProfilMapper.deDetailProfil(afficherSelonId(codeDetailProfil)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficher(String codeProfil, Long idOpcvm) {
        try {
            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            return ResponseHandler.generateResponse(
                    "Liste details profils par profil et opcvm ",
                    HttpStatus.OK,
                    DetailProfilDao.findByCodeProfilAndOpcvm(codeProfil,opcvm).stream().map(DetailProfilMapper::deDetailProfil).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String codeProfil, Long idOpcvm) {
        try {
            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            DetailProfilDao.deleteByCodeProfilAndOpcvm(codeProfil,opcvm);
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

    @Override
    public ResponseEntity<Object> creer(DetailProfilDto DetailProfilDto) {
        try {
            DetailProfilDto.setSupprimer(false);
            DetailProfil DetailProfil = DetailProfilMapper.deDetailProfilDto(DetailProfilDto);
            Personne Personne=new Personne();
            Opcvm opcvm=new Opcvm();
            CleDetailProfil cleDetailProfil=new CleDetailProfil();
            cleDetailProfil.setIdOpcvm(DetailProfilDto.getOpcvm().getIdOpcvm());
            cleDetailProfil.setCodeProfil(DetailProfilDto.getCodeProfil());
            cleDetailProfil.setBorneInferieur(DetailProfilDto.getBorneInferieur());
            DetailProfil.setCleDetailProfil(cleDetailProfil);
            System.out.println("codeprofil="+cleDetailProfil.getCodeProfil());
            System.out.println("idopcvm="+cleDetailProfil.getIdOpcvm());
            System.out.println("borneinferieur="+cleDetailProfil.getBorneInferieur());
            if(DetailProfilDto.getOpcvm()!=null){
                opcvm=opcvmDao.findById(DetailProfilDto.getOpcvm().getIdOpcvm()).orElseThrow();
                DetailProfil.setOpcvm(opcvm);
            }
            DetailProfil = DetailProfilDao.save(DetailProfil);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    DetailProfilMapper.deDetailProfil(DetailProfil));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(DetailProfilDto DetailProfilDto) {
        try {
            DetailProfilDto.setSupprimer(false);
            DetailProfil DetailProfil = DetailProfilMapper.deDetailProfilDto(DetailProfilDto);
            Personne Personne=new Personne();
            Opcvm opcvm=new Opcvm();
            CleDetailProfil cleDetailProfil=new CleDetailProfil();
            cleDetailProfil.setIdOpcvm(DetailProfilDto.getOpcvm().getIdOpcvm());
            cleDetailProfil.setCodeProfil(DetailProfilDto.getCodeProfil());
            cleDetailProfil.setBorneInferieur(DetailProfilDto.getBorneInferieur());
            DetailProfil.setCleDetailProfil(cleDetailProfil);

            if(DetailProfilDto.getOpcvm()!=null){
                opcvm=opcvmDao.findById(DetailProfilDto.getOpcvm().getIdOpcvm()).orElseThrow();
                DetailProfil.setOpcvm(opcvm);
            }
            DetailProfil = DetailProfilDao.save(DetailProfil);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    DetailProfilMapper.deDetailProfil(DetailProfil));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleDetailProfil codeDetailProfil) {
        try {
            DetailProfilDao.deleteById(codeDetailProfil);
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
