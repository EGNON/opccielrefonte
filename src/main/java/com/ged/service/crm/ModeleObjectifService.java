package com.ged.service.crm;

import com.ged.dto.crm.ModeleObjectifDto;
import com.ged.entity.crm.ModeleObjectif;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ModeleObjectifService {
    Page<ModeleObjectifDto> afficherParPage(int page, int size);
    List<ModeleObjectifDto> afficherTous();
    ModeleObjectif afficherSelonId(Long id);
    ModeleObjectifDto afficher(Long id);
    ModeleObjectifDto creer(ModeleObjectifDto modeleObjectifDto);
    ModeleObjectifDto modifier(ModeleObjectifDto modeleObjectifDto);
    void supprimer(Long id);
}
