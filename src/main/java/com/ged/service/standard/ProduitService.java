package com.ged.service.standard;

import com.ged.dto.standard.ProduitDto;
import com.ged.entity.standard.Produit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProduitService  {
    Page<ProduitDto> afficherProduits(int page, int size);
    List<ProduitDto> afficherProduits();
    Produit afficherProduitSelonId(long idProduit);
    ProduitDto afficherProduit(long idProduit);
    ProduitDto rechercherProduitParDesignation(String desigantion);
    ProduitDto creerProduit(ProduitDto produitDto);
    ProduitDto modifierProduit(ProduitDto produitDto);
    void supprimerProduit(long id);
}
