package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.FormuleDto;
import com.ged.entity.opcciel.comptabilite.Formule;
import org.springframework.http.ResponseEntity;

public interface FormuleService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);

    ResponseEntity<Object> afficher(Long id);

    Formule afficherSelonId(Long id);
    ResponseEntity<Object> creer(FormuleDto formuleDto);
    ResponseEntity<Object> modifier(FormuleDto formuleDto);
    ResponseEntity<Object> supprimer(Long id);
}
