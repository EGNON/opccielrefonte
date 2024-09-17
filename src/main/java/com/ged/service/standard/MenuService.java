package com.ged.service.standard;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.MenuDto;
import com.ged.entity.standard.Menu;

import java.util.List;

public interface MenuService {
    DataTablesResponse<MenuDto> afficherTous(DatatableParameters parameters);
    List<MenuDto> afficheTous();
    List<MenuDto> afficherSelonUtilisateur(Long id);
    Menu afficherSelonId(Long id);
    MenuDto afficher(Long id);
    MenuDto ajouter(MenuDto menuDto);
    MenuDto modifier(MenuDto menuDto);
    void supprimer(Long id);
}
