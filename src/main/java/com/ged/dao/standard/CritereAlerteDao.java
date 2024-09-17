package com.ged.dao.standard;

import com.ged.entity.standard.CritereAlerte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CritereAlerteDao extends JpaRepository<CritereAlerte,Long> {
}
