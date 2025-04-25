package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.TypeOrdreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.TypeOrdreDto;
import com.ged.entity.opcciel.TypeOrdre;
import com.ged.mapper.opcciel.TypeOrdreMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.TypeOrdreService;
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
public class TypeOrdreServiceImpl implements TypeOrdreService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final TypeOrdreDao typeOrdreDao;
    private final TypeOrdreMapper typeOrdreMapper;

    public TypeOrdreServiceImpl(TypeOrdreDao TypeOrdreDao, TypeOrdreMapper TypeOrdreMapper){

        this.typeOrdreDao = TypeOrdreDao;
        this.typeOrdreMapper = TypeOrdreMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"codeTypeOrdre");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<TypeOrdre> typeOrdrePage;
            typeOrdrePage = typeOrdreDao.findBySupprimer(false,pageable);
            List<TypeOrdreDto> content = typeOrdrePage.getContent().stream().map(typeOrdreMapper::deTypeOrdre).collect(Collectors.toList());
            DataTablesResponse<TypeOrdreDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)typeOrdrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)typeOrdrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Type Ordre par page datatable",
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
    public Page<TypeOrdreDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleTypeOrdre");
            List<TypeOrdreDto> TypeOrdreDtos = typeOrdreDao.findBySupprimerOrderByLibelleTypeOrdre(false).stream().map(typeOrdreMapper::deTypeOrdre).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Types Ordre",
                    HttpStatus.OK,
                    TypeOrdreDtos);
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
    public TypeOrdre afficherSelonId(Long id) {
        return typeOrdreDao.findById(id).orElseThrow(() -> new EntityNotFoundException(TypeOrdre.class, "ID",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "TypeOrdre dont ID = " + id,
                    HttpStatus.OK,
                    typeOrdreMapper.deTypeOrdre(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(TypeOrdreDto TypeOrdreDto) {
        try {
            TypeOrdreDto.setSupprimer(false);
            TypeOrdre  TypeOrdre = typeOrdreMapper.deTypeOrdreDto(TypeOrdreDto);
            TypeOrdre = typeOrdreDao.save(TypeOrdre);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    typeOrdreMapper.deTypeOrdre(TypeOrdre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TypeOrdreDto typeOrdreDto) {
        try {
//            if(!typeOrdreDao.existsById(typeOrdreDto.getCodeTypeOrdre()))
//                throw  new EntityNotFoundException(TypeOrdre.class, "ID", typeOrdreDto.getCodeTypeOrdre().toString());
            typeOrdreDto.setSupprimer(false);
            TypeOrdre typeOrdre = typeOrdreMapper.deTypeOrdreDto(typeOrdreDto);
            typeOrdre= typeOrdreDao.save(typeOrdre);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    typeOrdreMapper.deTypeOrdre(typeOrdre));
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
            typeOrdreDao.deleteById(id);
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
