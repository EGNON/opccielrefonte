package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationPaiementChargeDto;
import com.ged.entity.opcciel.OperationPaiementCharge;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OperationPaiementChargeMapper {

    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final ChargeMapper chargeMapper;

    public OperationPaiementCharge deDto(OperationPaiementChargeDto dto) {
        OperationPaiementCharge entite = new OperationPaiementCharge();
        BeanUtils.copyProperties(dto, entite);
        entite.setOpcvm(opcvmMapper.deOpcvmDto(dto.getOpcvm()));
        entite.setNatureOperation(natureOperationMapper.deNatureOperationDto(dto.getNatureOperation()));
//        entite.setCharge(chargeMapper.deChargeDto(dto.getCharge()));
        return entite;
    }

    public OperationPaiementChargeDto deEntite(OperationPaiementCharge entite) {
        OperationPaiementChargeDto dto = new OperationPaiementChargeDto();
        BeanUtils.copyProperties(entite, dto);
        dto.setOpcvm(opcvmMapper.deOpcvm(entite.getOpcvm()));
        dto.setNatureOperation(natureOperationMapper.deNatureOperation(entite.getNatureOperation()));
//        dto.setCharge(chargeMapper.deCharge(entite.getCharge()));
        return dto;
    }
}
