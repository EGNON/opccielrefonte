package com.ged.mapper.opcciel;


import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.entity.opcciel.comptabilite.Exercice;
import com.ged.projection.ExerciceProjection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ExerciceMapper {
    private final PlanMapper planMapper;
    private final OpcvmMapper opcvmMapper;

    public ExerciceMapper(PlanMapper planMapper, OpcvmMapper opcvmMapper) {
        this.planMapper = planMapper;
        this.opcvmMapper = opcvmMapper;
    }

    public ExerciceDto deExercice(Exercice Exercice)
    {
        if(Exercice == null)
            return null;
        ExerciceDto ExerciceDto = new ExerciceDto();
        BeanUtils.copyProperties(Exercice, ExerciceDto);
        if(Exercice.getPlan()!=null)
            ExerciceDto.setPlan(planMapper.dePlan(Exercice.getPlan()));
        return ExerciceDto;
    }

    public Exercice deExerciceDto(ExerciceDto ExerciceDto)
    {
        Exercice Exercice = new Exercice();
        BeanUtils.copyProperties(ExerciceDto, Exercice);
        return Exercice;
    }
    public ExerciceDto deExerciceProjection(ExerciceProjection exercice)
    {
        if(exercice == null) {
            return null;
        }
        ExerciceDto ExerciceDto = new ExerciceDto();
        BeanUtils.copyProperties(exercice, ExerciceDto);
        if(exercice.getPlan()!=null)
            ExerciceDto.setPlan(planMapper.dePlan(exercice.getPlan()));

//        if(exercice.getOpcvm()!=null)
//            ExerciceDto.setOpcvm(opcvmMapper.deOpcvm(exercice.getOpcvm()));

        return ExerciceDto;
    }
}
