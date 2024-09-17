package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.ClassificationOPCDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.titresciel.ClassificationOPCDto;
import com.ged.entity.security.Permission;
import com.ged.entity.titresciel.ClassificationOPC;
import com.ged.mapper.titresciel.ClassificationOPCMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.ClassificationService;
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
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassificationServiceImpl implements ClassificationService {
    /*@Autowired
    @Qualifier("titrescielEntityManagerFactory")
    private EntityManager emTitresciel;*/
    private final ClassificationOPCDao classificationOPCDao;
    private final ClassificationOPCMapper classificationOPCMapper;

    public ClassificationServiceImpl(ClassificationOPCDao classificationOPCDao, ClassificationOPCMapper classificationOPCMapper) {
        this.classificationOPCDao = classificationOPCDao;
        this.classificationOPCMapper = classificationOPCMapper;
    }

    /*@Override
    public List<Object> createClassificationFromOppciel1() {
        List<Object> result = new ArrayList<>();
        List<Object[]> classifications;
        //Se connecter à opcciel1 et récupérer les différentes classifications
        try {
            StoredProcedureQuery query = emTitresciel.createStoredProcedureQuery("[Titre].[PS_ClassificationOPC_SP]");
            //Déclarer les différents paramètres
            query.registerStoredProcedureParameter("codeClassificationOPC", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("libelleClassificationOPC", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("numLigne", Double.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateCreationServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifServeur", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("dateDernModifClient", Date.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("userLogin", String.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("supprimer", Boolean.class, ParameterMode.IN);
            query.registerStoredProcedureParameter("rowvers", byte[].class, ParameterMode.IN);
            //Fournir les différents paramètres
            query.setParameter("codeClassificationOPC", null);
            query.setParameter("libelleClassificationOPC", null);
            query.setParameter("numLigne", null);
            query.setParameter("dateCreationServeur", null);
            query.setParameter("dateDernModifServeur", null);
            query.setParameter("dateDernModifClient", null);
            query.setParameter("userLogin", null);
            query.setParameter("supprimer", false);
            query.setParameter("rowvers", null);
            query.execute();
            classifications = query.getResultList();
            for (Object[] o: classifications) {
                if(!classificationOPCDao.existsByCodeClassificationIgnoreCase((String)o[0]))
                {
                    //System.out.println("ELEMENT 1 === " + o[0]);
                    ClassificationOPC classificationOPC = new ClassificationOPC();
                    classificationOPC.setCodeClassification((String)o[0]);
                    classificationOPC.setLibelleClassification((String)o[1]);
                    result.add(classificationOPCDao.save(classificationOPC));
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return result;
    }*/

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleClassification");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<ClassificationOPC> classificationOPCPage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                classificationOPCPage = classificationOPCDao.findByLibelleClassificationContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
            }
            else {
                classificationOPCPage = classificationOPCDao.findAll(pageable);
            }
            List<ClassificationOPCDto> content = classificationOPCPage.getContent().stream().map(classificationOPCMapper::deClassification).collect(Collectors.toList());
            DataTablesResponse<ClassificationOPCDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)classificationOPCPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)classificationOPCPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des classifications par page datatable",
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
    public ResponseEntity<Object> afficherTous() {
        try {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleClassification");
            return ResponseHandler.generateResponse(
                "Liste de toutes les classifications",
                HttpStatus.OK,
                classificationOPCDao.findAll(sort).stream().map(classificationOPCMapper::deClassification).collect(Collectors.toList()));
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
    public ClassificationOPC afficherSelonId(String codeClassification) {
        return classificationOPCDao.findById(codeClassification).orElseThrow(() -> new EntityNotFoundException(ClassificationOPC.class, "code", codeClassification));
    }

    @Override
    public ResponseEntity<Object> afficher(String codeClassification) {
        try {
            return ResponseHandler.generateResponse(
                    "Classification dont CODE = " + codeClassification,
                    HttpStatus.OK,
                    classificationOPCMapper.deClassification(afficherSelonId(codeClassification)));
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
    public ResponseEntity<Object> creer(ClassificationOPCDto classificationOPCDto) {
        try {
            ClassificationOPC classificationOPC = classificationOPCMapper.deClassificationDto(classificationOPCDto);
            classificationOPC = classificationOPCDao.save(classificationOPC);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    classificationOPCMapper.deClassification(classificationOPC));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ClassificationOPCDto classificationOPCDto) {
        try {
            if(!classificationOPCDao.existsById(classificationOPCDto.getCodeClassification()))
                throw  new EntityNotFoundException(Permission.class, "code", classificationOPCDto.getCodeClassification());
            ClassificationOPC classificationOPC = classificationOPCMapper.deClassificationDto(classificationOPCDto);
            classificationOPC = classificationOPCDao.save(classificationOPC);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    classificationOPCMapper.deClassification(classificationOPC));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String codeClassification) {
        try {
            classificationOPCDao.deleteById(codeClassification);
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
