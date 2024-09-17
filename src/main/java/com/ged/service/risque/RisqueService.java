package com.ged.service.risque;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.DegreDto;
import com.ged.dto.risque.AlphaDto;
import com.ged.dto.risque.RatioSharpDto;
import com.ged.entity.crm.Degre;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RisqueService {
    ResponseEntity<Object> afficherAlpha(DatatableParameters parameters);
    List<AlphaDto> afficherAlpha(long idOpcvm, String anneeDebut, String anneeFin);
    List<RatioSharpDto> afficherRatioSharp(long idOpcvm, String anneeDebut, String anneeFin);
}
