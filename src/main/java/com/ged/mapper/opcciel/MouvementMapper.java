package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.MouvementDto;
import com.ged.entity.opcciel.comptabilite.Mouvement;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MouvementMapper {
    private final PlanMapper planMapper;
    private final OpcvmMapper opcvmMapper;
    private final IbMapper ibMapper;
    private final OperationMapper operationMapper;

    public MouvementMapper(PlanMapper planMapper, OpcvmMapper opcvmMapper, IbMapper ibMapper, OperationMapper operationMapper) {
        this.planMapper = planMapper;
        this.opcvmMapper = opcvmMapper;
        this.ibMapper = ibMapper;
        this.operationMapper = operationMapper;
    }

    public MouvementDto deMouvement(Mouvement mouvement) {
        if(mouvement == null)
            return null;
        MouvementDto mouvementDto = new MouvementDto();
        BeanUtils.copyProperties(mouvement, mouvementDto);
        mouvementDto.setPlan(planMapper.dePlan(mouvement.getPlan()));
        mouvementDto.setOpcvm(opcvmMapper.deOpcvm(mouvement.getOpcvm()));
        mouvementDto.setIb(ibMapper.deIb(mouvement.getIb()));
        mouvementDto.setOperation(operationMapper.deOperation(mouvement.getOperation()));
        return mouvementDto;
    }

    public Mouvement deMouvementDto(MouvementDto mouvementDto) {
        if(mouvementDto == null)
            return null;
        Mouvement mouvement = new Mouvement();
        BeanUtils.copyProperties(mouvementDto, mouvement);
        BeanUtils.copyProperties(mouvement, mouvementDto);
        mouvement.setPlan(planMapper.dePlanDto(mouvementDto.getPlan()));
        mouvement.setOpcvm(opcvmMapper.deOpcvmDto(mouvementDto.getOpcvm()));
        mouvement.setIb(ibMapper.deIbDto(mouvementDto.getIb()));
        mouvement.setOperation(operationMapper.deOperationDto(mouvementDto.getOperation()));
        return mouvement;
    }
}
