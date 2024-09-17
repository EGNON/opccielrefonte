package com.ged.mapper.standard;

import com.ged.dto.standard.QualiteDto;
import com.ged.entity.standard.Qualite;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class QualiteMapper {
    public QualiteDto deQualite(Qualite qualite)
    {
        if(qualite == null)
            return null;
        QualiteDto qualiteDto = new QualiteDto();
        BeanUtils.copyProperties(qualite, qualiteDto);
        return qualiteDto;
    }

    public Qualite deQualiteDto(QualiteDto qualiteDto)
    {
        if(qualiteDto == null)
            return null;
        Qualite qualite= new Qualite();
        BeanUtils.copyProperties(qualiteDto, qualite);
        return qualite;
    }

}
