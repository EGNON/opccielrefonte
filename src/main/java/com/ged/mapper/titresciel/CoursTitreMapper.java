package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.CleCoursTitreDto;
import com.ged.dto.titresciel.CoursTitreDto;
import com.ged.dto.titresciel.CoursTitreResponse;
import com.ged.entity.titresciel.CleCoursTitre;
import com.ged.entity.titresciel.CoursTitre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CoursTitreMapper {
    private final TitreMapper titreMapper;

    public CoursTitreMapper(TitreMapper titreMapper) {
        this.titreMapper = titreMapper;
    }

    public CoursTitreResponse deCoursTitre1(CoursTitre CoursTitre)
    {
        if(CoursTitre == null)
            return null;
        CoursTitreResponse CoursTitreDto = new CoursTitreResponse();
        BeanUtils.copyProperties(CoursTitre, CoursTitreDto);
        CleCoursTitreDto cleCoursTitreDto = new CleCoursTitreDto();
        cleCoursTitreDto.setIdTitre(CoursTitre.getIdCoursTitre().getIdTitre());
        cleCoursTitreDto.setDateCours(CoursTitre.getIdCoursTitre().getDateCours());
        CoursTitreDto.setIdCoursTitre(cleCoursTitreDto);
//        CoursTitreDto.setTitre(titreMapper.deTitre(CoursTitre.getTitre()));
        return CoursTitreDto;
    }

    public CoursTitreDto deCoursTitre(CoursTitre CoursTitre)
    {
        if(CoursTitre == null)
            return null;
        CoursTitreDto CoursTitreDto = new CoursTitreDto();
        BeanUtils.copyProperties(CoursTitre, CoursTitreDto);
        CleCoursTitreDto cleCoursTitreDto = new CleCoursTitreDto();
        cleCoursTitreDto.setIdTitre(CoursTitre.getIdCoursTitre().getIdTitre());
        cleCoursTitreDto.setDateCours(CoursTitre.getIdCoursTitre().getDateCours());
        CoursTitreDto.setIdCoursTitre(cleCoursTitreDto);
//        CoursTitreDto.setTitre(titreMapper.deTitre(CoursTitre.getTitre()));
        return CoursTitreDto;
    }

    public CoursTitre deCoursTitreDto(CoursTitreDto CoursTitreDTO)
    {
        CoursTitre CoursTitre = new CoursTitre();
        BeanUtils.copyProperties(CoursTitreDTO, CoursTitre);
        System.out.println("Test => " + CoursTitreDTO.getIdCoursTitre());
        CleCoursTitre cleCoursTitre = new CleCoursTitre();
        cleCoursTitre.setDateCours(CoursTitreDTO.getIdCoursTitre().getDateCours());
        cleCoursTitre.setIdTitre(CoursTitreDTO.getIdCoursTitre().getIdTitre());
        CoursTitre.setIdCoursTitre(cleCoursTitre);
        CoursTitre.setTitre(titreMapper.deTitreDto(CoursTitreDTO.getTitre()));
        return CoursTitre;
    }
}
