package com.ged.controller.standard.notification;

import com.ged.dto.standard.MailSenderDto;
import com.ged.service.standard.impl.MailSenderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mailsender")
public class MailSenderController {
    @Autowired
    MailSenderServiceImpl mailSenderServiceImpl;

    @PostMapping("/one")
    public String sendEmail(@RequestBody MailSenderDto mailSenderDto) {

        boolean envoi= mailSenderServiceImpl.send(mailSenderDto.getSubject(),
                mailSenderDto.getRecipientEmailMany()[0],
                mailSenderDto.getContent());
        if(envoi){
            return "Mail envoyé avec succès";
        }
        else
            return "Erreur lors de l'envoi";
    }
    @PostMapping("/onewithfiles")
    public String sendEmailWithAttachement(@RequestBody MailSenderDto mailSenderDto) {

        boolean envoi= mailSenderServiceImpl.sendWithAttachement(mailSenderDto.getSubject(),
                mailSenderDto.getRecipientEmailMany()[0],
                mailSenderDto.getContent(),
                mailSenderDto.getFileName(),
                mailSenderDto.getUrl()
                );
        if(envoi){
            return "Mail envoyé avec succès";
        }
        else
            return "Erreur lors de l'envoi";
    }
    @PostMapping("/many")
    public String sendEmailMany(@RequestBody MailSenderDto mailSenderDto) {

        boolean envoi= mailSenderServiceImpl.sendMany(mailSenderDto.getSubject(),
                mailSenderDto.getRecipientEmailMany(),
                mailSenderDto.getContent());
        if(envoi){
            return "Mail envoyé avec succès";
        }
        else
            return "Erreur lors de l'envoi";
    }
    @PostMapping("/manywithfiles")
    public String sendEmailManyWithAttachement(@RequestBody MailSenderDto mailSenderDto) {

        boolean envoi= mailSenderServiceImpl.sendManyWithAttachement(mailSenderDto.getSubject(),
                mailSenderDto.getRecipientEmailMany(),
                mailSenderDto.getContent(),
                mailSenderDto.getFileName(),
                mailSenderDto.getfToByte());
        if(envoi){
            return "Mail envoyé avec succès";
        }
        else
            return "Erreur lors de l'envoi";
    }

    @PostMapping("/manywithfiles2")
    public String sendEmailManyWithAttachement2(@RequestBody MailSenderDto mailSenderDto) {

        boolean envoi= mailSenderServiceImpl.sendManyWithAttachementBlob(mailSenderDto.getSubject(),
                mailSenderDto.getRecipientEmailMany()[0],
                mailSenderDto.getContent(),
                mailSenderDto.getFileName()[0],
                mailSenderDto.getfToByte()[0]);
        if(envoi){
            return "Mail envoyé avec succès";
        }
        else
            return "Erreur lors de l'envoi";
    }
}
