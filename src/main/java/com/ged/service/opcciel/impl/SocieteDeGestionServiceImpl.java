package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.FormeJuridiqueDao;
import com.ged.dao.opcciel.SocieteDeGestionDao;
import com.ged.dao.standard.*;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.SocieteDeGestionDto;
import com.ged.entity.opcciel.SocieteDeGestion;
import com.ged.entity.standard.Commune;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.Secteur;
import com.ged.mapper.opcciel.SocieteDeGestionMapper;
import com.ged.service.opcciel.SocieteDeGestionService;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SocieteDeGestionServiceImpl implements SocieteDeGestionService {
    private final SocieteDeGestionDao societeDeGestionDao;
    private final SocieteDeGestionMapper societeDeGestionMapper;

    private final SecteurDao secteurDao;
    private final PaysDao paysDao;
    private final PersonneDao personneDao;
    private final FormeJuridiqueDao formeJuridiqueDao;
    private final CommuneDao communeDao;

    public SocieteDeGestionServiceImpl(SocieteDeGestionDao societeDeGestionDao, SocieteDeGestionMapper societeDeGestionMapper, SecteurDao secteurDao, PaysDao paysDao, PersonneDao personneDao, FormeJuridiqueDao formeJuridiqueDao, CommuneDao communeDao) {
        this.societeDeGestionDao = societeDeGestionDao;
        this.societeDeGestionMapper = societeDeGestionMapper;
        this.secteurDao = secteurDao;
        this.paysDao = paysDao;
        this.personneDao = personneDao;
        this.formeJuridiqueDao = formeJuridiqueDao;
        this.communeDao = communeDao;
    }

    @Override
    public DataTablesResponse<SocieteDeGestionDto> afficherTous(DatatableParameters parameters) {
        Page<SocieteDeGestion> societeDeGestionPage;
        Sort sort=Sort.by(Sort.Direction.ASC,"denomination");
        Pageable pageable= PageRequest.of(parameters.getStart()/parameters.getLength(), parameters.getLength(),sort);
        if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
        {
            societeDeGestionPage = societeDeGestionDao.findByDenominationContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
        }
        else {
            societeDeGestionPage = societeDeGestionDao.findAll(pageable);
        }
        List<SocieteDeGestionDto> content = societeDeGestionPage.getContent().stream().map(societeDeGestionMapper::deSocieteDeGestion).collect(Collectors.toList());
        DataTablesResponse<SocieteDeGestionDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) societeDeGestionPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) societeDeGestionPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }

    @Override
    public Page<SocieteDeGestionDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public List<SocieteDeGestionDto> afficherTous() {
        return null;
    }

    @Override
    public SocieteDeGestion afficherSelonId(long id) {
        return societeDeGestionDao.findById(id);
    }

    @Override
    public SocieteDeGestionDto afficher(long id) {
        return societeDeGestionMapper.deSocieteDeGestion(afficherSelonId(id));
    }

    @Override
    public SocieteDeGestionDto creer(SocieteDeGestionDto societeDeGestionDto) {
        SocieteDeGestion societeDeGestion=societeDeGestionMapper.deSocieteDeGestionDto(societeDeGestionDto);
        societeDeGestion.setDenomination(societeDeGestionDto.getRaisonSociale() + " [" + societeDeGestionDto.getSigle() + "]");

        if (societeDeGestionDto.getCommune() != null && societeDeGestionDto.getCommune().getIdCommune() != null) {
            Commune commune =communeDao.findById(societeDeGestionDto.getCommune().getIdCommune())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Commune.class, societeDeGestionDto.getCommune().getIdCommune().toString()));
            societeDeGestion.setCommune(commune);
        }
        if (societeDeGestionDto.getPaysResidence() != null && societeDeGestionDto.getPaysResidence().getIdPays() != null) {
            Pays paysResidence = paysDao.findById(societeDeGestionDto.getPaysResidence().getIdPays())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, societeDeGestionDto.getPaysResidence().getIdPays().toString()));
            societeDeGestion.setPaysResidence(paysResidence);
        }
        /*if(societeDeGestionDto.getFormeJuridique() != null && societeDeGestionDto.getFormeJuridique().getCodeFormeJuridique() != null)
        {
            FormeJuridique formeJuridique = formeJuridiqueDao.findById(societeDeGestionDto.getFormeJuridique().getCodeFormeJuridique())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, societeDeGestionDto.getFormeJuridique().getCodeFormeJuridique()));
            societeDeGestion.setFormeJuridique(formeJuridique);
        }*/

        if (societeDeGestionDto.getSecteur() != null && societeDeGestionDto.getSecteur().getIdSecteur() != null) {
            Secteur secteur = secteurDao.findById(societeDeGestionDto.getSecteur().getIdSecteur())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, societeDeGestionDto.getSecteur().getIdSecteur().toString()));
            societeDeGestion.setSecteur(secteur);
        }

        return societeDeGestionMapper.deSocieteDeGestion(societeDeGestionDao.save(societeDeGestion));
    }

    @Override
    public SocieteDeGestionDto modifier(SocieteDeGestionDto societeDeGestionDto) {
        SocieteDeGestion societeDeGestion=societeDeGestionMapper.deSocieteDeGestionDto(societeDeGestionDto);
        societeDeGestion.setDenomination(societeDeGestionDto.getRaisonSociale() + " [" + societeDeGestionDto.getSigle() + "]");

        if (societeDeGestionDto.getCommune() != null && societeDeGestionDto.getCommune().getIdCommune() != null) {
            Commune commune =communeDao.findById(societeDeGestionDto.getCommune().getIdCommune())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Commune.class, societeDeGestionDto.getCommune().getIdCommune().toString()));
            societeDeGestion.setCommune(commune);
        }
        if (societeDeGestionDto.getPaysResidence() != null && societeDeGestionDto.getPaysResidence().getIdPays() != null) {
            Pays paysResidence = paysDao.findById(societeDeGestionDto.getPaysResidence().getIdPays())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, societeDeGestionDto.getPaysResidence().getIdPays().toString()));
            societeDeGestion.setPaysResidence(paysResidence);
        }
        /*if(societeDeGestionDto.getFormeJuridique() != null && societeDeGestionDto.getFormeJuridique().getCodeFormeJuridique() != null)
        {
            FormeJuridique formeJuridique = formeJuridiqueDao.findById(societeDeGestionDto.getFormeJuridique().getCodeFormeJuridique())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, societeDeGestionDto.getFormeJuridique().getCodeFormeJuridique()));
            societeDeGestion.setFormeJuridique(formeJuridique);
        }*/

        if (societeDeGestionDto.getSecteur() != null && societeDeGestionDto.getSecteur().getIdSecteur() != null) {
            Secteur secteur = secteurDao.findById(societeDeGestionDto.getSecteur().getIdSecteur())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, societeDeGestionDto.getSecteur().getIdSecteur().toString()));
            societeDeGestion.setSecteur(secteur);
        }

        return societeDeGestionMapper.deSocieteDeGestion(societeDeGestionDao.save(societeDeGestion));
    }

    @Override
    public void supprimer(long id) {
        societeDeGestionDao.deleteById(id);
    }
}
