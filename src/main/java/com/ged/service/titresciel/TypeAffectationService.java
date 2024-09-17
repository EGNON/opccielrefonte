package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeAffectationTitreDto;
import com.ged.entity.titresciel.TypeAffectationTitre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TypeAffectationService {
    ResponseEntity<Object> afficherTous();

//    List<Object> createTypeAffectationFromTitresciel1();
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    TypeAffectationTitre afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(TypeAffectationTitreDto typeAffectationTitreDto);
    ResponseEntity<Object> modifier(TypeAffectationTitreDto typeAffectationTitreDto);
    ResponseEntity<Object> supprimer(Long id);
}
