package com.ged.mapper.standard;

import com.ged.dto.standard.CommuneDto;
import com.ged.entity.standard.Commune;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CommuneMapper {
    private final DepartementMapper departementMapper;

    public CommuneMapper(DepartementMapper departementMapper) {
        this.departementMapper = departementMapper;
    }

    public CommuneDto deCommune(Commune commune)
    {
        CommuneDto communeDto = new CommuneDto();
        BeanUtils.copyProperties(commune, communeDto);
        communeDto.setDepartement(departementMapper.deDepartement(commune.getDepartement()));
        return communeDto;
    }

    public Commune deCommuneDto(CommuneDto communeDTO)
    {
        Commune commune = new Commune();
        BeanUtils.copyProperties(communeDTO, commune);
        return commune;
    }
}
