package com.ged.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class Base implements Serializable {
    //Champs communs
//    @CreationTimestamp
    @CreatedDate
    @Column(name = "dateCreationServeur", nullable = false, updatable = false)
    private LocalDateTime dateCreationServeur;
//    @UpdateTimestamp
    @LastModifiedDate
    @Column(name = "dateDernModifServeur", insertable = false)
    private LocalDateTime dateDernModifServeur;
    private LocalDateTime dateDernModifClient;
    @ColumnDefault("0")
    private Boolean supprimer = false;
    /*@Column(insertable = false, updatable = false)
    @JdbcTypeCode(Types.TIMESTAMP)
    private byte[] rowvers;*/
    @CreatedBy
    @Column(nullable = false, updatable = false)
    private Long creePar;
    @LastModifiedBy
    @Column(insertable = false)
    private Long modifiePar;
   /* @ManyToOne
    @JoinColumn(name = "idCreateur")
    private Utilisateur createur;*/
    private Long idCreateur;
    private Long idOcc;
}
