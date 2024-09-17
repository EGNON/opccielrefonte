package com.ged.service.crm;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.AffectationDto;
import com.ged.entity.crm.Affectation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AffectationService {
    DataTablesResponse<AffectationDto> afficherTous(DatatableParameters parameters);
    Page<AffectationDto> afficherTousParPage(int page, int size);
    List<AffectationDto> afficherTous();
    List<AffectationDto> afficherSelonPersonnel(long idPersonne);
    Affectation afficherSelonId(Long id);
    AffectationDto afficher(Long id);
    AffectationDto creer(AffectationDto affectationDto);
    AffectationDto modifier(AffectationDto affectationDto);
    void supprimer(Long id);
}
