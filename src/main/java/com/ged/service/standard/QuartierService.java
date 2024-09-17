package com.ged.service.standard;

import com.ged.dto.standard.QuartierDto;
import com.ged.entity.standard.Quartier;
import com.ged.projection.QuartierProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface QuartierService {
    Page<QuartierDto> afficherQuartiers(int page, int size);
    List<QuartierProjection> afficherQuartierSelonVille(long idVille);
    List<QuartierProjection> afficherQuartierSelonCommune(String libelle);
    Quartier afficherQuartierSelonId(long idQuartier);
    QuartierDto afficherQuartier(long idQuartier);
    QuartierDto creerQuartier(QuartierDto quartierDto);
    QuartierDto modifierQuartier(QuartierDto quartierDto);
    void supprimerQuartier(long idQuartier);
}
