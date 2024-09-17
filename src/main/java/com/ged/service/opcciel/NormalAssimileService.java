
package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.NormalAssimileDto;
import com.ged.entity.standard.NormalAssimile;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface NormalAssimileService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<NormalAssimileDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    NormalAssimile afficherSelonId(String codeNormalAssimile);
    ResponseEntity<Object> afficher(String codeNormalAssimile);
    ResponseEntity<Object> creer(NormalAssimileDto NormalAssimileDto);
    ResponseEntity<Object> modifier(NormalAssimileDto NormalAssimileDto);
    ResponseEntity<Object> supprimer(String codeNormalAssimile);
}
