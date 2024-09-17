package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.OpcDto;
import com.ged.entity.titresciel.Opc;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface OpcService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<OpcDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Opc afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(OpcDto OpcDto);
    ResponseEntity<Object> modifier(OpcDto OpcDto);
    ResponseEntity<Object> supprimer(Long id);
}
