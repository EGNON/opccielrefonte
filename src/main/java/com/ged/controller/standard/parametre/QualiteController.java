package com.ged.controller.standard.parametre;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.QualiteDto;
import com.ged.service.standard.QualiteService;
import jakarta.annotation.PostConstruct;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/qualites")
public class QualiteController {
    private final QualiteService qualiteService;

    public QualiteController(QualiteService qualiteService) {
        this.qualiteService = qualiteService;
    }

    @PostMapping("tous/{phOrPm}")
    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public ResponseEntity<Object> afficherTous(@PathVariable Boolean phOrPm)
    {
        return qualiteService.afficherTous(phOrPm);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public Page<QualiteDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                         @RequestParam(value = "size",defaultValue = "10") int size){
        return qualiteService.afficherQualites(page,size);
    }

    @GetMapping("qualite/{qualite}")
    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public QualiteDto afficherSelonLibelle(@PathVariable("qualite") String qualite)
    {
        return qualiteService.afficherSelonLibelle(qualite);
    }

    @PostMapping("/datatable/list")
    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public DataTablesResponse<QualiteDto> datatableList(@RequestBody DatatableParameters params)
    {
        return qualiteService.afficherTous(params);
    }

    @PostMapping("/datatable/list-ph/{idPersonne}")
    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public ResponseEntity<Object> datatableListPh(@PathVariable Long idPersonne)
    {
        return qualiteService.afficherTousPh(idPersonne);
    }

    @PostMapping("/datatable/list-pm/{idPersonne}")
    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public ResponseEntity<Object> datatableListPm(@PathVariable Long idPersonne)
    {
        return qualiteService.afficherTousPm(idPersonne);
    }

    @GetMapping("/{idQualite}")
    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public QualiteDto afficher(@PathVariable("idQualite") long idQualite) {
        return qualiteService.afficherQualite(idQualite);
    }
//    @GetMapping("/{libelleQualite}")
//    public QualiteDto afficherSelonLibelle(@PathVariable("libelleQualite") String libelleQualite) {
//        return qualiteService.rechercherQualiteParLibelle(libelleQualite);
//    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public QualiteDto ajouter(@RequestBody QualiteDto qualiteDto)
    {
        return qualiteService.creerQualite(qualiteDto);
    }

    @PostConstruct
    public void generatePh() {
        String[] qualites = {"COMMISSAIRES AUX COMPTES", "DIRIGEANTS", "CONSEILS D'ADMINISTRATIONS",
                "GESTIONNAIRES", "SIGNATAIRES"};
        for (String qualite: qualites) {
            if(!qualiteService.existByLibelle(qualite))
            {
                QualiteDto qualiteDto = new QualiteDto();
                qualiteDto.setLibelleQualite(qualite);
                qualiteDto.setEstPH(true);
                qualiteDto.setEstPM(false);
                qualiteService.creerQualite(qualiteDto);
            }
        }
    }

    /*@PostConstruct
    public void generatePm() {
        String[] qualites = {"DEPOSITAIRE", "EMETTEUR", "REGISTRAIRE", "ORGANISME"};
        for (String qualite: qualites) {
            if(!qualiteService.existByLibelle(qualite))
            {
                QualiteDto qualiteDto = new QualiteDto();
                qualiteDto.setLibelleQualite(qualite);
                qualiteDto.setEstPH(false);
                qualiteDto.setEstPM(true);
                qualiteService.creerQualite(qualiteDto);
            }
        }
    }

    @PostConstruct
    public void generatePhPm() {
        String[] qualites = {"ACTIONNAIRE", "DISTRIBUTEUR", "PROSPECT"};
        for (String qualite: qualites) {
            if(!qualiteService.existByLibelle(qualite))
            {
                QualiteDto qualiteDto = new QualiteDto();
                qualiteDto.setLibelleQualite(qualite);
                qualiteDto.setEstPH(true);
                qualiteDto.setEstPM(true);
                qualiteService.creerQualite(qualiteDto);
            }
        }
    }*/

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public QualiteDto modifier(@PathVariable long id, @RequestBody QualiteDto qualiteDto){
        qualiteDto.setIdQualite(id);
        return qualiteService.modifierQualite(qualiteDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_QUALITE')")
    public void Supprimer(@PathVariable long id){
        qualiteService.supprimerQualite(id);
    }
}
