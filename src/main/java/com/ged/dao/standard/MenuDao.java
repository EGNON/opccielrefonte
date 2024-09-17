package com.ged.dao.standard;

import com.ged.entity.standard.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuDao extends JpaRepository<Menu, Long> {
    List<Menu> findAllByOrderByOrdreAffichageAsc();
    Boolean existsByTitle(String title);
}
