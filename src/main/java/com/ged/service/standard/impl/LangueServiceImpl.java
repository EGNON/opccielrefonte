package com.ged.service.standard.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.LangueDao;
import com.ged.dao.standard.PaysDao;
import com.ged.dao.standard.PaysLangueDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.LangueDto;
import com.ged.dto.standard.PaysLangueDto;
import com.ged.entity.standard.ClePaysLangue;
import com.ged.entity.standard.Langue;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.PaysLangue;
import com.ged.mapper.standard.LangueMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.LangueService;
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

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LangueServiceImpl implements LangueService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final LangueDao langueDao;
    private final PaysDao paysDao;
    private final PaysLangueDao paysLangueDao;
    private final LangueMapper langueMapper;

    public LangueServiceImpl(LangueDao LangueDao, PaysDao paysDao, PaysLangueDao paysLangueDao, LangueMapper LangueMapper){
        this.langueDao = LangueDao;
        this.paysDao = paysDao;
        this.paysLangueDao = paysLangueDao;

        this.langueMapper = LangueMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleLangue");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Langue> LanguePage;
            LanguePage = langueDao.findAll(pageable);
            List<LangueDto> content = LanguePage.getContent().stream().map(langueMapper::deLangue).collect(Collectors.toList());
            DataTablesResponse<LangueDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)LanguePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)LanguePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des langues par page datatable",
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
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleLangue");
            List<LangueDto> Langues = langueDao.findAll(sort).stream().map(langueMapper::deLangue).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des langues",
                    HttpStatus.OK,
                    Langues);
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
    public ResponseEntity<Object> afficher(int page, int size) {
        return null;
    }

    @Override
    public Langue afficherSelonId(Long id) {
        return langueDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Langue.class, "code",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Langue dont id = " + id.toString(),
                    HttpStatus.OK,
                    langueMapper.deLangue(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(LangueDto LangueDto) {
        try {
            LangueDto.setSupprimer(false);
            Langue Langue = langueMapper.deLangueDto(LangueDto);
//            if(Langue.getPays()!=null){
//                Pays pays=paysDao.findByLibelleFrIgnoreCase(LangueDto.getPays().getLibelleFr()).orElseThrow();
//                Langue.setPays(pays);
//            }

            Langue = langueDao.save(Langue);
            for(PaysLangueDto o:LangueDto.getPaysLangues()){
                PaysLangue paysLangue=new PaysLangue();
                ClePaysLangue clePaysLangue=new ClePaysLangue();
                clePaysLangue.setIdLangue(Langue.getIdLangue());
                clePaysLangue.setIdPays(o.getPays().getIdPays());
                paysLangue.setIdPaysLangue(clePaysLangue);

                paysLangue.setLangue(Langue);
                if(o.getPays()!=null){
                    Pays pays=paysDao.findById(o.getPays().getIdPays()).orElseThrow();
                    paysLangue.setPays(pays);
                }
                paysLangue.setSupprimer(false);
                paysLangueDao.save(paysLangue);
            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    langueMapper.deLangue(Langue));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(LangueDto LangueDto) {
        try {
            LangueDto.setSupprimer(false);
            Langue Langue = langueMapper.deLangueDto(LangueDto);
//            if(Langue.getPays()!=null){
//                Pays pays=paysDao.findByLibelleFrIgnoreCase(LangueDto.getPays().getLibelleFr()).orElseThrow();
//                Langue.setPays(pays);
//            }
            Langue = langueDao.save(Langue);
            /*Langue langue=langueDao.findById(Langue.getIdLangue()).orElseThrow();
            System.out.println(LangueDto.getPaysLangues().size());
            paysLangueDao.deleteByLangue(langue);*/

            for(PaysLangueDto o:LangueDto.getPaysLangues()){
                System.out.println(o.getPays().getLibelleFr());
                PaysLangue paysLangue=new PaysLangue();
                ClePaysLangue clePaysLangue=new ClePaysLangue();
                clePaysLangue.setIdLangue(Langue.getIdLangue());
                clePaysLangue.setIdPays(o.getPays().getIdPays());
                paysLangue.setIdPaysLangue(clePaysLangue);

                paysLangue.setLangue(Langue);
                if(o.getPays()!=null){
                    Pays pays=paysDao.findById(o.getPays().getIdPays()).orElseThrow();
                    paysLangue.setPays(pays);
                }
                paysLangue.setSupprimer(false);
                paysLangueDao.save(paysLangue);
            }
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    langueMapper.deLangue(Langue));
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
            langueDao.deleteById(id);
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
