package com.ged.mapper.standard;

import com.ged.dto.standard.ProfilCommissionSousRachDto;
import com.ged.entity.standard.ProfilCommissionSousRach;
import com.ged.mapper.opcciel.OpcvmMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProfilCommissionSousRachMapper {
    private final OpcvmMapper opcvmMapper;

    public ProfilCommissionSousRachMapper(OpcvmMapper opcvmMapper) {
        this.opcvmMapper = opcvmMapper;
    }


    public ProfilCommissionSousRachDto deProfilCommissionSousRach(ProfilCommissionSousRach ProfilCommissionSousRach)
    {
        ProfilCommissionSousRachDto ProfilCommissionSousRachDto = new ProfilCommissionSousRachDto();
        BeanUtils.copyProperties(ProfilCommissionSousRach, ProfilCommissionSousRachDto);
        if(ProfilCommissionSousRach.getOpcvm() != null) ProfilCommissionSousRachDto.setOpcvm(opcvmMapper.deOpcvm(ProfilCommissionSousRach.getOpcvm()));
        return ProfilCommissionSousRachDto;
    }

    public ProfilCommissionSousRach deProfilCommissionSousRachDto(ProfilCommissionSousRachDto ProfilCommissionSousRachDto)
    {
        ProfilCommissionSousRach ProfilCommissionSousRach = new ProfilCommissionSousRach();
        BeanUtils.copyProperties(ProfilCommissionSousRachDto, ProfilCommissionSousRach);
        return ProfilCommissionSousRach;
    }
}
