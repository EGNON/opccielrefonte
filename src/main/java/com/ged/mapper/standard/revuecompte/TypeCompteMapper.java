package com.ged.mapper.standard.revuecompte;

import com.ged.dto.standard.revuecompte.TypeCompteDto;
import com.ged.entity.standard.revuecompte.TypeCompte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeCompteMapper {
    public TypeCompteDto deTypeCompte(TypeCompte typeCompte)
    {
        TypeCompteDto typeCompteDto = new TypeCompteDto();
        BeanUtils.copyProperties(typeCompte, typeCompteDto);
        return typeCompteDto;
    }

    public TypeCompte deTypeCompteDto(TypeCompteDto typeCompteDto)
    {
        TypeCompte typeCompte = new TypeCompte();
        BeanUtils.copyProperties(typeCompteDto, typeCompte);
        return typeCompte;
    }
}
