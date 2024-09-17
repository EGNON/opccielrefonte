package com.ged.dao.standard;

import com.ged.entity.standard.Alerte;
import com.ged.entity.standard.CleTempsAlerte;
import com.ged.entity.standard.TempsAlerte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TempsAlerteDao extends JpaRepository<TempsAlerte, CleTempsAlerte> {
    List<TempsAlerte> findByAlerte(Alerte alerte);
    void deleteByAlerte(Alerte alerte);
}
