package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Banque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BanqueDao extends JpaRepository<Banque, Long> {
    Optional<Banque> findByCodeBanqueIgnoreCase(String code);
}
