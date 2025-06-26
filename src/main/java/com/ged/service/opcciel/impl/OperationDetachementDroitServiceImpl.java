package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OperationDetachementDroitDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.titresciel.DroitDao;
import com.ged.dao.titresciel.ObligationDao;
import com.ged.dao.titresciel.TcnDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationDetachementDroitDto;
import com.ged.dto.titresciel.DroitDto;
import com.ged.entity.opcciel.OperationDetachementDroit;
import com.ged.entity.titresciel.Droit;
import com.ged.entity.titresciel.Obligation;
import com.ged.entity.titresciel.Tcn;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.opcciel.OperationDetachementDroitMapper;
import com.ged.mapper.titresciel.DroitMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.opcciel.OperationDetachementDroitService;
import com.ged.service.standard.impl.MailSenderServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OperationDetachementDroitServiceImpl implements OperationDetachementDroitService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final FiltersSpecification<OperationDetachementDroit> DetachementFiltersSpecification;
    private final OperationDetachementDroitDao operationDetachementDroitDao;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final LibraryDao libraryDao;
    private final TitreDao titreDao;
    private final TcnDao tcnDao;
    private final ObligationDao obligationDao;
    private final DroitDao droitDao;
    private final DroitMapper droitMapper;
    private final NatureOperationDao natureOperationDao;
    private final OperationDetachementDroitMapper operationDetachementDroitMapper;

    public OperationDetachementDroitServiceImpl(FiltersSpecification<OperationDetachementDroit> DetachementFiltersSpecification, OperationDetachementDroitDao operationDetachementDroitDao, PersonneDao personneDao, OpcvmDao opcvmDao, LibraryDao libraryDao, TitreDao titreDao, TcnDao tcnDao, ObligationDao obligationDao, DroitDao droitDao, DroitMapper droitMapper, NatureOperationDao natureOperationDao, OperationDetachementDroitMapper operationDetachementDroitMapper){
        this.DetachementFiltersSpecification = DetachementFiltersSpecification;
        this.operationDetachementDroitDao = operationDetachementDroitDao;
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.libraryDao = libraryDao;
        this.titreDao = titreDao;
        this.tcnDao = tcnDao;
        this.obligationDao = obligationDao;
        this.droitDao = droitDao;
        this.droitMapper = droitMapper;
        this.natureOperationDao = natureOperationDao;

        this.operationDetachementDroitMapper = operationDetachementDroitMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
//            Pageable pageable = PageRequest.of(
//                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
//            Page<OperationDetachementDroitProjection> operationDetachementDroitPage;
//            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
//                operationDetachementDroitPage = libraryDao.operationDetachementDroitListeRecherche(idOpcvm,null,parameters.getSearch().getValue(),pageable);
//            } else {
////            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
//                operationDetachementDroitPage = libraryDao.operationDetachementDroitListe(idOpcvm,null,pageable);
//            }
//
////            List<OperationDetachementDroitDto> content = operationDetachementDroitPage.getContent().stream().map(operationDetachementDroitMapper::deOperationDetachementDroitProjection).collect(Collectors.toList());
//            List<OperationDetachementDroitProjection> content = operationDetachementDroitPage.getContent().stream().collect(Collectors.toList());
//            DataTablesResponse<OperationDetachementDroitProjection> dataTablesResponse = new DataTablesResponse<>();
//            dataTablesResponse.setDraw(parameters.getDraw());
//            dataTablesResponse.setRecordsFiltered((int)operationDetachementDroitPage.getTotalElements());
//            dataTablesResponse.setRecordsTotal((int)operationDetachementDroitPage.getTotalElements());
//            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des détachements par page datatable",
                    HttpStatus.OK,
                    null);
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
    public ResponseEntity<Object> afficherTous(Long idOpcvm,Long idTitre) {
        try {
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            OperationDetachementDroitDto operationDetachementDroitDto =operationDetachementDroitMapper.deOperationDetachementDroitProjection(libraryDao.operationDetachementListe(idOpcvm,idTitre));
            return ResponseHandler.generateResponse(
                    "Liste des détachements de droit ",
                    HttpStatus.OK,
                    operationDetachementDroitDto);
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
    public ResponseEntity<Object> afficherTitre() {
        try {
//            List<PortefeuilleOpcvmProjection> portefeuilleOpcvmProjections=new ArrayList<>();//libraryDao.portefeuilleOPCVM(idOpcvm, dateEstimation);
            List<DroitDto> list=droitDao.findAll().stream().map(droitMapper::deDroit).collect(Collectors.toList());


            return ResponseHandler.generateResponse(
                    "PortefeuilleOPCVM  ",
                    HttpStatus.OK,
                    list);
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
    public ResponseEntity<Object> creer(OperationDetachementDroitDto operationDetachementDroitDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[OperationCapital].[PS_OperationDetachementDroit_IP_New]");

            q.registerStoredProcedureParameter("IdAvis", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("qteAction", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("qteDroit", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

            String codeNatureOpe="";
            String valeurCodeAnalytique="";
            String valeurFormule="";
            Droit droit=droitDao.findById(operationDetachementDroitDto.getTitre().getIdTitre()).orElseThrow();
            if (droit.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("DPS"))
            {
                codeNatureOpe= "DET_DRTSOUS";
                valeurCodeAnalytique = "OPC:" + operationDetachementDroitDto.getOpcvm().getIdOpcvm() +
                        ";TIT2:" + droit.getActionLiee().getIdTitre();
                valeurFormule = "54:" + operationDetachementDroitDto.getQteAction().toString().replace(',', '.') +
                        ";87:" + new BigDecimal((operationDetachementDroitDto.getQteAction().doubleValue() *
                        droit.getCoursTheorique().doubleValue())).setScale(0,RoundingMode.HALF_UP).toString()
                        .replace(',', '.');
            }
            else
            {
                codeNatureOpe = "DET_DRTATTR";
                valeurCodeAnalytique = "OPC:" + operationDetachementDroitDto.getOpcvm().getIdOpcvm() +
                        ";TIT2:" + droit.getActionLiee().getIdTitre();
                valeurFormule = "54:" + operationDetachementDroitDto.getQteAction().toString().replace(',', '.') +
                        ";87:" + new BigDecimal((operationDetachementDroitDto.getQteAction().doubleValue() *
                        droit.getCoursTheorique().doubleValue())).setScale(0,RoundingMode.HALF_UP).toString()
                        .replace(',', '.');
            }
//            q.setParameter("idOperation", 0);
            q.setParameter("IdAvis", 0);
            q.setParameter("IdTransaction", 0);
            q.setParameter("idSeance", operationDetachementDroitDto.getIdSeance());
            q.setParameter("codeNatureOperation",codeNatureOpe);
            q.setParameter("dateOperation", operationDetachementDroitDto.getDateOperation());
            q.setParameter("dateSaisie", operationDetachementDroitDto.getDateSaisie());
            q.setParameter("dateValeur", operationDetachementDroitDto.getDateValeur());
            q.setParameter("datePiece",operationDetachementDroitDto.getDatePiece());
            q.setParameter("referencePiece", operationDetachementDroitDto.getReferencePiece());
            q.setParameter("montant", operationDetachementDroitDto.getMontant());
            q.setParameter("ecriture", "A");
            q.setParameter("libelleOperation", operationDetachementDroitDto.getLibelleOperation());
            q.setParameter("estOD", false);
            q.setParameter("type", "DD");
            q.setParameter("idTitre", operationDetachementDroitDto.getTitre().getIdTitre());
            q.setParameter("qteAction", operationDetachementDroitDto.getQteAction());
            q.setParameter("qteDroit",operationDetachementDroitDto.getQteDroit());
            q.setParameter("idOpcvm", operationDetachementDroitDto.getOpcvm().getIdOpcvm());
            q.setParameter("valeurFormule", valeurFormule);
            q.setParameter("valeurCodeAnalytique", valeurCodeAnalytique);
            q.setParameter("userLogin", operationDetachementDroitDto.getUserLogin());
            q.setParameter("dateDernModifClient", LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie", sortie);

            String[] s=new String[20];
            try
            {
                q.execute();
                String result=(String) q.getOutputParameterValue("Sortie");
                s=result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            }
            catch(Exception e){

            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    s[s.length-1]);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }


    @Override
    public ResponseEntity<Object> modifier(OperationDetachementDroitDto operationDetachementDroitDto) {
        try {

            String sortie="";
            var q = em.createStoredProcedureQuery("[OperationCapital].[PS_OperationConversiontTitre_UP_New]");

            q.registerStoredProcedureParameter("IdAvis", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("qteAction", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("qteDroit", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            String codeNatureOpe="";
            String valeurCodeAnalytique="";
            String valeurFormule="";
            Droit droit=droitDao.findById(operationDetachementDroitDto.getTitre().getIdTitre()).orElseThrow();
            if (droit.getTypeTitre().getCodeTypeTitre().trim().toUpperCase().equals("DPS"))
            {
                codeNatureOpe= "DET_DRTSOUS";
                valeurCodeAnalytique = "OPC:" + operationDetachementDroitDto.getOpcvm().getIdOpcvm() +
                        ";TIT2:" + droit.getActionLiee().getIdTitre();
                valeurFormule = "54:" + operationDetachementDroitDto.getQteAction().toString().replace(',', '.') +
                        ";87:" + new BigDecimal((operationDetachementDroitDto.getQteAction().doubleValue() *
                        droit.getCoursTheorique().doubleValue())).setScale(0,RoundingMode.HALF_UP).toString()
                        .replace(',', '.');
            }
            else
            {
                codeNatureOpe = "DET_DRTATTR";
                valeurCodeAnalytique = "OPC:" + operationDetachementDroitDto.getOpcvm().getIdOpcvm() +
                        ";TIT2:" + droit.getActionLiee().getIdTitre();
                valeurFormule = "54:" + operationDetachementDroitDto.getQteAction().toString().replace(',', '.') +
                        ";87:" + new BigDecimal((operationDetachementDroitDto.getQteAction().doubleValue() *
                        droit.getCoursTheorique().doubleValue())).setScale(0,RoundingMode.HALF_UP).toString()
                        .replace(',', '.');
            }
//            q.setParameter("idOperation", 0);
            q.setParameter("IdAvis", operationDetachementDroitDto.getIdOperation());
            q.setParameter("IdTransaction", 0);
            q.setParameter("idSeance", operationDetachementDroitDto.getIdSeance());
            q.setParameter("codeNatureOperation",codeNatureOpe);
            q.setParameter("dateOperation", operationDetachementDroitDto.getDateOperation());
            q.setParameter("dateSaisie", operationDetachementDroitDto.getDateSaisie());
            q.setParameter("dateValeur", operationDetachementDroitDto.getDateValeur());
            q.setParameter("datePiece",operationDetachementDroitDto.getDatePiece());
            q.setParameter("referencePiece", operationDetachementDroitDto.getReferencePiece());
            q.setParameter("montant", operationDetachementDroitDto.getMontant());
            q.setParameter("ecriture", "A");
            q.setParameter("libelleOperation", operationDetachementDroitDto.getLibelleOperation());
            q.setParameter("estOD", false);
            q.setParameter("type", "DD");
            q.setParameter("idTitre", operationDetachementDroitDto.getTitre().getIdTitre());
            q.setParameter("qteAction", operationDetachementDroitDto.getQteAction());
            q.setParameter("qteDroit",operationDetachementDroitDto.getQteDroit());
            q.setParameter("idOpcvm", operationDetachementDroitDto.getOpcvm().getIdOpcvm());
            q.setParameter("userLogin", operationDetachementDroitDto.getUserLogin());
            q.setParameter("dateDernModifClient",LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);
            String[] s=new String[20];
            try
            {
                q.execute();
                String result=(String) q.getOutputParameterValue("Sortie");
                s=result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            }
            catch(Exception e){

            }
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
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
    public ResponseEntity<Object> supprimer(String userLogin,
                                            Long idDetachement ) {
        try {
            String sortie="";
            var q = em.createStoredProcedureQuery("[OperationCapital].[PS_OperationDetachementDroit_DP_New]");

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idAvis", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);

            q.setParameter("userLogin", userLogin);
            q.setParameter("idAvis",idDetachement);
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);

            try
            {
                q.execute();
                String result=(String) q.getOutputParameterValue("Sortie");
//                s=result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            }
            catch(Exception e){

            }
//            operationDetachementDroitDao.deleteById(id);
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
