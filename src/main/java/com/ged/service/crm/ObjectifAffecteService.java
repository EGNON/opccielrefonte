package com.ged.service.crm;

import com.ged.dto.crm.ObjectifAffecteDto;
import com.ged.dto.crm.ObjectifAffecteEtatDto;
import com.ged.entity.crm.CleObjectifAffecte;
import com.ged.entity.crm.ObjectifAffecte;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ObjectifAffecteService {
    Page<ObjectifAffecteDto> afficherObjectifAffectes(int page, int size);
    List<ObjectifAffecteEtatDto> afficherSelonPersonnelEtPeriodicite(long idPersonne, long idPeriodicite);
    ObjectifAffecte afficherObjectifAffecteSelonId(CleObjectifAffecte idObjectifAffecte);
    ObjectifAffecteDto creerObjectifAffecte(ObjectifAffecteDto objectifAffecteDto);
    ObjectifAffecteDto modifierObjectifAffecte(ObjectifAffecteDto objectifAffecteDto);
    void supprimerObjectifAffecte(CleObjectifAffecte idObjectifAffecte);
    void supprimerSelonIdAffectation(Long id);
}
