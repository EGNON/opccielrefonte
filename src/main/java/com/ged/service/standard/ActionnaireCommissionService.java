package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ActionnaireCommissionDto;
import com.ged.entity.standard.ActionnaireCommission;
import com.ged.entity.standard.CleActionnaireCommission;
import org.springframework.http.ResponseEntity;

public interface ActionnaireCommissionService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm);
    ResponseEntity<Object> afficherTous();
    ActionnaireCommission afficherSelonId(CleActionnaireCommission idActionnaireCommission);
    ResponseEntity<Object> afficher(CleActionnaireCommission idActionnaireCommission);
    ResponseEntity<Object> creer(ActionnaireCommissionDto ActionnaireCommissionDto);
    ResponseEntity<Object> modifier(ActionnaireCommissionDto ActionnaireCommissionDto);
    ResponseEntity<Object> supprimer(CleActionnaireCommission idActionnaireCommission);
}
