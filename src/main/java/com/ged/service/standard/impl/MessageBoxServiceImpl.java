package com.ged.service.standard.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.AlerteDao;
import com.ged.dao.standard.MessageBoxDao;
import com.ged.dao.standard.PersonnelDao;
import com.ged.dto.standard.MessageBoxDto;
import com.ged.entity.standard.Alerte;
import com.ged.entity.standard.MessageBox;
import com.ged.entity.standard.Personne;
import com.ged.mapper.standard.MessageBoxMapper;
import com.ged.service.standard.MessageBoxService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageBoxServiceImpl implements MessageBoxService {
    private final MessageBoxDao messageBoxDao;
    private final MessageBoxMapper messageBoxMapper;
    private final AlerteDao alerteDao;
    private final PersonnelDao personnelDao;

    public MessageBoxServiceImpl(MessageBoxDao messageBoxDao, MessageBoxMapper messageBoxMapper, AlerteDao alerteDao, PersonnelDao personnelDao) {
        this.messageBoxDao = messageBoxDao;
        this.messageBoxMapper = messageBoxMapper;
        this.alerteDao = alerteDao;
        this.personnelDao = personnelDao;
    }

    @Override
    public Page<MessageBoxDto> afficherMsg(int page, int size) {
        return null;
    }

    @Override
    public List<MessageBoxDto> afficherTous() {
        return messageBoxDao.findAll().stream().map(messageBoxMapper::deMessageBox).collect(Collectors.toList());
    }

    @Override
    public MessageBox afficherMsgSelonId(Long idMsg) {
        return messageBoxDao.findById(idMsg).orElseThrow(() -> new EntityNotFoundException(MessageBox.class, idMsg.toString()));
    }

    @Override
    public MessageBoxDto creerMsg(MessageBoxDto messageBoxDto) {
        MessageBox messageBox = messageBoxMapper.deMessageBoxDto(messageBoxDto);
        Alerte alerte = alerteDao.findById(messageBoxDto.getAlerte().getIdAlerte()).orElseThrow(null);
        messageBox.setAlerte(alerte);
        Personne personne = personnelDao.findById(messageBoxDto.getDestinataire().getIdPersonne()).orElseThrow(null);
        messageBox.setDestinataire(personne);
        return messageBoxMapper.deMessageBox(messageBoxDao.save(messageBox));
    }

    @Override
    public MessageBoxDto modifierMsg(MessageBoxDto messageBoxDto) {
        MessageBox messageBox = messageBoxMapper.deMessageBoxDto(messageBoxDto);
        Alerte alerte = alerteDao.findById(messageBoxDto.getAlerte().getIdAlerte()).orElseThrow(null);
        messageBox.setAlerte(alerte);
        Personne personne = personnelDao.findById(messageBoxDto.getDestinataire().getIdPersonne()).orElseThrow(null);
        messageBox.setDestinataire(personne);
        return messageBoxMapper.deMessageBox(messageBoxDao.save(messageBox));
    }

    @Override
    public void supprimerMsg(Long idMsg) {
        messageBoxDao.deleteById(idMsg);
    }
}
