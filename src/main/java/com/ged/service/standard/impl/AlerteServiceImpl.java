package com.ged.service.standard.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.AlerteDao;
import com.ged.entity.standard.Alerte;
import com.ged.dto.standard.*;
import com.ged.mapper.crm.PeriodiciteMapper;
import com.ged.mapper.standard.AlerteMapper;
import com.ged.mapper.standard.TypePlanificationMapper;
import com.ged.service.standard.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class AlerteServiceImpl implements AlerteService {
    private final AlerteDao alerteDao;
    private final AlerteMapper alerteMapper;
    private final DiffusionAlerteService diffusionAlerteService;
    private final ProtoAlerteService protoAlerteService;
    private final TypePlanificationMapper typePlanificationMapper;
    private final PeriodiciteMapper periodiciteMapper;
    private final TempsAlerteService tempsAlerteService;
    private final NbreJoursAlerteService nbreJoursAlerteService;
    private final JoursAlerteService joursAlerteService;

    public AlerteServiceImpl(
            AlerteDao alerteDao,
            AlerteMapper alerteMapper,
            DiffusionAlerteService diffusionAlerteService,
            ProtoAlerteService protoAlerteService,
            TypePlanificationMapper typePlanificationMapper,
            PeriodiciteMapper periodiciteMapper,
            TempsAlerteService tempsAlerteService,
            NbreJoursAlerteService nbreJoursAlerteService,
            JoursAlerteService joursAlerteService) {
        this.alerteDao = alerteDao;
        this.alerteMapper = alerteMapper;
        this.diffusionAlerteService = diffusionAlerteService;
        this.protoAlerteService = protoAlerteService;
        this.typePlanificationMapper = typePlanificationMapper;
        this.periodiciteMapper = periodiciteMapper;
        this.tempsAlerteService = tempsAlerteService;
        this.nbreJoursAlerteService = nbreJoursAlerteService;
        this.joursAlerteService = joursAlerteService;
    }

    @Override
    public Page<AlerteDto> afficherAlertes(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Alerte> alertePage = alerteDao.findAll(pageRequest);
        return new PageImpl<>(alertePage.getContent().stream().map(alerteMapper::deAlerte).collect(Collectors.toList()), pageRequest, alertePage.getTotalElements());
    }

    @Override
    public Alerte afficherAlerteSelonId(Long idAlerte) {
        return alerteDao.findById(idAlerte).orElseThrow(() -> new EntityNotFoundException(Alerte.class, "id", idAlerte.toString()));
    }

    @Override
    public AlerteDto afficherAlerte(Long idAlerte) {
        return alerteMapper.deAlerte(afficherAlerteSelonId(idAlerte));
    }

    @Override
    public AlerteDto creerAlerte(AlerteDto alerteDto) {
        Alerte alerte = alerteMapper.deAlerteDto(alerteDto);
        alerte.setTypePlanification(typePlanificationMapper.deTypePlanificationDto(alerteDto.getTypePlanification()));
        if(alerteDto.getPeriodicite() != null) alerte.setPeriodicite(periodiciteMapper.dePeriodiciteDto(alerteDto.getPeriodicite()));
        alerte = alerteDao.save(alerte);
        if(alerte.getIdAlerte() != null)
        {
            Set<ProtoAlerteDto> protoAlerteDtos = alerteDto.getProtoAlertes();
            if(protoAlerteDtos != null)
            {
                for (ProtoAlerteDto protoAlerteDto : protoAlerteDtos) {
                    if(protoAlerteDto.getModeleMsgAlerte() != null)
                    {
                        protoAlerteDto.setAlerte(alerteMapper.deAlerte(alerte));
                        protoAlerteDto = protoAlerteService.creerProtoAlerte(protoAlerteDto);
                    }
                }
            }
            Set<TempsAlerteDto> tempsAlertes = alerteDto.getTempsAlertes();
            for (TempsAlerteDto tempsAlerteDto : tempsAlertes) {
                if (tempsAlerteDto.getTemps() != null)
                {
                    tempsAlerteDto.setAlerte(alerteMapper.deAlerte(alerte));
                    tempsAlerteDto = tempsAlerteService.creerTempsAlerte(tempsAlerteDto);
                }
            }
            Set<NbreJoursAlerteDto> nbreJoursAlertes = alerteDto.getNbreJoursAlertes();
            for (NbreJoursAlerteDto nbreJoursAlerteDto : nbreJoursAlertes) {
//                System.out.println(nbreJoursAlerteDto.toString());
                if(nbreJoursAlerteDto.getNbreJours() != null)
                {
                    nbreJoursAlerteDto.setAlerte(alerteMapper.deAlerte(alerte));
                    nbreJoursAlerteDto = nbreJoursAlerteService.creerNbreJoursAlerte(nbreJoursAlerteDto);
                }
            }
            Set<JoursAlerteDto> joursAlertes = alerteDto.getJoursAlertes();
            for (JoursAlerteDto joursAlerteDto : joursAlertes) {
                if(joursAlerteDto.getJours() != null && joursAlerteDto.getEtat())
                {
                    joursAlerteDto.setAlerte(alerteMapper.deAlerte(alerte));
                    joursAlerteDto = joursAlerteService.creerJoursAlerte(joursAlerteDto);
                }
            }
        }
        return alerteMapper.deAlerte(alerte);
    }

    @Override
    public AlerteDto modifierAlerte(AlerteDto alerteDto) {
        Alerte alerte = alerteMapper.deAlerteDto(alerteDto);
        alerte.setTypePlanification(typePlanificationMapper.deTypePlanificationDto(alerteDto.getTypePlanification()));
        if(alerteDto.getPeriodicite() != null) alerte.setPeriodicite(periodiciteMapper.dePeriodiciteDto(alerteDto.getPeriodicite()));
        alerte = alerteDao.save(alerte);
        if(alerte.getIdAlerte() != null)
        {
            Set<ProtoAlerteDto> protoAlerteDtos = alerteDto.getProtoAlertes();
            if(protoAlerteDtos != null)
            {
                for (ProtoAlerteDto protoAlerteDto : protoAlerteDtos) {
                    if(protoAlerteDto.getModeleMsgAlerte() != null)
                    {
                        protoAlerteDto.setAlerte(alerteMapper.deAlerte(alerte));
                        protoAlerteDto = protoAlerteService.creerProtoAlerte(protoAlerteDto);
                    }
                }
            }
            Set<TempsAlerteDto> tempsAlertes = alerteDto.getTempsAlertes();
            for (TempsAlerteDto tempsAlerteDto : tempsAlertes) {
                if (tempsAlerteDto.getTemps() != null)
                {
                    tempsAlerteDto.setAlerte(alerteMapper.deAlerte(alerte));
                    tempsAlerteDto = tempsAlerteService.creerTempsAlerte(tempsAlerteDto);
                }
            }
            Set<NbreJoursAlerteDto> nbreJoursAlertes = alerteDto.getNbreJoursAlertes();
            for (NbreJoursAlerteDto nbreJoursAlerteDto : nbreJoursAlertes) {
//                System.out.println(nbreJoursAlerteDto.toString());
                if(nbreJoursAlerteDto.getNbreJours() != null)
                {
                    nbreJoursAlerteDto.setAlerte(alerteMapper.deAlerte(alerte));
                    nbreJoursAlerteDto = nbreJoursAlerteService.creerNbreJoursAlerte(nbreJoursAlerteDto);
                }
            }
            Set<JoursAlerteDto> joursAlertes = alerteDto.getJoursAlertes();
            for (JoursAlerteDto joursAlerteDto : joursAlertes) {
                if(joursAlerteDto.getJours() != null && joursAlerteDto.getEtat())
                {
                    joursAlerteDto.setAlerte(alerteMapper.deAlerte(alerte));
                    joursAlerteDto = joursAlerteService.creerJoursAlerte(joursAlerteDto);
                }
            }
        }
        return alerteMapper.deAlerte(alerte);
    }

    @Override
    public void supprimerAlerte(Long id) {
        diffusionAlerteService.supprimerDiffusionAlerteSelonAlerte(id);
        alerteDao.deleteById(id);
    }
}
