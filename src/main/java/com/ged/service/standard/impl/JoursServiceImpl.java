package com.ged.service.standard.impl;
import com.ged.dao.standard.JoursDao;
import com.ged.dto.standard.JoursDto;
import com.ged.entity.standard.Jours;
import com.ged.mapper.standard.JoursMapper;
import com.ged.service.standard.JoursService;
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
public class JoursServiceImpl implements JoursService {
    private final JoursDao joursDao;
    private final JoursMapper joursMapper;

    public JoursServiceImpl(JoursDao joursDao, JoursMapper joursMapper) {
        this.joursDao = joursDao;
        this.joursMapper = joursMapper;
    }


    @Override
    public Boolean existByLibelle(String libelle) {
        return joursDao.existsByLibelleJours(libelle);
    }

    @Override
    public Page<JoursDto> afficherJours(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Jours> JoursPage = joursDao.findAll(pageRequest);
        return new PageImpl<>(JoursPage.getContent().stream().map(joursMapper::deJours).collect(Collectors.toList()), pageRequest, JoursPage.getTotalElements());
    }

    @Override
    public List<JoursDto> afficherTous() {
        return joursDao.findAll().stream().map(joursMapper::deJours).collect(Collectors.toList());
    }

    @Override
    public Jours afficherJoursSelonId(long idJours) {
        return joursDao.findById(idJours).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
    }


    @Override
    public JoursDto creerJours(JoursDto JoursDto) {
        Jours Jours = joursMapper.deJoursDto(JoursDto);

        Jours JoursSaved = joursDao.save(Jours);
        return joursMapper.deJours(JoursSaved);
    }

    @Override
    public JoursDto modifierJours(JoursDto JoursDto) {
        Jours jours = joursMapper.deJoursDto(JoursDto);
        jours = joursDao.save(jours);
        return joursMapper.deJours(jours);
    }

    @Override
    public void supprimerJours(long idJours) {
        joursDao.deleteById(idJours);
    }
}
