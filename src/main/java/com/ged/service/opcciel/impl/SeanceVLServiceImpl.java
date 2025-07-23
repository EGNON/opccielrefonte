package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.ParametreJourFerieDao;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.dto.opcciel.SeanceVLDto;
import com.ged.entity.standard.ParametreJourFerie;
import com.ged.mapper.opcciel.SeanceOpcvmMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.MiseEnAffectationService;
import com.ged.service.opcciel.SeanceVLService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SeanceVLServiceImpl implements SeanceVLService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final SeanceOpcvmDao seanceOpcvmDao;
    private final ParametreJourFerieDao parametreJourFerieDao;
    private final SeanceOpcvmMapper seanceOpcvmMapper;
    private final MiseEnAffectationService miseEnAffectationService;
    private final LibraryDao libraryDao;
    @PersistenceContext
    EntityManager em;
    public SeanceVLServiceImpl(SeanceOpcvmDao seanceOpcvmDao, ParametreJourFerieDao parametreJourFerieDao, SeanceOpcvmMapper seanceOpcvmMapper, MiseEnAffectationService miseEnAffectationService, LibraryDao libraryDao){
        this.seanceOpcvmDao = seanceOpcvmDao;
        this.parametreJourFerieDao = parametreJourFerieDao;

        this.seanceOpcvmMapper = seanceOpcvmMapper;
        this.miseEnAffectationService = miseEnAffectationService;
        this.libraryDao = libraryDao;
    }
    @Override
    public ResponseEntity<Object> cloturerSeance(String userLogin, Long idOpcvm) {
        try {
            if (libraryDao.verificationMiseAffectationEnAttente(idOpcvm).size() != 0) {
                return ResponseHandler.generateResponse(
                        "Vérification étape",
                        HttpStatus.OK,
                        "Veuillez éffectuer l'enrégistrement de la décision du conseil avant de continuer.");

            } else {
                SeanceOpcvmDto seanceOpcvmDto = seanceOpcvmMapper.deSeanceOpcvm(seanceOpcvmDao.afficherSeanceEnCours(idOpcvm));
                if ((seanceOpcvmDto.getIdSeanceOpcvm().getIdSeance() == 0 && seanceOpcvmDto.getNiveau() == 7) ||
                        (seanceOpcvmDto.getIdSeanceOpcvm().getIdSeance() != 0 && seanceOpcvmDto.getNiveau() == 13)) {
                    LocalDateTime dateFermeture = seanceOpcvmDto.getDateFermeture();
                    String sortie = "";
                    List<ParametreJourFerie> joursFeries = parametreJourFerieDao.findAll();
                    LocalDate dateFermetureCalculee;

                    if (dateFermeture.getDayOfWeek() == DayOfWeek.FRIDAY) {
                        dateFermetureCalculee = dateFermeture.toLocalDate().plusDays(3);
                    } else {
                        dateFermetureCalculee = dateFermeture.toLocalDate().plusDays(1);
                    }

                    while (true) {
                        LocalDate finalDateFermetureCalculee = dateFermetureCalculee;
                        boolean isJourFerie = joursFeries.stream().anyMatch(jf ->
                                jf.getDate().toLocalDate().equals(finalDateFermetureCalculee) ||
                                        (jf.isEstAnnuel() && jf.getDate().getDayOfMonth() == finalDateFermetureCalculee.getDayOfMonth()
                                                && jf.getDate().getMonth() == finalDateFermetureCalculee.getMonth())
                        );

                        if (isJourFerie) {
                            dateFermetureCalculee = dateFermetureCalculee.plusDays(1);
                            if (dateFermetureCalculee.getDayOfWeek() == DayOfWeek.SATURDAY) {
                                dateFermetureCalculee = dateFermetureCalculee.plusDays(2);
                            }
                        } else {
                            break;
                        }
                    }

                    var q = em.createStoredProcedureQuery("[Parametre].[PS_SeanceOpcvm_IP]");
                    q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("dateSeance", LocalDateTime.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("dateOuverture", LocalDateTime.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("dateFermeture", LocalDateTime.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("genere", Boolean.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("typeSeance", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("valeurLiquidative", BigDecimal.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("estEnCours", Boolean.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
                    q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


                    q.setParameter("idOpcvm", idOpcvm);
                    q.setParameter("idSeance", seanceOpcvmDto.getIdSeanceOpcvm().getIdSeance() + 1);
                    q.setParameter("dateSeance", LocalDateTime.now());
                    q.setParameter("dateOuverture", seanceOpcvmDto.getDateFermeture());
                    q.setParameter("dateFermeture", dateFermetureCalculee.atTime(LocalTime.from(LocalDateTime.now())));
                    q.setParameter("genere", false);
                    q.setParameter("typeSeance", "");
                    q.setParameter("valeurLiquidative", BigDecimal.ZERO);
                    q.setParameter("estEnCours", true);
                    q.setParameter("userLogin", userLogin);
                    q.setParameter("dateDernModifClient", LocalDateTime.now());
                    q.setParameter("CodeLangue", "fr-FR");
                    q.setParameter("Sortie", sortie);
                    q.execute();

                    // Clôturer l’ancienne séance
                    seanceOpcvmDao.modifier(seanceOpcvmDto.getIdSeanceOpcvm().getIdOpcvm(), seanceOpcvmDto.getIdSeanceOpcvm().getIdSeance());

                    // ici vous pouvez persister dans la BDD si besoin

                    return ResponseHandler.generateResponse(
                            "Vérification étape",
                            HttpStatus.OK,
                            "Clôture effectuée avec succès");
                }
                else
                {
                    return ResponseHandler.generateResponse(
                            "Vérification étape",
                            HttpStatus.OK,
                            "Toutes les étapes ne sont pas validées");
                }
            }

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
    public ResponseEntity<Object> afficherTous(Long idOpcvm) {
        try {
                List<SeanceVLDto> list=new ArrayList<>();
                SeanceVLDto seanceVLDto=new SeanceVLDto();

                SeanceOpcvmDto seanceOpcvmDto=seanceOpcvmMapper.deSeanceOpcvm(seanceOpcvmDao.afficherSeanceEnCours(idOpcvm));
                if (seanceOpcvmDto != null)
                {
                    String numSeance = seanceOpcvmDto.getIdSeanceOpcvm().getIdSeance().toString();
                    BigDecimal VL= seanceOpcvmDto.getValeurLiquidative();
                    LocalDateTime dateOuverture = seanceOpcvmDto.getDateOuverture();
                    LocalDateTime dateFermeture = seanceOpcvmDto.getDateFermeture();


                    //ajout des étapes de cloture d'une séance
                    if (numSeance == "0")//il s'agit de la constitution de capital
                    {
                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("CONSTITUTION DU CAPITAL");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setNumero(1L);
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION DE LA CONSTITUTION DU CAPITAL");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(2L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 1 (CC)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(3L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 2 (CC)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(4L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VALORISATION DES POSTES COMPTABLES");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(5L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 1 (PC)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(6L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 2 (PC)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(7L);
                        list.add(seanceVLDto);

                    }
                    else
                    {
                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("GENERATION DIFFERENCES D'ESTIMATION");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(1L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 1 (DE)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(2L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 2 (DE)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(3L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 1 JEUX D'ECRITURE(DE)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(4L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 2 JEUX D'ECRITURE(DE)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(5L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("AMORTISSEMENT DES CHARGES");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(6L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 1 (CHARGES)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(7L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 2 (CHARGES)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(8L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 1 JEUX D'ECRITURE(CHARGES)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(9L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 2 JEUX D'ECRITURE(CHARGES)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(10L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VALORISATION DES POSTES COMPTABLES");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(11L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 1 (PC)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(12L);
                        list.add(seanceVLDto);

                        seanceVLDto=new SeanceVLDto();
                        seanceVLDto.setEtat(false);
                        seanceVLDto.setLibelle("VERIFICATION NIVEAU 2 (PC)");
                        seanceVLDto.setNumSeance(Long.valueOf(numSeance));
                        seanceVLDto.setVl(VL);
                        seanceVLDto.setDateOuverture(dateOuverture);
                        seanceVLDto.setDateFermeture(dateFermeture);
                        seanceVLDto.setNumero(13L);
                        list.add(seanceVLDto);

                    }
                    for (int i = 0; i < seanceOpcvmDto.getNiveau(); i++)
                    {
                        list.get(i).setEtat(true);
                    }
                }

            return ResponseHandler.generateResponse(
                    "Vérification étape",
                    HttpStatus.OK,
                    list);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
