package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationExtourneVDEDto;
import com.ged.entity.opcciel.OperationExtourneVDE;
import com.ged.mapper.titresciel.TitreMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationExtourneVDEMapper {
    private final OpcvmMapper opcvmMapper;
    private final TitreMapper titreMapper;

    public OperationExtourneVDEMapper(OpcvmMapper opcvmMapper, TitreMapper titreMapper) {
        this.opcvmMapper = opcvmMapper;
        this.titreMapper = titreMapper;
    }

    public OperationExtourneVDEDto deOperationExtourneVDE(OperationExtourneVDE operationExtourneVDE)
    {
        if(operationExtourneVDE == null)
            return null;
        OperationExtourneVDEDto operationExtourneVDEDto = new OperationExtourneVDEDto();
        BeanUtils.copyProperties(operationExtourneVDE, operationExtourneVDEDto);
        if(operationExtourneVDE.getOpcvm()!=null)
            operationExtourneVDEDto.setOpcvm(opcvmMapper.deOpcvm(operationExtourneVDE.getOpcvm()));

        if(operationExtourneVDE.getTitre()!=null)
            operationExtourneVDEDto.setTitre(titreMapper.deTitre(operationExtourneVDE.getTitre()));

        return operationExtourneVDEDto;
    }

//    public OperationExtourneVDEDto deOperationExtourneVDEProjection(OperationExtourneVDEProjection operationExtourneVDE)
//    {
//        if(operationExtourneVDE == null)
//            return null;
//        OperationExtourneVDEDto operationExtourneVDEDto = new OperationExtourneVDEDto();
//        BeanUtils.copyProperties(operationExtourneVDE, operationExtourneVDEDto);
//        if(operationExtourneVDE.getOpcvm()!=null)
//            operationExtourneVDEDto.setOpcvm(opcvmMapper.deOpcvm(operationExtourneVDE.getOpcvm()));
//
//        if(operationExtourneVDE.getNatureOperation()!=null)
//            operationExtourneVDEDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationExtourneVDE.getNatureOperation()));
//
//        if(operationExtourneVDE.getTitre()!=null)
//            operationExtourneVDEDto.setTitre(titreMapper.deTitre(operationExtourneVDE.getTitre()));
//
//        return operationExtourneVDEDto;
//    }

    public OperationExtourneVDE deOperationExtourneVDEDto(OperationExtourneVDEDto operationExtourneVDEDto)
    {
        if(operationExtourneVDEDto == null)
            return null;

        OperationExtourneVDE operationExtourneVDE= new OperationExtourneVDE();
        BeanUtils.copyProperties(operationExtourneVDEDto, operationExtourneVDE);
        BeanUtils.copyProperties(operationExtourneVDEDto, operationExtourneVDE);
        operationExtourneVDE.setOpcvm(opcvmMapper.deOpcvmDto(operationExtourneVDEDto.getOpcvm()));
        operationExtourneVDE.setTitre(titreMapper.deTitreDto(operationExtourneVDEDto.getTitre()));
        return operationExtourneVDE;
    }

}
