package com.ged.service.titresciel;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.CompartimentDto;
import com.ged.entity.titresciel.Compartiment;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompartimentService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<CompartimentDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Compartiment afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(CompartimentDto CompartimentDto);
    ResponseEntity<Object> modifier(CompartimentDto CompartimentDto);
    ResponseEntity<Object> supprimer(Long id);
}
