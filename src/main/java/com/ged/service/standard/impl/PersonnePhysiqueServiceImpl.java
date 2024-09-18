package com.ged.service.standard.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dao.standard.*;
import com.ged.dao.standard.revuecompte.CategorieClientDao;
import com.ged.dao.standard.revuecompte.SousTypeClientDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.entity.standard.revuecompte.CategorieClient;
import com.ged.entity.standard.revuecompte.SousTypeClient;
import com.ged.mapper.standard.DocumentMapper;
import com.ged.mapper.standard.PersonnePhysiqueMapper;
import com.ged.dao.crm.DegreDao;
import com.ged.dto.standard.*;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.*;
import com.ged.projection.NumOrdreProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
//@Transactional(isolation = Isolation.REPEATABLE_READ)
public class PersonnePhysiqueServiceImpl implements PersonnePhysiqueService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonneMoraleServiceImpl.class);

    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/

    @Value("${media.upload.dir}")
    public String PATH;
    private final PersonnePhysiqueDao personnePhysiqueDao;
    private final PersonnePhysiqueMapper personnePhysiqueMapper;
    //    private final PersonnePhysiquePaysMapper personnePhysiquePaysMapper;
    private final PersonnePhysiquePaysService personnePhysiquePaysService;
    private final ProfessionDao professionDao;
    private final SecteurDao secteurDao;
    private final StatutPersonneDao statutPersonneDao;
    private final PaysDao paysDao;
    private final CategorieClientDao categorieClientDao;
    private final SousTypeClientDao sousTypeClientDao;
    private final DegreDao degreDao;
    private final LangueDao langueDao;
    private final ModeEtablissementDao modeEtablissementDao;
    private final PersonneDao personneDao;
    private final DocumentMapper documentMapper;
    private final EmailServiceImpl emailService;
    private final MailService mailService;
    private final EnvoiMailService envoiMailService;
    private final FileService fileService;
    private final QualiteDao qualiteDao;

    public PersonnePhysiqueServiceImpl(PersonnePhysiqueDao personnePhysiqueDao, PersonnePhysiqueMapper personnePhysiqueMapper, PersonnePhysiquePaysService personnePhysiquePaysService, ProfessionDao professionDao, SecteurDao secteurDao, StatutPersonneDao statutPersonneDao, PaysDao paysDao, CategorieClientDao categorieClientDao, SousTypeClientDao sousTypeClientDao, DegreDao degreDao, LangueDao langueDao, ModeEtablissementDao modeEtablissementDao, PersonneDao personneDao, DocumentMapper documentMapper, EmailServiceImpl emailService, MailService mailService, EnvoiMailService envoiMailService, FileService fileService, QualiteDao qualiteDao) {
        this.personnePhysiqueDao = personnePhysiqueDao;
        this.personnePhysiqueMapper = personnePhysiqueMapper;
        this.personnePhysiquePaysService = personnePhysiquePaysService;
        this.professionDao = professionDao;
        this.secteurDao = secteurDao;
        this.statutPersonneDao = statutPersonneDao;
        this.paysDao = paysDao;
        this.categorieClientDao = categorieClientDao;
        this.sousTypeClientDao = sousTypeClientDao;
        this.degreDao = degreDao;
        this.langueDao = langueDao;
        this.modeEtablissementDao = modeEtablissementDao;
        this.personneDao = personneDao;
        this.documentMapper = documentMapper;
        this.emailService = emailService;
        this.mailService = mailService;
        this.envoiMailService = envoiMailService;
        this.fileService = fileService;
        this.qualiteDao = qualiteDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            return ResponseHandler.generateResponse(
                    "Liste de tous les ph",
                    HttpStatus.OK,
                    personnePhysiqueDao.findAll().stream().map(personnePhysiqueMapper::dePersonnePhysique).collect(Collectors.toList()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Page<PersonnePhysique> personnePhysiquePage;

            Pageable pageable = PageRequest.of(parameters.getStart() / parameters.getLength(), parameters.getLength());
            personnePhysiquePage = personnePhysiqueDao.findAll(pageable);
            List<PersonnePhysiqueDto> content = personnePhysiquePage.getContent().stream().map(personnePhysiqueMapper::dePersonnePhysique).collect(Collectors.toList());
            DataTablesResponse<PersonnePhysiqueDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int) personnePhysiquePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int) personnePhysiquePage.getTotalElements());
            dataTablesResponse.setData(content);

            return ResponseHandler.generateResponse(
                    "Liste de tous les ph",
                    HttpStatus.OK,
                    dataTablesResponse);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public DataTablesResponse<PersonnePhysiqueDto> afficherTous(String qualite, DatatableParameters parameters) {
        Page<PersonnePhysique> personnePhysiquePage;

        Pageable pageable = PageRequest.of(
                parameters.getStart() / parameters.getLength(), parameters.getLength());
        if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
            personnePhysiquePage = personnePhysiqueDao.rechercherSelonQualite(
                    qualite,
                    parameters.getSearch().getValue(),
                    pageable);
        } else {
            personnePhysiquePage = personnePhysiqueDao.afficherTousSelonQualite(
                    qualite,
                    pageable);
        }
        List<PersonnePhysiqueDto> content = personnePhysiquePage.getContent().stream().map(personnePhysiqueMapper::dePersonnePhysique).collect(Collectors.toList());

        DataTablesResponse<PersonnePhysiqueDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int) personnePhysiquePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int) personnePhysiquePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public DataTablesResponse<PersonnePhysiqueDto> afficherPersonneSanctionnee(DatatableParameters parameters) {
        Page<PersonnePhysique> personnePhysiquePage;

        Pageable pageable = PageRequest.of(
                parameters.getStart() / parameters.getLength(), parameters.getLength());
        /*if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
        {
//            System.out.println(qualite);
            personnePhysiquePage = personnePhysiqueDao.rechercherSelonQualite(qualite,parameters.getSearch().getValue(), pageable);
        }
        else {*/
        personnePhysiquePage = personnePhysiqueDao.afficherPersonneSanctionnee(pageable);
        //}
        List<PersonnePhysiqueDto> content = personnePhysiquePage.getContent().stream().map(personnePhysiqueMapper::dePersonnePhysique).collect(Collectors.toList());
        DataTablesResponse<PersonnePhysiqueDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int) personnePhysiquePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int) personnePhysiquePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public DataTablesResponse<PersonnePhysiqueDto> afficherPersonnePhysiquePolitiquementExpose(String qualite, DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart() / parameters.getLength(), parameters.getLength());
        Page<PersonnePhysique> personnePhysiquePage = personnePhysiqueDao.afficherPersonnePhysiquePolitiquementExpose(qualite, pageable);
        List<PersonnePhysiqueDto> content = personnePhysiquePage.getContent().stream().map(personnePhysiqueMapper::dePersonnePhysique).collect(Collectors.toList());
        DataTablesResponse<PersonnePhysiqueDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int) personnePhysiquePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int) personnePhysiquePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public DataTablesResponse<PersonnePhysiqueDto> afficherPersonnePhysiqueJuge(String qualite, DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart() / parameters.getLength(), parameters.getLength());
        Page<PersonnePhysique> personnePhysiquePage = personnePhysiqueDao.afficherPersonnePhysiqueJuge(qualite, pageable);
        List<PersonnePhysiqueDto> content = personnePhysiquePage.getContent().stream().map(personnePhysiqueMapper::dePersonnePhysique).collect(Collectors.toList());
        DataTablesResponse<PersonnePhysiqueDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int) personnePhysiquePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int) personnePhysiquePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public Page<PersonnePhysiqueDto> afficherPersonnePhysiques(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<PersonnePhysique> personnePhysiquePage = personnePhysiqueDao.findAll(pageRequest);
        return new PageImpl<>(personnePhysiquePage.getContent().stream().map(personnePhysiqueMapper::dePersonnePhysique).collect(Collectors.toList()), pageRequest, personnePhysiquePage.getTotalElements());
    }

    @Override
    public PersonnePhysiqueDto afficherPersonnePhysique(Long id) {
        return personnePhysiqueMapper.dePersonnePhysique(afficherPersonnePhysiqueSelonId(id));
    }

    @Override
    public PersonnePhysiqueDto afficherSelonIdQualite(Long id, String qualite) {
        return personnePhysiqueMapper.dePersonnePhysique(personnePhysiqueDao.afficherSelonIdQualite(id, qualite));
    }

    @Override
    public Long afficherMaxNumordre() {
        return personnePhysiqueDao.maxNumordre().getNumOrdre();
    }

    @Override
    public List<PersonnePhysiqueDto> afficherSelonQualite(String qualite) {
        return personnePhysiqueDao.afficherPersonnePhysiqueSelonQualite(qualite).stream().map(personnePhysiqueMapper::dePersonnePhysique).collect(Collectors.toList());
    }

    @Override
    public List<PersonnePhysiqueDto> afficherPersonnePhysiqueNayantPasInvesti(String qualite, LocalDateTime dateDebut, LocalDateTime dateFin) {
        return personnePhysiqueDao.afficherPersonnePhysiqueNayantPasInsvesti(qualite, dateDebut, dateFin).stream().map(personnePhysiqueMapper::dePersonnePhysique).collect(Collectors.toList());
    }

    @Override
    public PersonnePhysique afficherPersonnePhysiqueSelonId(long idPersonne) {
        return personnePhysiqueDao.findById(idPersonne).orElseThrow(() -> new EntityNotFoundException("Personne physique avec ID " + idPersonne + " est introuvable"));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PersonnePhysiqueDto creerPersonnePhysique(List<MultipartFile> files, PersonnePhysiqueDto personnePhysiqueDto) {
        PersonnePhysiqueDto personnePhysiqueDtoSaved = null;
        try {
            PersonnePhysique personnePhysique = personnePhysiqueMapper.dePersonnePhysiqueDto(personnePhysiqueDto);
            personnePhysique.setDenomination(personnePhysiqueDto.getNom() + " " + personnePhysiqueDto.getPrenom());
            if (personnePhysiqueDto.getProfession() != null && personnePhysiqueDto.getProfession().getIdProf() != null) {
                Profession profession = professionDao.findById(personnePhysiqueDto.getProfession().getIdProf())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Profession.class, personnePhysiqueDto.getProfession().getIdProf().toString()));
                personnePhysique.setProfession(profession);
            }
            if (personnePhysiqueDto.getModeEtablissementDto() != null && personnePhysiqueDto.getModeEtablissementDto().getIdModeEtablissement() != null) {
                ModeEtablissement modeEtablissement = modeEtablissementDao.findById(personnePhysiqueDto.getModeEtablissementDto().getIdModeEtablissement())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(ModeEtablissement.class, personnePhysiqueDto.getModeEtablissementDto().getIdModeEtablissement().toString()));
                personnePhysique.setModeEtablissement2(modeEtablissement);
            }
            if (personnePhysiqueDto.getSecteurEmp() != null && personnePhysiqueDto.getSecteurEmp().getIdSecteur() != null) {
                Secteur secteurEmp = secteurDao.findById(personnePhysiqueDto.getSecteurEmp().getIdSecteur())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personnePhysiqueDto.getSecteurEmp().getIdSecteur().toString()));
                personnePhysique.setSecteurEmp(secteurEmp);
            }

            if (personnePhysiqueDto.getCategorieClient() != null && personnePhysiqueDto.getCategorieClient().getIdCategorieClient() != null) {
                CategorieClient categorieClient = categorieClientDao.findById(personnePhysiqueDto.getCategorieClient().getIdCategorieClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(CategorieClient.class, personnePhysiqueDto.getCategorieClient().getIdCategorieClient().toString()));
                personnePhysique.setCategorieClient(categorieClient);
            }

            if (personnePhysiqueDto.getSousTypeClient() != null && personnePhysiqueDto.getSousTypeClient().getIdSousTypeClient() != null) {
                SousTypeClient sousTypeClient = sousTypeClientDao.findById(personnePhysiqueDto.getSousTypeClient().getIdSousTypeClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(SousTypeClient.class, personnePhysiqueDto.getSousTypeClient().getIdSousTypeClient().toString()));
                personnePhysique.setSousTypeClient(sousTypeClient);
            }

            if (personnePhysiqueDto.getPaysPere() != null && personnePhysiqueDto.getPaysPere().getIdPays() != null) {
                Pays paysPere = paysDao.findById(personnePhysiqueDto.getPaysPere().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysPere().getIdPays().toString()));
                personnePhysique.setPaysPere(paysPere);
            }
            if (personnePhysiqueDto.getPaysMere() != null && personnePhysiqueDto.getPaysMere().getIdPays() != null) {
                Pays paysMere = paysDao.findById(personnePhysiqueDto.getPaysMere().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysMere().getIdPays().toString()));
                personnePhysique.setPaysMere(paysMere);
            }
            if (personnePhysiqueDto.getPaysConjoint() != null && personnePhysiqueDto.getPaysConjoint().getIdPays() != null) {
                Pays paysConjoint = paysDao.findById(personnePhysiqueDto.getPaysConjoint().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysConjoint().getIdPays().toString()));
                personnePhysique.setPaysConjoint(paysConjoint);
            }
            if (personnePhysiqueDto.getPaysNationalite() != null && personnePhysiqueDto.getPaysNationalite().getIdPays() != null) {
                Pays paysNationalite = paysDao.findById(personnePhysiqueDto.getPaysNationalite().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysNationalite().getIdPays().toString()));
                personnePhysique.setPaysNationalite(paysNationalite);
            }
            if (personnePhysiqueDto.getPaysResidence() != null && personnePhysiqueDto.getPaysResidence().getIdPays() != null) {
                Pays paysResidence = paysDao.findById(personnePhysiqueDto.getPaysResidence().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysResidence().getIdPays().toString()));
                personnePhysique.setPaysResidence(paysResidence);
            }
            if (personnePhysiqueDto.getDegre() != null && personnePhysiqueDto.getDegre().getIdDegre() != null) {
                Degre degre = degreDao.findById(personnePhysiqueDto.getDegre().getIdDegre())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Degre.class, personnePhysiqueDto.getDegre().getIdDegre().toString()));
                personnePhysique.setDegre(degre);
            }

            if (personnePhysiqueDto.getSecteur() != null && personnePhysiqueDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(personnePhysiqueDto.getSecteur().getIdSecteur())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personnePhysiqueDto.getSecteur().getIdSecteur().toString()));
                personnePhysique.setSecteur(secteur);
            }
            if (personnePhysiqueDto.getDistributeur() != null && personnePhysiqueDto.getDistributeur().getIdPersonne() != null) {
                Personne distributeur = personneDao.findById(personnePhysiqueDto.getDistributeur().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Personne.class, personnePhysiqueDto.getDistributeur().getIdPersonne().toString()));
                personnePhysique.setDistributeur(distributeur);
            }
            if (personnePhysiqueDto.getLangue() != null && personnePhysiqueDto.getLangue().getIdLangue() != null) {
                Langue langue = langueDao.findById(personnePhysiqueDto.getLangue().getIdLangue())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Langue.class, personnePhysiqueDto.getLangue().getIdLangue().toString()));
                personnePhysique.setLangue(langue);
            }


            Set<DocumentDto> documents = personnePhysiqueDto.getDocuments();
            for (DocumentDto doc : documents) {
                personnePhysique.ajouterDocument(documentMapper.deDocumentDto(doc));
            }

            PersonnePhysiqueDto personnePhysiqueSave = personnePhysiqueMapper.dePersonnePhysique(personnePhysiqueDao.save(personnePhysique));
            personnePhysiqueDtoSaved = personnePhysiqueSave;
            if (personnePhysiqueSave.getIdPersonne() != null) {
                Set<PersonnePhysiquePaysDto> personnePhysiquePaysDtos = personnePhysiqueDto.getPersonnePhysiquePaysDtos();
                for (PersonnePhysiquePaysDto o : personnePhysiqueDto.getPersonnePhysiquePaysDtos()) {
                    o.setPersonnePhysiqueDto(personnePhysiqueSave);
                    personnePhysiquePaysService.creerPersonnePhysiquePays(o);
//                    personnePhysique.ajouterPersonnePhysiquePays(personnePhysiquePaysMapper.dePersonnePhysiquePaysDto(o));
                }
            }
            if (files != null) {
                Set<DocumentDto> docs = new HashSet<>(personnePhysiqueSave.getDocuments());
                Set<DocumentDto> newDocs = fileService.uploadMedia(PATH, files, docs);
                personnePhysiqueSave.setDocuments(newDocs);
            }
            if (personnePhysiqueSave.getIdPersonne() != null) {
                if (
                        StringUtils.hasLength(personnePhysiqueSave.getEmailPro()) ||
                                StringUtils.hasLength(personnePhysiqueSave.getEmailPerso())
                ) {
                    emailService.sendMail(
                            personnePhysiqueSave.getEmailPerso(),
                            "Remerciement",
                            "SAPHIR, vous remercie pour votre disponibilité !");
                    MailDto mailDto = new MailDto();
                    mailDto.setDateEnvoi(new Date());
                    mailDto.setHeureEnvoi(null);
                    mailDto.setObjet("Remerciement");
                    mailDto.setMsg("SAPHIR, vous remercie pour votre disponibilité !");
                    mailDto = mailService.creerMail(mailDto);
                    if (mailDto.getIdMail() != null) {
                        EnvoiMailDto envoiMailDto = new EnvoiMailDto();
                        envoiMailDto.setMailDto(mailDto);
                        envoiMailDto.setPersonneDto(personnePhysiqueDtoSaved);
                        envoiMailDto = envoiMailService.creerEnvoiMail(envoiMailDto);
                    }
                }
            }
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return personnePhysiqueDtoSaved;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PersonnePhysiqueDto modifierPersonnePhysique(List<MultipartFile> files, PersonnePhysiqueDto personnePhysiqueDto) throws Throwable {
        PersonnePhysique personnePhysique = personnePhysiqueMapper.dePersonnePhysiqueDto(personnePhysiqueDto);
        PersonnePhysique personnePhysiqueSelonId = new PersonnePhysique();
        if (personnePhysiqueDto.getIdPersonne() != null) {
            personnePhysiqueSelonId = afficherPersonnePhysiqueSelonId(personnePhysiqueDto.getIdPersonne());
            personnePhysique.setStatutPersonnes(personnePhysiqueSelonId.getStatutPersonnes());
        }

        personnePhysique.setDenomination(personnePhysiqueDto.getNom() + " " + personnePhysiqueDto.getPrenom());
        if (personnePhysiqueDto.isEstExpose() || personnePhysiqueDto.isEstJuge()) {
            if (personnePhysiqueSelonId != null) {
                if (personnePhysiqueSelonId.getMobile1() != null && personnePhysiqueDto.getMobile1() == null) {
                    personnePhysique.setMobile1(personnePhysiqueSelonId.getMobile1());
                }
                if (personnePhysiqueSelonId.getNumeroCpteDeposit() != null && personnePhysiqueDto.getNumeroCpteDeposit() == null) {
                    personnePhysique.setNumeroCpteDeposit(personnePhysiqueSelonId.getNumeroCpteDeposit());
                }
                if (personnePhysiqueSelonId.getMobile2() != null && personnePhysiqueDto.getMobile2() == null) {
                    personnePhysique.setMobile2(personnePhysiqueSelonId.getMobile2());
                }
                if (personnePhysiqueSelonId.getFixe1() != null && personnePhysiqueDto.getFixe1() == null) {
                    personnePhysique.setFixe1(personnePhysiqueSelonId.getFixe1());
                }
                if (personnePhysiqueSelonId.getFixe2() != null && personnePhysiqueDto.getFixe2() == null) {
                    personnePhysique.setFixe2(personnePhysiqueSelonId.getFixe2());
                }
                if (personnePhysiqueSelonId.getNbrPersonneACharge() != 0 && personnePhysiqueDto.getNbrPersonneACharge() == 0) {
                    personnePhysique.setNbrPersonneACharge(personnePhysiqueSelonId.getNbrPersonneACharge());
                }
                if (personnePhysiqueSelonId.getNbrEnfant() != 0 && personnePhysiqueDto.getNbrEnfant() == 0) {
                    personnePhysique.setNbrEnfant(personnePhysiqueSelonId.getNbrEnfant());
                }
                if (personnePhysiqueSelonId.getOrigineFonds() != null && personnePhysiqueDto.getOrigineFonds() == null) {
                    personnePhysique.setOrigineFonds(personnePhysiqueSelonId.getOrigineFonds());
                }
                if (personnePhysiqueSelonId.getSalaire() != 0 && personnePhysiqueDto.getSalaire() == 0) {
                    personnePhysique.setSalaire(personnePhysiqueSelonId.getSalaire());
                }
                if (personnePhysiqueSelonId.getAdresseGeoEmp() != null && personnePhysiqueDto.getAdresseGeoEmp() == null) {
                    personnePhysique.setAdresseGeoEmp(personnePhysiqueSelonId.getAdresseGeoEmp());
                }
                if (personnePhysiqueSelonId.getAdressePostaleEmp() != null && personnePhysiqueDto.getAdressePostaleEmp() == null) {
                    personnePhysique.setAdressePostaleEmp(personnePhysiqueSelonId.getAdressePostaleEmp());
                }
                if (personnePhysiqueSelonId.getAutresBiens() != null && personnePhysiqueDto.getAutresBiens() == null) {
                    personnePhysique.setAutresBiens(personnePhysiqueSelonId.getAutresBiens());
                }
                if (personnePhysiqueSelonId.getAutresRevenus() != 0 && personnePhysiqueDto.getAutresRevenus() == 0) {
                    personnePhysique.setAutresRevenus(personnePhysiqueSelonId.getAutresRevenus());
                }
                if (personnePhysiqueSelonId.getTeint() != null && personnePhysiqueDto.getTeint() == null) {
                    personnePhysique.setTeint(personnePhysiqueSelonId.getTeint());
                }
                if (personnePhysiqueSelonId.getDateNaissanceConjoint() != null && personnePhysiqueDto.getDateNaissanceConjoint() == null) {
                    personnePhysique.setDateNaissanceConjoint(personnePhysiqueSelonId.getDateNaissanceConjoint());
                }
                if (personnePhysiqueSelonId.getNomConjoint() != null && personnePhysiqueDto.getNomConjoint() == null) {
                    personnePhysique.setNomConjoint(personnePhysiqueSelonId.getNomConjoint());
                }
                if (personnePhysiqueSelonId.getPrenomConjoint() != null && personnePhysiqueDto.getPrenomConjoint() == null) {
                    personnePhysique.setPrenomConjoint(personnePhysiqueSelonId.getPrenomConjoint());
                }

                if (personnePhysiqueSelonId.getCategorieClient() != null && personnePhysiqueDto.getCategorieClient() == null) {
                     CategorieClient categorieClient = categorieClientDao.findById(personnePhysiqueSelonId.getCategorieClient().getIdCategorieClient())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(CategorieClient.class, personnePhysiqueDto.getCategorieClient().getIdCategorieClient().toString()));
                    personnePhysique.setCategorieClient(categorieClient);
                } else if (personnePhysiqueDto.getCategorieClient() != null) {
                    CategorieClient categorieClient  = categorieClientDao.findById(personnePhysiqueDto.getCategorieClient().getIdCategorieClient())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(CategorieClient.class, personnePhysiqueDto.getCategorieClient().getIdCategorieClient().toString()));
                    personnePhysique.setCategorieClient(categorieClient);
                }

                if (personnePhysiqueSelonId.getSousTypeClient() != null && personnePhysiqueDto.getSousTypeClient() == null) {
                     SousTypeClient sousTypeClient = sousTypeClientDao.findById(personnePhysiqueSelonId.getSousTypeClient().getIdSousTypeClient())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(SousTypeClient.class, personnePhysiqueDto.getSousTypeClient().getIdSousTypeClient().toString()));
                    personnePhysique.setSousTypeClient(sousTypeClient);
                } else if (personnePhysiqueDto.getSousTypeClient() != null) {
                    SousTypeClient sousTypeClient = sousTypeClientDao.findById(personnePhysiqueDto.getSousTypeClient().getIdSousTypeClient())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(SousTypeClient.class, personnePhysiqueDto.getSousTypeClient().getIdSousTypeClient().toString()));
                    personnePhysique.setSousTypeClient(sousTypeClient);
                }

                if (personnePhysiqueSelonId.getPaysConjoint() != null && personnePhysiqueDto.getPaysConjoint() == null) {
                    Pays paysConjoint = paysDao.findById(personnePhysiqueSelonId.getPaysConjoint().getIdPays())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysConjoint().getIdPays().toString()));
                    personnePhysique.setPaysConjoint(paysConjoint);
                } else if (personnePhysiqueDto.getPaysConjoint() != null) {
                    Pays paysConjoint = paysDao.findById(personnePhysiqueDto.getPaysConjoint().getIdPays())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysConjoint().getIdPays().toString()));
                    personnePhysique.setPaysConjoint(paysConjoint);
                }
                //pere
                if (personnePhysiqueSelonId.getDateNaissancePere() != null && personnePhysiqueDto.getDateNaissancePere() == null) {
                    personnePhysique.setDateNaissancePere(personnePhysiqueSelonId.getDateNaissancePere());
                }
                if (personnePhysiqueSelonId.getNomPere() != null && personnePhysiqueDto.getNomPere() == null) {
                    personnePhysique.setNomPere(personnePhysiqueSelonId.getNomPere());
                }
                if (personnePhysiqueSelonId.getPrenomsPere() != null && personnePhysiqueDto.getPrenomsPere() == null) {
                    personnePhysique.setPrenomsPere(personnePhysiqueSelonId.getPrenomsPere());
                }
                if (personnePhysiqueSelonId.getPaysPere() != null && personnePhysiqueDto.getPaysPere() == null) {
                    Pays paysPere = paysDao.findById(personnePhysiqueSelonId.getPaysPere().getIdPays())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysPere().getIdPays().toString()));
                    personnePhysique.setPaysPere(paysPere);
                } else if (personnePhysiqueDto.getPaysPere() != null) {
                    Pays paysPere = paysDao.findById(personnePhysiqueDto.getPaysPere().getIdPays())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysPere().getIdPays().toString()));
                    personnePhysique.setPaysPere(paysPere);
                }
                //mere
                if (personnePhysiqueSelonId.getDateNaissanceMere() != null && personnePhysiqueDto.getDateNaissanceMere() == null) {
                    personnePhysique.setDateNaissanceMere(personnePhysiqueSelonId.getDateNaissanceMere());
                }
                if (personnePhysiqueSelonId.getNomMere() != null && personnePhysiqueDto.getNomMere() == null) {
                    personnePhysique.setNomMere(personnePhysiqueSelonId.getNomMere());
                }
                if (personnePhysiqueSelonId.getPrenomsMere() != null && personnePhysiqueDto.getPrenomsMere() == null) {
                    personnePhysique.setPrenomsMere(personnePhysiqueSelonId.getPrenomsMere());
                }
                if (personnePhysiqueSelonId.getPaysMere() != null && personnePhysiqueDto.getPaysMere() == null) {
                    Pays paysMere = paysDao.findById(personnePhysiqueSelonId.getPaysMere().getIdPays())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysMere().getIdPays().toString()));
                    personnePhysique.setPaysMere(paysMere);
                } else if (personnePhysiqueDto.getPaysMere() != null) {
                    Pays paysMere = paysDao.findById(personnePhysiqueDto.getPaysMere().getIdPays())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysMere().getIdPays().toString()));
                    personnePhysique.setPaysMere(paysMere);
                }
                //emp
                if (personnePhysiqueSelonId.getEmailEmp() != null && personnePhysiqueDto.getEmailEmp() == null) {
                    personnePhysique.setEmailEmp(personnePhysiqueSelonId.getEmailEmp());
                }
                if (personnePhysiqueSelonId.getNomEmployeur() != null && personnePhysiqueDto.getNomEmployeur() == null) {
                    personnePhysique.setNomEmployeur(personnePhysiqueSelonId.getNomEmployeur());
                }
                if (personnePhysiqueSelonId.getSecteurEmp() != null && personnePhysiqueDto.getSecteurEmp() == null) {
                    Secteur secteur = secteurDao.findById(personnePhysiqueSelonId.getSecteurEmp().getIdSecteur())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personnePhysiqueDto.getSecteurEmp().getIdSecteur().toString()));
                    personnePhysique.setSecteurEmp(secteur);
                } else if (personnePhysiqueDto.getSecteurEmp() != null) {
                    Secteur secteur = secteurDao.findById(personnePhysiqueDto.getSecteurEmp().getIdSecteur())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personnePhysiqueDto.getSecteurEmp().getIdSecteur().toString()));
                    personnePhysique.setSecteurEmp(secteur);
                }
                if (personnePhysiqueSelonId.getTelEmp() != null && personnePhysiqueDto.getTelEmp() == null) {
                    personnePhysique.setTelEmp(personnePhysiqueSelonId.getTelEmp());
                }
                if (personnePhysiqueSelonId.getTransactionEnvisagee() != null && personnePhysiqueDto.getTransactionEnvisagee() == null) {
                    personnePhysique.setTransactionEnvisagee(personnePhysiqueSelonId.getTransactionEnvisagee());
                }
                if (personnePhysiqueSelonId.getEmailPerso() != null && personnePhysiqueDto.getEmailPerso() == null) {
                    personnePhysique.setEmailPerso(personnePhysiqueSelonId.getEmailPerso());
                }
                if (personnePhysiqueSelonId.getEmailPro() != null && personnePhysiqueDto.getEmailPro() == null) {
                    personnePhysique.setEmailPro(personnePhysiqueSelonId.getEmailPro());
                }
                if (personnePhysiqueSelonId.getNomContact() != null && personnePhysiqueDto.getNomContact() == null) {
                    personnePhysique.setNomContact(personnePhysiqueSelonId.getNomContact());
                }
                if (personnePhysiqueSelonId.getEmailContact() != null && personnePhysiqueDto.getEmailContact() == null) {
                    personnePhysique.setEmailContact(personnePhysiqueSelonId.getEmailContact());
                }
                if (personnePhysiqueSelonId.getPrenomContact() != null && personnePhysiqueDto.getPrenomContact() == null) {
                    personnePhysique.setPrenomContact(personnePhysiqueSelonId.getPrenomContact());
                }
                if (personnePhysiqueSelonId.getTelContact() != null && personnePhysiqueDto.getTelContact() == null) {
                    personnePhysique.setTelContact(personnePhysiqueSelonId.getTelContact());
                }
                if (personnePhysiqueSelonId.getImmobilier() != null && personnePhysiqueDto.getImmobilier() == null) {
                    personnePhysique.setImmobilier(personnePhysiqueSelonId.getImmobilier());
                }
                if (personnePhysiqueSelonId.getLieuTravail() != null && personnePhysiqueDto.getLieuTravail() == null) {
                    personnePhysique.setLieuTravail(personnePhysiqueSelonId.getLieuTravail());
                }
                if (personnePhysiqueSelonId.getPeriodicite() != null && personnePhysiqueDto.getPeriodicite() == null) {
                    personnePhysique.setPeriodicite(personnePhysiqueSelonId.getPeriodicite());
                }

                if (personnePhysiqueSelonId.getProfession() != null && personnePhysiqueDto.getProfession() == null) {
                    Profession profession = professionDao.findById(personnePhysiqueSelonId.getProfession().getIdProf())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Profession.class, personnePhysiqueDto.getProfession().getIdProf().toString()));
                    personnePhysique.setProfession(profession);
                } else if (personnePhysiqueDto.getProfession() != null) {
                    if (personnePhysiqueDto.getProfession().getIdProf() != null) {
                        Profession profession = professionDao.findById(personnePhysiqueDto.getProfession().getIdProf())
                                .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Profession.class, personnePhysiqueDto.getProfession().getIdProf().toString()));
                        personnePhysique.setProfession(profession);
                    }
                }
                if (personnePhysiqueDto.getPaysResidence() != null) {
                    Pays paysResidence = paysDao.findById(personnePhysiqueDto.getPaysResidence().getIdPays())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysResidence().getIdPays().toString()));
                    personnePhysique.setPaysResidence(paysResidence);
                }

                if (personnePhysiqueDto.getPaysNationalite() != null) {
                    Pays paysNationalite = paysDao.findById(personnePhysiqueDto.getPaysNationalite().getIdPays())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysNationalite().getIdPays().toString()));
                    personnePhysique.setPaysNationalite(paysNationalite);
//                    personnePhysique.setPaysResidence(personnePhysiqueSelonId.getPaysResidence());
                }
                if (personnePhysiqueSelonId.getDegre() != null && personnePhysiqueDto.getDegre() == null) {
                    Degre degre = degreDao.findById(personnePhysiqueSelonId.getDegre().getIdDegre())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getDegre().getIdDegre().toString()));
                    personnePhysique.setDegre(degre);
                } else if (personnePhysiqueDto.getDegre() != null) {
                    Degre degre = degreDao.findById(personnePhysiqueDto.getDegre().getIdDegre())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getDegre().getIdDegre().toString()));
                    personnePhysique.setDegre(degre);
                }
               // System.out.println("idlangue1==" + personnePhysiqueDto.getLangue().getIdLangue().toString());
                if (personnePhysiqueSelonId.getLangue() != null && personnePhysiqueDto.getLangue() == null) {
                    Langue langue = langueDao.findById(personnePhysiqueSelonId.getLangue().getIdLangue())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Langue.class, personnePhysiqueDto.getLangue().getIdLangue().toString()));
                    personnePhysique.setLangue(langue);
                } else if (personnePhysiqueDto.getLangue() != null) {
                    Langue langue = langueDao.findById(personnePhysiqueDto.getLangue().getIdLangue())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Langue.class, personnePhysiqueDto.getLangue().getIdLangue().toString()));
                    personnePhysique.setLangue(langue);
                }
                if (personnePhysiqueSelonId.getSecteur() != null && personnePhysiqueDto.getSecteur() == null) {
                    //System.out.println(personnePhysiqueSelonId.getSecteur().getIdSecteur());
                    Secteur secteur = secteurDao.findById(personnePhysiqueSelonId.getSecteur().getIdSecteur())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personnePhysiqueDto.getSecteur().getIdSecteur().toString()));
                    personnePhysique.setSecteur(secteur);
                } else if (personnePhysiqueDto.getSecteur() != null) {
                    Secteur secteur = secteurDao.findById(personnePhysiqueDto.getSecteur().getIdSecteur())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personnePhysiqueDto.getSecteur().getIdSecteur().toString()));
                    personnePhysique.setSecteur(secteur);
                }
                if (personnePhysiqueSelonId.getModeEtablissement2() != null && personnePhysiqueDto.getModeEtablissementDto() == null) {
                    ModeEtablissement modeEtablissement = modeEtablissementDao.findById(personnePhysiqueSelonId.getModeEtablissement2().getIdModeEtablissement())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(ModeEtablissement.class, personnePhysiqueDto.getModeEtablissementDto().getIdModeEtablissement().toString()));
                    personnePhysique.setModeEtablissement2(modeEtablissement);
                } else if (personnePhysiqueDto.getModeEtablissementDto() != null) {
                    ModeEtablissement modeEtablissement = modeEtablissementDao.findById(personnePhysiqueDto.getModeEtablissementDto().getIdModeEtablissement())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(ModeEtablissement.class, personnePhysiqueDto.getModeEtablissementDto().getIdModeEtablissement().toString()));
                    personnePhysique.setModeEtablissement2(modeEtablissement);
                }
                if (personnePhysiqueSelonId.getDistributeur() != null && personnePhysiqueDto.getDistributeur() == null) {
                    Personne distributeur = personneDao.findById(personnePhysiqueSelonId.getDistributeur().getIdPersonne())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Personne.class, personnePhysiqueDto.getDistributeur().getIdPersonne().toString()));
                    personnePhysique.setDistributeur(distributeur);
                } else if (personnePhysiqueDto.getDistributeur() != null) {
                    Personne distributeur = personneDao.findById(personnePhysiqueDto.getDistributeur().getIdPersonne())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Personne.class, personnePhysiqueDto.getDistributeur().getIdPersonne().toString()));
                    personnePhysique.setDistributeur(distributeur);
                }
                if (personnePhysiqueSelonId.getSurfaceTotale() != 0 && personnePhysiqueDto.getSurfaceTotale() == 0) {
                    personnePhysique.setSurfaceTotale(personnePhysiqueSelonId.getSurfaceTotale());
                }
                if (personnePhysiqueSelonId.getBp() != null && personnePhysiqueDto.getBp() == null) {
                    personnePhysique.setBp(personnePhysiqueSelonId.getBp());
                }
                if (personnePhysiqueSelonId.getIfu() != null && personnePhysiqueDto.getIfu() == null) {
                    personnePhysique.setIfu(personnePhysiqueSelonId.getIfu());
                }
                if (personnePhysiqueSelonId.getDomicile() != null && personnePhysiqueDto.getDomicile() == null) {
                    personnePhysique.setDomicile(personnePhysiqueSelonId.getDomicile());
                }
                if (personnePhysiqueSelonId.getTitreContact() != null && personnePhysiqueDto.getTitreContact() == null) {
                    personnePhysique.setTitreContact(personnePhysiqueSelonId.getTitreContact());
                }
                if (personnePhysiqueSelonId.getModeEtablissement() != null && personnePhysiqueDto.getModeEtablissement() == null) {
                    personnePhysique.setModeEtablissement(personnePhysiqueSelonId.getModeEtablissement());
                }
                if (personnePhysiqueSelonId.getStatutMatrimonial() != null && personnePhysiqueDto.getStatutMatrimonial() == null) {
                    personnePhysique.setStatutMatrimonial(personnePhysiqueSelonId.getStatutMatrimonial());
                }
            }
        } else {
            if (personnePhysiqueDto.getProfession() != null && personnePhysiqueDto.getProfession().getIdProf() != null) {
                Profession profession = professionDao.findById(personnePhysiqueDto.getProfession().getIdProf())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Profession.class, personnePhysiqueDto.getProfession().getIdProf().toString()));
                personnePhysique.setProfession(profession);
            }
            if (personnePhysiqueDto.getSecteurEmp() != null && personnePhysiqueDto.getSecteurEmp().getIdSecteur() != null) {
                Secteur secteurEmp = secteurDao.findById(personnePhysiqueDto.getSecteurEmp().getIdSecteur())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personnePhysiqueDto.getSecteurEmp().getIdSecteur().toString()));
                personnePhysique.setSecteurEmp(secteurEmp);
            }

            if (personnePhysiqueDto.getCategorieClient() != null && personnePhysiqueDto.getCategorieClient().getIdCategorieClient() != null) {
                CategorieClient categorieClient = categorieClientDao.findById(personnePhysiqueDto.getCategorieClient().getIdCategorieClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(CategorieClient.class, personnePhysiqueDto.getCategorieClient().getIdCategorieClient().toString()));
                personnePhysique.setCategorieClient(categorieClient);
            }

            if (personnePhysiqueDto.getSousTypeClient() != null && personnePhysiqueDto.getSousTypeClient().getIdSousTypeClient() != null) {
               SousTypeClient sousTypeClient = sousTypeClientDao.findById(personnePhysiqueDto.getSousTypeClient().getIdSousTypeClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(SousTypeClient.class, personnePhysiqueDto.getSousTypeClient().getIdSousTypeClient().toString()));
                personnePhysique.setSousTypeClient(sousTypeClient);
            }
            if (personnePhysiqueDto.getPaysPere() != null && personnePhysiqueDto.getPaysPere().getIdPays() != null) {
                Pays paysPere = paysDao.findById(personnePhysiqueDto.getPaysPere().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysPere().getIdPays().toString()));
                personnePhysique.setPaysPere(paysPere);
            }
            if (personnePhysiqueDto.getPaysMere() != null && personnePhysiqueDto.getPaysMere().getIdPays() != null) {
                Pays paysMere = paysDao.findById(personnePhysiqueDto.getPaysMere().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysMere().getIdPays().toString()));
                personnePhysique.setPaysMere(paysMere);
            }
            if (personnePhysiqueDto.getPaysConjoint() != null && personnePhysiqueDto.getPaysConjoint().getIdPays() != null) {
                Pays paysConjoint = paysDao.findById(personnePhysiqueDto.getPaysConjoint().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysConjoint().getIdPays().toString()));
                personnePhysique.setPaysConjoint(paysConjoint);
            }
            if (personnePhysiqueDto.getPaysNationalite() != null && personnePhysiqueDto.getPaysNationalite().getIdPays() != null) {
                Pays paysNationalite = paysDao.findById(personnePhysiqueDto.getPaysNationalite().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysNationalite().getIdPays().toString()));
                personnePhysique.setPaysNationalite(paysNationalite);
            }
            if (personnePhysiqueDto.getPaysResidence() != null && personnePhysiqueDto.getPaysResidence().getIdPays() != null) {
                Pays paysResidence = paysDao.findById(personnePhysiqueDto.getPaysResidence().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDto.getPaysResidence().getIdPays().toString()));
                personnePhysique.setPaysResidence(paysResidence);
            }
            if (personnePhysiqueDto.getDegre() != null && personnePhysiqueDto.getDegre().getIdDegre() != null) {
                Degre degre = degreDao.findById(personnePhysiqueDto.getDegre().getIdDegre())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Degre.class, personnePhysiqueDto.getDegre().getIdDegre().toString()));
                personnePhysique.setDegre(degre);
            }
            if (personnePhysiqueDto.getSecteur() != null && personnePhysiqueDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(personnePhysiqueDto.getSecteur().getIdSecteur())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personnePhysiqueDto.getSecteur().getIdSecteur().toString()));
                personnePhysique.setSecteur(secteur);
            }
            if (personnePhysiqueDto.getDistributeur() != null && personnePhysiqueDto.getDistributeur().getIdPersonne() != null) {
                Personne distributeur = personneDao.findById(personnePhysiqueDto.getDistributeur().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Personne.class, personnePhysiqueDto.getDistributeur().getIdPersonne().toString()));
                personnePhysique.setDistributeur(distributeur);
            }
            if (personnePhysiqueDto.getModeEtablissementDto() != null && personnePhysiqueDto.getModeEtablissementDto().getIdModeEtablissement() != null) {
                ModeEtablissement modeEtablissement = modeEtablissementDao.findById(personnePhysiqueDto.getModeEtablissementDto().getIdModeEtablissement())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(ModeEtablissement.class, personnePhysiqueDto.getModeEtablissementDto().getIdModeEtablissement().toString()));
                personnePhysique.setModeEtablissement2(modeEtablissement);
            }
//            System.out.println("idlangue==" + personnePhysiqueDto.getLangue().getIdLangue().toString());
            if (personnePhysiqueDto.getLangue() != null && personnePhysiqueDto.getLangue().getIdLangue() != null) {
                Langue langue = langueDao.findById(personnePhysiqueDto.getLangue().getIdLangue())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Langue.class, personnePhysiqueDto.getLangue().getIdLangue().toString()));
                personnePhysique.setLangue(langue);
            }
        }
        Set<DocumentDto> documents = personnePhysiqueDto.getDocuments();
        for (DocumentDto doc : documents) {
            personnePhysique.ajouterDocument(documentMapper.deDocumentDto(doc));
        }
        PersonnePhysiqueDto personnePhysiqueSave = personnePhysiqueMapper.dePersonnePhysique(personnePhysiqueDao.save(personnePhysique));
        personnePhysiquePaysService.supprimerSelonPersonne(personnePhysiqueSave.getIdPersonne());
        PersonnePhysiquePaysDto personnePhysiquePaysDto = new PersonnePhysiquePaysDto();
        if (personnePhysiqueDto.getPersonnePhysiquePaysDtos() != null) {
            for (PersonnePhysiquePaysDto o : personnePhysiqueDto.getPersonnePhysiquePaysDtos()) {
                ClePersonnePhysiquePays clePersonnePhysiquePays = new ClePersonnePhysiquePays();
                clePersonnePhysiquePays.setIdPays(o.getPaysDto().getIdPays());
                clePersonnePhysiquePays.setIdPersonne(personnePhysiqueSave.getIdPersonne());
                personnePhysiquePaysDto.setIdPersonnePhysiquePays(clePersonnePhysiquePays);
                personnePhysiquePaysDto.setPaysDto(o.getPaysDto());
                personnePhysiquePaysDto.setPersonnePhysiqueDto(personnePhysiqueSave);
//            o.setPersonnePhysiqueDto(personnePhysiqueDto);
//            o.setPaysDto();
                personnePhysiquePaysService.creerPersonnePhysiquePays(personnePhysiquePaysDto);
//            System.out.println("User1 - {} " + o);
            }
        }

        if (files != null) {
            Set<DocumentDto> docs = new HashSet<>(personnePhysiqueSave.getDocuments());
            Set<DocumentDto> newDocs = fileService.uploadMedia(PATH, files, docs);
            personnePhysiqueSave.setDocuments(newDocs);
        }

        return personnePhysiqueSave;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object> createPHFromOppciel1() throws JsonProcessingException {
        List<Object> result = new ArrayList<>();
        List<Object[]> personnePhysiques;
        //Se connecter à opcciel1 et récupérer les différentes catégories
        /*try {
            StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Parametre].[PS_PersonnePhysique_SP]");
            //Déclarer les différents paramètres
            query.registerStoredProcedureParameter("IdPersonne", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("Civilte", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("Sexe", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("NomPersonnePhysique", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PrenomPersonnePhysique", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("DateNaissance", java.time.LocalDateTime.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("CodePaysNaissance", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("LieuNaissance", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("EstMineur", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("nomDeJeuneFille", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("PhotoPersonnePhysique", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("SignaturePersonnePhysique", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("LibelleSecteurActivite", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("LibelleProfession", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("EstActifPersonne", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("CodePays", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libelleQualite", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("numCompteDepositaire", String.class, ParameterMode.IN);

            query.registerStoredProcedureParameter("numLigne", Double.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateCreationServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifClient", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("supprimer", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("rowvers", byte[].class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateFermetureCompte", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("motifFermetureCompte", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pieceFermetureCompte", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("statutCompte", String.class, ParameterMode.IN);

            //Fournir les différents paramètres
            query.setParameter("IdPersonne", null);
            query.setParameter("Civilte", null);
            query.setParameter("Sexe", null);
            query.setParameter("NomPersonnePhysique", null);
            query.setParameter("PrenomPersonnePhysique", null);
            query.setParameter("DateNaissance", null);
            query.setParameter("CodePaysNaissance", null);
            query.setParameter("LieuNaissance", null);
            query.setParameter("EstMineur", null);
            query.setParameter("nomDeJeuneFille", null);
            query.setParameter("PhotoPersonnePhysique", null);
            query.setParameter("SignaturePersonnePhysique", null);
            query.setParameter("LibelleSecteurActivite", null);
            query.setParameter("LibelleProfession", null);
            query.setParameter("EstActifPersonne", null);
            query.setParameter("CodePays", null);
            query.setParameter("libelleQualite", null);
            query.setParameter("numCompteDepositaire", null);

            query.setParameter("numLigne", null);
            query.setParameter("dateCreationServeur", null);
            query.setParameter("dateDernModifServeur", null);
            query.setParameter("dateDernModifClient", null);
            query.setParameter("userLogin", null);
            query.setParameter("supprimer", false);
            query.setParameter("rowvers", null);

            query.setParameter("dateFermetureCompte", null);
            query.setParameter("motifFermetureCompte", null);
            query.setParameter("pieceFermetureCompte", null);
            query.setParameter("statutCompte", null);
            query.execute();
            personnePhysiques = query.getResultList();

            int cpt = 0;
            for (Object[] o : personnePhysiques) {
                var idPersonne = (BigDecimal) o[0];
                LocalDateTime dateNaiss = ((Timestamp) o[5]).toLocalDateTime();
                String codePaysNaiss = ((String) o[6]).trim();
                String paysNaiss = ((String) o[7]).trim();
                String lieuNaiss = ((String) o[8]).trim();
                boolean estMineur = (boolean) o[9];
                String nomDeJeuneFille = ((String) o[10]).trim();
                String libelleSecteurActivite = ((String) o[13]).trim();
                boolean estActif = (boolean) o[15];
                String codePays = ((String) o[16]).trim();
                String qualite = ((String) o[18]).trim();
                String telFix = ((String) o[21]).trim();
                String mobile1 = ((String) o[22]).trim();
                String mobile2 = ((String) o[23]).trim();
                String email = ((String) o[24]).trim();
                String bp = ((String) o[25]).trim();
                String adresse = ((String) o[26]).trim();
                String numCompteSGI = ((String) o[38]).trim();
                BigDecimal tauxRetroCourSous = (BigDecimal) o[39];
                BigDecimal tauxRetroCourRach = (BigDecimal) o[40];
                LocalDateTime dateFermetureCompte = ((Timestamp) o[41]).toLocalDateTime();
                String motifFermetureCompte = ((String) o[42]).trim();
                String pieceFermetureCompte = ((String) o[43]).trim();
                String statutCompte = ((String) o[44]).trim();
                String libelleProfession = ((String) o[14]).trim();
                String nom = ((String) o[3]).trim();
                String prenoms = ((String) o[4]).trim();

                if (nom.contains("_") || nom.contains("-") || nom.contains("*"))
                    nom = nom.replace('_', ' ').replace('-', ' ').replace('*', ' ');

                if (prenoms.contains("_") || prenoms.contains("-") || prenoms.contains("*"))
                    prenoms = prenoms.replace('_', ' ').replace('-', ' ').replace('*', ' ');

                if (nom.equals(prenoms)) {
                    StringJoiner joiner = new StringJoiner(" ");
                    int i = 0;
                    for (String s : nom.split(" ")) {
                        if (s.equalsIgnoreCase("DE"))
                            continue;
                        if (i > 0) {
                            joiner.add(s);
                        }
                        i++;
                    }
                    prenoms = joiner.toString().trim();
                    //System.out.println(((String)o[18]).trim() + "_" + ((String)o[o.length-9]).trim() + " (DOUBLON) : " + prenoms.trim());
                }

                //Instancier un objet de type PersonnePhysique
                PersonnePhysique personnePhysique = personnePhysiqueDao.findByNumeroCpteDepositIgnoreCase(((String)o[o.length - 9]).trim()).orElse(new PersonnePhysique());
                personnePhysique.setIdOcc(idPersonne.longValue());
                personnePhysique.setCivilite(((String) o[1]).trim());
                personnePhysique.setSexe(((String) o[2]).trim());
                personnePhysique.setNom(nom);
                personnePhysique.setNomPersonnePhysique(nom);
                personnePhysique.setPrenom(prenoms);
                personnePhysique.setPrenomPersonnePhysique(prenoms);
                personnePhysique.setDenomination(personnePhysique.getNom() + " " + personnePhysique.getPrenom());
                personnePhysique.setCodePaysNaissance(codePaysNaiss.trim());
                personnePhysique.setLieuNaissance(lieuNaiss.trim());
                personnePhysique.setEstMineur(estMineur);
                personnePhysique.setNomDeJeuneFille(nomDeJeuneFille.trim());
                personnePhysique.setLibelleSecteurActivite(libelleSecteurActivite.trim());
                personnePhysique.setEstActifPersonne(estActif);
                personnePhysique.setCodePays(codePays.trim());
                personnePhysique.setTelephoneFixe(telFix.trim());
                personnePhysique.setFixe1(telFix.trim());
                personnePhysique.setTelephoneMobile1(mobile1.trim());
                personnePhysique.setTelephoneMobile2(mobile2.trim());
                personnePhysique.setMobile1(mobile1.trim());
                personnePhysique.setMobile2(mobile2.trim());
                personnePhysique.setEmail(email.trim());
                personnePhysique.setEmailPerso(email.trim());
                personnePhysique.setBoitePostale(bp.trim());
                personnePhysique.setBp(bp.trim());
                personnePhysique.setAdresseComplete(adresse.trim());
                personnePhysique.setTauxRetroCourSous(tauxRetroCourSous);
                personnePhysique.setTauxRetroCourRach(tauxRetroCourRach);
                personnePhysique.setDateFermetureCompte(dateFermetureCompte);
                personnePhysique.setMotifFermetureCompte(motifFermetureCompte.trim());
                personnePhysique.setPieceFermetureCompte(pieceFermetureCompte.trim());
                personnePhysique.setStatutCompte(statutCompte.trim());
                personnePhysique.setDateNaissance(dateNaiss);
                personnePhysique.setNumCompteDepositaire(((String) o[o.length - 9]).trim());
                personnePhysique.setNumCompteSgi(numCompteSGI.trim());
                personnePhysique.setNumeroCpteDeposit(((String) o[o.length - 9]).trim());
                personnePhysique.setLibelleProfession(libelleProfession.trim());
                personnePhysique.setLibelleTypePersonne("PH");

                //Enregistrement du pays de naissance s'il n'existe pas dans Refonte
                Pays paysNaissance = null;
                try {
                    if (!StringUtils.isEmpty(codePaysNaiss) && !paysDao.existsByCodePaysIgnoreCase(codePaysNaiss)) {
                        //Vérifier si la qualité à ajouter contient une des qualités de la base
                        boolean alreadyExist = false;
                        for (Pays p : paysDao.findAll()) {
                            if (
                                    !StringUtils.isEmpty(p.getCodePays()) &&
                                            org.apache.commons.lang3.StringUtils.containsIgnoreCase(codePaysNaiss, p.getCodePays().toLowerCase())
                            ) {
                                alreadyExist = true;
                                break;
                            }
                        }

                        if (!alreadyExist) {
                            Pays pays = new Pays();
                            pays.setCodePays(codePaysNaiss);
                            pays.setLibelleFr(paysNaiss);
                            paysNaissance = paysDao.save(pays);
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("Erreur de récupération du pays de naissance " + codePaysNaiss + " " + e.getMessage());
                }

                if (!StringUtils.isEmpty(codePaysNaiss) && paysNaissance == null) {
                    paysNaissance = paysDao.findByCodePaysIgnoreCase(codePaysNaiss).orElse(null);
                }

                //Enregistrer le secteur s'il n'existe pas dans Refonte
                Secteur secteur = null;
                if (!StringUtils.isEmpty(libelleSecteurActivite) && !secteurDao.existsByLibelleSecteurIgnoreCase(libelleSecteurActivite)) {
                    //Vérifier si la qualité à ajouter contient une des qualités de la base
                    boolean alreadyExist = false;
                    for (Secteur s : secteurDao.findAll()) {
                        if (!StringUtils.isEmpty(s.getLibelleSecteur()) &&
                                org.apache.commons.lang3.StringUtils.containsIgnoreCase(libelleSecteurActivite, s.getLibelleSecteur().toLowerCase())) {
                            alreadyExist = true;
                            break;
                        }
                    }

                    if (!alreadyExist) {
                        Secteur sect = new Secteur();
                        sect.setLibelleSecteur(libelleSecteurActivite);
                        secteur = secteurDao.save(sect);
                    }
                }

                if (!StringUtils.isEmpty(libelleSecteurActivite) && secteur == null)
                    secteur = secteurDao.findByLibelleSecteurIgnoreCase(libelleSecteurActivite).orElse(null);

                //Instancier l'objet StatutPersonne
                //Vérifier si la qualité existe dans la table Parametre.T_Qualite
                Qualite qualitePers = null;
                if (!StringUtils.isEmpty(qualite) && !qualiteDao.existsByLibelleQualiteIgnoreCase(qualite).orElse(false)) {
                    //Vérifier si la qualité à ajouter contient une des qualités de la base
                    boolean alreadyExist = false;
                    for (Qualite q : qualiteDao.findAll()) {
                        if (!StringUtils.isEmpty(q.getLibelleQualite()) &&
                                org.apache.commons.lang3.StringUtils.containsIgnoreCase(qualite, q.getLibelleQualite().toLowerCase())) {
                            qualite = q.getLibelleQualite();
                            alreadyExist = true;
                            break;
                        }
                    }

                    if (!alreadyExist) {
                        Qualite quality = new Qualite();
                        quality.setLibelleQualite(qualite);
                        qualitePers = qualiteDao.save(quality);
                    }
                }

                if (!StringUtils.isEmpty(qualite) && qualitePers == null) {
                    qualitePers = qualiteDao.findByLibelleQualiteIgnoreCase(qualite).orElse(null);
                }

                Profession profession = null;
                if (!StringUtils.isEmpty(libelleProfession) && !professionDao.existsByLibelleProfession(libelleProfession)) {
                    //Vérifier si la qualité à ajouter contient une des qualités de la base
                    boolean alreadyExist = false;
                    for (Profession p : professionDao.findAll()) {
                        if (!StringUtils.isEmpty(p.getLibelleProfession()) &&
                                org.apache.commons.lang3.StringUtils.containsIgnoreCase(libelleProfession, p.getLibelleProfession().toLowerCase())
                        ) {
                            alreadyExist = true;
                            break;
                        }
                    }

                    if (!alreadyExist) {
                        Profession prof = new Profession();
                        prof.setLibelleProfession(libelleProfession);
                        profession = professionDao.save(prof);
                    }
                }

                if (!StringUtils.isEmpty(libelleProfession) && profession == null)
                    profession = professionDao.findByLibelleProfession(libelleProfession);

                //Enregistrement de PersonnePhysique
                personnePhysique.setSecteur(secteur);
                personnePhysique.setPaysNationalite(paysNaissance);
                personnePhysique.setProfession(profession);
                PersonnePhysique personnePhysiqueSaved = personnePhysiqueDao.save(personnePhysique);

                //Vérifier si une instance de StatutPersonne avec la qualité existait déjà
                if (qualitePers != null && personnePhysiqueSaved.getIdPersonne() != null) {
                    if (!statutPersonneDao.existsByPersonne_IdPersonneAndQualite_IdQualite(personnePhysiqueSaved.getIdPersonne(), qualitePers.getIdQualite())) {
                        StatutPersonne statutPersonne = new StatutPersonne();
                        CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                        cleStatutPersonne.setIdPersonne(personnePhysiqueSaved.getIdPersonne());
                        cleStatutPersonne.setIdQualite(qualitePers.getIdQualite());
                        statutPersonne.setIdStatutPersonne(cleStatutPersonne);

                        statutPersonne.setPersonne(personnePhysiqueSaved);
                        statutPersonne.setQualite(qualitePers);
                        statutPersonneDao.save(statutPersonne);
                    } else {
                        LOGGER.info("Cette qualité existait déjà pour ce " + qualite);
                    }
                } else {
                    LOGGER.info("Cette qualité n'existe pas.");
                }

                result.add(personnePhysiqueSaved);
                cpt++;
            }
        }
        catch (Exception e) {
            LOGGER.error("Erreur : " + e.getMessage());
        }
        finally {
            LOGGER.info("Importation Personnes Physiques terminées...");
        }*/

        return result;
    }

    @Override
    public PersonnePhysiqueDtoEJ creerPersonnePhysiqueEJ(List<MultipartFile> files, PersonnePhysiqueDtoEJ personnePhysiqueDtoEJ) {
        return null;
    }

    @Override
    public PersonnePhysiqueDtoEJ modifierPersonnePhysiqueEJ(List<MultipartFile> files, PersonnePhysiqueDtoEJ personnePhysiqueDtoEJ) throws Throwable {
        PersonnePhysique personnePhysique = personnePhysiqueMapper.dePersonnePhysiqueDtoEJ(personnePhysiqueDtoEJ);
        personnePhysique.setDenomination(personnePhysiqueDtoEJ.getNom() + " " + personnePhysiqueDtoEJ.getPrenom());

        if (personnePhysiqueDtoEJ.getPaysNationalite() != null && personnePhysiqueDtoEJ.getPaysNationalite().getIdPays() != null) {
            Pays paysNationalite = paysDao.findById(personnePhysiqueDtoEJ.getPaysNationalite().getIdPays())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDtoEJ.getPaysNationalite().getIdPays().toString()));
            personnePhysique.setPaysNationalite(paysNationalite);
        }
        if (personnePhysiqueDtoEJ.getPaysResidence() != null && personnePhysiqueDtoEJ.getPaysResidence().getIdPays() != null) {
            Pays paysResidence = paysDao.findById(personnePhysiqueDtoEJ.getPaysResidence().getIdPays())
                    .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personnePhysiqueDtoEJ.getPaysResidence().getIdPays().toString()));
            personnePhysique.setPaysResidence(paysResidence);
        }

        Set<DocumentDto> documents = personnePhysiqueDtoEJ.getDocuments();
        for (DocumentDto doc : documents) {
            personnePhysique.ajouterDocument(documentMapper.deDocumentDto(doc));
        }
        PersonnePhysiqueDtoEJ personnePhysiqueSave = personnePhysiqueMapper.dePersonnePhysiqueEJ(personnePhysiqueDao.save(personnePhysique));
        personnePhysiquePaysService.supprimerSelonPersonne(personnePhysiqueSave.getIdPersonne());
        PersonnePhysiquePaysDtoEJ personnePhysiquePaysDto = new PersonnePhysiquePaysDtoEJ();
        if (personnePhysiqueDtoEJ.getPersonnePhysiquePaysDtos() != null) {
            for (PersonnePhysiquePaysDto o : personnePhysiqueDtoEJ.getPersonnePhysiquePaysDtos()) {
                ClePersonnePhysiquePays clePersonnePhysiquePays = new ClePersonnePhysiquePays();
                clePersonnePhysiquePays.setIdPays(o.getPaysDto().getIdPays());
                clePersonnePhysiquePays.setIdPersonne(personnePhysiqueSave.getIdPersonne());
                personnePhysiquePaysDto.setIdPersonnePhysiquePays(clePersonnePhysiquePays);
                personnePhysiquePaysDto.setPaysDto(o.getPaysDto());
                personnePhysiquePaysDto.setPersonnePhysiqueDto(personnePhysiqueSave);
//            o.setPersonnePhysiqueDto(personnePhysiqueDto);
//            o.setPaysDto();
                //System.out.println("def=" + o.toString());
                personnePhysiquePaysService.creerPersonnePhysiquePaysEJ(personnePhysiquePaysDto);
//            System.out.println("User1 - {} " + o);
            }
        }
        if (!personnePhysiqueSave.isEstExpose() && !personnePhysiqueSave.isEstJuge()) {
            if (files != null) {
                Set<DocumentDto> docs = new HashSet<>(personnePhysiqueSave.getDocuments());
                Set<DocumentDto> newDocs = fileService.uploadMedia(PATH, files, docs);
                personnePhysiqueSave.setDocuments(newDocs);
            }
        }
        return personnePhysiqueSave;
    }

    @Override
    public void supprimerPersonnePhysique(long idPersonne) {
        personnePhysiqueDao.deleteById(idPersonne);
    }

    @Override
    public void supprimerPersonnePhysique(long idPersonne, long idQualite) {
        PersonnePhysiqueDto personnePhysiqueDto = afficherPersonnePhysique(idPersonne);
        long nbre = 0;
        if (personnePhysiqueDto.getStatutPersonnes() != null) {
            nbre = personnePhysiqueDto.getStatutPersonnes().stream().count();
            //System.out.println(nbre);
            if (nbre == 1) {

                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(idPersonne);
                cleStatutPersonne.setIdQualite(idQualite);
                statutPersonneDao.deleteById(cleStatutPersonne);
                personnePhysiqueDao.deleteById(idPersonne);
            } else {
                CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(idPersonne);
                cleStatutPersonne.setIdQualite(idQualite);
                statutPersonneDao.deleteById(cleStatutPersonne);
            }
        }
    }
}
