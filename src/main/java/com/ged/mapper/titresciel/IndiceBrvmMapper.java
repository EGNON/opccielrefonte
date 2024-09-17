package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.CleIndiceBrvmDto;
import com.ged.dto.titresciel.IndiceBrvmDto;
import com.ged.entity.titresciel.CleIndiceBrvm;
import com.ged.entity.titresciel.IndiceBrvm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class IndiceBrvmMapper {
    public IndiceBrvmDto deIndiceBrvm(IndiceBrvm indiceBrvm) {
        if(indiceBrvm == null)
            return null;
        IndiceBrvmDto indiceBrvmDto = new IndiceBrvmDto();
        BeanUtils.copyProperties(indiceBrvm, indiceBrvmDto);
        CleIndiceBrvmDto cleIndiceBrvmDto = new CleIndiceBrvmDto();
        cleIndiceBrvmDto.setDateIndice(indiceBrvm.getIdIndice().getDateIndice());
        cleIndiceBrvmDto.setLibelleIndice(indiceBrvm.getIdIndice().getLibelleIndice());
        indiceBrvmDto.setIdIndice(cleIndiceBrvmDto);
        return indiceBrvmDto;
    }

    public IndiceBrvm deIndiceBrvmDto(IndiceBrvmDto indiceBrvmDto) {
        if(indiceBrvmDto == null)
            return null;
        IndiceBrvm indiceBrvm = new IndiceBrvm();
        BeanUtils.copyProperties(indiceBrvmDto, indiceBrvm);
        CleIndiceBrvm cleIndiceBrvm = new CleIndiceBrvm();
        cleIndiceBrvm.setDateIndice(indiceBrvmDto.getIdIndice().getDateIndice());
        cleIndiceBrvm.setLibelleIndice(indiceBrvmDto.getIdIndice().getLibelleIndice());
        indiceBrvm.setIdIndice(cleIndiceBrvm);
        return indiceBrvm;
    }
}
