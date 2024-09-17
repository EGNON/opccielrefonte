package com.ged.service.standard.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.ModeEtablissementDao;
import com.ged.dto.standard.ModeEtablissementDto;
import com.ged.entity.standard.ModeEtablissement;
import com.ged.mapper.standard.ModeEtablissementMapper;
import com.ged.service.standard.ModeEtablissementService;
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
public class ModeEtablissementServiceImpl implements ModeEtablissementService {

    private final ModeEtablissementDao modeEtablissementDao;
    private final ModeEtablissementMapper modeEtablissementMapper;

    public ModeEtablissementServiceImpl(ModeEtablissementDao modeEtablissementDao, ModeEtablissementMapper modeEtablissementMapper) {
        this.modeEtablissementDao = modeEtablissementDao;
        this.modeEtablissementMapper = modeEtablissementMapper;
    }

    @Override
    public Page<ModeEtablissementDto> afficherModeEtablissements(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<ModeEtablissement> modeEtablissementPage = modeEtablissementDao.findAll(pageRequest);

        return new PageImpl<>(modeEtablissementPage.getContent().stream().map(modeEtablissementMapper::deModeEtablissement).collect(Collectors.toList()), pageRequest, modeEtablissementPage.getTotalElements());
    }

    @Override
    public List<ModeEtablissementDto> afficherModeEtablissements() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelle");
        return modeEtablissementDao.findAll(sort).stream().map(modeEtablissementMapper::deModeEtablissement).collect(Collectors.toList());
    }

    @Override
    public ModeEtablissementDto afficher(Long id) {
        return modeEtablissementMapper.deModeEtablissement(afficherModeEtablissementSelonId(id));
    }

    @Override
    public boolean existByLibelle(String libelle) {
        return modeEtablissementDao.existsByLibelle(libelle);
    }

    @Override
    public ModeEtablissement afficherModeEtablissementSelonId(Long idCategorie) {
        return modeEtablissementDao.findById(idCategorie).orElseThrow(() -> new EntityNotFoundException(ModeEtablissement.class, "id", idCategorie.toString()));
    }

    @Override
    public ModeEtablissementDto creerModeEtablissement(ModeEtablissementDto ModeEtablissementDto) throws JsonProcessingException {
        ModeEtablissement ModeEtablissement = modeEtablissementMapper.deModeEtablissementDto(ModeEtablissementDto);
        ModeEtablissement = modeEtablissementDao.save(ModeEtablissement);
        return modeEtablissementMapper.deModeEtablissement(ModeEtablissement);
    }

    @Override
    public ModeEtablissementDto modifierModeEtablissement(ModeEtablissementDto ModeEtablissementDto) {
        if(!modeEtablissementDao.existsById(ModeEtablissementDto.getIdModeEtablissement()))
            throw new EntityNotFoundException(ModeEtablissement.class, "id", ModeEtablissementDto.getIdModeEtablissement().toString());
        ModeEtablissement ModeEtablissement = modeEtablissementMapper.deModeEtablissementDto(ModeEtablissementDto);
        ModeEtablissement = modeEtablissementDao.save(ModeEtablissement);
        return modeEtablissementMapper.deModeEtablissement(ModeEtablissement);
    }

    
    @Override
    public void supprimerModeEtablissement(Long id) {
        modeEtablissementDao.deleteById(id);
    }
}
