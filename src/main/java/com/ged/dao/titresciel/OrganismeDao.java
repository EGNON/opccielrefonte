package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Opc;
import com.ged.entity.titresciel.Organisme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganismeDao extends JpaRepository<Organisme, Long> {

}
