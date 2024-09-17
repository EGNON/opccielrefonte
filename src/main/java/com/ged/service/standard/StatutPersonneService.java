package com.ged.service.standard;

import com.ged.dto.standard.StatutPersonneDto;
import com.ged.entity.standard.CleStatutPersonne;
import com.ged.entity.standard.StatutPersonne;
import org.springframework.data.domain.Page;

public interface StatutPersonneService {
    Page<StatutPersonneDto> afficherStatutPersonnes(int page, int size);
    StatutPersonneDto afficherStatutSelonQualite(String qualite);
    StatutPersonne afficherStatutPersonneSelonId(CleStatutPersonne idStatutPersonne);
    StatutPersonneDto creerStatutPersonne(StatutPersonneDto statutPersonneDto);
    StatutPersonneDto modifierStatutPersonne(StatutPersonneDto statutPersonneDto);
    void supprimerStatutPersonne(CleStatutPersonne idStatutPersonne);
}
