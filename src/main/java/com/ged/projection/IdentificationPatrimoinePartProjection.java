package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.Personne;
import com.ged.entity.titresciel.ClasseTitre;
import com.ged.entity.titresciel.Place;
import com.ged.entity.titresciel.Registraire;

import java.math.BigDecimal;

public interface IdentificationPatrimoinePartProjection {
   String getTitre();
    String getLibelleTitre ();
    String getNumero();
    String getLibelle_numero();
    String getPetit_Numero();
    String getLibelle_Petit_numero();
    String getType ();
    String getValeur();
    BigDecimal getNombre();
}
