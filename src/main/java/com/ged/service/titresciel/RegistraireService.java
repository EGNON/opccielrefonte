package com.ged.service.titresciel;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.RegistraireDto;
import com.ged.entity.titresciel.Registraire;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RegistraireService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<RegistraireDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Registraire afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(RegistraireDto RegistraireDto);
    ResponseEntity<Object> modifier(RegistraireDto RegistraireDto);
    ResponseEntity<Object> supprimer(Long id);
}
