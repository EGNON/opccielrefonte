package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.FormuleDao;
import com.ged.dao.opcciel.comptabilite.ModeleEcritureDao;
import com.ged.dao.opcciel.comptabilite.ModeleEcritureFormuleDao;
import com.ged.dao.opcciel.comptabilite.PlanDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureDto;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureFormuleDto;
import com.ged.entity.opcciel.comptabilite.CleModeleEcritureFormule;
import com.ged.entity.opcciel.comptabilite.Formule;
import com.ged.entity.opcciel.comptabilite.ModeleEcriture;
import com.ged.entity.opcciel.comptabilite.ModeleEcritureFormule;
import com.ged.mapper.opcciel.ModeleEcritureMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.ModeleEcritureService;
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
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ModeleEcritureServiceImpl implements ModeleEcritureService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final ModeleEcritureDao ModeleEcritureDao;
    private final FormuleDao formuleDao;
    private final ModeleEcritureFormuleDao modeleEcritureFormuleDao;
    private final PlanDao planDao;
    private final ModeleEcritureMapper ModeleEcritureMapper;

    public ModeleEcritureServiceImpl(ModeleEcritureDao ModeleEcritureDao, FormuleDao formuleDao, ModeleEcritureFormuleDao modeleEcritureFormuleDao, PlanDao planDao, ModeleEcritureMapper ModeleEcritureMapper){
        this.ModeleEcritureDao = ModeleEcritureDao;
        this.formuleDao = formuleDao;
        this.modeleEcritureFormuleDao = modeleEcritureFormuleDao;
        this.planDao = planDao;

        this.ModeleEcritureMapper = ModeleEcritureMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleModeleEcriture");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<ModeleEcriture> ModeleEcriturePage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                ModeleEcriturePage = ModeleEcritureDao.rechercher(
                        parameters.getSearch().getValue(),
                        pageable);
            } else {
                ModeleEcriturePage = ModeleEcritureDao.findBySupprimer(false,pageable);
            }

            List<ModeleEcritureDto> content = ModeleEcriturePage.getContent().stream().map(ModeleEcritureMapper::deModeleEcriture).collect(Collectors.toList());
            DataTablesResponse<ModeleEcritureDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)ModeleEcriturePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)ModeleEcriturePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des modèles écritures par page datatable",
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
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleModeleEcriture");
            List<ModeleEcritureDto> ModeleEcritures = ModeleEcritureDao.findBySupprimerOrderByLibelleModeleEcritureAsc(false).stream().map(ModeleEcritureMapper::deModeleEcriture).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des modèles écritures ",
                    HttpStatus.OK,
                    ModeleEcritures);
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
    public ModeleEcriture afficherSelonId(String codeModeleEcriture) {
        return ModeleEcritureDao.findById(codeModeleEcriture).orElseThrow(() -> new EntityNotFoundException(ModeleEcriture.class, "code",codeModeleEcriture));
    }

    @Override
    public ResponseEntity<Object> afficher(String codeModeleEcriture) {
        try {
            return ResponseHandler.generateResponse(
                    "ModeleEcriture dont CODE = " + codeModeleEcriture,
                    HttpStatus.OK,
                    ModeleEcritureMapper.deModeleEcriture(afficherSelonId(codeModeleEcriture)));
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
    public ResponseEntity<Object> creer(ModeleEcritureDto ModeleEcritureDto) {
        try {
            ModeleEcritureDto.setSupprimer(false);

            ModeleEcriture ModeleEcriture = ModeleEcritureMapper.deModeleEcritureDto(ModeleEcritureDto);
            ModeleEcriture.setDateDernModifClient(LocalDateTime.now());
            ModeleEcriture.setUserLogin(ModeleEcritureDto.getUserLogin());
            ModeleEcriture = ModeleEcritureDao.save(ModeleEcriture);

            if(ModeleEcritureDto.getModeleEcritureFormules()!=null){
                for(ModeleEcritureFormuleDto o:ModeleEcritureDto.getModeleEcritureFormules()){
                    ModeleEcritureFormule modeleEcritureFormule=new ModeleEcritureFormule();
                    CleModeleEcritureFormule cleModeleEcritureFormule=new CleModeleEcritureFormule();
                    cleModeleEcritureFormule.setCodeModeleEcriture(ModeleEcritureDto.getCodeModeleEcriture());
                    cleModeleEcritureFormule.setIdFormule(o.getFormule().getIdFormule());
                    modeleEcritureFormule.setIdModeleEcritureFormule(cleModeleEcritureFormule);
                    modeleEcritureFormule.setSupprimer(false);
                    modeleEcritureFormule.setModeleEcriture(ModeleEcriture);
                    if(o.getFormule()!=null){
                        Formule formule=formuleDao.findById(o.getFormule().getIdFormule()).orElseThrow();
                        modeleEcritureFormule.setFormule(formule);
                    }
                    modeleEcritureFormuleDao.save(modeleEcritureFormule);
                }
            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    ModeleEcritureMapper.deModeleEcriture(ModeleEcriture));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ModeleEcritureDto ModeleEcritureDto) {
        try {
            if(!ModeleEcritureDao.existsById(ModeleEcritureDto.getCodeModeleEcriture()))
                throw  new EntityNotFoundException(ModeleEcriture.class, "code", ModeleEcritureDto.getCodeModeleEcriture().toString());
            ModeleEcritureDto.setSupprimer(false);

            ModeleEcriture ModeleEcriture = ModeleEcritureMapper.deModeleEcritureDto(ModeleEcritureDto);
            ModeleEcriture.setDateDernModifClient(LocalDateTime.now());
            ModeleEcriture.setUserLogin(ModeleEcritureDto.getUserLogin());
            ModeleEcriture = ModeleEcritureDao.save(ModeleEcriture);
            //System.out.println("size modfier="+ModeleEcritureDto.getModeleEcritureFormules().size());
            if(ModeleEcritureDto.getModeleEcritureFormules()!=null){
                for(ModeleEcritureFormuleDto o:ModeleEcritureDto.getModeleEcritureFormules()){

                    ModeleEcritureFormule modeleEcritureFormule=new ModeleEcritureFormule();
                    CleModeleEcritureFormule cleModeleEcritureFormule=new CleModeleEcritureFormule();
                    cleModeleEcritureFormule.setCodeModeleEcriture(ModeleEcritureDto.getCodeModeleEcriture());
                    cleModeleEcritureFormule.setIdFormule(o.getFormule().getIdFormule());
                    modeleEcritureFormule.setIdModeleEcritureFormule(cleModeleEcritureFormule);
                    modeleEcritureFormule.setSupprimer(false);
              //      System.out.println("code="+ModeleEcritureDto.getCodeModeleEcriture());
                //    System.out.println("IdFormule="+o.getFormule().getIdFormule());
                    modeleEcritureFormule.setModeleEcriture(ModeleEcriture);
                    if(o.getFormule()!=null){
                  //      System.out.println("pass");
                        Formule formule=formuleDao.findById(o.getFormule().getIdFormule()).orElseThrow();
                        modeleEcritureFormule.setFormule(formule);
                    }
                    modeleEcritureFormuleDao.save(modeleEcritureFormule);
                }
            }
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    ModeleEcritureMapper.deModeleEcriture(ModeleEcriture));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String codeModeleEcriture) {
        try {
            ModeleEcritureDao.deleteById(codeModeleEcriture);
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
