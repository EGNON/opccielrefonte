package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.SystemeDinformationDto;
import com.ged.entity.standard.SystemeDinformation;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SystemeDinformationService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    List<SystemeDinformationDto> afficherListe();
    ResponseEntity<Object> afficherSystemeDinformations(int page, int size);
    ResponseEntity<Object> afficherSystemeDinformation(Long id);
    SystemeDinformation afficherSystemeDinformationSelonId(Long idSystemeDinformation);
    ResponseEntity<Object> creerSystemeDinformation(SystemeDinformationDto systemeDinformationDto);
    ResponseEntity<Object> modifierSystemeDinformation(SystemeDinformationDto systemeDinformationDto);
    ResponseEntity<Object> supprimerSystemeDinformation(Long id);
}
