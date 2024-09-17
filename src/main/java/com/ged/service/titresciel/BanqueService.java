package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.BanqueDto;
import com.ged.entity.titresciel.Banque;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface BanqueService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<BanqueDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Banque afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(BanqueDto BanqueDto);
    ResponseEntity<Object> modifier(BanqueDto BanqueDto);
    ResponseEntity<Object> supprimer(Long id);
}
