package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.CompteComptableDto;
import com.ged.entity.opcciel.comptabilite.CleCompteComptable;
import com.ged.entity.opcciel.comptabilite.CompteComptable;
import org.springframework.http.ResponseEntity;

public interface CompteComptableService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficherSelonNumCompteComptable(String numCompteComptable);
    ResponseEntity<Object> afficherSelonPlan(DatatableParameters parameters,String codePlan);
    ResponseEntity<Object> afficherSelonPlan(String codePlan);
    ResponseEntity<Object> afficherSelonPlanEtEstMvt(String codePlan,boolean estMvt);
    CompteComptable afficherSelonId(CleCompteComptable idCompteComptable);
    ResponseEntity<Object> afficher(CleCompteComptable idCompteComptable);
    ResponseEntity<Object> afficherTousCompte();
    ResponseEntity<Object> creer(CompteComptableDto compteComptableDto);
    ResponseEntity<Object> modifier(CompteComptableDto compteComptableDto);
    ResponseEntity<Object> supprimer(CleCompteComptable idCompteComptable);
}
