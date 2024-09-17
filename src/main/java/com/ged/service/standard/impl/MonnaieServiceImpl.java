package com.ged.service.standard.impl;

import com.ged.dao.standard.MonnaieDao;
import com.ged.dto.standard.MonnaieDto;
import com.ged.entity.standard.Monnaie;
import com.ged.mapper.standard.MonnaieMapper;
import com.ged.service.standard.MonnaieService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MonnaieServiceImpl implements MonnaieService {
    private final MonnaieDao monnaieDao;
    private final MonnaieMapper monnaieMapper;

    public MonnaieServiceImpl(MonnaieDao monnaieDao, MonnaieMapper monnaieMapper) {
        this.monnaieDao = monnaieDao;
        this.monnaieMapper = monnaieMapper;
    }

    @Override
    public Page<MonnaieDto> afficherMonnaies(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"nom");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Monnaie> monnaiePage=monnaieDao.findAll(pageRequest);
        return new PageImpl<>(monnaiePage.getContent().stream().map(monnaieMapper::deMonnaie).collect(Collectors.toList()),pageRequest,monnaiePage.getTotalElements());
    }

    public List<MonnaieDto> afficherMonnaies() {
        Sort sort=Sort.by(Sort.Direction.ASC,"nom");
       return monnaieDao.findAll(sort).stream().map(monnaieMapper::deMonnaie).collect(Collectors.toList());
    }

    @Override
    public MonnaieDto afficherMonnaie(String codeMonnaie) {
        return monnaieMapper.deMonnaie(afficherMonnaieSelonId(codeMonnaie));
    }

    @Override
    public MonnaieDto rechercherMonnaieParCode(String codeMonnaie) {
        Monnaie monnaie=monnaieDao.findByCodeMonnaie(codeMonnaie);
        if(monnaie==null)
            return null;
        else
            return monnaieMapper.deMonnaie(monnaie);
    }

    @Override
    public Monnaie afficherMonnaieSelonId(String codeMonnaie) {
        return monnaieDao.findById(codeMonnaie).orElseThrow(()-> new EntityNotFoundException("Monnaie avec ID "+codeMonnaie+" est introuvable"));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MonnaieDto creerMonnaie(MonnaieDto monnaieDto) {
        Monnaie monnaie=monnaieMapper.deMonnaieDto(monnaieDto);
        Monnaie monnaieSave=monnaieDao.save(monnaie);
        return monnaieMapper.deMonnaie(monnaieSave);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MonnaieDto modifierMonnaie(MonnaieDto monnaieDto) {
        Monnaie monnaie=monnaieMapper.deMonnaieDto(monnaieDto);
        Monnaie monnaieMaj=monnaieDao.save(monnaie);
        return monnaieMapper.deMonnaie(monnaieMaj);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void supprimerMonnaie(String codeMonnaie) {
        monnaieDao.deleteById(codeMonnaie);
    }
}
