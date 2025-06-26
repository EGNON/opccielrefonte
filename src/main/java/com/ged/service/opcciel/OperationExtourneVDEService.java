package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationExtourneVDEDto;
//import com.ged.projection.OperationExtourneVDEProjection;
import com.ged.dto.request.ConstatationChargeListeRequest;
import com.ged.dto.request.ExtourneVDERequest;
import com.ged.entity.opcciel.CleOperationExtourneVDE;
import com.ged.entity.opcciel.OperationExtourneVDE;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;

public interface OperationExtourneVDEService {
    ResponseEntity<Object> afficherTous(ConstatationChargeListeRequest request);
    OperationExtourneVDE afficherSelonId(CleOperationExtourneVDE id);
    ResponseEntity<Object> afficher(CleOperationExtourneVDE id);
    ResponseEntity<Object> jaspertReportVDE(Long idSeance,Long idOpcvm,Boolean estVerifie,Boolean estVerifie1,Boolean estVerifie2,Long niveau, HttpServletResponse response) throws IOException, JRException;
    ResponseEntity<Object> creer(ExtourneVDERequest extourneVDERequest) ;
    ResponseEntity<Object> afficherTitre(Long idOpcvm,LocalDateTime dateEstimation,String typeEvenement);
    ResponseEntity<Object> afficherTous(Long idOpcvm);
    ResponseEntity<Object> creer(OperationExtourneVDEDto operationExtourneVDEDto);
    ResponseEntity<Object> modifier(OperationExtourneVDEDto operationExtourneVDEDto);
    ResponseEntity<Object> supprimer(String userLogin,
                                    Long idExtourneVDE);
}
