package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.CompartimentDto;
import com.ged.entity.titresciel.Compartiment;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class CompartimentMapper {
    public CompartimentDto deCompartiment(Compartiment compartiment)
    {
        if(compartiment == null)
            return null;
        CompartimentDto compartimentDto = new CompartimentDto();
        BeanUtils.copyProperties(compartiment, compartimentDto);
        return compartimentDto;
    }

    public Compartiment deCompartimentDto(CompartimentDto compartimentDTO)
    {
        Compartiment compartiment = new Compartiment();
        BeanUtils.copyProperties(compartimentDTO, compartiment);
        return compartiment;
    }
}
