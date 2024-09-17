package com.ged.service.standard.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.TypeModeleDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.TypeModeleDto;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.TypeModele;
import com.ged.mapper.standard.TypeModeleMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.TypeModeleService;
import jakarta.transaction.Transactional;
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
@Transactional
public class TypeModeleServiceImpl implements TypeModeleService {
    private final TypeModeleDao TypeModeleDao;
    private final TypeModeleMapper TypeModeleMapper;

    public TypeModeleServiceImpl(TypeModeleDao TypeModeleDao, TypeModeleMapper TypeModeleMapper) {
        this.TypeModeleDao = TypeModeleDao;
        this.TypeModeleMapper = TypeModeleMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"libelleTypeModele");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeModele> TypeModelePage;
            /*if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                TypeModelePage = TypeModeleDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {*/
                TypeModelePage = TypeModeleDao.findAll(pageable);
//            }
            List<TypeModeleDto> content = TypeModelePage.getContent().stream().map(TypeModeleMapper::deTypeModele).collect(Collectors.toList());
            DataTablesResponse<TypeModeleDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)TypeModelePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)TypeModelePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Modeles par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"libelleTypeModele");
            return ResponseHandler.generateResponse(
                    "Liste de tous les Type Modeles",
                    HttpStatus.OK,
                    TypeModeleDao.findAll(sort).stream().map(TypeModeleMapper::deTypeModele).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficher(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeModele dont code = " + id.toString(),
                    HttpStatus.OK,
                    TypeModeleMapper.deTypeModele(afficherSelonId(id)));
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
    public TypeModele afficherSelonId(Long id) {
        return TypeModeleDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", id.toString()));
    }
    @Override
    public ResponseEntity<Object> creer(TypeModeleDto TypeModeleDto) {
        try {
            TypeModeleDto.setSupprimer(false);
            TypeModele TypeModele = TypeModeleMapper.deTypeModeleDto(TypeModeleDto);
            TypeModele TypeModeleSaved = TypeModeleDao.save(TypeModele);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    TypeModeleMapper.deTypeModele(TypeModeleSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeModeleDto TypeModeleDto) {
        try {
            //if(!TypeModeleDao.existsById(TypeModeleDto.getIdTypeModele()))
              //  throw  new EntityNotFoundException(TypeModele.class, "id", TypeModeleDto.getIdTypeModele().toString());
//            TypeModeleDao.findById(code);
            TypeModeleDto.setSupprimer(false);
            TypeModele TypeModele = TypeModeleMapper.deTypeModeleDto(TypeModeleDto);
            TypeModele = TypeModeleDao.save(TypeModele);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    TypeModeleMapper.deTypeModele(TypeModele));
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
            TypeModeleDao.deleteById(id);
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
