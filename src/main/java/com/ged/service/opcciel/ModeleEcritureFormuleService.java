package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureFormuleDto;
import com.ged.entity.opcciel.comptabilite.CleModeleEcritureFormule;
import com.ged.entity.opcciel.comptabilite.ModeleEcritureFormule;
import org.springframework.http.ResponseEntity;

public interface ModeleEcritureFormuleService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ModeleEcritureFormule afficherSelonId(CleModeleEcritureFormule idModeleEcritureFormule);
    ResponseEntity<Object> afficher(CleModeleEcritureFormule idModeleEcritureFormule);
    ResponseEntity<Object> afficherSelonModeleEcriture(String codeModeleEcriture);
    ResponseEntity<Object> creer(ModeleEcritureFormuleDto ModeleEcritureFormuleDto);
    ResponseEntity<Object> modifier(ModeleEcritureFormuleDto ModeleEcritureFormuleDto);
    ResponseEntity<Object> supprimer(CleModeleEcritureFormule idModeleEcritureFormule);
    ResponseEntity<Object> supprimerSelonModeleEcriture(String codeModeleEcriture);
}
