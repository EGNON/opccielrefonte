package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.IbDto;
import com.ged.entity.opcciel.comptabilite.Ib;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface IbService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<IbDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object>  afficherTous();
    Ib afficherSelonId(String codeIb);
    ResponseEntity<Object>  afficher(String codeIb);
    ResponseEntity<Object>  creer(IbDto IbDto);
    ResponseEntity<Object>  modifier(IbDto IbDto);
    ResponseEntity<Object>  supprimer(String codeIb);
}
