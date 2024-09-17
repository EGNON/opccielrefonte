package com.ged.service.standard;

import com.ged.dto.standard.NbreJoursAlerteDto;
import com.ged.entity.standard.CleNbreJoursAlerte;
import com.ged.entity.standard.NbreJoursAlerte;
import org.springframework.data.domain.Page;

import java.util.List;

public interface NbreJoursAlerteService {
    Page<NbreJoursAlerteDto> afficherNbreJoursAlertes(int page, int size);
    NbreJoursAlerte afficherNbreJoursAlerteSelonId(CleNbreJoursAlerte idNbreJoursAlerte);
    List<NbreJoursAlerteDto> afficherNbreJoursAlerteSelonAlerte(long idAlerte);
    NbreJoursAlerteDto creerNbreJoursAlerte(NbreJoursAlerteDto nbreJoursAlerte);
    NbreJoursAlerteDto modifierNbreJoursAlerte(NbreJoursAlerteDto nbreJoursAlerte);
    void supprimerNbreJoursAlerte(CleNbreJoursAlerte idNbreJoursAlerte);
    void supprimerNbreJoursAlerteSelonAlerte(long idAlerte);
}
