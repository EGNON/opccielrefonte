package com.ged.mapper.standard;

import com.ged.dto.standard.MessageBoxDto;
import com.ged.entity.standard.MessageBox;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class MessageBoxMapper {
    private final AlerteMapper alerteMapper;
    private final PersonneMapper personneMapper;

    public MessageBoxMapper(AlerteMapper alerteMapper, PersonneMapper personneMapper) {
        this.alerteMapper = alerteMapper;
        this.personneMapper = personneMapper;
    }

    public MessageBoxDto deMessageBox(MessageBox messageBox)
    {
        MessageBoxDto messageBoxDto = new MessageBoxDto();
        BeanUtils.copyProperties(messageBox, messageBoxDto);
        if(messageBox.getAlerte() != null) messageBoxDto.setAlerte(alerteMapper.deAlerte(messageBox.getAlerte()));
        if(messageBox.getDestinataire() != null) messageBoxDto.setDestinataire(personneMapper.dePersonne(messageBox.getDestinataire()));
        return messageBoxDto;
    }

    public MessageBox deMessageBoxDto(MessageBoxDto messageBoxDto)
    {
        MessageBox messageBox = new MessageBox();
        BeanUtils.copyProperties(messageBoxDto, messageBox);
        return messageBox;
    }
}
