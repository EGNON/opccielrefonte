package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.TransactionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.TransactionDto;
import com.ged.entity.opcciel.comptabilite.Transaction;
import com.ged.mapper.opcciel.TransactionMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.TransactionService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final TransactionDao transactionDao;
    private final TransactionMapper transactionMapper;

    public TransactionServiceImpl(TransactionDao TransactionDao, TransactionMapper TransactionMapper){

        this.transactionDao = TransactionDao;
        this.transactionMapper = TransactionMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"denomination");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Transaction> transactionPage;
            transactionPage = transactionDao.findAll(pageable);
            List<TransactionDto> content = transactionPage.getContent().stream().map(transactionMapper::deTransaction).collect(Collectors.toList());
            DataTablesResponse<TransactionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)transactionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)transactionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Transactions par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public Page<TransactionDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"denomination");
            List<TransactionDto> TransactionDtos = transactionDao.findAll().stream().map(transactionMapper::deTransaction).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Transactions ",
                    HttpStatus.OK,
                    TransactionDtos);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }

    }

    @Override
    public Transaction afficherSelonId(Long id) {
        return transactionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Transaction.class, "ID",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Transaction dont ID = " + id,
                    HttpStatus.OK,
                    transactionMapper.deTransaction(afficherSelonId(id)));
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }

    }

    @Override
    public ResponseEntity<Object> creer(TransactionDto TransactionDto) {
        try {
            Transaction  Transaction = transactionMapper.deTransactionDto(TransactionDto);
            Transaction = transactionDao.save(Transaction);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    transactionMapper.deTransaction(Transaction));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TransactionDto transactionDto) {
        try {
            if(!transactionDao.existsById(transactionDto.getIdTransaction()))
                throw  new EntityNotFoundException(Transaction.class, "ID", transactionDto.getIdTransaction().toString());
            Transaction transaction = transactionMapper.deTransactionDto(transactionDto);
            transaction= transactionDao.save(transaction);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    transactionMapper.deTransaction(transaction));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        try {
            transactionDao.deleteById(id);
            return ResponseHandler.generateResponse(
                    "Suppression effectuée avec succès",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
