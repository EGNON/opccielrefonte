package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "T_NbreJours",schema = "Notification")
public class NbreJours extends Base {
    @Id
    private long idNbreJours;

    public NbreJours() {
    }

    public NbreJours(long id) {
        this.idNbreJours = id;
    }

    public long getIdNbreJours() {
        return idNbreJours;
    }

    public void setIdNbreJours(long idNbreJours) {
        this.idNbreJours = idNbreJours;
    }
}
