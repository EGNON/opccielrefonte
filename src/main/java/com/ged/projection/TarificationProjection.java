package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
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

    Depositaire getDepositaire();

    Opcvm getOpcvm() ;

    Registraire getRegistraire();

    double getBorneInferieur() ;

    double getBorneSuperieur();

    double getTaux();

    double getForfait() ;

}
