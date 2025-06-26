package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationDifferenceEstimationDto;
import com.ged.entity.opcciel.OperationDifferenceEstimation;
import com.ged.mapper.titresciel.TitreMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationDifferenceEstimationMapper {
    private final OpcvmMapper opcvmMapper;
    private final TitreMapper titreMapper;

    public OperationDifferenceEstimationMapper(OpcvmMapper opcvmMapper, TitreMapper titreMapper) {
        this.opcvmMapper = opcvmMapper;
        this.titreMapper = titreMapper;
    }

    public OperationDifferenceEstimationDto deOperationDifferenceEstimation(OperationDifferenceEstimation operationDifferenceEstimation)
    {
        if(operationDifferenceEstimation == null)
            return null;
        OperationDifferenceEstimationDto operationDifferenceEstimationDto = new OperationDifferenceEstimationDto();
        BeanUtils.copyProperties(operationDifferenceEstimation, operationDifferenceEstimationDto);
        if(operationDifferenceEstimation.getOpcvm()!=null)
            operationDifferenceEstimationDto.setOpcvm(opcvmMapper.deOpcvm(operationDifferenceEstimation.getOpcvm()));

        if(operationDifferenceEstimation.getTitre()!=null)
            operationDifferenceEstimationDto.setTitre(titreMapper.deTitre(operationDifferenceEstimation.getTitre()));

        return operationDifferenceEstimationDto;
    }

//    public OperationDifferenceEstimationDto deOperationDifferenceEstimationProjection(OperationDifferenceEstimationProjection operationDifferenceEstimation)
//    {
//        if(operationDifferenceEstimation == null)
//            return null;
//        OperationDifferenceEstimationDto operationDifferenceEstimationDto = new OperationDifferenceEstimationDto();
//        BeanUtils.copyProperties(operationDifferenceEstimation, operationDifferenceEstimationDto);
//        if(operationDifferenceEstimation.getOpcvm()!=null)
//            operationDifferenceEstimationDto.setOpcvm(opcvmMapper.deOpcvm(operationDifferenceEstimation.getOpcvm()));
//
//        if(operationDifferenceEstimation.getNatureOperation()!=null)
//            operationDifferenceEstimationDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationDifferenceEstimation.getNatureOperation()));
//
//        if(operationDifferenceEstimation.getTitre()!=null)
//            operationDifferenceEstimationDto.setTitre(titreMapper.deTitre(operationDifferenceEstimation.getTitre()));
//
//        return operationDifferenceEstimationDto;
//    }

    public OperationDifferenceEstimation deOperationDifferenceEstimationDto(OperationDifferenceEstimationDto operationDifferenceEstimationDto)
    {
        if(operationDifferenceEstimationDto == null)
            return null;
        OperationDifferenceEstimation operationDifferenceEstimation= new OperationDifferenceEstimation();
        BeanUtils.copyProperties(operationDifferenceEstimationDto, operationDifferenceEstimation);
        BeanUtils.copyProperties(operationDifferenceEstimationDto, operationDifferenceEstimation);
        operationDifferenceEstimation.setOpcvm(opcvmMapper.deOpcvmDto(operationDifferenceEstimationDto.getOpcvm()));
        operationDifferenceEstimation.setTitre(titreMapper.deTitreDto(operationDifferenceEstimationDto.getTitre()));
        return operationDifferenceEstimation;
    }

}
