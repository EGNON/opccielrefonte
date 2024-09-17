package com.ged.mapper.standard;

import com.ged.dto.standard.AlerteDto;
import com.ged.entity.standard.Alerte;
import com.ged.mapper.crm.PeriodiciteMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AlerteMapper {
    private final TypePlanificationMapper typePlanificationMapper;
    private final PeriodiciteMapper periodiciteMapper;

    public AlerteMapper(TypePlanificationMapper typePlanificationMapper, PeriodiciteMapper periodiciteMapper) {
        this.typePlanificationMapper = typePlanificationMapper;
        this.periodiciteMapper = periodiciteMapper;
    }

    public AlerteDto deAlerte(Alerte alerte)
    {
        AlerteDto alerteDto = new AlerteDto();
        BeanUtils.copyProperties(alerte, alerteDto);
        if(alerte.getTypePlanification() != null) alerteDto.setTypePlanification(typePlanificationMapper.deTypePlanification(alerte.getTypePlanification()));
        if(alerte.getPeriodicite() != null) alerteDto.setPeriodicite(periodiciteMapper.dePeriodicite(alerte.getPeriodicite()));
        return alerteDto;
    }

    public Alerte deAlerteDto(AlerteDto alerteDto)
    {
        Alerte alerte = new Alerte();
        BeanUtils.copyProperties(alerteDto, alerte);
        return alerte;
    }
}
