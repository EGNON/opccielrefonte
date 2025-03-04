package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.response.ConsultattionEcritureRes;
import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.mapper.standard.PersonneMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationMapper {
    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final PersonneMapper personneMapper;
    private final TransactionMapper transactionMapper;

    public OperationMapper(OpcvmMapper opcvmMapper, NatureOperationMapper natureOperationMapper, PersonneMapper personneMapper, TransactionMapper transactionMapper) {
        this.opcvmMapper = opcvmMapper;
        this.natureOperationMapper = natureOperationMapper;
        this.personneMapper = personneMapper;
        this.transactionMapper = transactionMapper;
    }

    public ConsultattionEcritureRes deEntite(Operation operation) {
        if(operation == null) {
            return null;
        }
        ConsultattionEcritureRes ecritureRes = new ConsultattionEcritureRes();
        BeanUtils.copyProperties(operation, ecritureRes);
        ecritureRes.setOpcvm(operation.getOpcvm());
        ecritureRes.setNatureOperation(operation.getNatureOperation());
        ecritureRes.setActionnaire(operation.getActionnaire());
        ecritureRes.setTransaction(operation.getTransaction());
        return ecritureRes;
    }

    public OperationDto deOperation(Operation operation) {
        if(operation == null) {
            return null;
        }
        OperationDto operationDto = new OperationDto();
        BeanUtils.copyProperties(operation, operationDto);
        operationDto.setOpcvm(opcvmMapper.deOpcvm(operation.getOpcvm()));
        operationDto.setNatureOperation(natureOperationMapper.deNatureOperation(operation.getNatureOperation()));
        operationDto.setActionnaire(personneMapper.dePersonne(operation.getActionnaire()));
        operationDto.setTransaction(transactionMapper.deTransaction(operation.getTransaction()));
        return operationDto;
    }

    public Operation deOperationDto(OperationDto operationDto) {
        if(operationDto == null) {
            return null;
        }
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDto, operation);
        operation.setOpcvm(opcvmMapper.deOpcvmDto(operationDto.getOpcvm()));
        operation.setNatureOperation(natureOperationMapper.deNatureOperationDto(operationDto.getNatureOperation()));
        operation.setActionnaire(personneMapper.dePersonneDto(operationDto.getActionnaire()));
        operation.setTransaction(transactionMapper.deTransactionDto(operationDto.getTransaction()));
        return operation;
    }
}
