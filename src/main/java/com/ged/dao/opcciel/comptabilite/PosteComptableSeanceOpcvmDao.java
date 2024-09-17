
package com.ged.dao.opcciel.comptabilite;

import com.ged.entity.opcciel.comptabilite.ClePosteComptable;
import com.ged.entity.opcciel.comptabilite.PosteComptable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosteComptableSeanceOpcvmDao extends JpaRepository<PosteComptable, ClePosteComptable> {

}
