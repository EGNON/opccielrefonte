package com.ged.mapper.standard;

import com.ged.dto.standard.PaysDto;
import com.ged.entity.standard.Pays;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PaysMapper {
    private final MonnaieMapper monnaieMapper;

    public PaysMapper(MonnaieMapper monnaieMapper) {
        this.monnaieMapper = monnaieMapper;
    }

    public PaysDto dePays(Pays pays)
    {
        if(pays == null)
            return null;
        PaysDto paysDto = new PaysDto();
        BeanUtils.copyProperties(pays, paysDto);
        paysDto.setMonnaieDto(monnaieMapper.deMonnaie(pays.getMonnaie()));

        return paysDto;
    }

    public Pays dePaysDto(PaysDto paysDto)
    {
        Pays pays = new Pays();
        BeanUtils.copyProperties(paysDto, pays);
        if(paysDto.getMonnaieDto()!=null) {
            pays.setMonnaie(monnaieMapper.deMonnaieDto(paysDto.getMonnaieDto()));
        }
        return pays;
    }
}
