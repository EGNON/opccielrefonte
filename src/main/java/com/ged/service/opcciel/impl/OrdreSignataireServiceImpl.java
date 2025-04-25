package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.OrdreDao;
import com.ged.dao.opcciel.OrdreSignataireDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.dto.opcciel.OrdreSignataireDto;
import com.ged.entity.opcciel.CleOrdre;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.Ordre;
import com.ged.entity.opcciel.OrdreSignataire;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.entity.standard.Personne;
import com.ged.mapper.opcciel.OrdreMapper;
import com.ged.mapper.opcciel.OrdreSignataireMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OrdreSignataireService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdreSignataireServiceImpl implements OrdreSignataireService {
    @PersistenceContext
    EntityManager em;
    private final PersonneDao personneDao;
    private final OrdreSignataireDao ordreSignataireDao;
    private final OrdreSignataireMapper ordreSignataireMapper;
    private final TitreDao titreDao;
    private final OrdreDao ordreDao;
    private final OrdreMapper ordreMapper;

    public OrdreSignataireServiceImpl(PersonneDao personneDao, OrdreSignataireDao ordreSignataireDao, OrdreSignataireMapper ordreSignataireMapper, TitreDao titreDao, OrdreDao ordreDao, OrdreMapper ordreMapper){
        this.personneDao = personneDao;
        this.ordreSignataireDao = ordreSignataireDao;
        this.ordreSignataireMapper = ordreSignataireMapper;
        this.titreDao = titreDao;

        this.ordreDao = ordreDao;
        this.ordreMapper = ordreMapper;
    }

//    @Override
//    public ResponseEntity<Object> afficherTous(Long idOpcvm,DatatableParameters parameters) {
//        try {
////            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
//            Pageable pageable = PageRequest.of(
//                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
//            Page<Ordre> OrdrePage;
//           /* if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
//                planPage = planDao.rechercher(
//                        parameters.getSearch().getValue(),
//                        pageable);
//            } else {*/
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
//            OrdrePage = ordreDao.findByOpcvmAndEstEnvoyeAndSupprimer(opcvm,false,false,pageable);
//            //}
//
//            List<OrdreDto> content = OrdrePage.getContent().stream().map(ordreMapper::deOrdre).collect(Collectors.toList());
//            DataTablesResponse<OrdreDto> dataTablesResponse = new DataTablesResponse<>();
//            dataTablesResponse.setDraw(parameters.getDraw());
//            dataTablesResponse.setRecordsFiltered((int)OrdrePage.getTotalElements());
//            dataTablesResponse.setRecordsTotal((int)OrdrePage.getTotalElements());
//            dataTablesResponse.setData(content);
//            return ResponseHandler.generateResponse(
//                    "Liste des ordre de bourse par page datatable",
//                    HttpStatus.OK,
//                    dataTablesResponse);
//        }
//        catch(Exception e)
//        {
//            return ResponseHandler.generateResponse(
//                    e.getMessage(),
//                    HttpStatus.MULTI_STATUS,
//                    e);
//        }
//    }

    @Override
    public ResponseEntity<Object> afficherTous(Long idOrdre) {
        try {
            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Ordre ordre=ordreDao.findById(idOrdre).orElseThrow();
            List<OrdreSignataireDto> ordreSignataireDtos = ordreSignataireDao.findByOrdre(ordre).stream().map(ordreSignataireMapper::deOrdreSignataire).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des ordres de bourse",
                    HttpStatus.OK,
                    ordreSignataireDtos);
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
    public OrdreSignataire afficherSelonId(CleOrdre id) {
        return ordreSignataireDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Plan.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(CleOrdre id) {
        try {
            return ResponseHandler.generateResponse(
                    "Ordre de bourse dont ID = " + id,
                    HttpStatus.OK,
                    ordreSignataireMapper.deOrdreSignataire(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(OrdreSignataireDto ordreSignataireDto) {
        try {
            OrdreSignataire ordreSignataire=ordreSignataireMapper.deOrdreSignataireDto(ordreSignataireDto);
            if(ordreSignataireDto.getOrdre()!=null){
                Ordre ordre=ordreDao.findById(ordreSignataireDto.getOrdre().getIdOrdre()).orElseThrow();
                ordreSignataire.setOrdre(ordre);
            }

            if(ordreSignataireDto.getPersonne()!=null){
                Personne personne=personneDao.findById(ordreSignataireDto.getPersonne().getIdPersonne()).orElseThrow();
                ordreSignataire.setPersonne(personne);
            }
            OrdreSignataire ordreSignataireSave= ordreSignataireDao.save(ordreSignataire);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                   ordreSignataireMapper.deOrdreSignataire(ordreSignataireSave));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creer(Long idOrdre, Long[] idPersonne) {
        try {
            OrdreSignataire ordreSignataireSave=new OrdreSignataire();
            for(int i=0;i<idPersonne.length;i++){

                OrdreSignataire ordreSignataire=new OrdreSignataire();
                LocalDateTime date=LocalDateTime.now();
                CleOrdre cleOrdre=new CleOrdre();
                cleOrdre.setIdOrdre(idOrdre);
                cleOrdre.setIdPersonne(idPersonne[i]);
                Ordre ordre=ordreDao.findById(idOrdre).orElseThrow();
                ordreSignataire.setOrdre(ordre);
                ordreSignataire.setDateCreationServeur(date);
                ordreSignataire.setDateDernModifClient(date);
                ordreSignataire.setDateDernModifServeur(date);
                ordreSignataire.setUserLogin(ordre.getUserLogin());
                Personne personne=personneDao.findById(idPersonne[i]).orElseThrow();
                ordreSignataire.setPersonne(personne);

                ordreSignataire.setIdOrdre(cleOrdre);
                ordreSignataireSave= ordreSignataireDao.save(ordreSignataire);
            }

            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    ordreSignataireMapper.deOrdreSignataire(ordreSignataireSave));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

//    @Override
//    public ResponseEntity<Object> modifier(OrdreDto OrdreDto) {
//        try {
////            if(!OrdreDao.existsById(OrdreDto.getIdOperation()))
////                throw  new EntityNotFoundException(Ordre.class, "code",OrdreDto.getIdOperation().toString());
////            OrdreDto.setSupprimer(false);
////            Ordre Ordre =OrdreMapper.deOrdreDto(OrdreDto);
////            if(OrdreDto.getActionnaire()!=null)
////            {
////                Personne personne=personneDao.findById(OrdreDto.getActionnaire().getIdPersonne()).orElseThrow();
////                Ordre.setActionnaire(personne);
////            }
////            if(OrdreDto.getOpcvm()!=null)
////            {
////                Opcvm opcvm=opcvmDao.findById(OrdreDto.getOpcvm().getIdOpcvm()).orElseThrow();
////                Ordre.setOpcvm(opcvm);
////            }
////            if(OrdreDto.getNatureOperation()!=null){
////                NatureOperation natureOperation=natureOperationDao.findById(OrdreDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
////                Ordre.setNatureOperation(natureOperation);
////            }
////
////            if(OrdreDto.getTitre()!=null)
////            {
////                Titre titre=titreDao.findById(OrdreDto.getTitre().getIdTitre()).orElseThrow();
////                Ordre.setTitre(titre);
////            }
////            if(OrdreDto.getTransaction()!=null){
////                Transaction transaction=transactionDao.findById(OrdreDto.getTransaction().getIdTransaction()).orElseThrow();
////                Ordre.setTransaction(transaction);
////            }
////            Ordre=OrdreDao.save(Ordre);
//            return ResponseHandler.generateResponse(
//                    "Modification effectuée avec succès !",
//                    HttpStatus.OK,
//                    null);
//        } catch (Exception e) {
//            return ResponseHandler.generateResponse(
//                    e.getMessage(),
//                    HttpStatus.MULTI_STATUS,
//                    e);
//        }
//    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        try {
            Ordre ordre=ordreDao.findById(id).orElseThrow();
            ordreSignataireDao.deleteByOrdre(ordre);
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
