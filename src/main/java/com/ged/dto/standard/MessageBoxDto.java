package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageBoxDto {
    private Long idMsgBox;
    private LocalDateTime dateEnvoiMsg;
    private String objet;
    private String contenu;
    private PersonneDto destinataire;
    private AlerteDto alerte;
}
