package com.ged.service.standard;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.EmetteurDto;
import com.ged.entity.titresciel.Emetteur;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmetteurService {
    DataTablesResponse<EmetteurDto> afficherTous(DatatableParameters parameters);
    Page<EmetteurDto> afficherTousParPage(int page, int size);
    List<EmetteurDto> afficherTous();
    Emetteur afficherSelonId(long id);
    EmetteurDto afficher(long id);
    EmetteurDto creer(EmetteurDto EmetteurDto);
    EmetteurDto modifier(EmetteurDto EmetteurDto);
    void supprimer(long id);
}
