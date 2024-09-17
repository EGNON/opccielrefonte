package com.ged.mapper.standard;

import com.ged.dto.standard.LangueDto;
import com.ged.dto.standard.PaysLangueDto;
import com.ged.entity.standard.Langue;
import com.ged.entity.standard.PaysLangue;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class LangueMapper {
    private final PaysMapper paysMapper;

    public LangueMapper( PaysMapper paysMapper) {

        this.paysMapper = paysMapper;
    }

    public LangueDto deLangue(Langue Langue)
    {
        if(Langue == null)
            return null;
        LangueDto LangueDto = new LangueDto();
        BeanUtils.copyProperties(Langue, LangueDto);
//        LangueDto.setPays(paysMapper.dePays(Langue.getPays()));
        //System.out.println("count="+Langue.getPaysLangues().size());
        if(Langue.getPaysLangues() != null)
        {
            Set<PaysLangueDto> paysLangueDtoSet=new HashSet<>();
            Langue.getPaysLangues().forEach(PaysLangue -> {
                PaysLangueDto paysLangueDto=new PaysLangueDto();
                if(PaysLangue.getPays()!=null) {
                    paysLangueDto.setPays(paysMapper.dePays(PaysLangue.getPays()));
                }
                if(PaysLangue.getLangue()!=null) {
                    LangueDto langueDto=new LangueDto();
                    BeanUtils.copyProperties(PaysLangue.getLangue(), langueDto);
                    paysLangueDto.setLangue(langueDto);
                }
                paysLangueDtoSet.add(paysLangueDto);
            });
            LangueDto.setPaysLangues(paysLangueDtoSet);
        }
        return LangueDto;
    }

    public Langue deLangueDto(LangueDto LangueDto)
    {
        Langue Langue = new Langue();
        BeanUtils.copyProperties(LangueDto, Langue);
        if(LangueDto.getPaysLangues() != null)
        {
            Set<PaysLangue> paysLangueSet=new HashSet<>();
            LangueDto.getPaysLangues().forEach(PaysLangue -> {
                PaysLangue paysLangue=new PaysLangue();
                if(PaysLangue.getPays()!=null) {
                    paysLangue.setPays(paysMapper.dePaysDto(PaysLangue.getPays()));
                }
                if(PaysLangue.getLangue()!=null) {
                    Langue langue=new Langue();
                    BeanUtils.copyProperties(PaysLangue.getLangue(), langue);
                    paysLangue.setLangue(langue);
                }
                paysLangueSet.add(paysLangue);
            });
            Langue.setPaysLangues(paysLangueSet);
        }
        return Langue;
    }
}
