package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.dto.opcciel.OrdreSignataireDto;
import com.ged.entity.opcciel.CleOrdre;
import com.ged.entity.opcciel.Ordre;
import com.ged.entity.opcciel.OrdreSignataire;
import org.springframework.http.ResponseEntity;

public interface OrdreSignataireService {
//    ResponseEntity<Object> afficherTous(Long idOpcvm,DatatableParameters parameters);
    OrdreSignataire afficherSelonId(CleOrdre id);
    ResponseEntity<Object> afficher(CleOrdre id);
    ResponseEntity<Object> afficherTous(Long idOrdre);
    ResponseEntity<Object> creer(OrdreSignataireDto ordreSignataireDto);
    ResponseEntity<Object> creer(Long idOrdre,Long[]idPersonne);
//    ResponseEntity<Object> modifier(OrdreDto OrdreDto);
    ResponseEntity<Object> supprimer(Long idOrdre);
}
