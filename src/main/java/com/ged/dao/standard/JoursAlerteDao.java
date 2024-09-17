package com.ged.dao.standard;

import com.ged.entity.standard.Alerte;
import com.ged.entity.standard.CleJoursAlerte;
import com.ged.entity.standard.JoursAlerte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JoursAlerteDao extends JpaRepository<JoursAlerte, CleJoursAlerte> {
    List<JoursAlerte> findByAlerte(Alerte alerte);
    void deleteByAlerte(Alerte alerte);
}
