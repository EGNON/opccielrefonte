package com.ged.projection;

import jxl.write.DateTime;

import java.util.Date;

public interface FicheKYCProjection {
    String getNom();
    String getPrenom();
    Double getAutresRevenus();
    String getPeriodicite();
    String getStatutMatrimonial();
    Integer getNbrEnfant();
    Integer getNbrPersonneACharge();
    String getNomEmployeur();
    String getAdressePostaleEmp();
    String getAdresseGeoEmp();
    String getLibelleSecteur();
    String getTelEmp();
    String getEmailEmp();
    String getNomPere();
    String getPrenomsPere();
    Date getDateNaissancePere();
    String getPaysPere();
    String getNomMere();
    String getPrenomsMere();
    Date getDateNaissanceMere();
    String getPaysMere();
    String getNomConjoint();
    String getPrenomConjoint();
    Date getDateNaissanceConjoint();
    String getPaysConjoint();
    String getOrigineFonds();
    String getTransactionEnvisagee();
    String getImmobilier();
    String getAutresBiens();
    Double getSurfaceTotale();
    Double getSalaire();


}
