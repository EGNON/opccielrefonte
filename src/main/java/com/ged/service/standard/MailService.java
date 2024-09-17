package com.ged.service.standard;

import com.ged.dto.standard.MailDto;
import com.ged.entity.standard.Mail;
import org.springframework.data.domain.Page;

public interface MailService{
    Page<MailDto> afficherMails(int page, int size);
    Mail afficherMailSelonId(long idMail);
    MailDto creerMail(MailDto mailDto) throws Throwable;
    MailDto modifierMail(MailDto mailDto) throws Throwable;
    void supprimerMail(long idMail);
    void supprimerMailSelonModeleMsgAlerte(long idModeleMsgAlerte);
}
