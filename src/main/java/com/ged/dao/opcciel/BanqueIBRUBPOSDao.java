package com.ged.dao.opcciel;

import com.ged.entity.opcciel.BanqueIBRUBPOS;
import com.ged.entity.opcciel.CleBanqueIBRUBPOS;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanqueIBRUBPOSDao extends JpaRepository<BanqueIBRUBPOS, CleBanqueIBRUBPOS> {
    
}
