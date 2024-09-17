package com.ged.dao.standard;

import com.ged.entity.standard.Alerte;
import com.ged.entity.standard.CleNbreJoursAlerte;
import com.ged.entity.standard.NbreJoursAlerte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NbreJoursAlerteDao extends JpaRepository<NbreJoursAlerte, CleNbreJoursAlerte> {
    List<NbreJoursAlerte> findByAlerte(Alerte alerte);
    void deleteByAlerte(Alerte alerte);
}
