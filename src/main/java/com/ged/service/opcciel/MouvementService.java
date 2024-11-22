package com.ged.service.opcciel;

import com.ged.dto.opcciel.comptabilite.MouvementDto;
import com.ged.entity.opcciel.comptabilite.Mouvement;
import org.springframework.http.ResponseEntity;

public interface MouvementService {
    ResponseEntity<Object> afficher(Long idMvt);
    Mouvement afficherSelondId(Long idMvt);
    ResponseEntity<Object> creer(MouvementDto mouvementDto);
    ResponseEntity<Object> modifier(MouvementDto mouvementDto);
    ResponseEntity<Object> supprimer(Long idMvt);
}
