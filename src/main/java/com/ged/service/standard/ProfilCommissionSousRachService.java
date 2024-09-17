package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.ProfilCommissionSousRachDto;
import com.ged.entity.standard.ProfilCommissionSousRach;
import com.ged.entity.standard.CleProfilCommissionSousRach;
import org.springframework.http.ResponseEntity;

public interface ProfilCommissionSousRachService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm);
    ResponseEntity<Object> afficherTous();
    ProfilCommissionSousRach afficherSelonId(CleProfilCommissionSousRach idProfilCommissionSousRach);
    ResponseEntity<Object> afficherSelonTypeCommissionOpcvm(String typeCommission,Long idOpcvm);
    ResponseEntity<Object> afficher(CleProfilCommissionSousRach idProfilCommissionSousRach);
    ResponseEntity<Object> creer(ProfilCommissionSousRachDto ProfilCommissionSousRachDto);
    ResponseEntity<Object> modifier(ProfilCommissionSousRachDto ProfilCommissionSousRachDto);
    ResponseEntity<Object> supprimer(CleProfilCommissionSousRach idProfilCommissionSousRach);
}
