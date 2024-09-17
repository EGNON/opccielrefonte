package com.ged.dao.standard;

import com.ged.entity.standard.Personnel;
//import org.hibernate.validator.constraints.EAN;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonnelDao extends JpaRepository<Personnel, Long> {
    @Query(value = "SELECT p FROM Personnel p WHERE p.typePersonne = :ptype")
    Page<Personnel> findAllByTypePersonne(@Param("ptype") Pageable pageable);
    List<Personnel> findByEstCommercial(boolean estCommercial);
}
