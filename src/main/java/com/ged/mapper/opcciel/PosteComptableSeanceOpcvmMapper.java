package com.ged.mapper.opcciel;
import com.ged.dto.opcciel.comptabilite.PosteComptableDto;
import com.ged.dto.opcciel.comptabilite.PosteComptableSeanceOpcvmDto;
import com.ged.entity.opcciel.comptabilite.PosteComptable;
import com.ged.entity.opcciel.comptabilite.PosteComptableSeanceOpcvm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PosteComptableSeanceOpcvmMapper {
    private final PlanMapper planMapper;

    public PosteComptableSeanceOpcvmMapper(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    public PosteComptableSeanceOpcvmDto dePosteComptable(PosteComptableSeanceOpcvm posteComptable)
    {
        PosteComptableSeanceOpcvmDto posteComptableDto = new PosteComptableSeanceOpcvmDto();
        BeanUtils.copyProperties(posteComptable, posteComptableDto);
        if(posteComptable.getPlan()!=null){
            posteComptableDto.setPlan(planMapper.dePlan(posteComptable.getPlan()));
        }
        return posteComptableDto;
    }

    public PosteComptableSeanceOpcvm dePosteComptableDto(PosteComptableSeanceOpcvmDto posteComptableDto)
    {
        PosteComptableSeanceOpcvm posteComptable = new PosteComptableSeanceOpcvm();
        BeanUtils.copyProperties(posteComptableDto, posteComptable);
        return posteComptable;
    }
}
