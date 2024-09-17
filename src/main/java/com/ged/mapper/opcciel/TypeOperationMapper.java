package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.TypeOperationDto;
import com.ged.entity.opcciel.comptabilite.TypeOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeOperationMapper {
    public TypeOperationDto deTypeOperation(TypeOperation typeOperation)
    {
        TypeOperationDto typeOperationDto = new TypeOperationDto();
        BeanUtils.copyProperties(typeOperation, typeOperationDto);
        return typeOperationDto;
    }

    public TypeOperation deTypeOperationDto(TypeOperationDto typeOperationDto)
    {
        TypeOperation typeOperation = new TypeOperation();
        BeanUtils.copyProperties(typeOperationDto, typeOperation);
        return typeOperation;
    }
}
