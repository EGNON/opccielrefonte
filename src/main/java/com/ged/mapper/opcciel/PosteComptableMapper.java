package com.ged.mapper.opcciel;
import com.ged.dto.opcciel.comptabilite.PosteComptableDto;
import com.ged.entity.opcciel.comptabilite.PosteComptable;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PosteComptableMapper {
    private final PlanMapper planMapper;

    public PosteComptableMapper(PlanMapper planMapper) {
        this.planMapper = planMapper;
    }

    public PosteComptableDto dePosteComptable(PosteComptable posteComptable)
    {
        PosteComptableDto posteComptableDto = new PosteComptableDto();
        BeanUtils.copyProperties(posteComptable, posteComptableDto);
        if(posteComptable.getPlan()!=null){
            posteComptableDto.setPlan(planMapper.dePlan(posteComptable.getPlan()));
        }
        return posteComptableDto;
    }

    public PosteComptable dePosteComptableDto(PosteComptableDto posteComptableDto)
    {
        PosteComptable posteComptable = new PosteComptable();
        BeanUtils.copyProperties(posteComptableDto, posteComptable);
        return posteComptable;
    }
}
