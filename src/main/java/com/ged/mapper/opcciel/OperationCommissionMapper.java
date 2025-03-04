package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationCommissionDto;
import com.ged.dto.request.CommissionAddRequest;
import com.ged.entity.opcciel.OperationCommission;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OperationCommissionMapper {

    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;

    public OperationCommission deDto(OperationCommissionDto dto) {
        if(dto == null)
            return null;
        OperationCommission entite = new OperationCommission();
        BeanUtils.copyProperties(dto, entite);
        entite.setOpcvm(opcvmMapper.deOpcvmDto(dto.getOpcvm()));
        entite.setNatureOperation(natureOperationMapper.deNatureOperationDto(dto.getNatureOperation()));
        return entite;
    }

    public OperationCommission deDto(CommissionAddRequest dto) {
        if(dto == null)
            return null;
        OperationCommission entite = new OperationCommission();
        BeanUtils.copyProperties(dto, entite);
        entite.setOpcvm(opcvmMapper.deOpcvmDto(dto.getOpcvm()));
        entite.setNatureOperation(natureOperationMapper.deNatureOperationDto(dto.getNatureOperation()));
        return entite;
    }

    public OperationCommissionDto deEntite(OperationCommission entite) {
        if(entite == null)
            return null;
        OperationCommissionDto dto = new OperationCommissionDto();
        BeanUtils.copyProperties(entite, dto);
        dto.setOpcvm(opcvmMapper.deOpcvm(entite.getOpcvm()));
        dto.setNatureOperation(natureOperationMapper.deNatureOperation(entite.getNatureOperation()));
        return dto;
    }
}
