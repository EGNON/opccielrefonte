package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TypeAffectationTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeAffectationTitreDto;
import com.ged.entity.titresciel.TypeAffectationTitre;
import com.ged.mapper.titresciel.TypeAffectationTitreMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.TypeAffectationService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeAffectationServiceImpl implements TypeAffectationService {
    /*@Autowired
    @Qualifier("titrescielEntityManagerFactory")
    private EntityManager emTitresciel;*/
    private final TypeAffectationTitreDao typeAffectationTitreDao;
    private final TypeAffectationTitreMapper typeAffectationTitreMapper;

    public TypeAffectationServiceImpl(TypeAffectationTitreDao typeAffectationTitreDao, TypeAffectationTitreMapper typeAffectationTitreMapper) {
        this.typeAffectationTitreDao = typeAffectationTitreDao;
        this.typeAffectationTitreMapper = typeAffectationTitreMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            return ResponseHandler.generateResponse(
                    "Liste de tous les types d'affectation",
                    HttpStatus.OK,
                    typeAffectationTitreDao.findAll().stream().map(typeAffectationTitreMapper::deTypeAffectation).collect(Collectors.toList()));
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    /*@Override
    public List<Object> createTypeAffectationFromTitresciel1() {
        List<Object> result = new ArrayList<>();
        List<Object[]> typeAffectations;
        //Se connecter à opcciel1 et récupérer les différentes TypeAffectation
        try {
            StoredProcedureQuery query = emTitresciel.createStoredProcedureQuery("[Titre].[PS_TypeAffectationVL_SP]");
            //Déclarer les différents paramètres
            query.registerStoredProcedureParameter("libelleTypeAffectationVL", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("numLigne", Double.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateCreationServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifClient", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("supprimer", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("rowvers", byte[].class, ParameterMode.IN);
            //Fournir les différents paramètres
            query.setParameter("libelleTypeAffectationVL", null);
            query.setParameter("numLigne", null);
            query.setParameter("dateCreationServeur", null);
            query.setParameter("dateDernModifServeur", null);
            query.setParameter("dateDernModifClient", null);
            query.setParameter("userLogin", null);
            query.setParameter("supprimer", false);
            query.setParameter("rowvers", null);
            query.execute();
            typeAffectations = query.getResultList();
            for (Object[] o: typeAffectations) {
                if(!typeAffectationTitreDao.existsByLibelleTypeAffectationIgnoreCase((String)o[0]))
                {
                    //System.out.println("ELEMENT 1 === " + o[0]);
                    TypeAffectationTitre typeAffectationTitre = new TypeAffectationTitre();
                    typeAffectationTitre.setLibelleTypeAffectation((String)o[0]);
                    result.add(typeAffectationTitreDao.save(typeAffectationTitre));
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return result;
    }*/

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeAffectation");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeAffectationTitre> typeAffectationPage;
            typeAffectationPage = typeAffectationTitreDao.findAll(pageable);
            List<TypeAffectationTitreDto> content = typeAffectationPage.getContent().stream().map(typeAffectationTitreMapper::deTypeAffectation).collect(Collectors.toList());
            DataTablesResponse<TypeAffectationTitreDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeAffectationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeAffectationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des typeAffectations par page datatable",
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
    public TypeAffectationTitre afficherSelonId(Long id) {
        return typeAffectationTitreDao.findById(id).orElseThrow(() -> new EntityNotFoundException(TypeAffectationTitre.class, "id", id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeAffectation dont ID = " + id.toString(),
                    HttpStatus.OK,
                    typeAffectationTitreMapper.deTypeAffectation(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(TypeAffectationTitreDto typeAffectationTitreDto) {
        try {
            TypeAffectationTitre typeAffectationTitre = typeAffectationTitreMapper.deTypeAffectationDto(typeAffectationTitreDto);
            typeAffectationTitre = typeAffectationTitreDao.save(typeAffectationTitre);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeAffectationTitreMapper.deTypeAffectation(typeAffectationTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeAffectationTitreDto typeAffectationTitreDto) {
        try {
            if(!typeAffectationTitreDao.existsById(typeAffectationTitreDto.getIdTypeAffectation()))
                throw  new EntityNotFoundException(TypeAffectationTitre.class, "id", typeAffectationTitreDto.getIdTypeAffectation().toString());
            TypeAffectationTitre typeAffectationTitre = typeAffectationTitreMapper.deTypeAffectationDto(typeAffectationTitreDto);
            typeAffectationTitre = typeAffectationTitreDao.save(typeAffectationTitre);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeAffectationTitreMapper.deTypeAffectation(typeAffectationTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        try {
            typeAffectationTitreDao.deleteById(id);
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
