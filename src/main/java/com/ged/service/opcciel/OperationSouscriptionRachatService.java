package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto2;
import com.ged.dto.request.SousRachRequest;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public interface OperationSouscriptionRachatService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    OperationSouscriptionRachat afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> listeOperationSouscriptionRachat(Long idOpcvm,
                                                            String codeNatureOperation, LocalDateTime dateDebut,LocalDateTime dateFin);

    ResponseEntity<Object> listeOpSouscriptionRachat(SousRachRequest sousRachRequest);

    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> creer(OperationSouscriptionRachatDto operationSouscriptionRachatDto);
    ResponseEntity<Object> avisOperation(String idOperation);
    ResponseEntity<Object> avisOperation(String idOperation,HttpServletResponse response);
    ResponseEntity<Object> avisOperation2(String idOperation) throws JRException, FileNotFoundException;

    ResponseEntity<Object> avisSouscriptionExportJasperReport(HttpServletResponse response, List<OperationSouscriptionRachatDto> operationSouscriptionRachatDtoList);

    ResponseEntity<Object> creer(OperationSouscriptionRachatDto2[] operationSouscriptionRachatTab);
    ResponseEntity<Object> modifier(OperationSouscriptionRachatDto operationSouscriptionRachatDto);
    ResponseEntity<Object> supprimer(Long id);
}
