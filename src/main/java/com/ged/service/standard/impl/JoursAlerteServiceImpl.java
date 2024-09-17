package com.ged.service.standard.impl;

import com.ged.dao.standard.AlerteDao;
import com.ged.dao.standard.JoursAlerteDao;
import com.ged.dto.standard.JoursAlerteDto;
import com.ged.entity.standard.Alerte;
import com.ged.entity.standard.CleJoursAlerte;
import com.ged.entity.standard.JoursAlerte;
import com.ged.mapper.standard.AlerteMapper;
import com.ged.mapper.standard.JoursAlerteMapper;
import com.ged.mapper.standard.JoursMapper;
import com.ged.service.standard.JoursAlerteService;
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
public class JoursAlerteServiceImpl implements JoursAlerteService {
    private final JoursAlerteDao joursAlerteDao;
    private final JoursAlerteMapper joursAlerteMapper;
    private final JoursMapper joursMapper;
    private final AlerteMapper alerteMapper;
    private final AlerteDao alerteDao;

    public JoursAlerteServiceImpl(JoursAlerteDao joursAlerteDao, JoursAlerteMapper joursAlerteMapper, JoursMapper joursMapper, AlerteMapper alerteMapper, AlerteDao alerteDao) {
        this.joursAlerteDao = joursAlerteDao;
        this.joursAlerteMapper = joursAlerteMapper;
        this.joursMapper = joursMapper;
        this.alerteMapper = alerteMapper;
        this.alerteDao = alerteDao;
    }


    @Override
    public Page<JoursAlerteDto> afficherJoursAlertes(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<JoursAlerte> JoursAlertePage = joursAlerteDao.findAll(pageRequest);
        return new PageImpl<>(JoursAlertePage.getContent().stream().map(joursAlerteMapper::deJoursAlerte).collect(Collectors.toList()), pageRequest, JoursAlertePage.getTotalElements());
    }

    @Override
    public JoursAlerte afficherJoursAlerteSelonId(CleJoursAlerte idJoursAlerte) {
        return joursAlerteDao.findById(idJoursAlerte).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
    }

    @Override
    public List<JoursAlerteDto> afficherJoursAlerteSelonAlerte(long idAlerte) {
        Alerte alerte=alerteDao.findById(idAlerte);
        return joursAlerteDao.findByAlerte(alerte).stream().map(joursAlerteMapper::deJoursAlerte).collect(Collectors.toList());
    }

    @Override
    public JoursAlerteDto creerJoursAlerte(JoursAlerteDto JoursAlerteDto) {
        JoursAlerte JoursAlerte = joursAlerteMapper.deJoursAlerteDto(JoursAlerteDto);
        if(JoursAlerteDto.getAlerte() != null && JoursAlerteDto.getJours() != null)
        {
            CleJoursAlerte cleJoursAlerte = new CleJoursAlerte();
            cleJoursAlerte.setIdAlerte(JoursAlerteDto.getAlerte().getIdAlerte());
            cleJoursAlerte.setIdJours(JoursAlerteDto.getJours().getIdJours());
            JoursAlerte.setIdJoursAlerte(cleJoursAlerte);

            JoursAlerte.setAlerte(alerteMapper.deAlerteDto(JoursAlerteDto.getAlerte()));
            JoursAlerte.setJours(joursMapper.deJoursDto(JoursAlerteDto.getJours()));
            JoursAlerte = joursAlerteDao.save(JoursAlerte);
        }
        return joursAlerteMapper.deJoursAlerte(JoursAlerte);
    }

    @Override
    public JoursAlerteDto modifierJoursAlerte(JoursAlerteDto JoursAlerteDto) {
        JoursAlerte JoursAlerte = joursAlerteMapper.deJoursAlerteDto(JoursAlerteDto);
        if(JoursAlerteDto.getAlerte() != null && JoursAlerteDto.getJours() != null)
        {
            CleJoursAlerte cleJoursAlerte = new CleJoursAlerte();
            cleJoursAlerte.setIdAlerte(JoursAlerteDto.getAlerte().getIdAlerte());
            cleJoursAlerte.setIdJours(JoursAlerteDto.getJours().getIdJours());
            JoursAlerte.setIdJoursAlerte(cleJoursAlerte);

            JoursAlerte.setAlerte(alerteMapper.deAlerteDto(JoursAlerteDto.getAlerte()));
            JoursAlerte.setJours(joursMapper.deJoursDto(JoursAlerteDto.getJours()));
            JoursAlerte = joursAlerteDao.save(JoursAlerte);
        }
        return joursAlerteMapper.deJoursAlerte(JoursAlerte);
    }

    @Override
    public void supprimerJoursAlerte(CleJoursAlerte cleJoursAlerte) {
        joursAlerteDao.deleteById(cleJoursAlerte);
    }
    @Override
    public void supprimerJoursAlerteSelonAlerte(long idAlerte) {
        Alerte alerte=alerteDao.findById(idAlerte);
        joursAlerteDao.deleteByAlerte(alerte);
    }
}
