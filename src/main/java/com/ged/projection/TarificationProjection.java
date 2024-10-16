package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.Personne;
import com.ged.entity.standard.PersonneMorale;
import com.ged.entity.titresciel.ClasseTitre;
import com.ged.entity.titresciel.Depositaire;
import com.ged.entity.titresciel.Place;
import com.ged.entity.titresciel.Registraire;

public interface TarificationProjection  {

    Long getIdTarificationOrdinaire() ;


    String getCodeRole();

    ClasseTitre getClasseTitre() ;

    Place getPlace() ;

    Personne getDepositaire();

    Opcvm getOpcvm() ;

    Personne getRegistraire();

    double getBorneInferieur() ;

    double getBorneSuperieur();

    double getTaux();

    double getForfait() ;

}
