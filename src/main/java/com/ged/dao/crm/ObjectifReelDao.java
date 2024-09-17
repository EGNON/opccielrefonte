package com.ged.dao.crm;

import com.ged.entity.crm.ObjectifReel;
import com.ged.projection.StatistiqueProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ObjectifReelDao extends JpaRepository<ObjectifReel,Long> {

}
