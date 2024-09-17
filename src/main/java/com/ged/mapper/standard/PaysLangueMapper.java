package com.ged.mapper.standard;

import com.ged.dto.standard.PaysLangueDto;
import com.ged.entity.standard.PersonnePhysique;
import com.ged.entity.standard.PaysLangue;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PaysLangueMapper {
    private final PaysMapper paysMapper;
    private final LangueMapper langueMapper;

    public PaysLangueMapper(PaysMapper paysMapper, LangueMapper langueMapper) {
        this.paysMapper = paysMapper;
        this.langueMapper = langueMapper;
    }

    public PaysLangueDto dePaysLangue(PaysLangue PaysLangue)
    {
        PaysLangueDto PaysLangueDto = new PaysLangueDto();
        BeanUtils.copyProperties(PaysLangue, PaysLangueDto);
        //System.out.println(PaysLangue.toString());
        if(PaysLangue.getPays()!=null) {
            PaysLangueDto.setPays(paysMapper.dePays(PaysLangue.getPays()));
        }
        if(PaysLangue.getLangue()!=null) {
            PaysLangueDto.setLangue(langueMapper.deLangue(PaysLangue.getLangue()));
        }
        return PaysLangueDto;
    }

    public PaysLangue dePaysLangueDto(PaysLangueDto PaysLangueDto)
    {
        PaysLangue PaysLangue= new PaysLangue();
        BeanUtils.copyProperties(PaysLangueDto, PaysLangue);
        if(PaysLangueDto.getPays()!=null) {
            PaysLangue.setPays(paysMapper.dePaysDto(PaysLangueDto.getPays()));
        }
        if(PaysLangueDto.getLangue()!=null) {
            PaysLangue.setLangue(langueMapper.deLangueDto(PaysLangueDto.getLangue()));
        }
        return PaysLangue;
    }
}
