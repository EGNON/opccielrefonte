package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.TypeFormuleDto;
import com.ged.entity.opcciel.comptabilite.TypeFormule;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TypeFormuleService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TypeFormuleDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TypeFormule afficherSelonId(String codeTypeFormule);
    ResponseEntity<Object> afficher(String codeTypeFormule);
    ResponseEntity<Object> creer(TypeFormuleDto TypeFormuleDto);
    ResponseEntity<Object> modifier(TypeFormuleDto TypeFormuleDto);
    ResponseEntity<Object> supprimer(String codeTypeFormule);
}
