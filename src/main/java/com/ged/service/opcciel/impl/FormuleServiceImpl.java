package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.FormuleDao;
import com.ged.dao.opcciel.comptabilite.ModeleEcritureDao;
import com.ged.dao.opcciel.comptabilite.ModeleEcritureFormuleDao;
import com.ged.dao.opcciel.comptabilite.TypeFormuleDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.FormuleDto;
import com.ged.entity.crm.Degre;
import com.ged.entity.opcciel.comptabilite.Formule;
import com.ged.entity.opcciel.comptabilite.TypeFormule;
import com.ged.mapper.opcciel.FormuleMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.FormuleService;
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
public class FormuleServiceImpl implements FormuleService {
    private final FormuleDao formuleDao;
    private final FormuleMapper formuleMapper;
    private final ModeleEcritureFormuleDao modeleEcritureFormuleDao;
    private final TypeFormuleDao typeFormuleDao;
    private final ModeleEcritureDao modeleEcritureDao;
    public FormuleServiceImpl(FormuleDao formuleDao, FormuleMapper formuleMapper, ModeleEcritureFormuleDao modeleEcritureFormuleDao, TypeFormuleDao typeFormuleDao, ModeleEcritureDao modeleEcritureDao) {
        this.formuleDao = formuleDao;
        this.formuleMapper = formuleMapper;
        this.modeleEcritureFormuleDao = modeleEcritureFormuleDao;
        this.typeFormuleDao = typeFormuleDao;
        this.modeleEcritureDao = modeleEcritureDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort=Sort.by(Sort.Direction.ASC,"libelleFormule");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Formule> formulePage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                formulePage = formuleDao.rechercher(parameters.getSearch().getValue(), pageable);
            }
            else {
                formulePage = formuleDao.findBySupprimer(false,pageable);
            }
            List<FormuleDto> content = formulePage.getContent().stream().map(formuleMapper::deFormule).collect(Collectors.toList());
            DataTablesResponse<FormuleDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)formulePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)formulePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des formules par page datatable",
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
            Sort sort=Sort.by(Sort.Direction.ASC,"libelleFormule");
            return ResponseHandler.generateResponse(
                    "Liste de toutes les formules",
                    HttpStatus.OK,
                    formuleDao.findBySupprimerOrderByLibelleFormuleAsc(false).stream().map(formuleMapper::deFormule).collect(Collectors.toList()));
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
                    "Formule dont code = " + id.toString(),
                    HttpStatus.OK,
                    formuleMapper.deFormule(afficherSelonId(id)));
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
    public Formule afficherSelonId(Long id) {
        return formuleDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Degre.class, "id", id.toString()));
    }
    @Override
    public ResponseEntity<Object> creer(FormuleDto formuleDto) {
        try {
            formuleDto.setSupprimer(false);
            Formule formule = formuleMapper.deFormuleDto(formuleDto);
            TypeFormule typeFormule=new TypeFormule();
            if(formuleDto.getTypeFormule()!=null){
                typeFormule=typeFormuleDao.findById(formuleDto.getTypeFormule().getCodeTypeFormule()).orElseThrow();
            }
            formule.setTypeFormule(typeFormule);
            Formule formuleSaved = formuleDao.save(formule);
            /*if(formuleDto.getModeleEcritureFormules()!=null){
                for(ModeleEcritureFormuleDto o:formuleDto.getModeleEcritureFormules()){
                    ModeleEcritureFormule modeleEcritureFormule=new ModeleEcritureFormule();
                    modeleEcritureFormule.setSupprimer(false);
                    modeleEcritureFormule.setFormule(formuleSaved);
                    if(o.getModeleEcriture()!=null){
                        ModeleEcriture modeleEcriture=modeleEcritureDao.findById(o.getModeleEcriture().getCodeModeleEcriture()).orElseThrow();
                        modeleEcritureFormule.setModeleEcriture(modeleEcriture);
                    }
                    modeleEcritureFormuleDao.save(modeleEcritureFormule);
                }
            }*/
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    formuleMapper.deFormule(formuleSaved));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(FormuleDto formuleDto) {
        try {
            //if(!formuleDao.existsById(formuleDto.getIdFormule()))
              //  throw  new EntityNotFoundException(Formule.class, "id", formuleDto.getIdFormule().toString());
//            formuleDao.findById(code);
            formuleDto.setSupprimer(false);
            Formule formule = formuleMapper.deFormuleDto(formuleDto);
            TypeFormule typeFormule=new TypeFormule();
            if(formuleDto.getTypeFormule()!=null){
                typeFormule=typeFormuleDao.findById(formuleDto.getTypeFormule().getCodeTypeFormule()).orElseThrow();
            }
            formule.setTypeFormule(typeFormule);
            formule = formuleDao.save(formule);
            /*if(formuleDto.getModeleEcritureFormules()!=null){
                for(ModeleEcritureFormuleDto o:formuleDto.getModeleEcritureFormules()){
                    ModeleEcritureFormule modeleEcritureFormule=new ModeleEcritureFormule();
                    modeleEcritureFormule.setSupprimer(false);
                    modeleEcritureFormule.setFormule(formule);
                    if(o.getModeleEcriture()!=null){
                        ModeleEcriture modeleEcriture=modeleEcritureDao.findById(o.getModeleEcriture().getCodeModeleEcriture()).orElseThrow();
                        modeleEcritureFormule.setModeleEcriture(modeleEcriture);
                    }
                    modeleEcritureFormuleDao.save(modeleEcritureFormule);
                }
            }*/
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    formuleMapper.deFormule(formule));
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
            formuleDao.deleteById(id);
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
