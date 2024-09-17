package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.entity.opcciel.SeanceOpcvm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SeanceOpcvmMapper {

    public SeanceOpcvmMapper() {
    }

    public SeanceOpcvmDto deSeanceOpcvm(SeanceOpcvm seanceOpcvm)
    {
        if(seanceOpcvm == null)
            return  null;
        SeanceOpcvmDto seanceOpcvmDto = new SeanceOpcvmDto();
        BeanUtils.copyProperties(seanceOpcvm, seanceOpcvmDto);
        return seanceOpcvmDto;
    }

    public SeanceOpcvm deSeanceOpcvmDto(SeanceOpcvmDto seanceOpcvmDto)
    {
        SeanceOpcvm seanceOpcvm = new SeanceOpcvm();
        BeanUtils.copyProperties(seanceOpcvmDto, seanceOpcvm);
        return seanceOpcvm;
    }
}
