package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.DepotRachatDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.entity.opcciel.DepotRachat;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.standard.Personne;
import com.ged.mapper.opcciel.DepotRachatMapper;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.projection.NbrePartProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.DepotRachatService;
import com.ged.service.opcciel.SeanceOpcvmService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepotRachatImpl implements DepotRachatService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final DepotRachatDao depotRachatDao;
    private final OpcvmDao opcvmDao;
    private final NatureOperationDao natureOperationDao;
    private final DepotRachatMapper depotRachatMapper;
    private final PersonneMapper personneMapper;
    private final PersonneDao personneDao;
    private final LibraryDao libraryDao;
    private final OpcvmMapper opcvmMapper;
    private final SeanceOpcvmService seanceOpcvmService;

    public DepotRachatImpl(DepotRachatDao DepotRachatDao, OpcvmDao opcvmDao, NatureOperationDao natureOperationDao, DepotRachatMapper DepotRachatMapper, PersonneMapper personneMapper, PersonneDao personneDao, LibraryDao libraryDao, OpcvmMapper opcvmMapper, SeanceOpcvmService seanceOpcvmService){
        this.depotRachatDao = DepotRachatDao;
        this.opcvmDao = opcvmDao;
        this.natureOperationDao = natureOperationDao;

        this.depotRachatMapper = DepotRachatMapper;
        this.personneMapper = personneMapper;
        this.personneDao = personneDao;
        this.libraryDao = libraryDao;
        this.opcvmMapper = opcvmMapper;
        this.seanceOpcvmService = seanceOpcvmService;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters,long idOpcvm,long idSeance,String codeNatureOperation) {
        try {
            Opcvm opcvm=new Opcvm();
            opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            NatureOperation natureOperation=new NatureOperation();
            natureOperation=natureOperationDao.findById(codeNatureOperation).orElseThrow();
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleDepotRachat");
            Pageable pageable = PageRequest.of(
                    parameters.getStart() / parameters.getLength(), parameters.getLength());
            Page<DepotRachat> DepotRachatPage;
//            DepotRachatPage = depotRachatDao.listeDesDepotSeance(pageable);
            DepotRachatPage = depotRachatDao.findByOpcvmAndIdSeanceAndNatureOperation(
                    opcvm,idSeance,natureOperation,pageable);
            List<DepotRachatDto> content = DepotRachatPage.getContent().stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
            DataTablesResponse<DepotRachatDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)DepotRachatPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)DepotRachatPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des dépôts rachats par page datatable",
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
    public Page<DepotRachatDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libelleDepotRachat");
            List<DepotRachatDto> DepotRachats = depotRachatDao.findAll().stream().map(depotRachatMapper::deDepotRachat).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des dépôts rachats ",
                    HttpStatus.OK,
                    DepotRachats);
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
    public DepotRachat afficherSelonId(Long idOperation) {
        return depotRachatDao.findById(idOperation).orElseThrow(() -> new EntityNotFoundException(DepotRachat.class, "id",idOperation.toString()));
    }

    @Override
    public List<NbrePartProjection> afficherNbrePart(Long idOpcvm,
                                                     Long idActionnaire) {
        Sort sort=Sort.by(Sort.Direction.ASC,"dateOuverture");
        SeanceOpcvm seanceOpcvm=seanceOpcvmService.afficherSeanceEnCours(idOpcvm);
        LocalDateTime dateEstimation=seanceOpcvm.getDateFermeture();
        List<NbrePartProjection>  list=libraryDao.afficherNbrePart(idActionnaire,idOpcvm,false,
                true,true,dateEstimation);
        /*var q = em.createNativeQuery("SELECT * FROM [Operation].[FT_NbrePart](:idActionnaire,:idOpcvm,:estLevee," +
                ":estVerifie1,:estVerifie2,:dateEstimation)");
        SeanceOpcvm seanceOpcvm=seanceOpcvmService.afficherSeanceEnCours(idOpcvm);
        LocalDateTime dateEstimation=seanceOpcvm.getDateFermeture();
        q.setParameter("idActionnaire", idActionnaire);
        q.setParameter("idOpcvm", idOpcvm);
        q.setParameter("estLevee",false);
        q.setParameter("estVerifie1", true);
        q.setParameter("estVerifie2", true);
        q.setParameter("dateEstimation", dateEstimation);

        try {
            // Execute query
            //list = q.getResultList();
            list=q.getResultList();



        } finally {
            try
            {

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }*/

        return list;
    }

    @Override
    public ResponseEntity<Object> afficher(Long idOperation) {
        try {
            return ResponseHandler.generateResponse(
                    "DepotRachat dont ID = " + idOperation,
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat2(afficherSelonId(idOperation)));
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
    public ResponseEntity<Object> creer(DepotRachatDto DepotRachatDto) {
        try {
            DepotRachat DepotRachat = depotRachatMapper.deDepotRachatDto(DepotRachatDto);

            if(DepotRachatDto.getPersonne()!=null)
            {
                Personne personne=personneDao.findById(DepotRachatDto.getPersonne().getIdPersonne()).orElseThrow();
                if(personne!=null)
                    DepotRachat.setPersonne(personne);
            }
            if(DepotRachatDto.getActionnaire()!=null)
            {
                Personne personne=personneDao.findById(DepotRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                DepotRachat.setActionnaire(personne);
            }
            if(DepotRachatDto.getOpcvm()!=null)
            {
                Opcvm opcvm=opcvmDao.findById(DepotRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();
                if(opcvm!=null)
                    DepotRachat.setOpcvm(opcvm);
            }
            NatureOperation natureOperation=natureOperationDao.findById("INT_RACH").orElseThrow();
            if(natureOperation!=null)
                DepotRachat.setNatureOperation(natureOperation);

            DepotRachat = depotRachatDao.save(DepotRachat);
            System.out.println("Dep === " + DepotRachat);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat(DepotRachat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(DepotRachatDto depotRachatDto) {
        try {
            if(!depotRachatDao.existsById(depotRachatDto.getIdDepotRachat()))
                throw  new EntityNotFoundException(DepotRachat.class, "ID", depotRachatDto.getIdDepotRachat().toString());
            DepotRachat DepotRachat = depotRachatMapper.deDepotRachatDto(depotRachatDto);

            if(depotRachatDto.getPersonne()!=null)
            {
                Personne personne=personneDao.findById(depotRachatDto.getPersonne().getIdPersonne()).orElseThrow();
                if(personne!=null)
                    DepotRachat.setPersonne(personne);
            }
            if(depotRachatDto.getActionnaire()!=null)
            {
                Personne personne=personneDao.findById(depotRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                DepotRachat.setActionnaire(personne);
            }
            if(depotRachatDto.getOpcvm()!=null)
            {
                Opcvm opcvm=opcvmDao.findById(depotRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();
                if(opcvm!=null)
                    DepotRachat.setOpcvm(opcvm);
            }
            NatureOperation natureOperation=natureOperationDao.findById("INT_RACH").orElseThrow();
            if(natureOperation!=null)
                DepotRachat.setNatureOperation(natureOperation);

            DepotRachat = depotRachatDao.save(DepotRachat);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    depotRachatMapper.deDepotRachat(DepotRachat));
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
            depotRachatDao.deleteById(idOperation);
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
