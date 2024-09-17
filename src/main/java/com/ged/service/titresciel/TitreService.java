package com.ged.service.titresciel;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PersonnePhysiqueDto;
import com.ged.dto.titresciel.TitreDto;
import com.ged.entity.titresciel.Titre;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TitreService {
    ResponseEntity<Object> findBySymbolTitre(String symbolTitre);

    ResponseEntity<Object> afficherTous(DatatableParameters parameters);

    Page<TitreDto> afficherTousParPage(int page, int size);

    DataTablesResponse<TitreDto> afficherTous(String qualite, DatatableParameters parameters);

    ResponseEntity<Object> afficherTous();

    Titre afficherSelonId(Long id);

    ResponseEntity<Object> afficher(Long id);

    ResponseEntity<Object> creer(TitreDto TitreDto);

    ResponseEntity<Object> modifier(TitreDto TitreDto);

    void supprimer(Long id);
}
