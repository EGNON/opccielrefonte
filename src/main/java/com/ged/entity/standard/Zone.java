package com.ged.entity.standard;

import com.ged.entity.Base;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "T_Zone", schema = "Parametre")
public class Zone extends Base {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String codeZone;
    @Basic
    private String libelleZone;
    @OneToMany(mappedBy = "zone", fetch = FetchType.LAZY)
    private Set<Pays> pays = new HashSet<>();

    public Zone() {
    }

    public Zone(String codeZone, String libelleZone) {
        this.codeZone = codeZone;
        this.libelleZone = libelleZone;
    }

    public String getCodeZone() {
        return codeZone;
    }

    public void setCodeZone(String codeZone) {
        this.codeZone = codeZone;
    }

    public String getLibelleZone() {
        return libelleZone;
    }

    public void setLibelleZone(String libelleZone) {
        this.libelleZone = libelleZone;
    }

    public Set<Pays> getPays() {
        return pays;
    }

    public void setPays(Set<Pays> pays) {
        this.pays = pays;
    }
}
