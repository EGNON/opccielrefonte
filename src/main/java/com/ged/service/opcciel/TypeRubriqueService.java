package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeRubriqueDto;
import com.ged.entity.opcciel.comptabilite.TypeRubrique;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeRubriqueService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeRubriqueDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeRubrique afficherSelonId(String codeTypeRubrique);
    ResponseEntity<Object> afficher(String codeTypeRubrique);
    ResponseEntity<Object> creer(TypeRubriqueDto TypeRubriqueDto);
    ResponseEntity<Object> modifier(TypeRubriqueDto TypeRubriqueDto);
    ResponseEntity<Object> supprimer(String codeTypeRubrique);
}
