package com.ged.service.standard;

import com.ged.dto.standard.DiffusionAlerteDto;
import com.ged.entity.standard.CleDiffusionAlerte;
import com.ged.entity.standard.DiffusionAlerte;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DiffusionAlerteService {
    void updateScheduler();
    List<DiffusionAlerteDto> afficherTous();
    Page<DiffusionAlerteDto> afficherDiffusionAlertes(int page, int size);
    DiffusionAlerte afficherDiffusionAlerteSelonId(CleDiffusionAlerte idDiffusionAlerte);
    List<DiffusionAlerteDto> afficherDiffusionAlerteSelonAlerte(long idAlerte);
    DiffusionAlerteDto creerDiffusionAlerte(DiffusionAlerteDto diffusionAlerteDto);
    DiffusionAlerteDto modifierDiffusionAlerte(DiffusionAlerteDto diffusionAlerteDto);
    void supprimerDiffusionAlerte(CleDiffusionAlerte idDiffusionAlerte);
    void supprimerDiffusionAlerteSelonAlerte(long idAlerte);
}
