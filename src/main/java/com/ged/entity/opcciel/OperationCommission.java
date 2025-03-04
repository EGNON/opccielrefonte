package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "T_OperationCommission", schema = "Operation")
public class OperationCommission extends Operation {
    private LocalDateTime dateSolde;
    @Column(precision = 18, scale = 6)
    private BigDecimal montantCommission;
    private String typeCommission;
}
