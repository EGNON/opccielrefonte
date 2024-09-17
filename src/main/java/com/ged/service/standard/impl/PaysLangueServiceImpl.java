package com.ged.service.standard.impl;

import com.ged.dao.standard.LangueDao;
import com.ged.dao.standard.PaysDao;
import com.ged.dao.standard.PaysLangueDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PaysLangueDto;
import com.ged.entity.standard.ClePaysLangue;
import com.ged.entity.standard.Langue;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.PaysLangue;
import com.ged.mapper.standard.PaysLangueMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.PaysLangueService;
import jakarta.transaction.Transactional;
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
public class PaysLangueServiceImpl implements PaysLangueService {
    private final PaysLangueDao paysLangueDao;
    private final PaysDao paysDao;
    private final LangueDao langueDao;
    private final PaysLangueMapper paysLangueMapper;

    public PaysLangueServiceImpl(PaysLangueDao paysLangueDao, PaysDao paysDao, LangueDao langueDao, PaysLangueMapper paysLangueMapper) {
        this.paysLangueDao = paysLangueDao;
        this.paysDao = paysDao;
        this.langueDao = langueDao;
        this.paysLangueMapper = paysLangueMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"numPaysLangue");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<PaysLangue> PaysLanguePage;
           // if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            //{
              //  PaysLanguePage = PaysLangueDao.findByLibellePaysLangueContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
            //}
            //else {
                PaysLanguePage = paysLangueDao.findAll(pageable);
            //}
            List<PaysLangueDto> content = PaysLanguePage.getContent().stream().map(paysLangueMapper::dePaysLangue).collect(Collectors.toList());
            DataTablesResponse<PaysLangueDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)PaysLanguePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)PaysLanguePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des pays et langues par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"numPaysLangue");
            return ResponseHandler.generateResponse(
                    "Liste de tous les pays et langues",
                    HttpStatus.OK,
                    paysLangueDao.findAll(sort).stream().map(paysLangueMapper::dePaysLangue).collect(Collectors.toList()));
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
    public PaysLangue afficherSelonId(ClePaysLangue idPaysLangue) {
        return paysLangueDao.findById(idPaysLangue).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(ClePaysLangue codePaysLangue) {
        try {
            return ResponseHandler.generateResponse(
                    "Modele formule dont id = " + codePaysLangue,
                    HttpStatus.OK,
                    paysLangueMapper.dePaysLangue(afficherSelonId(codePaysLangue)));
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
    public ResponseEntity<Object> afficherSelonLangue(Long idLangue) {
        try {
            Langue Langue=langueDao.findById(idLangue).orElseThrow();
            return ResponseHandler.generateResponse(
                    "Liste des pays et langues selon "+idLangue,
                    HttpStatus.OK,
                    paysLangueDao.findByLangue(Langue).stream().map(paysLangueMapper::dePaysLangue).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficherSelonPays(Long idPays) {
        try {
            Pays pays=paysDao.findById(idPays).orElseThrow();
            return ResponseHandler.generateResponse(
                    "Liste des pays et langues selon "+idPays,
                    HttpStatus.OK,
                    paysLangueDao.findByPays(pays).stream().map(paysLangueMapper::dePaysLangue).collect(Collectors.toList()));
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
    public ResponseEntity<Object> creer(PaysLangueDto PaysLangueDto) {
        try {
            PaysLangueDto.setSupprimer(false);
            PaysLangue PaysLangue = paysLangueMapper.dePaysLangueDto(PaysLangueDto);
            ClePaysLangue clePaysLangue=new ClePaysLangue();
            clePaysLangue.setIdLangue(PaysLangueDto.getLangue().getIdLangue());
            clePaysLangue.setIdPays(PaysLangueDto.getPays().getIdPays());
            PaysLangue.setIdPaysLangue(clePaysLangue);
            Langue Langue=new Langue();
            Pays pays=new Pays();
            if(PaysLangueDto.getLangue()!=null){
                Langue=langueDao.findById(PaysLangueDto.getLangue().getIdLangue()).orElseThrow();
                PaysLangue.setLangue(Langue);
            }
            if(PaysLangueDto.getPays()!=null){
                pays=paysDao.findById(PaysLangueDto.getPays().getIdPays()).orElseThrow();
                PaysLangue.setPays(pays);
            }
            PaysLangue = paysLangueDao.save(PaysLangue);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    paysLangueMapper.dePaysLangue(PaysLangue));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(PaysLangueDto PaysLangueDto) {
        try {
            PaysLangueDto.setSupprimer(false);
            PaysLangue PaysLangue = paysLangueMapper.dePaysLangueDto(PaysLangueDto);

            ClePaysLangue clePaysLangue=new ClePaysLangue();
            clePaysLangue.setIdLangue(PaysLangueDto.getLangue().getIdLangue());
            clePaysLangue.setIdPays(PaysLangueDto.getPays().getIdPays());
            PaysLangue.setIdPaysLangue(clePaysLangue);
            Langue Langue=new Langue();
            Pays pays=new Pays();
            if(PaysLangueDto.getLangue()!=null){
                Langue=langueDao.findById(PaysLangueDto.getLangue().getIdLangue()).orElseThrow();
                PaysLangue.setLangue(Langue);
            }
            if(PaysLangueDto.getPays()!=null){
                pays=paysDao.findById(PaysLangueDto.getPays().getIdPays()).orElseThrow();
                PaysLangue.setPays(pays);
            }
            PaysLangue = paysLangueDao.save(PaysLangue);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    paysLangueMapper.dePaysLangue(PaysLangue));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(ClePaysLangue codePaysLangue) {
        try {
            paysLangueDao.deleteById(codePaysLangue);
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

    @Override
    public ResponseEntity<Object> supprimerSelonLangue(Long idLangue) {
        try {
            Langue Langue=langueDao.findById(idLangue).orElseThrow();
            paysLangueDao.deleteByLangue(Langue);
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
