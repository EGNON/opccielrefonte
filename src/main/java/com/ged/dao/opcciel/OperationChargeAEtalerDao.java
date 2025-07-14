package com.ged.dao.opcciel;

import com.ged.dto.opcciel.OperationChargeAEtalerDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.OperationChargeAEtaler;
import com.ged.entity.opcciel.OperationDetachement;
import com.ged.projection.OperationDetachementProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface OperationChargeAEtalerDao extends JpaRepository<OperationChargeAEtaler,Long>, JpaSpecificationExecutor<OperationChargeAEtaler> {
    @Query("update OperationChargeAEtaler o set" +
            " o.estVerifie1=:estVerifie1,o.userLoginVerificateur1=:userLoginVerificateur1,o.dateVerification1=:dateVerification1" +
            " where o.idOperation=:idOperation")
    @Modifying
    int modifier1(Long idOperation, String userLoginVerificateur1, LocalDateTime dateVerification1,
                  Boolean estVerifie1);
    @Query("update OperationChargeAEtaler o set" +
            " o.estVerifie2=:estVerifie2,o.userLoginVerificateur2=:userLoginVerificateur2,o.dateVerification2=:dateVerification2" +
            " where o.idOperation=:idOperation")
    @Modifying
    int modifier2(Long idOperation, String userLoginVerificateur2, LocalDateTime dateVerification2,
                  Boolean estVerifie2);
}
