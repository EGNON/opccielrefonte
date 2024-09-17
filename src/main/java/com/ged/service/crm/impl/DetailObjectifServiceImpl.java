package com.ged.service.crm.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.crm.DetailObjectifDao;
import com.ged.dto.crm.DetailObjectifDto;
import com.ged.entity.crm.CleDetailObjectif;
import com.ged.entity.crm.DetailObjectif;
import com.ged.mapper.crm.DetailObjectifMapper;
import com.ged.service.crm.DetailObjectifService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@Transactional
public class DetailObjectifServiceImpl implements DetailObjectifService {
    private final DetailObjectifDao detailObjectifDao;
    private final DetailObjectifMapper detailObjectifMapper;

    public DetailObjectifServiceImpl(DetailObjectifDao detailObjectifDao,
                                     DetailObjectifMapper detailObjectifMapper) {
        this.detailObjectifDao = detailObjectifDao;
        this.detailObjectifMapper = detailObjectifMapper;
    }

    @Override
    public Page<DetailObjectifDto> afficherDetailObjectifs(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DetailObjectif> detailObjectifPage = detailObjectifDao.findAll(pageRequest);
        return new PageImpl<>(detailObjectifPage.getContent().stream().map(detailObjectifMapper::deDetailObjectif).collect(Collectors.toList()), pageRequest, detailObjectifPage.getTotalElements());
    }

    @Override
    public DetailObjectif afficherDetailObjectifSelonId(CleDetailObjectif idDetailObjectif) {
        return detailObjectifDao.findById(idDetailObjectif).orElseThrow(() -> new EntityNotFoundException(DetailObjectif.class, "id", idDetailObjectif.toString()));
    }

    @Override
    public DetailObjectifDto creerDetailObjectif(DetailObjectifDto detailObjectifDto) {
        ModelMapper modelMapper = new ModelMapper();
        DetailObjectif detailObjectif = modelMapper.map(detailObjectifDto, DetailObjectif.class);
        CleDetailObjectif cleDetailObjectif = new CleDetailObjectif();
        cleDetailObjectif.setIdIndicateur(detailObjectifDto.getIndicateur().getIdIndicateur());
        cleDetailObjectif.setIdCategorie(detailObjectifDto.getCategoriePersonne().getIdCategorie());
        cleDetailObjectif.setIdPeriodicite(detailObjectifDto.getPeriodicite().getIdPeriodicite());
        detailObjectif.setIdDetail(cleDetailObjectif);

        detailObjectif = detailObjectifDao.save(detailObjectif);
        return detailObjectifMapper.deDetailObjectif(detailObjectif);
    }

    @Override
    public DetailObjectifDto modifierDetailObjectif(DetailObjectifDto detailObjectifDto) {
        ModelMapper modelMapper = new ModelMapper();
        DetailObjectif detailObjectif = modelMapper.map(detailObjectifDto, DetailObjectif.class);
        CleDetailObjectif cleDetailObjectif = new CleDetailObjectif();
        cleDetailObjectif.setIdIndicateur(detailObjectifDto.getIndicateur().getIdIndicateur());
        cleDetailObjectif.setIdCategorie(detailObjectifDto.getCategoriePersonne().getIdCategorie());
        cleDetailObjectif.setIdPeriodicite(detailObjectifDto.getPeriodicite().getIdPeriodicite());
        detailObjectif.setIdDetail(cleDetailObjectif);

        detailObjectif = detailObjectifDao.save(detailObjectif);
        return detailObjectifMapper.deDetailObjectif(detailObjectif);
    }

    @Override
    public void supprimerDetailObjectif(CleDetailObjectif cleDetailObjectif) {
        detailObjectifDao.deleteById(cleDetailObjectif);
    }
}
