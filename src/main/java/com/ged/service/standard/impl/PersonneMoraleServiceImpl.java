package com.ged.service.standard.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.ged.dao.opcciel.FormeJuridiqueDao;
import com.ged.dao.standard.*;
import com.ged.dao.standard.revuecompte.CategorieClientDao;
import com.ged.dao.standard.revuecompte.SousTypeClientDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.EnvoiMailDto;
import com.ged.dto.standard.MailDto;
import com.ged.dto.standard.PersonneMoraleDto;
import com.ged.entity.standard.FormeJuridique;
import com.ged.entity.standard.revuecompte.CategorieClient;
import com.ged.entity.standard.revuecompte.SousTypeClient;
import com.ged.mapper.standard.PersonneMoraleMapper;
import com.ged.service.standard.EnvoiMailService;
import com.ged.service.standard.MailService;
import com.ged.service.standard.PersonneMoraleService;
import com.ged.dao.crm.DegreDao;
import com.ged.entity.crm.Degre;
import com.ged.entity.standard.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional
public class PersonneMoraleServiceImpl implements PersonneMoraleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonneMoraleServiceImpl.class);

    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;

    private final PersonneMoraleDao personneMoraleDao;
    private final PersonneMoraleMapper personneMoraleMapper;
    private final SecteurDao secteurDao;
    private final StatutPersonneDao statutPersonneDao;
    private final PaysDao paysDao;
    private final DegreDao degreDao;
    private final PersonneDao personneDao;
    private final CategorieClientDao categorieClientDao;
    private final SousTypeClientDao sousTypeClientDao;
    private final EmailServiceImpl emailService;
    private final MailService mailService;
    private final EnvoiMailService envoiMailService;
    private final QualiteDao qualiteDao;
    private final FormeJuridiqueDao formeJuridiqueDao;

    public PersonneMoraleServiceImpl(PersonneMoraleDao personneMoraleDao, PersonneMoraleMapper personneMoraleMapper, SecteurDao secteurDao, StatutPersonneDao statutPersonneDao, PaysDao paysDao, DegreDao degreDao, PersonneDao personneDao, CategorieClientDao categorieClientDao, SousTypeClientDao sousTypeClientDao, EmailServiceImpl emailService, MailService mailService, EnvoiMailService envoiMailService, QualiteDao qualiteDao, FormeJuridiqueDao formeJuridiqueDao) {
        this.personneMoraleDao = personneMoraleDao;
        this.personneMoraleMapper = personneMoraleMapper;
        this.secteurDao = secteurDao;
        this.statutPersonneDao = statutPersonneDao;
        this.paysDao = paysDao;
        this.degreDao = degreDao;
        this.personneDao = personneDao;
        this.categorieClientDao = categorieClientDao;
        this.sousTypeClientDao = sousTypeClientDao;
        this.emailService = emailService;
        this.mailService = mailService;
        this.envoiMailService = envoiMailService;
        this.qualiteDao = qualiteDao;
        this.formeJuridiqueDao = formeJuridiqueDao;
    }

    @Override
    public DataTablesResponse<PersonneMoraleDto> afficherTous(String qualite, DatatableParameters parameters) {
        Page<PersonneMorale> personneMoralePage;
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());

        if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
        {
            personneMoralePage = personneMoraleDao.rechercherSelonQualite(qualite,parameters.getSearch().getValue(), pageable);
        }
        else {
            personneMoralePage = personneMoraleDao.afficherTousSelonQualite(qualite, pageable);
        }
        List<PersonneMoraleDto> content = personneMoralePage.getContent().stream().map(personneMoraleMapper::dePersonneMorale).collect(Collectors.toList());
        DataTablesResponse<PersonneMoraleDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)personneMoralePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)personneMoralePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public DataTablesResponse<PersonneMoraleDto> afficherPersonneMoralePolitiquementExpose(String qualite, DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());
        Page<PersonneMorale> personneMoralePage = personneMoraleDao.afficherPersonneMoralePolitiquementExpose(qualite, pageable);

        List<PersonneMoraleDto> content = personneMoralePage.getContent().stream().map(personneMoraleMapper::dePersonneMorale).collect(Collectors.toList());
        DataTablesResponse<PersonneMoraleDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)personneMoralePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)personneMoralePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public DataTablesResponse<PersonneMoraleDto> afficherPersonneMoraleJuge(String qualite, DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());
        Page<PersonneMorale> personneMoralePage = personneMoraleDao.afficherPersonneMoraleJuge(qualite, pageable);

        List<PersonneMoraleDto> content = personneMoralePage.getContent().stream().map(personneMoraleMapper::dePersonneMorale).collect(Collectors.toList());
        DataTablesResponse<PersonneMoraleDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)personneMoralePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)personneMoralePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public DataTablesResponse<PersonneMoraleDto> afficherPersonneSanctionnee(DatatableParameters parameters) {
        Pageable pageable = PageRequest.of(
                parameters.getStart()/ parameters.getLength(), parameters.getLength());
        Page<PersonneMorale> personneMoralePage = personneMoraleDao.afficherPersonneSanctionnee(pageable);

        List<PersonneMoraleDto> content = personneMoralePage.getContent().stream().map(personneMoraleMapper::dePersonneMorale).collect(Collectors.toList());
        DataTablesResponse<PersonneMoraleDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)personneMoralePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)personneMoralePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public Long afficherMaxNumordre(String valeur) {
        return personneMoraleDao.maxNumordre(valeur).getNumOrdre();
    }

    @Override
    public Page<PersonneMoraleDto> afficherPersonneMorales(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        Page<PersonneMorale> personneMoralePage=personneMoraleDao.findAll(pageRequest);
        return new PageImpl<>(personneMoralePage.getContent().stream().map(personneMoraleMapper::dePersonneMorale).collect(Collectors.toList()),pageRequest,personneMoralePage.getTotalElements());
    }

    @Override
    public PersonneMoraleDto afficherPersonneMorale(Long id) {
        return personneMoraleMapper.dePersonneMorale(afficherPersonneMoraleSelonId(id));
    }

    @Override
    public PersonneMorale afficherPersonneMoraleSelonId(Long idPersonne) {
        return personneMoraleDao.findById(idPersonne).orElseThrow(()-> new EntityNotFoundException("Personne morale avec ID "+idPersonne+" est introuvable"));
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PersonneMoraleDto creerPersonneMorale(PersonneMoraleDto personneMoraleDto) {
        PersonneMoraleDto personneMoraleDtoSaved = null;
        try {
            PersonneMorale personneMorale=personneMoraleMapper.dePersonneMoraleDto(personneMoraleDto);
            personneMorale.setDenomination(personneMoraleDto.getRaisonSociale() + " [" + personneMoraleDto.getSigle() + "]");
            if(personneMoraleDto.getPaysResidence() != null && personneMoraleDto.getPaysResidence().getIdPays() != null)
            {
                Pays paysResidence = paysDao.findById(personneMoraleDto.getPaysResidence().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personneMoraleDto.getPaysResidence().getIdPays().toString()));
                personneMorale.setPaysResidence(paysResidence);
            }
            /*if(personneMoraleDto.getFormeJuridique() != null && personneMoraleDto.getFormeJuridique().getCodeFormeJuridique() != null)
            {
                FormeJuridique formeJuridique = formeJuridiqueDao.findById(personneMoraleDto.getFormeJuridique().getCodeFormeJuridique())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personneMoraleDto.getFormeJuridique().getCodeFormeJuridique()));
                personneMorale.setFormeJuridique(formeJuridique);
            }*/
            if(personneMoraleDto.getDegre() != null && personneMoraleDto.getDegre().getIdDegre() != null)
            {
                Degre degre = degreDao.findById(personneMoraleDto.getDegre().getIdDegre())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Degre.class, personneMoraleDto.getDegre().getIdDegre().toString()));
                personneMorale.setDegre(degre);
            }

            if (personneMoraleDto.getCategorieClient() != null && personneMoraleDto.getCategorieClient().getIdCategorieClient() != null) {
                CategorieClient categorieClient = categorieClientDao.findById(personneMoraleDto.getCategorieClient().getIdCategorieClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(CategorieClient.class, personneMoraleDto.getCategorieClient().getIdCategorieClient().toString()));
                personneMorale.setCategorieClient(categorieClient);
            }

            if (personneMoraleDto.getSousTypeClient() != null && personneMoraleDto.getSousTypeClient().getIdSousTypeClient() != null) {
                SousTypeClient sousTypeClient = sousTypeClientDao.findById(personneMoraleDto.getSousTypeClient().getIdSousTypeClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(SousTypeClient.class, personneMoraleDto.getSousTypeClient().getIdSousTypeClient().toString()));
                personneMorale.setSousTypeClient(sousTypeClient);
            }

            if(personneMoraleDto.getSecteur() != null && personneMoraleDto.getSecteur().getIdSecteur() != null)
            {
                Secteur secteur = secteurDao.findById(personneMoraleDto.getSecteur().getIdSecteur())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personneMoraleDto.getSecteur().getIdSecteur().toString()));
                personneMorale.setSecteur(secteur);
            }
            if(personneMoraleDto.getDistributeur() != null && personneMoraleDto.getDistributeur().getIdPersonne() != null)
            {
                Personne personne = personneDao.findById(personneMoraleDto.getDistributeur().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Personne.class, personneMoraleDto.getDistributeur().getIdPersonne().toString()));
                personneMorale.setDistributeur(personne);
            }
            PersonneMorale personneMoraleSave = personneMoraleDao.save(personneMorale);
            personneMoraleDtoSaved = personneMoraleMapper.dePersonneMorale(personneMoraleSave);
            if(personneMoraleSave.getIdPersonne() != null)
            {
                if(
                        StringUtils.hasLength(personneMoraleSave.getEmailPro()) ||
                                StringUtils.hasLength(personneMoraleSave.getEmailPerso())
                )
                {
                    emailService.sendMail(
                            personneMoraleSave.getEmailPerso(),
                            "Remerciement",
                            "SAPHIR, vous remercie pour votre disponibilité !");
                    MailDto mailDto = new MailDto();
                    mailDto.setDateEnvoi(new Date());
                    mailDto.setHeureEnvoi(null);
                    mailDto.setObjet("Remerciement");
                    mailDto.setMsg("SAPHIR, vous remercie pour votre disponibilité !");
                    mailDto = mailService.creerMail(mailDto);
                    if(mailDto.getIdMail() != null)
                    {
                        EnvoiMailDto envoiMailDto = new EnvoiMailDto();
                        envoiMailDto.setMailDto(mailDto);
                        envoiMailDto.setPersonneDto(personneMoraleDtoSaved);
                        envoiMailDto = envoiMailService.creerEnvoiMail(envoiMailDto);
                    }
                }
            }
        }
        catch (Exception ex)
        {
//            personneMoraleDtoSaved = null;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return personneMoraleDtoSaved;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public PersonneMoraleDto modifierPersonneMorale(PersonneMoraleDto personneMoraleDto) {
        PersonneMorale personneMorale=personneMoraleMapper.dePersonneMoraleDto(personneMoraleDto);
        PersonneMorale personneMoraleSelonId=new PersonneMorale();
        if(personneMoraleDto.getIdPersonne()!=null)
        {
            personneMoraleSelonId=afficherPersonneMoraleSelonId(personneMoraleDto.getIdPersonne());
            personneMorale.setStatutPersonnes(personneMoraleSelonId.getStatutPersonnes());
        }

        personneMorale.setDenomination(personneMoraleDto.getRaisonSociale() + " [" + personneMoraleDto.getSigle() + "]");
        if(personneMoraleDto.isEstExpose()||personneMoraleDto.isEstJuge())
        {
            if (personneMoraleSelonId.getSiteWeb() != null && personneMoraleDto.getSiteWeb()== null) {
                personneMorale.setSiteWeb(personneMoraleSelonId.getSiteWeb());
            }
            if (personneMoraleSelonId.getBp() != null && personneMoraleDto.getBp()== null) {
                personneMorale.setBp(personneMoraleSelonId.getBp());
            }
            if (personneMoraleSelonId.getNomContact() != null && personneMoraleDto.getNomContact()== null) {
                personneMorale.setNomContact(personneMoraleSelonId.getNomContact());
            }
            if (personneMoraleSelonId.getEmailContact() != null && personneMoraleDto.getEmailContact()== null) {
                personneMorale.setEmailContact(personneMoraleSelonId.getEmailContact());
            }
            if (personneMoraleSelonId.getPrenomContact() != null && personneMoraleDto.getPrenomContact()== null) {
                personneMorale.setPrenomContact(personneMoraleSelonId.getPrenomContact());
            }
            if (personneMoraleSelonId.getTelContact() != null && personneMoraleDto.getTelContact()== null) {
                personneMorale.setTelContact(personneMoraleSelonId.getTelContact());
            }
            if (personneMoraleSelonId.getTitreContact() != null && personneMoraleDto.getTitreContact()== null) {
                personneMorale.setTitreContact(personneMoraleSelonId.getTitreContact());
            }
            if (personneMoraleSelonId.getEmailPerso() != null && personneMoraleDto.getEmailPerso()== null) {
                personneMorale.setEmailPerso(personneMoraleSelonId.getEmailPerso());
            }
            if (personneMoraleSelonId.getEmailPro() != null && personneMoraleDto.getEmailPro()== null) {
                personneMorale.setEmailPro(personneMoraleSelonId.getEmailPro());
            }
            if (personneMoraleSelonId.getDomicile() != null && personneMoraleDto.getDomicile()== null) {
                personneMorale.setDomicile(personneMoraleSelonId.getDomicile());
            }
            if (personneMoraleSelonId.getMobile1() != null && personneMoraleDto.getMobile1()== null) {
                personneMorale.setMobile1(personneMoraleSelonId.getMobile1());
            }
            if (personneMoraleSelonId.getMobile2() != null && personneMoraleDto.getMobile2()== null) {
                personneMorale.setMobile2(personneMoraleSelonId.getMobile2());
            }
            if (personneMoraleSelonId.getFixe1() != null && personneMoraleDto.getFixe1()== null) {
                personneMorale.setFixe1(personneMoraleSelonId.getFixe1());
            }
            if (personneMoraleSelonId.getFixe2() != null && personneMoraleDto.getFixe2()== null) {
                personneMorale.setFixe2(personneMoraleSelonId.getFixe2());
            }
            if (personneMoraleSelonId.getIfu() != null && personneMoraleDto.getIfu()== null) {
                personneMorale.setIfu(personneMoraleSelonId.getIfu());
            }
            if (personneMoraleDto.getPaysResidence() != null) {
                Pays paysResidence = paysDao.findById(personneMoraleDto.getPaysResidence().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personneMoraleDto.getPaysResidence().getIdPays().toString()));
                personneMorale.setPaysResidence(paysResidence);
            }

            if (personneMoraleSelonId.getCategorieClient() != null && personneMoraleDto.getCategorieClient() == null) {
                CategorieClient categorieClient = categorieClientDao.findById(personneMoraleSelonId.getCategorieClient().getIdCategorieClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(CategorieClient.class, personneMoraleDto.getCategorieClient().getIdCategorieClient().toString()));
                personneMorale.setCategorieClient(categorieClient);
            } else if (personneMoraleDto.getCategorieClient() != null) {
                CategorieClient categorieClient  = categorieClientDao.findById(personneMoraleDto.getCategorieClient().getIdCategorieClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(CategorieClient.class, personneMoraleDto.getCategorieClient().getIdCategorieClient().toString()));
                personneMorale.setCategorieClient(categorieClient);
            }

            if (personneMoraleSelonId.getSousTypeClient() != null && personneMoraleDto.getSousTypeClient() == null) {
                SousTypeClient sousTypeClient = sousTypeClientDao.findById(personneMoraleSelonId.getSousTypeClient().getIdSousTypeClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(SousTypeClient.class, personneMoraleDto.getSousTypeClient().getIdSousTypeClient().toString()));
                personneMorale.setSousTypeClient(sousTypeClient);
            } else if (personneMoraleDto.getSousTypeClient() != null) {
                SousTypeClient sousTypeClient = sousTypeClientDao.findById(personneMoraleDto.getSousTypeClient().getIdSousTypeClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(SousTypeClient.class, personneMoraleDto.getSousTypeClient().getIdSousTypeClient().toString()));
                personneMorale.setSousTypeClient(sousTypeClient);
            }

            if (personneMoraleSelonId.getDegre() != null && personneMoraleDto.getDegre()== null) {
                Degre degre = degreDao.findById(personneMoraleSelonId.getDegre().getIdDegre())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Degre.class, personneMoraleDto.getDegre().getIdDegre().toString()));
                personneMorale.setDegre(degre);
            }
            else
                if (personneMoraleDto.getDegre()!= null) {
                    Degre degre = degreDao.findById(personneMoraleDto.getDegre().getIdDegre())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Degre.class, personneMoraleDto.getDegre().getIdDegre().toString()));
                    personneMorale.setDegre(degre);
                }
            if (personneMoraleSelonId.getSecteur() != null && personneMoraleDto.getSecteur()== null) {
                Secteur secteur = secteurDao.findById(personneMoraleSelonId.getSecteur().getIdSecteur())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personneMoraleDto.getSecteur().getIdSecteur().toString()));
                personneMorale.setSecteur(secteur);
            }
            else
                if (personneMoraleDto.getSecteur()!= null) {
                    Secteur secteur = secteurDao.findById(personneMoraleDto.getSecteur().getIdSecteur())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personneMoraleDto.getSecteur().getIdSecteur().toString()));
                    personneMorale.setSecteur(secteur);
                }
            if (personneMoraleSelonId.getDistributeur() != null && personneMoraleDto.getDistributeur()== null) {
                Personne personne = personneDao.findById(personneMoraleSelonId.getDistributeur().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Personne.class, personneMoraleDto.getDistributeur().getIdPersonne().toString()));
                personneMorale.setDistributeur(personne);
            }
            else
                if (personneMoraleDto.getDistributeur()!= null) {
                    Personne personne = personneDao.findById(personneMoraleDto.getDistributeur().getIdPersonne())
                            .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Personne.class, personneMoraleDto.getDistributeur().getIdPersonne().toString()));
                    personneMorale.setDistributeur(personne);
                }
        }
        else {
            if (personneMoraleDto.getPaysResidence() != null && personneMoraleDto.getPaysResidence().getIdPays() != null) {
                Pays paysResidence = paysDao.findById(personneMoraleDto.getPaysResidence().getIdPays())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personneMoraleDto.getPaysResidence().getIdPays().toString()));
                personneMorale.setPaysResidence(paysResidence);
            }
            /*if(personneMoraleDto.getFormeJuridique() != null && personneMoraleDto.getFormeJuridique().getCodeFormeJuridique() != null)
            {
                FormeJuridique formeJuridique = formeJuridiqueDao.findById(personneMoraleDto.getFormeJuridique().getCodeFormeJuridique())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Pays.class, personneMoraleDto.getFormeJuridique().getCodeFormeJuridique()));
                personneMorale.setFormeJuridique(formeJuridique);
            }*/
            if (personneMoraleDto.getDegre() != null && personneMoraleDto.getDegre().getIdDegre() != null) {
                Degre degre = degreDao.findById(personneMoraleDto.getDegre().getIdDegre())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Degre.class, personneMoraleDto.getDegre().getIdDegre().toString()));
                personneMorale.setDegre(degre);
            }

            if (personneMoraleDto.getCategorieClient() != null && personneMoraleDto.getCategorieClient().getIdCategorieClient() != null) {
                CategorieClient categorieClient = categorieClientDao.findById(personneMoraleDto.getCategorieClient().getIdCategorieClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(CategorieClient.class, personneMoraleDto.getCategorieClient().getIdCategorieClient().toString()));
                personneMorale.setCategorieClient(categorieClient);
            }

            if (personneMoraleDto.getSousTypeClient() != null && personneMoraleDto.getSousTypeClient().getIdSousTypeClient() != null) {
                SousTypeClient sousTypeClient = sousTypeClientDao.findById(personneMoraleDto.getSousTypeClient().getIdSousTypeClient())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(SousTypeClient.class, personneMoraleDto.getSousTypeClient().getIdSousTypeClient().toString()));
                personneMorale.setSousTypeClient(sousTypeClient);
            }
            if (personneMoraleDto.getSecteur() != null && personneMoraleDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(personneMoraleDto.getSecteur().getIdSecteur())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Secteur.class, personneMoraleDto.getSecteur().getIdSecteur().toString()));
                personneMorale.setSecteur(secteur);
            }
            if (personneMoraleDto.getDistributeur() != null && personneMoraleDto.getDistributeur().getIdPersonne() != null) {
                Personne personne = personneDao.findById(personneMoraleDto.getDistributeur().getIdPersonne())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Personne.class, personneMoraleDto.getDistributeur().getIdPersonne().toString()));
                personneMorale.setDistributeur(personne);
            }
        }
        PersonneMorale personneMoraleMaj=personneMoraleDao.save(personneMorale);
        return personneMoraleMapper.dePersonneMorale(personneMoraleMaj);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Object> createPMFromOppciel1() throws JsonProcessingException {
        List<Object> result = new ArrayList<>();
        List<Object[]> personneMorales;
        //Se connecter à opcciel1 et récupérer les différentes catégories
        try {
            StoredProcedureQuery query = emOpcciel.createStoredProcedureQuery("[Parametre].[PS_PersonneMorale_SP]");
            //Déclarer les différents paramètres
            query.registerStoredProcedureParameter("IdPersonne", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("NumeroAgrementPersonneMorale", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("NumeroINSAE", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("codeSecteur", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("CapitalSocial", BigDecimal.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("SiglePersonneMorale", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("RaisonSociale", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("CodeFormeJuridique", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("EstActifPersonne", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("LibelleTypePersonne", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("CodePays", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libelleQualite", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libelleSousCategorie", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateCreationPM", java.time.LocalDateTime.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("numCompteDepositaire", String.class, ParameterMode.IN);

            query.registerStoredProcedureParameter("numLigne", Double.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateCreationServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifClient", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("supprimer", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("rowvers", byte[].class, ParameterMode.IN);

            query.registerStoredProcedureParameter("dateFermetureCompte", java.time.LocalDateTime.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("motifFermetureCompte", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("pieceFermetureCompte", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("statutCompte", String.class, ParameterMode.IN);

            //Fournir les différents paramètres
            query.setParameter("IdPersonne", null);
            query.setParameter("NumeroAgrementPersonneMorale", null);
            query.setParameter("NumeroINSAE", null);
            query.setParameter("codeSecteur", null);
            query.setParameter("CapitalSocial", null);
            query.setParameter("SiglePersonneMorale", null);
            query.setParameter("RaisonSociale", null);
            query.setParameter("CodeFormeJuridique", null);
            query.setParameter("EstActifPersonne", null);
            query.setParameter("LibelleTypePersonne", null);
            query.setParameter("CodePays", null);
            query.setParameter("libelleQualite", null);
            query.setParameter("libelleSousCategorie", null);
            query.setParameter("dateCreationPM", null);
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
            personneMorales = query.getResultList();

            int cpt = 0;
            for (Object[] o: personneMorales) {
                var idPersonne = (BigDecimal)o[0];
                String NumeroAgrementPersonneMorale = ((String)o[1]).trim();
                String NumeroINSAE = ((String)o[2]).trim();
                String codeSecteur = ((String)o[3]).trim();
                BigDecimal CapitalSocial = (BigDecimal)o[4];
                String libelleSousCategorie = ((String)o[5]).trim();
                String SiglePersonneMorale = ((String)o[6]).trim();
                String RaisonSociale = ((String)o[7]).trim();
                String CodeFormeJuridique = ((String)o[8]).trim();
                boolean EstActifPersonne = (boolean)o[9];
                String LibelleTypePersonne = ((String)o[10]).trim();
                String CodePays = ((String)o[11]).trim();
                String libellePays = ((String)o[12]).trim();
                String libelleQualite = ((String)o[13]).trim();
                String libelleFormeJuridiqueOPC = ((String)o[14]).trim();
                LocalDateTime dateCreationPM = ((Timestamp)o[15]).toLocalDateTime();
                String numCompteDepositaire = ((String)o[16]).trim();
                String telephoneFixe = ((String)o[17]).trim();
                String telephoneMobile1 = ((String)o[18]).trim();
                String telephoneMobile2 = ((String)o[19]).trim();
                String email = ((String)o[20]).trim();
                String boitePostale = ((String)o[21]).trim();
                String adresseComplete = ((String)o[22]).trim();
                BigDecimal tauxRetroCourSous = (BigDecimal)o[23];
                BigDecimal tauxRetroCourRach = (BigDecimal)o[24];
                String numCompteSgi = ((String)o[32]).trim();
                LocalDateTime dateFermetureCompte = ((Timestamp)o[33]).toLocalDateTime();
                String motifFermetureCompte = ((String)o[34]).trim();
                String pieceFermetureCompte = ((String)o[35]).trim();
                String statutCompte = ((String)o[36]).trim();

                PersonneMorale personneMorale = personneMoraleDao.findByNumeroCpteDepositIgnoreCase(((String)o[16]).trim()).orElse(new PersonneMorale());
                personneMorale.setIdOcc(idPersonne.longValue());
                personneMorale.setSiglePersonneMorale(SiglePersonneMorale.trim());
                personneMorale.setSigle(SiglePersonneMorale.trim());
                personneMorale.setRaisonSociale(RaisonSociale.trim());
                if(!StringUtils.isEmpty(personneMorale.getRaisonSociale()) && !StringUtils.isEmpty(personneMorale.getSigle()))
                {
                    personneMorale.setDenomination(personneMorale.getRaisonSociale() + " [" + personneMorale.getSigle() + "]");
                }
                if(!StringUtils.isEmpty(personneMorale.getRaisonSociale()) && StringUtils.isEmpty(personneMorale.getSigle()))
                {
                    personneMorale.setDenomination(personneMorale.getRaisonSociale());
                }
                if(StringUtils.isEmpty(personneMorale.getRaisonSociale()) && !StringUtils.isEmpty(personneMorale.getSigle()))
                {
                    personneMorale.setDenomination(personneMorale.getSigle());
                }
                personneMorale.setNumeroAgrementPersonneMorale(NumeroAgrementPersonneMorale.trim());
                personneMorale.setNumeroINSAE(NumeroINSAE.trim());
                personneMorale.setCodeSecteur(codeSecteur.trim());
                personneMorale.setCapitalSocial(CapitalSocial);
                personneMorale.setLibelleSousCategorie(libelleSousCategorie.trim());
//                    personneMorale.setCodeFormeJuridique(CodeFormeJuridique.trim());
                personneMorale.setEstActifPersonne(EstActifPersonne);
                personneMorale.setLibelleTypePersonne(LibelleTypePersonne.trim());
                personneMorale.setCodePays(CodePays.trim());
                personneMorale.setNumCompteDepositaire(numCompteDepositaire.trim());
                personneMorale.setNumeroCpteDeposit(numCompteDepositaire.trim());
                personneMorale.setTelephoneFixe1(telephoneFixe.trim());
                personneMorale.setFixe1(telephoneFixe.trim());
                personneMorale.setTelephoneMobile1(telephoneMobile1.trim());
                personneMorale.setMobile1(telephoneMobile1.trim());
                personneMorale.setTelephoneMobile2(telephoneMobile2.trim());
                personneMorale.setMobile2(telephoneMobile2.trim());
                personneMorale.setEmail(email.trim());
                personneMorale.setEmailPerso(email.trim());
                personneMorale.setEmailPro(email.trim());
                personneMorale.setBoitePostale(boitePostale.trim());
                personneMorale.setBp(boitePostale.trim());
                personneMorale.setAdresseComplete(adresseComplete.trim());
                personneMorale.setTauxRetroCourRach(tauxRetroCourRach);
                personneMorale.setTauxRetroCourSous(tauxRetroCourSous);
                personneMorale.setNumCompteSgi(numCompteSgi.trim());
                personneMorale.setDateCreationPM(dateCreationPM);
                personneMorale.setDateFermetureCompte(dateFermetureCompte);
                personneMorale.setMotifFermetureCompte(motifFermetureCompte.trim());
                personneMorale.setPieceFermetureCompte(pieceFermetureCompte.trim());
                personneMorale.setStatutCompte(statutCompte.trim());
                personneMorale.setIdOcc(((BigDecimal)o[0]).longValue());

                //Enregistrement du pays de naissance s'il n'existe pas dans Refonte
                Pays paysResidence = null;
                if(!StringUtils.isEmpty(CodePays) && !paysDao.existsByCodePaysIgnoreCase(CodePays.toLowerCase()))
                {
                    //Vérifier si la qualité à ajouter contient une des qualités de la base
                    boolean alreadyExist = false;
                    for (Pays p: paysDao.findAll()) {
                        if(!StringUtils.isEmpty(p.getCodePays()) &&
                                org.apache.commons.lang3.StringUtils.containsIgnoreCase(CodePays, p.getCodePays().toLowerCase())
//                                    CodePays.contains(p.getCodePays().toLowerCase())
                        )
                        {
                            alreadyExist = true;
                            break;
                        }
                    }

                    if(!alreadyExist)
                    {
                        Pays pays = new Pays();
                        pays.setCodePays(CodePays);
                        pays.setLibelleFr(libellePays);
                        paysResidence = paysDao.save(pays);
                    }
                }

                if(!StringUtils.isEmpty(CodePays) && paysResidence == null)
                    paysResidence = paysDao.findByCodePaysIgnoreCase(CodePays).orElse(null);

                //Enregistrer le secteur s'il n'existe pas dans Refonte
                Secteur secteur = null;
                if(!StringUtils.isEmpty(codeSecteur) && !secteurDao.existsByLibelleSecteurIgnoreCase(codeSecteur))
                {
                    //Vérifier si la qualité à ajouter contient une des qualités de la base
                    boolean alreadyExist = false;
                    for (Secteur s: secteurDao.findAll()) {
                        if(!StringUtils.isEmpty(s.getLibelleSecteur()) &&
                                org.apache.commons.lang3.StringUtils.containsIgnoreCase(codeSecteur, s.getLibelleSecteur().toLowerCase())
//                                    codeSecteur.contains(s.getLibelleSecteur().toLowerCase())
                        )
                        {
                            alreadyExist = true;
                            break;
                        }
                    }

                    if(!alreadyExist)
                    {
                        Secteur sect = new Secteur();
                        sect.setLibelleSecteur(codeSecteur);
                        secteur = secteurDao.save(sect);
                    }
                }

                if(!StringUtils.isEmpty(codeSecteur) && secteur == null)
                    secteur = secteurDao.findByLibelleSecteurIgnoreCase(codeSecteur).orElse(null);

                //Instancier l'objet StatutPersonne
                //Vérifier si la qualité existe dans la table Parametre.T_Qualite
                Qualite qualitePers = null;
                if(!StringUtils.isEmpty(libelleQualite) && !qualiteDao.existsByLibelleQualiteIgnoreCase(libelleQualite).orElse(false))
                {
                    //Vérifier si la qualité à ajouter contient une des qualités de la base
                    boolean alreadyExist = false;
                    for (Qualite q: qualiteDao.findAll()) {
                        if(!StringUtils.isEmpty(q.getLibelleQualite()) &&
                                org.apache.commons.lang3.StringUtils.containsIgnoreCase(libelleQualite, q.getLibelleQualite().toLowerCase())
//                                    libelleQualite.contains(q.getLibelleQualite().toLowerCase())
                        )
                        {
                            libelleQualite = q.getLibelleQualite();
                            alreadyExist = true;
                            break;
                        }
                    }

                    if(!alreadyExist)
                    {
                        Qualite quality = new Qualite();
                        quality.setLibelleQualite(libelleQualite);
                        qualitePers = qualiteDao.save(quality);
                    }
                }

                if(!StringUtils.isEmpty(libelleQualite) && qualitePers == null)
                    qualitePers = qualiteDao.findByLibelleQualiteIgnoreCase(libelleQualite).orElse(null);

                FormeJuridique formeJuridique = null;
                if(!StringUtils.isEmpty(CodeFormeJuridique) && !formeJuridiqueDao.existsByCodeFormeJuridiqueContainsIgnoreCase(CodeFormeJuridique.toLowerCase()))
                {
                    //Vérifier si la qualité à ajouter contient une des qualités de la base
                    boolean alreadyExist = false;
                    for (FormeJuridique f: formeJuridiqueDao.findAll()) {
                        if(!StringUtils.isEmpty(f.getCodeFormeJuridique()) &&
                                org.apache.commons.lang3.StringUtils.containsIgnoreCase(CodeFormeJuridique, f.getCodeFormeJuridique().toLowerCase())
//                                    CodePays.contains(p.getCodePays().toLowerCase())
                        )
                        {
                            alreadyExist = true;
                            break;
                        }
                    }

                    if(!alreadyExist)
                    {
                        FormeJuridique formeJur = new FormeJuridique();
                        formeJur.setCodeFormeJuridique(CodeFormeJuridique);
                        formeJur.setLibelleFormeJuridique(libelleFormeJuridiqueOPC);
                        formeJuridique = formeJuridiqueDao.save(formeJur);
                    }
                }

                if(!StringUtils.isEmpty(CodePays) && paysResidence == null)
                    paysResidence = paysDao.findByCodePaysIgnoreCase(CodePays).orElse(null);

                //Enregistrement dans la table Parametre.T_PersonneMorale
                personneMorale.setPaysResidence(paysResidence);
                personneMorale.setSecteur(secteur);
//                personneMorale.setFormeJuridique(formeJuridique);
                PersonneMorale personneMoraleSaved = personneMoraleDao.save(personneMorale);

                //Vérifier si une instance de StatutPersonne avec la qualité existait déjà
                LOGGER.info("Qualité PM Trouvée === " + qualitePers);
                if(qualitePers != null)
                {
                    LOGGER.info("Personne PM === " + personneMoraleSaved);
                    if(!statutPersonneDao.existsByPersonneAndQualite(personneMoraleSaved, qualitePers)) {
                        LOGGER.info(String.valueOf(personneMoraleSaved));
                        StatutPersonne statutPersonne = new StatutPersonne();
                        CleStatutPersonne cleStatutPersonne = new CleStatutPersonne();
                        cleStatutPersonne.setIdPersonne(personneMoraleSaved.getIdPersonne());
                        cleStatutPersonne.setIdQualite(qualitePers.getIdQualite());
                        statutPersonne.setIdStatutPersonne(cleStatutPersonne);

                        statutPersonne.setPersonne(personneMoraleSaved);
                        statutPersonne.setQualite(qualitePers);
                        statutPersonneDao.save(statutPersonne);
                    }
                }
                result.add(personneMoraleSaved);

                cpt++;
            }
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public void supprimerPersonneMorale(Long idPersonne) {
       personneMoraleDao.deleteById(idPersonne);
    }

    @Override
    public void supprimerPersonneMorale(Long idPersonne, Long idQualite) {
        PersonneMoraleDto personneMoraleDto=afficherPersonneMorale(idPersonne);
        long nbre=0;
        if(personneMoraleDto.getStatutPersonnes()!=null)
        {
            nbre=personneMoraleDto.getStatutPersonnes().stream().count();
            //System.out.println(nbre);
            if(nbre==1) {

                CleStatutPersonne cleStatutPersonne=new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(idPersonne);
                cleStatutPersonne.setIdQualite(idQualite);
                statutPersonneDao.deleteById(cleStatutPersonne);
                personneMoraleDao.deleteById(idPersonne);
            }
            else
            {
                CleStatutPersonne cleStatutPersonne=new CleStatutPersonne();
                cleStatutPersonne.setIdPersonne(idPersonne);
                cleStatutPersonne.setIdQualite(idQualite);
                statutPersonneDao.deleteById(cleStatutPersonne);
            }
        }
    }

    @Override
    public List<PersonneMoraleDto> afficherSelonQualite(String qualite) {
        return personneMoraleDao.afficherPersonneMoraleSelonQualite(qualite).stream().map(personneMoraleMapper::dePersonneMorale).collect(Collectors.toList());
    }

    @Override
    public List<PersonneMoraleDto> afficherPersonneMoraleNayantPasInvesti(String qualite, LocalDateTime dateDebut, LocalDateTime dateFin) {
        return personneMoraleDao.afficherPersonneMoraleNayantPasInvesti(qualite,dateDebut,dateFin).stream().map(personneMoraleMapper::dePersonneMorale).collect(Collectors.toList());
    }
}
