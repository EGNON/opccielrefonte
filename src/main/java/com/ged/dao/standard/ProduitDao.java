package com.ged.dao.standard;

import com.ged.entity.standard.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitDao extends JpaRepository<Produit,Long> {
    Produit findByDesignation(String designation);
    Page<Produit> findByDesignationContainsIgnoreCase(String Designation, Pageable pageable);
}
