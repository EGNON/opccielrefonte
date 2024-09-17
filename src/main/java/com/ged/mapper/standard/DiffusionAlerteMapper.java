package com.ged.mapper.standard;

import com.ged.dao.standard.ProtoAlerteDao;
import com.ged.dto.standard.DiffusionAlerteDto;
import com.ged.entity.standard.CleProtoAlerte;
import com.ged.entity.standard.DiffusionAlerte;
import com.ged.entity.standard.ProtoAlerte;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DiffusionAlerteMapper {
    private final PersonneMapper personneMapper;
    private final ModeleMsgAlerteMapper modeleMsgAlerteMapper;
    private final AlerteMapper alerteMapper;
    private final ProtoAlerteDao protoAlerteDao;

    public DiffusionAlerteMapper(PersonneMapper personneMapper, ModeleMsgAlerteMapper modeleMsgAlerteMapper, AlerteMapper alerteMapper, ProtoAlerteDao protoAlerteDao) {
        this.personneMapper = personneMapper;
        this.modeleMsgAlerteMapper = modeleMsgAlerteMapper;
        this.alerteMapper = alerteMapper;
        this.protoAlerteDao = protoAlerteDao;
    }

    public DiffusionAlerteDto deDiffusionAlerte(DiffusionAlerte diffusionAlerte)
    {
        DiffusionAlerteDto diffusionAlerteDto = new DiffusionAlerteDto();
        BeanUtils.copyProperties(diffusionAlerte, diffusionAlerteDto);

        CleProtoAlerte cleProtoAlerte = new CleProtoAlerte();
        cleProtoAlerte.setIdModele(diffusionAlerte.getModeleMsgAlerte().getIdModele());
        cleProtoAlerte.setIdAlerte(diffusionAlerte.getAlerte().getIdAlerte());
        ProtoAlerte protoAlerte = protoAlerteDao.findById(cleProtoAlerte).orElseThrow();
        diffusionAlerteDto.setContenu(protoAlerte.getContenu());
        diffusionAlerteDto.setModeleMsgAlerte(modeleMsgAlerteMapper.deModeleMsgAlerte(diffusionAlerte.getModeleMsgAlerte()));
        diffusionAlerteDto.setPersonne(personneMapper.dePersonne(diffusionAlerte.getPersonne()));
        diffusionAlerteDto.setAlerte(alerteMapper.deAlerte(diffusionAlerte.getAlerte()));
        return diffusionAlerteDto;
    }

    public DiffusionAlerte deDiffusionAlerteDto(DiffusionAlerteDto diffusionAlerteDto)
    {
        DiffusionAlerte diffusionAlerte = new DiffusionAlerte();
        BeanUtils.copyProperties(diffusionAlerteDto, diffusionAlerte);
        diffusionAlerte.setPersonne(personneMapper.dePersonneDto(diffusionAlerteDto.getPersonne()));
        return diffusionAlerte;
    }
}
