package com.ged.service.crm;

import com.ged.dto.crm.IndicateurDto;
import com.ged.entity.crm.Indicateur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IndicateurService {
    Page<IndicateurDto> afficherIndicateurs(int page, int size);
    List<IndicateurDto> afficherIndicateurs();
    Indicateur afficherIndicateurSelonId(long idIndicateur);
    IndicateurDto afficherIndicateur(long idIndicateur);
    IndicateurDto rechercherIndicateurParLibelle(String libelle);
    IndicateurDto creerIndicateur(IndicateurDto indicateurDto);
    IndicateurDto modifierIndicateur(IndicateurDto indicateurDto);
    void supprimerIndicateur(long idIndicateur);

}
