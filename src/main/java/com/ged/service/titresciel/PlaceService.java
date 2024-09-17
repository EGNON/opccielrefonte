package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.PlaceDto;
import com.ged.entity.titresciel.Place;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface PlaceService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<PlaceDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object>  afficherTous();
    Place afficherSelonId(String codePlace);
    ResponseEntity<Object>  afficher(String codePlace);
    ResponseEntity<Object>  creer(PlaceDto PlaceDto);
    ResponseEntity<Object>  modifier(PlaceDto PlaceDto);
    ResponseEntity<Object>  supprimer(String codePlace);
}
