package com.ged.entity.opcciel;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "T_Migration", schema = "Parametre")
public class Migration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMig;
    private LocalDateTime dateMigration;

    public Migration() {
    }

    public Migration(Long idMig, LocalDateTime dateMigration) {
        this.idMig = idMig;
        this.dateMigration = dateMigration;
    }

    public Long getIdMig() {
        return idMig;
    }

    public void setIdMig(Long idMig) {
        this.idMig = idMig;
    }

    public LocalDateTime getDateMigration() {
        return dateMigration;
    }

    public void setDateMigration(LocalDateTime dateMigration) {
        this.dateMigration = dateMigration;
    }
}
