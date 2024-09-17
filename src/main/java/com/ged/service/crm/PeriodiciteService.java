package com.ged.service.crm;

import com.ged.dto.standard.PeriodiciteDto;
import com.ged.entity.standard.Periodicite;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PeriodiciteService{
    Boolean existByLibelle(String libelle);
    Page<PeriodiciteDto> afficherPeriodicites(int page, int size);
    List<PeriodiciteDto> afficherPeriodicites();
    Periodicite afficherPeriodiciteSelonId(long idPeriodicite);
    PeriodiciteDto afficherPeriodicite(long idPeriodicite);
    PeriodiciteDto rechercherPeriodiciteParLibelle(String libelle);
    PeriodiciteDto creerPeriodicite(PeriodiciteDto periodiciteDto);
    PeriodiciteDto modifierPeriodicite(PeriodiciteDto periodiciteDto);
    void supprimerPeriodicite(long idPeriodicite);
}
