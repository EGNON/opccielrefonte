
package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.EmetteurDto;
import com.ged.entity.titresciel.Emetteur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EmetteurMapper {

    public EmetteurDto deEmetteur(Emetteur emetteur)
    {
        EmetteurDto emetteurDto = new EmetteurDto();
        BeanUtils.copyProperties(emetteur, emetteurDto);
        return emetteurDto;
    }

    public Emetteur deEmetteurDto(EmetteurDto emetteurDto)
    {
        Emetteur emetteur = new Emetteur();
        BeanUtils.copyProperties(emetteurDto, emetteur);
        
        return emetteur;
    }
}
