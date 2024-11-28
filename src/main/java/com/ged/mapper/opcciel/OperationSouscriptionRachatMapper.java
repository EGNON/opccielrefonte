package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.entity.opcciel.OperationSouscriptionRachat;
import com.ged.mapper.standard.PersonneMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationSouscriptionRachatMapper {
    private final OpcvmMapper opcvmMapper;
    private final PersonneMapper personneMapper;
    private final NatureOperationMapper natureOperationMapper;

    public OperationSouscriptionRachatMapper(OpcvmMapper opcvmMapper, PersonneMapper personneMapper, NatureOperationMapper natureOperationMapper) {
        this.opcvmMapper = opcvmMapper;
        this.personneMapper = personneMapper;
        this.natureOperationMapper = natureOperationMapper;
    }

    public OperationSouscriptionRachatDto deOperationSouscriptionRachat(OperationSouscriptionRachat operationSouscriptionRachat)
    {
        OperationSouscriptionRachatDto operationSouscriptionRachatDto = new OperationSouscriptionRachatDto();
        BeanUtils.copyProperties(operationSouscriptionRachat, operationSouscriptionRachatDto);
        operationSouscriptionRachatDto.setOpcvm(opcvmMapper.deOpcvm(operationSouscriptionRachat.getOpcvm()));
        operationSouscriptionRachatDto.setActionnaire(personneMapper.dePersonne(operationSouscriptionRachat.getActionnaire()));
        operationSouscriptionRachatDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationSouscriptionRachat.getNatureOperation()));
        return operationSouscriptionRachatDto;
    }

    public OperationSouscriptionRachat deOperationSouscriptionRachatDto(OperationSouscriptionRachatDto operationSouscriptionRachatDto)
    {
        if(operationSouscriptionRachatDto == null)
            return null;
        OperationSouscriptionRachat operationSouscriptionRachat= new OperationSouscriptionRachat();
        BeanUtils.copyProperties(operationSouscriptionRachatDto, operationSouscriptionRachat);
        operationSouscriptionRachat.setOpcvm(opcvmMapper.deOpcvmDto(operationSouscriptionRachatDto.getOpcvm()));
        operationSouscriptionRachat.setActionnaire(personneMapper.dePersonneDto(operationSouscriptionRachatDto.getActionnaire()));
        operationSouscriptionRachat.setNatureOperation(natureOperationMapper.deNatureOperationDto(operationSouscriptionRachatDto.getNatureOperation()));
        return operationSouscriptionRachat;
    }

}
