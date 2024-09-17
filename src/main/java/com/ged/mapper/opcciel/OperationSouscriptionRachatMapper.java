package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationSouscriptionRachatMapper {
    public OperationSouscriptionRachatDto deOperationSouscriptionRachat(OperationSouscriptionRachat operationSouscriptionRachat)
    {
        OperationSouscriptionRachatDto operationSouscriptionRachatDto = new OperationSouscriptionRachatDto();
        BeanUtils.copyProperties(operationSouscriptionRachat, operationSouscriptionRachatDto);
        return operationSouscriptionRachatDto;
    }

    public OperationSouscriptionRachat deOperationSouscriptionRachatDto(OperationSouscriptionRachatDto operationSouscriptionRachatDto)
    {
        if(operationSouscriptionRachatDto == null)
            return null;
        OperationSouscriptionRachat operationSouscriptionRachat= new OperationSouscriptionRachat();
        BeanUtils.copyProperties(operationSouscriptionRachatDto, operationSouscriptionRachat);
        return operationSouscriptionRachat;
    }

}
