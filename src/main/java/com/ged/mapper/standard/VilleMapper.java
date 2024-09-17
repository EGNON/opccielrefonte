package com.ged.mapper.standard;

import com.ged.dto.standard.VilleDto;
import com.ged.entity.standard.Ville;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class VilleMapper {
    private final CommuneMapper communeMapper;

    public VilleMapper(CommuneMapper communeMapper) {
        this.communeMapper = communeMapper;
    }

    public VilleDto deVille(Ville ville)
    {
        if(ville == null)
            return null;
        VilleDto villeDto = new VilleDto();
        BeanUtils.copyProperties(ville, villeDto);
        villeDto.setCommune(communeMapper.deCommune(ville.getCommune()));
        return villeDto;
    }

    public Ville deVilleDto(VilleDto villeDto)
    {
        if(villeDto == null)
            return  null;
        Ville ville = new Ville();
        BeanUtils.copyProperties(villeDto, ville);
        ville.setCommune(communeMapper.deCommuneDto(villeDto.getCommune()));
        return ville;
    }
}
