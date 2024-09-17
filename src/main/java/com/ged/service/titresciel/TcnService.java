package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TcnDto;
import com.ged.entity.titresciel.Tcn;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TcnService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TcnDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Tcn afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(TcnDto TcnDto);
    ResponseEntity<Object> modifier(TcnDto TcnDto);
    ResponseEntity<Object> supprimer(Long id);
}
