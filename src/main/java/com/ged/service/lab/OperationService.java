package com.ged.service.lab;


import com.ged.dto.opcciel.comptabilite.Operation2Dto;

import java.util.List;

public interface OperationService {
    List<Operation2Dto> afficherDepotSuperieurADixMillionsSurAnnee(long codeExercice) ;
    List<Operation2Dto> afficherOperationConstituantDeNouvelleRelation(long annee) ;
    List<Operation2Dto> afficherTransactionConditionInhabituel(long annee) ;
    List<Operation2Dto> afficherTransactionConditionNormale(long annee) ;
    List<Operation2Dto> afficherOperationSuperieurACinqMillions(long codeExercice) ;
    List<Operation2Dto> afficherDepotSurAnnee(long codeExercice) ;
}
