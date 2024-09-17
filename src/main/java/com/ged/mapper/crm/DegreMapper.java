package com.ged.mapper.crm;

import com.ged.dto.crm.DegreDto;
import com.ged.entity.crm.Degre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DegreMapper {
    public DegreDto deDegre(Degre degre)
    {
        if(degre == null)
            return null;
        DegreDto degreDto = new DegreDto();
        BeanUtils.copyProperties(degre, degreDto);
        return degreDto;
    }

    public Degre deDegreDto(DegreDto degreDto)
    {
        Degre degre = new Degre();
        BeanUtils.copyProperties(degreDto, degre);
        return degre;
    }
}
