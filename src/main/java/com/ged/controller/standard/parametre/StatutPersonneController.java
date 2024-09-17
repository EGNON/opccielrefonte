package com.ged.controller.standard.parametre;

import com.ged.dao.standard.PersonneDao;
import com.ged.dto.standard.StatutPersonneDto;
import com.ged.entity.standard.CleStatutPersonne;
import com.ged.entity.standard.Personne;
import com.ged.entity.standard.Qualite;
import com.ged.entity.standard.StatutPersonne;
import com.ged.mapper.standard.QualiteMapper;
import com.ged.service.standard.PersonneMoraleService;
import com.ged.service.standard.PersonnePhysiqueService;
import com.ged.service.standard.QualiteService;
import com.ged.service.standard.StatutPersonneService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/statutPersonnes")
public class StatutPersonneController {
    private final StatutPersonneService statutPersonneService;
    private final QualiteService qualiteService;
    private final QualiteMapper qualiteMapper;
    private final PersonneDao personneDao;
    private final PersonnePhysiqueService personnePhysiqueService;
    private final PersonneMoraleService personneMoraleService;

    public StatutPersonneController(StatutPersonneService statutPersonneService, QualiteService qualiteService, QualiteMapper qualiteMapper, PersonneDao personneDao, PersonnePhysiqueService personnePhysiqueService, PersonneMoraleService personneMoraleService) {
        this.statutPersonneService = statutPersonneService;
        this.qualiteService = qualiteService;
        this.qualiteMapper = qualiteMapper;
        this.personneDao = personneDao;
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

        //System.out.println("qualité="+qualite1.getLibelleQualite());
        if(!qualite1.getLibelleQualite().toLowerCase().equals("prospect")) {
            Personne personne = personneDao.findById(statutPersonneDto.getPersonne().getIdPersonne()).orElseThrow();
            if(personne.getSousTypeClient().getTypeClient().getLibelleTypeClient().toLowerCase().contains("personne physique"))
                personne.setNumOrdre(personnePhysiqueService.afficherMaxNumordre()+1);
            else
                personne.setNumOrdre(personneMoraleService.afficherMaxNumordre(personne.getSousTypeClient().getTypeClient().getLibelleTypeClient())+1);

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
