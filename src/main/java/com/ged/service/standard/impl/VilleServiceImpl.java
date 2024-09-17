package com.ged.service.standard.impl;

import com.ged.dao.standard.VilleDao;
import com.ged.dto.standard.VilleDto;
import com.ged.entity.standard.Ville;
import com.ged.mapper.standard.VilleMapper;
import com.ged.service.standard.VilleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VilleServiceImpl implements VilleService {
    private final VilleDao villeDao;
    private final VilleMapper villeMapper;

    public VilleServiceImpl(VilleDao villeDao, VilleMapper villeMapper) {
        this.villeDao = villeDao;
        this.villeMapper = villeMapper;
    }

    @Override
    public Page<VilleDto> afficherVilles(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleVille");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Ville> VillePage= villeDao.findAll(pageRequest);
        return new PageImpl<>(VillePage.getContent().stream().map(villeMapper::deVille).collect(Collectors.toList()),pageRequest,VillePage.getTotalElements());
    }

    @Override
    public List<VilleDto> afficherVilleListe() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleVille");
//        List<Ville> villes=villeDao.findAll();
        return villeDao.findAll(sort).stream().map(villeMapper::deVille).collect(Collectors.toList());
    }

    @Override
    public Ville afficherVilleSelonId(long idVille) {
        return villeDao.findById(idVille).orElseThrow(()-> new EntityNotFoundException("Ville avec ID "+idVille+" est introuvable"));
    }

    @Override
    public VilleDto afficherVille(long idVille) {
        return villeMapper.deVille(afficherVilleSelonId(idVille));
    }

    @Override
    public VilleDto creerVille(VilleDto VilleDto) {
        Ville Ville= villeMapper.deVilleDto(VilleDto);
        Ville VilleSave= villeDao.save(Ville);
        return villeMapper.deVille(VilleSave);
    }

    @Override
    public VilleDto modifierVille(VilleDto VilleDto) {
        Ville Ville= villeMapper.deVilleDto(VilleDto);
        Ville VilleMaj= villeDao.save(Ville);
        return villeMapper.deVille(VilleMaj);
    }

    @Override
    public void supprimerVille(long idVille) {
        villeDao.deleteById(idVille);
    }
}
