package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeIbDto;
import com.ged.entity.opcciel.comptabilite.TypeIb;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeIbService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeIbDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeIb afficherSelonId(String codeTypeIb);
    ResponseEntity<Object> afficher(String codeTypeIb);
    ResponseEntity<Object> creer(TypeIbDto TypeIbDto);
    ResponseEntity<Object> modifier(TypeIbDto TypeIbDto);
    ResponseEntity<Object> supprimer(String codeTypeIb);
}
