package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TypeActionDto;
import com.ged.entity.titresciel.TypeAction;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeActionMapper {
    public TypeActionDto deTypeAction(TypeAction typeAction) {
        if(typeAction == null) {
            return null;
        }
        TypeActionDto typeActionDto = new TypeActionDto();
        BeanUtils.copyProperties(typeAction, typeActionDto);
        return typeActionDto;
    }

    public TypeAction deTypeActionDto(TypeActionDto typeActionDto) {
        if(typeActionDto == null) {
            return null;
        }
        TypeAction typeAction = new TypeAction();
        BeanUtils.copyProperties(typeActionDto, typeAction);
        return typeAction;
    }
}
