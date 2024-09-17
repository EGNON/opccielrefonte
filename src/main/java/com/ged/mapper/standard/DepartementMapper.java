package com.ged.mapper.standard;

import com.ged.dto.standard.DepartementDto;
import com.ged.entity.standard.Departement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DepartementMapper {
    public DepartementDto deDepartement(Departement departement)
    {
        DepartementDto departementDto = new DepartementDto();
        BeanUtils.copyProperties(departement, departementDto);
        return departementDto;
    }

    public Departement deDepartementDto(DepartementDto departementDTO)
    {
        Departement departement = new Departement();
        BeanUtils.copyProperties(departementDTO, departement);
        return departement;
    }
}
