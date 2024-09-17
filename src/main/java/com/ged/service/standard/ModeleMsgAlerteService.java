package com.ged.service.standard;

import com.ged.dto.standard.ModeleMsgAlerteDto;
import com.ged.entity.standard.ModeleMsgAlerte;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ModeleMsgAlerteService {
    Page<ModeleMsgAlerteDto> afficherModeleMsgAlertes(int page, int size);
    ModeleMsgAlerte afficherModeleMsgAlerteSelonId(long idModeleMsgAlerte);
    ModeleMsgAlerteDto afficherModeleMsgAlerteSelonTypeModeleEtDefaut(String libelle);
    List<ModeleMsgAlerteDto> afficherModeleMsgAlerteSelonTypeModele(String libelle);
    ModeleMsgAlerteDto afficherModeleMsgAlerte(long idModeleMsgAlerte);
    ModeleMsgAlerteDto creerModeleMsgAlerte(ModeleMsgAlerteDto modeleMsgAlerteDto);
    ModeleMsgAlerteDto modifierModeleMsgAlerte(ModeleMsgAlerteDto modeleMsgAlerteDto);
    void supprimerModeleMsgAlerte(long idModeleMsgAlerte);
    int modifier(Long idModele);
}
