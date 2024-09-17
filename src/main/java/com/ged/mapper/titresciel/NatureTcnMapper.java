package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.NatureTcnDto;
import com.ged.entity.titresciel.NatureTcn;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NatureTcnMapper {
    public NatureTcnDto deNatureTcn(NatureTcn NatureTcn)
    {
        if(NatureTcn == null)
            return null;
        NatureTcnDto NatureTcnDto = new NatureTcnDto();
        BeanUtils.copyProperties(NatureTcn, NatureTcnDto);
        return NatureTcnDto;
    }

    public NatureTcn deNatureTcnDto(NatureTcnDto NatureTcnDTO)
    {
        NatureTcn NatureTcn = new NatureTcn();
        BeanUtils.copyProperties(NatureTcnDTO, NatureTcn);
        return NatureTcn;
    }
}
