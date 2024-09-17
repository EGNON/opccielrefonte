package com.ged.mapper.standard;

import com.ged.dto.standard.ProtoAlerteDto;
import com.ged.entity.standard.ProtoAlerte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ProtoAlerteMapper {
    private final ModeleMsgAlerteMapper modeleMsgAlerteMapper;
    private final AlerteMapper alerteMapper;
    private final PersonnelMapper personnelMapper;

    public ProtoAlerteMapper(ModeleMsgAlerteMapper modeleMsgAlerteMapper, AlerteMapper alerteMapper, PersonnelMapper personnelMapper) {
        this.modeleMsgAlerteMapper = modeleMsgAlerteMapper;
        this.alerteMapper = alerteMapper;
        this.personnelMapper = personnelMapper;
    }

    public ProtoAlerteDto deProtoAlerte(ProtoAlerte protoAlerte)
    {
        ProtoAlerteDto protoAlerteDto = new ProtoAlerteDto();
        BeanUtils.copyProperties(protoAlerte, protoAlerteDto);
        if(protoAlerte.getModeleMsgAlerte() != null)
        {
            protoAlerteDto.setModeleMsgAlerte(modeleMsgAlerteMapper.deModeleMsgAlerte(protoAlerte.getModeleMsgAlerte()));
        }
        if(protoAlerte.getAlerte() != null)
        {
            protoAlerteDto.setAlerte(alerteMapper.deAlerte(protoAlerte.getAlerte()));
        }
//        protoAlerteDto.setDiffusionAlertes(protoAlerte.getDiffusionAlertes().stream()
//                .map(diffusionAlerteMapper::deDiffusionAlerte).collect(Collectors.toSet()));
        protoAlerteDto.setPersonnels(protoAlerte.getPersonnels().stream()
                .map(personnelMapper::dePersonnel).collect(Collectors.toSet()));
        return protoAlerteDto;
    }

    public ProtoAlerte deProtoAlerteDto(ProtoAlerteDto protoAlerteDto)
    {
        ProtoAlerte protoAlerte = new ProtoAlerte();
        BeanUtils.copyProperties(protoAlerteDto, protoAlerte);
        if(protoAlerteDto.getModeleMsgAlerte() != null)
        {
            protoAlerte.setModeleMsgAlerte(modeleMsgAlerteMapper.deModeleMsgAlerteDto(protoAlerteDto.getModeleMsgAlerte()));
        }
        protoAlerte.setPersonnels(protoAlerteDto.getPersonnels().stream().map(personnelMapper::dePersonnelDto).collect(Collectors.toSet()));
        protoAlerte.setAlerte(alerteMapper.deAlerteDto(protoAlerteDto.getAlerte()));
        return protoAlerte;
    }
}
