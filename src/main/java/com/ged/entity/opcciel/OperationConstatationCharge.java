package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "T_OperationConstatationCharge", schema = "Operation")
public class OperationConstatationCharge extends Operation {
    private LocalDateTime dateSolde;
    @Column(precision = 18, scale = 6)
    private BigDecimal montantCharge;
    /*@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codeCharge", referencedColumnName = "")
    private Charge charge;*/
    private String codeCharge;
    private Boolean estPayee;
    private Long idSeancePaiement;
}
