package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.OrdreDao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.standard.TarificationOrdinaireDao;
import com.ged.dao.titresciel.DepositaireDao;
import com.ged.dao.titresciel.RegistraireDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.Ordre;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.entity.titresciel.Registraire;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.opcciel.OrdreMapper;
import com.ged.projection.AvisOperationProjection;
import com.ged.projection.OrdreProjection;
import com.ged.projection.TarificationProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OrdreService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrdreServiceImpl implements OrdreService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final LibraryDao libraryDao;
    private final TitreDao titreDao;
    private final OrdreDao ordreDao;
    private final RegistraireDao registraireDao;
    private final DepositaireDao depositaireDao;
    private final OrdreMapper ordreMapper;
    private final TarificationOrdinaireDao  tarificationOrdinaireDao;


    public OrdreServiceImpl(PersonneDao personneDao, OpcvmDao opcvmDao, LibraryDao libraryDao, TitreDao titreDao, OrdreDao ordreDao, RegistraireDao registraireDao, DepositaireDao depositaireDao, OrdreMapper ordreMapper, TarificationOrdinaireDao tarificationOrdinaireDao){
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.libraryDao = libraryDao;
        this.titreDao = titreDao;
        this.ordreDao = ordreDao;
        this.registraireDao = registraireDao;
        this.depositaireDao = depositaireDao;
        this.ordreMapper = ordreMapper;
        this.tarificationOrdinaireDao = tarificationOrdinaireDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(Long idOpcvm,DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Ordre> OrdrePage;
           /* if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                planPage = planDao.rechercher(
                        parameters.getSearch().getValue(),
                        pageable);
            } else {*/
            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            OrdrePage = ordreDao.findByOpcvmAndEstEnvoyeAndSupprimer(opcvm,false,false,pageable);
            //}

            List<OrdreDto> content = OrdrePage.getContent().stream().map(ordreMapper::deOrdre).collect(Collectors.toList());
            DataTablesResponse<OrdreDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)OrdrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)OrdrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des ordre de bourse par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e)
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
            //Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            List<OrdreDto> OrdreDtos = ordreDao.findByOpcvmAndEstEnvoyeAndSupprimer(opcvm,false,false).stream().map(ordreMapper::deOrdre).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des ordres de bourse",
                    HttpStatus.OK,
                    OrdreDtos);
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
    public Ordre afficherSelonId(Long id) {
        return ordreDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Plan.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Ordre de bourse dont ID = " + id,
                    HttpStatus.OK,
                    ordreMapper.deOrdre(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(OrdreDto ordreDto) {
        try {
            String sortie="";
            var q = em.createStoredProcedureQuery("[OrdreDeBourse].[PS_Ordre_IP_New]");

            q.registerStoredProcedureParameter("idOperation", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("codeNatureOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("libelleOperation", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurFormule", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("valeurCodeAnalytique", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdOrdre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTitre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("role", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOrdre", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("statut", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTypeOrdre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteLimite", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdIntervenant", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateEnvoi", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateLimite", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("coursLimite", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("accepterPerte", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estEnvoye", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionPlace", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionSGI", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionDepositaire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("TAF", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IRVM", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("interet", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("plusOuMoinsValue", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteExecute", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantNet", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantBrut", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commentaires", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idIntervenant_New", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            q.setParameter("idOperation", 0);
            q.setParameter("idSeance", ordreDto.getIdSeance());
            q.setParameter("codeNatureOperation", ordreDto.getNatureOperation().getCodeNatureOperation());
            q.setParameter("libelleOperation", ordreDto.getLibelleOperation());
            q.setParameter("valeurFormule",ordreDto.getValeurFormule());
            q.setParameter("valeurCodeAnalytique",ordreDto.getValeurCodeAnalytique());
            q.setParameter("IdOrdre",ordreDto.getIdOrdre());
            q.setParameter("IdOpcvm", ordreDto.getOpcvm().getIdOpcvm());
            q.setParameter("IdTitre", ordreDto.getTitre().getIdTitre());
            q.setParameter("role", ordreDto.getRole());
            q.setParameter("dateOrdre", ordreDto.getDateOrdre());
            q.setParameter("statut", ordreDto.getStatut());
            q.setParameter("IdTypeOrdre", ordreDto.getTypeOrdre().getIdTypeOrdre());
            q.setParameter("quantiteLimite", ordreDto.getQuantiteLimite());
            q.setParameter("IdIntervenant", ordreDto.getPersonne().getIdPersonne());
            q.setParameter("dateEnvoi", ordreDto.getDateEnvoi());
            q.setParameter("dateLimite", ordreDto.getDateLimite());
            q.setParameter("coursLimite", ordreDto.getCoursLimite());
            q.setParameter("accepterPerte", ordreDto.getAccepterPerte());
            q.setParameter("estEnvoye", ordreDto.getEstEnvoye());
            q.setParameter("commissionPlace", ordreDto.getCommissionPlace());
            q.setParameter("commissionSGI", ordreDto.getCommissionSGI());
            q.setParameter("commissionDepositaire", ordreDto.getCommissionDepositaire());
            q.setParameter("TAF", ordreDto.gettAF());
            q.setParameter("IRVM", ordreDto.getiRVM());
            q.setParameter("interet", ordreDto.getInteret());
            q.setParameter("plusOuMoinsValue",ordreDto.getPlusOuMoinsValue());
            q.setParameter("quantiteExecute", ordreDto.getQuantiteExecute());
            q.setParameter("montantNet", ordreDto.getMontantNet());
            q.setParameter("montantBrut", ordreDto.getMontantBrut());
            q.setParameter("commentaires", ordreDto.getCommentaires());
            q.setParameter("idIntervenant_New", ordreDto.getPersonne().getIdPersonne());

            q.setParameter("userLogin", ordreDto.getUserLogin());
            q.setParameter("dateDernModifClient",LocalDateTime.now());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);
            String[] s=new String[20];
            try
            {
                q.execute();
                String result=(String) q.getOutputParameterValue("Sortie");
                s=result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            }
            catch(Exception e){

            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    s[s.length-1]);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> calculer(OrdreDto ordreDto) {
        try {
            String sortie="";
            BigDecimal montantBrut =BigDecimal.valueOf(0);
            BigDecimal interetCourru = BigDecimal.valueOf(0);
            String codeRole = "";
            BigDecimal commissionDepo = BigDecimal.valueOf(0);
            BigDecimal commissionPlace = BigDecimal.valueOf(0);
            BigDecimal commissionSGI = BigDecimal.valueOf(0);
            BigDecimal irvm = BigDecimal.valueOf(0);
            BigDecimal tafCommissionSGI = BigDecimal.valueOf(0);
            BigDecimal montantNet = BigDecimal.valueOf(0);
            BigDecimal plusOuMoinsValue = BigDecimal.valueOf(0);
            BigDecimal quantiteDisponible=BigDecimal.valueOf(10000000);
            codeRole=ordreDto.getRole();

            Double coursLimite=Double.valueOf(0);
            Double quantiteLimite=Double.valueOf(0);
            if(ordreDto.getCoursLimite()!=null){
                coursLimite=Double.valueOf(ordreDto.getCoursLimite().toString());
            }
            if(ordreDto.getQuantiteLimite()!=null){
                quantiteLimite=Double.valueOf(ordreDto.getQuantiteLimite().toString());
            }
            montantBrut = new BigDecimal(BigDecimal.valueOf(coursLimite
                    * quantiteLimite).toString()).setScale(0);

            if(ordreDto.getTitre()!=null && ordreDto.getTitre().getIdTitre()!=null){
                Titre titre=titreDao.findById(ordreDto.getTitre().getIdTitre()).orElseThrow();
                String codeClasseTitre=titre.getTypeTitre().getClasseTitre().getCodeClasseTitre().trim();

                String codePlace=titre.getPlace().getCodePlace().trim();
                if (codeClasseTitre.trim().toUpperCase().equals("OBLIGATION")||
                        codeClasseTitre.trim().toUpperCase().equals("TCN"))
                {
                    interetCourru = new BigDecimal(Double.valueOf(libraryDao.interetCouru(titre.getIdTitre(),ordreDto.getDateOrdre(), false,null).toString()) *
                            Double.valueOf(ordreDto.getQuantiteLimite().toString())).setScale(0,RoundingMode.HALF_UP);
                }
                //DEPOSITAIRE
                Long idDepositaire=depositaireDao.findByIdPersonne(titre.getDepositaire().getIdPersonne()).getIdDepositaire();
                TarificationProjection objTOD = tarificationOrdinaireDao.afficherTarificationD(codeClasseTitre,idDepositaire,codeRole);
                if (objTOD != null)
                {
                    if (objTOD.getBorneInferieur() < montantBrut.doubleValue() &&
                            montantBrut.doubleValue() < objTOD.getBorneSuperieur())
                    {
                        commissionDepo = new BigDecimal(
                                Double.valueOf((montantBrut.doubleValue() + interetCourru.doubleValue())) * objTOD.getTaux() / 100).setScale(0);
                    }
                    else
                    {
                        commissionDepo =new BigDecimal(objTOD.getForfait());
                    }
                }

                //PLACE
                TarificationProjection objTOP = tarificationOrdinaireDao.afficherTarificationP(codeClasseTitre, codePlace,codeRole);
                if (objTOP != null)
                {
                    if (objTOP.getBorneInferieur() < montantBrut.doubleValue() &&
                            montantBrut.doubleValue() < objTOP.getBorneSuperieur())
                    {
                        commissionPlace= new BigDecimal(
                                Double.valueOf((montantBrut.doubleValue() + interetCourru.doubleValue())) * objTOP.getTaux() / 100).setScale(0);
                    }
                    else
                    {
                        commissionPlace =new BigDecimal(objTOP.getForfait());
                    }
                }
                //SGI
                Registraire registraire=registraireDao.findByIdPersonne(ordreDto.getPersonne().getIdPersonne());
                TarificationProjection objTOS = tarificationOrdinaireDao.afficherTarificationS(codeClasseTitre,
                        registraire.getIdRegistraire(),codeRole,ordreDto.getOpcvm().getIdOpcvm());
                if (objTOS != null)
                {
                    if (objTOS.getBorneInferieur() < montantBrut.doubleValue() &&
                            montantBrut.doubleValue() < objTOS.getBorneSuperieur())
                    {
                        commissionSGI= new BigDecimal(
                                Double.valueOf((montantBrut.doubleValue() + interetCourru.doubleValue())) * objTOS.getTaux() / 100).setScale(0);
                    }
                    else
                    {
                        commissionSGI =new BigDecimal(objTOS.getForfait());
                    }
                }

                //IRVM
                if (codeRole.trim().toUpperCase().equals("VENTE"))
                {
                    //calculer apres le montant de l'irvm après détermination de l'IRVM

                    //calcul du montant net
                    montantNet = new BigDecimal(montantBrut.doubleValue() + interetCourru.doubleValue() - commissionPlace.doubleValue()
                            - commissionSGI.doubleValue() - tafCommissionSGI.doubleValue() -
                            commissionDepo.doubleValue() - irvm.doubleValue());
                }
                else
                {
                    montantNet =new BigDecimal(montantBrut.doubleValue()
                            + commissionPlace.doubleValue() + commissionSGI.doubleValue() + tafCommissionSGI.doubleValue() +
                            commissionDepo.doubleValue() + interetCourru.doubleValue());
                }

                //détermination dela quantité disponible pour le titre
                quantiteDisponible = libraryDao.quantiteReelTitre(ordreDto.getOpcvm().getIdOpcvm(), titre.getIdTitre(), ordreDto.getDateOrdre());
            }

            ordreDto.setMontantBrut(montantBrut);
            ordreDto.setMontantNet(montantNet);
            ordreDto.setPlusOuMoinsValue(plusOuMoinsValue);
            ordreDto.setCommissionDepositaire(commissionDepo);
            ordreDto.setCommissionPlace(commissionPlace);
            ordreDto.setCommissionSGI(commissionSGI);
            ordreDto.setiRVM(irvm);
            ordreDto.setInteret(interetCourru.doubleValue());
            ordreDto.settAF(tafCommissionSGI);
            ordreDto.setQuantiteDisponible(quantiteDisponible);

            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    ordreDto);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(OrdreDto ordreDto) {
        try {
            String sortie = "";
            var q = em.createStoredProcedureQuery("[OrdreDeBourse].[PS_Ordre_UP_New]");

            q.registerStoredProcedureParameter("IdOrdre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTitre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("role", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateOrdre", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("statut", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdTypeOrdre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteLimite", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IdIntervenant", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateEnvoi", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateLimite", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("coursLimite", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("accepterPerte", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("estEnvoye", Boolean.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionPlace", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionSGI", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commissionDepositaire", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("TAF", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("IRVM", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("interet", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("plusOuMoinsValue", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("quantiteExecute", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantNet", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("montantBrut", BigDecimal.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("commentaires", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("NumLigne", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.IN);

            q.setParameter("IdOrdre", ordreDto.getIdOrdre());
            q.setParameter("IdOpcvm", ordreDto.getOpcvm().getIdOpcvm());
            q.setParameter("IdTitre", ordreDto.getTitre().getIdTitre());
            q.setParameter("role", ordreDto.getRole());
            q.setParameter("dateOrdre", ordreDto.getDateOrdre());
            q.setParameter("statut", ordreDto.getStatut());
            q.setParameter("IdTypeOrdre", ordreDto.getTypeOrdre().getIdTypeOrdre());
            q.setParameter("quantiteLimite", ordreDto.getQuantiteLimite());
            q.setParameter("IdIntervenant", ordreDto.getPersonne().getIdPersonne());
            q.setParameter("dateEnvoi", ordreDto.getDateEnvoi());
            q.setParameter("dateLimite", ordreDto.getDateLimite());
            q.setParameter("coursLimite", ordreDto.getCoursLimite());
            q.setParameter("accepterPerte", ordreDto.getAccepterPerte());
            q.setParameter("estEnvoye", ordreDto.getEstEnvoye());
            q.setParameter("commissionPlace", ordreDto.getCommissionPlace());
            q.setParameter("commissionSGI",ordreDto.getCommissionSGI());
            q.setParameter("commissionDepositaire", ordreDto.getCommissionDepositaire());
            q.setParameter("TAF", ordreDto.gettAF());
            q.setParameter("IRVM", ordreDto.getiRVM());
            q.setParameter("interet", ordreDto.getInteret());
            q.setParameter("plusOuMoinsValue", ordreDto.getPlusOuMoinsValue());
            q.setParameter("quantiteExecute", ordreDto.getQuantiteLimite());
            q.setParameter("montantNet", ordreDto.getMontantNet());
            q.setParameter("montantBrut", ordreDto.getMontantBrut());
            q.setParameter("commentaires", ordreDto.getCommentaires());
            q.setParameter("NumLigne", 0);
            q.setParameter("userLogin", ordreDto.getUserLogin());
            q.setParameter("dateDernModifClient", ordreDto.getDateDernModifClient());
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);
            String[] s = new String[20];
            try {
                q.executeUpdate();
                String result = (String) q.getOutputParameterValue("Sortie");
                s = result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            } catch (Exception e) {

            }
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> validation(Long[] id,String userLogin) {
        try {
            String sortie = "";
            for(int i=0;i<id.length;i++){
                var q = em.createStoredProcedureQuery("[OrdreDeBourse].[PS_Ordre_UP1_New]");

                q.registerStoredProcedureParameter("IdOrdre", Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("statut", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("estEnvoye", Boolean.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

                q.setParameter("IdOrdre", id[i]);
                q.setParameter("statut", "ENVOYE");
                q.setParameter("estEnvoye", true);
                q.setParameter("userLogin", userLogin);
                q.setParameter("CodeLangue", "fr-FR");
                q.setParameter("Sortie",sortie);
                String[] s = new String[20];
                try {
                    q.executeUpdate();
                    String result = (String) q.getOutputParameterValue("Sortie");
                    s = result.split("#");
                    //System.out.println("idOperation="+s[s.length-1]);
                } catch (Exception e) {

                }

            }
            return ResponseHandler.generateResponse(
                    "Validation effectuée avec succès !",
                    HttpStatus.OK,
                    null);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> impressionOrdreBourse(Long idOpcvm, Long idSeance) {
        try {
            List<OrdreProjection> ordreProjections=ordreDao.afficherListeOrdre(idOpcvm,idSeance);
            return ResponseHandler.generateResponse(
                    "Impression effectuée avec succès !",
                    HttpStatus.OK,
                    ordreProjections);

        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> jaspertReportOrdreBourse(String[] numeroOrdre, HttpServletResponse response) throws IOException, JRException {
        String num="";
        for(int i=0;i<numeroOrdre.length;i++){
            if(i==0){
                num=numeroOrdre[i];
            }
            else
                num=num+";"+numeroOrdre[i];
        }
        List<OrdreProjection> list=libraryDao.listeOrdreApercu(num);
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());

        parameters.put("letterDate", letterDate);
        File file = ResourceUtils.getFile("classpath:ordreDeBourse.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                list);
    }

    @Override
    public ResponseEntity<Object> supprimer(Long idOrdre,String userLogin) {
        try {
            String sortie = "";
            var q = em.createStoredProcedureQuery("[OrdreDeBourse].[PS_Ordre_DP_New]");

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOrdre", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

            q.setParameter("userLogin", userLogin);
            q.setParameter("idOrdre",idOrdre);
            q.setParameter("CodeLangue", "fr-FR");
            q.setParameter("Sortie",sortie);
            String[] s = new String[20];
            try {
                q.execute();
                String result = (String) q.getOutputParameterValue("Sortie");
                s = result.split("#");
                //System.out.println("idOperation="+s[s.length-1]);
            } catch (Exception e) {

            }
            return ResponseHandler.generateResponse(
                    "Suppression effectuée avec succès !",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
