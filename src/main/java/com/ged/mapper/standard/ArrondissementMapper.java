package com.ged.mapper.standard;

import com.ged.dao.standard.ArrondissementDao;
import com.ged.dto.standard.ArrondissementDto;
import com.ged.entity.standard.Arrondissement;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ArrondissementMapper {
    private final ArrondissementDao arrondissementDao;
    private final CommuneMapper communeMapper;

    public ArrondissementMapper(ArrondissementDao arrondissementDao, CommuneMapper communeMapper) {
        this.arrondissementDao = arrondissementDao;
        this.communeMapper = communeMapper;
    }

    public ArrondissementDto deArrondissement(Arrondissement arrondissement)
    {
        ArrondissementDto arrondissementDTO = new ArrondissementDto();
        BeanUtils.copyProperties(arrondissement, arrondissementDTO);
        arrondissementDTO.setCommune(communeMapper.deCommune(arrondissement.getCommune()));
        return arrondissementDTO;
    }

    public Arrondissement deArrondissementDto(ArrondissementDto arrondissementDto)
    {
        Arrondissement arrondissement = new Arrondissement();
        if(arrondissementDto.getIdArrondissement() != null)
            arrondissement = arrondissementDao.findById(arrondissementDto.getIdArrondissement()).orElseThrow(() -> new EntityNotFoundException(""));
        BeanUtils.copyProperties(arrondissementDto, arrondissement);
        return arrondissement;
    }
}
