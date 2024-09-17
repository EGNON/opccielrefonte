package com.ged.service.standard.impl.revuecompte;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.revuecompte.TypeClientDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.TypeClientDto;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.revuecompte.TypeClient;
import com.ged.mapper.standard.revuecompte.TypeClientMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.revuecompte.TypeClientService;
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
public class TypeClientServiceImpl implements TypeClientService {
    private final TypeClientDao TypeClientDao;
    private final TypeClientMapper TypeClientMapper;

    public TypeClientServiceImpl(TypeClientDao TypeClientDao, TypeClientMapper TypeClientMapper) {
        this.TypeClientDao = TypeClientDao;
        this.TypeClientMapper = TypeClientMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"codeTypeClient");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeClient> TypeClientPage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                TypeClientPage = TypeClientDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                TypeClientPage = TypeClientDao.findAll(pageable);
            }
            List<TypeClientDto> content = TypeClientPage.getContent().stream().map(TypeClientMapper::deTypeClient).collect(Collectors.toList());
            DataTablesResponse<TypeClientDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)TypeClientPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)TypeClientPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Clients par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"codeTypeClient");
            return ResponseHandler.generateResponse(
                    "Liste de tous les Type Clients",
                    HttpStatus.OK,
                    TypeClientDao.findAll(sort).stream().map(TypeClientMapper::deTypeClient).collect(Collectors.toList()));
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
                    "TypeClient dont code = " + id.toString(),
                    HttpStatus.OK,
                    TypeClientMapper.deTypeClient(afficherSelonId(id)));
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
    public TypeClient afficherSelonId(Long id) {
        return TypeClientDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", id.toString()));
    }
    @Override
    public ResponseEntity<Object> creer(TypeClientDto TypeClientDto) {
        try {
            TypeClientDto.setSupprimer(false);
            TypeClient TypeClient = TypeClientMapper.deTypeClientDto(TypeClientDto);
            TypeClient TypeClientSaved = TypeClientDao.save(TypeClient);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    TypeClientMapper.deTypeClient(TypeClientSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeClientDto TypeClientDto) {
        try {
            //if(!TypeClientDao.existsById(TypeClientDto.getIdTypeClient()))
              //  throw  new EntityNotFoundException(TypeClient.class, "id", TypeClientDto.getIdTypeClient().toString());
//            TypeClientDao.findById(code);
            TypeClientDto.setSupprimer(false);
            TypeClient TypeClient = TypeClientMapper.deTypeClientDto(TypeClientDto);
            TypeClient = TypeClientDao.save(TypeClient);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    TypeClientMapper.deTypeClient(TypeClient));
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
            TypeClientDao.deleteById(id);
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
