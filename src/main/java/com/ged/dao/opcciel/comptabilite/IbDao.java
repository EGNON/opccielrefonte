package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.Ib;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IbDao extends JpaRepository<Ib, String> {
    Optional<Ib> findByCodeIBIgnoreCase(String code);
}
