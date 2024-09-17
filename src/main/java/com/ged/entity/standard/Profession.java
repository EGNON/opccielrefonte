package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Set;

@Entity
@Table(name = "T_Profession",schema = "Parametre")
public class Profession extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProf;
    @NotBlank(message = "La profession est obligatoire")
    private String libelleProfession;
    @OneToMany(mappedBy = "profession")
    //@JsonManagedReference
    private Set<PersonnePhysique> personnePhysiques;

    public Profession() {
    }

    public Profession(String libelleProfession) {
        this.libelleProfession = libelleProfession;
    }

    public Long getIdProf() {
        return idProf;
    }

    public void setIdProf(Long idProf) {
        this.idProf = idProf;
    }

    public String getLibelleProfession() {
        return libelleProfession;
    }

    public void setLibelleProfession(String libelleProfession) {
        this.libelleProfession = libelleProfession;
    }

    public Set<PersonnePhysique> getPersonnePhysiques() {
        return personnePhysiques;
    }

    public void setPersonnePhysiques(Set<PersonnePhysique> personnePhysiques) {
        this.personnePhysiques = personnePhysiques;
    }
}
