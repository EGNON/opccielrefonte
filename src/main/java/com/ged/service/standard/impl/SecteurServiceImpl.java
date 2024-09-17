package com.ged.service.standard.impl;

import com.ged.dao.standard.SecteurDao;
import com.ged.dto.standard.SecteurDto;
import com.ged.entity.standard.Secteur;
import com.ged.mapper.standard.SecteurMapper;
import com.ged.service.standard.SecteurService;
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
public class SecteurServiceImpl implements SecteurService {
    private final SecteurDao secteurDao;
    private final SecteurMapper secteurMapper;

    public SecteurServiceImpl(SecteurDao secteurDao, SecteurMapper secteurMapper) {
        this.secteurDao = secteurDao;
        this.secteurMapper = secteurMapper;
    }

    @Override
    public Page<SecteurDto> afficherSecteurs(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleSecteur");
        PageRequest pageRequest = PageRequest.of(page, size,sort);
        Page<Secteur> secteurPage = secteurDao.findAll(pageRequest);
        return new PageImpl<>(secteurPage.getContent().stream().map(secteurMapper::deSecteur).collect(Collectors.toList()), pageRequest, secteurPage.getTotalElements());
    }

    @Override
    public List<SecteurDto> afficherSecteurs() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleSecteur");
        return secteurDao.findAll(sort).stream().map(secteurMapper::deSecteur).collect(Collectors.toList());
    }

    @Override
    public SecteurDto afficherSecteur(Long id) {
        return secteurMapper.deSecteur(afficherSecteurSelonId(id));
    }

    @Override
    public SecteurDto rechercherSecteurParLibelle(String libelle) {
        Secteur secteur=secteurDao.findByLibelleSecteurIgnoreCase(libelle).orElse(null);
        if(secteur!=null)
            return secteurMapper.deSecteur(secteur);
        else
            return null;
    }

    @Override
    public Secteur afficherSecteurSelonId(long idSecteur) {
        return secteurDao.findById(idSecteur).orElseThrow(() -> new EntityNotFoundException("Secteur avec ID " + idSecteur + " introuvable"));
    }

    @Override
    public SecteurDto creerSecteur(SecteurDto secteurDto) {
        Secteur secteur = secteurMapper.deSecteurDto(secteurDto);
        Secteur secteurSaved = secteurDao.save(secteur);
        return secteurMapper.deSecteur(secteurSaved);
    }

    @Override
    public SecteurDto modifierSecteur(SecteurDto secteurDto) {
        Secteur secteurOld = afficherSecteurSelonId(secteurDto.getIdSecteur());
        Secteur secteur = secteurMapper.deSecteurDto(secteurDto);
        Secteur secteurMaj = secteurDao.save(secteur);
        return secteurMapper.deSecteur(secteurMaj);
    }

    @Override
    public void supprimerSecteur(Long idSecteur) {
        secteurDao.deleteById(idSecteur);
    }
}
