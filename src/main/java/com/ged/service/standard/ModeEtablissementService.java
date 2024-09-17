package com.ged.service.standard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dto.standard.ModeEtablissementDto;
import com.ged.entity.standard.ModeEtablissement;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ModeEtablissementService {

    Page<ModeEtablissementDto> afficherModeEtablissements(int page, int size);
    List<ModeEtablissementDto> afficherModeEtablissements();
    ModeEtablissementDto afficher(Long id);
    boolean existByLibelle(String libelle);
    ModeEtablissement afficherModeEtablissementSelonId(Long idCategorie);
    ModeEtablissementDto creerModeEtablissement(ModeEtablissementDto ModeEtablissementDto) throws JsonProcessingException;
    ModeEtablissementDto modifierModeEtablissement(ModeEtablissementDto ModeEtablissementDto);

    void supprimerModeEtablissement(Long id);
}
