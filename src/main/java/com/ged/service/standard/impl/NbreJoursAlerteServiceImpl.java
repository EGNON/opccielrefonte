package com.ged.service.standard.impl;

import com.ged.dao.standard.AlerteDao;
import com.ged.dao.standard.NbreJoursAlerteDao;
import com.ged.dto.standard.NbreJoursAlerteDto;
import com.ged.entity.standard.Alerte;
import com.ged.entity.standard.CleNbreJoursAlerte;
import com.ged.entity.standard.NbreJoursAlerte;
import com.ged.service.standard.NbreJoursAlerteService;
import com.ged.mapper.standard.AlerteMapper;
import com.ged.mapper.standard.NbreJoursAlerteMapper;
import com.ged.mapper.standard.NbreJoursMapper;
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
public class NbreJoursAlerteServiceImpl implements NbreJoursAlerteService {
    private final NbreJoursAlerteDao nbreJoursAlerteDao;
    private final NbreJoursAlerteMapper nbreJoursAlerteMapper;
    private final NbreJoursMapper nbreJoursMapper;
    private final AlerteMapper alerteMapper;
    private final AlerteDao alerteDao;

    public NbreJoursAlerteServiceImpl(NbreJoursAlerteDao nbreJoursAlerteDao, NbreJoursAlerteMapper nbreJoursAlerteMapper, NbreJoursMapper nbreJoursMapper, AlerteMapper alerteMapper, AlerteDao alerteDao) {
        this.nbreJoursAlerteDao = nbreJoursAlerteDao;
        this.nbreJoursAlerteMapper = nbreJoursAlerteMapper;
        this.nbreJoursMapper = nbreJoursMapper;
        this.alerteMapper = alerteMapper;
        this.alerteDao = alerteDao;
    }

    @Override
    public Page<NbreJoursAlerteDto> afficherNbreJoursAlertes(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<NbreJoursAlerte> NbreJoursAlertePage = nbreJoursAlerteDao.findAll(pageRequest);
        return new PageImpl<>(NbreJoursAlertePage.getContent().stream().map(nbreJoursAlerteMapper::deNbreJoursAlerte).collect(Collectors.toList()), pageRequest, NbreJoursAlertePage.getTotalElements());
    }

    @Override
    public NbreJoursAlerte afficherNbreJoursAlerteSelonId(CleNbreJoursAlerte idNbreJoursAlerte) {
        return nbreJoursAlerteDao.findById(idNbreJoursAlerte).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
    }

    @Override
    public List<NbreJoursAlerteDto> afficherNbreJoursAlerteSelonAlerte(long idAlerte) {
        Alerte alerte=alerteDao.findById(idAlerte);
        return nbreJoursAlerteDao.findByAlerte(alerte).stream().map(nbreJoursAlerteMapper::deNbreJoursAlerte).collect(Collectors.toList());
    }

    @Override
    public NbreJoursAlerteDto creerNbreJoursAlerte(NbreJoursAlerteDto nbreJoursAlerte) {
        NbreJoursAlerte NbreJoursAlerte = nbreJoursAlerteMapper.deNbreJoursAlerteDto(nbreJoursAlerte);
        if(nbreJoursAlerte.getAlerte() != null && nbreJoursAlerte.getNbreJours() != null)
        {
            CleNbreJoursAlerte cleNbreJoursAlerte = new CleNbreJoursAlerte();
            cleNbreJoursAlerte.setIdAlerte(nbreJoursAlerte.getAlerte().getIdAlerte());
            cleNbreJoursAlerte.setIdNbreJours(nbreJoursAlerte.getNbreJours().getIdNbreJours());
            NbreJoursAlerte.setIdNbreJoursAlerte(cleNbreJoursAlerte);

            NbreJoursAlerte.setAlerte(alerteMapper.deAlerteDto(nbreJoursAlerte.getAlerte()));
            NbreJoursAlerte.setNbreJours(nbreJoursMapper.deNbreJoursDto(nbreJoursAlerte.getNbreJours()));
            NbreJoursAlerte = nbreJoursAlerteDao.save(NbreJoursAlerte);
        }
        return nbreJoursAlerteMapper.deNbreJoursAlerte(NbreJoursAlerte);
    }

    @Override
    public NbreJoursAlerteDto modifierNbreJoursAlerte(NbreJoursAlerteDto nbreJoursAlerte) {
        NbreJoursAlerte NbreJoursAlerte = nbreJoursAlerteMapper.deNbreJoursAlerteDto(nbreJoursAlerte);
        if(nbreJoursAlerte.getAlerte() != null && nbreJoursAlerte.getNbreJours() != null)
        {
            CleNbreJoursAlerte cleNbreJoursAlerte = new CleNbreJoursAlerte();
            cleNbreJoursAlerte.setIdAlerte(nbreJoursAlerte.getAlerte().getIdAlerte());
            cleNbreJoursAlerte.setIdNbreJours(nbreJoursAlerte.getNbreJours().getIdNbreJours());
            NbreJoursAlerte.setIdNbreJoursAlerte(cleNbreJoursAlerte);

            NbreJoursAlerte.setAlerte(alerteMapper.deAlerteDto(nbreJoursAlerte.getAlerte()));
            NbreJoursAlerte.setNbreJours(nbreJoursMapper.deNbreJoursDto(nbreJoursAlerte.getNbreJours()));
            NbreJoursAlerte = nbreJoursAlerteDao.save(NbreJoursAlerte);
        }
        return nbreJoursAlerteMapper.deNbreJoursAlerte(NbreJoursAlerte);
    }

    @Override
    public void supprimerNbreJoursAlerte(CleNbreJoursAlerte cleNbreJoursAlerte) {
        nbreJoursAlerteDao.deleteById(cleNbreJoursAlerte);
    }

    @Override
    public void supprimerNbreJoursAlerteSelonAlerte(long idAlerte) {
        Alerte alerte=alerteDao.findById(idAlerte);
        nbreJoursAlerteDao.deleteByAlerte(alerte);
    }
}
