package com.ged.dao.lab.reportings;

import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.lab.reportings.RegistreConfidentiel;
import com.ged.dto.lab.reportings.TransactionSuspecteInhabituelleDto;
import jakarta.persistence.*;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional
public class ReportingsDao {
    @PersistenceContext
    EntityManager em;

    public List<Object[]> recensementOpClientOcassionnel() {
        List<Object[]> list;
        StoredProcedureQuery q = em.createStoredProcedureQuery("[Impression].[PS_OperationClient]");
        try {
            // Execute query
            q.execute();
            list = q.getResultList();
        } finally {
            try
            {
                q.unwrap(ProcedureOutputs.class).release();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        /*q.execute();
        list = q.getResultList();*/
        return list;
    }

    public List<Object[]> transactionSuspectInhabituel(TransactionSuspecteInhabituelleDto transactionSuspecteInhabituelleDto) {
        List<Object[]> list;
        StoredProcedureQuery q = em.createStoredProcedureQuery("[Impression].[PS_TransactionSuspectInhabituel]");
        q.registerStoredProcedureParameter("typePersonne", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("opMontantTransac", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("montantTransac", BigDecimal.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("opQtePart", String.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("qtePart", BigDecimal.class, ParameterMode.IN);

        try {
            q.setParameter("typePersonne", transactionSuspecteInhabituelleDto.getTypePersonne());
            q.setParameter("opMontantTransac", transactionSuspecteInhabituelleDto.getOpMontantTransac());
            q.setParameter("montantTransac", transactionSuspecteInhabituelleDto.getMontantTransac());
            q.setParameter("opQtePart", transactionSuspecteInhabituelleDto.getOpQtePart());
            q.setParameter("qtePart", transactionSuspecteInhabituelleDto.getQtePart());

            // Execute query
            q.execute();
            list = q.getResultList();
        } finally {
            try {
                q.unwrap(ProcedureOutputs.class).release();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return list;
    }

    public List<Object[]> suiviClientSanction() {
        List<Object[]> list;
        StoredProcedureQuery q = em.createStoredProcedureQuery("[Impression].[PS_SuiviClientSanctionne]");
        try {
            // Execute query
            q.execute();
            list = q.getResultList();
        } finally {
            try
            {
                q.unwrap(ProcedureOutputs.class).release();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return list;
    }

    public List<Object[]> registreConfidentiel(BeginEndDateParameter beginEndDateParameter) {
        List<Object[]> list;
        StoredProcedureQuery q = em.createStoredProcedureQuery("[Impression].[PS_RegistreConfidentiel]");
        q.registerStoredProcedureParameter("dateDebut", java.time.LocalDateTime.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("dateFin", java.time.LocalDateTime.class, ParameterMode.IN);

        try {
            System.out.println("DATE === " + beginEndDateParameter.getStartDate());
            System.out.println("DATE === " + beginEndDateParameter.getEndDate());
            q.setParameter("dateDebut", beginEndDateParameter.getStartDate());
            q.setParameter("dateFin", beginEndDateParameter.getEndDate());

            // Execute query
            q.execute();
            list = q.getResultList();
        } finally {
            try
            {
                q.unwrap(ProcedureOutputs.class).release();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return list;
    }

    public List<Object[]> volatilite(Long idOpcvm, BeginEndDateParameter beginEndDateParameter) {
        List<Object[]> list;

        var q = em.createNativeQuery("SELECT * FROM [Impressions].[FT_CalculVolatiliteJ](:idOpcvm, :dateDebut, :dateFin)");
                q.setParameter("idOpcvm", idOpcvm);
                q.setParameter("dateDebut", beginEndDateParameter.getStartDate());
                q.setParameter("dateFin", beginEndDateParameter.getEndDate());

        try {
            System.out.println("PARAMETRE 1 === " + beginEndDateParameter.getStartDate().toString());
            System.out.println("PARAMETRE 2 === " + beginEndDateParameter.getEndDate().toString());
            // Execute query
            list = q.getResultList();
        } finally {
            try
            {

            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return list;
    }
}
