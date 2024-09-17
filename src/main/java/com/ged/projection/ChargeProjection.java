package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

public interface ChargeProjection {

     String getCodeCharge();

     String getDesignation() ;
     Double getMontant() ;

     String getTypeCharge() ;

     Long getIdCharge();

     NatureOperation getNatureOperation() ;
     Opcvm getOpcvm() ;

     boolean isAppliquerSurActifNet() ;
}
