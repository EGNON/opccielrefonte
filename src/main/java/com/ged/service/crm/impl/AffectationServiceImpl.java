package com.ged.service.crm.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.crm.AffectationDao;
import com.ged.dao.standard.PersonnelDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.AffectationDto;
import com.ged.dto.crm.ObjectifAffecteDto;
import com.ged.entity.crm.Affectation;
import com.ged.entity.crm.CleObjectifAffecte;
import com.ged.entity.standard.Personnel;
import com.ged.mapper.crm.AffectationMapper;
import com.ged.service.crm.AffectationService;
import com.ged.service.crm.ObjectifAffecteService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AffectationServiceImpl implements AffectationService {
    private final AffectationDao affectationDao;
    private final PersonnelDao personnelDao;
    private final ObjectifAffecteService objectifAffecteService;
    private final AffectationMapper affectationMapper;

    public AffectationServiceImpl(AffectationDao affectationDao, PersonnelDao personnelDao, ObjectifAffecteService objectifAffecteService, AffectationMapper affectationMapper) {
        this.affectationDao = affectationDao;
        this.personnelDao = personnelDao;
        this.objectifAffecteService = objectifAffecteService;
        this.affectationMapper = affectationMapper;
    }

    @Override
    public DataTablesResponse<AffectationDto> afficherTous(DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());
        Page<Affectation> affectationPage = affectationDao.findAll(pageable);

        List<AffectationDto> content = affectationPage.getContent().stream().map(affectationMapper::deAffectation).collect(Collectors.toList());
        DataTablesResponse<AffectationDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)affectationPage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)affectationPage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public Page<AffectationDto> afficherTousParPage(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.DESC,"dateAffectation");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Affectation> affectationPage= affectationDao.findAll(pageRequest);
        return new PageImpl<>(affectationPage.getContent().stream().map(affectationMapper::deAffectation).collect(Collectors.toList()), pageRequest, affectationPage.getTotalElements());

    }

    @Override
    public List<AffectationDto> afficherTous() {
        ModelMapper modelMapper = new ModelMapper();
        return affectationDao.findAll().stream().map(affectationMapper::deAffectation)
                .collect(Collectors.toList());
    }

    @Override
    public List<AffectationDto> afficherSelonPersonnel(long idPersonne) {
        Personnel personnel = personnelDao.findById(idPersonne).orElseThrow();
//        ModelMapper modelMapper = new ModelMapper();
        return affectationDao.findByPersonnel(personnel).stream().map(affectationMapper::deAffectation)
                .collect(Collectors.toList());
    }

    @Override
    public Affectation afficherSelonId(Long id) {
        return affectationDao.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Affectation.class, id.toString()));
    }

    @Override
    public AffectationDto afficher(Long id) {
        ModelMapper modelMapper = new ModelMapper();
        return affectationMapper.deAffectation(afficherSelonId(id));
    }

    @Override
    public AffectationDto creer(AffectationDto affectationDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Affectation affectation = modelMapper.map(affectationDto, Affectation.class);
        affectation = affectationDao.save(affectation);
        if(affectation.getIdAffectation() != null)
        {
            for (ObjectifAffecteDto objectifAffecteDto: affectationDto.getObjectifAffectes()) {
//                AffectationDto affectationDto1 = new AffectationDto();
//                affectationDto1.setIdAffectation(affectation);
                objectifAffecteDto.setAffectation(modelMapper.map(affectation, AffectationDto.class));
                objectifAffecteDto = objectifAffecteService.creerObjectifAffecte(objectifAffecteDto);
            }
        }
        return modelMapper.map(affectation, AffectationDto.class);
    }

    @Override
    public AffectationDto modifier(AffectationDto affectationDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Affectation affectation = afficherSelonId(affectationDto.getIdAffectation());
        affectation.setPersonnel(modelMapper.map(affectationDto.getPersonnel(), Personnel.class));
        affectation.setDateSoumission(affectationDto.getDateSoumission());
        affectation = affectationDao.save(affectation);
        if(affectation.getIdAffectation() != null)
        {
            for (ObjectifAffecteDto objectifAffecteDto: affectationDto.getObjectifAffectes()) {
                objectifAffecteDto.setAffectation(affectationDto);
                CleObjectifAffecte cleObjectifAffecte = new CleObjectifAffecte();
                cleObjectifAffecte.setIdAffectation(affectation.getIdAffectation());
                cleObjectifAffecte.setIdPeriodicite(objectifAffecteDto.getPeriodicite().getIdPeriodicite());
                cleObjectifAffecte.setIdCategorie(objectifAffecteDto.getCategoriePersonne().getIdCategorie());
                cleObjectifAffecte.setIdIndicateur(objectifAffecteDto.getIndicateur().getIdIndicateur());
                objectifAffecteService.supprimerObjectifAffecte(cleObjectifAffecte);
                objectifAffecteDto = objectifAffecteService.creerObjectifAffecte(objectifAffecteDto);
            }
        }
        return modelMapper.map(affectation, AffectationDto.class);
    }

    @Override
    public void supprimer(Long id) {
        affectationDao.deleteById(id);
    }
}
