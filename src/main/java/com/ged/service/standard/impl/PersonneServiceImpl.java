package com.ged.service.standard.impl;

import com.ged.dao.standard.PaysDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.standard.SecteurDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.mapper.standard.DocumentMapper;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.projection.PersonneProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.PersonneService;
import com.ged.dto.standard.DocumentDto;
import com.ged.dto.standard.PersonneDto;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.Personne;
import com.ged.entity.standard.Secteur;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonneServiceImpl implements PersonneService {
    private final PersonneDao personneDao;
    private final PersonneMapper personneMapper;
    private final PaysDao paysDao;
    private final DocumentMapper documentMapper;
    private final SecteurDao secteurDao;

    public PersonneServiceImpl(PersonneDao personneDao, PersonneMapper personneMapper, PaysDao paysDao, DocumentMapper documentMapper, SecteurDao secteurDao) {
        this.personneDao = personneDao;
        this.personneMapper = personneMapper;
        this.paysDao = paysDao;
        this.documentMapper = documentMapper;
        this.secteurDao = secteurDao;
    }

    @Override
    public Boolean existsByNumeroCpteDeposit(String numero) {
        return personneDao.existsByNumeroCpteDepositIgnoreCase(numero);
    }

    @Override
    public Page<PersonneDto> afficherPersonnes(int page, int size) {
        return null;
    }

    @Override
    public DataTablesResponse<PersonneDto> afficherCompteGele(DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());
        Page<Personne> personnePage = personneDao.afficherCompteGele(pageable);
        List<PersonneDto> content = personnePage.getContent().stream().map(personneMapper::dePersonne).collect(Collectors.toList());
        DataTablesResponse<PersonneDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)personnePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)personnePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public DataTablesResponse<PersonneDto> afficherCompteNonGele(DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());
        Page<Personne> personnePage = personneDao.afficherCompteNonGele(pageable);
        List<PersonneDto> content = personnePage.getContent().stream().map(personneMapper::dePersonne).collect(Collectors.toList());
        DataTablesResponse<PersonneDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)personnePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)personnePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public List<PersonneProjection> afficherPersonnePhysiqueMorales() {

        return personneDao.afficherPersonnePhysiqueMorale();
    }

    @Override
    public List<PersonneProjection> afficherPersonnePhysiqueMoralesListe() {
        List<PersonneProjection> personneProjections=personneDao.afficherPersonnePhysiqueMoraleListe();
        return personneProjections;
    }

    @Override
    public List<PersonneProjection> afficherPersonneNotInOpcvm(Long idOpcvm) {
        return personneDao.afficherPersonne(idOpcvm);
    }

    @Override
    public List<PersonneProjection> afficherPersonneInOpcvm(Long idOpcvm) {
        return personneDao.afficherPersonneInOpcvm(idOpcvm);
    }

    @Override
    public List<PersonneDto> afficherSelonQualite(String qualite) {
        return personneDao.afficherTousSelonQualite(qualite).stream().map(personneMapper::dePersonne).collect(Collectors.toList());
    }

    @Override
    public List<PersonneDto> afficherSelonQualite() {
        return personneDao.afficherTousSelonQualite().stream().map(personneMapper::dePersonne).collect(Collectors.toList());
    }

    @Override
    public List<PersonneDto> recherherNumeroCompteDepositaire(String numero) {
        return personneDao.findByNumeroCpteDeposit(numero).stream().map(personneMapper::dePersonne).collect(Collectors.toList());
    }

    @Override
    public boolean verifierNumeroCompteDepositaire(String numero) {
        return personneDao.existsByNumeroCpteDeposit(numero);
    }

    @Override
    public DataTablesResponse<PersonneDto> afficherSelonQualite(DatatableParameters parameters) {
        Page<Personne> personnePage;
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());
        if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
            if (parameters.getSearch().getValue().equalsIgnoreCase("oui")) {
                personnePage = personneDao.compteEstGele(true,pageable);
            } else if (parameters.getSearch().getValue().equalsIgnoreCase("non")) {
                personnePage = personneDao.compteEstGele(false,pageable);
            }
            else
                personnePage = personneDao.rechercherGele(parameters.getSearch().getValue(),pageable);
        }else
            personnePage = personneDao.afficherTousSelonQualite(pageable);

        List<PersonneDto> content = personnePage.getContent().stream().map(personneMapper::dePersonne).collect(Collectors.toList());
        DataTablesResponse<PersonneDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)personnePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)personnePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public List<PersonneDto> afficherPersonneTous() {
        Sort sort=Sort.by(Sort.Direction.ASC,"denomination");
        return personneDao.findAll(sort).stream().map(personneMapper::dePersonne).collect(Collectors.toList());
    }

    @Override
    public List<PersonneDto> afficherCompteGeleNonGele() {
        return personneDao.afficherTousSelonQualiteListe().stream().map(personneMapper::dePersonne).collect(Collectors.toList());
    }

    @Override
    public List<PersonneDto> afficherCompteGeleNonGele(boolean estGele) {
        return personneDao.compteEstGeleListe(estGele).stream().map(personneMapper::dePersonne).collect(Collectors.toList());
    }

    public PersonneProjection afficherPersonnePhysiqueMoraleSelonId(long id) {
        return personneDao.afficherPersonnePhysiqueMoraleSelonId(id);
    }

    @Override
    public Personne afficherPersonneSelonId(long idPersonne) {
        return personneDao.findById(idPersonne).orElse(null);
    }

    @Override
    public PersonneDto creerPersonne(PersonneDto personneDto) {
        return null;
    }

    @Override
    public PersonneDto modifierPersonne(PersonneDto personneDto) {
        Personne personne = personneMapper.dePersonneDto(personneDto);
//        Personne personneSelonId=new Personne();
//        System.out.println(personneDto.getIdPersonne());
//        if(personneDto.getIdPersonne()!=null)
//            personneSelonId=afficherPersonneSelonId(personneDto.getIdPersonne());

        personne.setEstGele(personneDto.isEstGele());
        personne.setDenomination(personneDto.getDenomination());

//        if (personneSelonId.getMobile1() != null && personneDto.getMobile1() == null) {
            personne.setMobile1(personneDto.getMobile1());
//        }

            personne.setMobile2(personneDto.getMobile2());

//        if (personneSelonId.getFixe1() != null && personneDto.getFixe1() == null) {
            personne.setFixe1(personneDto.getFixe1());
//        }
//        if (personneSelonId.getFixe2() != null && personneDto.getFixe2() == null) {
            personne.setFixe2(personneDto.getFixe2());
//        }
//        if (personneSelonId.getEmailPerso() != null && personneDto.getEmailPerso() == null) {
            personne.setEmailPerso(personneDto.getEmailPerso());
//        }
//        if (personneSelonId.getEmailPro() != null && personneDto.getEmailPro() == null) {
            personne.setEmailPro(personneDto.getEmailPro());
//        }
//        if (personneSelonId.getNomContact() != null && personneDto.getNomContact() == null) {
            personne.setNomContact(personneDto.getNomContact());
//        }
//        if (personneSelonId.getEmailContact() != null && personneDto.getEmailContact() == null) {
            personne.setEmailContact(personneDto.getEmailContact());
//        }
//        if (personneSelonId.getPrenomContact() != null && personneDto.getPrenomContact() == null) {
            personne.setPrenomContact(personneDto.getPrenomContact());
//        }
//        if (personneSelonId.getTelContact() != null && personneDto.getTelContact() == null) {
            personne.setTelContact(personneDto.getTelContact());
//        }
//        if (personneDto.getPaysResidence() != null) {
            Pays paysResidence = paysDao.findById(personneDto.getPaysResidence().getIdPays())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personneDto.getPaysResidence().getIdPays().toString()));
            personne.setPaysResidence(paysResidence);
//        }
//
       if (personneDto.getSecteur() != null) {
           //System.out.println(personneDto.getSecteur().getIdSecteur());
           Secteur secteur = secteurDao.findById(personneDto.getSecteur().getIdSecteur())
                   .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personneDto.getSecteur().getIdSecteur().toString()));
           personne.setSecteur(secteur);
       }
        if (personneDto.getDistributeur() != null) {
            Personne distributeur = personneDao.findById(personneDto.getDistributeur().getIdPersonne())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Personne.class, personneDto.getDistributeur().getIdPersonne().toString()));
            personne.setDistributeur(distributeur);
        }

//        if (personneSelonId.getBp() != null && personneDto.getBp() == null) {
            personne.setBp(personneDto.getBp());
//        }
//        if (personneSelonId.getIfu() != null && personneDto.getIfu() == null) {
            personne.setIfu(personneDto.getIfu());
//        }
//        if (personneSelonId.getDomicile() != null && personneDto.getDomicile() == null) {
            personne.setDomicile(personneDto.getDomicile());
//        }
//        if (personneSelonId.getTitreContact() != null && personneDto.getTitreContact() == null) {
            personne.setTitreContact(personneDto.getTitreContact());
//        }
//        if (personneSelonId.getModeEtablissement() != null && personneDto.getModeEtablissement() == null) {
            personne.setModeEtablissement(personneDto.getModeEtablissement());
//        }

        Set<DocumentDto> documents = personneDto.getDocuments();
        for (DocumentDto doc : documents) {
            personne.ajouterDocument(documentMapper.deDocumentDto(doc));
        }

        return personneMapper.dePersonne(personneDao.save(personne));
    }

    @Override
    public ResponseEntity<?> searchBySigleIgnoreCase(String sigle) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Personne personne = personneDao.searchBySigleIgnoreCase(sigle).orElse(null);
            return ResponseHandler.generateResponse(
                    "Recherche de personne dont Sigle = " + sigle,
                    HttpStatus.OK,
                    personne);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public void supprimerPersonne(long idPersonne) {

    }
}
