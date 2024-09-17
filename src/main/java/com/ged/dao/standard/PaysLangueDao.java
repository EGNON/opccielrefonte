package com.ged.dao.standard;

import com.ged.entity.standard.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaysLangueDao extends JpaRepository<PaysLangue, ClePaysLangue> {
    void deleteByLangue(Langue langue);
    List<PaysLangue> findByLangue(Langue langue);
    List<PaysLangue> findByPays(Pays pays);
}
