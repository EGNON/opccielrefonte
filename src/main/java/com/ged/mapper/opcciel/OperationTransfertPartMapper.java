package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationTransfertPartDto;
import com.ged.entity.opcciel.OperationTransfertPart;
import com.ged.mapper.standard.PersonneMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationTransfertPartMapper {
    private final PersonneMapper personneMapper;
    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;

    public OperationTransfertPartMapper(
            PersonneMapper personneMapper,
            OpcvmMapper opcvmMapper,
            NatureOperationMapper natureOperationMapper
    ) {
        this.personneMapper = personneMapper;
        this.opcvmMapper = opcvmMapper;
        this.natureOperationMapper = natureOperationMapper;
    }

    public OperationTransfertPartDto deEntite(OperationTransfertPart op) {
        if(op == null)
            return null;
        OperationTransfertPartDto opDto = new OperationTransfertPartDto();
        BeanUtils.copyProperties(op, opDto);
        opDto.setDemandeur(personneMapper.dePersonne(op.getDemandeur()));
        opDto.setBeneficiaire(personneMapper.dePersonne(op.getBeneficiaire()));
        opDto.setOpcvm(opcvmMapper.deOpcvm(op.getOpcvm()));
        opDto.setNatureOperation(natureOperationMapper.deNatureOperation(op.getNatureOperation()));
        return opDto;
    }

    public OperationTransfertPart deDto(OperationTransfertPartDto opDto) {
        if(opDto == null)
            return null;
        OperationTransfertPart op = new OperationTransfertPart();
        BeanUtils.copyProperties(opDto, op);
        op.setDemandeur(personneMapper.dePersonneDto(opDto.getDemandeur()));
        op.setBeneficiaire(personneMapper.dePersonneDto(opDto.getBeneficiaire()));
        op.setOpcvm(opcvmMapper.deOpcvmDto(opDto.getOpcvm()));
        op.setNatureOperation(natureOperationMapper.deNatureOperationDto(opDto.getNatureOperation()));
        return op;
    }
}
