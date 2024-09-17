package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.FormeJuridiqueDto;
import com.ged.entity.standard.FormeJuridique;
import org.springframework.http.ResponseEntity;

public interface FormeJuridiqueService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficherFormeJuridiques(int page, int size);

    ResponseEntity<Object> afficherFormeJuridique(String id);

    FormeJuridique afficherFormeJuridiqueSelonId(String codeFormeJuridique);

    ResponseEntity<Object> rechercherFormeJuridiqueParLibelle(String libelle);
    ResponseEntity<Object> creerFormeJuridique(FormeJuridiqueDto formeJuridiqueDto);
    ResponseEntity<Object> modifierFormeJuridique(FormeJuridiqueDto formeJuridiqueDto);
    ResponseEntity<Object> supprimerFormeJuridique(String code);
}
