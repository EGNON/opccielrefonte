package com.ged.service.standard;

import com.ged.dto.standard.EnvoiMailDto;
import com.ged.entity.standard.CleEnvoiMail;
import com.ged.entity.standard.EnvoiMail;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EnvoiMailService {
    Page<EnvoiMailDto> afficherEnvoiMails(int page, int size);
    EnvoiMail afficherEnvoiMailSelonId(CleEnvoiMail idEnvoiMail);
    List<EnvoiMailDto> afficherEnvoiMailSelonMail(long idMail);
    EnvoiMailDto creerEnvoiMail(EnvoiMailDto envoiMailDto);
    void creerEnvoiMail(EnvoiMailDto[] envoiMailDto);
    EnvoiMailDto modifierEnvoiMail(EnvoiMailDto envoiMailDto);
    void supprimerEnvoiMail(CleEnvoiMail cleEnvoiMail);
    void supprimerEnvoiMailSelonMail(long idMail);
}
