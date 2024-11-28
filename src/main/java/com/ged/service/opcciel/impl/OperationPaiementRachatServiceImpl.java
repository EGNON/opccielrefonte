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
import com.ged.dto.opcciel.OperationSouscriptionRachatDto2;
import com.ged.dto.opcciel.PrecalculPaiementRachatDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.entity.opcciel.comptabilite.Transaction;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.opcciel.OperationSouscriptionRachatMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationPaiementRachatService;
import com.ged.service.opcciel.OperationSouscriptionRachatService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationPaiementRachatServiceImpl implements OperationPaiementRachatService {
    @PersistenceContext
    EntityManager em;

    @Override
    public ResponseEntity<Object> precalculPAiementRachat(Long idOpcvm, Long idSeance) {
        try {
            List<Object[]>  resultat;
            List<PrecalculPaiementRachatDto> precalculPaiementRachatDtos=new ArrayList<>();
        var q = em.createStoredProcedureQuery("[Operation].[PS_PrecalculPaieRachat]");
        q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
        q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);


        q.setParameter("idOpcvm",idOpcvm);
        q.setParameter("idSeance",idSeance);


        try {
            // Execute query
            resultat=q.getResultList();
            for(Object[] o:resultat){
                PrecalculPaiementRachatDto precalculPaiementRachatDto=new PrecalculPaiementRachatDto();
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
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
