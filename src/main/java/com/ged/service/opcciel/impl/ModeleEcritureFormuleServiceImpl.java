package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.FormuleDao;
import com.ged.dao.opcciel.comptabilite.ModeleEcritureDao;
import com.ged.dao.opcciel.comptabilite.ModeleEcritureFormuleDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureFormuleDto;
import com.ged.entity.opcciel.comptabilite.CleModeleEcritureFormule;
import com.ged.entity.opcciel.comptabilite.Formule;
import com.ged.entity.opcciel.comptabilite.ModeleEcriture;
import com.ged.entity.opcciel.comptabilite.ModeleEcritureFormule;
import com.ged.mapper.opcciel.ModeleEcritureFormuleMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.ModeleEcritureFormuleService;
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
public class ModeleEcritureFormuleServiceImpl implements ModeleEcritureFormuleService {
    private final ModeleEcritureFormuleDao ModeleEcritureFormuleDao;
    private final ModeleEcritureDao modeleEcritureDao;
    private final FormuleDao formuleDao;
    private final ModeleEcritureFormuleMapper ModeleEcritureFormuleMapper;

    public ModeleEcritureFormuleServiceImpl(ModeleEcritureFormuleDao ModeleEcritureFormuleDao, com.ged.dao.opcciel.comptabilite.ModeleEcritureDao modeleEcritureDao, FormuleDao formuleDao, ModeleEcritureFormuleMapper ModeleEcritureFormuleMapper) {
        this.ModeleEcritureFormuleDao = ModeleEcritureFormuleDao;
        this.modeleEcritureDao = modeleEcritureDao;
        this.formuleDao = formuleDao;
        this.ModeleEcritureFormuleMapper = ModeleEcritureFormuleMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"numModeleEcritureFormule");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<ModeleEcritureFormule> ModeleEcritureFormulePage;
           // if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            //{
              //  ModeleEcritureFormulePage = ModeleEcritureFormuleDao.findByLibelleModeleEcritureFormuleContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
            //}
            //else {
                ModeleEcritureFormulePage = ModeleEcritureFormuleDao.findBySupprimer(false,pageable);
            //}
            List<ModeleEcritureFormuleDto> content = ModeleEcritureFormulePage.getContent().stream().map(ModeleEcritureFormuleMapper::deModeleEcritureFormule).collect(Collectors.toList());
            DataTablesResponse<ModeleEcritureFormuleDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)ModeleEcritureFormulePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)ModeleEcritureFormulePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des modèles formules par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"numModeleEcritureFormule");
            return ResponseHandler.generateResponse(
                    "Liste de tous les modèles formules",
                    HttpStatus.OK,
                    ModeleEcritureFormuleDao.findBySupprimer(false).stream().map(ModeleEcritureFormuleMapper::deModeleEcritureFormule).collect(Collectors.toList()));
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
    public ModeleEcritureFormule afficherSelonId(CleModeleEcritureFormule idModeleEcritureFormule) {
        return ModeleEcritureFormuleDao.findById(idModeleEcritureFormule).orElseThrow();
    }

    @Override
    public ResponseEntity<Object> afficher(CleModeleEcritureFormule codeModeleEcritureFormule) {
        try {
            return ResponseHandler.generateResponse(
                    "Modele formule dont id = " + codeModeleEcritureFormule,
                    HttpStatus.OK,
                    ModeleEcritureFormuleMapper.deModeleEcritureFormule(afficherSelonId(codeModeleEcritureFormule)));
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
    public ResponseEntity<Object> afficherSelonModeleEcriture(String codeModeleEcriture) {
        try {
            ModeleEcriture modeleEcriture=modeleEcritureDao.findById(codeModeleEcriture).orElseThrow();
            return ResponseHandler.generateResponse(
                    "Liste des modèles  formules selon "+codeModeleEcriture,
                    HttpStatus.OK,
                    ModeleEcritureFormuleDao.findByModeleEcritureAndSupprimer(modeleEcriture,false).stream().map(ModeleEcritureFormuleMapper::deModeleEcritureFormule).collect(Collectors.toList()));
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
    public ResponseEntity<Object> creer(ModeleEcritureFormuleDto ModeleEcritureFormuleDto) {
        try {
            ModeleEcritureFormuleDto.setSupprimer(false);
            ModeleEcritureFormule ModeleEcritureFormule = ModeleEcritureFormuleMapper.deModeleEcritureFormuleDto(ModeleEcritureFormuleDto);
            CleModeleEcritureFormule cleModeleEcritureFormule=new CleModeleEcritureFormule();
            cleModeleEcritureFormule.setIdFormule(ModeleEcritureFormuleDto.getFormule().getIdFormule());
            cleModeleEcritureFormule.setCodeModeleEcriture(ModeleEcritureFormuleDto.getModeleEcriture().getCodeModeleEcriture());
            ModeleEcritureFormule.setIdModeleEcritureFormule(cleModeleEcritureFormule);
            ModeleEcriture modeleEcriture=new ModeleEcriture();
            Formule formule=new Formule();
            if(ModeleEcritureFormuleDto.getModeleEcriture()!=null){
                modeleEcriture=modeleEcritureDao.findById(ModeleEcritureFormuleDto.getModeleEcriture().getCodeModeleEcriture()).orElseThrow();
                ModeleEcritureFormule.setModeleEcriture(modeleEcriture);
            }
            if(ModeleEcritureFormuleDto.getFormule()!=null){
                formule=formuleDao.findById(ModeleEcritureFormuleDto.getFormule().getIdFormule()).orElseThrow();
                ModeleEcritureFormule.setFormule(formule);
            }
            ModeleEcritureFormule = ModeleEcritureFormuleDao.save(ModeleEcritureFormule);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    ModeleEcritureFormuleMapper.deModeleEcritureFormule(ModeleEcritureFormule));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ModeleEcritureFormuleDto ModeleEcritureFormuleDto) {
        try {
            ModeleEcritureFormuleDto.setSupprimer(false);
            ModeleEcritureFormule ModeleEcritureFormule = ModeleEcritureFormuleMapper.deModeleEcritureFormuleDto(ModeleEcritureFormuleDto);

            CleModeleEcritureFormule cleModeleEcritureFormule=new CleModeleEcritureFormule();
            cleModeleEcritureFormule.setIdFormule(ModeleEcritureFormuleDto.getFormule().getIdFormule());
            cleModeleEcritureFormule.setCodeModeleEcriture(ModeleEcritureFormuleDto.getModeleEcriture().getCodeModeleEcriture());
            ModeleEcritureFormule.setIdModeleEcritureFormule(cleModeleEcritureFormule);


            ModeleEcriture modeleEcriture=new ModeleEcriture();
            Formule formule=new Formule();
            if(ModeleEcritureFormuleDto.getModeleEcriture()!=null){
                modeleEcriture=modeleEcritureDao.findById(ModeleEcritureFormuleDto.getModeleEcriture().getCodeModeleEcriture()).orElseThrow();
                ModeleEcritureFormule.setModeleEcriture(modeleEcriture);
            }
            if(ModeleEcritureFormuleDto.getFormule()!=null){
                formule=formuleDao.findById(ModeleEcritureFormuleDto.getFormule().getIdFormule()).orElseThrow();
                ModeleEcritureFormule.setFormule(formule);
            }
            ModeleEcritureFormule = ModeleEcritureFormuleDao.save(ModeleEcritureFormule);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    ModeleEcritureFormuleMapper.deModeleEcritureFormule(ModeleEcritureFormule));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleModeleEcritureFormule codeModeleEcritureFormule) {
        try {
            ModeleEcritureFormuleDao.deleteById(codeModeleEcritureFormule);
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
    public ResponseEntity<Object> supprimerSelonModeleEcriture(String codeModeleEcriture) {
        try {
            ModeleEcriture modeleEcriture=modeleEcritureDao.findById(codeModeleEcriture).orElseThrow();
            ModeleEcritureFormuleDao.deleteByModeleEcriture(modeleEcriture);
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
