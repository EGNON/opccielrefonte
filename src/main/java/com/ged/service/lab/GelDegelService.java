package com.ged.service.lab;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.lab.GelDegelDto;
import com.ged.entity.lab.GelDegel;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface GelDegelService {
    Page<GelDegelDto> afficherGelDegels(int page, int size);
    DataTablesResponse<GelDegelDto> afficherGelDegels(DatatableParameters parameters);
    GelDegel afficherGelDegelSelonId(long idGelDegel);
    GelDegel afficherGelDegelSelonEstGeleEtPersonne(boolean estGele,long idPersonne);
    GelDegelDto creerGelDegel(GelDegelDto gelDegelDto);
    GelDegelDto modifierGelDegel(GelDegelDto gelDegelDto);
    int modifierGelDegel(long idPersonne, LocalDateTime dateFin);
    void supprimerGelDegel(Long id);
}
