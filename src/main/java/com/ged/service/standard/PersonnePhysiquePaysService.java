package com.ged.service.standard;

import com.ged.dto.standard.PersonnePhysiquePaysDto;
import com.ged.dto.standard.PersonnePhysiquePaysDtoEJ;
import com.ged.entity.standard.ClePersonnePhysiquePays;
import com.ged.entity.standard.PersonnePhysiquePays;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonnePhysiquePaysService{
    Page<PersonnePhysiquePaysDto> afficherPersonnePhysiquePays(int page, int size);
    PersonnePhysiquePays afficherPersonnePhysiquePaysSelonId(ClePersonnePhysiquePays idPersonnePhysiquePays);
    List<PersonnePhysiquePaysDto> afficherSelonPersonnePhysique(long idPersonne);
    PersonnePhysiquePaysDto creerPersonnePhysiquePays(PersonnePhysiquePaysDto personnePhysiquePaysDto);
    PersonnePhysiquePaysDtoEJ creerPersonnePhysiquePaysEJ(PersonnePhysiquePaysDtoEJ personnePhysiquePaysDtoEJ);
    PersonnePhysiquePaysDto modifierPersonnePhysiquePays(PersonnePhysiquePaysDto personnePhysiquePaysDto);
    void supprimerPersonnePhysiquePays(ClePersonnePhysiquePays idPersonnePhysiquePays);
    void supprimerSelonPersonne(long idPersonne);
}
