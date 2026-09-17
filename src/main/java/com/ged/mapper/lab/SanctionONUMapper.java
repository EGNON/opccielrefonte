package com.ged.mapper.lab;

import com.ged.dto.lab.SanctionsONUDto;
import com.ged.dto.lab.TypeCritereDto;
import com.ged.entity.lab.SanctionsONU;
import com.ged.entity.lab.TypeCritere;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SanctionONUMapper {
    public SanctionsONUDto deSanctionsONU(SanctionsONU sanctionsONU)
    {
        SanctionsONUDto sanctionsONUDto = new SanctionsONUDto();
        BeanUtils.copyProperties(sanctionsONU, sanctionsONUDto);
        return sanctionsONUDto;
    }
    public SanctionsONU deSanctionsONUDto(SanctionsONUDto sanctionsONUDto)
    {
        SanctionsONU sanctionsONU = new SanctionsONU();
        BeanUtils.copyProperties(sanctionsONUDto, sanctionsONU);
        return sanctionsONU;
    }

}
