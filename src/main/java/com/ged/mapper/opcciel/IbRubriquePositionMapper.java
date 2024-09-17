package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.IbRubriquePositionDto;
import com.ged.entity.opcciel.comptabilite.IbRubriquePosition;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class IbRubriquePositionMapper {
    private final IbMapper ibMapper;

    public IbRubriquePositionMapper(IbMapper ibMapper) {
        this.ibMapper = ibMapper;
    }

    public IbRubriquePositionDto deIbRubriquePosition(IbRubriquePosition IbRubriquePosition)
    {
        IbRubriquePositionDto IbRubriquePositionDto = new IbRubriquePositionDto();
        BeanUtils.copyProperties(IbRubriquePosition, IbRubriquePositionDto);
       if(IbRubriquePosition.getIb()!=null){
           IbRubriquePositionDto.setIb(ibMapper.deIb(IbRubriquePosition.getIb()));
       }
        return IbRubriquePositionDto;
    }

    public IbRubriquePosition deIbRubriquePositionDto(IbRubriquePositionDto IbRubriquePositionDto)
    {
        IbRubriquePosition IbRubriquePosition = new IbRubriquePosition();
        BeanUtils.copyProperties(IbRubriquePositionDto, IbRubriquePosition);
        return IbRubriquePosition;
    }
}
