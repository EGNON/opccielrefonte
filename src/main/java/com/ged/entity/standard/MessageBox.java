package com.ged.entity.standard;

import com.ged.entity.Base;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "T_MessageBox", schema = "Notification")
public class MessageBox extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMsgBox;
    private LocalDateTime dateEnvoiMsg;
    private String objet;
    private String contenu;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPersonne")
    //@JsonBackReference
    private Personne destinataire;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAlerte")
    //@JsonBackReference
    private Alerte alerte;
}
