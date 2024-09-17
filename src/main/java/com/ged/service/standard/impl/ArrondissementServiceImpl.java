package com.ged.service.standard.impl;

import com.ged.dao.standard.ArrondissementDao;
import com.ged.dto.standard.ArrondissementDto;
import com.ged.entity.standard.Arrondissement;
import com.ged.entity.standard.Profession;
import com.ged.mapper.standard.ArrondissementMapper;
import com.ged.service.standard.ArrondissementService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class ArrondissementServiceImpl implements ArrondissementService {
    private final ArrondissementDao arrondissementDao;
    private final ArrondissementMapper arrondissementMapper;

    public ArrondissementServiceImpl(ArrondissementDao arrondissementDao, ArrondissementMapper arrondissementMapper) {
        this.arrondissementDao = arrondissementDao;
        this.arrondissementMapper = arrondissementMapper;
    }

    @Override
    public Page<ArrondissementDto> afficherArrondissements(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleArrondissement");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Arrondissement> arrondissementPage = arrondissementDao.findAll(pageRequest);
        return new PageImpl<>(arrondissementPage.getContent().stream().map(arrondissementMapper::deArrondissement).collect(Collectors.toList()), pageRequest, arrondissementPage.getTotalElements());
    }

    @Override
    public Arrondissement afficherArrondissementSelonId(long idArrondissement) {
        return arrondissementDao.findById(idArrondissement).orElseThrow(() -> new EntityNotFoundException("Arrondissement avec ID " + idArrondissement + " introuvable"));
    }

    @Override
    public ArrondissementDto creerArrondissement(ArrondissementDto arrondissementDto) {
        Arrondissement arrondissement = arrondissementMapper.deArrondissementDto(arrondissementDto);
        Arrondissement arrondissementSaved = arrondissementDao.save(arrondissement);
        return arrondissementMapper.deArrondissement(arrondissementSaved);
    }

    @Override
    public ArrondissementDto modifierArrondissement(ArrondissementDto arrondissementDto) {
        if(!arrondissementDao.existsById(arrondissementDto.getIdArrondissement()))
            throw new com.ged.advice.EntityNotFoundException(Profession.class, "id", arrondissementDto.getIdArrondissement().toString());
        Arrondissement arrondissement = arrondissementMapper.deArrondissementDto(arrondissementDto);
        Arrondissement arrondissementMaj = arrondissementDao.save(arrondissement);
        return arrondissementMapper.deArrondissement(arrondissementMaj);
    }

    @Override
    public void supprimerArrondissement(Long idArrondissement) {
        arrondissementDao.deleteById(idArrondissement);
    }
}
