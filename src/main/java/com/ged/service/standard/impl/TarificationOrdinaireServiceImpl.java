package com.ged.service.standard.impl;

import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.standard.PersonneMoraleDao;
import com.ged.dao.standard.TarificationOrdinaireDao;
import com.ged.dao.titresciel.*;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.TarificationOrdinaireDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.PersonneMorale;
import com.ged.entity.standard.TarificationOrdinaire;
import com.ged.entity.titresciel.ClasseTitre;
import com.ged.entity.titresciel.Place;
import com.ged.mapper.standard.TarificationOrdinaireMapper;
import com.ged.projection.TarificationProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.TarificationOrdinaireService;
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
public class TarificationOrdinaireServiceImpl implements TarificationOrdinaireService {
    private final TarificationOrdinaireDao TarificationOrdinaireDao;
    private final PersonneMoraleDao personneMoraleDao;
    private final OpcvmDao opcvmDao;
    private final ClasseTitreDao classeTitreDao;
    private final PlaceDao placeDao;
    private final TarificationOrdinaireMapper TarificationOrdinaireMapper;

    public TarificationOrdinaireServiceImpl(TarificationOrdinaireDao tarificationOrdinaireDao, PersonneMoraleDao personneMoraleDao, OpcvmDao opcvmDao, ClasseTitreDao classeTitreDao, PlaceDao placeDao, com.ged.mapper.standard.TarificationOrdinaireMapper tarificationOrdinaireMapper) {
        TarificationOrdinaireDao = tarificationOrdinaireDao;
        this.personneMoraleDao = personneMoraleDao;
        this.opcvmDao = opcvmDao;
        this.classeTitreDao = classeTitreDao;
        this.placeDao = placeDao;
        TarificationOrdinaireMapper = tarificationOrdinaireMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTousSGI(DatatableParameters parameters,Long idOpcvm) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCompte");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<TarificationProjection> TarificationOrdinairePage;
//            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
//            {
//                TarificationOrdinairePage = TarificationOrdinaireDao.rechercher(parameters.getSearch().getValue(), pageable);
//            }
//            else {
                Opcvm opcvm=new Opcvm();
//                System.out.println("idOpcvm="+idOpcvm);
                opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
                TarificationOrdinairePage = TarificationOrdinaireDao.afficherRegistraire(idOpcvm,pageable);
//            }
            List<TarificationOrdinaireDto> content = TarificationOrdinairePage.getContent().stream().map(TarificationOrdinaireMapper::deTarificationProjection).collect(Collectors.toList());
            DataTablesResponse<TarificationOrdinaireDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)TarificationOrdinairePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)TarificationOrdinairePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des tarifications opc par opcvm par page datatable",
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
    public ResponseEntity<Object> afficherTousDepositaire(DatatableParameters parameters, Long idOpcvm) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCompte");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<TarificationOrdinaire> TarificationOrdinairePage;
//            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
//            {
//                TarificationOrdinairePage = TarificationOrdinaireDao.rechercher(parameters.getSearch().getValue(), pageable);
//            }
//            else {
            Opcvm opcvm=new Opcvm();
//                System.out.println("idOpcvm="+idOpcvm);
            opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            TarificationOrdinairePage = TarificationOrdinaireDao.findByIdDepositaireIsNot(0L, pageable);
//            }
            List<TarificationOrdinaireDto> content = TarificationOrdinairePage.getContent().stream().map(TarificationOrdinaireMapper::deTarificationOrdinaire).collect(Collectors.toList());
            DataTablesResponse<TarificationOrdinaireDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)TarificationOrdinairePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)TarificationOrdinairePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des tarifications opc par opcvm par page datatable",
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
    public ResponseEntity<Object> afficherTousPlace(DatatableParameters parameters, Long idOpcvm) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCompte");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<TarificationOrdinaire> TarificationOrdinairePage;
//            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
//            {
//                TarificationOrdinairePage = TarificationOrdinaireDao.rechercher(parameters.getSearch().getValue(), pageable);
//            }
//            else {
            Opcvm opcvm=new Opcvm();
//                System.out.println("idOpcvm="+idOpcvm);
            opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            TarificationOrdinairePage = TarificationOrdinaireDao.findByPlaceIsNotNull(pageable);
            //var o = TarificationOrdinaireDao.findAll();
//            }
            List<TarificationOrdinaireDto> content = TarificationOrdinairePage.getContent().stream().map(TarificationOrdinaireMapper::deTarificationOrdinaire).collect(Collectors.toList());
            DataTablesResponse<TarificationOrdinaireDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)TarificationOrdinairePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)TarificationOrdinairePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des tarifications opc par opcvm par page datatable",
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
                    "Liste de toutes les tarifications opc par opcvm",
                    HttpStatus.OK,
                    TarificationOrdinaireDao.findAll().stream().map(TarificationOrdinaireMapper::deTarificationOrdinaire).collect(Collectors.toList()));
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
    public TarificationOrdinaire afficherSelonId(Long idTarificationOrdinaire) {
        return TarificationOrdinaireDao.findById(idTarificationOrdinaire).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(Long codeTarificationOrdinaire) {
        try {
            return ResponseHandler.generateResponse(
                    "tarification opc dont id = " + codeTarificationOrdinaire,
                    HttpStatus.OK,
                    TarificationOrdinaireMapper.deTarificationOrdinaire(afficherSelonId(codeTarificationOrdinaire)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherRegistraireSelonId(Long idTarificationOrdinaire) {
        try {
            return ResponseHandler.generateResponse(
                    "tarification opc dont id = " + idTarificationOrdinaire,
                    HttpStatus.OK,
                    TarificationOrdinaireMapper.deTarificationProjection(TarificationOrdinaireDao.rechercherRegistraireSelonId(idTarificationOrdinaire)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherDepositaireSelonId(Long idTarificationOrdinaire) {
        try {
            return ResponseHandler.generateResponse(
                    "tarification opc dont id = " + idTarificationOrdinaire,
                    HttpStatus.OK,
                    TarificationOrdinaireMapper.deTarificationProjection(TarificationOrdinaireDao.rechercherDeposiatireSelonId(idTarificationOrdinaire)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherPlaceSelonId(Long idTarificationOrdinaire) {
        try {
            return ResponseHandler.generateResponse(
                    "tarification opc dont id = " + idTarificationOrdinaire,
                    HttpStatus.OK,
                    TarificationOrdinaireMapper.deTarificationProjection(TarificationOrdinaireDao.rechercherPlaceSelonId(idTarificationOrdinaire)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creer(TarificationOrdinaireDto TarificationOrdinaireDto) {
        try {
            TarificationOrdinaireDto.setSupprimer(false);
            TarificationOrdinaire TarificationOrdinaire = TarificationOrdinaireMapper.deTarificationOrdinaireDto(TarificationOrdinaireDto);
            PersonneMorale registraire=new PersonneMorale();
            PersonneMorale depositaire=new PersonneMorale();
            ClasseTitre classeTitre=new ClasseTitre();
            Place place=new Place();
            Opcvm opcvm=new Opcvm();
            /*CleTarificationOrdinaire cleTarificationOrdinaire=new CleTarificationOrdinaire();
            cleTarificationOrdinaire.setCodeRole(TarificationOrdinaireDto.getCodeRole());
            cleTarificationOrdinaire.setCodeClasseTitre(TarificationOrdinaireDto.getClasseTitre().getCodeClasseTitre());
            cleTarificationOrdinaire.setIdOpcvm(TarificationOrdinaireDto.getOpcvm().getIdOpcvm());
            cleTarificationOrdinaire.setCodePlace(TarificationOrdinaireDto.getPlace().getCodePlace());
            cleTarificationOrdinaire.setIdDepositaireNew(TarificationOrdinaireDto.getDepositaire().getIdPersonne());
            cleTarificationOrdinaire.setIdRegistraireNew(TarificationOrdinaireDto.getRegistraire().getIdPersonne());
            TarificationOrdinaire.setIdTarificationOrdinaire(cleTarificationOrdinaire);*/

            if(TarificationOrdinaireDto.getOpcvm()!=null){
                opcvm=opcvmDao.findById(TarificationOrdinaireDto.getOpcvm().getIdOpcvm()).orElseThrow();
                TarificationOrdinaire.setOpcvm(opcvm);
            }
            if(TarificationOrdinaireDto.getClasseTitre()!=null){
                classeTitre=classeTitreDao.findById(TarificationOrdinaireDto.getClasseTitre().getCodeClasseTitre()).orElseThrow();
                TarificationOrdinaire.setClasseTitre(classeTitre);
            }
            if(TarificationOrdinaireDto.getPlace()!=null){
                place=placeDao.findById(TarificationOrdinaireDto.getPlace().getCodePlace()).orElseThrow();
                TarificationOrdinaire.setPlace(place);
            }
            if(TarificationOrdinaireDto.getDepositaire()!=null){
               depositaire=personneMoraleDao.findById(TarificationOrdinaireDto.getDepositaire().getIdPersonne()).orElseThrow();
                TarificationOrdinaire.setDepositaire(depositaire);
            }
            if(TarificationOrdinaireDto.getRegistraire()!=null){
                registraire=personneMoraleDao.findById(TarificationOrdinaireDto.getRegistraire().getIdPersonne()).orElseThrow();
                TarificationOrdinaire.setRegistraire(registraire);
            }
            TarificationOrdinaire = TarificationOrdinaireDao.save(TarificationOrdinaire);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    TarificationOrdinaireMapper.deTarificationOrdinaire(TarificationOrdinaire));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TarificationOrdinaireDto TarificationOrdinaireDto,String qualite) {
        try {
            /*TarificationOrdinaireDto.setSupprimer(false);
            TarificationOrdinaire TarificationOrdinaire = TarificationOrdinaireMapper.deTarificationOrdinaireDto(TarificationOrdinaireDto);
            PersonneMorale registraire=new PersonneMorale();
            PersonneMorale depositaire=new PersonneMorale();
            ClasseTitre classeTitre=new ClasseTitre();
            Place place=new Place();
            Opcvm opcvm=new Opcvm();

            if(TarificationOrdinaireDto.getOpcvm()!=null){
                opcvm=opcvmDao.findById(TarificationOrdinaireDto.getOpcvm().getIdOpcvm()).orElseThrow();
                TarificationOrdinaire.setOpcvm(opcvm);
            }
            if(TarificationOrdinaireDto.getClasseTitre()!=null){
                classeTitre=classeTitreDao.findById(TarificationOrdinaireDto.getClasseTitre().getCodeClasseTitre()).orElseThrow();
                TarificationOrdinaire.setClasseTitre(classeTitre);
            }
            if(TarificationOrdinaireDto.getPlace()!=null){
                place=placeDao.findById(TarificationOrdinaireDto.getPlace().getCodePlace()).orElseThrow();
                TarificationOrdinaire.setPlace(place);
            }
            if(TarificationOrdinaireDto.getDepositaire()!=null){
                depositaire=personneMoraleDao.findById(TarificationOrdinaireDto.getDepositaire().getIdPersonne()).orElseThrow();
                TarificationOrdinaire.setDepositaire(depositaire);
            }
            if(TarificationOrdinaireDto.getRegistraire()!=null){
                registraire=personneMoraleDao.findById(TarificationOrdinaireDto.getRegistraire().getIdPersonne()).orElseThrow();
                TarificationOrdinaire.setRegistraire(registraire);
            }

            TarificationOrdinaire = TarificationOrdinaireDao.save(TarificationOrdinaire);*/
            if(qualite=="sgi")
            {
                TarificationOrdinaireDao.modifierRegistraire(TarificationOrdinaireDto.getIdTarificationOrdinaire(),
                        TarificationOrdinaireDto.getRegistraire().getIdPersonne(),TarificationOrdinaireDto.getBorneInferieur(),
                        TarificationOrdinaireDto.getBorneSuperieur(),TarificationOrdinaireDto.getClasseTitre().getCodeClasseTitre(),
                        TarificationOrdinaireDto.getCodeRole(),TarificationOrdinaireDto.getForfait(),TarificationOrdinaireDto.getTaux(),TarificationOrdinaireDto.getOpcvm().getIdOpcvm());
            }
            else
            if(qualite=="depositaire")
            {
                TarificationOrdinaireDao.modifierDepositaire(TarificationOrdinaireDto.getIdTarificationOrdinaire(),
                        TarificationOrdinaireDto.getDepositaire().getIdPersonne(),TarificationOrdinaireDto.getBorneInferieur(),
                        TarificationOrdinaireDto.getBorneSuperieur(),TarificationOrdinaireDto.getClasseTitre().getCodeClasseTitre(),
                        TarificationOrdinaireDto.getCodeRole(),TarificationOrdinaireDto.getForfait(),TarificationOrdinaireDto.getTaux(),TarificationOrdinaireDto.getOpcvm().getIdOpcvm());
            }
            else
            if(qualite=="place")
            {
                TarificationOrdinaireDao.modifierPlace(TarificationOrdinaireDto.getIdTarificationOrdinaire(),
                        TarificationOrdinaireDto.getPlace().getCodePlace(),TarificationOrdinaireDto.getBorneInferieur(),
                        TarificationOrdinaireDto.getBorneSuperieur(),TarificationOrdinaireDto.getClasseTitre().getCodeClasseTitre(),
                        TarificationOrdinaireDto.getCodeRole(),TarificationOrdinaireDto.getForfait(),TarificationOrdinaireDto.getTaux(),TarificationOrdinaireDto.getOpcvm().getIdOpcvm());
            }
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
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
    public ResponseEntity<Object> supprimer(Long codeTarificationOrdinaire) {
        try {
            TarificationOrdinaireDao.supprimerPlace(codeTarificationOrdinaire);
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
