package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.OperationEvenementSurValeurDto;
import com.ged.entity.opcciel.OperationEvenementSurValeur;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.mapper.titresciel.TitreMapper;
import com.ged.projection.OperationEvenementSurValeurProjection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OperationEvenementSurValeurMapper {
    private final OpcvmMapper opcvmMapper;
    private final PersonneMapper personneMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final TransactionMapper transactionMapper;
    private final TitreMapper titreMapper;
    private final OperationDetachementMapper operationDetachementMapper;

    public OperationEvenementSurValeurMapper(OpcvmMapper opcvmMapper, PersonneMapper personneMapper, NatureOperationMapper natureOperationMapper, TransactionMapper transactionMapper, TitreMapper titreMapper, OperationDetachementMapper operationDetachementMapper) {
        this.opcvmMapper = opcvmMapper;
        this.personneMapper = personneMapper;
        this.natureOperationMapper = natureOperationMapper;
        this.transactionMapper = transactionMapper;
        this.titreMapper = titreMapper;
        this.operationDetachementMapper = operationDetachementMapper;
    }

    public OperationEvenementSurValeurDto deOperationEvenementSurValeur(OperationEvenementSurValeur operationEvenementSurValeur)
    {
        if(operationEvenementSurValeur == null)
            return null;
        OperationEvenementSurValeurDto operationEvenementSurValeurDto = new OperationEvenementSurValeurDto();
        BeanUtils.copyProperties(operationEvenementSurValeur, operationEvenementSurValeurDto);

        if(operationEvenementSurValeur.getOpcvm()!=null)
            operationEvenementSurValeurDto.setOpcvm(opcvmMapper.deOpcvm(operationEvenementSurValeur.getOpcvm()));

        if(operationEvenementSurValeur.getIntervenant()!=null)
            operationEvenementSurValeurDto.setIntervenant(personneMapper.dePersonne(operationEvenementSurValeur.getIntervenant()));

        if(operationEvenementSurValeur.getNatureOperation()!=null)
            operationEvenementSurValeurDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationEvenementSurValeur.getNatureOperation()));
        System.out.println(operationEvenementSurValeur.getTitre());

        if(operationEvenementSurValeur.getTitre()!=null)
            operationEvenementSurValeurDto.setTitre(titreMapper.deTitre(operationEvenementSurValeur.getTitre()));

        if(operationEvenementSurValeur.getOperationDetachement()!=null)
            operationEvenementSurValeurDto.setOperationDetachement(operationDetachementMapper.deOperationDetachement(operationEvenementSurValeur.getOperationDetachement()));

        return operationEvenementSurValeurDto;
    }

    public OperationEvenementSurValeurDto deOperationEvenementSurValeurProjection(OperationEvenementSurValeurProjection operationEvenementSurValeur)
    {
        if(operationEvenementSurValeur == null)
            return null;
        OperationEvenementSurValeurDto operationEvenementSurValeurDto = new OperationEvenementSurValeurDto();
        BeanUtils.copyProperties(operationEvenementSurValeur, operationEvenementSurValeurDto);
        if(operationEvenementSurValeur.getOpcvm()!=null)
            operationEvenementSurValeurDto.setOpcvm(opcvmMapper.deOpcvm(operationEvenementSurValeur.getOpcvm()));

        if(operationEvenementSurValeur.getPersonne()!=null)
            operationEvenementSurValeurDto.setIntervenant(personneMapper.dePersonne(operationEvenementSurValeur.getPersonne()));

        if(operationEvenementSurValeur.getNatureOperation()!=null)
            operationEvenementSurValeurDto.setNatureOperation(natureOperationMapper.deNatureOperation(operationEvenementSurValeur.getNatureOperation()));

        if(operationEvenementSurValeur.getTitre()!=null)
            operationEvenementSurValeurDto.setTitre(titreMapper.deTitre(operationEvenementSurValeur.getTitre()));

        if(operationEvenementSurValeur.getOperationDetachement()!=null)
            operationEvenementSurValeurDto.setOperationDetachement(operationDetachementMapper.deOperationDetachement(operationEvenementSurValeur.getOperationDetachement()));

        return operationEvenementSurValeurDto;
    }

    public OperationEvenementSurValeur deOperationEvenementSurValeurDto(OperationEvenementSurValeurDto operationEvenementSurValeurDto)
    {
        if(operationEvenementSurValeurDto == null)
            return null;
        OperationEvenementSurValeur operationEvenementSurValeur= new OperationEvenementSurValeur();
        BeanUtils.copyProperties(operationEvenementSurValeurDto, operationEvenementSurValeur);
        operationEvenementSurValeur.setOpcvm(opcvmMapper.deOpcvmDto(operationEvenementSurValeurDto.getOpcvm()));
        operationEvenementSurValeur.setActionnaire(personneMapper.dePersonneDto(operationEvenementSurValeurDto.getActionnaire()));
        operationEvenementSurValeur.setNatureOperation(natureOperationMapper.deNatureOperationDto(operationEvenementSurValeurDto.getNatureOperation()));
        operationEvenementSurValeur.setTransaction(transactionMapper.deTransactionDto(operationEvenementSurValeurDto.getTransaction()));
        return operationEvenementSurValeur;
    }

}
