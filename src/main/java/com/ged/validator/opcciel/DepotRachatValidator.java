package com.ged.validator.opcciel;

import com.ged.dto.opcciel.DepotRachatDto;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DepotRachatValidator {
    public static List<String> validate(DepotRachatDto depotRachatDto) {
        List<String> errors = new ArrayList<>();
        if(depotRachatDto == null) {
            errors.add("Le libellé de l'opération est obligatoire");
            errors.add("veuillez saisir la référence.");
            errors.add("Veuillez choisir le mode VL.");
            errors.add("Veuillez choisir le distributeur.");
            errors.add("Veuillez choisir l'actionnaire.");
            errors.add("La saisie du montant souscrit est obligatoire.");
        }
        else {
            if(!StringUtils.hasLength(depotRachatDto.getLibelleOperation())) {
                errors.add("Le libellé de l'opération est obligatoire");
            }
            if(!StringUtils.hasLength(depotRachatDto.getReferencePiece())) {
                errors.add("veuillez saisir la référence.");
            }
            if(!StringUtils.hasLength(depotRachatDto.getModeVL())) {
                errors.add("Veuillez choisir le mode VL.");
            }
            if(depotRachatDto.getPersonne() == null) {
                errors.add("Veuillez choisir le distributeur.");
            }
            if(depotRachatDto.getActionnaire() == null) {
                errors.add("Veuillez choisir l'actionnaire.");
            }
            if(depotRachatDto.getMontantSouscrit() == null) {
                errors.add("La saisie du montant souscrit est obligatoire.");
            }
        }
        return errors;
    }
}
