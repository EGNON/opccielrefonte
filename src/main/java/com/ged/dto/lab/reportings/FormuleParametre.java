package com.ged.dto.lab.reportings;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class FormuleParametre {
    LocalDateTime dateTimeParametre;
    Date dateParametre;
    Long idOpcvm;
    Long idTitre;

    public LocalDateTime getDateTimeParametre() {
        return dateTimeParametre;
    }

    public void setDateTimeParametre(LocalDateTime dateTimeParametre) {
        this.dateTimeParametre = dateTimeParametre;
    }

    public Date getDateParametre() {
        return dateParametre;
    }

    public void setDateParametre(Date dateParametre) {
        this.dateParametre = dateParametre;
    }

    public Long getIdOpcvm() {
        return idOpcvm;
    }

    public void setIdOpcvm(Long idOpcvm) {
        this.idOpcvm = idOpcvm;
    }

    public Long getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(Long idTitre) {
        this.idTitre = idTitre;
    }
}
