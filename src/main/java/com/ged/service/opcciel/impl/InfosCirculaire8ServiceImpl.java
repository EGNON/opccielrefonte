package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.InfosCirculaire8Dao;
import com.ged.dao.standard.PersonneDao;
import com.ged.dao.standard.TarificationOrdinaireDao;
import com.ged.dao.titresciel.DepositaireDao;
import com.ged.dao.titresciel.RegistraireDao;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.InfosCirculaire8Dto;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.InfosCirculaire8;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.entity.titresciel.Registraire;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.opcciel.InfosCirculaire8Mapper;
import com.ged.projection.TarificationProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.InfosCirculaire8Service;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class InfosCirculaire8ServiceImpl implements InfosCirculaire8Service {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    @PersistenceContext
    EntityManager em;
    private final PersonneDao personneDao;
    private final OpcvmDao opcvmDao;
    private final LibraryDao libraryDao;
    private final TitreDao titreDao;
    private final InfosCirculaire8Dao InfosCirculaire8Dao;
    private final RegistraireDao registraireDao;
    private final DepositaireDao depositaireDao;
    private final InfosCirculaire8Mapper InfosCirculaire8Mapper;
    private final TarificationOrdinaireDao  tarificationOrdinaireDao;


    public InfosCirculaire8ServiceImpl(PersonneDao personneDao, OpcvmDao opcvmDao, LibraryDao libraryDao, TitreDao titreDao, InfosCirculaire8Dao InfosCirculaire8Dao, RegistraireDao registraireDao, DepositaireDao depositaireDao, InfosCirculaire8Mapper InfosCirculaire8Mapper, TarificationOrdinaireDao tarificationOrdinaireDao){
        this.personneDao = personneDao;
        this.opcvmDao = opcvmDao;
        this.libraryDao = libraryDao;
        this.titreDao = titreDao;
        this.InfosCirculaire8Dao = InfosCirculaire8Dao;
        this.registraireDao = registraireDao;
        this.depositaireDao = depositaireDao;
        this.InfosCirculaire8Mapper = InfosCirculaire8Mapper;
        this.tarificationOrdinaireDao = tarificationOrdinaireDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(Long idOpcvm,DatatableParameters parameters) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libellePlan");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<InfosCirculaire8> InfosCirculaire8Page;
           /* if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                planPage = planDao.rechercher(
                        parameters.getSearch().getValue(),
                        pageable);
            } else {*/
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            InfosCirculaire8Page = InfosCirculaire8Dao.afficherListe(idOpcvm,pageable);
            //}

            List<InfosCirculaire8Dto> content = InfosCirculaire8Page.getContent().stream().map(InfosCirculaire8Mapper::deInfosCirculaire8).collect(Collectors.toList());
            DataTablesResponse<InfosCirculaire8Dto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)InfosCirculaire8Page.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)InfosCirculaire8Page.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des InfosCirculaire8 par page datatable",
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
//            Opcvm opcvm=opcvmDao.findById(idOpcvm).orElseThrow();
            List<InfosCirculaire8Dto> InfosCirculaire8Dtos = InfosCirculaire8Dao.afficherListe(idOpcvm).stream().map(InfosCirculaire8Mapper::deInfosCirculaire8).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des InfosCirculaire8s ",
                    HttpStatus.OK,
                    InfosCirculaire8Dtos);
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
    public InfosCirculaire8 afficherSelonId(Long id) {
        return InfosCirculaire8Dao.findById(id).orElseThrow(() -> new EntityNotFoundException(Plan.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "InfosCirculaire8 de bourse dont ID = " + id,
                    HttpStatus.OK,
                    InfosCirculaire8Mapper.deInfosCirculaire8(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(InfosCirculaire8Dto InfosCirculaire8Dto) {
        try {
            String sortie="";
            var q = em.createStoredProcedureQuery("[Impressions].[PS_InfosCirculaire8_IP]");

            q.registerStoredProcedureParameter("dateDebut", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateFin", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("evenementMarquant", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("analysePortefeuille1", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("analysePortefeuille2", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            q.setParameter("dateDebut", InfosCirculaire8Dto.getDateDebut());
            q.setParameter("dateFin", InfosCirculaire8Dto.getDateFin());
            q.setParameter("evenementMarquant", InfosCirculaire8Dto.getEvenementMarquant());
            q.setParameter("analysePortefeuille1", InfosCirculaire8Dto.getAnalysePortefeuille1());
            q.setParameter("analysePortefeuille2", InfosCirculaire8Dto.getAnalysePortefeuille2());
            q.setParameter("idOpcvm", InfosCirculaire8Dto.getOpcvm().getIdOpcvm());

            q.setParameter("userLogin", InfosCirculaire8Dto.getUserLogin());
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
    public ResponseEntity<Object> modifier(InfosCirculaire8Dto InfosCirculaire8Dto) {
        try {
            String sortie = "";
            var q = em.createStoredProcedureQuery("[Impressions].[PS_InfosCirculaire8_UP_New]");
            q.registerStoredProcedureParameter("dateDebut", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateFin", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("evenementMarquant", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("analysePortefeuille1", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("analysePortefeuille2", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("idOpcvm", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("NumLigne", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("dateDernModifClient", LocalDateTime.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);


            q.setParameter("dateDebut", InfosCirculaire8Dto.getDateDebut());
            q.setParameter("dateFin", InfosCirculaire8Dto.getDateFin());
            q.setParameter("evenementMarquant", InfosCirculaire8Dto.getEvenementMarquant());
            q.setParameter("analysePortefeuille1", InfosCirculaire8Dto.getAnalysePortefeuille1());
            q.setParameter("analysePortefeuille2", InfosCirculaire8Dto.getAnalysePortefeuille2());
            q.setParameter("idOpcvm", InfosCirculaire8Dto.getOpcvm().getIdOpcvm());
            q.setParameter("NumLigne", InfosCirculaire8Dto.getNumLigne());

            q.setParameter("userLogin", InfosCirculaire8Dto.getUserLogin());
            q.setParameter("dateDernModifClient",LocalDateTime.now());
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
    public ResponseEntity<Object> supprimer(Long id,String userLogin) {
        try {
            String sortie = "";
            var q = em.createStoredProcedureQuery("[Impressions].[PS_InfosCirculaire8_DP_New]");

            q.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("numLigne", Long.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("CodeLangue", String.class, ParameterMode.IN);
            q.registerStoredProcedureParameter("Sortie", String.class, ParameterMode.OUT);

            q.setParameter("userLogin", userLogin);
            q.setParameter("numLigne",id);
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
