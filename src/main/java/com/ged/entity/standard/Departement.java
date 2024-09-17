package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "T_Departement", schema = "Parametre")
public class Departement extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDepartement;
    private String libelleDepartement;
    @OneToMany(mappedBy = "departement", fetch = FetchType.LAZY)
    //@JsonManagedReference
    private Set<Commune> communes = new HashSet<>();

    public Departement() {
    }

    public Departement(String libelleDepartement) {
        this.libelleDepartement = libelleDepartement;
    }

    public void affecterCommune(Commune commune)
    {
        this.communes.add(commune);
    }

    public void supprimerCommuneDuDepartement(Commune commune)
    {
        this.communes.remove(commune);
    }

    public Long getIdDepartement() {
        return idDepartement;
    }

    public void setIdDepartement(Long idDepartement) {
        this.idDepartement = idDepartement;
    }

    public String getLibelleDepartement() {
        return libelleDepartement;
    }

    public void setLibelleDepartement(String libelleDepartement) {
        this.libelleDepartement = libelleDepartement;
    }

    public Set<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(Set<Commune> communes) {
        this.communes = communes;
    }

    @Override
    public String toString() {
        return "Departement [" +
                "idDepartement=" + idDepartement +
                ", libelleDepartement='" + libelleDepartement + '\'' +
                ']';
    }
}
