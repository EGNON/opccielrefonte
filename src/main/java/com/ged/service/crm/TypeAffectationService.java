package com.ged.service.crm;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.TypeAffectationDto;
import com.ged.entity.crm.TypeAffectation;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TypeAffectationService {
    DataTablesResponse<TypeAffectationDto> afficherTous(DatatableParameters parameters);
    Page<TypeAffectationDto> afficherTousParPage(int page, int size);
    List<TypeAffectationDto> afficherTous();
    TypeAffectation afficherSelonId(long id);
    TypeAffectationDto afficher(long id);
    TypeAffectationDto creer(TypeAffectationDto TypeAffectationDto);
    TypeAffectationDto modifier(TypeAffectationDto TypeAffectationDto);
    void supprimer(long id);
}
