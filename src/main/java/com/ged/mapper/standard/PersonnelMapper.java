package com.ged.mapper.standard;

import com.ged.dto.standard.PaysDto;
import com.ged.dto.standard.PersonnelDto;
import com.ged.dto.standard.ProfessionDto;
import com.ged.entity.standard.Personnel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonnelMapper {
    @Autowired
    private ModelMapper modelMapper;
    public PersonnelDto dePersonnel(Personnel personnel)
    {
        if(personnel == null) {
            return null;
        }
        PersonnelDto personnelDto = new PersonnelDto();
        BeanUtils.copyProperties(personnel, personnelDto);
        if(personnel.getProfession() != null) {
            personnelDto.setProfession(modelMapper.map(personnel.getProfession(), ProfessionDto.class));
        }
        if(personnel.getPaysResidence()!=null) {
            personnelDto.setPaysResidence(modelMapper.map(personnel.getPaysResidence(), PaysDto.class));
        }
        if(personnel.getPaysNationalite()!=null) {
            personnelDto.setPaysNationalite(modelMapper.map(personnel.getPaysNationalite(), PaysDto.class));
        }
        return personnelDto;
    }

    public Personnel dePersonnelDto(PersonnelDto personnelDto)
    {
        if(personnelDto == null) {
            return null;
        }
        Personnel personnel= new Personnel();
        BeanUtils.copyProperties(personnelDto, personnel);
        return personnel;
    }
}
