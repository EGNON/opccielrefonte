package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.PlanDto;
import com.ged.entity.opcciel.comptabilite.Plan;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface SeanceVLService {
    ResponseEntity<Object>  afficherTous(Long idOpcvm);
    ResponseEntity<Object> cloturerSeance(String userLogin, Long idOpcvm);
}
