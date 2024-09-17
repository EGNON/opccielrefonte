package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.ClassificationOPCDto;
import com.ged.entity.titresciel.ClassificationOPC;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClassificationOPCMapper {
    public ClassificationOPCDto deClassification(ClassificationOPC classification)
    {
        if(classification == null)
            return null;
        ClassificationOPCDto classificationOPCDto = new ClassificationOPCDto();
        BeanUtils.copyProperties(classification, classificationOPCDto);
        return classificationOPCDto;
    }

    public ClassificationOPC deClassificationDto(ClassificationOPCDto classificationOPCDTO)
    {
        if(classificationOPCDTO == null)
            return null;
        ClassificationOPC classification = new ClassificationOPC();
        BeanUtils.copyProperties(classificationOPCDTO, classification);
        return classification;
    }
}
