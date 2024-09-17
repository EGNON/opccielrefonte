package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.BanqueDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.BanqueDto;
import com.ged.entity.titresciel.Banque;
import com.ged.mapper.titresciel.BanqueMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.BanqueService;
import jakarta.persistence.EntityManager;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BanqueServiceImpl implements BanqueService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final BanqueDao banqueDao;
    private final BanqueMapper banqueMapper;

    public BanqueServiceImpl(BanqueDao BanqueDao, BanqueMapper BanqueMapper){
        this.banqueDao = BanqueDao;

        this.banqueMapper = BanqueMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomBanque");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Banque> BanquePage;
            BanquePage = banqueDao.findAll(pageable);
            List<BanqueDto> content = BanquePage.getContent().stream().map(banqueMapper::deBanque).collect(Collectors.toList());
            DataTablesResponse<BanqueDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)BanquePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)BanquePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des banques par page datatable",
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
    public Page<BanqueDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomBanque");
            List<BanqueDto> Banques = banqueDao.findAll(sort).stream().map(banqueMapper::deBanque).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des banques",
                    HttpStatus.OK,
                    Banques);
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
    public Banque afficherSelonId(Long id) {
        return banqueDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Banque.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Banque dont ID = " + id,
                    HttpStatus.OK,
                    banqueMapper.deBanque(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(BanqueDto BanqueDto) {
        try {
            Banque Banque = banqueMapper.deBanqueDto(BanqueDto);
            Banque = banqueDao.save(Banque);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    banqueMapper.deBanque(Banque));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(BanqueDto BanqueDto) {
        try {
            if(!banqueDao.existsById(BanqueDto.getIdBanque()))
                throw  new EntityNotFoundException(Banque.class, "ID", BanqueDto.getIdBanque().toString());
            Banque Banque = banqueMapper.deBanqueDto(BanqueDto);
            Banque = banqueDao.save(Banque);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    banqueMapper.deBanque(Banque));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long idOperation) {
        try {
            banqueDao.deleteById(idOperation);
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
