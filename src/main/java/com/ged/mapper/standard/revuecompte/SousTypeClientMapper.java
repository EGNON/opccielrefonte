package com.ged.mapper.standard.revuecompte;

import com.ged.dto.standard.revuecompte.SousTypeClientDto;
import com.ged.entity.standard.revuecompte.SousTypeClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SousTypeClientMapper {
    private final TypeClientMapper typeClientMapper;

    public SousTypeClientMapper(TypeClientMapper typeClientMapper) {
        this.typeClientMapper = typeClientMapper;
    }

    public SousTypeClientDto deSousTypeClient(SousTypeClient SousTypeClient)
    {
        SousTypeClientDto SousTypeClientDTO = new SousTypeClientDto();
        BeanUtils.copyProperties(SousTypeClient, SousTypeClientDTO);
        SousTypeClientDTO.setTypeClient(typeClientMapper.deTypeClient(SousTypeClient.getTypeClient()));
        return SousTypeClientDTO;
    }

    public SousTypeClient deSousTypeClientDto(SousTypeClientDto SousTypeClientDto)
    {
        SousTypeClient SousTypeClient = new SousTypeClient();
        BeanUtils.copyProperties(SousTypeClientDto, SousTypeClient);
        SousTypeClient.setTypeClient(typeClientMapper.deTypeClientDto(SousTypeClientDto.getTypeClient()));
        return SousTypeClient;
    }
}
