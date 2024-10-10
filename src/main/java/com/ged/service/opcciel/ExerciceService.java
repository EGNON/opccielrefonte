package com.ged.service.opcciel;


import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import org.springframework.http.ResponseEntity;

import java.sql.SQLException;
import java.util.List;

public interface ExerciceService {
    List<ExerciceDto> afficherTous() throws SQLException;

    ResponseEntity<Object> exerciceCourant(Long idOpcvm);
}
