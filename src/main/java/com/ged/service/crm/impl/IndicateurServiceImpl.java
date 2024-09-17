package com.ged.service.crm.impl;

import com.ged.dao.crm.IndicateurDao;
import com.ged.dto.crm.IndicateurDto;
import com.ged.entity.crm.Indicateur;
import com.ged.mapper.crm.IndicateurMapper;
import com.ged.service.crm.IndicateurService;
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
public class IndicateurServiceImpl implements IndicateurService {
    private final IndicateurDao indicateurDao;
    private final IndicateurMapper indicateurMapper;

    public IndicateurServiceImpl(IndicateurDao indicateurDao, IndicateurMapper indicateurMapper) {
        this.indicateurDao = indicateurDao;
        this.indicateurMapper = indicateurMapper;
    }

    @Override
    public Page<IndicateurDto> afficherIndicateurs(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Indicateur> indicateurPage=indicateurDao.findAll(pageRequest);
        return new PageImpl<>(indicateurPage.getContent().stream().map(indicateurMapper::deIndicateur).collect(Collectors.toList()),pageRequest,indicateurPage.getTotalElements());
    }

    @Override
    public List<IndicateurDto> afficherIndicateurs() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        return indicateurDao.findAll(sort).stream().map(indicateurMapper::deIndicateur).collect(Collectors.toList());
    }

    @Override
    public Indicateur afficherIndicateurSelonId(long idIndicateur) {
        return indicateurDao.findById(idIndicateur).orElseThrow(()-> new EntityNotFoundException("Indicateur avec ID "+idIndicateur+" est introuvable"));
    }

    @Override
    public IndicateurDto afficherIndicateur(long idIndicateur) {
        return indicateurMapper.deIndicateur(afficherIndicateurSelonId(idIndicateur));
    }

    @Override
    public IndicateurDto rechercherIndicateurParLibelle(String libelle) {
        Indicateur indicateur=indicateurDao.findByLibelle(libelle);
        if(indicateur!=null)
            return indicateurMapper.deIndicateur(indicateur);
        else
            return null;
    }

    @Override
    public IndicateurDto creerIndicateur(IndicateurDto indicateurDto) {
        Indicateur indicateur=indicateurMapper.deIndicateurDto(indicateurDto);
        Indicateur indicateurSave=indicateurDao.save(indicateur);
        return indicateurMapper.deIndicateur(indicateurSave);
    }

    @Override
    public IndicateurDto modifierIndicateur(IndicateurDto indicateurDto) {
        Indicateur indicateur=indicateurMapper.deIndicateurDto(indicateurDto);
        Indicateur indicateurMaj=indicateurDao.save(indicateur);
        return indicateurMapper.deIndicateur(indicateurMaj);
    }

    @Override
    public void supprimerIndicateur(long idIndicateur) {
        indicateurDao.deleteById(idIndicateur);
    }
}
