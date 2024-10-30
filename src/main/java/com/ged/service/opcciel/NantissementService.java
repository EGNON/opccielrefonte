
package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.List;

public interface NantissementService {
    BigDecimal afficherPartNanti(Long idOpcvm, Long idActionnaire);
}
