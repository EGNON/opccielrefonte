
package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.TypeEmetteurDto;
import com.ged.entity.titresciel.TypeEmetteur;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeEmetteurMapper {
    public TypeEmetteurDto deTypeEmetteur(TypeEmetteur typeEmetteur)
    {
        TypeEmetteurDto typeEmetteurDto = new TypeEmetteurDto();
        BeanUtils.copyProperties(typeEmetteur, typeEmetteurDto);
        return typeEmetteurDto;
    }

    public TypeEmetteur deTypeEmetteurDto(TypeEmetteurDto typeEmetteurDto)
    {
        TypeEmetteur typeEmetteur = new TypeEmetteur();
        BeanUtils.copyProperties(typeEmetteurDto, typeEmetteur);
        return typeEmetteur;
    }
}
