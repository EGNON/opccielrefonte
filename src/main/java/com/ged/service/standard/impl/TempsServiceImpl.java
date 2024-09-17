package com.ged.service.standard.impl;

import com.ged.dao.standard.TempsDao;
import com.ged.dto.standard.TempsDto;
import com.ged.entity.standard.Temps;
import com.ged.mapper.standard.TempsMapper;
import com.ged.service.standard.TempsService;
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
public class TempsServiceImpl implements TempsService {
    private final TempsDao tempsDao;
    private final TempsMapper tempsMapper;

    public TempsServiceImpl(TempsDao tempsDao, TempsMapper tempsMapper) {
        this.tempsDao = tempsDao;
        this.tempsMapper = tempsMapper;
    }

    @Override
    public boolean existsByLibelle(String libelle)
    {
        return tempsDao.existsByLibelle(libelle);
    }

    @Override
    public Page<TempsDto> afficherTempss(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        Page<Temps> tempsPage=tempsDao.findAll(pageRequest);
        return new PageImpl<>(tempsPage.getContent().stream().map(tempsMapper::deTemps).collect(Collectors.toList()),pageRequest,tempsPage.getTotalElements());
    }

    @Override
    public List<TempsDto> afficherTous() {
        return tempsDao.findAll().stream().map(tempsMapper::deTemps).collect(Collectors.toList());
    }

    @Override
    public Temps afficherTempsSelonId(long idTemps) {
        return tempsDao.findById(idTemps).orElseThrow(()-> new EntityNotFoundException("Type document avec ID"+idTemps+" introuvable"));
    }

    @Override
    public TempsDto afficherTemps(long idTemps) {
        return tempsMapper.deTemps(afficherTempsSelonId(idTemps));
    }

    @Override
    public TempsDto creerTemps(TempsDto tempsDto) {
        Temps temps=tempsMapper.deTempsDto(tempsDto);
        Temps tempsSave=tempsDao.save(temps);
        return tempsMapper.deTemps(tempsSave);
    }

    @Override
    public TempsDto modifierTemps(TempsDto tempsDto) {
        Temps temps= tempsMapper.deTempsDto(tempsDto);
        Temps tempsMaj=tempsDao.save(temps);
        return tempsMapper.deTemps(tempsMaj);
    }

    @Override
    public void supprimerTemps(long idTemps) {
        tempsDao.deleteById(idTemps);
    }
}
