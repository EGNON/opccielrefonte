package com.ged.service.standard.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.StatutPersonneDao;
import com.ged.dto.standard.StatutPersonneDto;
import com.ged.entity.standard.CleStatutPersonne;
import com.ged.entity.standard.StatutPersonne;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.mapper.standard.PersonnelMapper;
import com.ged.mapper.standard.QualiteMapper;
import com.ged.mapper.standard.StatutPersonneMapper;
import com.ged.service.standard.StatutPersonneService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class StatutPersonneServiceImpl implements StatutPersonneService {
    private final StatutPersonneDao statutPersonneDao;
    private final StatutPersonneMapper statutPersonneMapper;
    private final PersonneMapper personneMapper;
    private final QualiteMapper qualiteMapper;
    private final PersonnelMapper personnelMapper;

    public StatutPersonneServiceImpl(StatutPersonneDao statutPersonneDao, StatutPersonneMapper statutPersonneMapper, PersonneMapper personneMapper, QualiteMapper qualiteMapper, PersonnelMapper personnelMapper) {
        this.statutPersonneDao = statutPersonneDao;
        this.statutPersonneMapper = statutPersonneMapper;
        this.personneMapper = personneMapper;
        this.qualiteMapper = qualiteMapper;
        this.personnelMapper = personnelMapper;
    }

    @Override
    public Page<StatutPersonneDto> afficherStatutPersonnes(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        Page<StatutPersonne> statutPersonnePage=statutPersonneDao.findAll(pageRequest);
        return new PageImpl<>(statutPersonnePage.getContent().stream().map(statutPersonneMapper::deStatutPersonne).collect(Collectors.toList()),pageRequest,statutPersonnePage.getTotalElements());
    }

    @Override
    public StatutPersonneDto afficherStatutSelonQualite(String qualite) {
        return statutPersonneMapper.deStatutPersonne(statutPersonneDao.afficherStatutSelonQualite(qualite));
    }

    @Override
    public StatutPersonne afficherStatutPersonneSelonId(CleStatutPersonne idStatutPersonne) {
        return statutPersonneDao.afficherStatutSelonId(idStatutPersonne);
    }

    @Override
    public StatutPersonneDto creerStatutPersonne(StatutPersonneDto statutPersonneDto) {
        StatutPersonne statutPersonne = statutPersonneMapper.deStatutPersonneDto(statutPersonneDto);

        CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
        cleStatutPersonne.setIdPersonne(statutPersonneDto.getPersonne().getIdPersonne());
        cleStatutPersonne.setIdQualite(statutPersonneDto.getQualite().getIdQualite());
        statutPersonne.setIdStatutPersonne(cleStatutPersonne);
        if(statutPersonneDto.getPersonne() != null)
        {
            statutPersonne.setPersonne(personneMapper.dePersonneDto(statutPersonneDto.getPersonne()));
        }
        if(statutPersonneDto.getQualite() != null)
        {
            statutPersonne.setQualite(qualiteMapper.deQualiteDto(statutPersonneDto.getQualite()));
        }
        if(statutPersonneDto.getPersonnel() != null && statutPersonneDto.getPersonnel().getIdPersonne() != null)
        {
            statutPersonne.setPersonnel(personnelMapper.dePersonnelDto(statutPersonneDto.getPersonnel()));
        }
        statutPersonne = statutPersonneDao.save(statutPersonne);
        /*statutPersonneDao.insertStatutPersonne(statutPersonneDto.getPersonne().getIdPersonne(),
        statutPersonneDto.getPersonnel().getIdPersonne(),statutPersonneDto.getQualite().getIdQualite());
        */
        return statutPersonneMapper.deStatutPersonne(statutPersonne);
    }

    @Override
    public StatutPersonneDto modifierStatutPersonne(StatutPersonneDto statutPersonneDto) {
        StatutPersonne statutPersonne = statutPersonneMapper.deStatutPersonneDto(statutPersonneDto);
        CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
        cleStatutPersonne.setIdPersonne(statutPersonneDto.getPersonne().getIdPersonne());
        cleStatutPersonne.setIdQualite(statutPersonneDto.getQualite().getIdQualite());
        statutPersonne.setIdStatutPersonne(cleStatutPersonne);
        if(statutPersonneDto.getPersonne() != null)
        {
            statutPersonne.setPersonne(personneMapper.dePersonneDto(statutPersonneDto.getPersonne()));
        }
        if(statutPersonneDto.getQualite() != null)
        {
            statutPersonne.setQualite(qualiteMapper.deQualiteDto(statutPersonneDto.getQualite()));
        }
        if(statutPersonneDto.getPersonnel() != null && statutPersonneDto.getPersonnel().getIdPersonne() != null)
        {
            statutPersonne.setPersonnel(personnelMapper.dePersonnelDto(statutPersonneDto.getPersonnel()));
        }
        statutPersonne = statutPersonneDao.save(statutPersonne);
        return statutPersonneMapper.deStatutPersonne(statutPersonne);
    }

    @Override
    public void supprimerStatutPersonne(CleStatutPersonne idStatutPersonne) {
        statutPersonneDao.deleteById(idStatutPersonne);
    }
}
