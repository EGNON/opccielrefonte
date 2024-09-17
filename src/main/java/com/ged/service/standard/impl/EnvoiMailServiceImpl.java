package com.ged.service.standard.impl;

import com.ged.dao.standard.EnvoiMailDao;
import com.ged.dao.standard.MailDao;
import com.ged.dto.standard.EnvoiMailDto;
import com.ged.entity.standard.CleEnvoiMail;
import com.ged.entity.standard.EnvoiMail;
import com.ged.entity.standard.Mail;
import com.ged.mapper.standard.EnvoiMailMapper;
import com.ged.mapper.standard.MailMapper;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.service.standard.EnvoiMailService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnvoiMailServiceImpl implements EnvoiMailService {
    private final EnvoiMailDao envoiMailDao;
    private final MailDao mailDao;
    private final EnvoiMailMapper envoiMailMapper;
    private final PersonneMapper personneMapper;
    private final MailMapper mailMapper;

    public EnvoiMailServiceImpl(EnvoiMailDao envoiMailDao, MailDao mailDao, EnvoiMailMapper envoiMailMapper, PersonneMapper personneMapper, MailMapper mailMapper) {
        this.envoiMailDao = envoiMailDao;
        this.mailDao = mailDao;
        this.envoiMailMapper = envoiMailMapper;
        this.personneMapper = personneMapper;
        this.mailMapper = mailMapper;
    }

    @Override
    public Page<EnvoiMailDto> afficherEnvoiMails(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<EnvoiMail> envoiMailPage = envoiMailDao.findAll(pageRequest);
        return new PageImpl<>(envoiMailPage.getContent().stream().map(envoiMailMapper::deEnvoiMail).collect(Collectors.toList()), pageRequest, envoiMailPage.getTotalElements());
    }

    @Override
    public EnvoiMail afficherEnvoiMailSelonId(CleEnvoiMail idEnvoiMail) {
        return envoiMailDao.findById(idEnvoiMail).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
    }

    @Override
    public List<EnvoiMailDto> afficherEnvoiMailSelonMail(long idMail) {
        Mail mail=mailDao.findById(idMail).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
        return envoiMailDao.findByMail(mail).stream().map(envoiMailMapper::deEnvoiMail).collect(Collectors.toList());
    }

    @Override
    public EnvoiMailDto creerEnvoiMail(EnvoiMailDto envoiMailDto) {
        EnvoiMail envoiMail = envoiMailMapper.deEnvoiMailDto(envoiMailDto);

        CleEnvoiMail cleEnvoiMail= new CleEnvoiMail();
        cleEnvoiMail.setIdPersonne(envoiMailDto.getPersonneDto().getIdPersonne());
        cleEnvoiMail.setIdMail(envoiMailDto.getMailDto().getIdMail());
        envoiMail.setIdEnvoi(cleEnvoiMail);
        if(envoiMailDto.getPersonneDto() != null)
        {
            envoiMail.setPersonne(personneMapper.dePersonneDto(envoiMailDto.getPersonneDto()));
        }
        if(envoiMailDto.getMailDto() != null)
        {
            envoiMail.setMail(mailMapper.deMailDto(envoiMailDto.getMailDto()));
        }
        EnvoiMail envoiMailSaved = envoiMailDao.save(envoiMail);
        return envoiMailMapper.deEnvoiMail(envoiMailSaved);
    }

    @Override
    public void creerEnvoiMail(EnvoiMailDto[] envoiMailDto) {
        for(EnvoiMailDto o:envoiMailDto){
            creerEnvoiMail(o);
        }
    }

    @Override
    public EnvoiMailDto modifierEnvoiMail(EnvoiMailDto envoiMailDto) {
        CleEnvoiMail cleEnvoiMail = new CleEnvoiMail();
        cleEnvoiMail.setIdMail(envoiMailDto.getMailDto().getIdMail());
        cleEnvoiMail.setIdPersonne(envoiMailDto.getPersonneDto().getIdPersonne());
        EnvoiMail envoiMail = afficherEnvoiMailSelonId(cleEnvoiMail);
        if(envoiMail.getIdEnvoi() == null)
            return envoiMailDto;
        envoiMail = envoiMailMapper.deEnvoiMailDto(envoiMailDto);
        envoiMail = envoiMailDao.save(envoiMail);
        return envoiMailMapper.deEnvoiMail(envoiMail);
    }

    @Override
    public void supprimerEnvoiMail(CleEnvoiMail cleEnvoiMail) {
        envoiMailDao.deleteById(cleEnvoiMail);
    }

    @Override
    public void supprimerEnvoiMailSelonMail(long idMail) {
        Mail mail=mailDao.findById(idMail).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
        envoiMailDao.deleteByMail(mail);
    }
}
