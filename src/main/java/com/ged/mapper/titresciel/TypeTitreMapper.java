package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TypeTitreDto;
import com.ged.entity.titresciel.TypeTitre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeTitreMapper {
    private final ClasseTitreMapper classeTitreMapper;

    public TypeTitreMapper(ClasseTitreMapper classeTitreMapper) {
        this.classeTitreMapper = classeTitreMapper;
    }

    public TypeTitreDto deTypeTitre(TypeTitre typeTitre)
    {
        if(typeTitre == null)
        {
            return null;
        }
        TypeTitreDto typeTitreDto = new TypeTitreDto();
        BeanUtils.copyProperties(typeTitre, typeTitreDto);
        typeTitreDto.setClasseTitre(classeTitreMapper.deClasseTitre(typeTitre.getClasseTitre()));
        return typeTitreDto;
    }

    public TypeTitre deTypeTitreDto(TypeTitreDto typeTitreDto)
    {
        TypeTitre typeTitre = new TypeTitre();
        BeanUtils.copyProperties(typeTitreDto, typeTitre);
        return typeTitre;
    }
}
