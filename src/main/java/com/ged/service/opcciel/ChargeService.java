package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.ChargeDto;
import com.ged.entity.opcciel.Charge;
import org.springframework.http.ResponseEntity;

public interface ChargeService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm);
    ResponseEntity<Object> afficherTous();
    Charge afficherSelonId(Long idCharge);
    ResponseEntity<Object>  afficherChargeSelonId(Long idCharge);
    ResponseEntity<Object> afficher(Long idCharge);
    ResponseEntity<Object> creer(ChargeDto chargeDto);
    ResponseEntity<Object> modifier(ChargeDto chargeDto);
    ResponseEntity<Object> supprimer(Long idCharge);
}
