package com.ged.service.lab.impl;

import com.ged.dao.standard.PersonneDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.entity.standard.Personne;
import com.ged.entity.standard.Profession;
import com.ged.dao.lab.GelDegelDao;
import com.ged.dto.lab.GelDegelDto;
import com.ged.entity.lab.GelDegel;
import com.ged.mapper.lab.GelDegelMapper;
import com.ged.service.lab.GelDegelService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GelDegelServiceImpl implements GelDegelService {
    private final GelDegelDao gelDegelDao;
    private final GelDegelMapper gelDegelMapper;
    private final PersonneDao personneDao;
    private LocalDateTime dateServeur;
    public GelDegelServiceImpl(GelDegelDao gelDegelDao, GelDegelMapper gelDegelMapper, PersonneDao personneDao) {
        this.gelDegelDao = gelDegelDao;
        this.gelDegelMapper = gelDegelMapper;
        this.personneDao = personneDao;
    }

    @Override
    public Page<GelDegelDto> afficherGelDegels(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<GelDegel> gelDegelPage = gelDegelDao.findAll(pageRequest);
        return new PageImpl<>(gelDegelPage.getContent().stream().map(gelDegelMapper::deGelDegel).collect(Collectors.toList()), pageRequest, gelDegelPage.getTotalElements());
    }

    @Override
    public DataTablesResponse<GelDegelDto> afficherGelDegels(DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());
        Page<GelDegel> gelDegelPage = gelDegelDao.findAll(pageable);
        List<GelDegelDto> content = gelDegelPage.getContent().stream().map(gelDegelMapper::deGelDegel).collect(Collectors.toList());
        DataTablesResponse<GelDegelDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)gelDegelPage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)gelDegelPage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public GelDegel afficherGelDegelSelonId(long idGelDegel) {
        return gelDegelDao.findById(idGelDegel).orElseThrow(() -> new EntityNotFoundException("GelDegel avec ID " + idGelDegel + " introuvable"));
    }

    @Override
    public GelDegel afficherGelDegelSelonEstGeleEtPersonne(boolean estGele, long idPersonne) {
        Personne personne=personneDao.findById(idPersonne).orElseThrow();
        return gelDegelDao.findByEstGeleAndPersonne(true,personne);
    }

    @Override
    public GelDegelDto creerGelDegel(GelDegelDto gelDegelDto) {
        GelDegel gelDegel = gelDegelMapper.deGelDegelDto(gelDegelDto);
        if(gelDegelDto.getPersonneDto() != null && gelDegelDto.getPersonneDto().getIdPersonne() != null)
        {
            Personne personne = personneDao.findById(gelDegelDto.getPersonneDto().getIdPersonne())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Profession.class, gelDegelDto.getPersonneDto().getIdPersonne().toString()));
            gelDegel.setPersonne(personne);
        }
        dateServeur=LocalDateTime.now();
        gelDegel.setDateDebut(dateServeur);
        GelDegel gelDegelSaved = gelDegelDao.save(gelDegel);
        return gelDegelMapper.deGelDegel(gelDegelSaved);
    }

    @Override
    public GelDegelDto modifierGelDegel(GelDegelDto gelDegelDto) {
        GelDegel gelDegel = afficherGelDegelSelonId(gelDegelDto.getIdGelDegel());
        if(gelDegel.getIdGelDegel() == null)
            return null;
        gelDegel = gelDegelMapper.deGelDegelDto(gelDegelDto);
        if(gelDegelDto.getPersonneDto() != null && gelDegelDto.getPersonneDto().getIdPersonne() != null)
        {
            Personne personne = personneDao.findById(gelDegelDto.getPersonneDto().getIdPersonne())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Profession.class, gelDegelDto.getPersonneDto().getIdPersonne().toString()));
            gelDegel.setPersonne(personne);
        }
        GelDegel gelDegelMaj = gelDegelDao.save(gelDegel);
        return gelDegelMapper.deGelDegel(gelDegelMaj);
    }

    @Override
    public int modifierGelDegel(long idPersonne,LocalDateTime dateFin) {
        return gelDegelDao.updateGelDegel(idPersonne,dateFin);
    }

    @Override
    public void supprimerGelDegel(Long id) {
        gelDegelDao.deleteById(id);
    }
}
