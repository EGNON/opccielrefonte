package com.ged.service.standard;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PaysDto;
import com.ged.entity.standard.Pays;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PaysService{
    ResponseEntity<Object> afficherTous();

    Page<PaysDto> afficherPays(int page, int size);
    DataTablesResponse<PaysDto> afficherPaysSelonEstGafi(boolean estGafi, DatatableParameters parameters);
    List<PaysDto> afficherPaysSelonEstGafi(boolean estGafi);
    List<PaysDto> afficherListePays();
    Pays afficherPaysSelonId(long idPays);
    PaysDto rechercherPaysParLibelleFr(String libelleFr);
    PaysDto afficherPays(long idPays);
    PaysDto creerPays(PaysDto paysDto);
    PaysDto modifierPays(PaysDto paysDto);
    void supprimerPays(long idPays);
}
