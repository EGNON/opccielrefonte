package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationRegulEcartSoldeDto;
import com.ged.entity.opcciel.OperationRegulEcartSolde;
import com.ged.projection.OperationRegulEcartSoldeProjection;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class OperationRegulEcartSoldeMapper {

    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;

    public OperationRegulEcartSolde deDto(OperationRegulEcartSoldeDto dto) {
        OperationRegulEcartSolde entite = new OperationRegulEcartSolde();
        BeanUtils.copyProperties(dto, entite);
        entite.setOpcvm(opcvmMapper.deOpcvmDto(dto.getOpcvm()));
        entite.setNatureOperation(natureOperationMapper.deNatureOperationDto(dto.getNatureOperation()));
        return entite;
    }

    public OperationRegulEcartSoldeDto deEntite(OperationRegulEcartSolde entite) {
        OperationRegulEcartSoldeDto dto = new OperationRegulEcartSoldeDto();
        BeanUtils.copyProperties(entite, dto);
        dto.setOpcvm(opcvmMapper.deOpcvm(entite.getOpcvm()));
        dto.setNatureOperation(natureOperationMapper.deNatureOperation(entite.getNatureOperation()));
        return dto;
    }
//    public OperationRegulEcartSoldeDto deProjetion(OperationRegulEcartSoldeProjection entite) {
//        OperationRegulEcartSoldeDto dto = new OperationRegulEcartSoldeDto();
//        BeanUtils.copyProperties(entite, dto);
//        dto.setOpcvm(opcvmMapper.deOpcvm(entite.getOpcvm()));
//        dto.setNatureOperation(natureOperationMapper.deNatureOperation(entite.getNatureOperation()));
//        return dto;
//    }
}
