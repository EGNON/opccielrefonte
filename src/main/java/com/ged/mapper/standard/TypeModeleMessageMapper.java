package com.ged.mapper.standard;

import com.ged.dto.standard.TypeModeleMessageDto;
import com.ged.entity.standard.TypeModeleMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeModeleMessageMapper {
    private final TypeModeleMapper typeModeleMapper;
    private final ModeleMsgAlerteMapper modeleMsgAlerteMapper;

    public TypeModeleMessageMapper(TypeModeleMapper typeModeleMapper, ModeleMsgAlerteMapper modeleMsgAlerteMapper) {
        this.typeModeleMapper = typeModeleMapper;
        this.modeleMsgAlerteMapper = modeleMsgAlerteMapper;
    }

    public TypeModeleMessageDto deTypeModeleMessage(TypeModeleMessage TypeModeleMessage)
    {
        TypeModeleMessageDto TypeModeleMessageDto = new TypeModeleMessageDto();
        BeanUtils.copyProperties(TypeModeleMessage, TypeModeleMessageDto);
        if(TypeModeleMessage.getTypeModele()!=null)
            TypeModeleMessageDto.setTypeModele(typeModeleMapper.deTypeModele(TypeModeleMessage.getTypeModele()));

        if(TypeModeleMessage.getModeleMsgAlerte()!=null)
            TypeModeleMessageDto.setModeleMsgAlerte(modeleMsgAlerteMapper.deModeleMsgAlerte(TypeModeleMessage.getModeleMsgAlerte()));
        return TypeModeleMessageDto;
    }

    public TypeModeleMessage deTypeModeleMessageDto(TypeModeleMessageDto TypeModeleMessageDto)
    {
        TypeModeleMessage TypeModeleMessage = new TypeModeleMessage();
        BeanUtils.copyProperties(TypeModeleMessageDto, TypeModeleMessage);
        return TypeModeleMessage;
    }
}
