package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.entity.opcciel.comptabilite.Operation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationMapper {
    public OperationDto deOperation(Operation operation) {
        if(operation == null) {
            return null;
        }
        OperationDto operationDto = new OperationDto();
        BeanUtils.copyProperties(operation, operationDto);

        return operationDto;
    }

    public Operation deOperationDto(OperationDto operationDto) {
        if(operationDto == null) {
            return null;
        }
        Operation operation = new Operation();
        BeanUtils.copyProperties(operationDto, operation);

        return operation;
    }
}
