package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.DetailModeleDto;
import com.ged.entity.opcciel.comptabilite.CleDetailModele;
import com.ged.entity.opcciel.comptabilite.DetailModele;
import org.springframework.http.ResponseEntity;

public interface DetailModeleService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    DetailModele afficherSelonId(CleDetailModele idDetailModele);
    ResponseEntity<Object> afficherSelonModeleEcriture(String codeModeleEcriture);
    ResponseEntity<Object> afficher(CleDetailModele idDetailModele);
    ResponseEntity<Object> creer(DetailModeleDto DetailModeleDto);
    ResponseEntity<Object> creer(DetailModeleDto[] DetailModeleDto);
    ResponseEntity<Object> modifier(DetailModeleDto DetailModeleDto);
    ResponseEntity<Object> supprimer(CleDetailModele idDetailModele);
    void supprimerSelonModeleEcriture(String codeModeleEcriture);
}
