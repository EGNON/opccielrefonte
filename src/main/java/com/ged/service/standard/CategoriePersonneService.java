package com.ged.service.standard;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dto.standard.CategoriePersonneDto;
import com.ged.entity.standard.CategoriePersonne;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoriePersonneService {
    Object[] get();

    Page<CategoriePersonneDto> afficherCategoriePersonnes(int page, int size);
    List<CategoriePersonneDto> afficherCategoriePersonnes();
    CategoriePersonneDto afficher(Long id);
    CategoriePersonne afficherCategoriePersonneSelonId(Long idCategorie);
    CategoriePersonneDto rechercherCategoriePersonneParLibelle(String libelle);
    CategoriePersonneDto creerCategoriePersonne(CategoriePersonneDto categoriePersonneDto) throws JsonProcessingException;
    CategoriePersonneDto modifierCategoriePersonne(CategoriePersonneDto categoriePersonneDto);

    List<Object> createCategorieFromOppciel1();

    void supprimerCategoriePersonne(Long id);
}
