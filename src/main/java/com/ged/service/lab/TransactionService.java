package com.ged.service.lab;

import com.ged.datatable.DatatableParameters;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters,long critere);
    ResponseEntity<Object> afficherTous();
}
