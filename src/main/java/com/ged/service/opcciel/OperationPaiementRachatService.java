package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationPaiementRachatDto2;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto2;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;

public interface OperationPaiementRachatService {
    ResponseEntity<Object> precalculPAiementRachat(Long idOpcvm,Long idSeance);
    ResponseEntity<Object> liste(Long idOpcvm,Long idSeance);
    ResponseEntity<Object> verifierPaiementRachat(Long idOpcvm,
                                                  Long idSeance,
                                                  String denominationOpcvm,
                                                  String dateOuv,
                                                  String dateFerm,
                                                  HttpServletResponse response);
    ResponseEntity<Object> creer(OperationPaiementRachatDto2[] operationPaiementRachatTab);
}
