package com.ged.service.opcciel;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.SocieteDeGestionDto;
import com.ged.entity.opcciel.SocieteDeGestion;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SocieteDeGestionService {
    DataTablesResponse<SocieteDeGestionDto> afficherTous(DatatableParameters parameters);
    Page<SocieteDeGestionDto> afficherTousParPage(int page, int size);
    List<SocieteDeGestionDto> afficherTous();
    SocieteDeGestion afficherSelonId(long id);
    SocieteDeGestionDto afficher(long id);
    SocieteDeGestionDto creer(SocieteDeGestionDto SocieteDeGestionDto);
    SocieteDeGestionDto modifier(SocieteDeGestionDto SocieteDeGestionDto);
    void supprimer(long id);
}
