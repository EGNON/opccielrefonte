package com.ged.mapper.crm;

import com.ged.dto.crm.ModeleObjectifDto;
import com.ged.entity.crm.ModeleObjectif;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class ModeleObjectifMapper {
    private final DetailObjectifMapper detailObjectifMapper;

    public ModeleObjectifMapper(DetailObjectifMapper detailObjectifMapper) {
        this.detailObjectifMapper = detailObjectifMapper;
    }

    public ModeleObjectifDto deModeleObjectif(ModeleObjectif modeleObjectif)
    {
        ModeleObjectifDto modeleObjectifDto = new ModeleObjectifDto();
        BeanUtils.copyProperties(modeleObjectif, modeleObjectifDto);
        ModelMapper modelMapper = new ModelMapper();
        modeleObjectifDto.setDetailObjectifs(modeleObjectif.getDetailObjectifs().stream().map(detailObjectifMapper::deDetailObjectif)
                .collect(Collectors.toSet()));
        return modeleObjectifDto;
    }

    public ModeleObjectif deModeleObjectifDto(ModeleObjectifDto modeleObjectifDto)
    {
        ModeleObjectif modeleObjectif = new ModeleObjectif();
        BeanUtils.copyProperties(modeleObjectifDto, modeleObjectif);
        return modeleObjectif;
    }
}
