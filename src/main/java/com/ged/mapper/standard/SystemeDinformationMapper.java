package com.ged.mapper.standard;

import com.ged.dto.standard.SystemeDinformationDto;
import com.ged.entity.standard.SystemeDinformation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class SystemeDinformationMapper {
    public SystemeDinformationDto deSystemeDinformation(SystemeDinformation systemeDinformation) {
        SystemeDinformationDto systemeDinformationDto = new SystemeDinformationDto();
        BeanUtils.copyProperties(systemeDinformation, systemeDinformationDto);
       
        return systemeDinformationDto;
    }

    public SystemeDinformation deSystemeDinformationDto(SystemeDinformationDto systemeDinformationDto) {
        SystemeDinformation systemeDinformation = new SystemeDinformation();
        BeanUtils.copyProperties(systemeDinformationDto, systemeDinformation);
      
        return systemeDinformation;
    }
}
