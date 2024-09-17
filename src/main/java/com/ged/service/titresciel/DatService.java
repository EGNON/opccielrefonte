package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.DatDto;
import com.ged.entity.titresciel.Dat;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface DatService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<DatDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Dat afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(DatDto DatDto);
    ResponseEntity<Object> modifier(DatDto DatDto);
    ResponseEntity<Object> supprimer(Long id);
}
