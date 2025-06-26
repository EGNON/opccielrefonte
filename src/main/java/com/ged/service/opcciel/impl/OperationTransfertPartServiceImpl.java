package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OperationTransfertPartDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.dto.opcciel.OperationTransfertPartDto;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.dto.request.TransfertPartAddRequest;
import com.ged.dto.request.TransfertPartListeRequest;
import com.ged.entity.opcciel.OperationTransfertPart;
import com.ged.mapper.opcciel.NatureOperationMapper;
import com.ged.mapper.opcciel.OperationTransfertPartMapper;
import com.ged.projection.OperationTransfertDePartProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.AppService;
import com.ged.service.opcciel.OperationTransfertPartService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OperationTransfertPartServiceImpl implements OperationTransfertPartService {
    private final OperationTransfertPartDao operationTransfertPartDao;
    private final OperationTransfertPartMapper transfertPartMapper;
    private final AppService appService;
    private final NatureOperationDao natureOperationDao;
    private final LibraryDao libraryDao;
    private final NatureOperationMapper natureOperationMapper;
    @PersistenceContext
    EntityManager em;


    public OperationTransfertPartServiceImpl(OperationTransfertPartDao operationTransfertPartDao, OperationTransfertPartMapper transfertPartMapper, AppService appService, NatureOperationDao natureOperationDao, LibraryDao libraryDao, NatureOperationMapper natureOperationMapper) {
        this.operationTransfertPartDao = operationTransfertPartDao;
        this.transfertPartMapper = transfertPartMapper;
        this.appService = appService;
        this.natureOperationDao = natureOperationDao;
        this.libraryDao = libraryDao;
        this.natureOperationMapper = natureOperationMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(TransfertPartListeRequest request) {
        var parameters = request.getDatatableParameters();
        Pageable pageable= PageRequest.of(parameters.getStart()/ parameters.getLength(), parameters.getLength());
        try {

            Page<OperationTransfertDePartProjection> listeOpTransPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                listeOpTransPage = new PageImpl<>(null);
            }
            else {
                listeOpTransPage = libraryDao.operationTransfertDePart(
                            request.getIdOpcvm(),false,pageable);
            }
            List<OperationTransfertDePartProjection> listeOpTransDep=listeOpTransPage.stream().toList();
            DataTablesResponse<OperationTransfertDePartProjection> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)listeOpTransPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)listeOpTransPage.getTotalElements());
            dataTablesResponse.setData(listeOpTransDep);
            return ResponseHandler.generateResponse(
                    "Liste des transferts départs",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public OperationTransfertPart afficherSelonId(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> creer(OperationTransfertPartDto opDto) {
        try {
            OperationTransfertPart op = transfertPartMapper.deDto(opDto);
            op = operationTransfertPartDao.save(op);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    transfertPartMapper.deEntite(op));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(OperationTransfertPartDto opDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> creerTransfert(TransfertPartAddRequest request) {
        var montant = request.getCumpEntre().multiply(request.getQteTrans());
        var seance = appService.currentSeance(request.getOpcvm().getIdOpcvm());
        String sortie="";
        // Transfert départ
        var q=em.createStoredProcedureQuery("[Comptabilite].[PS_OperationTransfertDePart_IP1_New]");
        q.registerStoredProcedureParameter("IdOperation", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("idActionnaire", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


        q.setParameter("IdOperation", 0);
        q.setParameter("idOpcvm", request.getOpcvm().getIdOpcvm());
        q.setParameter("idActionnaire", request.getDemandeur().getIdPersonne());
        q.setParameter("idTitre",0);
        q.setParameter("IdTransaction",0);
        q.setParameter("idSeance", request.getIdSeance());
        q.setParameter("codeNatureOperation", "TRSFT_PART_DEPAR");
        q.setParameter("dateOperation", request.getDateOperation());
        q.setParameter("libelleOperation","TRANSFERT DEPART DE " + request.getQteTrans().toString()  +
                            " PARTS PAR " + request.getDemandeur().getDenomination());
        q.setParameter("dateSaisie", LocalDateTime.now());
        q.setParameter("datePiece", seance.getDateFermeture());
        q.setParameter("dateValeur", request.getDateOperation());
        q.setParameter("referencePiece", "");
        q.setParameter("montant", montant);
        q.setParameter("ecriture","A");
        q.setParameter("estOD",false);
        q.setParameter("type", "TRSF");
        q.setParameter("valeurFormule", "21:" + montant +
                ";104:" + request.getQteTrans().toString());
        q.setParameter("valeurCodeAnalytique", "OPC:" + request.getOpcvm().getIdOpcvm().toString() +
                ";ACT:" + request.getDemandeur().getIdPersonne().toString());
        q.setParameter("userLogin", request.getUserLogin());
        q.setParameter("dateDernModifClient", LocalDateTime.now());
        q.setParameter("CodeLangue", "fr-FR");
        q.setParameter("Sortie", sortie);
        q.execute();
        String[] s=new String[20];
        String result=(String) q.getOutputParameterValue("Sortie");
        s=result.split("#");
//        OperationTransfertPartDto departDto = new OperationTransfertPartDto();
//        departDto.setIdTitre(0L);
//        departDto.setIdSeance(request.getIdSeance());
//        departDto.setDemandeur(request.getDemandeur());
//        departDto.setActionnaire(request.getDemandeur());
//        departDto.setIdActionnaire(request.getDemandeur().getIdPersonne());
//        departDto.setBeneficiaire(request.getBeneficiaire());
//        departDto.setQteTransfert(request.getQteTrans());
//        departDto.setCumpEntre(request.getCumpEntre());
//        departDto.setQteInitiale(request.getQteAvtD());
//        NatureOperationDto nature = natureOperationMapper.deNatureOperation(
//            natureOperationDao.findByCodeNatureOperationIgnoreCase("TRSFT_PART_DEPAR").orElse(null)
//        );
//        departDto.setNatureOperation(nature);
//        departDto.setOpcvm(request.getOpcvm());
//        departDto.setLibelleOperation(
//            "TRANSFERT DEPART DE " + request.getQteTrans().toString() +
//            " PARTS PAR " + request.getDemandeur().getDenomination()
//        );
//        departDto.setMontant(montant);
//        departDto.setValeurCodeAnalytique(
//            "OPC:" + request.getOpcvm().getIdOpcvm().toString() +
//            ";ACT:" + request.getDemandeur().getIdPersonne().toString()
//        );
//        departDto.setValeurFormule(
//            "21:" + departDto.getMontant().toString() +
//            ";104:" + request.getQteTrans().toString()
//        );
//        departDto.setReferencePiece("");
//        departDto.setDateOperation(request.getDateOperation());
//        departDto.setDatePiece(seance.getDateFermeture());
//        departDto.setDateSaisie(LocalDateTime.now());
//        departDto.setDateValeur(request.getDateOperation());
//        departDto.setDateDernModifClient(LocalDateTime.now());
//        departDto = appService.genererEcritureComptable(departDto);
        // Transfert arrivéString[] s2=new String[20];
        String[] s2=new String[20];
        if(Long.parseLong(s[s.length-1])>0L)
        {
            q=em.createStoredProcedureQuery("[Comptabilite].[PS_OperationTransfertDePart_IP1_New]");
            q.registerStoredProcedureParameter("IdOperation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idActionnaire", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTitre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("ecriture", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estOD", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("type", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            q.setParameter("IdOperation", 0);
            q.setParameter("idOpcvm", request.getOpcvm().getIdOpcvm());
            q.setParameter("idActionnaire", request.getBeneficiaire().getIdPersonne());
            q.setParameter("idTitre",0);
            q.setParameter("IdTransaction",0);
            q.setParameter("idSeance", request.getIdSeance());
            q.setParameter("codeNatureOperation", "TRSFT_PART_ARV");
            q.setParameter("dateOperation", request.getDateOperation());
            q.setParameter("libelleOperation","TRANSFERT ARRIVE DE " + request.getQteTrans().toString() +
                    " PARTS A " + request.getBeneficiaire().getDenomination());
            q.setParameter("dateSaisie", LocalDateTime.now());
            q.setParameter("datePiece", seance.getDateFermeture());
            q.setParameter("dateValeur", request.getDateOperation());
            q.setParameter("referencePiece", "");
            q.setParameter("montant", montant);
            q.setParameter("ecriture","A");
            q.setParameter("estOD",false);
            q.setParameter("type", "TRSF");
            q.setParameter("valeurFormule", "21:" + montant +
                    ";104:" + request.getQteTrans().toString());
            q.setParameter("valeurCodeAnalytique",  "OPC:" + request.getOpcvm().getIdOpcvm().toString() +
                    ";ACT:" + request.getDemandeur().getIdPersonne().toString());
            q.setParameter("userLogin", request.getUserLogin());
            q.setParameter("dateDernModifClient", LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie", sortie);
            q.execute();

             result=(String) q.getOutputParameterValue("Sortie");
            s2=result.split("#");

//            OperationTransfertPartDto arriveDto = new OperationTransfertPartDto();
//
//                arriveDto.setIdTitre(0L);
//                arriveDto.setIdSeance(request.getIdSeance());
//                arriveDto.setDemandeur(request.getDemandeur());
//                arriveDto.setActionnaire(request.getBeneficiaire());
//                arriveDto.setIdActionnaire(request.getDemandeur().getIdPersonne());
//                arriveDto.setBeneficiaire(request.getBeneficiaire());
//                arriveDto.setQteTransfert(request.getQteTrans());
//                arriveDto.setCumpEntre(request.getCumpEntre());
//                arriveDto.setQteInitiale(request.getQteAvtB());
//                NatureOperationDto natureArr = natureOperationMapper.deNatureOperation(
//                        natureOperationDao.findByCodeNatureOperationIgnoreCase("TRSFT_PART_ARV").orElse(null)
//                );
//                arriveDto.setNatureOperation(natureArr);
//                arriveDto.setOpcvm(request.getOpcvm());
//                arriveDto.setLibelleOperation(
//                        "TRANSFERT ARRIVE DE " + request.getQteTrans().toString() +
//                                " PARTS A " + request.getBeneficiaire().getDenomination()
//                );
//                arriveDto.setMontant(montant);
//                arriveDto.setValeurCodeAnalytique(
//                        "OPC:" + request.getOpcvm().getIdOpcvm().toString() +
//                                ";ACT:" + request.getDemandeur().getIdPersonne().toString()
//                );
//                arriveDto.setValeurFormule(
//                        "21:" + arriveDto.getMontant().toString() +
//                                ";104:" + request.getQteTrans().toString()
//                );
//                arriveDto.setReferencePiece("");
//                arriveDto.setDateOperation(request.getDateOperation());
//                arriveDto.setDatePiece(seance.getDateFermeture());
//                arriveDto.setDateSaisie(LocalDateTime.now());
//                arriveDto.setDateValeur(request.getDateOperation());
//                arriveDto.setDateDernModifClient(LocalDateTime.now());
//                arriveDto = appService.genererEcritureComptable(arriveDto);
            if(Long.parseLong(s2[s2.length-1])>0L)
            {
                q=em.createStoredProcedureQuery("[Operation].[PS_OperationTransfertDePart_IP]");
                q.registerStoredProcedureParameter("idDemandeur", Long.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("qteInitialeD", BigDecimal.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("idBeneficiaire", Long.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("qteInitialeB", BigDecimal.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("cumpEntre", BigDecimal.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("qteTransfert", BigDecimal.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("idOpcvm", Long.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("idOperation", String.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("userLogin", String.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("CodeLangue", String.class,ParameterMode.IN);
                q.registerStoredProcedureParameter("Sortie", String.class,ParameterMode.OUT);


                q.setParameter("idDemandeur", request.getDemandeur().getIdPersonne());
                q.setParameter("qteInitialeD", request.getQteApresD());
                q.setParameter("idBeneficiaire", request.getBeneficiaire().getIdPersonne());
                q.setParameter("qteInitialeB", request.getQteApresB());
                q.setParameter("cumpEntre", request.getCumpEntre());
                q.setParameter("qteTransfert", request.getQteTrans());
                q.setParameter("dateOperation", request.getDateOperation());
                q.setParameter("idOpcvm", request.getOpcvm().getIdOpcvm());
                q.setParameter("idOperation", s[s.length-1]+";"+s2[s2.length-1]);
                q.setParameter("userLogin", request.getUserLogin());
                q.setParameter("dateDernModifClient", LocalDateTime.now());
                q.setParameter("CodeLangue", "fr-FR");
                q.setParameter("Sortie", sortie);

                q.execute();
            }

        }

        return ResponseHandler.generateResponse(
            "Enregistrement effectué avec succès",
            HttpStatus.OK,
            s[s.length-1] + ";" + s2[s2.length-1]
        );
    }
}
