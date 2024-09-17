package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeEmetteurDto;
import com.ged.entity.titresciel.TypeEmetteur;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeEmetteurService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeEmetteurDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeEmetteur afficherSelonId(String codeTypeEmetteur);
    ResponseEntity<Object> afficher(String codeTypeEmetteur);
    ResponseEntity<Object> creer(TypeEmetteurDto TypeEmetteurDto);
    ResponseEntity<Object> modifier(TypeEmetteurDto TypeEmetteurDto);
    ResponseEntity<Object> supprimer(String codeTypeEmetteur);
}
