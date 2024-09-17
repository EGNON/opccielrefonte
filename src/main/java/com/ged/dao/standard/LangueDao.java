package com.ged.dao.standard;

import com.ged.entity.standard.Langue;
import com.ged.entity.standard.Pays;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LangueDao extends JpaRepository<Langue,Long> {

}
