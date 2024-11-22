package com.ged.service.lab;


import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.comptabilite.Operation2Dto;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OperationService {
    List<Operation2Dto> afficherDepotSuperieurADixMillionsSurAnnee(long codeExercice) ;
    List<Operation2Dto> afficherOperationConstituantDeNouvelleRelation(long annee) ;
    List<Operation2Dto> afficherTransactionConditionInhabituel(long annee) ;
    List<Operation2Dto> afficherTransactionConditionNormale(long annee) ;
    List<Operation2Dto> afficherOperationSuperieurACinqMillions(long codeExercice) ;
    List<Operation2Dto> afficherDepotSurAnnee(long codeExercice);
    ResponseEntity<Object> creer(OperationDto operationDto);
    ResponseEntity<Object> modifier(OperationDto operationDto);
    ResponseEntity<Object> supprimer(Long idOperation);
}
