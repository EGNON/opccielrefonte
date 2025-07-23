package com.ged.service.opcciel.impl;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.OpcvmDao;
import com.ged.dao.opcciel.SeanceOpcvmDao;
import com.ged.dao.opcciel.comptabilite.ExerciceDao;
import com.ged.dao.opcciel.comptabilite.PlanDao;
import com.ged.dao.opcciel.comptabilite.PosteComptableDao;
import com.ged.dao.opcciel.comptabilite.PosteComptableSeanceOpcvmDao;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.OpcvmDto;
import com.ged.dto.opcciel.SeanceOpcvmDto;
import com.ged.dto.opcciel.comptabilite.ExerciceDto;
import com.ged.dto.opcciel.comptabilite.PosteComptableDto;
import com.ged.dto.opcciel.comptabilite.PosteComptableSeanceOpcvmDto;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.entity.opcciel.comptabilite.ClePosteComptableSeanceOpcvm;
import com.ged.entity.opcciel.comptabilite.PosteComptableSeanceOpcvm;
import com.ged.mapper.opcciel.*;
import com.ged.projection.CodePosteComptableProjection;
import com.ged.projection.SoldeCompteFormuleProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationService;
import com.ged.service.opcciel.PosteComptableSeanceOpcvmService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
//@Transactional
public class PosteComptableSeanceOpcvmServiceImpl implements PosteComptableSeanceOpcvmService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final PosteComptableSeanceOpcvmDao PosteComptableSeanceOpcvmDao;
    private final PlanDao planDao;
    private final OperationService operationService;
    private final OperationMapper operationMapper;
    private final LibraryDao libraryDao;
    private final PosteComptableSeanceOpcvmMapper PosteComptableSeanceOpcvmMapper;
    private final PosteComptableMapper posteComptableMapper;
    private final PosteComptableDao posteComptableDao;
    private final PlanMapper planMapper;
    private final ExerciceDao exerciceDao;
    private final ExerciceMapper exerciceMapper;
    private final SeanceOpcvmDao seanceOpcvmDao;
    private final SeanceOpcvmMapper seanceOpcvmMapper;
    private final OpcvmMapper opcvmMapper;
    private final OpcvmDao opcvmDao;
    @PersistenceContext
    EntityManager em;

    public PosteComptableSeanceOpcvmServiceImpl(PosteComptableSeanceOpcvmDao PosteComptableSeanceOpcvmDao, PlanDao planDao, OperationService operationService, OperationMapper operationMapper, LibraryDao libraryDao, PosteComptableSeanceOpcvmMapper PosteComptableSeanceOpcvmMapper, PosteComptableMapper posteComptableMapper, PosteComptableDao posteComptableDao, PlanMapper planMapper, ExerciceDao exerciceDao, ExerciceMapper exerciceMapper, SeanceOpcvmDao seanceOpcvmDao, SeanceOpcvmMapper seanceOpcvmMapper, OpcvmMapper opcvmMapper, OpcvmDao opcvmDao){
        this.PosteComptableSeanceOpcvmDao = PosteComptableSeanceOpcvmDao;
        this.planDao = planDao;
        this.operationService = operationService;
        this.operationMapper = operationMapper;
        this.libraryDao = libraryDao;
        this.PosteComptableSeanceOpcvmMapper = PosteComptableSeanceOpcvmMapper;
        this.posteComptableMapper = posteComptableMapper;
        this.posteComptableDao = posteComptableDao;
        this.planMapper = planMapper;
        this.exerciceDao = exerciceDao;
        this.exerciceMapper = exerciceMapper;
        this.seanceOpcvmDao = seanceOpcvmDao;
        this.seanceOpcvmMapper = seanceOpcvmMapper;
        this.opcvmMapper = opcvmMapper;
        this.opcvmDao = opcvmDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(Long idOpcvm,Long idSeance) {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libellePosteComptableSeanceOpcvm");
//            Pageable pageable = PageRequest.of(
//                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
//            Page<PosteComptableSeanceOpcvm> PosteComptableSeanceOpcvmPage;
//            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
//                PosteComptableSeanceOpcvmPage = PosteComptableSeanceOpcvmDao.rechercher(
//                        parameters.getSearch().getValue(),
//                        pageable);
//            } else {
//                PosteComptableSeanceOpcvmPage = PosteComptableSeanceOpcvmDao.findBySupprimer(false,pageable);
//            }
//
//            List<PosteComptableSeanceOpcvmDto> content = PosteComptableSeanceOpcvmPage.getContent().stream().map(PosteComptableSeanceOpcvmMapper::dePosteComptableSeanceOpcvm).collect(Collectors.toList());
//            DataTablesResponse<PosteComptableSeanceOpcvmDto> dataTablesResponse = new DataTablesResponse<>();
//            dataTablesResponse.setDraw(parameters.getDraw());
//            dataTablesResponse.setRecordsFiltered((int)PosteComptableSeanceOpcvmPage.getTotalElements());
//            dataTablesResponse.setRecordsTotal((int)PosteComptableSeanceOpcvmPage.getTotalElements());
//            dataTablesResponse.setData(content);
            List<CodePosteComptableProjection> list=libraryDao.afficherCodePosteComptable(idOpcvm, idSeance, null, null);
            return ResponseHandler.generateResponse(
                    "Liste des postes comptables par page datatable",
                    HttpStatus.OK,
                    list);
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
    public Page<PosteComptableSeanceOpcvmDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
//            Sort sort = Sort.by(Sort.Direction.ASC,"libellePosteComptableSeanceOpcvm");
//            List<PosteComptableSeanceOpcvmDto> PosteComptableSeanceOpcvmDtos = PosteComptableSeanceOpcvmDao.findBySupprimerOrderByLibellePosteComptableSeanceOpcvmAsc(false).stream().map(PosteComptableSeanceOpcvmMapper::dePosteComptableSeanceOpcvm).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des postes comptables ",
                    HttpStatus.OK,
                    null);
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
    public PosteComptableSeanceOpcvm afficherSelonId(ClePosteComptableSeanceOpcvm clePosteComptableSeanceOpcvm) {
        return null;// PosteComptableSeanceOpcvmDao.findById(clePosteComptableSeanceOpcvm).orElseThrow(() -> new EntityNotFoundException(PosteComptableSeanceOpcvm.class, "code",clePosteComptableSeanceOpcvm.getCodePosteComptableSeanceOpcvm()+"-"+clePosteComptableSeanceOpcvm.getCodePlan()));
    }

    @Override
    public ResponseEntity<Object> afficher(ClePosteComptableSeanceOpcvm clePosteComptableSeanceOpcvm) {
        try {
            return ResponseHandler.generateResponse(
                    "Poste Comptable dont CODE = " + clePosteComptableSeanceOpcvm,
                    HttpStatus.OK,
                    null);//PosteComptableSeanceOpcvmMapper.dePosteComptableSeanceOpcvm(afficherSelonId(clePosteComptableSeanceOpcvm)));
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
    public ResponseEntity<Object> afficherSelonCodePosteComptableSeanceOpcvm(String codePosteComptableSeanceOpcvm) {
        try {
            return ResponseHandler.generateResponse(
                    "Poste Comptable dont CODE = " + codePosteComptableSeanceOpcvm,
                    HttpStatus.OK,
                    null);//PosteComptableSeanceOpcvmMapper.dePosteComptableSeanceOpcvm(PosteComptableSeanceOpcvmDao.findByCodePosteComptableSeanceOpcvm(codePosteComptableSeanceOpcvm)));
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
    public String calculerExpressionTraitee(String expression) {
        int i = 0;
        boolean isDecimalPart = false;
        char car;
        BigDecimal nbreEnTempon = BigDecimal.ZERO;
        double ct = 1.0;
        char opTp = '\0';
        String expresPriorite = "";
        int nbParantPrio = 0;
        BigDecimal retour = BigDecimal.ZERO;
        String chaineRetour = "";

        try {
            // Gestion des valeurs signées
            if (expression.startsWith("-")) {
                expression = "0" + expression;
            }

            // Ajout du caractère de fin
            expression = expression.trim().replace('.',',') + ".";

            for (i = 0; i < expression.length(); i++) {
                car = Character.toLowerCase(expression.charAt(i));

                // Si c'est un chiffre
                if (Character.isDigit(car)) {
                    if (!isDecimalPart) {
                        nbreEnTempon =new BigDecimal((nbreEnTempon.doubleValue()*BigDecimal.valueOf(10).doubleValue())+
                                BigDecimal.valueOf(Character.getNumericValue(car)).doubleValue());
                    } else {
                        if (ct < 1e14) {
                            ct *= 10;
                            nbreEnTempon =new BigDecimal(nbreEnTempon.doubleValue()+BigDecimal.valueOf(Character.getNumericValue(car) / ct).doubleValue());
                        }
                    }
                }

                // Partie décimale
//            car=Character.toLowerCase(String.valueOf(car).replace('.',',').charAt(0));
                if (car == ',') {
                    isDecimalPart = true;
                }

                // Si c'est un opérateur
                if (car == '/' || car == '*' || car == '+' || car == '-') {
                    isDecimalPart = false;
                    switch (opTp) {
                        case '/':
                            if (nbreEnTempon.compareTo(BigDecimal.ZERO) != 0) {
                                retour = new BigDecimal(retour.doubleValue()/nbreEnTempon.doubleValue());
                            } else {
                                return "#Erreur: Division par zéro#";
                            }
                            break;
                        case '*':
                            retour = new BigDecimal(retour.doubleValue()*nbreEnTempon.doubleValue());
                            break;
                        case '+':
                        case '-':
                            if (car == '/' || car == '*') {
                                // Gestion de priorité
                                expresPriorite = nbreEnTempon.toString() + car;
                                int j = i;
                                nbParantPrio = 0;

                                do {
                                    j++;
                                    String current = expression.substring(j, j + 1);
                                    if (current.equals("(")) nbParantPrio++;
                                    if (current.equals(")")) nbParantPrio--;
                                    expresPriorite += current;
                                } while ((j < expression.length() - 2) &&
                                        !((expression.charAt(j) == '+' || expression.charAt(j) == '-') && nbParantPrio == 0));

                                String eval = calculerExpressionTraitee(expresPriorite);
                                BigDecimal val = new BigDecimal(eval);
                                retour = (opTp == '+') ?  new BigDecimal(retour.doubleValue()+val.doubleValue()) : new BigDecimal(retour.doubleValue()-val.doubleValue());
                                i = j;
                                car = expression.charAt(j);
                                expresPriorite = "";
                            } else if (opTp == '+') {
                                retour =  new BigDecimal(retour.doubleValue()+nbreEnTempon.doubleValue());
                            } else {
                                retour =  new BigDecimal(retour.doubleValue()-nbreEnTempon.doubleValue());
                            }
                            break;
                        default:
                            retour = nbreEnTempon;
                            break;
                    }

                    opTp = car;
                    nbreEnTempon = BigDecimal.ZERO;
                    isDecimalPart = false;
                    ct = 1.0;
                }

                // Gestion des parenthèses
                if (car == '(') {
                    int nbreParant = 1;
                    int j = i;
                    expresPriorite = "";

                    do {
                        j++;
                        String current = expression.substring(j, j + 1);
                        if (!(current.equals(")") && nbreParant == 1)) {
                            expresPriorite += current;
                        }
                        if (current.equals("(")) nbreParant++;
                        if (current.equals(")")) nbreParant--;
                    } while (j < expression.length() - 2 && nbreParant != 0);

                    nbreEnTempon = new BigDecimal(calculerExpressionTraitee(expresPriorite));
                    i = j;
                    expresPriorite = "";
                }

                // Fin d'expression
                if (car == '.') {
                    switch (opTp) {
                        case '/':
                            if (nbreEnTempon.compareTo(BigDecimal.ZERO) != 0) {
                                retour =  new BigDecimal(retour.doubleValue()/nbreEnTempon.doubleValue());
                            } else {
                                return "#Erreur: Division par zéro#";
                            }
                            break;
                        case '*':
                            retour =  new BigDecimal(retour.doubleValue()*nbreEnTempon.doubleValue());
                            break;
                        case '+':
                            retour =  new BigDecimal(retour.doubleValue()+nbreEnTempon.doubleValue());
                            break;
                        case '-':
                            retour = new BigDecimal(retour.doubleValue()-nbreEnTempon.doubleValue());
                            break;
                        case '\0':
                            retour = nbreEnTempon;
                            break;
                    }
                }
            }

        } catch (Exception e) {
            return "#Erreur: Exception Interne#";
        }

        chaineRetour = retour.stripTrailingZeros().toPlainString();
        return chaineRetour;
    }

    public  String calculer(String expression, String codePlan, LocalDateTime dateEvalution,Long idOpcvm)
    {
        String test = interpreter(expression, codePlan, dateEvalution,idOpcvm);
        try
        {
            if (!test.contains("#Erreur#"))
            {
                test = calculerExpressionTraitee(test.trim());
                if (test.contains("Erreur"))
                {
                    test = "0";
                }
                test = new BigDecimal(test).setScale(4).toString();
            }
        }
        catch (Exception e ){ }

        return test;

    }
    public String interpreter(String expressionO, String codePlan, LocalDateTime dateEvaluation, Long idOpcvm) {
        String retour = "", codeVal = "", numCpte = "", carsAutorises = "0123456789,-+*/()";
        char car;
        int i, j, nbparant = 0;
        String codePoste = "", formuleCP = "", expression = "";

        try {
            // === Traitement des codes postes ===
            for (i = 0; i < expressionO.length(); i++) {
                car = Character.toLowerCase(expressionO.charAt(i));
                if (car == '_') {
                    j = i;
                    do {
                        codePoste += expressionO.substring(j, j + 1).trim();
                        j++;
                    } while (j < expressionO.length() && !"-+*/)".contains(expressionO.substring(j, j + 1)));

                    formuleCP = libraryDao.formuleDunCodePoste(codePoste);
                    codePoste = "";

                    String tempon = calculer(formuleCP, codePlan, dateEvaluation, idOpcvm);

                    if (!expression.isEmpty() && tempon.startsWith("-")) {
                        if (expression.endsWith("-")) {
                            expression = expression.substring(0, expression.length() - 1) + "+" + tempon.substring(1);
                        } else {
                            expression = expression.substring(0, expression.length() - 1) + tempon;
                        }
                    } else {
                        expression += tempon;
                    }
                    formuleCP = "";
                    i = j - 1;
                } else {
                    expression += String.valueOf(car).trim();
                }
            }

            // === Analyse de l'expression ===
            if (expression.isEmpty() || !"-+0123456789(ms".contains(expression.substring(0, 1).toLowerCase())) {
                return "#Erreur#0";
            }

            for (i = 0; i < expression.length(); i++) {
                car = Character.toLowerCase(expression.charAt(i));

                if ("0123456789,.-+*/()mcds".contains(String.valueOf(car))) {
                    if (Character.isDigit(car)) {
                        if (i + 1 < expression.length() && "(mcds".contains(String.valueOf(expression.charAt(i + 1)).toLowerCase())) {
                            return "#Erreur#" + i;
                        }
                    }

                    if (car == ',' || car=='.') {
                        if (i + 1 >= expression.length() || !Character.isDigit(expression.charAt(i + 1))) {
                            return "#Erreur#" + i;
                        }
                    }

                    if ("-+*/".contains(String.valueOf(car))) {
                        if (i + 1 >= expression.length() || !"0123456789ms(".contains(String.valueOf(expression.charAt(i + 1)).toLowerCase())) {
                            return "#Erreur#" + i;
                        }
                    }

                    if (car == 'm') {
                        if (i + 1 >= expression.length() || !"cd".contains(String.valueOf(expression.charAt(i + 1)).toLowerCase())) {
                            return "#Erreur#" + i;
                        }
                    }

                    if ("cds".contains(String.valueOf(car))) {
                        if (i + 1 >= expression.length() || !Character.isDigit(expression.charAt(i + 1))) {
                            return "#Erreur#" + i;
                        }
                    }

                    if (car == '(') nbparant++;
                    if (car == ')') nbparant--;
                } else {
                    return "#Erreur:caractère non autorisé#";
                }
            }

            if (nbparant != 0) {
                return "#Erreur:paranthèse(s) manquante(s)#";
            }

            // === Remplacement des opérations sur les comptes ===
            retour = "";
            i = 0;
            while (i < expression.length()) {
                car = expression.charAt(i);
                if (car == 'm' || car == 's') {
                    j = i;
                    codeVal = String.valueOf(car);
                    numCpte = "";

                    while (++j < expression.length()) {
                        char next = expression.charAt(j);
                        if ("+-/*)".contains(String.valueOf(next))) break;
                        if (codeVal.length() == 1 && car == 'm') {
                            codeVal += next;
                        } else {
                            numCpte += next;
                        }
                    }

//                    SoldeResult solde = soldeCompteService.getSolde(idOpcvm, numCpte.trim(), codePlan, dateEvaluation);
//                    if (solde.getSortie().contains("Erreur")) {
//                        return solde.getSortie();
//                    }
                    LocalDateTime dateTime = dateEvaluation;
                    Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                    List<SoldeCompteFormuleProjection> list = libraryDao.afficherSoldeCompteFormule(idOpcvm, numCpte, codePlan, null, date);
                    String sortie = (list.size() != 0) ? list.get(0).getSortie() : "";

                    String tempon = "";
                    switch (codeVal.toLowerCase()) {
                        case "mc":
                            tempon = String.valueOf(list.get(0).getSoldeCredit());
                            break;
                        case "md":
                            tempon = String.valueOf(list.get(0).getSoldeDebit());
                            break;
                        case "s":
                            tempon = String.valueOf(list.get(0).getSoldeReel());
                            break;
                        default:
                            return "#Erreur#" + i;
                    }

                    if (!retour.isEmpty() && tempon.startsWith("-") && retour.endsWith("-")) {
                        retour = retour.substring(0, retour.length() - 1) + "+" + tempon.substring(1);
                    } else {
                        retour += tempon;
                    }

                    codeVal = "";
                    numCpte = "";
                    i = j;
                } else {
                    retour += car;
                    i++;
                }
            }

        } catch (Exception e) {
            return "#Erreur Exception Interne#";
        }

        return retour.trim();
    }
    @Override
    public ResponseEntity<Object> valoriser(DifferenceEstimationRequest posteComptableSeanceOpcvm) {
        try {
            String etape=operationService.verifierEtape(posteComptableSeanceOpcvm.getNiveau(),posteComptableSeanceOpcvm.getIdOpcvm());
            if(etape.equals(""))
            {
                SeanceOpcvm seanceOpcvm=seanceOpcvmDao.afficherSeance(posteComptableSeanceOpcvm.getIdOpcvm(),posteComptableSeanceOpcvm.getIdSeance());
                if (seanceOpcvm != null )
                {
                    List<PosteComptableSeanceOpcvmDto> lstPCSO = new ArrayList<>();
                    BigDecimal valeurCP = BigDecimal.ZERO;
                    ExerciceDto exerciceDto=exerciceMapper.deExercice(exerciceDao.exerciceCourant(posteComptableSeanceOpcvm.getIdOpcvm()));
                    List<PosteComptableDto> lstPosteComptable=posteComptableDao.findBySupprimerAndPlan(false,planMapper.dePlanDto(exerciceDto.getPlan()))
                            .stream().map(posteComptableMapper::dePosteComptable).collect(Collectors.toList());
                    for (PosteComptableDto obj :lstPosteComptable)
                    {
                        String valeurCodePoste = new BigDecimal(calculer(
                                obj.getFormule().trim(), obj.getPlan().getCodePlan(),
                                posteComptableSeanceOpcvm.getDateOperation(),posteComptableSeanceOpcvm.getIdOpcvm())).toString();
                        if (valeurCodePoste.contains("#Erreur"))
                        {
                            valeurCP = BigDecimal.ZERO;
                        }
                        else
                        {
                            String code = obj.getCodePosteComptable().trim();

                            // Détermine le format en fonction du code
                            String pattern = (code.equals("_VL") || code.equals("_VLD")) ? "0.00" : "0.0000";

                            // Convertir en BigDecimal
                            BigDecimal valeur = new BigDecimal(valeurCodePoste);

                            // Déterminer le nombre de décimales sans arrondi
                            int scale = pattern.equals("0.00") ? 2 : 4;
                            valeur = valeur.setScale(scale, RoundingMode.DOWN);

                            // Formater avec DecimalFormat
                            DecimalFormat df = new DecimalFormat(pattern);
                            df.setRoundingMode(RoundingMode.DOWN); // par sécurité
                            String verbe = df.format(valeur);

                            // Convertir en BigDecimal sans erreur à cause de virgule
                            valeurCP = new BigDecimal(verbe.replace(',', '.'));
                        }
                        //ajout du code poste à la liste à afficher
                        PosteComptableSeanceOpcvmDto objP = new PosteComptableSeanceOpcvmDto();
                        objP.setPlan(obj.getPlan());
                        objP.setCodePosteComptable(obj.getCodePosteComptable());
                        LocalDateTime dateTime=posteComptableSeanceOpcvm.getDateOperation();
                        Instant i = dateTime.atZone(ZoneId.systemDefault()).toInstant();
                        Date date = Date.from(i);
                        objP.setDateValeur(date);
                        objP.setFormuleSysteme(obj.getFormule());
                        objP.setOpcvm(opcvmMapper.deOpcvm(seanceOpcvm.getOpcvm()));
                        objP.setIdSeance(posteComptableSeanceOpcvm.getIdSeance()+1);
                        objP.setLibellePosteComptable(obj.getLibellePosteComptable());
                        objP.setValeur(valeurCP);
                        lstPCSO.add(objP);
                    }


                    return ResponseHandler.generateResponse(
                            "Valorisation",
                            HttpStatus.OK,
                            lstPCSO);
                }
                else
                {
                    return ResponseHandler.generateResponse(
                            "Enregistrement effectué avec succès !",
                            HttpStatus.OK,
                            "Veuillez sélectionner la séance encours.1");
                }
            }
            else
            {
                return ResponseHandler.generateResponse(
                        "Enregistrement effectué avec succès !",
                        HttpStatus.OK,
                        etape+".1");
            }


        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creer(List<PosteComptableSeanceOpcvmDto> PosteComptableSeanceOpcvmDto) {
        try {
            String sortie="";
            Long i=0L;
            Long idOpcvm=0L;
            Long idSeance=0L;
            for(PosteComptableSeanceOpcvmDto o:PosteComptableSeanceOpcvmDto){
                if(i==0){
                    var k=em.createStoredProcedureQuery("[Comptabilite].[PS_PosteComptableSeanceOpcvm_DP_ALL]" );
                    k.registerStoredProcedureParameter("idOpcvm", Long.class,ParameterMode.IN);
                    k.registerStoredProcedureParameter("idSeance", Long.class,ParameterMode.IN);

                    k.setParameter("idOpcvm",o.getOpcvm().getIdOpcvm());
                    k.setParameter("idSeance",o.getIdSeance()+1);

                    k.execute();

                }
                i++;
                var q=em.createStoredProcedureQuery("[Comptabilite].[PS_PosteComptableSeanceOpcvm_IP]");
                q.registerStoredProcedureParameter("codePosteComptable",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("codePlan",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idOpcvm",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("idSeance",Long.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("formuleSysteme",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateValeur",Date.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("valeur",BigDecimal.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("userLogin",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("dateDernModifClient",LocalDateTime.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("CodeLangue",String.class, ParameterMode.IN);
                q.registerStoredProcedureParameter("Sortie",String.class, ParameterMode.OUT);

//                LocalDateTime dateTime = o.getDateValeur();
//                Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
                q.setParameter("codePosteComptable",o.getCodePosteComptable());
                q.setParameter("codePlan",o.getPlan().getCodePlan());
                q.setParameter("idOpcvm",o.getOpcvm().getIdOpcvm());
                q.setParameter("idSeance",o.getIdSeance());
                q.setParameter("formuleSysteme",o.getFormuleSysteme());
                q.setParameter("dateValeur",o.getDateValeur());
                q.setParameter("valeur",o.getValeur());
                q.setParameter("userLogin",o.getUserLogin());
                q.setParameter("dateDernModifClient",LocalDateTime.now());
                q.setParameter("CodeLangue","fr-FR");
                q.setParameter("Sortie",sortie);

                idOpcvm=o.getOpcvm().getIdOpcvm();
                idSeance=o.getIdSeance();
                q.execute();
            }
            var q=em.createStoredProcedureQuery("[Parametre].[PS_Niveau_UP]");
            q.registerStoredProcedureParameter("idOpcvm", Long.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("niveau", Long.class,ParameterMode.IN);

            q.setParameter("idOpcvm",idOpcvm);
            q.setParameter("idSeance",idSeance-1);
            q.setParameter("niveau",idSeance==0?5:11);
            q.execute();

            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    null);//PosteComptableSeanceOpcvmMapper.dePosteComptableSeanceOpcvm(PosteComptableSeanceOpcvm));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> jaspertReportCodePoste(Long idOpcvm, Long idSeance, Boolean estVerifie1, Boolean estVerifie2,Long niveau, HttpServletResponse response) throws IOException, JRException {
        List<CodePosteComptableProjection> list=libraryDao.afficherCodePosteComptable(idOpcvm, idSeance, estVerifie1, estVerifie2);
        Map<String, Object> parameters = new HashMap<>();
        DateFormat dateFormatter = new SimpleDateFormat("dd MMMM yyyy");
        String letterDate = dateFormatter.format(new Date());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        SeanceOpcvmDto seanceOpcvmDto=seanceOpcvmMapper.deSeanceOpcvm(seanceOpcvmDao.afficherSeance(idOpcvm,idSeance-1));
        parameters.put("letterDate", letterDate);
        parameters.put("niveau", niveau.toString());
        parameters.put("dateOuv", seanceOpcvmDto.getDateOuverture().format(formatter).toString());
        parameters.put("dateFerm", seanceOpcvmDto.getDateFermeture().format(formatter).toString());

        OpcvmDto opcvmDto=opcvmMapper.deOpcvm(opcvmDao.findById(idOpcvm).orElseThrow());
        parameters.put("denominationOpcvm", opcvmDto.getDenominationOpcvm());
        InputStream inputStream = getClass().getResourceAsStream("/verificationValorisationCodePosteN1N2.jrxml");
        if (inputStream == null) {
            throw new FileNotFoundException("Fichier JRXML introuvable dans le classpath");
        }

        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        // Export vers le flux de sortie HTTP
        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
//        File file = ResourceUtils.getFile("classpath:verificationValorisationCodePosteN1N2.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(list);
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, dataSource);
//        JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());
        return ResponseHandler.generateResponse(
                "Ordre de bourse",
                HttpStatus.OK,
                list);
    }

    @Override
    public ResponseEntity<Object> validerNiveau(Long idOpcvm, Long idSeance, Long niveau,String userLogin) {
        try
        {
            var q=em.createStoredProcedureQuery("[Comptabilite].[PS_PosteComptableSeanceOpcvm_Verification]");
            q.registerStoredProcedureParameter("idOpcvm", Long.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("estVerifie", Boolean.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("dateVerification", LocalDateTime.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("userLoginVerificateur", String.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("niveauVerfication", Long.class,ParameterMode.IN);

            q.setParameter("idOpcvm",idOpcvm);
            q.setParameter("idSeance",idSeance);
            q.setParameter("estVerifie",true);
            q.setParameter("dateVerification",LocalDateTime.now());
            q.setParameter("userLoginVerificateur",userLogin);
            q.setParameter("niveauVerfication",niveau);
            q.execute();


             q=em.createStoredProcedureQuery("[Parametre].[PS_Niveau_UP]");
            q.registerStoredProcedureParameter("idOpcvm", Long.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("idSeance", Long.class,ParameterMode.IN);
            q.registerStoredProcedureParameter("niveau", Long.class,ParameterMode.IN);

            q.setParameter("idOpcvm",idOpcvm);
            q.setParameter("idSeance",idSeance-1);
            q.setParameter("niveau",idSeance==0?niveau==1?6:7:niveau==1?12:13);
            q.execute();

            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    "Validation effectuée avec succès");

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
    public ResponseEntity<Object> modifier(PosteComptableSeanceOpcvmDto PosteComptableSeanceOpcvmDto) {
        try {
//            PosteComptableSeanceOpcvmDto.setSupprimer(false);
//            PosteComptableSeanceOpcvm PosteComptableSeanceOpcvm =PosteComptableSeanceOpcvmMapper.dePosteComptableSeanceOpcvmDto(PosteComptableSeanceOpcvmDto);
//
//            ClePosteComptableSeanceOpcvm clePosteComptableSeanceOpcvm=new ClePosteComptableSeanceOpcvm();
//            clePosteComptableSeanceOpcvm.setCodePosteComptableSeanceOpcvm(PosteComptableSeanceOpcvmDto.getCodePosteComptableSeanceOpcvm());
//            clePosteComptableSeanceOpcvm.setCodePlan(PosteComptableSeanceOpcvmDto.getPlan().getCodePlan());
//            PosteComptableSeanceOpcvm.setIdPosteComptableSeanceOpcvm(clePosteComptableSeanceOpcvm);
//
//            if(PosteComptableSeanceOpcvmDto.getPlan()!=null)
//            {
//                Plan plan=planDao.findById(PosteComptableSeanceOpcvmDto.getPlan().getCodePlan()).orElseThrow();
//                PosteComptableSeanceOpcvm.setPlan(plan);
//            }
//            PosteComptableSeanceOpcvm=PosteComptableSeanceOpcvmDao.save(PosteComptableSeanceOpcvm);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    null);//PosteComptableSeanceOpcvmMapper.dePosteComptableSeanceOpcvm(PosteComptableSeanceOpcvm));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(ClePosteComptableSeanceOpcvm clePosteComptableSeanceOpcvm) {
        try {
//            PosteComptableSeanceOpcvmDao.deleteById(clePosteComptableSeanceOpcvm);
            return ResponseHandler.generateResponse(
                    "Suppression effectuée avec succès",
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
