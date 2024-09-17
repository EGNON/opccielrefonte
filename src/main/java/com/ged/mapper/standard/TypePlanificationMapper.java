package com.ged.mapper.standard;

import com.ged.dto.standard.TypePlanificationDto;
import com.ged.entity.standard.TypePlanification;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypePlanificationMapper {
    public TypePlanificationDto deTypePlanification(TypePlanification typePlanification)
    {
        TypePlanificationDto typePlanificationDto = new TypePlanificationDto();
        BeanUtils.copyProperties(typePlanification, typePlanificationDto);
        return typePlanificationDto;
    }

    public TypePlanification deTypePlanificationDto(TypePlanificationDto typePlanificationDto)
    {
        TypePlanification typePlanification = new TypePlanification();
        BeanUtils.copyProperties(typePlanificationDto, typePlanification);
        return typePlanification;
    }
}
