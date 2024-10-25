package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.DepotRachatDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.comptabilite.NatureOperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.entity.opcciel.DepotRachat;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.mapper.opcciel.DepotRachatMapper;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.DepotRachatService;
import com.ged.service.opcciel.NantissementService;
import com.ged.service.opcciel.SeanceOpcvmService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.hibernate.procedure.ProcedureOutputs;
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
public class NatissementServiceImpl implements NantissementService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;

    @Override
    public BigDecimal afficherPartNanti(Long idOpcvm,
                                           Long idActionnaire) {
        BigDecimal partNanti=BigDecimal.valueOf(0);
        Object list;
        StoredProcedureQuery q = em.createStoredProcedureQuery("[Operation].[PS_Nantissement_SP_SOMME]");
        q.registerStoredProcedureParameter("idActionnaire", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("estLevee", Boolean.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("estVerifie1", Boolean.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("estVerifie2", Boolean.class, ParameterMode.IN);

        try {

            q.setParameter("idActionnaire", idActionnaire);
            q.setParameter("idOpcvm", idOpcvm);
            q.setParameter("estLevee", false);
            q.setParameter("estVerifie1", true);
            q.setParameter("estVerifie2", true);

            // Execute query
            q.execute();
            list = q.getSingleResult();
            if(list==null)
                partNanti=BigDecimal.valueOf(0);
            else
                partNanti=BigDecimal.valueOf(Double.valueOf(list.toString()));
        } finally {
            try
            {
                q.unwrap(ProcedureOutputs.class).release();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        return partNanti;
    }

}
