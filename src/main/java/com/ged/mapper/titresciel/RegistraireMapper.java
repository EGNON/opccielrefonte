package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.RegistraireDto;
import com.ged.entity.titresciel.Registraire;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class RegistraireMapper {
    public RegistraireDto deRegistraire(Registraire registraire)
    {
        if(registraire == null)
            return null;
        RegistraireDto registraireDto = new RegistraireDto();
        BeanUtils.copyProperties(registraire, registraireDto);
        return registraireDto;
    }

    public Registraire deRegistraireDto(RegistraireDto registraireDto)
    {
        Registraire registraire = new Registraire();
        BeanUtils.copyProperties(registraireDto, registraire);
        return registraire;
    }
}
