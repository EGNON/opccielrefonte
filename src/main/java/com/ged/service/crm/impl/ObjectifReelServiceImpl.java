package com.ged.service.crm.impl;

import com.ged.dao.crm.ObjectifReelDao;
import com.ged.dto.crm.ObjectifReelDto;
import com.ged.entity.crm.ObjectifReel;
import com.ged.mapper.crm.ObjectifReelMapper;
import com.ged.service.crm.ObjectifReelService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class ObjectifReelServiceImpl implements ObjectifReelService {
    private final ObjectifReelDao objectifReelDao;
    private final ObjectifReelMapper objectifReelMapper;

    public ObjectifReelServiceImpl(ObjectifReelDao objectifReelDao, ObjectifReelMapper objectifReelMapper) {
        this.objectifReelDao = objectifReelDao;
        this.objectifReelMapper = objectifReelMapper;
    }

    @Override
    public Page<ObjectifReelDto> afficherObjectifReels(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        Page<ObjectifReel> objectifReelPage=objectifReelDao.findAll(pageRequest);
        return new PageImpl<>(objectifReelPage.getContent().stream().map(objectifReelMapper::deObjectifReel).collect(Collectors.toList()),pageRequest,objectifReelPage.getTotalElements());
    }

    @Override
    public ObjectifReel afficherObjectifReelSelonId(long idObjectifReel) {
        return objectifReelDao.findById(idObjectifReel).orElseThrow(()-> new EntityNotFoundException("Objectif réel avec ID "+idObjectifReel+" est introuvable"));
    }

    @Override
    public ObjectifReelDto creerObjectifReel(ObjectifReelDto objectifReelDto) {
        ObjectifReel objectifReel=objectifReelMapper.deObjectifReelDto(objectifReelDto);
        ObjectifReel objectifReelSave=objectifReelDao.save(objectifReel);
        return objectifReelMapper.deObjectifReel(objectifReelSave);
    }

    @Override
    public ObjectifReelDto modifierObjectifReel(ObjectifReelDto objectifReelDto) {
        ObjectifReel objectifReel=objectifReelMapper.deObjectifReelDto(objectifReelDto);
        ObjectifReel objectifReelMaj=objectifReelDao.save(objectifReel);
        return objectifReelMapper.deObjectifReel(objectifReelMaj);
    }

    @Override
    public void supprimerObjectifReel(long idObjectifReel) {
        objectifReelDao.deleteById(idObjectifReel);
    }
}