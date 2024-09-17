package com.ged.service.titresciel;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.DepositaireDto;
import com.ged.dto.titresciel.DepositaireDto;
import com.ged.entity.titresciel.Depositaire;
import com.ged.entity.titresciel.Depositaire;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DepositaireService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<DepositaireDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Depositaire afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(DepositaireDto DepositaireDto);
    ResponseEntity<Object> modifier(DepositaireDto DepositaireDto);
    ResponseEntity<Object> supprimer(Long id);
}
