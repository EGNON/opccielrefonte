package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

@Entity
@Table(name = "T_TypePlanification",schema = "Notification")
public class TypePlanification extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypePlanification;
    private String libelleTypePlanification;

    public TypePlanification() {
    }

    public TypePlanification(String libelleTypePlanification) {
        this.libelleTypePlanification = libelleTypePlanification;
    }

    public Long getIdTypePlanification() {
        return idTypePlanification;
    }

    public void setIdTypePlanification(Long idTypePlanification) {
        this.idTypePlanification = idTypePlanification;
    }

    public String getLibelleTypePlanification() {
        return libelleTypePlanification;
    }

    public void setLibelleTypePlanification(String libelleTypePlanification) {
        this.libelleTypePlanification = libelleTypePlanification;
    }
}
