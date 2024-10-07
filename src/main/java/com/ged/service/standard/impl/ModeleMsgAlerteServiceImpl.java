package com.ged.service.standard.impl;

import com.ged.dao.standard.ModeleMsgAlerteDao;
import com.ged.dao.standard.TypeModeleDao;
import com.ged.dto.standard.ModeleMsgAlerteDto;
import com.ged.entity.standard.ModeleMsgAlerte;
import com.ged.entity.standard.TypeModele;
import com.ged.mapper.standard.ModeleMsgAlerteMapper;
import com.ged.service.standard.ModeleMsgAlerteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ModeleMsgAlerteServiceImpl implements ModeleMsgAlerteService {
    private final ModeleMsgAlerteDao modeleMsgAlerteDao;
    private final TypeModeleDao typeModeleDao;
    private final ModeleMsgAlerteMapper modeleMsgAlerteMapper;

    public ModeleMsgAlerteServiceImpl(ModeleMsgAlerteDao modeleMsgAlerteDao, TypeModeleDao typeModeleDao, ModeleMsgAlerteMapper modeleMsgAlerteMapper) {
        this.modeleMsgAlerteDao = modeleMsgAlerteDao;
        this.typeModeleDao = typeModeleDao;
        this.modeleMsgAlerteMapper = modeleMsgAlerteMapper;
    }

    @Override
    public Page<ModeleMsgAlerteDto> afficherModeleMsgAlertes(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"objet");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<ModeleMsgAlerte> modeleMsgAlertePage=modeleMsgAlerteDao.findAll(pageRequest);
        return new PageImpl<>(modeleMsgAlertePage.getContent().stream().map(modeleMsgAlerteMapper::deModeleMsgAlerte).collect(Collectors.toList()),pageRequest,modeleMsgAlertePage.getTotalElements());
    }

    @Override
    public ModeleMsgAlerte afficherModeleMsgAlerteSelonId(long idModeleMsgAlerte) {
        return modeleMsgAlerteDao.findById(idModeleMsgAlerte).orElseThrow(()-> new EntityNotFoundException("ModeleMsgAlerte avec ID "+idModeleMsgAlerte+" est introuvable"));
    }

    @Override
    public ModeleMsgAlerteDto afficherModeleMsgAlerteSelonTypeModeleEtDefaut(String libelle) {
        return modeleMsgAlerteMapper.deModeleMsgAlerte(modeleMsgAlerteDao.recherherSelonTypeModeleEtDefaut(libelle));
    }

    @Override
    public List<ModeleMsgAlerteDto> afficherModeleMsgAlerteSelonTypeModele(String libelle) {
        return modeleMsgAlerteDao.rechercherSelonTypeModele(libelle).stream().map(modeleMsgAlerteMapper::deModeleMsgAlerte).collect(Collectors.toList());
    }

    @Override
    public ModeleMsgAlerteDto afficherModeleMsgAlerte(long idModeleMsgAlerte) {
        return modeleMsgAlerteMapper.deModeleMsgAlerte(afficherModeleMsgAlerteSelonId(idModeleMsgAlerte));
    }

    @Override
    public ModeleMsgAlerteDto creerModeleMsgAlerte(ModeleMsgAlerteDto modeleMsgAlerteDto) {
        ModeleMsgAlerte modeleMsgAlerte=modeleMsgAlerteMapper.deModeleMsgAlerteDto(modeleMsgAlerteDto);
        if(modeleMsgAlerteDto.getTypeModele()!=null)
        {
            TypeModele typeModele=new TypeModele();
            typeModele=typeModeleDao.findById(modeleMsgAlerteDto.getTypeModele().getIdTypeModele()).orElseThrow();
            modeleMsgAlerte.setTypeModele(typeModele);
        }
        modeleMsgAlerte.setDateModeleMsgAlerte(LocalDateTime.now());

        ModeleMsgAlerte modeleMsgAlerteSave=modeleMsgAlerteDao.save(modeleMsgAlerte);
        return modeleMsgAlerteMapper.deModeleMsgAlerte(modeleMsgAlerteSave);
    }

    @Override
    public ModeleMsgAlerteDto modifierModeleMsgAlerte(ModeleMsgAlerteDto modeleMsgAlerteDto) {
        ModeleMsgAlerte modeleMsgAlerte=modeleMsgAlerteMapper.deModeleMsgAlerteDto(modeleMsgAlerteDto);
        if(modeleMsgAlerteDto.getTypeModele()!=null)
        {
            TypeModele typeModele = typeModeleDao.findById(modeleMsgAlerteDto.getTypeModele().getIdTypeModele()).orElseThrow();
            modeleMsgAlerte.setTypeModele(typeModele);
        }
        ModeleMsgAlerte modeleMsgAlerteMaj=modeleMsgAlerteDao.save(modeleMsgAlerte);
        return modeleMsgAlerteMapper.deModeleMsgAlerte(modeleMsgAlerteMaj);
    }

    @Override
    public void supprimerModeleMsgAlerte(long idModeleMsgAlerte) {
        modeleMsgAlerteDao.deleteById(idModeleMsgAlerte);
    }

    @Override
    public int modifier(Long idModele) {
        return modeleMsgAlerteDao.modifier(idModele);
    }
}
