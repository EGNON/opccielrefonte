package com.ged.service.crm.impl;

import com.ged.dao.crm.ObjectifAffecteDao;
import com.ged.dto.crm.*;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.entity.crm.CleObjectifAffecte;
import com.ged.entity.crm.ObjectifAffecte;
import com.ged.mapper.crm.ObjectifAffecteMapper;
import com.ged.projection.ObjectifAffecteProjection;
import com.ged.service.crm.ObjectifAffecteService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ObjectifAffecteServiceImpl implements ObjectifAffecteService {
    private final ObjectifAffecteDao objectifAffecteDao;
    private final ObjectifAffecteMapper objectifAffecteMapper;

    public ObjectifAffecteServiceImpl(ObjectifAffecteDao objectifAffecteDao, ObjectifAffecteMapper objectifAffecteMapper) {
        this.objectifAffecteDao = objectifAffecteDao;
        this.objectifAffecteMapper = objectifAffecteMapper;
    }

    @Override
    public Page<ObjectifAffecteDto> afficherObjectifAffectes(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        Page<ObjectifAffecte> objectifAffectePage=objectifAffecteDao.findAll(pageRequest);
        return new PageImpl<>(objectifAffectePage.getContent().stream().map(objectifAffecteMapper::deObjectifAffecte).collect(Collectors.toList()),pageRequest,objectifAffectePage.getTotalElements());
    }

    @Override
    public List<ObjectifAffecteEtatDto> afficherSelonPersonnelEtPeriodicite(long idPersonne, BeginEndDateParameter beginEndDateParameter) {
        System.out.println("dateDebut="+beginEndDateParameter.getStartDate());
        System.out.println("dateFin="+beginEndDateParameter.getEndDate());
        return objectifAffecteDao.afficherSelonPersonnelEtPeriodicite(idPersonne,beginEndDateParameter.getStartDate()
                ,beginEndDateParameter.getEndDate()).stream().map(
                objectifAffecteMapper::deObjectifAffecteEtat
        ).collect(Collectors.toList());
    }

    @Override
    public List<ObjectifAffecteProjection> afficherSelonPersonnelEtPeriodiciteEtat(String etat,long idPersonne, BeginEndDateParameter beginEndDateParameter, HttpServletResponse response) throws IOException, JRException {
        List<ObjectifAffecteProjection> list=objectifAffecteDao.afficherSelonPersonnelEtPeriodiciteEtat(idPersonne,beginEndDateParameter.getStartDate()
                ,beginEndDateParameter.getEndDate()).stream().collect(Collectors.toList());

        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        parameters.put("letterDate", letterDate);
        File file = ResourceUtils.getFile("classpath:objectif_"+etat+".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters , dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        return list;
    }

    @Override
    public ObjectifAffecte afficherObjectifAffecteSelonId(CleObjectifAffecte idObjectifAffecte) {
        return objectifAffecteDao.findById(idObjectifAffecte).orElseThrow(()-> new EntityNotFoundException("Elément introuvable"));
    }

    @Override
    public ObjectifAffecteDto creerObjectifAffecte(ObjectifAffecteDto objectifAffecteDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ObjectifAffecte objectifAffecte = modelMapper.map(objectifAffecteDto, ObjectifAffecte.class);

        CleObjectifAffecte cleObjectifAffecte = new CleObjectifAffecte();
        cleObjectifAffecte.setIdAffectation(objectifAffecteDto.getAffectation().getIdAffectation());
        cleObjectifAffecte.setIdPeriodicite(objectifAffecteDto.getPeriodicite().getIdPeriodicite());
        cleObjectifAffecte.setIdCategorie(objectifAffecteDto.getCategoriePersonne().getIdCategorie());
        cleObjectifAffecte.setIdIndicateur(objectifAffecteDto.getIndicateur().getIdIndicateur());
        objectifAffecte.setIdObjectifAffecte(cleObjectifAffecte);

        objectifAffecte = objectifAffecteDao.save(objectifAffecte);
        return modelMapper.map(objectifAffecte, ObjectifAffecteDto.class);
    }

    @Override
    public ObjectifAffecteDto modifierObjectifAffecte(ObjectifAffecteDto objectifAffecteDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        ObjectifAffecte objectifAffecte = modelMapper.map(objectifAffecteDto, ObjectifAffecte.class);

        CleObjectifAffecte cleObjectifAffecte = new CleObjectifAffecte();
        cleObjectifAffecte.setIdAffectation(objectifAffecteDto.getAffectation().getIdAffectation());
        cleObjectifAffecte.setIdPeriodicite(objectifAffecteDto.getPeriodicite().getIdPeriodicite());
        cleObjectifAffecte.setIdCategorie(objectifAffecteDto.getCategoriePersonne().getIdCategorie());
        cleObjectifAffecte.setIdIndicateur(objectifAffecteDto.getIndicateur().getIdIndicateur());
        objectifAffecte.setIdObjectifAffecte(cleObjectifAffecte);

        objectifAffecte = objectifAffecteDao.save(objectifAffecte);
        return modelMapper.map(objectifAffecte, ObjectifAffecteDto.class);
    }

    @Override
    public void supprimerObjectifAffecte(CleObjectifAffecte idObjectifAffecte) {
        objectifAffecteDao.deleteById(idObjectifAffecte);
    }

    @Override
    public void supprimerSelonIdAffectation(Long id) {

    }
}
