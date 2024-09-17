package com.ged.mapper.standard;

import com.ged.dto.standard.ActionnaireCommissionDto;
import com.ged.entity.standard.ActionnaireCommission;
import com.ged.mapper.opcciel.OpcvmMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ActionnaireCommissionMapper {
    private final PersonneMapper personneMapper;
    private final OpcvmMapper opcvmMapper;

    public ActionnaireCommissionMapper(PersonneMapper personneMapper, OpcvmMapper opcvmMapper) {
        this.personneMapper = personneMapper;
        this.opcvmMapper = opcvmMapper;
    }


    public ActionnaireCommissionDto deActionnaireCommission(ActionnaireCommission ActionnaireCommission)
    {
        ActionnaireCommissionDto ActionnaireCommissionDto = new ActionnaireCommissionDto();
        BeanUtils.copyProperties(ActionnaireCommission, ActionnaireCommissionDto);
        if(ActionnaireCommission.getPersonne() != null)
            ActionnaireCommissionDto.setPersonne(personneMapper.dePersonne(ActionnaireCommission.getPersonne()));
        if(ActionnaireCommission.getOpcvm() != null) ActionnaireCommissionDto.setOpcvm(opcvmMapper.deOpcvm(ActionnaireCommission.getOpcvm()));
        return ActionnaireCommissionDto;
    }

    public ActionnaireCommission deActionnaireCommissionDto(ActionnaireCommissionDto ActionnaireCommissionDto)
    {
        ActionnaireCommission ActionnaireCommission = new ActionnaireCommission();
        BeanUtils.copyProperties(ActionnaireCommissionDto, ActionnaireCommission);
        return ActionnaireCommission;
    }
}
