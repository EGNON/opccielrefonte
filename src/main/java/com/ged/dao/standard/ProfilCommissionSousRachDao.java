package com.ged.dao.standard;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.ProfilCommissionSousRach;
import com.ged.entity.standard.CleProfilCommissionSousRach;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfilCommissionSousRachDao extends JpaRepository<ProfilCommissionSousRach,CleProfilCommissionSousRach> {
    Page<ProfilCommissionSousRach> findByOpcvm(Opcvm opcvm,Pageable pageable);

    List<ProfilCommissionSousRach> findByTypeCommissionAndOpcvm(String typeCommission,Opcvm opcvm);
}
