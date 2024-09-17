
package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.JournalDto;
import com.ged.entity.opcciel.comptabilite.Journal;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface JournalService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<JournalDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Journal afficherSelonId(String codeJournal);
    ResponseEntity<Object> afficher(String codeJournal);
    ResponseEntity<Object> creer(JournalDto JournalDto);
    ResponseEntity<Object> modifier(JournalDto JournalDto);
    ResponseEntity<Object> supprimer(String codeJournal);
}
