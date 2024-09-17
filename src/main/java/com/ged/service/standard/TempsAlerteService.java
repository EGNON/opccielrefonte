package com.ged.service.standard;

import com.ged.dto.standard.TempsAlerteDto;
import com.ged.entity.standard.CleTempsAlerte;
import com.ged.entity.standard.TempsAlerte;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TempsAlerteService {
    Page<TempsAlerteDto> afficherTempsAlertes(int page, int size);
    TempsAlerte afficherTempsAlerteSelonId(CleTempsAlerte idTempsAlerte);
    List<TempsAlerteDto> afficherTempsAlerteSelonAlerte(long idAlerte);
    TempsAlerteDto creerTempsAlerte(TempsAlerteDto tempsAlerte);
    TempsAlerteDto modifierTempsAlerte(TempsAlerteDto tempsAlerte);
    void supprimerTempsAlerte(CleTempsAlerte idTempsAlerte);
    void supprimerTempsAlerteSelonAlerte(long idAlerte);
}
