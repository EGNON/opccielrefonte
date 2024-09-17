package com.ged.service.standard.impl.revuecompte;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.standard.revuecompte.NumeroCompteDao;
import com.ged.dao.standard.revuecompte.SousTypeCompteDao;
import com.ged.dao.titresciel.TypeTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.NumeroCompteDto;
import com.ged.entity.standard.Personne;
import com.ged.entity.standard.revuecompte.CleNumeroCompte;
import com.ged.entity.standard.revuecompte.NumeroCompte;
import com.ged.entity.standard.revuecompte.SousTypeCompte;
import com.ged.entity.titresciel.TypeTitre;
import com.ged.mapper.standard.revuecompte.NumeroCompteMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.revuecompte.NumeroCompteService;
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
public class NumeroCompteServiceImpl implements NumeroCompteService {
    private final NumeroCompteDao numeroCompteDao;
    private final PersonneDao personneDao;
    private final SousTypeCompteDao sousTypeCompteDao;
    private final TypeTitreDao typeTitreDao;
    private final NumeroCompteMapper numeroCompteMapper;

    public NumeroCompteServiceImpl(NumeroCompteDao NumeroCompteDao, PersonneDao personneDao, SousTypeCompteDao SousTypeCompteDao, TypeTitreDao typeTitreDao, NumeroCompteMapper NumeroCompteMapper) {
        this.numeroCompteDao = NumeroCompteDao;
        this.personneDao = personneDao;
        this.sousTypeCompteDao = SousTypeCompteDao;
        this.typeTitreDao = typeTitreDao;
        this.numeroCompteMapper = NumeroCompteMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numCompte");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<NumeroCompte> NumeroComptePage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                NumeroComptePage = numeroCompteDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                NumeroComptePage = numeroCompteDao.findAll(pageable);
            }
            List<NumeroCompteDto> content = NumeroComptePage.getContent().stream().map(numeroCompteMapper::deNumeroCompte).collect(Collectors.toList());
            DataTablesResponse<NumeroCompteDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)NumeroComptePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)NumeroComptePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des comptes par page datatable",
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
                    "Liste de tous les Comptes",
                    HttpStatus.OK,
                    numeroCompteDao.findAll().stream().map(numeroCompteMapper::deNumeroCompte).collect(Collectors.toList()));
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
    public NumeroCompte afficherSelonId(CleNumeroCompte idNumeroCompte) {
        return numeroCompteDao.findById(idNumeroCompte).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(CleNumeroCompte codeNumeroCompte) {
        try {
            return ResponseHandler.generateResponse(
                    "Numero de compte dont id = " + codeNumeroCompte,
                    HttpStatus.OK,
                    numeroCompteMapper.deNumeroCompte(afficherSelonId(codeNumeroCompte)));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    @Override
    public ResponseEntity<Object> creer(NumeroCompteDto NumeroCompteDto) {
        try {
            NumeroCompteDto.setSupprimer(false);
            NumeroCompte NumeroCompte = numeroCompteMapper.deNumeroCompteDto(NumeroCompteDto);
            Personne Personne=new Personne();
            SousTypeCompte SousTypeCompte=new SousTypeCompte();
            CleNumeroCompte cleNumeroCompte=new CleNumeroCompte();
            cleNumeroCompte.setIdSousTypeCompte(NumeroCompteDto.getSousTypeCompte().getIdSousTypeCompte());
            cleNumeroCompte.setIdPersonne(NumeroCompteDto.getPersonne().getIdPersonne());
            NumeroCompte.setCleNumeroCompte(cleNumeroCompte);

            if(NumeroCompteDto.getPersonne()!=null){
                Personne=personneDao.findById(NumeroCompteDto.getPersonne().getIdPersonne()).orElseThrow();
                NumeroCompte.setPersonne(Personne);
            }
            if(NumeroCompteDto.getSousTypeCompte()!=null){
                SousTypeCompte=sousTypeCompteDao.findById(NumeroCompteDto.getSousTypeCompte().getIdSousTypeCompte()).orElseThrow();
                NumeroCompte.setSousTypeCompte(SousTypeCompte);
            }
            NumeroCompte = numeroCompteDao.save(NumeroCompte);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    numeroCompteMapper.deNumeroCompte(NumeroCompte));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(NumeroCompteDto NumeroCompteDto) {
        try {
            NumeroCompteDto.setSupprimer(false);
            NumeroCompte NumeroCompte = numeroCompteMapper.deNumeroCompteDto(NumeroCompteDto);
            Personne Personne=new Personne();
            SousTypeCompte SousTypeCompte=new SousTypeCompte();

            CleNumeroCompte cleNumeroCompte=new CleNumeroCompte();
            cleNumeroCompte.setIdSousTypeCompte(NumeroCompteDto.getSousTypeCompte().getIdSousTypeCompte());
            cleNumeroCompte.setIdPersonne(NumeroCompteDto.getPersonne().getIdPersonne());
            NumeroCompte.setCleNumeroCompte(cleNumeroCompte);

            if(NumeroCompteDto.getPersonne()!=null){
                Personne=personneDao.findById(NumeroCompteDto.getPersonne().getIdPersonne()).orElseThrow();
                NumeroCompte.setPersonne(Personne);
            }
            if(NumeroCompteDto.getSousTypeCompte()!=null){
                SousTypeCompte=sousTypeCompteDao.findById(NumeroCompteDto.getSousTypeCompte().getIdSousTypeCompte()).orElseThrow();
                NumeroCompte.setSousTypeCompte(SousTypeCompte);
            }
            NumeroCompte = numeroCompteDao.save(NumeroCompte);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    numeroCompteMapper.deNumeroCompte(NumeroCompte));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleNumeroCompte codeNumeroCompte) {
        try {
            numeroCompteDao.deleteById(codeNumeroCompte);
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
