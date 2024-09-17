package com.ged.dao.standard;

import com.ged.entity.standard.ClePersonnePhysiquePays;
import com.ged.entity.standard.PersonnePhysique;
import com.ged.entity.standard.PersonnePhysiquePays;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonnePhysiquePaysDao extends JpaRepository<PersonnePhysiquePays, ClePersonnePhysiquePays> {
    void deleteByPersonnePhysique(PersonnePhysique personnePhysique);
    List<PersonnePhysiquePays> findByPersonnePhysique(PersonnePhysique personnePhysique);
}
