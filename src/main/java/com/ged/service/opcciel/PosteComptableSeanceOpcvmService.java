package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.PosteComptableSeanceOpcvmDto;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.entity.opcciel.comptabilite.ClePosteComptableSeanceOpcvm;
import com.ged.entity.opcciel.comptabilite.PosteComptableSeanceOpcvm;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PosteComptableSeanceOpcvmService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<PosteComptableSeanceOpcvmDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object>  afficherTous();
    PosteComptableSeanceOpcvm afficherSelonId(ClePosteComptableSeanceOpcvm clePosteComptableSeanceOpcvm);
    ResponseEntity<Object>  afficher(ClePosteComptableSeanceOpcvm clePosteComptableSeanceOpcvm);
    ResponseEntity<Object>  afficherSelonCodePosteComptableSeanceOpcvm(String codePosteComptableSeanceOpcvm);
    ResponseEntity<Object>  valoriser(DifferenceEstimationRequest posteComptableSeanceOpcvm);
    ResponseEntity<Object>  creer(List<PosteComptableSeanceOpcvmDto> PosteComptableSeanceOpcvmDto);
    ResponseEntity<Object>  modifier(PosteComptableSeanceOpcvmDto PosteComptableSeanceOpcvmDto);
    ResponseEntity<Object>  supprimer(ClePosteComptableSeanceOpcvm clePosteComptableSeanceOpcvm);
}
