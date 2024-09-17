package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.PersonneMorale;
import com.ged.entity.titresciel.ClasseTitre;
import com.ged.entity.titresciel.Place;

public interface TarificationProjection  {

    Long getIdTarificationOrdinaire() ;


    String getCodeRole();

    ClasseTitre getClasseTitre() ;

    Place getPlace() ;

    PersonneMorale getDepositaire();

    Opcvm getOpcvm() ;

    PersonneMorale getRegistraire();

    double getBorneInferieur() ;

    double getBorneSuperieur();

    double getTaux();

    double getForfait() ;

}
