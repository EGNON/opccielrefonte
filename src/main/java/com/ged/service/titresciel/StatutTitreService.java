package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.StatutTitreDto;
import com.ged.entity.titresciel.CleStatutTitre;
import com.ged.entity.titresciel.StatutTitre;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface StatutTitreService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<StatutTitreDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    StatutTitre afficherSelonId(CleStatutTitre id);
    ResponseEntity<Object> afficher(CleStatutTitre id);
    ResponseEntity<Object> creer(StatutTitreDto StatutTitreDto);
    ResponseEntity<Object> modifier(StatutTitreDto StatutTitreDto);
    ResponseEntity<Object> supprimer(CleStatutTitre id);
}
