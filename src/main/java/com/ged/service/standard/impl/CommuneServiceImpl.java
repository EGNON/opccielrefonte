package com.ged.service.standard.impl;

import com.ged.dao.standard.CommuneDao;
import com.ged.dto.standard.CommuneDto;
import com.ged.entity.standard.Commune;
import com.ged.mapper.standard.CommuneMapper;
import com.ged.service.standard.CommuneService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommuneServiceImpl implements CommuneService {
    private final CommuneDao communeDao;
    private final CommuneMapper communeMapper;

    public CommuneServiceImpl(CommuneDao communeDao, CommuneMapper communeMapper) {
        this.communeDao = communeDao;
        this.communeMapper = communeMapper;
    }

    @Override
    public Page<CommuneDto> afficherCommunes(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleCommune");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Commune> communePage = communeDao.findAll(pageRequest);
        return new PageImpl<>(communePage.getContent().stream().map(communeMapper::deCommune).collect(Collectors.toList()), pageRequest, communePage.getTotalElements());
    }

    @Override
    public List<CommuneDto> afficherCommunesListe() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleCommune");
        return communeDao.findAll(sort).stream().map(communeMapper::deCommune).collect(Collectors.toList());
    }

    @Override
    public Commune afficherCommuneSelonId(long idCommune) {
        return communeDao.findById(idCommune).orElseThrow(() -> new EntityNotFoundException("Commune avec ID " + idCommune + " introuvable"));
    }

    @Override
    public CommuneDto creerCommune(CommuneDto communeDto) {
        Commune commune = communeMapper.deCommuneDto(communeDto);
        Commune communeSaved = communeDao.save(commune);
        return communeMapper.deCommune(communeSaved);
    }

    @Override
    public CommuneDto modifierCommune(CommuneDto communeDto) {
        Commune commune = communeMapper.deCommuneDto(communeDto);
        Commune communeMaj = communeDao.save(commune);
        return communeMapper.deCommune(communeMaj);
    }

    @Override
    public void supprimerCommune(Long id) {
        communeDao.deleteById(id);
    }
}
