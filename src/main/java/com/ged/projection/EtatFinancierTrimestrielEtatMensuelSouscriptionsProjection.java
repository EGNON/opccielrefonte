package com.ged.projection;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.comptabilite.NatureOperation;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface EtatFinancierTrimestrielEtatMensuelSouscriptionsProjection {

    Long getIdOpcvm();
    String getDesignationOpcvm();
    BigDecimal geTnbreSousPP();
    BigDecimal geTnbrePartSousPP();
    BigDecimal getVolumeSousPP();
    BigDecimal getNbreRachatPP();
    BigDecimal getNbrePartRachatPP();
    BigDecimal getVolumeRachatPP();
    BigDecimal getNbreTotalPorteurPartPP();
    BigDecimal getVolumeTotalDetenuPP();
    LocalDateTime getDateDebut();
    LocalDateTime getDateFin();

    BigDecimal getNbreSousBanquesPM();
    BigDecimal getNbrePartSousBanquesPM();
    BigDecimal getVolumeSousBanquesPM();
    BigDecimal getNbreRachatBanquesPM();
    BigDecimal getNbrePartRachatBanquesPM();
    BigDecimal getVolumeRachatBanquesPM();
    BigDecimal getNbreTotalPorteurPartBanquesPM();
    BigDecimal getVolumeTotalDetenuBanquesPM();

    BigDecimal getNbreSousSocieteFinancementPM();
    BigDecimal getNbrePartSousSocieteFinancementPM();
    BigDecimal getVolumeSousSocieteFinancementPM();
    BigDecimal getNbreRachatSocieteFinancementPM();
    BigDecimal getNbrePartRachatSocieteFinancementPM();
    BigDecimal getVolumeRachatSocieteFinancementPM();
    BigDecimal getNbreTotalPorteurPartSocieteFinancementPM();
    BigDecimal getVolumeTotalDetenuSocieteFinancementPM();

    BigDecimal getNbreSousCompaAssuranceReassurancePM();
    BigDecimal getNbrePartSousCompaAssuranceReassurancePM();
    BigDecimal getVolumeSousCompaAssuranceReassurancePM();
    BigDecimal getNbreRachatCompaAssuranceReassurancePM();
    BigDecimal getNbrePartRachatCompaAssuranceReassurancePM();
    BigDecimal getVolumeRachatCompaAssuranceReassurancePM();
    BigDecimal getNbreTotalPorteurPartCompaAssuranceReassurancePM();
    BigDecimal getVolumeTotalDetenuCompaAssuranceReassurancePM();

    BigDecimal getNbreSousOrganPrevoRetraitePM();
    BigDecimal getNbrePartSousOrganPrevoRetraitePM();
    BigDecimal getVolumeSousOrganPrevoRetraitePM();
    BigDecimal getNbreRachatOrganPrevoRetraitePM();
    BigDecimal getNbrePartRachatOrganPrevoRetraitePM();
    BigDecimal getVolumeRachatOrganPrevoRetraitePM();
    BigDecimal getNbreTotalPorteurPartOrganPrevoRetraitePM();
    BigDecimal getVolumeTotalDetenuOrganPrevoRetraitePM();

    BigDecimal getNbreSousOpcvmPM();
    BigDecimal getNbrePartSousOpcvmPM();
    BigDecimal getVolumeSousOpcvmPM();
    BigDecimal getNbreRachatOpcvmPM();
    BigDecimal getNbrePartRachatOpcvmPM();
    BigDecimal getVolumeRachatOpcvmPM();
    BigDecimal getNbreTotalPorteurPartOpcvmPM();
    BigDecimal getVolumeTotalDetenuOpcvmPM();

    BigDecimal getNbreSousSocieteBoursePM();
    BigDecimal getNbrePartSousSocieteBoursePM();
    BigDecimal getVolumeSousSocieteBoursePM();
    BigDecimal getNbreRachatSocieteBoursePM();
    BigDecimal getNbrePartRachatSocieteBoursePM();
    BigDecimal getVolumeRachatSocieteBoursePM();
    BigDecimal getNbreTotalPorteurPartSocieteBoursePM();
    BigDecimal getVolumeTotalDetenuSocieteBoursePM();
}
