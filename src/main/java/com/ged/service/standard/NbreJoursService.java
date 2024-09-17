package com.ged.service.standard;

import com.ged.dto.standard.NbreJoursDto;
import com.ged.entity.standard.NbreJours;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NbreJoursService {
    Boolean existById(Long id);
    Page<NbreJoursDto> afficherNbreJours(int page, int size);
    List<NbreJoursDto> afficherTous();
    NbreJours afficherNbreJoursSelonId(long idNbreJours);
    NbreJoursDto creerNbreJours(NbreJoursDto nbreJours);
    NbreJoursDto modifierNbreJours(NbreJoursDto nbreJours);
    void supprimerNbreJours(long idNbreJours);
}
