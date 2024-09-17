package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.PlanDto;
import com.ged.entity.opcciel.comptabilite.Plan;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface PlanService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<PlanDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object>  afficherTous();
    Plan afficherSelonId(String codePlan);
    ResponseEntity<Object>  afficher(String codePlan);
    ResponseEntity<Object>  creer(PlanDto PlanDto);
    ResponseEntity<Object>  modifier(PlanDto PlanDto);
    ResponseEntity<Object>  supprimer(String codePlan);
}
