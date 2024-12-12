package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.ModeleEcritureDto;
import com.ged.entity.opcciel.comptabilite.ModeleEcriture;
import org.springframework.http.ResponseEntity;

public interface MiseEnAffectationService {

    ResponseEntity<Object> verifierMiseEnAffectationEnAttente(Long idOpcvm);
}
