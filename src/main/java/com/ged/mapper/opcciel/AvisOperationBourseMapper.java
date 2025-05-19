package com.ged.mapper.opcciel;
import com.ged.dto.opcciel.AvisOperationBourseDto;
import com.ged.entity.opcciel.AvisOperationBourse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AvisOperationBourseMapper {
    private final OrdreMapper ordreMapper;

    public AvisOperationBourseMapper(OrdreMapper ordreMapper){

        this.ordreMapper = ordreMapper;
    }

    public AvisOperationBourseDto deAvisOperationBourse(AvisOperationBourse avisOperationBourse)
    {
        AvisOperationBourseDto avisOperationBourseDto = new AvisOperationBourseDto();
        BeanUtils.copyProperties(avisOperationBourse, avisOperationBourseDto);
        if(avisOperationBourse.getOrdre()!=null){
            avisOperationBourseDto.setOrdre(ordreMapper.deOrdre(avisOperationBourse.getOrdre()));
        }
        return avisOperationBourseDto;
    }

    public AvisOperationBourse deAvisOperationBourseDto(AvisOperationBourseDto avisOperationBourseDto)
    {
        AvisOperationBourse avisOperationBourse = new AvisOperationBourse();
        BeanUtils.copyProperties(avisOperationBourseDto, avisOperationBourse);
        return avisOperationBourse;
    }
}
