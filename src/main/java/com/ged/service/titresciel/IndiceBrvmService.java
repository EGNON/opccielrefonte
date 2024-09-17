package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.CleIndiceBrvmDto;
import com.ged.dto.titresciel.IndiceBrvmDto;
import com.ged.entity.titresciel.IndiceBrvm;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IndiceBrvmService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    IndiceBrvm afficherSelonId(CleIndiceBrvmDto id);
    ResponseEntity<Object> afficher(CleIndiceBrvmDto id);
    ResponseEntity<Object> creer(IndiceBrvmDto indiceBrvmDto);
    ResponseEntity<Object> modifier(IndiceBrvmDto indiceBrvmDto);
    ResponseEntity<Object> supprimer(CleIndiceBrvmDto id);
    ResponseEntity<Object> addAll(List<IndiceBrvmDto> indiceBrvmDtos);
}
