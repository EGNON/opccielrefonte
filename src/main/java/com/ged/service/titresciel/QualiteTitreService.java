package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.QualiteTitreDto;
import com.ged.entity.titresciel.QualiteTitre;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface QualiteTitreService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<QualiteTitreDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    QualiteTitre afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(QualiteTitreDto QualiteTitreDto);
    ResponseEntity<Object> modifier(QualiteTitreDto QualiteTitreDto);
    ResponseEntity<Object> supprimer(Long id);
}
