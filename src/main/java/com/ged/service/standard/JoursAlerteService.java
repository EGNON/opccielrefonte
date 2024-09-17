package com.ged.service.standard;

import com.ged.dto.standard.JoursAlerteDto;
import com.ged.entity.standard.CleJoursAlerte;
import com.ged.entity.standard.JoursAlerte;
import org.springframework.data.domain.Page;

import java.util.List;

public interface JoursAlerteService {
    Page<JoursAlerteDto> afficherJoursAlertes(int page, int size);
    JoursAlerte afficherJoursAlerteSelonId(CleJoursAlerte idJoursAlerte);
    List<JoursAlerteDto> afficherJoursAlerteSelonAlerte(long idAlerte);
    JoursAlerteDto creerJoursAlerte(JoursAlerteDto JoursAlerteDto);
    JoursAlerteDto modifierJoursAlerte(JoursAlerteDto JoursAlerteDto);
    void supprimerJoursAlerte(CleJoursAlerte idJoursAlerte);
    void supprimerJoursAlerteSelonAlerte(long idAlerte);
}
