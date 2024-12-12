package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationRestitutionReliquatDto;
import com.ged.entity.opcciel.OperationRestitutionReliquat;
import com.ged.mapper.standard.PersonneMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationRestitutionReliquatMapper {
    private final OpcvmMapper opcvmMapper;
    private final PersonneMapper personneMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final TransactionMapper transactionMapper;

    public OperationRestitutionReliquatMapper(OpcvmMapper opcvmMapper, PersonneMapper personneMapper, NatureOperationMapper natureOperationMapper, TransactionMapper transactionMapper) {
        this.opcvmMapper = opcvmMapper;
        this.personneMapper = personneMapper;
        this.natureOperationMapper = natureOperationMapper;
        this.transactionMapper = transactionMapper;
    }

    public OperationRestitutionReliquatDto aEntite(OperationRestitutionReliquat operationRestitutionReliquat) {
        if(operationRestitutionReliquat == null) {
            return null;
        }
        OperationRestitutionReliquatDto operationRestitutionReliquatDto = new OperationRestitutionReliquatDto();
        BeanUtils.copyProperties(operationRestitutionReliquat, operationRestitutionReliquatDto);
        operationRestitutionReliquatDto.setOpcvm(opcvmMapper.deOpcvm(operationRestitutionReliquat.getOpcvm()));
        operationRestitutionReliquatDto.setActionnaire(personneMapper.dePersonne(operationRestitutionReliquat.getActionnaire()));
        operationRestitutionReliquatDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationRestitutionReliquat.getNatureOperation()));
        operationRestitutionReliquatDto.setTransaction(transactionMapper.deTransaction(operationRestitutionReliquat.getTransaction()));

        return operationRestitutionReliquatDto;
    }

    public OperationRestitutionReliquat deDto(OperationRestitutionReliquatDto operationRestitutionReliquatDto) {
        if(operationRestitutionReliquatDto == null) {
            return null;
        }
        OperationRestitutionReliquat operationRestitutionReliquat = new OperationRestitutionReliquat();
        BeanUtils.copyProperties(operationRestitutionReliquatDto, operationRestitutionReliquat);
        operationRestitutionReliquat.setOpcvm(opcvmMapper.deOpcvmDto(operationRestitutionReliquatDto.getOpcvm()));
        operationRestitutionReliquat.setActionnaire(personneMapper.dePersonneDto(operationRestitutionReliquatDto.getActionnaire()));
        operationRestitutionReliquat.setNatureOperation(natureOperationMapper.deNatureOperationDto(operationRestitutionReliquatDto.getNatureOperation()));
        operationRestitutionReliquat.setTransaction(transactionMapper.deTransactionDto(operationRestitutionReliquatDto.getTransaction()));

        return operationRestitutionReliquat;
    }
}
