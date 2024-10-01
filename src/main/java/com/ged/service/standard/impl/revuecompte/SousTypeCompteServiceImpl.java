package com.ged.service.standard.impl.revuecompte;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.revuecompte.SousTypeCompteDao;
import com.ged.dao.standard.revuecompte.TypeCompteDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.SousTypeCompteDto;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.revuecompte.SousTypeCompte;
import com.ged.entity.standard.revuecompte.TypeCompte;
import com.ged.mapper.standard.revuecompte.SousTypeCompteMapper;
import com.ged.mapper.standard.revuecompte.TypeCompteMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.revuecompte.SousTypeCompteService;
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
public class SousTypeCompteServiceImpl implements SousTypeCompteService {
    private final SousTypeCompteDao SousTypeCompteDao;
    private final SousTypeCompteMapper SousTypeCompteMapper;
    private final TypeCompteMapper typeCompteMapper;
    private final TypeCompteDao typeCompteDao;

    public SousTypeCompteServiceImpl(SousTypeCompteDao SousTypeCompteDao, SousTypeCompteMapper SousTypeCompteMapper, TypeCompteMapper typeCompteMapper, TypeCompteDao typeCompteDao) {
        this.SousTypeCompteDao = SousTypeCompteDao;
        this.SousTypeCompteMapper = SousTypeCompteMapper;
        this.typeCompteMapper = typeCompteMapper;
        this.typeCompteDao = typeCompteDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"code");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<SousTypeCompte> SousTypeComptePage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                SousTypeComptePage = SousTypeCompteDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                SousTypeComptePage = SousTypeCompteDao.findAll(pageable);
            }
            List<SousTypeCompteDto> content = SousTypeComptePage.getContent().stream().map(SousTypeCompteMapper::deSousTypeCompte).collect(Collectors.toList());
            DataTablesResponse<SousTypeCompteDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)SousTypeComptePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)SousTypeComptePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Sous Type Comptes par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"code");
            return ResponseHandler.generateResponse(
                    "Liste de tous les Sous Type Comptes",
                    HttpStatus.OK,
                    SousTypeCompteDao.findAll(sort).stream().map(SousTypeCompteMapper::deSousTypeCompte).collect(Collectors.toList()));
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
                    "SousTypeCompte dont code = " + id.toString(),
                    HttpStatus.OK,
                    SousTypeCompteMapper.deSousTypeCompte(afficherSelonId(id)));
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
    public SousTypeCompte afficherSelonId(Long id) {
        return SousTypeCompteDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", id.toString()));
    }
    @Override
    public ResponseEntity<Object> creer(SousTypeCompteDto SousTypeCompteDto) {
        try {
            SousTypeCompteDto.setSupprimer(false);
            SousTypeCompte SousTypeCompte = SousTypeCompteMapper.deSousTypeCompteDto(SousTypeCompteDto);
            if(SousTypeCompteDto.getTypeCompte()!=null)
            {
                TypeCompte typeCompte=typeCompteDao.findById(SousTypeCompteDto.getTypeCompte().getIdTypeCompte()).orElseThrow();
                SousTypeCompte.setTypeCompte(typeCompte);
            }
            SousTypeCompte SousTypeCompteSaved = SousTypeCompteDao.save(SousTypeCompte);

            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    SousTypeCompteMapper.deSousTypeCompte(SousTypeCompteSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(SousTypeCompteDto SousTypeCompteDto) {
        try {
            //if(!SousTypeCompteDao.existsById(SousTypeCompteDto.getIdSousTypeCompte()))
              //  throw  new EntityNotFoundException(SousTypeCompte.class, "id", SousTypeCompteDto.getIdSousTypeCompte().toString());
//            SousTypeCompteDao.findById(code);
            SousTypeCompteDto.setSupprimer(false);
            SousTypeCompte SousTypeCompte = SousTypeCompteMapper.deSousTypeCompteDto(SousTypeCompteDto);

            if(SousTypeCompteDto.getTypeCompte()!=null)
            {
                TypeCompte typeCompte=typeCompteDao.findById(SousTypeCompteDto.getTypeCompte().getIdTypeCompte()).orElseThrow();
                SousTypeCompte.setTypeCompte(typeCompte);
            }
            SousTypeCompte = SousTypeCompteDao.save(SousTypeCompte);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    SousTypeCompteMapper.deSousTypeCompte(SousTypeCompte));
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
            SousTypeCompteDao.deleteById(id);
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
