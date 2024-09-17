package com.ged.service.standard.impl;

import com.ged.dao.standard.QuartierDao;
import com.ged.dto.standard.QuartierDto;
import com.ged.entity.standard.Quartier;
import com.ged.mapper.standard.QuartierMapper;
import com.ged.projection.QuartierProjection;
import com.ged.service.standard.QuartierService;
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
public class QuartierServiceImpl implements QuartierService {
    private final QuartierDao quartierDao;
    private final QuartierMapper quartierMapper;

    public QuartierServiceImpl(QuartierDao quartierDao, QuartierMapper quartierMapper) {
        this.quartierDao = quartierDao;
        this.quartierMapper = quartierMapper;
    }

    @Override
    public Page<QuartierDto> afficherQuartiers(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleQuartier");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Quartier> quartierPage=quartierDao.findAll(pageRequest);
        return new PageImpl<>(quartierPage.getContent().stream().map(quartierMapper::deQuartier).collect(Collectors.toList()),pageRequest,quartierPage.getTotalElements());
    }

    @Override
    public List<QuartierProjection> afficherQuartierSelonVille(long idVille) {
        return quartierDao.afficherQuartierSelonVille(idVille);
    }

    @Override
    public List<QuartierProjection> afficherQuartierSelonCommune(String libelle) {
        return quartierDao.afficherQuartierSelonCommune(libelle);
    }

    @Override
    public Quartier afficherQuartierSelonId(long idQuartier) {
        return quartierDao.findById(idQuartier).orElseThrow(()-> new EntityNotFoundException("Quartier avec ID "+idQuartier+" est introuvable"));
    }

    @Override
    public QuartierDto afficherQuartier(long idQuartier) {
        return quartierMapper.deQuartier(afficherQuartierSelonId(idQuartier));
    }

    @Override
    public QuartierDto creerQuartier(QuartierDto quartierDto) {
        Quartier quartier=quartierMapper.deQuartierDto(quartierDto);
        Quartier quartierSave=quartierDao.save(quartier);
        return quartierMapper.deQuartier(quartierSave);
    }

    @Override
    public QuartierDto modifierQuartier(QuartierDto quartierDto) {
        Quartier quartier=quartierMapper.deQuartierDto(quartierDto);
        Quartier quartierMaj=quartierDao.save(quartier);
        return quartierMapper.deQuartier(quartierMaj);
    }

    @Override
    public void supprimerQuartier(long idQuartier) {
        quartierDao.deleteById(idQuartier);
    }
}
