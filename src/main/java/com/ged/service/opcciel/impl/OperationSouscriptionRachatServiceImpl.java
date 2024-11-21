package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OperationSouscriptionRachatDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.opcciel.comptabilite.TransactionDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.entity.opcciel.comptabilite.Transaction;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.opcciel.OperationSouscriptionRachatMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationSouscriptionRachatService;
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
public class OperationSouscriptionRachatServiceImpl implements OperationSouscriptionRachatService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final OperationSouscriptionRachatDao operationSouscriptionRachatDao;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final TitreDao titreDao;
    private final TransactionDao transactionDao;
    private final NatureOperationDao natureOperationDao;
    private final OperationSouscriptionRachatMapper operationSouscriptionRachatMapper;

    public OperationSouscriptionRachatServiceImpl(OperationSouscriptionRachatDao operationSouscriptionRachatDao, PersonneDao personneDao, OpcvmDao opcvmDao, TitreDao titreDao, TransactionDao transactionDao, NatureOperationDao natureOperationDao, OperationSouscriptionRachatMapper operationSouscriptionRachatMapper){
        this.operationSouscriptionRachatDao = operationSouscriptionRachatDao;
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.titreDao = titreDao;
        this.transactionDao = transactionDao;
        this.natureOperationDao = natureOperationDao;

        this.operationSouscriptionRachatMapper = operationSouscriptionRachatMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<OperationSouscriptionRachat> operationSouscriptionRachatPage;
           /* if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                planPage = planDao.rechercher(
                        parameters.getSearch().getValue(),
                        pageable);
            } else {*/
            operationSouscriptionRachatPage = operationSouscriptionRachatDao.findBySupprimer(false,pageable);
            //}

            List<OperationSouscriptionRachatDto> content = operationSouscriptionRachatPage.getContent().stream().map(operationSouscriptionRachatMapper::deOperationSouscriptionRachat).collect(Collectors.toList());
            DataTablesResponse<OperationSouscriptionRachatDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationSouscriptionRachatPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationSouscriptionRachatPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des opérations souscriptions rachats par page datatable",
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
            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            List<OperationSouscriptionRachatDto> operationSouscriptionRachatDtos = operationSouscriptionRachatDao.findBySupprimer(false).stream().map(operationSouscriptionRachatMapper::deOperationSouscriptionRachat).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des opérations souscriptions rachats ",
                    HttpStatus.OK,
                    operationSouscriptionRachatDtos);
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
    public OperationSouscriptionRachat afficherSelonId(Long id) {
        return operationSouscriptionRachatDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Plan.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Operation souscription rachat dont ID = " + id,
                    HttpStatus.OK,
                    operationSouscriptionRachatMapper.deOperationSouscriptionRachat(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(OperationSouscriptionRachatDto operationSouscriptionRachatDto) {
        try {

            operationSouscriptionRachatDto.setSupprimer(false);
            OperationSouscriptionRachat  operationSouscriptionRachat =operationSouscriptionRachatMapper.deOperationSouscriptionRachatDto(operationSouscriptionRachatDto);
            if(operationSouscriptionRachatDto.getActionnaire()!=null)
            {
                Personne personne=personneDao.findById(operationSouscriptionRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                operationSouscriptionRachat.setActionnaire(personne);
            }
            if(operationSouscriptionRachatDto.getOpcvm()!=null)
            {
                Opcvm opcvm=opcvmDao.findById(operationSouscriptionRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();
                operationSouscriptionRachat.setOpcvm(opcvm);
            }
            if(operationSouscriptionRachatDto.getNatureOperation()!=null){
                NatureOperation natureOperation=natureOperationDao.findById(operationSouscriptionRachatDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
                operationSouscriptionRachat.setNatureOperation(natureOperation);
            }

            if(operationSouscriptionRachatDto.getTitre()!=null)
            {
                Titre titre=titreDao.findById(operationSouscriptionRachatDto.getTitre().getIdTitre()).orElseThrow();
                operationSouscriptionRachat.setTitre(titre);
            }
            if(operationSouscriptionRachatDto.getTransaction()!=null){
                Transaction transaction=transactionDao.findById(operationSouscriptionRachatDto.getTransaction().getIdTransaction()).orElseThrow();
                operationSouscriptionRachat.setTransaction(transaction);
            }
            operationSouscriptionRachat = operationSouscriptionRachatDao.save(operationSouscriptionRachat);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    operationSouscriptionRachatMapper.deOperationSouscriptionRachat(operationSouscriptionRachat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creer(OperationSouscriptionRachatDto[] operationSouscriptionRachatTab) {
        try {
            for(OperationSouscriptionRachatDto operationSouscriptionRachatDto:operationSouscriptionRachatTab)
            {
                operationSouscriptionRachatDto.setSupprimer(false);
                OperationSouscriptionRachat  operationSouscriptionRachat =operationSouscriptionRachatMapper.deOperationSouscriptionRachatDto(operationSouscriptionRachatDto);
                if(operationSouscriptionRachatDto.getActionnaire()!=null)
                {
                    Personne personne=personneDao.findById(operationSouscriptionRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                    operationSouscriptionRachat.setActionnaire(personne);
                }
                if(operationSouscriptionRachatDto.getOpcvm()!=null)
                {
                    Opcvm opcvm=opcvmDao.findById(operationSouscriptionRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();
                    operationSouscriptionRachat.setOpcvm(opcvm);
                }
                if(operationSouscriptionRachatDto.getNatureOperation()!=null){
                    NatureOperation natureOperation=natureOperationDao.findById(operationSouscriptionRachatDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
                    operationSouscriptionRachat.setNatureOperation(natureOperation);
                }

                if(operationSouscriptionRachatDto.getTitre()!=null)
                {
                    Titre titre=titreDao.findById(operationSouscriptionRachatDto.getTitre().getIdTitre()).orElseThrow();
                    operationSouscriptionRachat.setTitre(titre);
                }
                if(operationSouscriptionRachatDto.getTransaction()!=null){
                    Transaction transaction=transactionDao.findById(operationSouscriptionRachatDto.getTransaction().getIdTransaction()).orElseThrow();
                    operationSouscriptionRachat.setTransaction(transaction);
                }
                operationSouscriptionRachat = operationSouscriptionRachatDao.save(operationSouscriptionRachat);
            }

            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
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
    public ResponseEntity<Object> modifier(OperationSouscriptionRachatDto operationSouscriptionRachatDto) {
        try {
            if(!operationSouscriptionRachatDao.existsById(operationSouscriptionRachatDto.getIdOperation()))
                throw  new EntityNotFoundException(OperationSouscriptionRachat.class, "code",operationSouscriptionRachatDto.getIdOperation().toString());
            operationSouscriptionRachatDto.setSupprimer(false);
            OperationSouscriptionRachat operationSouscriptionRachat =operationSouscriptionRachatMapper.deOperationSouscriptionRachatDto(operationSouscriptionRachatDto);
            if(operationSouscriptionRachatDto.getActionnaire()!=null)
            {
                Personne personne=personneDao.findById(operationSouscriptionRachatDto.getActionnaire().getIdPersonne()).orElseThrow();
                operationSouscriptionRachat.setActionnaire(personne);
            }
            if(operationSouscriptionRachatDto.getOpcvm()!=null)
            {
                Opcvm opcvm=opcvmDao.findById(operationSouscriptionRachatDto.getOpcvm().getIdOpcvm()).orElseThrow();
                operationSouscriptionRachat.setOpcvm(opcvm);
            }
            if(operationSouscriptionRachatDto.getNatureOperation()!=null){
                NatureOperation natureOperation=natureOperationDao.findById(operationSouscriptionRachatDto.getNatureOperation().getCodeNatureOperation()).orElseThrow();
                operationSouscriptionRachat.setNatureOperation(natureOperation);
            }

            if(operationSouscriptionRachatDto.getTitre()!=null)
            {
                Titre titre=titreDao.findById(operationSouscriptionRachatDto.getTitre().getIdTitre()).orElseThrow();
                operationSouscriptionRachat.setTitre(titre);
            }
            if(operationSouscriptionRachatDto.getTransaction()!=null){
                Transaction transaction=transactionDao.findById(operationSouscriptionRachatDto.getTransaction().getIdTransaction()).orElseThrow();
                operationSouscriptionRachat.setTransaction(transaction);
            }
            operationSouscriptionRachat=operationSouscriptionRachatDao.save(operationSouscriptionRachat);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    operationSouscriptionRachatMapper.deOperationSouscriptionRachat(operationSouscriptionRachat));
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
            operationSouscriptionRachatDao.deleteById(id);
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
