package com.ged.service.standard.impl;

import com.ged.dao.standard.TypePlanificationDao;
import com.ged.dto.standard.TypePlanificationDto;
import com.ged.entity.standard.TypePlanification;
import com.ged.mapper.standard.TypePlanificationMapper;
import com.ged.service.standard.TypePlanificationService;
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
public class TypePlanificationServiceImpl implements TypePlanificationService {
    private final TypePlanificationDao typePlanificationDao;
    private final TypePlanificationMapper typePlanificationMapper;

    public TypePlanificationServiceImpl(TypePlanificationDao typePlanificationDao, TypePlanificationMapper typePlanificationMapper) {
        this.typePlanificationDao = typePlanificationDao;
        this.typePlanificationMapper = typePlanificationMapper;
    }

    @Override
    public Boolean existByLibelle(String libelle) {
        return typePlanificationDao.existsByLibelleTypePlanification(libelle);
    }

    @Override
    public Page<TypePlanificationDto> afficherTypePlanifications(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        Page<TypePlanification> typePlanificationPage=typePlanificationDao.findAll(pageRequest);
        return new PageImpl<>(typePlanificationPage.getContent().stream().map(typePlanificationMapper::deTypePlanification).collect(Collectors.toList()),pageRequest,typePlanificationPage.getTotalElements());
    }

    @Override
    public List<TypePlanificationDto> afficherTypePlanificationsTous() {
        return typePlanificationDao.findAll().stream().map(typePlanificationMapper::deTypePlanification).collect(Collectors.toList());
    }

    @Override
    public TypePlanification afficherTypePlanificationSelonId(long idTypePlanification) {
        return typePlanificationDao.findById(idTypePlanification).orElseThrow(()-> new EntityNotFoundException("Type Planification avec ID"+idTypePlanification+" introuvable"));
    }

    @Override
    public TypePlanificationDto afficherTypePlanification(long idTypePlanification) {
        return typePlanificationMapper.deTypePlanification(afficherTypePlanificationSelonId(idTypePlanification));
    }

    @Override
    public TypePlanificationDto creerTypePlanification(TypePlanificationDto typePlanificationDto) {
        TypePlanification typePlanification=typePlanificationMapper.deTypePlanificationDto(typePlanificationDto);
        TypePlanification typePlanificationSave=typePlanificationDao.save(typePlanification);
        return typePlanificationMapper.deTypePlanification(typePlanificationSave);
    }

    @Override
    public TypePlanificationDto modifierTypePlanification(TypePlanificationDto typePlanificationDto) {
        TypePlanification typePlanification= typePlanificationMapper.deTypePlanificationDto(typePlanificationDto);
        TypePlanification typePlanificationMaj=typePlanificationDao.save(typePlanification);
        return typePlanificationMapper.deTypePlanification(typePlanificationMaj);
    }

    @Override
    public void supprimerTypePlanification(long idTypePlanification) {
        typePlanificationDao.deleteById(idTypePlanification);
    }
}
