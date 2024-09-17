package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.NatureTcnDto;
import com.ged.entity.titresciel.NatureTcn;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface NatureTcnService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<NatureTcnDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    NatureTcn afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(NatureTcnDto NatureTcnDto);
    ResponseEntity<Object> modifier(NatureTcnDto NatureTcnDto);
    ResponseEntity<Object> supprimer(Long id);
}
