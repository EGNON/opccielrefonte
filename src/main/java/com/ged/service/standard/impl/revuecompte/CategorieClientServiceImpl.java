package com.ged.service.standard.impl.revuecompte;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.revuecompte.CategorieClientDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.CategorieClientDto;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.revuecompte.CategorieClient;
import com.ged.mapper.standard.revuecompte.CategorieClientMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.revuecompte.CategorieClientService;
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
public class CategorieClientServiceImpl implements CategorieClientService {
    private final CategorieClientDao categorieClientDao;
    private final CategorieClientMapper categorieClientMapper;

    public CategorieClientServiceImpl(CategorieClientDao categorieClientDao, CategorieClientMapper categorieClientMapper) {
        this.categorieClientDao = categorieClientDao;
        this.categorieClientMapper = categorieClientMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"codeCategorieClient");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<CategorieClient> CategorieClientPage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                CategorieClientPage = categorieClientDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                CategorieClientPage = categorieClientDao.findAll(pageable);
            }
            List<CategorieClientDto> content = CategorieClientPage.getContent().stream().map(categorieClientMapper::deCatClient).collect(Collectors.toList());
            DataTablesResponse<CategorieClientDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)CategorieClientPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)CategorieClientPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Categorie Clients par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"codeCategorieClient");
            return ResponseHandler.generateResponse(
                    "Liste de tous les Categorie Clients",
                    HttpStatus.OK,
                    categorieClientDao.findAll(sort).stream().map(categorieClientMapper::deCatClient).collect(Collectors.toList()));
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
                    "CategorieClient dont code = " + id.toString(),
                    HttpStatus.OK,
                    categorieClientMapper.deCatClient(afficherSelonId(id)));
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
    public CategorieClient afficherSelonId(Long id) {
        return categorieClientDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", id.toString()));
    }
    @Override
    public ResponseEntity<Object> creer(CategorieClientDto CategorieClientDto) {
        try {
            CategorieClientDto.setSupprimer(false);
            CategorieClient CategorieClient = categorieClientMapper.deCatClientDto(CategorieClientDto);
            CategorieClient CategorieClientSaved = categorieClientDao.save(CategorieClient);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    categorieClientMapper.deCatClient(CategorieClientSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(CategorieClientDto CategorieClientDto) {
        try {
            //if(!CategorieClientDao.existsById(CategorieClientDto.getIdCategorieClient()))
              //  throw  new EntityNotFoundException(CategorieClient.class, "id", CategorieClientDto.getIdCategorieClient().toString());
//            CategorieClientDao.findById(code);
            CategorieClientDto.setSupprimer(false);
            CategorieClient CategorieClient = categorieClientMapper.deCatClientDto(CategorieClientDto);
            CategorieClient = categorieClientDao.save(CategorieClient);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    categorieClientMapper.deCatClient(CategorieClient));
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
            categorieClientDao.deleteById(id);
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
