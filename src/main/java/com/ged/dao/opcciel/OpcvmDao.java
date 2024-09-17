
package com.ged.dao.opcciel;

import com.ged.entity.opcciel.Opcvm;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpcvmDao extends JpaRepository<Opcvm,Long> {
    Boolean existsByAgrementIgnoreCase(String agrement);
    Opcvm findByAgrementIgnoreCase(String agrement);
}
