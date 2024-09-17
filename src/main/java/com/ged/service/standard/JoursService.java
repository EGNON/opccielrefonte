package com.ged.service.standard;

import com.ged.dto.standard.JoursDto;
import com.ged.entity.standard.Jours;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JoursService {
    Boolean existByLibelle(String libelle);
    Page<JoursDto> afficherJours(int page, int size);
    List<JoursDto> afficherTous();
    Jours afficherJoursSelonId(long idJours);
    JoursDto creerJours(JoursDto JoursDto);
    JoursDto modifierJours(JoursDto JoursDto);
    void supprimerJours(long idJours);
}
