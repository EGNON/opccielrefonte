package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationPaiementRachatDto2;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto2;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import org.springframework.http.ResponseEntity;

public interface OperationPaiementRachatService {
    ResponseEntity<Object> precalculPAiementRachat(Long idOpcvm,Long idSeance);
    ResponseEntity<Object> liste(Long idOpcvm,Long idSeance);
    ResponseEntity<Object> creer(OperationPaiementRachatDto2[] operationPaiementRachatTab);
}
