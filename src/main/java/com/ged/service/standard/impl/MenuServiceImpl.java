package com.ged.service.standard.impl;

import com.ged.dao.standard.MenuDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.MenuDto;
import com.ged.entity.standard.Menu;
import com.ged.service.standard.MenuService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {
    private final MenuDao menuDao;
    private final ModelMapper modelMapper;

    public MenuServiceImpl(MenuDao menuDao, ModelMapper modelMapper) {
        this.menuDao = menuDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public DataTablesResponse<MenuDto> afficherTous(DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());
        Page<Menu> menuPage = menuDao.findAll(pageable);

        List<MenuDto> content = menuPage.getContent().stream().map(menu -> modelMapper.map(menu, MenuDto.class)).collect(Collectors.toList());
        DataTablesResponse<MenuDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)menuPage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)menuPage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public List<MenuDto> afficheTous() {
        return menuDao.findAllByOrderByOrdreAffichageAsc().stream().map(menu -> modelMapper.map(menu, MenuDto.class)).collect(Collectors.toList());
    }

    @Override
    public List<MenuDto> afficherSelonUtilisateur(Long id) {
        //Utilisateur utilisateur =
        return null;
    }

    @Override
    public Menu afficherSelonId(Long id) {
        return menuDao.findById(id).orElse(null);
    }

    @Override
    public MenuDto afficher(Long id) {
        return modelMapper.map(afficherSelonId(id), MenuDto.class);
    }

    @Override
    public MenuDto ajouter(MenuDto menuDto) {
        if(menuDao.existsByTitle(menuDto.getTitle()))
            return null;
//        System.out.println("Menu - {} " + menuDto);
        Menu menu = modelMapper.map(menuDto, Menu.class);
        menu = menuDao.save(menu);
        return modelMapper.map(menu, MenuDto.class);
    }

    @Override
    public MenuDto modifier(MenuDto menuDto) {
        Menu menu = modelMapper.map(menuDto, Menu.class);
        menu = menuDao.save(menu);
        return modelMapper.map(menu, MenuDto.class);
    }

    @Override
    public void supprimer(Long id) {
        menuDao.deleteById(id);
    }
}
