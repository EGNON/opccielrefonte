package com.ged.service.standard.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.ModeleMsgAlerteDao;
import com.ged.dao.standard.TypeModeleDao;
import com.ged.dao.standard.TypeModeleMessageDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.TypeModeleMessageDto;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.ModeleMsgAlerte;
import com.ged.entity.standard.TypeModele;
import com.ged.entity.standard.TypeModeleMessage;
import com.ged.mapper.standard.TypeModeleMessageMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.TypeModeleMessageService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeModeleMessageServiceImpl implements TypeModeleMessageService {
    private final TypeModeleMessageDao TypeModeleMessageDao;
    private final TypeModeleMessageMapper TypeModeleMessageMapper;
    private final TypeModeleDao typeModeleDao;
    private final ModeleMsgAlerteDao modeleMsgAlerteDao;

    public TypeModeleMessageServiceImpl(TypeModeleMessageDao TypeModeleMessageDao, TypeModeleMessageMapper TypeModeleMessageMapper, TypeModeleDao typeModeleDao, ModeleMsgAlerteDao modeleMsgAlerteDao) {
        this.TypeModeleMessageDao = TypeModeleMessageDao;
        this.TypeModeleMessageMapper = TypeModeleMessageMapper;
        this.typeModeleDao = typeModeleDao;
        this.modeleMsgAlerteDao = modeleMsgAlerteDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"codeTypeModeleMessage");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<TypeModeleMessage> TypeModeleMessagePage;
            /*if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                TypeModeleMessagePage = TypeModeleMessageDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {*/
                TypeModeleMessagePage = TypeModeleMessageDao.findAll(pageable);
//            }
            List<TypeModeleMessageDto> content = TypeModeleMessagePage.getContent().stream().map(TypeModeleMessageMapper::deTypeModeleMessage).collect(Collectors.toList());
            DataTablesResponse<TypeModeleMessageDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)TypeModeleMessagePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)TypeModeleMessagePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type ModeleMessages par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"codeTypeModeleMessage");
            return ResponseHandler.generateResponse(
                    "Liste de tous les Type ModeleMessages",
                    HttpStatus.OK,
                    TypeModeleMessageDao.findAll().stream().map(TypeModeleMessageMapper::deTypeModeleMessage).collect(Collectors.toList()));
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
                    "TypeModeleMessage dont code = " + id.toString(),
                    HttpStatus.OK,
                    TypeModeleMessageMapper.deTypeModeleMessage(afficherSelonId(id)));
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
    public TypeModeleMessage afficherSelonId(Long id) {
        return TypeModeleMessageDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", id.toString()));
    }
    @Override
    public ResponseEntity<Object> creer(TypeModeleMessageDto TypeModeleMessageDto) {
        try {
            TypeModeleMessageDto.setSupprimer(false);
            TypeModeleMessage TypeModeleMessage = TypeModeleMessageMapper.deTypeModeleMessageDto(TypeModeleMessageDto);
            if(TypeModeleMessageDto.getTypeModele()!=null)
            {
                TypeModele typeModele=typeModeleDao.findById(TypeModeleMessageDto.getTypeModele().getIdTypeModele()).orElseThrow();
                TypeModeleMessage.setTypeModele(typeModele);
            }
            if(TypeModeleMessageDto.getModeleMsgAlerte()!=null)
            {
                ModeleMsgAlerte modeleMsgAlerte=modeleMsgAlerteDao.findById(TypeModeleMessageDto.getModeleMsgAlerte().getIdModele()).orElseThrow();
                TypeModeleMessage.setModeleMsgAlerte(modeleMsgAlerte);
            }
            TypeModeleMessageDto.setDateTypeModeleMessage(LocalDateTime.now());
            TypeModeleMessage TypeModeleMessageSaved = TypeModeleMessageDao.save(TypeModeleMessage);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    TypeModeleMessageMapper.deTypeModeleMessage(TypeModeleMessageSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeModeleMessageDto TypeModeleMessageDto) {
        try {
            //if(!TypeModeleMessageDao.existsById(TypeModeleMessageDto.getIdTypeModeleMessage()))
              //  throw  new EntityNotFoundException(TypeModeleMessage.class, "id", TypeModeleMessageDto.getIdTypeModeleMessage().toString());
//            TypeModeleMessageDao.findById(code);
            TypeModeleMessageDto.setSupprimer(false);
            TypeModeleMessage TypeModeleMessage = TypeModeleMessageMapper.deTypeModeleMessageDto(TypeModeleMessageDto);
            if(TypeModeleMessageDto.getTypeModele()!=null)
            {
                TypeModele typeModele=typeModeleDao.findById(TypeModeleMessageDto.getTypeModele().getIdTypeModele()).orElseThrow();
                TypeModeleMessage.setTypeModele(typeModele);
            }
            if(TypeModeleMessageDto.getModeleMsgAlerte()!=null)
            {
                ModeleMsgAlerte modeleMsgAlerte=modeleMsgAlerteDao.findById(TypeModeleMessageDto.getModeleMsgAlerte().getIdModele()).orElseThrow();
                TypeModeleMessage.setModeleMsgAlerte(modeleMsgAlerte);
            }
            TypeModeleMessageDto.setDateTypeModeleMessage(LocalDateTime.now());
            TypeModeleMessage = TypeModeleMessageDao.save(TypeModeleMessage);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    TypeModeleMessageMapper.deTypeModeleMessage(TypeModeleMessage));
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
            TypeModeleMessageDao.deleteById(id);
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
