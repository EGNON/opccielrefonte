package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.TypeModeleMessageDto;
import com.ged.entity.standard.TypeModeleMessage;
import org.springframework.http.ResponseEntity;

public interface TypeModeleMessageService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);
    ResponseEntity<Object> afficher(Long id);
    TypeModeleMessage afficherSelonId(Long id);
    ResponseEntity<Object> creer(TypeModeleMessageDto TypeModeleMessageDto);
    ResponseEntity<Object> modifier(TypeModeleMessageDto TypeModeleMessageDto);
    ResponseEntity<Object> supprimer(Long id);
}
