package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.TransactionDto;
import com.ged.entity.opcciel.comptabilite.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<TransactionDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    Transaction afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(TransactionDto TransactionDto);
    ResponseEntity<Object> modifier(TransactionDto TransactionDto);
    ResponseEntity<Object> supprimer(Long id);
}
