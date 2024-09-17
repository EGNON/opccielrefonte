package com.ged.service.lab.impl;

import com.ged.dao.lab.TypeCritereDao;
import com.ged.dto.lab.TypeCritereDto;
import com.ged.entity.lab.TypeCritere;
import com.ged.mapper.lab.TypeCritereMapper;
import com.ged.service.lab.TypeCritereService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class TypeCritereServiceImpl implements TypeCritereService {
    private final TypeCritereDao typeCritereDao;
    private final TypeCritereMapper typeCritereMapper;

    public TypeCritereServiceImpl(TypeCritereDao typeCritereDao, TypeCritereMapper typeCritereMapper) {
        this.typeCritereDao = typeCritereDao;
        this.typeCritereMapper = typeCritereMapper;
    }

    @Override
    public Page<TypeCritereDto> afficherTypeCriteres(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<TypeCritere> typeCriterePage = typeCritereDao.findAll(pageRequest);
        return new PageImpl<>(typeCriterePage.getContent().stream().map(typeCritereMapper::deTypeCritere).collect(Collectors.toList()), pageRequest, typeCriterePage.getTotalElements());
    }

    @Override
    public TypeCritere afficherTypeCritereSelonId(long idTypeCritere) {
        return typeCritereDao.findById(idTypeCritere).orElseThrow(() -> new EntityNotFoundException("TypeCritere avec ID " + idTypeCritere + " introuvable"));
    }

    @Override
    public TypeCritereDto creerTypeCritere(TypeCritereDto typeCritereDto) {
        TypeCritere typeCritere = typeCritereMapper.deTypeCritereDto(typeCritereDto);
        TypeCritere typeCritereSaved = typeCritereDao.save(typeCritere);
        return typeCritereMapper.deTypeCritere(typeCritereSaved);
    }

    @Override
    public TypeCritereDto modifierTypeCritere(TypeCritereDto typeCritereDto) {
        TypeCritere typeCritere = afficherTypeCritereSelonId(typeCritereDto.getIdTypeCritere());
        if(typeCritere.getIdTypeCritere() == null)
            return null;
        typeCritere = typeCritereMapper.deTypeCritereDto(typeCritereDto);
        TypeCritere typeCritereMaj = typeCritereDao.save(typeCritere);
        return typeCritereMapper.deTypeCritere(typeCritereMaj);
    }

    @Override
    public void supprimerTypeCritere(Long id) {
        typeCritereDao.deleteById(id);
    }
}
