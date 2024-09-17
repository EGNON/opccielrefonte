package com.ged.service.standard;

import com.ged.dto.standard.TypePlanificationDto;
import com.ged.entity.standard.TypePlanification;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TypePlanificationService {
    Boolean existByLibelle(String libelle);
    Page<TypePlanificationDto> afficherTypePlanifications(int page, int size);
    List<TypePlanificationDto> afficherTypePlanificationsTous();
    TypePlanification afficherTypePlanificationSelonId(long idTypePlanification);
    TypePlanificationDto afficherTypePlanification(long idTypePlanification);
    TypePlanificationDto creerTypePlanification(TypePlanificationDto typePlanificationDto);
    TypePlanificationDto modifierTypePlanification(TypePlanificationDto typePlanificationDto);
    void supprimerTypePlanification(long idTypePlanification);
}
