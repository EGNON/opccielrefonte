package com.ged.service.crm.impl;

import com.ged.dao.standard.PeriodiciteDao;
import com.ged.dto.standard.PeriodiciteDto;
import com.ged.entity.standard.Periodicite;
import com.ged.mapper.crm.PeriodiciteMapper;
import com.ged.service.crm.PeriodiciteService;
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
public class PeriodiciteServiceImpl implements PeriodiciteService {
    private final PeriodiciteDao periodiciteDao;
    private final PeriodiciteMapper periodiciteMapper;

    public PeriodiciteServiceImpl(PeriodiciteDao periodiciteDao, PeriodiciteMapper periodiciteMapper) {
        this.periodiciteDao = periodiciteDao;
        this.periodiciteMapper = periodiciteMapper;
    }

    @Override
    public Boolean existByLibelle(String libelle) {
        return periodiciteDao.existsByLibelle(libelle);
    }

    @Override
    public Page<PeriodiciteDto> afficherPeriodicites(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Periodicite> periodicitePage=periodiciteDao.findAll(pageRequest);
        return new PageImpl<>(periodicitePage.getContent().stream().map(periodiciteMapper::dePeriodicite).collect(Collectors.toList()),pageRequest,periodicitePage.getTotalElements());
    }

    @Override
    public List<PeriodiciteDto> afficherPeriodicites() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        return periodiciteDao.findAll(sort).stream().map(periodiciteMapper::dePeriodicite).collect(Collectors.toList());
    }

    @Override
    public Periodicite afficherPeriodiciteSelonId(long idPeriodicite) {
        return periodiciteDao.findById(idPeriodicite).orElseThrow(()-> new EntityNotFoundException("Periodicité avec ID "+idPeriodicite+" est introuvable"));
    }

    @Override
    public PeriodiciteDto afficherPeriodicite(long idPeriodicite) {
        return periodiciteMapper.dePeriodicite(afficherPeriodiciteSelonId(idPeriodicite));
    }

    @Override
    public PeriodiciteDto rechercherPeriodiciteParLibelle(String libelle) {
        Periodicite periodicite=periodiciteDao.findByLibelle(libelle);
        if(periodicite!=null)
            return periodiciteMapper.dePeriodicite(periodicite);
        else
            return null;
    }

    @Override
    public PeriodiciteDto creerPeriodicite(PeriodiciteDto periodiciteDto) {
        Periodicite periodicite=periodiciteMapper.dePeriodiciteDto(periodiciteDto);
        Periodicite periodiciteSave=periodiciteDao.save(periodicite);
        return periodiciteMapper.dePeriodicite(periodiciteSave);
    }

    @Override
    public PeriodiciteDto modifierPeriodicite(PeriodiciteDto periodiciteDto) {
        Periodicite periodicite=periodiciteMapper.dePeriodiciteDto(periodiciteDto);
        Periodicite periodiciteMaj=periodiciteDao.save(periodicite);
        return periodiciteMapper.dePeriodicite(periodiciteMaj);
    }

    @Override
    public void supprimerPeriodicite(long idPeriodicite) {
        periodiciteDao.deleteById(idPeriodicite);
    }
}
