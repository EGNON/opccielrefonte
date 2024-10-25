package com.ged.mapper.standard;

import com.ged.dto.standard.DepartementDto;
import com.ged.entity.standard.Departement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DepartementMapper {
    public DepartementDto deDepartement(Departement departement)
    {
        if(departement == null) {
            return null;
        }
        DepartementDto departementDto = new DepartementDto();
        BeanUtils.copyProperties(departement, departementDto);
        return departementDto;
    }

    public Departement deDepartementDto(DepartementDto departementDTO)
    {
        if(departementDTO == null) {
            return null;
        }
        Departement departement = new Departement();
        BeanUtils.copyProperties(departementDTO, departement);
        return departement;
    }
}
