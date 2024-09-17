package com.ged.service.standard.impl;

import com.ged.dao.standard.AlerteDao;
import com.ged.dao.standard.TempsAlerteDao;
import com.ged.dto.standard.TempsAlerteDto;
import com.ged.entity.standard.Alerte;
import com.ged.entity.standard.CleTempsAlerte;
import com.ged.entity.standard.TempsAlerte;
import com.ged.mapper.standard.AlerteMapper;
import com.ged.mapper.standard.TempsAlerteMapper;
import com.ged.mapper.standard.TempsMapper;
import com.ged.service.standard.TempsAlerteService;
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
public class TempsAlerteServiceImpl implements TempsAlerteService {
    private final TempsAlerteDao tempsAlerteDao;
    private final TempsAlerteMapper tempsAlerteMapper;
    private final TempsMapper tempsMapper;
    private final AlerteMapper alerteMapper;
    private final AlerteDao alerteDao;

    public TempsAlerteServiceImpl(TempsAlerteDao tempsAlerteDao, TempsAlerteMapper tempsAlerteMapper, TempsMapper tempsMapper, AlerteMapper alerteMapper, AlerteDao alerteDao) {
        this.tempsAlerteDao = tempsAlerteDao;
        this.tempsAlerteMapper = tempsAlerteMapper;
        this.tempsMapper = tempsMapper;
        this.alerteMapper = alerteMapper;
        this.alerteDao = alerteDao;
    }

    @Override
    public Page<TempsAlerteDto> afficherTempsAlertes(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TempsAlerte> TempsAlertePage = tempsAlerteDao.findAll(pageRequest);
        return new PageImpl<>(TempsAlertePage.getContent().stream().map(tempsAlerteMapper::deTempsAlerte).collect(Collectors.toList()), pageRequest, TempsAlertePage.getTotalElements());
    }

    @Override
    public TempsAlerte afficherTempsAlerteSelonId(CleTempsAlerte idTempsAlerte) {
        return tempsAlerteDao.findById(idTempsAlerte).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
    }

    @Override
    public List<TempsAlerteDto> afficherTempsAlerteSelonAlerte(long idAlerte) {
        Alerte alerte=alerteDao.findById(idAlerte);
        return tempsAlerteDao.findByAlerte(alerte).stream().map(tempsAlerteMapper::deTempsAlerte).collect(Collectors.toList());
    }

    @Override
    public TempsAlerteDto creerTempsAlerte(TempsAlerteDto tempsAlerteDto) {
        TempsAlerte tempsAlerte = tempsAlerteMapper.deTempsAlerteDto(tempsAlerteDto);
        if(tempsAlerteDto.getAlerte() != null && tempsAlerteDto.getTemps() != null)
        {
            CleTempsAlerte cleTempsAlerte = new CleTempsAlerte();
            cleTempsAlerte.setIdAlerte(tempsAlerteDto.getAlerte().getIdAlerte());
            cleTempsAlerte.setIdTemps(tempsAlerteDto.getTemps().getIdTemps());
            tempsAlerte.setIdTempsAlerte(cleTempsAlerte);

            tempsAlerte.setAlerte(alerteMapper.deAlerteDto(tempsAlerteDto.getAlerte()));
            tempsAlerte.setTemps(tempsMapper.deTempsDto(tempsAlerteDto.getTemps()));
            tempsAlerte = tempsAlerteDao.save(tempsAlerte);
        }
        return tempsAlerteMapper.deTempsAlerte(tempsAlerte);
    }

    @Override
    public TempsAlerteDto modifierTempsAlerte(TempsAlerteDto tempsAlerteDto) {
        TempsAlerte tempsAlerte = tempsAlerteMapper.deTempsAlerteDto(tempsAlerteDto);
        if(tempsAlerteDto.getAlerte() != null && tempsAlerteDto.getTemps() != null)
        {
            CleTempsAlerte cleTempsAlerte = new CleTempsAlerte();
            cleTempsAlerte.setIdAlerte(tempsAlerteDto.getAlerte().getIdAlerte());
            cleTempsAlerte.setIdTemps(tempsAlerteDto.getTemps().getIdTemps());
            tempsAlerte.setIdTempsAlerte(cleTempsAlerte);

            tempsAlerte.setAlerte(alerteMapper.deAlerteDto(tempsAlerteDto.getAlerte()));
            tempsAlerte.setTemps(tempsMapper.deTempsDto(tempsAlerteDto.getTemps()));
            tempsAlerte = tempsAlerteDao.save(tempsAlerte);
        }
        return tempsAlerteMapper.deTempsAlerte(tempsAlerte);
    }

    @Override
    public void supprimerTempsAlerte(CleTempsAlerte cleTempsAlerte) {
        tempsAlerteDao.deleteById(cleTempsAlerte);
    }

    @Override
    public void supprimerTempsAlerteSelonAlerte(long idAlerte) {
        Alerte alerte=alerteDao.findById(idAlerte);
        tempsAlerteDao.deleteByAlerte(alerte);
    }
}
