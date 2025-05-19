package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationDetachementDto;
import com.ged.dto.request.SousRachRequest;
import com.ged.entity.opcciel.OperationDetachement;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public interface OperationDetachementService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm);
    OperationDetachement afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> afficherTitre(Long idOpcvm,LocalDateTime dateEstimation,String typeEvenement);
    ResponseEntity<Object> afficherTous(Long idOpcvm);
    ResponseEntity<Object> creer(OperationDetachementDto operationDetachementDto);
    ResponseEntity<Object> valeurOuQte(OperationDetachementDto operationDetachementDto);
    ResponseEntity<Object> modifier(OperationDetachementDto operationDetachementDto);
    ResponseEntity<Object> supprimer(Long id);
}
