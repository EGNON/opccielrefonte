package com.ged.service.titresciel;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ClasseTitreDto;
import com.ged.dto.titresciel.ClasseTitreDto;
import com.ged.entity.titresciel.ClasseTitre;
import com.ged.entity.titresciel.ClasseTitre;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClasseTitreService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<ClasseTitreDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    ClasseTitre afficherSelonId(String id);
    ResponseEntity<Object> afficher(String id);
    ResponseEntity<Object> creer(ClasseTitreDto ClasseTitreDto);
    ResponseEntity<Object> modifier(ClasseTitreDto ClasseTitreDto);
    ResponseEntity<Object> supprimer(String id);
}
