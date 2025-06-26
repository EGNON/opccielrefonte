package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OperationEvenementSurValeurDto;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.projection.OperationEvenementSurValeurProjection;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

public interface OperationEvenementSurValeurService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters,Long idOpcvm);
    OperationEvenementSurValeurProjection afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> afficherTitre(Long idOpcvm,LocalDateTime dateEstimation,String typeEvenement);
    ResponseEntity<Object> afficherTous(Long idOpcvm);
    ResponseEntity<Object> creer(OperationEvenementSurValeurDto operationEvenementSurValeurDto);
    ResponseEntity<Object> creer(OperationEvenementSurValeurDto[] operationEvenementSurValeurDto);
    ResponseEntity<Object> annuler(OperationEvenementSurValeurDto operationDto);
    ResponseEntity<Object> valeurOuQte(OperationEvenementSurValeurDto operationEvenementSurValeurDto);
    ResponseEntity<Object> modifier(OperationEvenementSurValeurDto operationEvenementSurValeurDto);
    ResponseEntity<Object> supprimer(String userLogin,
                                    Long idAvis);
}
