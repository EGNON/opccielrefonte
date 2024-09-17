package com.ged.dao.standard;

import com.ged.entity.standard.Alerte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlerteDao extends JpaRepository<Alerte,Long> {
    Alerte findById(long id);
}
