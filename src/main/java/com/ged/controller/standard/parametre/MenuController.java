package com.ged.controller.standard.parametre;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.MenuDto;
import com.ged.service.standard.MenuService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin/menus")
public class MenuController {
    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping
    public List<MenuDto> afficherTous()
    {
        return menuService.afficheTous();
    }

    @PostMapping("/datatable/list")
    public DataTablesResponse<MenuDto> datatableList(@RequestBody DatatableParameters params)
    {
        return menuService.afficherTous(params);
    }

    @GetMapping("/{id}")
    public MenuDto afficher(@PathVariable Long id)
    {
        return menuService.afficher(id);
    }

    @PostMapping
    public MenuDto ajouter(@RequestBody MenuDto menuDto)
    {
        return menuService.ajouter(menuDto);
    }

    @PutMapping("/{id}")
    public MenuDto modifier(@RequestBody MenuDto menuDto, @PathVariable Long id)
    {
        menuDto.setIdMenu(id);
        return menuService.modifier(menuDto);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable Long id)
    {
        menuService.supprimer(id);
    }
}
