package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.TransactionDto;
import com.ged.entity.opcciel.comptabilite.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TransactionMapper {
    public TransactionDto deTransaction(Transaction transaction)
    {
        if(transaction == null)
            return null;
        TransactionDto transactionDto = new TransactionDto();
        BeanUtils.copyProperties(transaction, transactionDto);
        return transactionDto;
    }

    public Transaction deTransactionDto(TransactionDto transactionDto)
    {
        if(transactionDto == null)
            return null;
        Transaction transaction = new Transaction();
        BeanUtils.copyProperties(transactionDto, transaction);
        return transaction;
    }
}
