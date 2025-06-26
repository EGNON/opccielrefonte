package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationDetachementDto;
import com.ged.entity.opcciel.OperationDetachement;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.mapper.titresciel.TitreMapper;
import com.ged.projection.OperationDetachementProjection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationDetachementMapper {
    private final OpcvmMapper opcvmMapper;
    private final PersonneMapper personneMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final TransactionMapper transactionMapper;
    private final TitreMapper titreMapper;

    public OperationDetachementMapper(OpcvmMapper opcvmMapper, PersonneMapper personneMapper, NatureOperationMapper natureOperationMapper, TransactionMapper transactionMapper, TitreMapper titreMapper) {
        this.opcvmMapper = opcvmMapper;
        this.personneMapper = personneMapper;
        this.natureOperationMapper = natureOperationMapper;
        this.transactionMapper = transactionMapper;
        this.titreMapper = titreMapper;
    }

    public OperationDetachementDto deOperationDetachement(OperationDetachement operationDetachement)
    {
        if(operationDetachement == null)
            return null;
        OperationDetachementDto operationDetachementDto = new OperationDetachementDto();
        BeanUtils.copyProperties(operationDetachement, operationDetachementDto);
//        operationDetachementDto.setOpcvm(opcvmMapper.deOpcvm(operationDetachement.getOpcvm()));
//        operationDetachementDto.setActionnaire(personneMapper.dePersonne(operationDetachement.getActionnaire()));
//        operationDetachementDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationDetachement.getNatureOperation()));
//        operationDetachementDto.setTransaction(transactionMapper.deTransaction(operationDetachement.getTransaction()));
        if(operationDetachement.getOpcvm()!=null)
            operationDetachementDto.setOpcvm(opcvmMapper.deOpcvm(operationDetachement.getOpcvm()));

        if(operationDetachement.getIntervenant()!=null)
            operationDetachementDto.setIntervenant(personneMapper.dePersonne(operationDetachement.getIntervenant()));

        if(operationDetachement.getNatureOperation()!=null)
            operationDetachementDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationDetachement.getNatureOperation()));
        System.out.println(operationDetachement.getTitre());

        if(operationDetachement.getTitre()!=null)
            operationDetachementDto.setTitre(titreMapper.deTitre(operationDetachement.getTitre()));

        return operationDetachementDto;
    }

    public OperationDetachementDto deOperationDetachementProjection(OperationDetachementProjection operationDetachement)
    {
        if(operationDetachement == null)
            return null;
        OperationDetachementDto operationDetachementDto = new OperationDetachementDto();
        BeanUtils.copyProperties(operationDetachement, operationDetachementDto);
        if(operationDetachement.getOpcvm()!=null)
            operationDetachementDto.setOpcvm(opcvmMapper.deOpcvm(operationDetachement.getOpcvm()));

        if(operationDetachement.getPersonne()!=null)
            operationDetachementDto.setIntervenant(personneMapper.dePersonne(operationDetachement.getPersonne()));

        if(operationDetachement.getNatureOperation()!=null)
            operationDetachementDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationDetachement.getNatureOperation()));

        if(operationDetachement.getTitre()!=null)
            operationDetachementDto.setTitre(titreMapper.deTitre(operationDetachement.getTitre()));

        return operationDetachementDto;
    }

    public OperationDetachement deOperationDetachementDto(OperationDetachementDto operationDetachementDto)
    {
        if(operationDetachementDto == null)
            return null;
        OperationDetachement operationDetachement= new OperationDetachement();
        BeanUtils.copyProperties(operationDetachementDto, operationDetachement);
        operationDetachement.setOpcvm(opcvmMapper.deOpcvmDto(operationDetachementDto.getOpcvm()));
        operationDetachement.setActionnaire(personneMapper.dePersonneDto(operationDetachementDto.getActionnaire()));
        operationDetachement.setNatureOperation(natureOperationMapper.deNatureOperationDto(operationDetachementDto.getNatureOperation()));
        operationDetachement.setTransaction(transactionMapper.deTransactionDto(operationDetachementDto.getTransaction()));
        return operationDetachement;
    }

}
