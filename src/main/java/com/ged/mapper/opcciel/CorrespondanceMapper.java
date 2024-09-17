package com.ged.mapper.opcciel;
import com.ged.dto.opcciel.comptabilite.CorrespondanceDto;
import com.ged.entity.opcciel.comptabilite.Correspondance;
import com.ged.projection.CorrespondanceProjection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CorrespondanceMapper {
    private final PlanMapper planMapper;
    private final IbMapper ibMapper;

    public CorrespondanceMapper(PlanMapper planMapper, IbMapper ibMapper) {
        this.planMapper = planMapper;
        this.ibMapper = ibMapper;
    }
    public CorrespondanceDto deCorrespondanceProjection(CorrespondanceProjection correspondanceProjection)
    {
        CorrespondanceDto correspondanceDto = new CorrespondanceDto();
        BeanUtils.copyProperties(correspondanceProjection, correspondanceDto);
        if(correspondanceProjection.getPlan()!=null) {
            correspondanceDto.setPlan(planMapper.dePlan(correspondanceProjection.getPlan()));
        }

        if(correspondanceProjection.getIb()!=null) {
            correspondanceDto.setIb(ibMapper.deIb(correspondanceProjection.getIb()));
        }
        return correspondanceDto;
    }
    public CorrespondanceDto deCorrespondance(Correspondance correspondance)
    {
        CorrespondanceDto correspondanceDto = new CorrespondanceDto();
        BeanUtils.copyProperties(correspondance, correspondanceDto);
        if(correspondance.getPlan()!=null) {
            correspondanceDto.setPlan(planMapper.dePlan(correspondance.getPlan()));
        }

        if(correspondance.getIb()!=null) {
            correspondanceDto.setIb(ibMapper.deIb(correspondance.getIb()));
        }
        return correspondanceDto;
    }

    public Correspondance deCorrespondanceDto(CorrespondanceDto correspondanceDTO)
    {
        Correspondance correspondance = new Correspondance();
        BeanUtils.copyProperties(correspondanceDTO, correspondance);
        return correspondance;
    }
}
