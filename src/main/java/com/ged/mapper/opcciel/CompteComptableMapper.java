package com.ged.mapper.opcciel;
import com.ged.dto.opcciel.comptabilite.CompteComptableDto;
import com.ged.entity.opcciel.comptabilite.CompteComptable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CompteComptableMapper {
    private final PlanMapper planMapper;

    public CompteComptableMapper(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    public CompteComptableDto deCompteComptable(CompteComptable compteComptable)
    {
        CompteComptableDto compteComptableDto = new CompteComptableDto();
        BeanUtils.copyProperties(compteComptable, compteComptableDto);
        compteComptableDto.setPlan(planMapper.dePlan(compteComptable.getPlan()));
        return compteComptableDto;
    }

    public CompteComptable deCompteComptableDto(CompteComptableDto compteComptableDTO)
    {
        CompteComptable compteComptable = new CompteComptable();
        BeanUtils.copyProperties(compteComptableDTO, compteComptable);
        return compteComptable;
    }
}
