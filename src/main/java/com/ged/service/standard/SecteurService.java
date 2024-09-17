package com.ged.service.standard;

import com.ged.dto.standard.SecteurDto;
import com.ged.entity.standard.Secteur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SecteurService {
    Page<SecteurDto> afficherSecteurs(int page, int size);
    List<SecteurDto> afficherSecteurs();
    SecteurDto afficherSecteur(Long id);
    SecteurDto rechercherSecteurParLibelle(String libelle);
    Secteur afficherSecteurSelonId(long idSecteur);
    SecteurDto creerSecteur(SecteurDto secteurDto);
    SecteurDto modifierSecteur(SecteurDto secteurDto);
    void supprimerSecteur(Long idSecteur);
}
