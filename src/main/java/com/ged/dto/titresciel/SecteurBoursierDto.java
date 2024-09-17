package com.ged.dto.titresciel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SecteurBoursierDto {

    private Long idSecteurBoursier;
    private String libelleSecteurBoursier;
//    @CreationTimestamp
//    private LocalDateTime dateCreationServeur;
//    @UpdateTimestamp
//    private LocalDateTime dateDernModifServeur;
//    @UpdateTimestamp
//    private LocalDateTime dateDernModifClient;
//    private long numLigne;
//    @Column(columnDefinition = "BIT", length = 1)
//    private boolean supprimer;
//
//    private LocalDateTime rowvers;
//    @Basic
//    private String userLogin;
//    @OneToMany(mappedBy = "SecteurBoursier", fetch = FetchType.LAZY)
//    @JsonManagedReference
//    private Set<Opcvm> opcvms = new HashSet<>();

    public SecteurBoursierDto() {
    }

    public SecteurBoursierDto(String libelleSecteurBoursier) {
        this.libelleSecteurBoursier = libelleSecteurBoursier;
    }

    public Long getIdSecteurBoursier() {
        return idSecteurBoursier;
    }

    public void setIdSecteurBoursier(Long idSecteurBoursier) {
        this.idSecteurBoursier = idSecteurBoursier;
    }

    public String getLibelleSecteurBoursier() {
        return libelleSecteurBoursier;
    }

    public void setLibelleSecteurBoursier(String libelleSecteurBoursier) {
        this.libelleSecteurBoursier = libelleSecteurBoursier;
    }

//    public Set<Opcvm> getOpcvms() {
//        return opcvms;
//    }
//
//    public void setOpcvms(Set<Opcvm> opcvms) {
//        this.opcvms = opcvms;
//    }
}
