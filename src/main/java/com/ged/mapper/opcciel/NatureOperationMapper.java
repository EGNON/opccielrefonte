package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.NatureOperationDto;
import com.ged.entity.opcciel.comptabilite.NatureOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class NatureOperationMapper {
    private final TypeOperationMapper typeOperationMapper;
    private final JournalMapper journalMapper;

    public NatureOperationMapper(TypeOperationMapper typeOperationMapper, JournalMapper journalMapper) {
        this.typeOperationMapper = typeOperationMapper;
        this.journalMapper = journalMapper;
    }

    public NatureOperationDto deNatureOperation(NatureOperation natureOperation)
    {
        NatureOperationDto natureOperationDto = new NatureOperationDto();
        BeanUtils.copyProperties(natureOperation, natureOperationDto);
        if(natureOperation.getTypeOperation()!=null)
        {
            natureOperationDto.setTypeOperation(typeOperationMapper.deTypeOperation(natureOperation.getTypeOperation()));
        }
        if(natureOperation.getJournal()!=null)
        {
            natureOperationDto.setJournal(journalMapper.deJournal(natureOperation.getJournal()));
        }
        return natureOperationDto;
    }

    public NatureOperation deNatureOperationDto(NatureOperationDto natureOperationDto)
    {
        if(natureOperationDto == null) {
            return null;
        }
        NatureOperation natureOperation = new NatureOperation();
        BeanUtils.copyProperties(natureOperationDto, natureOperation);
        return natureOperation;
    }
}
