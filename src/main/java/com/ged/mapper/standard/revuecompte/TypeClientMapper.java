package com.ged.mapper.standard.revuecompte;

import com.ged.dto.standard.revuecompte.TypeClientDto;
import com.ged.entity.standard.revuecompte.TypeClient;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeClientMapper {
    public TypeClientDto deTypeClient(TypeClient typeClient)
    {
        if(typeClient == null) {
            return null;
        }
        TypeClientDto typeClientDto = new TypeClientDto();
        BeanUtils.copyProperties(typeClient, typeClientDto);
        return typeClientDto;
    }

    public TypeClient deTypeClientDto(TypeClientDto typeClientDto)
    {
        if(typeClientDto == null) {
            return null;
        }
        TypeClient typeClient = new TypeClient();
        BeanUtils.copyProperties(typeClientDto, typeClient);
        return typeClient;
    }
}
