package com.ged.service.standard.impl;

import com.ged.dao.standard.AlerteDao;
import com.ged.dao.standard.DiffusionAlerteDao;
import com.ged.dao.standard.ProtoAlerteDao;
import com.ged.dto.standard.DiffusionAlerteDto;
import com.ged.dto.standard.MessageBoxDto;
import com.ged.service.standard.DiffusionAlerteService;
import com.ged.service.standard.MessageBoxService;
import com.ged.entity.standard.*;
import com.ged.mapper.standard.AlerteMapper;
import com.ged.mapper.standard.DiffusionAlerteMapper;
import com.ged.mapper.standard.ModeleMsgAlerteMapper;
import com.ged.mapper.standard.PersonneMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
//import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class DiffusionAlerteServiceImpl implements DiffusionAlerteService {
    private final DiffusionAlerteDao diffusionAlerteDao;
    private final DiffusionAlerteMapper diffusionAlerteMapper;
    private final ModeleMsgAlerteMapper modeleMsgAlerteMapper;
    private final AlerteMapper alerteMapper;
    private final AlerteDao alerteDao;
    private final PersonneMapper personneMapper;
    private final MessageBoxService messageBoxService;
    private final ProtoAlerteDao protoAlerteDao;

    public DiffusionAlerteServiceImpl(
            DiffusionAlerteDao diffusionAlerteDao,
            DiffusionAlerteMapper diffusionAlerteMapper,
            ModeleMsgAlerteMapper modeleMsgAlerteMapper,
            AlerteMapper alerteMapper,
            AlerteDao alerteDao,
            PersonneMapper personneMapper,
            MessageBoxService messageBoxService,
            ProtoAlerteDao protoAlerteDao) {
        this.diffusionAlerteDao = diffusionAlerteDao;
        this.diffusionAlerteMapper = diffusionAlerteMapper;
        this.modeleMsgAlerteMapper = modeleMsgAlerteMapper;
        this.alerteMapper = alerteMapper;
        this.alerteDao = alerteDao;
        this.personneMapper = personneMapper;
        this.messageBoxService = messageBoxService;
        this.protoAlerteDao = protoAlerteDao;
    }

    @Override
    @Scheduled(fixedRate = 1000)
    public void updateScheduler() {
        List<DiffusionAlerte> diffusionAlertes = diffusionAlerteDao.findAllByIsShownAndIsRead(false, false);
        for (DiffusionAlerte diffusionAlerte: diffusionAlertes) {
            Alerte alerte = diffusionAlerte.getAlerte();
            if(alerte != null && alerte.getIdAlerte() != null)
            {
                CleProtoAlerte cleProtoAlerte = new CleProtoAlerte();
                cleProtoAlerte.setIdAlerte(alerte.getIdAlerte());
                cleProtoAlerte.setIdModele(diffusionAlerte.getModeleMsgAlerte().getIdModele());
                ProtoAlerte protoAlerte = protoAlerteDao.findById(cleProtoAlerte).orElseThrow(null);
                MessageBoxDto messageBoxDto = new MessageBoxDto();
                if(protoAlerte != null)
                {
                    messageBoxDto.setObjet(diffusionAlerte.getModeleMsgAlerte().getObjet());
                    messageBoxDto.setContenu(protoAlerte.getContenu());
                    messageBoxDto.setDateEnvoiMsg(LocalDateTime.now());
                    messageBoxDto.setAlerte(alerteMapper.deAlerte(alerte));
                    messageBoxDto.setDestinataire(personneMapper.dePersonne(diffusionAlerte.getPersonne()));
                }

                //Get week day
                DateTimeFormatter dtfFrench = DateTimeFormatter.ofPattern("EEEE").localizedBy(Locale.forLanguageTag("fr"));
                String weekDay = dtfFrench.format(LocalDateTime.now().toLocalDate());
                System.out.println(weekDay);

                /*String weekDay = LocalDateTime.now().toLocalDate().getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.FRENCH);
                SimpleDateFormat formater = new SimpleDateFormat("EEEE");
                System.out.println("TEST === " + formater.format(LocalDateTime.now().toLocalDate()));*/

                //Formattage de datetime sous la forme dd/MM/yyyy
                DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dateDebut = alerte.getDateDebut().format(dateTimeFormatter);

                //Formattage de time sous la forme HH:mm:ss
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                String heureDeb = alerte.getHeureDebut().format(formatter);

                TypePlanification typePlanification = alerte.getTypePlanification();
                if(typePlanification != null && typePlanification.getIdTypePlanification() != null)
                {
                    //Traitement des alertes de type unique
                    if(typePlanification.getLibelleTypePlanification().equalsIgnoreCase("une fois"))
                    {
                        if(dateDebut.equalsIgnoreCase(LocalDateTime.now().format(dateTimeFormatter)))
                        {
                            if(heureDeb.equalsIgnoreCase(formatter.format(LocalDateTime.now())))
                            {
                                diffusionAlerte.setIsShown(true);
                                DiffusionAlerteDto maj = modifierDiffusionAlerte(diffusionAlerteMapper.deDiffusionAlerte(diffusionAlerte));
                                MessageBoxDto messageBoxSaved = messageBoxService.creerMsg(messageBoxDto);
                            }
                        }
                    }
                    //Traitement des alertes de type périodique
                    if(typePlanification.getLibelleTypePlanification().equalsIgnoreCase("périodique"))
                    {
                        Periodicite periodicite = alerte.getPeriodicite();
                        if(periodicite != null && periodicite.getIdPeriodicite() != null)
                        {
                            if(periodicite.getLibelle().equalsIgnoreCase("quotidienne"))
                            {
                                if(alerte.getDateFin() != null)
                                {
                                    //Vérifier si la date début et la date fin respecte la durée définie
                                    boolean betweenExpress = (alerte.getDateDebut().toLocalDate().isBefore(LocalDateTime.now().toLocalDate()) ||
                                            alerte.getDateDebut().toLocalDate().isEqual(LocalDateTime.now().toLocalDate())) &&
                                            (LocalDateTime.now().toLocalDate().isBefore(alerte.getDateFin().toLocalDate()) ||
                                                    LocalDateTime.now().toLocalDate().isEqual(alerte.getDateFin().toLocalDate()));

                                    if(betweenExpress && heureDeb.equalsIgnoreCase(formatter.format(LocalDateTime.now())))
                                    {
                                        diffusionAlerte.setIsShown(true);
                                        DiffusionAlerteDto maj = modifierDiffusionAlerte(diffusionAlerteMapper.deDiffusionAlerte(diffusionAlerte));
                                        MessageBoxDto messageBoxSaved = messageBoxService.creerMsg(messageBoxDto);
                                    }
                                }
                                else
                                {
                                    if(LocalDateTime.now().toLocalDate().isAfter(alerte.getDateDebut().toLocalDate()) ||
                                            LocalDateTime.now().toLocalDate().isEqual(alerte.getDateDebut().toLocalDate()))
                                    {
                                        if(heureDeb.equalsIgnoreCase(formatter.format(LocalDateTime.now())))
                                        {
                                            diffusionAlerte.setIsShown(true);
                                            DiffusionAlerteDto maj = modifierDiffusionAlerte(diffusionAlerteMapper.deDiffusionAlerte(diffusionAlerte));
                                            MessageBoxDto messageBoxSaved = messageBoxService.creerMsg(messageBoxDto);
                                        }
                                    }
                                }
                            }
                            //Vérifier les alertes qui doivent s'afficher selon le jour de la semaine
                            if(periodicite.getLibelle().equalsIgnoreCase("hebdomadaire"))
                            {
                                Set<JoursAlerte> joursAlertes = alerte.getJoursAlertes();
                                for (JoursAlerte joursAlerte: joursAlertes) {
                                    if(joursAlerte.getJours() != null && joursAlerte.getJours().getLibelleJours().toLowerCase().equals(weekDay))
                                    {
                                        if(heureDeb.equalsIgnoreCase(formatter.format(LocalDateTime.now())))
                                        {
                                            diffusionAlerte.setIsShown(true);
                                            DiffusionAlerteDto maj = modifierDiffusionAlerte(diffusionAlerteMapper.deDiffusionAlerte(diffusionAlerte));
                                            MessageBoxDto messageBoxSaved = messageBoxService.creerMsg(messageBoxDto);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<DiffusionAlerteDto> afficherTous() {
        return diffusionAlerteDao.findAllByIsShownAndIsRead(true, false).stream()
                .map(diffusionAlerteMapper::deDiffusionAlerte).collect(Collectors.toList());
    }

    @Override
    public Page<DiffusionAlerteDto> afficherDiffusionAlertes(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<DiffusionAlerte> diffusionAlertePage = diffusionAlerteDao.findAll(pageRequest);
        return new PageImpl<>(diffusionAlertePage.getContent().stream().map(diffusionAlerteMapper::deDiffusionAlerte).collect(Collectors.toList()), pageRequest, diffusionAlertePage.getTotalElements());
    }

    @Override
    public DiffusionAlerte afficherDiffusionAlerteSelonId(CleDiffusionAlerte idDiffusionAlerte) {
        return diffusionAlerteDao.findById(idDiffusionAlerte).orElseThrow(() -> new EntityNotFoundException("Element introuvable"));
    }

    @Override
    public List<DiffusionAlerteDto> afficherDiffusionAlerteSelonAlerte(long idAlerte) {
        Alerte alerte=alerteDao.findById(idAlerte);
        return diffusionAlerteDao.findByAlerte(alerte).stream().map(diffusionAlerteMapper::deDiffusionAlerte).collect(Collectors.toList());
    }

    @Override
    public DiffusionAlerteDto creerDiffusionAlerte(DiffusionAlerteDto diffusionAlerteDto) {
        DiffusionAlerte diffusionAlerte = diffusionAlerteMapper.deDiffusionAlerteDto(diffusionAlerteDto);

        //Définir la clé
        CleDiffusionAlerte cleDiffusionAlerte = new CleDiffusionAlerte();
        cleDiffusionAlerte.setIdPersonne(diffusionAlerteDto.getPersonne().getIdPersonne());
        cleDiffusionAlerte.setIdAlerte(diffusionAlerteDto.getProtoAlerte().getAlerte().getIdAlerte());
        cleDiffusionAlerte.setIdModele(diffusionAlerteDto.getProtoAlerte().getModeleMsgAlerte().getIdModele());
        diffusionAlerte.setIdDiffusion(cleDiffusionAlerte);

        //Affecter les objets relation
        diffusionAlerte.setPersonne(personneMapper.dePersonneDto(diffusionAlerteDto.getPersonne()));
        diffusionAlerte.setAlerte(alerteMapper.deAlerteDto(diffusionAlerteDto.getProtoAlerte().getAlerte()));
        diffusionAlerte.setModeleMsgAlerte(modeleMsgAlerteMapper.deModeleMsgAlerteDto(diffusionAlerteDto.getProtoAlerte().getModeleMsgAlerte()));

        //Enregistrement des diffusions
        diffusionAlerte = diffusionAlerteDao.save(diffusionAlerte);
        return diffusionAlerteMapper.deDiffusionAlerte(diffusionAlerte);
    }

    @Override
    public DiffusionAlerteDto modifierDiffusionAlerte(DiffusionAlerteDto diffusionAlerteDto) {
        DiffusionAlerte diffusionAlerte = diffusionAlerteMapper.deDiffusionAlerteDto(diffusionAlerteDto);

        //Définir la clé
        CleDiffusionAlerte cleDiffusionAlerte = new CleDiffusionAlerte();
        cleDiffusionAlerte.setIdPersonne(diffusionAlerteDto.getPersonne().getIdPersonne());
        cleDiffusionAlerte.setIdAlerte(diffusionAlerteDto.getAlerte().getIdAlerte());
        cleDiffusionAlerte.setIdModele(diffusionAlerteDto.getModeleMsgAlerte().getIdModele());
        diffusionAlerte.setIdDiffusion(cleDiffusionAlerte);

        //Affecter les objets relation
        diffusionAlerte.setPersonne(personneMapper.dePersonneDto(diffusionAlerteDto.getPersonne()));
        diffusionAlerte.setAlerte(alerteMapper.deAlerteDto(diffusionAlerteDto.getAlerte()));
        diffusionAlerte.setModeleMsgAlerte(modeleMsgAlerteMapper.deModeleMsgAlerteDto(diffusionAlerteDto.getModeleMsgAlerte()));

        //Enregistrement des diffusions
        diffusionAlerte = diffusionAlerteDao.save(diffusionAlerte);
        return diffusionAlerteMapper.deDiffusionAlerte(diffusionAlerte);
    }

    @Override
    public void supprimerDiffusionAlerte(CleDiffusionAlerte cleDiffusionAlerte) {
        diffusionAlerteDao.deleteById(cleDiffusionAlerte);
    }

    @Override
    public void supprimerDiffusionAlerteSelonAlerte(long idAlerte) {
        Alerte alerte=alerteDao.findById(idAlerte);
        diffusionAlerteDao.deleteByAlerte(alerte);
    }
}
