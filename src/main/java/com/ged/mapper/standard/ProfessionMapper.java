package com.ged.mapper.standard;

import com.ged.dto.standard.ProfessionDto;
import com.ged.entity.standard.Profession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ProfessionMapper {
    public ProfessionDto deProfession(Profession profession)
    {
        if(profession == null) {
            return null;
        }
        ProfessionDto professionDto = new ProfessionDto();
        if(profession != null)
            BeanUtils.copyProperties(profession, professionDto);
        return professionDto;
    }

    public Profession deProfessionDto(ProfessionDto professionDto)
    {
        if(professionDto == null) {
            return null;
        }
        Profession profession= new Profession();
        BeanUtils.copyProperties(professionDto, profession);
        return profession;
    }
}
