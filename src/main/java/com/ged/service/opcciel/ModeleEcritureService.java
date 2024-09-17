package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureDto;
import com.ged.entity.opcciel.comptabilite.ModeleEcriture;
import org.springframework.http.ResponseEntity;

public interface ModeleEcritureService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);

    ResponseEntity<Object> afficher(String code);
    ModeleEcriture afficherSelonId(String code);
    ResponseEntity<Object> creer(ModeleEcritureDto modeleEcritureDto);
    ResponseEntity<Object> modifier(ModeleEcritureDto modeleEcritureDto);
    ResponseEntity<Object> supprimer(String code);
}
