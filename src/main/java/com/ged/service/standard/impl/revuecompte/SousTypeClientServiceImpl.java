package com.ged.service.standard.impl.revuecompte;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.revuecompte.SousTypeClientDao;
import com.ged.dao.standard.revuecompte.TypeClientDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.SousTypeClientDto;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.revuecompte.SousTypeClient;
import com.ged.entity.standard.revuecompte.TypeClient;
import com.ged.mapper.standard.revuecompte.SousTypeClientMapper;
import com.ged.mapper.standard.revuecompte.TypeClientMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.revuecompte.SousTypeClientService;
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
public class SousTypeClientServiceImpl implements SousTypeClientService {
    private final SousTypeClientDao SousTypeClientDao;
    private final SousTypeClientMapper SousTypeClientMapper;
    private final TypeClientMapper TypeClientMapper;
    private final TypeClientDao TypeClientDao;

    public SousTypeClientServiceImpl(SousTypeClientDao SousTypeClientDao, SousTypeClientMapper SousTypeClientMapper, TypeClientMapper TypeClientMapper, TypeClientDao TypeClientDao) {
        this.SousTypeClientDao = SousTypeClientDao;
        this.SousTypeClientMapper = SousTypeClientMapper;
        this.TypeClientMapper = TypeClientMapper;
        this.TypeClientDao = TypeClientDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"code");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<SousTypeClient> SousTypeClientPage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                SousTypeClientPage = SousTypeClientDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                SousTypeClientPage = SousTypeClientDao.findAll(pageable);
            }
            List<SousTypeClientDto> content = SousTypeClientPage.getContent().stream().map(SousTypeClientMapper::deSousTypeClient).collect(Collectors.toList());
            DataTablesResponse<SousTypeClientDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)SousTypeClientPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)SousTypeClientPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Sous Type Clients par page datatable",
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
                    "Liste de tous les Sous Type Clients",
                    HttpStatus.OK,
                    SousTypeClientDao.findAll(sort).stream().map(SousTypeClientMapper::deSousTypeClient).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficherPersonnePhysique() {
        try {
            return ResponseHandler.generateResponse(
                    "Liste des sous type client dont libelleTypeClient =Personne physique ",
                    HttpStatus.OK,
                    SousTypeClientDao.afficherPersonnePhysique().stream().map(SousTypeClientMapper::deSousTypeClient).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficherAutresTypePersonne() {
        try {
            return ResponseHandler.generateResponse(
                    "Liste des sous type client dont libelleTypeClient =Personne physique ",
                    HttpStatus.OK,
                    SousTypeClientDao.afficherAutresTypeCLient().stream().map(SousTypeClientMapper::deSousTypeClient).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "SousTypeClient dont code = " + id.toString(),
                    HttpStatus.OK,
                    SousTypeClientMapper.deSousTypeClient(afficherSelonId(id)));
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
    public SousTypeClient afficherSelonId(Long id) {
        return SousTypeClientDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", id.toString()));
    }
    @Override
    public ResponseEntity<Object> creer(SousTypeClientDto SousTypeClientDto) {
        try {
            SousTypeClientDto.setSupprimer(false);
            SousTypeClient SousTypeClient = SousTypeClientMapper.deSousTypeClientDto(SousTypeClientDto);
            if(SousTypeClientDto.getTypeClient()!=null)
            {
                TypeClient TypeClient=TypeClientDao.findById(SousTypeClientDto.getTypeClient().getIdTypeClient()).orElseThrow();
                SousTypeClient.setTypeClient(TypeClient);
            }
            SousTypeClient SousTypeClientSaved = SousTypeClientDao.save(SousTypeClient);
            
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    SousTypeClientMapper.deSousTypeClient(SousTypeClientSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(SousTypeClientDto SousTypeClientDto) {
        try {
            //if(!SousTypeClientDao.existsById(SousTypeClientDto.getIdSousTypeClient()))
              //  throw  new EntityNotFoundException(SousTypeClient.class, "id", SousTypeClientDto.getIdSousTypeClient().toString());
//            SousTypeClientDao.findById(code);
            SousTypeClientDto.setSupprimer(false);
            SousTypeClient SousTypeClient = SousTypeClientMapper.deSousTypeClientDto(SousTypeClientDto);

            if(SousTypeClientDto.getTypeClient()!=null)
            {
                TypeClient TypeClient=TypeClientDao.findById(SousTypeClientDto.getTypeClient().getIdTypeClient()).orElseThrow();
                SousTypeClient.setTypeClient(TypeClient);
            }
            SousTypeClient = SousTypeClientDao.save(SousTypeClient);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    SousTypeClientMapper.deSousTypeClient(SousTypeClient));
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
            SousTypeClientDao.deleteById(id);
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
