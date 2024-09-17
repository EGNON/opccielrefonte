package com.ged.mapper.standard;

import com.ged.dto.standard.EnvoiMailDto;
import com.ged.entity.standard.EnvoiMail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EnvoiMailMapper {
    private final PersonneMapper personneMapper;
    private final MailMapper mailMapper;

    public EnvoiMailMapper(PersonneMapper personneMapper, MailMapper mailMapper) {
        this.personneMapper = personneMapper;
        this.mailMapper = mailMapper;
    }

    public EnvoiMailDto deEnvoiMail(EnvoiMail envoiMail)
    {
        EnvoiMailDto envoiMailDto = new EnvoiMailDto();
        BeanUtils.copyProperties(envoiMail, envoiMailDto);
        if(envoiMail.getPersonne()!=null)
        {
            envoiMailDto.setPersonneDto(personneMapper.dePersonne(envoiMail.getPersonne()));
        }
        if(envoiMail.getMail()!=null)
        {
            envoiMailDto.setMailDto(mailMapper.deMail(envoiMail.getMail()));
        }
        return envoiMailDto;
    }

    public EnvoiMail deEnvoiMailDto(EnvoiMailDto envoiMailDto)
    {
        EnvoiMail envoiMail = new EnvoiMail();
        BeanUtils.copyProperties(envoiMailDto, envoiMail);
        if(envoiMailDto.getPersonneDto()!=null)
        {
            envoiMail.setPersonne(personneMapper.dePersonneDto(envoiMailDto.getPersonneDto()));
        }
        if(envoiMailDto.getMailDto()!=null)
        {
            envoiMail.setMail(mailMapper.deMailDto(envoiMailDto.getMailDto()));
        }
        return envoiMail;
    }
}
