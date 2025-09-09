package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.CleCoursTitreDto;
import com.ged.dto.titresciel.CoursTitreDto;
import com.ged.dto.titresciel.CoursTitreResponse;
import com.ged.entity.titresciel.CleCoursTitre;
import com.ged.entity.titresciel.CoursTitre;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    public CoursTitre deCoursTitreDto2(CoursTitreDto CoursTitreDTO)
    {
        CoursTitre CoursTitre = new CoursTitre();
        BeanUtils.copyProperties(CoursTitreDTO, CoursTitre);
        System.out.println("Test => " + CoursTitreDTO.getIdCoursTitre());
        CleCoursTitre cleCoursTitre = new CleCoursTitre();
        cleCoursTitre.setDateCours(CoursTitreDTO.getIdCoursTitre().getDateCours());
        cleCoursTitre.setIdTitre(CoursTitreDTO.getIdCoursTitre().getIdTitre());
        CoursTitre.setIdCoursTitre(cleCoursTitre);
        CoursTitre.setTitre(titreMapper.deTitreDto(CoursTitreDTO.getTitre()));
        CoursTitre.setDateVerification1(LocalDateTime.parse("2050-12-31T00:00:00"));
        CoursTitre.setDateVerification2(LocalDateTime.parse("2050-12-31T00:00:00"));
        CoursTitre.setEstVerifie1(false);
        CoursTitre.setEstVerifie2(false);
        CoursTitre.setUserLoginVerificateur1("");
        CoursTitre.setUserLoginVerificateur2("");
        if (CoursTitre.getOuverture() == null) {
            CoursTitre.setOuverture(BigDecimal.ZERO);
        }
        if (CoursTitre.getHaut() == null) {
            CoursTitre.setHaut(BigDecimal.ZERO);
        }
        if (CoursTitre.getBas() == null) {
            CoursTitre.setBas(BigDecimal.ZERO);
        }
        CoursTitre.setSupprimer(false);
        CoursTitre.setDateDernModifServeur(LocalDateTime.now());
        CoursTitre.setDateCreationServeur(LocalDateTime.now());
        CoursTitre.setDateDernModifClient(LocalDateTime.now());

        return CoursTitre;
    }
}
