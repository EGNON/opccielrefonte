package com.ged.service.crm.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.crm.ModeleObjectifDao;
import com.ged.dto.crm.DetailObjectifDto;
import com.ged.dto.crm.ModeleObjectifDto;
import com.ged.mapper.crm.ModeleObjectifMapper;
import com.ged.service.crm.DetailObjectifService;
import com.ged.service.crm.ModeleObjectifService;
import com.ged.entity.crm.CleDetailObjectif;
import com.ged.entity.crm.ModeleObjectif;
import org.modelmapper.ModelMapper;
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
public class ModeleObjectifServiceImpl implements ModeleObjectifService {
    private final ModeleObjectifDao modeleObjectifDao;
    private final ModeleObjectifMapper modeleObjectifMapper;
    private final DetailObjectifService detailObjectifService;

    public ModeleObjectifServiceImpl(ModeleObjectifDao modeleObjectifDao, ModeleObjectifMapper modeleObjectifMapper, DetailObjectifService detailObjectifService) {
        this.modeleObjectifDao = modeleObjectifDao;
        this.modeleObjectifMapper = modeleObjectifMapper;
        this.detailObjectifService = detailObjectifService;
    }

    @Override
    public Page<ModeleObjectifDto> afficherParPage(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"nomModele");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<ModeleObjectif> modeleObjectifDtoPage = modeleObjectifDao.findAll(pageRequest);
        return new PageImpl<>(modeleObjectifDtoPage.getContent().stream().map(modeleObjectifMapper::deModeleObjectif).collect(Collectors.toList()), pageRequest, modeleObjectifDtoPage.getTotalElements());
    }

    @Override
    public List<ModeleObjectifDto> afficherTous() {
        Sort sort=Sort.by(Sort.Direction.ASC,"nomModele");
        return modeleObjectifDao.findAll(sort).stream().map(modeleObjectifMapper::deModeleObjectif).collect(Collectors.toList());
    }

    @Override
    public ModeleObjectif afficherSelonId(Long id) {
        return modeleObjectifDao.findById(id).orElseThrow(() -> new EntityNotFoundException(ModeleObjectif.class, id.toString()));
    }

    @Override
    public ModeleObjectifDto afficher(Long id) {
        return modeleObjectifMapper.deModeleObjectif(afficherSelonId(id));
    }

    @Override
    public ModeleObjectifDto creer(ModeleObjectifDto modeleObjectifDto) {
        ModeleObjectif modeleObjectif = modeleObjectifMapper.deModeleObjectifDto(modeleObjectifDto);
        ModelMapper modelMapper = new ModelMapper();
        modeleObjectif = modeleObjectifDao.save(modeleObjectif);
        for (DetailObjectifDto detailObjectifDto: modeleObjectifDto.getDetailObjectifs())
        {
            detailObjectifDto.setModelObj(modelMapper.map(modeleObjectif, ModeleObjectifDto.class));
            detailObjectifService.creerDetailObjectif(detailObjectifDto);
        }
        return modeleObjectifDto;
    }

    @Override
    public ModeleObjectifDto modifier(ModeleObjectifDto modeleObjectifDto) {
        ModeleObjectif modeleObjectif = modeleObjectifMapper.deModeleObjectifDto(modeleObjectifDto);
        ModelMapper modelMapper = new ModelMapper();
        modeleObjectif = modeleObjectifDao.save(modeleObjectif);
        for (DetailObjectifDto detailObjectifDto: modeleObjectifDto.getDetailObjectifs())
        {
            CleDetailObjectif cleDetailObjectif = new CleDetailObjectif();
            cleDetailObjectif.setIdIndicateur(detailObjectifDto.getIndicateur().getIdIndicateur());
            cleDetailObjectif.setIdCategorie(detailObjectifDto.getCategoriePersonne().getIdCategorie());
            cleDetailObjectif.setIdPeriodicite(detailObjectifDto.getPeriodicite().getIdPeriodicite());
            detailObjectifService.supprimerDetailObjectif(cleDetailObjectif);

            detailObjectifDto.setModelObj(modelMapper.map(modeleObjectif, ModeleObjectifDto.class));
            detailObjectifService.creerDetailObjectif(detailObjectifDto);
        }
        return modeleObjectifDto;
    }

    @Override
    public void supprimer(Long id) {
        modeleObjectifDao.deleteById(id);
    }
}
