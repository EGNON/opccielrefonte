package com.ged.service.titresciel;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.TableauAmortissementDto;
import com.ged.dto.titresciel.TableauAmortissementDto;
import com.ged.entity.titresciel.CleTableauAmortissement;
import com.ged.entity.titresciel.TableauAmortissement;
import com.ged.entity.titresciel.TableauAmortissement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TableauAmortissementService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TableauAmortissementDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    TableauAmortissement afficherSelonId(CleTableauAmortissement id);
    ResponseEntity<Object> afficher(CleTableauAmortissement id);
    ResponseEntity<Object> creer(TableauAmortissementDto TableauAmortissementDto);
    ResponseEntity<Object> modifier(TableauAmortissementDto TableauAmortissementDto);
    ResponseEntity<Object> supprimer(CleTableauAmortissement id);
}
