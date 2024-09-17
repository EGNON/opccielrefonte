package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.IbDto;
import com.ged.entity.opcciel.comptabilite.Ib;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class IbMapper {
    private final TypeIbMapper typeIbMapper;

    public IbMapper(TypeIbMapper typeIbMapper) {
        this.typeIbMapper = typeIbMapper;
    }

    public IbDto deIb(Ib Ib)
    {
        IbDto IbDto = new IbDto();
        BeanUtils.copyProperties(Ib, IbDto);
        if(Ib.getTypeIb()!=null){
            IbDto.setTypeIb(typeIbMapper.deTypeIb(Ib.getTypeIb()));
        }
        return IbDto;
    }

    public Ib deIbDto(IbDto IbDto)
    {
        Ib Ib = new Ib();
        BeanUtils.copyProperties(IbDto, Ib);
        return Ib;
    }
}
