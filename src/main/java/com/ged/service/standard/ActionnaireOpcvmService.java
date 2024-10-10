package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ActionnaireOpcvmDto;
import com.ged.entity.standard.CleActionnaireOpcvm;
import com.ged.entity.standard.ActionnaireOpcvm;
import org.springframework.http.ResponseEntity;

public interface ActionnaireOpcvmService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm);
    ResponseEntity<Object> afficherTous();
    ActionnaireOpcvm afficherSelonId(CleActionnaireOpcvm idActionnaireOpcvm);
    ResponseEntity<Object> afficher(CleActionnaireOpcvm idActionnaireOpcvm);
    ResponseEntity<Object> creer(ActionnaireOpcvmDto[] ActionnaireOpcvmDto);
    ResponseEntity<Object> modifier(ActionnaireOpcvmDto ActionnaireOpcvmDto);
    ResponseEntity<Object> supprimer(CleActionnaireOpcvm idActionnaireOpcvm);
}
