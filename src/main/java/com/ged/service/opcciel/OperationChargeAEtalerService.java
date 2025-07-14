package com.ged.service.opcciel;

import com.ged.dto.opcciel.OperationChargeAEtalerDto;
import com.ged.dto.opcciel.OperationDifferenceEstimationDto;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.entity.opcciel.CleOperationDifferenceEstimation;
import com.ged.entity.opcciel.OperationDifferenceEstimation;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.apache.catalina.LifecycleState;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface OperationChargeAEtalerService {
     ResponseEntity<Object> precalculChargeAEtaler(DifferenceEstimationRequest differenceEstimationRequest);
     ResponseEntity<Object> chargerOperation(DifferenceEstimationRequest differenceEstimationRequest);
     ResponseEntity<Object> creer(List<OperationChargeAEtalerDto> operationChargeAEtalerDto);
     ResponseEntity<Object> verifier(Long idSeance,Long idOpcvm, HttpServletResponse response) throws IOException, JRException;
     ResponseEntity<Object> verifier(Long idSeance,Long idOpcvm,Boolean estVerifie1,Boolean estVerifie2,Long niveau, HttpServletResponse response) throws IOException, JRException;
     ResponseEntity<Object> precalculChargeAEtalerListe(DifferenceEstimationRequest differenceEstimationRequest);
     ResponseEntity<Object> validationNiveau(DifferenceEstimationRequest differenceEstimationRequest);
}
