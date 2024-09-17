package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.TarificationOrdinaireDto;
import com.ged.entity.standard.TarificationOrdinaire;
import org.springframework.http.ResponseEntity;

public interface TarificationOrdinaireService {
    ResponseEntity<Object> afficherTousSGI(DatatableParameters parameters,Long idOpcvm);
    ResponseEntity<Object> afficherTousDepositaire(DatatableParameters parameters,Long idOpcvm);
    ResponseEntity<Object> afficherTousPlace(DatatableParameters parameters,Long idOpcvm);
    ResponseEntity<Object> afficherTous();
    TarificationOrdinaire afficherSelonId(Long idTarificationOrdinaire);
    ResponseEntity<Object> afficher(Long idTarificationOrdinaire);
    ResponseEntity<Object> afficherRegistraireSelonId(Long idTarificationOrdinaire);
    ResponseEntity<Object> afficherDepositaireSelonId(Long idTarificationOrdinaire);
    ResponseEntity<Object> afficherPlaceSelonId(Long idTarificationOrdinaire);
    ResponseEntity<Object> creer(TarificationOrdinaireDto TarificationOrdinaireDto);
    ResponseEntity<Object> modifier(TarificationOrdinaireDto TarificationOrdinaireDto,String qualite);
    ResponseEntity<Object> supprimer(Long idTarificationOrdinaire);
}
