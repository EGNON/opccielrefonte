package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.ChargeDto;
import com.ged.entity.opcciel.Charge;
import org.springframework.http.ResponseEntity;

public interface ChargeService {
    ResponseEntity<?> afficherListeCharges(Long idOpcvm);
    ResponseEntity<Object> afficherTous(DatatableParameters parameters, Long idOpcvm);
    ResponseEntity<Object> afficherTous();
    Charge afficherSelonId(Long idOpcvm,String codeCharge);
    ResponseEntity<Object>  afficherChargeSelonId(Long idOpcvm,String codeCharge);
    ResponseEntity<Object> afficher(Long idOpcvm,String codeCharge);
    ResponseEntity<Object> creer(ChargeDto chargeDto);
    ResponseEntity<Object> modifier(ChargeDto chargeDto);
    ResponseEntity<Object> supprimer(Long idCharge);
}
