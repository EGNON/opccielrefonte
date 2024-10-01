package com.ged.service.standard.impl.revuecompte;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.revuecompte.TypeCompteDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.TypeCompteDto;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.revuecompte.TypeCompte;
import com.ged.mapper.standard.revuecompte.TypeCompteMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.revuecompte.TypeCompteService;
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
public class TypeCompteServiceImpl implements TypeCompteService {
    private final TypeCompteDao TypeCompteDao;
    private final TypeCompteMapper TypeCompteMapper;

    public TypeCompteServiceImpl(TypeCompteDao TypeCompteDao, TypeCompteMapper TypeCompteMapper) {
        this.TypeCompteDao = TypeCompteDao;
        this.TypeCompteMapper = TypeCompteMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"code");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeCompte> TypeComptePage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                TypeComptePage = TypeCompteDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                TypeComptePage = TypeCompteDao.findAll(pageable);
            }
            List<TypeCompteDto> content = TypeComptePage.getContent().stream().map(TypeCompteMapper::deTypeCompte).collect(Collectors.toList());
            DataTablesResponse<TypeCompteDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)TypeComptePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)TypeComptePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Comptes par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"codeTypeCompte");
            return ResponseHandler.generateResponse(
                    "Liste de tous les Type Comptes",
                    HttpStatus.OK,
                    TypeCompteDao.findAll(sort).stream().map(TypeCompteMapper::deTypeCompte).collect(Collectors.toList()));
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
                    "TypeCompte dont code = " + id.toString(),
                    HttpStatus.OK,
                    TypeCompteMapper.deTypeCompte(afficherSelonId(id)));
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
    public TypeCompte afficherSelonId(Long id) {
        return TypeCompteDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", id.toString()));
    }
    @Override
    public ResponseEntity<Object> creer(TypeCompteDto TypeCompteDto) {
        try {
            TypeCompteDto.setSupprimer(false);
            TypeCompte TypeCompte = TypeCompteMapper.deTypeCompteDto(TypeCompteDto);
            TypeCompte TypeCompteSaved = TypeCompteDao.save(TypeCompte);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    TypeCompteMapper.deTypeCompte(TypeCompteSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeCompteDto TypeCompteDto) {
        try {
            //if(!TypeCompteDao.existsById(TypeCompteDto.getIdTypeCompte()))
              //  throw  new EntityNotFoundException(TypeCompte.class, "id", TypeCompteDto.getIdTypeCompte().toString());
//            TypeCompteDao.findById(code);
            TypeCompteDto.setSupprimer(false);
            TypeCompte TypeCompte = TypeCompteMapper.deTypeCompteDto(TypeCompteDto);
            TypeCompte = TypeCompteDao.save(TypeCompte);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    TypeCompteMapper.deTypeCompte(TypeCompte));
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
            TypeCompteDao.deleteById(id);
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
