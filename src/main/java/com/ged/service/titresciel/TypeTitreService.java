package com.ged.service.titresciel;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TypeTitreDto;
import com.ged.dto.titresciel.TypeTitreDto;
import com.ged.entity.titresciel.TypeTitre;
import com.ged.entity.titresciel.TypeTitre;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TypeTitreService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeTitreDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeTitre afficherSelonId(String codeTypeTitre);
    ResponseEntity<Object> afficher(String codeTypeTitre);
    ResponseEntity<Object> creer(TypeTitreDto TypeTitreDto);
    ResponseEntity<Object> modifier(TypeTitreDto TypeTitreDto);
    ResponseEntity<Object> supprimer(String codeTypeTitre);
}
