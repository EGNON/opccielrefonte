package com.ged.entity.opcciel;

import com.ged.entity.opcciel.comptabilite.Operation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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
    private String userLogin;
    private Boolean estPayee;
    private Long idSeancePaiement;
//    @ColumnDefault("0")
//    private Boolean supprimer = false;
}
