package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.IbDao;
import com.ged.dao.opcciel.comptabilite.TypeIbDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.IbDto;
import com.ged.entity.opcciel.comptabilite.Ib;
import com.ged.entity.opcciel.comptabilite.TypeIb;
import com.ged.mapper.opcciel.IbMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.IbService;
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
public class IbServiceImpl implements IbService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final IbDao IbDao;
    private final TypeIbDao typeIbDao;
    private final IbMapper IbMapper;

    public IbServiceImpl(IbDao IbDao, TypeIbDao typeIbDao, IbMapper IbMapper){
        this.IbDao = IbDao;
        this.typeIbDao = typeIbDao;
        this.IbMapper = IbMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleIb");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Ib> IbPage;
            IbPage = IbDao.findAll(pageable);
            List<IbDto> content = IbPage.getContent().stream().map(IbMapper::deIb).collect(Collectors.toList());
            DataTablesResponse<IbDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)IbPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)IbPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Ibs par page datatable",
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
    public Page<IbDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleIb");
            List<IbDto> IbDtos = IbDao.findAll(sort).stream().map(IbMapper::deIb).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Ibs ",
                    HttpStatus.OK,
                    IbDtos);
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
    public Ib afficherSelonId(String codeIb) {
        return IbDao.findById(codeIb).orElseThrow(() -> new EntityNotFoundException(Ib.class, "code",codeIb));
    }

    @Override
    public ResponseEntity<Object> afficher(String codeIb) {
        try {
            return ResponseHandler.generateResponse(
                    "Ib dont CODE = " + codeIb,
                    HttpStatus.OK,
                    IbMapper.deIb(afficherSelonId(codeIb)));
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
    public ResponseEntity<Object> creer(IbDto IbDto) {
        try {
            IbDto.setSupprimer(false);
            Ib  Ib =IbMapper.deIbDto(IbDto);
            TypeIb typeIb=new TypeIb();
            if(IbDto.getTypeIb()!=null)
            {
                typeIb=typeIbDao.findById(IbDto.getTypeIb().getCodeTypeIb()).orElseThrow();
                Ib.setTypeIb(typeIb);
            }
            Ib = IbDao.save(Ib);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    IbMapper.deIb(Ib));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(IbDto IbDto) {
        try {
            if(!IbDao.existsById(IbDto.getCodeIB()))
                throw  new EntityNotFoundException(Ib.class, "code", IbDto.getCodeIB().toString());
            IbDto.setSupprimer(false);
            Ib Ib =IbMapper.deIbDto(IbDto);
            TypeIb typeIb=new TypeIb();
            if(IbDto.getTypeIb()!=null)
            {
                typeIb=typeIbDao.findById(IbDto.getTypeIb().getCodeTypeIb()).orElseThrow();
                Ib.setTypeIb(typeIb);
            }
            Ib=IbDao.save(Ib);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    IbMapper.deIb(Ib));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String codeIb) {
        try {
            IbDao.deleteById(codeIb);
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
