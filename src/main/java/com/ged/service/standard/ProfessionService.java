package com.ged.service.standard;

import com.ged.dto.standard.ProfessionDto;
import com.ged.entity.standard.Profession;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProfessionService {
    Page<ProfessionDto> afficherProfessions(int page, int size);
    List<ProfessionDto> afficherProfessions();
    ProfessionDto afficher(Long id);
    ProfessionDto rechercherProfessionParLibelle(String libelle);
    Profession afficherProfessionSelonId(Long idProfession);
    ProfessionDto creerProfession(ProfessionDto professionDto);
    ProfessionDto modifierProfession(ProfessionDto professionDto);

    List<Object> createProfessionFromOppciel1();

    void supprimerProfession(Long idProf);
}
