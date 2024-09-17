package com.ged.service.standard;

import com.ged.dto.standard.CommuneDto;
import com.ged.entity.standard.Commune;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommuneService {
    Page<CommuneDto> afficherCommunes(int page, int size);
    List<CommuneDto> afficherCommunesListe();
    Commune afficherCommuneSelonId(long idCommune);
    CommuneDto creerCommune(CommuneDto communeDto);
    CommuneDto modifierCommune(CommuneDto communeDto);
    void supprimerCommune(Long id);
}
