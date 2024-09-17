package com.ged.service.lab;

import com.ged.dto.lab.TypeCritereDto;
import com.ged.entity.lab.TypeCritere;
import org.springframework.data.domain.Page;

public interface TypeCritereService  {
    Page<TypeCritereDto> afficherTypeCriteres(int page, int size);
    TypeCritere afficherTypeCritereSelonId(long idTypeCritere);
    TypeCritereDto creerTypeCritere(TypeCritereDto typeCritereDto);
    TypeCritereDto modifierTypeCritere(TypeCritereDto typeCritereDto);
    void supprimerTypeCritere(Long id);
}
