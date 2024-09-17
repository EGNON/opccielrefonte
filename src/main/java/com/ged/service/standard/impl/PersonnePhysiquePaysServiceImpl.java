package com.ged.service.standard.impl;

import com.ged.dao.standard.PersonnePhysiqueDao;
import com.ged.dao.standard.PersonnePhysiquePaysDao;
import com.ged.dto.standard.PersonnePhysiquePaysDto;
import com.ged.dto.standard.PersonnePhysiquePaysDtoEJ;
import com.ged.mapper.standard.PersonnePhysiquePaysMapper;
import com.ged.service.standard.PersonnePhysiquePaysService;
import com.ged.entity.standard.ClePersonnePhysiquePays;
import com.ged.entity.standard.PersonnePhysique;
import com.ged.entity.standard.PersonnePhysiquePays;
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
public class PersonnePhysiquePaysServiceImpl implements PersonnePhysiquePaysService {
    private final PersonnePhysiquePaysDao personnePhysiquePaysDao;
    private final PersonnePhysiqueDao personnePhysiqueDao;
    private final PersonnePhysiquePaysMapper personnePhysiquePaysMapper;

    public PersonnePhysiquePaysServiceImpl(PersonnePhysiquePaysDao personnePhysiquePaysDao, PersonnePhysiqueDao personnePhysiqueDao, PersonnePhysiquePaysMapper personnePhysiquePaysMapper) {
        this.personnePhysiquePaysDao = personnePhysiquePaysDao;
        this.personnePhysiqueDao = personnePhysiqueDao;
        this.personnePhysiquePaysMapper = personnePhysiquePaysMapper;
    }

    @Override
    public Page<PersonnePhysiquePaysDto> afficherPersonnePhysiquePays(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        Page<PersonnePhysiquePays> personnePhysiquePaysPage=personnePhysiquePaysDao.findAll(pageRequest);
        return new PageImpl<>(personnePhysiquePaysPage.getContent().stream().map(personnePhysiquePaysMapper::dePersonnePhysiquePays).collect(Collectors.toList()),pageRequest,personnePhysiquePaysPage.getTotalElements());
    }

    @Override
    public PersonnePhysiquePays afficherPersonnePhysiquePaysSelonId(ClePersonnePhysiquePays idPersonnePhysiquePays) {
        return personnePhysiquePaysDao.findById(idPersonnePhysiquePays).orElseThrow(()-> new EntityNotFoundException("Elément introuvable"));
    }

    @Override
    public List<PersonnePhysiquePaysDto> afficherSelonPersonnePhysique(long idPersonne) {
        PersonnePhysique personnePhysique=personnePhysiqueDao.findById(idPersonne).orElseThrow();
        return personnePhysiquePaysDao.findByPersonnePhysique(personnePhysique).stream().map(
                personnePhysiquePaysMapper::dePersonnePhysiquePays).collect(Collectors.toList());
    }

    @Override
    public PersonnePhysiquePaysDto creerPersonnePhysiquePays(PersonnePhysiquePaysDto personnePhysiquePaysDto) {
        PersonnePhysiquePays personnePhysiquePays=personnePhysiquePaysMapper.dePersonnePhysiquePaysDto(personnePhysiquePaysDto);
        PersonnePhysiquePays personnePhysiquePaysSave=personnePhysiquePaysDao.save(personnePhysiquePays);
        return personnePhysiquePaysMapper.dePersonnePhysiquePays(personnePhysiquePaysSave);
    }

    @Override
    public PersonnePhysiquePaysDtoEJ creerPersonnePhysiquePaysEJ(PersonnePhysiquePaysDtoEJ personnePhysiquePaysDtoEJ) {
        PersonnePhysiquePays personnePhysiquePays=personnePhysiquePaysMapper.dePersonnePhysiquePaysDtoEJ(personnePhysiquePaysDtoEJ);
        PersonnePhysiquePays personnePhysiquePaysSave=personnePhysiquePaysDao.save(personnePhysiquePays);
        return personnePhysiquePaysMapper.dePersonnePhysiquePaysEJ(personnePhysiquePaysSave);
    }


    @Override
    public PersonnePhysiquePaysDto modifierPersonnePhysiquePays(PersonnePhysiquePaysDto personnePhysiquePaysDto) {
        ClePersonnePhysiquePays clePersonnePhysiquePays = new ClePersonnePhysiquePays();
        clePersonnePhysiquePays.setIdPersonne(personnePhysiquePaysDto.getPersonnePhysiqueDto().getIdPersonne());
        clePersonnePhysiquePays.setIdPays(personnePhysiquePaysDto.getPaysDto().getIdPays());
        PersonnePhysiquePays personnePhysiquePays = afficherPersonnePhysiquePaysSelonId(clePersonnePhysiquePays);
        if(personnePhysiquePays.getIdPersonnePhysiquePays() == null)
            return personnePhysiquePaysDto;

        personnePhysiquePays=personnePhysiquePaysMapper.dePersonnePhysiquePaysDto(personnePhysiquePaysDto);
        PersonnePhysiquePays personnePhysiquePaysMaj=personnePhysiquePaysDao.save(personnePhysiquePays);
        return personnePhysiquePaysMapper.dePersonnePhysiquePays(personnePhysiquePaysMaj);
    }

    @Override
    public void supprimerPersonnePhysiquePays(ClePersonnePhysiquePays idPersonnePhysiquePays) {
        personnePhysiquePaysDao.deleteById(idPersonnePhysiquePays);
    }

    @Override
    public void supprimerSelonPersonne(long idPersonne) {
        PersonnePhysique personnePhysique=new PersonnePhysique();
        personnePhysique=personnePhysiqueDao.findById(idPersonne).orElseThrow();
        personnePhysiquePaysDao.deleteByPersonnePhysique(personnePhysique);
    }
}
