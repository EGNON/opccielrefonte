package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.PosteComptableDto;
import com.ged.entity.opcciel.comptabilite.ClePosteComptable;
import com.ged.entity.opcciel.comptabilite.PosteComptable;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface PosteComptableService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<PosteComptableDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object>  afficherTous();
    PosteComptable afficherSelonId(ClePosteComptable clePosteComptable);
    ResponseEntity<Object>  afficher(ClePosteComptable clePosteComptable);
    ResponseEntity<Object>  afficherSelonCodePosteCOmptable(String codePosteComptable);
    ResponseEntity<Object>  creer(PosteComptableDto PosteComptableDto);
    ResponseEntity<Object>  modifier(PosteComptableDto PosteComptableDto);
    ResponseEntity<Object>  supprimer(ClePosteComptable clePosteComptable);
}
