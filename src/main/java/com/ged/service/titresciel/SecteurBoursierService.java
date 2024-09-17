package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.SecteurBoursierDto;
import com.ged.entity.titresciel.SecteurBoursier;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface SecteurBoursierService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<SecteurBoursierDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    SecteurBoursier afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(SecteurBoursierDto SecteurBoursierDto);
    ResponseEntity<Object> modifier(SecteurBoursierDto SecteurBoursierDto);
    ResponseEntity<Object> supprimer(Long id);
}
