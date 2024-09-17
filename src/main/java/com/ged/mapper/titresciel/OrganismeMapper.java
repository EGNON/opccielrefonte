package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.OrganismeDto;
import com.ged.entity.titresciel.Organisme;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OrganismeMapper {
    public OrganismeDto deOrganisme(Organisme Organisme)
    {
        OrganismeDto OrganismeDto = new OrganismeDto();
        BeanUtils.copyProperties(Organisme, OrganismeDto);
        return OrganismeDto;
    }

    public Organisme deOrganismeDto(OrganismeDto OrganismeDTO)
    {
        Organisme Organisme = new Organisme();
        BeanUtils.copyProperties(OrganismeDTO, Organisme);
        return Organisme;
    }
}
