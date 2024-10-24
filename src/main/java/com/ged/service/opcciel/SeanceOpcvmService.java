package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.entity.opcciel.CleSeanceOpcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface SeanceOpcvmService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<SeanceOpcvmDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    SeanceOpcvm afficherSelonId(CleSeanceOpcvm id);
    SeanceOpcvm afficherSeanceEnCours(long idOpcvm);
    ResponseEntity<Object> afficher(long idOpcvm);
    ResponseEntity<Object> afficher(CleSeanceOpcvm id);
    ResponseEntity<Object> creer(SeanceOpcvmDto seanceOpcvmDto);
    ResponseEntity<Object> modifier(SeanceOpcvmDto seanceOpcvmDto);
    ResponseEntity<Object> supprimer(CleSeanceOpcvm id);
}
