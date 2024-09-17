package com.ged.service.standard;

import com.ged.dto.standard.AlerteDto;
import com.ged.entity.standard.Alerte;
import org.springframework.data.domain.Page;

public interface AlerteService {
    Page<AlerteDto> afficherAlertes(int page, int size);
    Alerte afficherAlerteSelonId(Long idAlerte);
    AlerteDto afficherAlerte(Long idAlerte);
    AlerteDto creerAlerte(AlerteDto alerteDto);
    AlerteDto modifierAlerte(AlerteDto alerteDto);
    void supprimerAlerte(Long id);
}
