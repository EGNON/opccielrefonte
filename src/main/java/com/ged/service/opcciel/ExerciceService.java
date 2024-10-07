package com.ged.service.opcciel;


import com.ged.dto.opcciel.comptabilite.ExerciceDto;

import java.sql.SQLException;
import java.util.List;

public interface ExerciceService {
    List<ExerciceDto> afficherTous() throws SQLException;
}
