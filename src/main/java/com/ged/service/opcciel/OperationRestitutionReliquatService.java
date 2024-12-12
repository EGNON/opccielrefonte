package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationRestitutionReliquatDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationRestitutionReliquatService {
    ResponseEntity<Object> listeOpRestitutionReliquat(DatatableParameters parameters, Long idOpcvm, Long idSeance);

    ResponseEntity<Object> listeOpRestitutionReliquat(Long idOpcvm, Long idSeance);

    ResponseEntity<Object> precalculRestitutionReliquat(DatatableParameters parameters, Long idOpcvm, Long idSeance);

    ResponseEntity<Object> enregisterTous(List<OperationRestitutionReliquatDto> restitutionReliquatDtos);
}
