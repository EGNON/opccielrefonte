package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.FormeJuridiqueDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.FormeJuridiqueDto;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.FormeJuridique;
import com.ged.mapper.opcciel.FormeJuridiqueMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.FormeJuridiqueService;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FormeJuridiqueServiceImpl implements FormeJuridiqueService {
    private final FormeJuridiqueDao formeJuridiqueDao;
    private final FormeJuridiqueMapper formeJuridiqueMapper;

    public FormeJuridiqueServiceImpl(FormeJuridiqueDao formeJuridiqueDao, FormeJuridiqueMapper formeJuridiqueMapper) {
        this.formeJuridiqueDao = formeJuridiqueDao;
        this.formeJuridiqueMapper = formeJuridiqueMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"libelleFormeJuridique");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<FormeJuridique> formeJuridiquePage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
//                ObjectMapper mapper = new ObjectMapper();
//                System.out.println("PARAMS -> " + mapper.writeValueAsString(parameters.getSearch()));
                formeJuridiquePage = formeJuridiqueDao.findByLibelleFormeJuridiqueContainsIgnoreCaseAndSupprimer(parameters.getSearch().getValue(),false, pageable);
            }
            else {
                formeJuridiquePage = formeJuridiqueDao.findBySupprimer(false,pageable);
            }
            List<FormeJuridiqueDto> content = formeJuridiquePage.getContent().stream().map(formeJuridiqueMapper::deFormeJuridique).collect(Collectors.toList());
            DataTablesResponse<FormeJuridiqueDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)formeJuridiquePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)formeJuridiquePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des forme juridique par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"libelleFormeJuridique");
            return ResponseHandler.generateResponse(
                    "Liste de toutes les formes juridiques",
                    HttpStatus.OK,
                    formeJuridiqueDao.findBySupprimerOrderByLibelleFormeJuridiqueAsc(false).stream().map(formeJuridiqueMapper::deFormeJuridique).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficherFormeJuridiques(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherFormeJuridique(String id) {
        try {
            return ResponseHandler.generateResponse(
                    "Forme juridique dont code = " + id.toString(),
                    HttpStatus.OK,
                    formeJuridiqueMapper.deFormeJuridique(afficherFormeJuridiqueSelonId(id)));
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
    public FormeJuridique afficherFormeJuridiqueSelonId(String codeFormeJuridique) {
        return formeJuridiqueDao.findById(codeFormeJuridique).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", codeFormeJuridique.toString()));
    }

    @Override
    public ResponseEntity<Object> rechercherFormeJuridiqueParLibelle(String libelle) {
        return null;
    }

    @Override
    public ResponseEntity<Object> creerFormeJuridique(FormeJuridiqueDto formeJuridiqueDto) {
        try {
            FormeJuridique formeJuridique = formeJuridiqueMapper.deFormeJuridiqueDto(formeJuridiqueDto);
            FormeJuridique formeJuridiqueSaved = formeJuridiqueDao.save(formeJuridique);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    formeJuridiqueMapper.deFormeJuridique(formeJuridiqueSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifierFormeJuridique(FormeJuridiqueDto formeJuridiqueDto) {
        try {
            if(!formeJuridiqueDao.existsById(formeJuridiqueDto.getCodeFormeJuridique()))
                throw  new EntityNotFoundException(FormeJuridique.class, "id", formeJuridiqueDto.getCodeFormeJuridique().toString());
//            formeJuridiqueDao.findById(code);
            FormeJuridique formeJuridique = formeJuridiqueMapper.deFormeJuridiqueDto(formeJuridiqueDto);
            formeJuridique = formeJuridiqueDao.save(formeJuridique);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    formeJuridiqueMapper.deFormeJuridique(formeJuridique));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimerFormeJuridique(String id) {
        try {
            formeJuridiqueDao.deleteById(id);
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
