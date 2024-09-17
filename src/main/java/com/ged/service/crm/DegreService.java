package com.ged.service.crm;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.DegreDto;
import com.ged.entity.crm.Degre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DegreService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    List<DegreDto> afficherListe();
    ResponseEntity<Object> afficherDegres(int page, int size);
    ResponseEntity<Object> afficherDegre(Long id);
    Degre afficherDegreSelonId(Long idDegre);
    ResponseEntity<Object> rechercherDegreParLibelle(String libelle);
    ResponseEntity<Object> creerDegre(DegreDto degreDto);
    ResponseEntity<Object> modifierDegre(DegreDto degreDto);
    ResponseEntity<Object> supprimerDegre(Long id);
}
