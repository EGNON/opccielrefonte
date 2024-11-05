package com.ged.dao;

import com.ged.entity.BaseEntity;
import com.ged.projection.FT_DepotRachatProjection;
import com.ged.projection.NbrePartProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface LibraryDao extends JpaRepository<BaseEntity, Long> {
    @Query(value = "select [Comptabilite].[FS_SoldeCompteClient](:idActionnaire, :idOpcvm)", nativeQuery = true)
    BigDecimal solde(@Param("idActionnaire") Long idActionnaire, @Param("idOpcvm") Long idOpcvm);

    @Query(value = "SELECT * FROM [Operation].[FT_NbrePart](:idActionnaire,:idOpcvm,:estLevee," +
            ":estVerifie1,:estVerifie2,:dateEstimation)",nativeQuery = true)
    List<NbrePartProjection> afficherNbrePart(@Param("idActionnaire") Long idActionnaire,
                                          @Param("idOpcvm") Long idOpcvm,
                                          @Param("estLevee") boolean estLevee,
                                          @Param("estVerifie1") boolean estVerifie1,
                                          @Param("estVerifie2") boolean estVerifie2,
                                          @Param("dateEstimation") LocalDateTime dateEstimation
                                          );
    @Query(value = "SELECT * FROM [Parametre].[FT_DepotRachat2](:IdSeance," +
            ":IdPersonne,:IdOpcvm,:codeNatureOperation,:niveau1" +
            ",:niveau2)",nativeQuery = true)
    List<FT_DepotRachatProjection> afficherFT_DepotRachat(@Param("IdSeance") Long IdSeance,
                                                    @Param("IdPersonne") Long IdPersonne,
                                                    @Param("IdOpcvm") Long IdOpcvm,
                                                    @Param("codeNatureOperation") String codeNatureOperation,
                                                    @Param("niveau1") boolean niveau1,
                                                    @Param("niveau2") boolean niveau2
                                          );
}
