package com.ged.dao.standard;

import com.ged.entity.standard.Alerte;
import com.ged.entity.standard.CleDiffusionAlerte;
import com.ged.entity.standard.DiffusionAlerte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DiffusionAlerteDao extends JpaRepository<DiffusionAlerte, CleDiffusionAlerte> {
    void deleteByAlerte(Alerte alerte);
    List<DiffusionAlerte> findByAlerte(Alerte alerte);

    List<DiffusionAlerte> findAllByIsShownAndIsRead(Boolean isShown, Boolean isRead);
}
