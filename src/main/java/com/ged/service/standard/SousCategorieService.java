package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.SousCategorieDto;
import com.ged.entity.standard.SousCategorie;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SousCategorieService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    List<SousCategorieDto> afficherListe();
    ResponseEntity<Object> afficherSousCategories(int page, int size);
    ResponseEntity<Object> afficherSousCategorie(Long id);
    SousCategorie afficherSousCategorieSelonId(Long idSousCategorie);
    ResponseEntity<Object> creerSousCategorie(SousCategorieDto SousCategorieDto);
    ResponseEntity<Object> modifierSousCategorie(SousCategorieDto SousCategorieDto);
    ResponseEntity<Object> supprimerSousCategorie(Long id);
}
