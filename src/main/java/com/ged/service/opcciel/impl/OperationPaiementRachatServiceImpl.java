package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dto.opcciel.OperationPaiementRachatDto2;
import com.ged.dto.opcciel.PrecalculPaiementRachatDto;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationPaiementRachatService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OperationPaiementRachatServiceImpl implements OperationPaiementRachatService {
    @PersistenceContext
    EntityManager em;
    private final SeanceOpcvmDao seanceOpcvmDao;

    public OperationPaiementRachatServiceImpl(SeanceOpcvmDao seanceOpcvmDao) {
        this.seanceOpcvmDao = seanceOpcvmDao;
    }

    @Override
    public ResponseEntity<Object> precalculPAiementRachat(Long idOpcvm, Long idSeance) {
        try {
            List<Object[]> resultat;
            List<PrecalculPaiementRachatDto> precalculPaiementRachatDtos = new ArrayList<>();
            var q = em.createStoredProcedureQuery("[Operation].[PS_PrecalculPaieRachat]");
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);


            q.setParameter("idOpcvm", idOpcvm);
            q.setParameter("idSeance", idSeance);


            try {
                // Execute query
                resultat = q.getResultList();
                for (Object[] o : resultat) {
                    PrecalculPaiementRachatDto precalculPaiementRachatDto = new PrecalculPaiementRachatDto();
                    precalculPaiementRachatDto.setIdActionnaire(Long.valueOf(o[2].toString()));
                    precalculPaiementRachatDto.setIdOpcvm(Long.valueOf(o[0].toString()));
                    precalculPaiementRachatDto.setIdSeance(Long.valueOf(o[1].toString()));
                    precalculPaiementRachatDto.setNomSigle(o[3].toString());
                    precalculPaiementRachatDto.setPrenomRaisonSociale(o[4].toString());
                    precalculPaiementRachatDto.setMontant(BigDecimal.valueOf(Double.parseDouble(o[5].toString())));
                    precalculPaiementRachatDtos.add(precalculPaiementRachatDto);
                }
                return ResponseHandler.generateResponse(
                        "Précalcul paiement rachat",
                        HttpStatus.OK,
                        precalculPaiementRachatDtos);
            } finally {
                try {

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> liste(Long idOpcvm, Long idSeance) {
        try {
            List<Object[]> resultat;
            List<OperationPaiementRachatDto2> operationPaiementRachatTab = new ArrayList<>();
            var q = em.createStoredProcedureQuery("[Operation].[PS_OperationPaiementRachat_SP]");
            q.registerStoredProcedureParameter("idOperation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idTransaction", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idActionnaire", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("numLigne", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateCreationServeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifServeur", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("supprimer", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("rowvers", Byte.class, ParameterMode.IN);

            q.setParameter("idOperation", null);
            q.setParameter("idTransaction", null);
            q.setParameter("idSeance", idSeance);
            q.setParameter("idActionnaire", null);
            q.setParameter("idOpcvm", idOpcvm);
            q.setParameter("codeNatureOperation", null);
            q.setParameter("dateOperation", null);
            q.setParameter("libelleOperation", null);
            q.setParameter("dateSaisie", null);
            q.setParameter("datePiece", null);
            q.setParameter("dateValeur", null);
            q.setParameter("referencePiece", null);
            q.setParameter("montant", null);
            q.setParameter("numLigne", null);
            q.setParameter("dateCreationServeur", null);
            q.setParameter("dateDernModifServeur", null);
            q.setParameter("dateDernModifClient", null);
            q.setParameter("userLogin", null);
            q.setParameter("supprimer", false);
            q.setParameter("rowvers", null);

            try {
                // Execute query
                resultat = q.getResultList();
                for (Object[] o : resultat) {
                    OperationPaiementRachatDto2 operationPaiementRachatDto2 = new OperationPaiementRachatDto2();
                    operationPaiementRachatDto2.setIdActionnaire(Long.valueOf(o[3].toString()));
                    operationPaiementRachatDto2.setIdOperation(Long.valueOf(o[0].toString()));
                    operationPaiementRachatDto2.setIdOpcvm(Long.valueOf(o[6].toString()));
                    operationPaiementRachatDto2.setIdSeance(Long.valueOf(o[2].toString()));
                    operationPaiementRachatDto2.setNomSigle(o[4].toString());
                    operationPaiementRachatDto2.setPrenomRaisonSociale(o[5].toString());
                    operationPaiementRachatDto2.setMontant(BigDecimal.valueOf(Double.parseDouble(o[15].toString())));
                    operationPaiementRachatTab.add(operationPaiementRachatDto2);
                }
                return ResponseHandler.generateResponse(
                        "Operation paiement rachat",
                        HttpStatus.OK,
                        operationPaiementRachatTab);
            } finally {
                try {

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> verifierPaiementRachat(Long idOpcvm, Long idSeance,String denominationOpcvm,
                                                         String dateOuv,
                                                         String dateFerm, HttpServletResponse response) {
        try {
        List<Object[]> resultat;
        List<OperationPaiementRachatDto2> operationPaiementRachatTab = new ArrayList<>();
        var q = em.createStoredProcedureQuery("[Operation].[PS_OperationPaiementRachat_SP]");
        q.registerStoredProcedureParameter("idOperation", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("idTransaction", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("idActionnaire", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("numLigne", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateCreationServeur", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateDernModifServeur", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("supprimer", Boolean.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("rowvers", Byte.class, ParameterMode.IN);

        q.setParameter("idOperation", null);
        q.setParameter("idTransaction", null);
        q.setParameter("idSeance", idSeance);
        q.setParameter("idActionnaire", null);
        q.setParameter("idOpcvm", idOpcvm);
        q.setParameter("codeNatureOperation", null);
        q.setParameter("dateOperation", null);
        q.setParameter("libelleOperation", null);
        q.setParameter("dateSaisie", null);
        q.setParameter("datePiece", null);
        q.setParameter("dateValeur", null);
        q.setParameter("referencePiece", null);
        q.setParameter("montant", null);
        q.setParameter("numLigne", null);
        q.setParameter("dateCreationServeur", null);
        q.setParameter("dateDernModifServeur", null);
        q.setParameter("dateDernModifClient", null);
        q.setParameter("userLogin", null);
        q.setParameter("supprimer", false);
        q.setParameter("rowvers", null);

        try {
            // Execute query
            resultat = q.getResultList();
            for (Object[] o : resultat) {
                OperationPaiementRachatDto2 operationPaiementRachatDto2 = new OperationPaiementRachatDto2();
                operationPaiementRachatDto2.setIdActionnaire(Long.valueOf(o[3].toString()));
                operationPaiementRachatDto2.setIdOperation(Long.valueOf(o[0].toString()));
                operationPaiementRachatDto2.setIdOpcvm(Long.valueOf(o[6].toString()));
                operationPaiementRachatDto2.setIdSeance(Long.valueOf(o[2].toString()));
                operationPaiementRachatDto2.setNomSigle(o[4].toString());
                operationPaiementRachatDto2.setPrenomRaisonSociale(o[5].toString());
                operationPaiementRachatDto2.setMontant(BigDecimal.valueOf(Double.parseDouble(o[15].toString())));
                operationPaiementRachatTab.add(operationPaiementRachatDto2);
            }
            Map<String, Object> parameters = new HashMap<>();
            DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
//            SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeanceEnCours(idOpcvm);
            String letterDate = dateFormatter.format(new Date());

            parameters.put("letterDate", letterDate);
            parameters.put("denominationOpcvm", denominationOpcvm);
            parameters.put("dateOuv", dateOuv);
            parameters.put("dateFerm", dateFerm);
//            File file = ResourceUtils.getFile("classpath:paiementRachat.jrxml");
//            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operationPaiementRachatTab);
//            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            InputStream inputStream = getClass().getResourceAsStream("/paiementRachat.jrxml");
            if (inputStream == null) {
                throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
            }

            JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(operationPaiementRachatTab);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

            // Export vers le flux de sortie HTTP
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
            return ResponseHandler.generateResponse(
                    "Operation paiement rachat",
                    HttpStatus.OK,
                    operationPaiementRachatTab);
        } finally {
            try {

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    } catch(Exception e)

    {
        return ResponseHandler.generateResponse(
                e.getMessage(),
                HttpStatus.MULTI_STATUS,
                e);
    }
}

    @Override
    public ResponseEntity<Object> creer(OperationPaiementRachatDto2[] operationPaiementRachatTab) {
        try {
            String sortie="";
            for(OperationPaiementRachatDto2 o:operationPaiementRachatTab)
            {
                var q = em.createStoredProcedureQuery("[Operation].[PS_OperationPaiementRachat_IP]");
                q.registerStoredProcedureParameter("idOperation", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idTransaction", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idActionnaire", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateOperation", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateSaisie", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("datePiece", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateValeur", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("referencePiece", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("montant", BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


                q.setParameter("idOperation",0);
                q.setParameter("idTransaction",0);
                q.setParameter("idSeance",o.getIdSeance());
                q.setParameter("idActionnaire",o.getIdActionnaire());
                q.setParameter("idOpcvm", o.getIdOpcvm());
                q.setParameter("codeNatureOperation", o.getCodeNatureOperation());
                q.setParameter("dateOperation",o.getDateOperation());
                q.setParameter("libelleOperation", o.getLibelleOperation());
                q.setParameter("dateSaisie",o.getDateSaisie());
                q.setParameter("datePiece", o.getDatePiece());
                q.setParameter("dateValeur", o.getDateValeur());
                q.setParameter("referencePiece", o.getReferencePiece());
                q.setParameter("montant", o.getMontant());
                q.setParameter("valeurFormule",o.getValeurFormule());
                q.setParameter("valeurCodeAnalytique", o.getValeurCodeAnalytique());
                q.setParameter("userLogin", o.getUserLogin());
                q.setParameter("dateDernModifClient",LocalDateTime.now());
                q.setParameter("CodeLangue", "fr-FR");
                q.setParameter("Sortie",sortie);

                try {
                    // Execute query
                    q.execute();
//                    String result=(String) q.getOutputParameterValue("Sortie");
//                    String[] s=result.split("#");


                    //System.out.println("idOperation="+s[s.length-1]);
                } finally {
                    try {

                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
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
}
