package com.ged.mapper.standard;

import com.ged.dto.standard.ActionnaireOpcvmDto;
import com.ged.entity.standard.ActionnaireOpcvm;
import com.ged.mapper.opcciel.OpcvmMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ActionnaireOpcvmMapper {
    private final PersonneMapper personneMapper;
    private final OpcvmMapper opcvmMapper;

    public ActionnaireOpcvmMapper(PersonneMapper personneMapper, OpcvmMapper opcvmMapper) {
        this.personneMapper = personneMapper;
        this.opcvmMapper = opcvmMapper;
    }


    public ActionnaireOpcvmDto deActionnaireOpcvm(ActionnaireOpcvm actionnaireOpcvm)
    {
        ActionnaireOpcvmDto actionnaireOpcvmDto = new ActionnaireOpcvmDto();
        BeanUtils.copyProperties(actionnaireOpcvm, actionnaireOpcvmDto);
        if(actionnaireOpcvm.getPersonne() != null)
            actionnaireOpcvmDto.setPersonne(personneMapper.dePersonne(actionnaireOpcvm.getPersonne()));
        if(actionnaireOpcvm.getOpcvm() != null) actionnaireOpcvmDto.setOpcvm(opcvmMapper.deOpcvm(actionnaireOpcvm.getOpcvm()));
        return actionnaireOpcvmDto;
    }

    public ActionnaireOpcvm deActionnaireOpcvmDto(ActionnaireOpcvmDto actionnaireOpcvmDto)
    {
        ActionnaireOpcvm actionnaireOpcvm = new ActionnaireOpcvm();
        BeanUtils.copyProperties(actionnaireOpcvmDto, actionnaireOpcvm);
        return actionnaireOpcvm;
    }
}
