package com.ged.dao.crm;

import com.ged.entity.crm.CleDetailObjectif;
import com.ged.entity.crm.DetailObjectif;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetailObjectifDao extends JpaRepository<DetailObjectif, CleDetailObjectif> {
}
