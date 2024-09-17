package com.ged.service.standard.impl;

import com.ged.dao.standard.DepartementDao;
import com.ged.dto.standard.DepartementDto;
import com.ged.entity.standard.Departement;
import com.ged.mapper.standard.DepartementMapper;
import com.ged.service.standard.DepartementService;
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
public class DepartementServiceImpl implements DepartementService {
    private final DepartementDao departementDao;
    private final DepartementMapper departementMapper;

    public DepartementServiceImpl(DepartementDao departementDao, DepartementMapper departementMapper) {
        this.departementDao = departementDao;
        this.departementMapper = departementMapper;
    }

    @Override
    public Page<DepartementDto> afficherDepartements(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleDepartement");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Departement> departementPage = departementDao.findAll(pageRequest);
        return new PageImpl<>(departementPage.getContent().stream().map(departementMapper::deDepartement).collect(Collectors.toList()), pageRequest, departementPage.getTotalElements());
    }

    @Override
    public Departement afficherDepartementSelonId(long idDepartement) {
        return departementDao.findById(idDepartement).orElseThrow(() -> new EntityNotFoundException("Département avec ID " + idDepartement + " introuvable"));
    }

    @Override
    public DepartementDto creerDepartement(DepartementDto departementDto) {
        Departement departement = departementMapper.deDepartementDto(departementDto);
        Departement departementSaved = departementDao.save(departement);
        return departementMapper.deDepartement(departementSaved);
    }

    @Override
    public DepartementDto modifierDepartement(DepartementDto departementDto) {
        Departement departement = departementMapper.deDepartementDto(departementDto);
        Departement departementMaj = departementDao.save(departement);
        return departementMapper.deDepartement(departementMaj);
    }

    @Override
    public void supprimerDepartement(Long id) {
        departementDao.deleteById(id);
    }
}
