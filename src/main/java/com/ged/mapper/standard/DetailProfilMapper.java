package com.ged.mapper.standard;

import com.ged.dto.standard.DetailProfilDto;
import com.ged.entity.standard.DetailProfil;
import com.ged.mapper.opcciel.OpcvmMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DetailProfilMapper {
    private final OpcvmMapper opcvmMapper;

    public DetailProfilMapper(OpcvmMapper opcvmMapper) {
        this.opcvmMapper = opcvmMapper;
    }


    public DetailProfilDto deDetailProfil(DetailProfil DetailProfil)
    {
        DetailProfilDto DetailProfilDto = new DetailProfilDto();
        BeanUtils.copyProperties(DetailProfil, DetailProfilDto);
        if(DetailProfil.getOpcvm() != null) DetailProfilDto.setOpcvm(opcvmMapper.deOpcvm(DetailProfil.getOpcvm()));
        return DetailProfilDto;
    }

    public DetailProfil deDetailProfilDto(DetailProfilDto DetailProfilDto)
    {
        DetailProfil DetailProfil = new DetailProfil();
        BeanUtils.copyProperties(DetailProfilDto, DetailProfil);
        return DetailProfil;
    }
}
