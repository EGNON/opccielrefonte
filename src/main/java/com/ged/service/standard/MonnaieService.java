package com.ged.service.standard;

import com.ged.dto.standard.MonnaieDto;
import com.ged.entity.standard.Monnaie;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MonnaieService {
    Page<MonnaieDto> afficherMonnaies(int page, int size);
    List<MonnaieDto> afficherMonnaies();
    MonnaieDto afficherMonnaie(String codeMonnaie);
    MonnaieDto rechercherMonnaieParCode(String codeMonnaie);
    Monnaie afficherMonnaieSelonId(String codeMonnaie);
    MonnaieDto creerMonnaie(MonnaieDto monnaieDto);
    MonnaieDto modifierMonnaie(MonnaieDto monnaieDto);
    void supprimerMonnaie(String codeMonnaie);
}
