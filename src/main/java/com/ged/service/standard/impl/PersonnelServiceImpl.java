package com.ged.service.standard.impl;

import com.ged.dao.standard.PaysDao;
import com.ged.dao.standard.PersonnelDao;
import com.ged.dao.standard.ProfessionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PersonnelDto;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.Personnel;
import com.ged.entity.standard.Profession;
import com.ged.mapper.standard.PersonnelMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.PersonnelService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonnelServiceImpl implements PersonnelService {
    private final PersonnelDao personnelDao;
    private final ProfessionDao professionDao;
    private final PaysDao paysDao;
    private final PersonnelMapper personnelMapper;

    public PersonnelServiceImpl(PersonnelDao personnelDao, ProfessionDao professionDao, PaysDao paysDao, PersonnelMapper personnelMapper) {
        this.personnelDao = personnelDao;
        this.professionDao = professionDao;
        this.paysDao = paysDao;
        this.personnelMapper = personnelMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"denomination");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(), sort);
            Page<Personnel> degrePage = personnelDao.findAll(pageable);
            List<PersonnelDto> content = degrePage.getContent().stream().map(personnelMapper::dePersonnel).collect(Collectors.toList());
            DataTablesResponse<PersonnelDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)degrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)degrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des personnels par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public Page<PersonnelDto> afficherPersonnels(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"denomination");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Personnel> personnelPage=personnelDao.findAll(pageRequest);
        return new PageImpl<>(personnelPage.getContent().stream().map(personnelMapper::dePersonnel).collect(Collectors.toList()),pageRequest,personnelPage.getTotalElements());
    }

    @Override
    public List<PersonnelDto> afficherPersonnelListe() {
        Sort sort=Sort.by(Sort.Direction.ASC,"denomination");
        List<Personnel> personnels=personnelDao.findAll(sort);
        return personnels.stream().map(personnelMapper::dePersonnel).collect(Collectors.toList());
    }

    @Override
    public List<PersonnelDto> afficherPersonnelSelonEstCommercil(boolean estCommercial) {
        Sort sort=Sort.by(Sort.Direction.ASC,"denomination");
        return personnelDao.findByEstCommercial(estCommercial).stream().map(personnelMapper::dePersonnel).collect(Collectors.toList());
    }

    @Override
    public Personnel afficherPersonnelSelonId(long idPersonne) {
        return personnelDao.findById(idPersonne).orElseThrow(()-> new EntityNotFoundException("Personnel avec ID "+idPersonne+" est introuvable"));
    }

    @Override
    public PersonnelDto afficherPersonnel(long idPersonne) {
        return personnelMapper.dePersonnel(afficherPersonnelSelonId(idPersonne));
    }

    @Override
    public PersonnelDto creerPersonnel(PersonnelDto personnelDto) {
        Personnel personnel = personnelMapper.dePersonnelDto(personnelDto);
        personnel.setDenomination(personnelDto.getNom() + " " + personnelDto.getPrenom());
        if (personnelDto.getProfession() != null && personnelDto.getProfession().getIdProf() != null) {
            Profession profession = professionDao.findById(personnelDto.getProfession().getIdProf())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Profession.class, personnelDto.getProfession().getIdProf().toString()));
            personnel.setProfession(profession);
        }
        if (personnelDto.getPaysNationalite() != null && personnelDto.getPaysNationalite().getIdPays() != null) {
            Pays paysNationalite = paysDao.findById(personnelDto.getPaysNationalite().getIdPays())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnelDto.getPaysNationalite().getIdPays().toString()));
            personnel.setPaysNationalite(paysNationalite);
        }
        if (personnelDto.getPaysResidence() != null && personnelDto.getPaysResidence().getIdPays() != null) {
            Pays paysResidence = paysDao.findById(personnelDto.getPaysResidence().getIdPays())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnelDto.getPaysResidence().getIdPays().toString()));
            personnel.setPaysResidence(paysResidence);
        }
        Personnel personnelSave=personnelDao.save(personnel);
        return personnelMapper.dePersonnel(personnelSave);
    }

    @Override
    public PersonnelDto modifierPersonnel(PersonnelDto personnelDto) {
        Personnel personnel = personnelMapper.dePersonnelDto(personnelDto);
        personnel.setDenomination(personnelDto.getNom() + " " + personnelDto.getPrenom());
        if (personnelDto.getProfession() != null && personnelDto.getProfession().getIdProf() != null) {
            Profession profession = professionDao.findById(personnelDto.getProfession().getIdProf())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Profession.class, personnelDto.getProfession().getIdProf().toString()));
            personnel.setProfession(profession);
        }
        if (personnelDto.getPaysNationalite() != null && personnelDto.getPaysNationalite().getIdPays() != null) {
            Pays paysNationalite = paysDao.findById(personnelDto.getPaysNationalite().getIdPays())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnelDto.getPaysNationalite().getIdPays().toString()));
            personnel.setPaysNationalite(paysNationalite);
        }
        if (personnelDto.getPaysResidence() != null && personnelDto.getPaysResidence().getIdPays() != null) {
            Pays paysResidence = paysDao.findById(personnelDto.getPaysResidence().getIdPays())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnelDto.getPaysResidence().getIdPays().toString()));
            personnel.setPaysResidence(paysResidence);
        }
        Personnel personnelMaj = personnelDao.save(personnel);
        return personnelMapper.dePersonnel(personnelMaj);
    }

    @Override
    public void supprimerPersonnel(long idPersonne) {
        personnelDao.deleteById(idPersonne);
    }
}
