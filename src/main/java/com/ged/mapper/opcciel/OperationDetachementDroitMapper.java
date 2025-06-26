package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationDetachementDroitDto;
import com.ged.dto.opcciel.OperationDetachementDto;
import com.ged.entity.opcciel.OperationDetachement;
import com.ged.entity.opcciel.OperationDetachementDroit;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.mapper.titresciel.TitreMapper;
import com.ged.projection.OperationDetachementDroitProjection;
import com.ged.projection.OperationDetachementProjection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationDetachementDroitMapper {
    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final TransactionMapper transactionMapper;
    private final TitreMapper titreMapper;

    public OperationDetachementDroitMapper(OpcvmMapper opcvmMapper, NatureOperationMapper natureOperationMapper, TransactionMapper transactionMapper, TitreMapper titreMapper) {
        this.opcvmMapper = opcvmMapper;
        this.natureOperationMapper = natureOperationMapper;
        this.transactionMapper = transactionMapper;
        this.titreMapper = titreMapper;
    }

    public OperationDetachementDroitDto deOperationDetachementDroit(OperationDetachementDroit operationDetachement)
    {
        if(operationDetachement == null)
            return null;
        OperationDetachementDroitDto operationDetachementDto = new OperationDetachementDroitDto();

        if(operationDetachement.getOpcvm()!=null)
            operationDetachementDto.setOpcvm(opcvmMapper.deOpcvm(operationDetachement.getOpcvm()));

        if(operationDetachement.getNatureOperation()!=null)
            operationDetachementDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationDetachement.getNatureOperation()));
        System.out.println(operationDetachement.getTitre());

        if(operationDetachement.getTitre()!=null)
            operationDetachementDto.setTitre(titreMapper.deTitre(operationDetachement.getTitre()));

        return operationDetachementDto;
    }

    public OperationDetachementDroitDto deOperationDetachementDroitProjection(OperationDetachementDroitProjection operationDetachement)
    {
        if(operationDetachement == null)
            return null;
        OperationDetachementDroitDto operationDetachementDto = new OperationDetachementDroitDto();
        BeanUtils.copyProperties(operationDetachement, operationDetachementDto);
        if(operationDetachement.getOpcvm()!=null)
            operationDetachementDto.setOpcvm(opcvmMapper.deOpcvm(operationDetachement.getOpcvm()));

        if(operationDetachement.getNatureOperation()!=null)
            operationDetachementDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationDetachement.getNatureOperation()));

        if(operationDetachement.getTitre()!=null)
            operationDetachementDto.setTitre(titreMapper.deTitre(operationDetachement.getTitre()));

        return operationDetachementDto;
    }

    public OperationDetachementDroit deOperationDetachementDroitDto(OperationDetachementDroitDto operationDetachementDto)
    {
        if(operationDetachementDto == null)
            return null;
        OperationDetachementDroit operationDetachement= new OperationDetachementDroit();
        BeanUtils.copyProperties(operationDetachementDto, operationDetachement);
        operationDetachement.setOpcvm(opcvmMapper.deOpcvmDto(operationDetachementDto.getOpcvm()));
        operationDetachement.setNatureOperation(natureOperationMapper.deNatureOperationDto(operationDetachementDto.getNatureOperation()));
        operationDetachement.setTransaction(transactionMapper.deTransactionDto(operationDetachementDto.getTransaction()));
        return operationDetachement;
    }

}
