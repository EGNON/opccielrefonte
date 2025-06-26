package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.reportings.FormuleParametre;
import com.ged.dto.opcciel.comptabilite.FormuleDto;
import com.ged.dto.opcciel.comptabilite.SoldeCompteDto;
import com.ged.dto.opcciel.comptabilite.SoldeCompteFormuleDto;
import com.ged.entity.opcciel.comptabilite.Formule;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Date;

public interface FormuleService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficher(int page, int size);

    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> afficherCump(Long idOpcvm,
                                        Long idTitre,
                                        LocalDateTime dateEstimation);
    ResponseEntity<Object> afficherQuantiteReelTitre(FormuleParametre formuleParametre);
    ResponseEntity<Object> derniereEcheance(FormuleParametre formuleParametre);
    ResponseEntity<Object> soldeCompteFormule(SoldeCompteFormuleDto soldeCompteFormuleDto);
    ResponseEntity<Object> soldeCompte(SoldeCompteDto soldeCompteDto);
    Formule afficherSelonId(Long id);
    ResponseEntity<Object> creer(FormuleDto formuleDto);
    ResponseEntity<Object> modifier(FormuleDto formuleDto);
    ResponseEntity<Object> supprimer(Long id);
}
