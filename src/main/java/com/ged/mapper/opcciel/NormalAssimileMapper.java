package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.NormalAssimileDto;
import com.ged.entity.standard.NormalAssimile;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NormalAssimileMapper {
    public NormalAssimileDto deNormalAssimile(NormalAssimile normalAssimile)
    {
        NormalAssimileDto normalAssimileDto = new NormalAssimileDto();
        BeanUtils.copyProperties(normalAssimile, normalAssimileDto);
        return normalAssimileDto;
    }

    public NormalAssimile deNormalAssimileDto(NormalAssimileDto normalAssimileDto)
    {
        NormalAssimile normalAssimile = new NormalAssimile();
        BeanUtils.copyProperties(normalAssimileDto, normalAssimile);
        return normalAssimile;
    }
}
