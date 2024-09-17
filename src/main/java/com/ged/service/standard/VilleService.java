package com.ged.service.standard;

import com.ged.dto.standard.VilleDto;
import com.ged.entity.standard.Ville;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VilleService {
    Page<VilleDto> afficherVilles(int page, int size);
    List<VilleDto> afficherVilleListe();
    Ville afficherVilleSelonId(long idVille);
    VilleDto afficherVille(long idVille);
    VilleDto creerVille(VilleDto villeDto);
    VilleDto modifierVille(VilleDto villeDto);
    void supprimerVille(long idVille);
}
