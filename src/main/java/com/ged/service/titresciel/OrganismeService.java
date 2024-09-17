package com.ged.service.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.OrganismeDto;
import com.ged.entity.titresciel.Organisme;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface OrganismeService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<OrganismeDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Organisme afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(OrganismeDto OrganismeDto);
    ResponseEntity<Object> modifier(OrganismeDto OrganismeDto);
    ResponseEntity<Object> supprimer(Long id);
}
