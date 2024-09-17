package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ClassificationOPCDto;
import com.ged.entity.titresciel.ClassificationOPC;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClassificationService {
//    List<Object> createClassificationFromOppciel1();
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ClassificationOPC afficherSelonId(String codeClassification);
    ResponseEntity<Object> afficher(String codeClassification);
    ResponseEntity<Object> creer(ClassificationOPCDto classificationOPCDto);
    ResponseEntity<Object> modifier(ClassificationOPCDto classificationOPCDto);
    ResponseEntity<Object> supprimer(String codeClassification);
}
