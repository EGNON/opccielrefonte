package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.PlanDto;
import com.ged.entity.opcciel.comptabilite.Plan;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PlanMapper {
    public PlanDto dePlan(Plan plan)
    {
        if(plan == null)
            return null;
        PlanDto planDto = new PlanDto();
        BeanUtils.copyProperties(plan, planDto);
        return planDto;
    }

    public Plan dePlanDto(PlanDto planDto)
    {
        if(planDto == null)
            return null;
        Plan plan = new Plan();
        BeanUtils.copyProperties(planDto, plan);
        return plan;
    }
}
