package com.ged.service.standard.impl;
import com.ged.dao.standard.NbreJoursDao;
import com.ged.dto.standard.NbreJoursDto;
import com.ged.entity.standard.NbreJours;
import com.ged.mapper.standard.NbreJoursMapper;
import com.ged.service.standard.NbreJoursService;
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
public class NbreJoursServiceImpl implements NbreJoursService {
    private final NbreJoursDao nbreJoursDao;
    private final NbreJoursMapper nbreJoursMapper;

    public NbreJoursServiceImpl(NbreJoursDao nbreJoursDao, NbreJoursMapper nbreJoursMapper) {
        this.nbreJoursDao = nbreJoursDao;
        this.nbreJoursMapper = nbreJoursMapper;
    }


    @Override
    public Boolean existById(Long id) {
        return nbreJoursDao.existsByIdNbreJours(id);
    }

    @Override
    public Page<NbreJoursDto> afficherNbreJours(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<NbreJours> NbreJoursPage = nbreJoursDao.findAll(pageRequest);
        return new PageImpl<>(NbreJoursPage.getContent().stream().map(nbreJoursMapper::deNbreJours).collect(Collectors.toList()), pageRequest, NbreJoursPage.getTotalElements());
    }

    @Override
    public List<NbreJoursDto> afficherTous() {
        return nbreJoursDao.findAll().stream().map(nbreJoursMapper::deNbreJours).collect(Collectors.toList());
    }

    @Override
    public NbreJours afficherNbreJoursSelonId(long idNbreJours) {
        return nbreJoursDao.findById(idNbreJours).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
    }

    @Override
    public NbreJoursDto creerNbreJours(NbreJoursDto nbreJours) {
        NbreJours NbreJours = nbreJoursMapper.deNbreJoursDto(nbreJours);
        NbreJours NbreJoursSaved = nbreJoursDao.save(NbreJours);
        return nbreJoursMapper.deNbreJours(NbreJoursSaved);
    }

    @Override
    public NbreJoursDto modifierNbreJours(NbreJoursDto nbreJoursDto) {
        NbreJours nbreJours= nbreJoursMapper.deNbreJoursDto(nbreJoursDto);
        nbreJours = nbreJoursDao.save(nbreJours);
        return nbreJoursMapper.deNbreJours(nbreJours);
    }

    @Override
    public void supprimerNbreJours(long idNbreJours) {
        nbreJoursDao.deleteById(idNbreJours);
    }
}
