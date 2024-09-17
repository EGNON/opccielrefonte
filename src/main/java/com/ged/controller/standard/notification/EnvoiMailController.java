package com.ged.controller.standard.notification;

import com.ged.dto.standard.EnvoiMailDto;
import com.ged.entity.standard.CleEnvoiMail;
import com.ged.service.standard.EnvoiMailService;
import jakarta.validation.constraints.Positive;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/envoimails")
public class EnvoiMailController {
    private final EnvoiMailService envoiMailService;

    public EnvoiMailController(EnvoiMailService envoiMailService) {
        this.envoiMailService = envoiMailService;
    }

    @GetMapping
    public Page<EnvoiMailDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return envoiMailService.afficherEnvoiMails(page, size);
    }
    @GetMapping("/{idMail}")
    public List<EnvoiMailDto> afficherEnvoiMail(@PathVariable("idMail") long idMail)
    {
        return envoiMailService.afficherEnvoiMailSelonMail(idMail);
    }
    @PostMapping
    public EnvoiMailDto ajouter(@RequestBody EnvoiMailDto EnvoiMailDto)
    {
        return envoiMailService.creerEnvoiMail(EnvoiMailDto);
    }
    @PostMapping("/group")
    public void ajouter(@RequestBody EnvoiMailDto[] EnvoiMailDto)
    {
         envoiMailService.creerEnvoiMail(EnvoiMailDto);
    }

    @PutMapping("/{idMail}/{idPersonne}")
    public EnvoiMailDto modifier(@PathVariable long idMail,
                                       @PathVariable long idPersonne,
                                       @RequestBody EnvoiMailDto envoiMailDto)
    {
        envoiMailDto.getMailDto().setIdMail(idMail);
        envoiMailDto.getPersonneDto().setIdPersonne(idPersonne);
        return envoiMailService.modifierEnvoiMail(envoiMailDto);
    }

    @DeleteMapping("/{idMail}/{idPersonne}")
    public void supprimer(@PathVariable long idMail,
                          @PathVariable long idPersonne)
    {
        CleEnvoiMail cleEnvoiMail=new CleEnvoiMail();
        cleEnvoiMail.setIdMail(idMail);
        cleEnvoiMail.setIdPersonne(idPersonne);
        envoiMailService.supprimerEnvoiMail(cleEnvoiMail);
    }
    @DeleteMapping("/{idMail}")
    public void supprimerSelonRDV(@Positive @PathVariable("idMail") Long idMail)
    {
        envoiMailService.supprimerEnvoiMailSelonMail(idMail);
    }
}
