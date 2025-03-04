package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationConstatationChargeDto;
import com.ged.entity.opcciel.OperationConstatationCharge;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OperationConstatationChargeMapper {

    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;

    public OperationConstatationCharge deDto(OperationConstatationChargeDto dto) {
        OperationConstatationCharge entite = new OperationConstatationCharge();
        BeanUtils.copyProperties(dto, entite);
        entite.setOpcvm(opcvmMapper.deOpcvmDto(dto.getOpcvm()));
        entite.setNatureOperation(natureOperationMapper.deNatureOperationDto(dto.getNatureOperation()));
        return entite;
    }

    public OperationConstatationChargeDto deEntite(OperationConstatationCharge entite) {
        OperationConstatationChargeDto dto = new OperationConstatationChargeDto();
        BeanUtils.copyProperties(entite, dto);
        dto.setOpcvm(opcvmMapper.deOpcvm(entite.getOpcvm()));
        dto.setNatureOperation(natureOperationMapper.deNatureOperation(entite.getNatureOperation()));
        return dto;
    }
}
