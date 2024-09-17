package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.QualiteTitreDto;
import com.ged.entity.titresciel.QualiteTitre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class QualiteTitreMapper {
    private final ClasseTitreMapper classeTitreMapper;

    public QualiteTitreMapper(ClasseTitreMapper classeTitreMapper) {
        this.classeTitreMapper = classeTitreMapper;
    }

    public QualiteTitreDto deQualiteTitre(QualiteTitre QualiteTitre)
    {
        if(QualiteTitre == null)
            return null;
        QualiteTitreDto QualiteTitreDto = new QualiteTitreDto();
        BeanUtils.copyProperties(QualiteTitre, QualiteTitreDto);
        QualiteTitreDto.setClasseTitre(classeTitreMapper.deClasseTitre(QualiteTitre.getClasseTitre()));
        return QualiteTitreDto;
    }

    public QualiteTitre deQualiteTitreDto(QualiteTitreDto QualiteTitreDTO)
    {
        QualiteTitre QualiteTitre = new QualiteTitre();
        BeanUtils.copyProperties(QualiteTitreDTO, QualiteTitre);
        return QualiteTitre;
    }
}
