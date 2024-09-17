package com.ged.service.standard.impl.revuecompte;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.standard.revuecompte.HistoriqueNumeroCompteDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.revuecompte.HistoriqueNumeroCompteDto;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.Personne;
import com.ged.entity.standard.revuecompte.HistoriqueNumeroCompte;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.mapper.standard.revuecompte.HistoriqueNumeroCompteMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.revuecompte.HistoriqueNumeroCompteService;
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
public class HistoriqueNumeroCompteServiceImpl implements HistoriqueNumeroCompteService {
    private final HistoriqueNumeroCompteDao HistoriqueNumeroCompteDao;
    private final HistoriqueNumeroCompteMapper HistoriqueNumeroCompteMapper;
    private final PersonneMapper personneMapper;
    private final PersonneDao personneDao;

    public HistoriqueNumeroCompteServiceImpl(HistoriqueNumeroCompteDao HistoriqueNumeroCompteDao, HistoriqueNumeroCompteMapper HistoriqueNumeroCompteMapper, PersonneMapper PersonneMapper, PersonneDao PersonneDao) {
        this.HistoriqueNumeroCompteDao = HistoriqueNumeroCompteDao;
        this.HistoriqueNumeroCompteMapper = HistoriqueNumeroCompteMapper;
        this.personneMapper = PersonneMapper;
        this.personneDao = PersonneDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
//            Sort sort=Sort.by(Sort.Direction.ASC,"libelleHistoriqueNumeroCompte");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<HistoriqueNumeroCompte> HistoriqueNumeroComptePage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                HistoriqueNumeroComptePage = HistoriqueNumeroCompteDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                HistoriqueNumeroComptePage = HistoriqueNumeroCompteDao.findAll(pageable);
            }
            List<HistoriqueNumeroCompteDto> content = HistoriqueNumeroComptePage.getContent().stream().map(HistoriqueNumeroCompteMapper::deHistoriqueNumeroCompte).collect(Collectors.toList());
            DataTablesResponse<HistoriqueNumeroCompteDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)HistoriqueNumeroComptePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)HistoriqueNumeroComptePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des HistoriqueNumeroComptes par page datatable",
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
//            Sort sort=Sort.by(Sort.Direction.ASC,"libelleHistoriqueNumeroCompte");
            return ResponseHandler.generateResponse(
                    "Liste de tous les HistoriqueNumeroComptes",
                    HttpStatus.OK,
                    HistoriqueNumeroCompteDao.findAll().stream().map(HistoriqueNumeroCompteMapper::deHistoriqueNumeroCompte).collect(Collectors.toList()));
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
                    "HistoriqueNumeroCompte dont code = " + id.toString(),
                    HttpStatus.OK,
                    HistoriqueNumeroCompteMapper.deHistoriqueNumeroCompte(afficherSelonId(id)));
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
    public HistoriqueNumeroCompte afficherSelonId(Long id) {
        return HistoriqueNumeroCompteDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", id.toString()));
    }
    @Override
    public ResponseEntity<Object> creer(HistoriqueNumeroCompteDto HistoriqueNumeroCompteDto) {
        try {
            HistoriqueNumeroCompteDto.setSupprimer(false);
            HistoriqueNumeroCompte HistoriqueNumeroCompte = HistoriqueNumeroCompteMapper.deHistoriqueNumeroCompteDto(HistoriqueNumeroCompteDto);
            if(HistoriqueNumeroCompteDto.getPersonne()!=null)
            {
                Personne Personne=personneDao.findById(HistoriqueNumeroCompteDto.getPersonne().getIdPersonne()).orElseThrow();
                HistoriqueNumeroCompte.setPersonne(Personne);
            }
            HistoriqueNumeroCompte HistoriqueNumeroCompteSaved = HistoriqueNumeroCompteDao.save(HistoriqueNumeroCompte);
            
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    HistoriqueNumeroCompteMapper.deHistoriqueNumeroCompte(HistoriqueNumeroCompteSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(HistoriqueNumeroCompteDto HistoriqueNumeroCompteDto) {
        try {
            //if(!HistoriqueNumeroCompteDao.existsById(HistoriqueNumeroCompteDto.getIdHistoriqueNumeroCompte()))
              //  throw  new EntityNotFoundException(HistoriqueNumeroCompte.class, "id", HistoriqueNumeroCompteDto.getIdHistoriqueNumeroCompte().toString());
//            HistoriqueNumeroCompteDao.findById(code);
            HistoriqueNumeroCompteDto.setSupprimer(false);
            HistoriqueNumeroCompte HistoriqueNumeroCompte = HistoriqueNumeroCompteMapper.deHistoriqueNumeroCompteDto(HistoriqueNumeroCompteDto);

            if(HistoriqueNumeroCompteDto.getPersonne()!=null)
            {
                Personne Personne=personneDao.findById(HistoriqueNumeroCompteDto.getPersonne().getIdPersonne()).orElseThrow();
                HistoriqueNumeroCompte.setPersonne(Personne);
            }
            HistoriqueNumeroCompte = HistoriqueNumeroCompteDao.save(HistoriqueNumeroCompte);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    HistoriqueNumeroCompteMapper.deHistoriqueNumeroCompte(HistoriqueNumeroCompte));
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
            HistoriqueNumeroCompteDao.deleteById(id);
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
