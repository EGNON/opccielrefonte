package com.ged.dao.standard;

import com.ged.entity.standard.Profession;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessionDao extends JpaRepository<Profession,Long> {
    Profession findByLibelleProfession(String libelleProfession);
    Boolean existsByLibelleProfession(String libelle);
    Page<Profession> findByLibelleProfessionContainsIgnoreCase(String libelle, Pageable pageable);
}
