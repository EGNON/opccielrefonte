package com.ged.service.standard;

import com.ged.dto.standard.TempsDto;
import com.ged.entity.standard.Temps;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TempsService {
    boolean existsByLibelle(String libelle);

    Page<TempsDto> afficherTempss(int page, int size);
    List<TempsDto> afficherTous();
    Temps afficherTempsSelonId(long idTemps);
    TempsDto afficherTemps(long idTemps);
    TempsDto creerTemps(TempsDto tempsDto);
    TempsDto modifierTemps(TempsDto tempsDto);
    void supprimerTemps(long idTemps);
}
