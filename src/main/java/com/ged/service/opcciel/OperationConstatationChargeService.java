package com.ged.service.opcciel;

import com.ged.dto.opcciel.OperationConstatationChargeDto;
import com.ged.dto.request.ConstatationChargeEditRequest;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ConstatationChargeAddRequest;
import com.ged.entity.opcciel.OperationConstatationCharge;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface OperationConstatationChargeService {
    ResponseEntity<?> afficherTous(ConstatationChargeListeRequest request);
    OperationConstatationCharge afficherSelonId(Long id);
    ResponseEntity<?> afficherConstationCharge(Long idOpcvm,Long idSeance);
    void imprimerConstationCharge(Long idOpcvm,Long idSeance, HttpServletResponse response) throws IOException, JRException;
    ResponseEntity<?> creer(ConstatationChargeAddRequest request);
    ResponseEntity<?> modifier(ConstatationChargeEditRequest request);
    ResponseEntity<?> supprimer(Long id);
}
