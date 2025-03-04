package com.ged.service.lab;


import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.comptabilite.Operation2Dto;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface OperationService {
    List<Operation2Dto> afficherDepotSuperieurADixMillionsSurAnnee(long codeExercice) ;
    List<Operation2Dto> afficherDepotSuperieurADixMillionsSurAnneeEtat(long codeExercice, HttpServletResponse response) throws IOException, JRException;
    List<Operation2Dto> afficherOperationConstituantDeNouvelleRelation(long annee) ;
    List<Operation2Dto> afficherOperationConstituantDeNouvelleRelationEtat(long annee, HttpServletResponse response) throws IOException, JRException;
    List<Operation2Dto> afficherTransactionConditionInhabituel(long annee) ;
    List<Operation2Dto> afficherTransactionConditionInhabituelEtat(long annee, HttpServletResponse response) throws IOException, JRException;
    List<Operation2Dto> afficherTransactionConditionNormale(long annee) ;
    List<Operation2Dto> afficherTransactionConditionNormaleEtat(long annee, HttpServletResponse response) throws IOException, JRException;
    List<Operation2Dto> afficherOperationSuperieurACinqMillions(long codeExercice) ;
    List<Operation2Dto> afficherOperationSuperieurACinqMillionsEtat(long codeExercice, HttpServletResponse response) throws IOException, JRException;
    List<Operation2Dto> afficherDepotSurAnnee(long codeExercice);
    List<Operation2Dto> afficherDepotSurAnneeEtat(long codeExercice, HttpServletResponse response) throws IOException, JRException;
    ResponseEntity<Object> creer(OperationDto operationDto);
    ResponseEntity<Object> modifier(OperationDto operationDto);
    ResponseEntity<Object> supprimer(Long idOperation);
}
