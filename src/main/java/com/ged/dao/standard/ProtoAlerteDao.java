package com.ged.dao.standard;

import com.ged.entity.standard.CleProtoAlerte;
import com.ged.entity.standard.ProtoAlerte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProtoAlerteDao extends JpaRepository<ProtoAlerte, CleProtoAlerte> {
}
