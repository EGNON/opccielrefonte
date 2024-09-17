package com.ged.dao.titresciel;

import com.ged.entity.titresciel.TypeEvenement;
import com.ged.entity.titresciel.TypeObligation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TypeEvenementDao extends JpaRepository<TypeEvenement, Long> {

}
