package com.ged.service.standard;

import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface StatistiqueService {
    ResponseEntity<Object> nbrePersonneParQualite(Optional<String> libelleQualite);
    ResponseEntity<Object> nbreRDVEnCours();
    ResponseEntity<Object> nbreRDVParMois(String annee);
    ResponseEntity<Object> niveauEvolutionObjectif(String annee);
}
