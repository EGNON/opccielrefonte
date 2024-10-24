package com.ged.dao.opcciel;

import com.ged.entity.opcciel.DepotRachat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DepotRachatDao extends JpaRepository<DepotRachat,Long> {
    @Query(value = "select d from DepotRachat d join d.natureOperation n where n.codeNatureOperation = 'DEP_SOUS'")
    Page<DepotRachat> listeDesDepotSeance(Pageable pageable);
}
