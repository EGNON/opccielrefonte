package com.ged.controller.standard.parametre;

import com.ged.dao.standard.PersonneDao;
import com.ged.dao.standard.PersonneMoraleDao;
import com.ged.dao.titresciel.DepositaireDao;
import com.ged.dao.titresciel.EmetteurDao;
import com.ged.dao.titresciel.RegistraireDao;
import com.ged.dto.standard.StatutPersonneDto;
import com.ged.dto.titresciel.DepositaireDto;
import com.ged.dto.titresciel.EmetteurDto;
import com.ged.dto.titresciel.RegistraireDto;
import com.ged.entity.standard.*;
import com.ged.entity.titresciel.Depositaire;
import com.ged.entity.titresciel.Emetteur;
import com.ged.entity.titresciel.Registraire;
import com.ged.mapper.standard.QualiteMapper;
import com.ged.mapper.titresciel.DepositaireMapper;
import com.ged.mapper.titresciel.EmetteurMapper;
import com.ged.mapper.titresciel.RegistraireMapper;
import com.ged.service.standard.PersonneMoraleService;
import com.ged.service.standard.PersonnePhysiqueService;
import com.ged.service.standard.QualiteService;
import com.ged.service.standard.StatutPersonneService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/statutPersonnes")
public class StatutPersonneController {
    private final StatutPersonneService statutPersonneService;
    private final QualiteService qualiteService;
    private final QualiteMapper qualiteMapper;
    private final RegistraireMapper registraireMapper;
    private final EmetteurMapper emetteurMapper;
    private final DepositaireMapper depositaireMapper;
    private final PersonneDao personneDao;
    private final PersonneMoraleDao personneMoraleDao;
    private final RegistraireDao registraireDao;
    private final EmetteurDao emetteurDao;
    private final DepositaireDao depositaireDao;
    private final PersonnePhysiqueService personnePhysiqueService;
    private final PersonneMoraleService personneMoraleService;

    public StatutPersonneController(StatutPersonneService statutPersonneService, QualiteService qualiteService, QualiteMapper qualiteMapper, RegistraireMapper registraireMapper, EmetteurMapper emetteurMapper, DepositaireMapper depositaireMapper, PersonneDao personneDao, PersonneMoraleDao personneMoraleDao, RegistraireDao registraireDao, EmetteurDao emetteurDao, DepositaireDao depositaireDao, PersonnePhysiqueService personnePhysiqueService, PersonneMoraleService personneMoraleService) {
        this.statutPersonneService = statutPersonneService;
        this.qualiteService = qualiteService;
        this.qualiteMapper = qualiteMapper;
        this.registraireMapper = registraireMapper;
        this.emetteurMapper = emetteurMapper;
        this.depositaireMapper = depositaireMapper;
        this.personneDao = personneDao;
        this.personneMoraleDao = personneMoraleDao;
        this.registraireDao = registraireDao;
        this.emetteurDao = emetteurDao;
        this.depositaireDao = depositaireDao;
        this.personnePhysiqueService = personnePhysiqueService;
        this.personneMoraleService = personneMoraleService;
    }

    @GetMapping
    public Page<StatutPersonneDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "size",defaultValue = "10") int size){
        return statutPersonneService.afficherStatutPersonnes(page,size);
    }

    @GetMapping("qualite/{qualite}")
    public StatutPersonneDto afficherStatutSelonQualite(@PathVariable("qualite") String qualite)
    {
        return statutPersonneService.afficherStatutSelonQualite(qualite);
    }

    @PostMapping
    public StatutPersonneDto ajouter(@RequestBody StatutPersonneDto statutPersonneDto)
    {
        return statutPersonneService.creerStatutPersonne(statutPersonneDto);
    }

    @PostMapping("/qualite")
    public StatutPersonneDto ajouterSelonQualite(@RequestBody StatutPersonneDto statutPersonneDto,
                                                 @RequestParam(value = "q", defaultValue = "") String q)
    {
        Qualite qualite1 = qualiteMapper.deQualiteDto(qualiteService.afficherSelonLibelle(q));
        statutPersonneDto.setQualite(qualiteMapper.deQualite(qualite1));

        CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
        cleStatutPersonne.setIdPersonne(statutPersonneDto.getPersonne().getIdPersonne());
        cleStatutPersonne.setIdQualite(qualite1.getIdQualite());

        StatutPersonne statutPersonne = statutPersonneService.afficherStatutPersonneSelonId(cleStatutPersonne);
        if(statutPersonne != null && statutPersonne.getIdStatutPersonne() != null) statutPersonneDto = statutPersonneService.modifierStatutPersonne(statutPersonneDto);
        else statutPersonneDto = statutPersonneService.creerStatutPersonne(statutPersonneDto);

        if(qualite1.getLibelleQualite().equalsIgnoreCase("registraires")) {
            PersonneMorale personneMorale = personneMoraleDao.findById(statutPersonneDto.getPersonne().getIdPersonne()).orElseThrow();
            RegistraireDto registraireDto=new RegistraireDto();
            registraireDto.setBp(personneMorale.getBp());
            registraireDto.setDenomination(personneMorale.getDenomination());
            registraireDto.setEstActifPersonne(personneMorale.getEstActifPersonne());
            registraireDto.setIdPersonne(personneMorale.getIdPersonne());
            registraireDto.setDateCreationServeur(LocalDateTime.now());
            registraireDto.setDateDernModifClient(LocalDateTime.now());
            registraireDto.setDateDernModifServeur(LocalDateTime.now());
            registraireDto.setSigle(personneMorale.getSigle());
            registraireDto.setSiglePersonneMorale(personneMorale.getSiglePersonneMorale());
            registraireDto.setRaisonSociale(personneMorale.getRaisonSociale());
            Registraire registraire =registraireMapper.deRegistraireDto(registraireDto);
            registraireDao.save(registraire);
        }
        if(qualite1.getLibelleQualite().equalsIgnoreCase("emetteurs")) {
            PersonneMorale personneMorale = personneMoraleDao.findById(statutPersonneDto.getPersonne().getIdPersonne()).orElseThrow();
            EmetteurDto emetteurDto=new EmetteurDto();
            emetteurDto.setBp(personneMorale.getBp());
            emetteurDto.setDenomination(personneMorale.getDenomination());
            emetteurDto.setEstActifPersonne(personneMorale.getEstActifPersonne());
            emetteurDto.setIdPersonne(personneMorale.getIdPersonne());
            emetteurDto.setDateCreationServeur(LocalDateTime.now());
            emetteurDto.setDateDernModifClient(LocalDateTime.now());
            emetteurDto.setDateDernModifServeur(LocalDateTime.now());
            emetteurDto.setSigle(personneMorale.getSigle());
            emetteurDto.setSiglePersonneMorale(personneMorale.getSigle());
            emetteurDto.setRaisonSociale(personneMorale.getRaisonSociale());
            Emetteur emetteur =emetteurMapper.deEmetteurDto(emetteurDto);
            emetteurDao.save(emetteur);
        }
        if(qualite1.getLibelleQualite().equalsIgnoreCase("depositaires")) {
            PersonneMorale personneMorale = personneMoraleDao.findById(statutPersonneDto.getPersonne().getIdPersonne()).orElseThrow();
            DepositaireDto depositaireDto=new DepositaireDto();
            depositaireDto.setBp(personneMorale.getBp());
            depositaireDto.setDenomination(personneMorale.getDenomination());
            depositaireDto.setEstActifPersonne(personneMorale.getEstActifPersonne());
            depositaireDto.setIdPersonne(personneMorale.getIdPersonne());
            depositaireDto.setDateCreationServeur(LocalDateTime.now());
            depositaireDto.setDateDernModifClient(LocalDateTime.now());
            depositaireDto.setDateDernModifServeur(LocalDateTime.now());
            depositaireDto.setSigle(personneMorale.getSigle());
            depositaireDto.setSiglePersonneMorale(personneMorale.getSiglePersonneMorale());
            depositaireDto.setRaisonSociale(personneMorale.getRaisonSociale());
            Depositaire depositaire =depositaireMapper.deDepositaireDto(depositaireDto);
            depositaireDao.save(depositaire);
        }
            //System.out.println("qualité="+qualite1.getLibelleQualite());
        if(!qualite1.getLibelleQualite().equalsIgnoreCase("prospect")) {
            Personne personne = personneDao.findById(statutPersonneDto.getPersonne().getIdPersonne()).orElseThrow();
           if(personne.getSousTypeClient()!=null)
           {
               if(personne.getSousTypeClient().getTypeClient()!=null)
               {
                   if(personne.getSousTypeClient().getTypeClient().getIntitule().toLowerCase().contains("personne physique"))
                       personne.setNumOrdre(personnePhysiqueService.afficherMaxNumordre()+1);
                   else
                       personne.setNumOrdre(personneMoraleService.afficherMaxNumordre(personne.getSousTypeClient().getTypeClient().getIntitule())+1);
               }
           }

            personneDao.save(personne);
        }

        return statutPersonneDto;
    }

    @PutMapping("/{idPersonne}/{idQualite}")
    public StatutPersonneDto modifier(@PathVariable long idPersonne,
                                      @PathVariable long idQualite,
                                      @RequestBody StatutPersonneDto statutPersonneDto){
        statutPersonneDto.getPersonne().setIdPersonne(idPersonne);
        statutPersonneDto.getQualite().setIdQualite(idQualite);
        return statutPersonneService.modifierStatutPersonne(statutPersonneDto);
    }
    @DeleteMapping("/{idPersonne}/{idQualite}/{idPersonnel}")
    public void Supprimer(@PathVariable long idPersonne,
                          @PathVariable long idQualite){
        CleStatutPersonne cleStatutPersonne=new CleStatutPersonne();
        cleStatutPersonne.setIdQualite(idQualite);
        cleStatutPersonne.setIdPersonne(idPersonne);
        statutPersonneService.supprimerStatutPersonne(cleStatutPersonne);
    }
}
