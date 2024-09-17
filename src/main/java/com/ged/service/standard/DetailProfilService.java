package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.DetailProfilDto;
import com.ged.entity.standard.CleDetailProfil;
import com.ged.entity.standard.DetailProfil;
import org.springframework.http.ResponseEntity;

public interface DetailProfilService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm);
    ResponseEntity<Object> afficherTous();
    DetailProfil afficherSelonId(CleDetailProfil idDetailProfil);
    ResponseEntity<Object> afficher(CleDetailProfil idDetailProfil);
    ResponseEntity<Object> afficher(String codeProfil,Long idOpcvm);
    ResponseEntity<Object> supprimer(String codeProfil,Long idOpcvm);
    ResponseEntity<Object> creer(DetailProfilDto DetailProfilDto);
    ResponseEntity<Object> modifier(DetailProfilDto DetailProfilDto);
    ResponseEntity<Object> supprimer(CleDetailProfil idDetailProfil);
}
