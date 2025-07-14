package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationChargeAEtalerDto;
import com.ged.entity.opcciel.OperationChargeAEtaler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OperationChargeAEtalerMapper {

    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final ChargeMapper chargeMapper;

    public OperationChargeAEtaler deDto(OperationChargeAEtalerDto dto) {
        OperationChargeAEtaler entite = new OperationChargeAEtaler();
        BeanUtils.copyProperties(dto, entite);
        entite.setOpcvm(opcvmMapper.deOpcvmDto(dto.getOpcvm()));
        entite.setNatureOperation(natureOperationMapper.deNatureOperationDto(dto.getNatureOperation()));
//        entite.setCharge(chargeMapper.deChargeDto(dto.getCharge()));
        return entite;
    }

    public OperationChargeAEtalerDto deEntite(OperationChargeAEtaler entite) {
        OperationChargeAEtalerDto dto = new OperationChargeAEtalerDto();
        BeanUtils.copyProperties(entite, dto);
        dto.setOpcvm(opcvmMapper.deOpcvm(entite.getOpcvm()));
        dto.setNatureOperation(natureOperationMapper.deNatureOperation(entite.getNatureOperation()));
//        dto.setCharge(chargeMapper.deCharge(entite.getCharge()));
        return dto;
    }
}
