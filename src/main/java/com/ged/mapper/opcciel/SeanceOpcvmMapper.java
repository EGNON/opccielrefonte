package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.entity.opcciel.SeanceOpcvm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SeanceOpcvmMapper {
    private final OpcvmMapper opcvmMapper;
    public SeanceOpcvmMapper(OpcvmMapper opcvmMapper) {
        this.opcvmMapper = opcvmMapper;
    }

    public SeanceOpcvmDto deSeanceOpcvm(SeanceOpcvm seanceOpcvm)
    {
        if(seanceOpcvm == null)
            return  null;
        SeanceOpcvmDto seanceOpcvmDto = new SeanceOpcvmDto();
        BeanUtils.copyProperties(seanceOpcvm, seanceOpcvmDto);
        if(seanceOpcvm.getOpcvm()!=null)
            seanceOpcvmDto.setOpcvm(opcvmMapper.deOpcvm(seanceOpcvm.getOpcvm()));
        return seanceOpcvmDto;
    }

    public SeanceOpcvm deSeanceOpcvmDto(SeanceOpcvmDto seanceOpcvmDto)
    {
        SeanceOpcvm seanceOpcvm = new SeanceOpcvm();
        BeanUtils.copyProperties(seanceOpcvmDto, seanceOpcvm);
        if(seanceOpcvmDto.getOpcvm()!=null)
            seanceOpcvm.setOpcvm(opcvmMapper.deOpcvmDto(seanceOpcvmDto.getOpcvm()));
        return seanceOpcvm;
    }
}
