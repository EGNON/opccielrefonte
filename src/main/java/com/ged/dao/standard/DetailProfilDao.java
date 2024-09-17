package com.ged.dao.standard;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.CleDetailProfil;
import com.ged.entity.standard.DetailProfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailProfilDao extends JpaRepository<DetailProfil,CleDetailProfil> {
    Page<DetailProfil> findByOpcvm(Opcvm opcvm,Pageable pageable);

    List<DetailProfil> findByCodeProfilAndOpcvm(String codeProfil,Opcvm opcvm);
    void deleteByCodeProfilAndOpcvm(String codeProfil,Opcvm opcvm);
}
