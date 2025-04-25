package com.ged.mapper.opcciel;
import com.ged.dto.opcciel.AvisOperationBourseDto;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.entity.opcciel.AvisOperationBourse;
import com.ged.entity.opcciel.Ordre;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.mapper.titresciel.TitreMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AvisOperationBourseMapper {

    public AvisOperationBourseMapper(){

    }

    public AvisOperationBourseDto deAvisOperationBourse(AvisOperationBourse avisOperationBourse)
    {
        AvisOperationBourseDto avisOperationBourseDto = new AvisOperationBourseDto();
        BeanUtils.copyProperties(avisOperationBourse, avisOperationBourseDto);

        return avisOperationBourseDto;
    }

    public AvisOperationBourse deAvisOperationBourseDto(AvisOperationBourseDto avisOperationBourseDto)
    {
        AvisOperationBourse avisOperationBourse = new AvisOperationBourse();
        BeanUtils.copyProperties(avisOperationBourseDto, avisOperationBourse);
        return avisOperationBourse;
    }
}
