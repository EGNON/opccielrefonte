package com.ged.service.standard;

import com.ged.dto.standard.ArrondissementDto;
import com.ged.entity.standard.Arrondissement;
import org.springframework.data.domain.Page;

public interface ArrondissementService {
    Page<ArrondissementDto> afficherArrondissements(int page, int size);
    Arrondissement afficherArrondissementSelonId(long idArrondissement);
    ArrondissementDto creerArrondissement(ArrondissementDto arrondissementDto);
    ArrondissementDto modifierArrondissement(ArrondissementDto arrondissementDto);
    void supprimerArrondissement(Long idArrondissement);
}
