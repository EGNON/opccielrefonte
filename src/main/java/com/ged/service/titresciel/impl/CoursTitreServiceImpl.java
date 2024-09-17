package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.CoursTitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.TableRequest;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.titresciel.CoursTitreDto;
import com.ged.dto.titresciel.CoursTitreResponse;
import com.ged.dto.titresciel.cours.CoursTitreMajDto;
import com.ged.dto.titresciel.cours.DateCoursDto;
import com.ged.entity.titresciel.CleCoursTitre;
import com.ged.entity.titresciel.CoursTitre;
import com.ged.mapper.titresciel.CoursTitreMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.titresciel.CoursTitreService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@Transactional
public class CoursTitreServiceImpl implements CoursTitreService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final CoursTitreDao coursTitreDao;
    private final CoursTitreMapper coursTitreMapper;

    public CoursTitreServiceImpl(CoursTitreDao CoursTitreDao, CoursTitreMapper CoursTitreMapper){
        this.coursTitreDao = CoursTitreDao;
        this.coursTitreMapper = CoursTitreMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomCoursTitre");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<CoursTitre> CoursTitrePage;
            CoursTitrePage = coursTitreDao.findAll(pageable);
            List<CoursTitreResponse> content = CoursTitrePage.getContent().stream().map(coursTitreMapper::deCoursTitre1).collect(Collectors.toList());
            DataTablesResponse<CoursTitreResponse> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)CoursTitrePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)CoursTitrePage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des CoursTitres par page datatable",
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
    public Page<CoursTitreDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomCoursTitre");
            List<CoursTitreResponse> CoursTitres = coursTitreDao.findAll().stream().map(coursTitreMapper::deCoursTitre1).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des CoursTitres",
                    HttpStatus.OK,
                    CoursTitres);
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
    public CoursTitre afficherSelonId(CleCoursTitre id) {
        return coursTitreDao.findById(id).orElseThrow(() -> new EntityNotFoundException(CoursTitre.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(CleCoursTitre id) {
        try {
            return ResponseHandler.generateResponse(
                    "CoursTitre dont ID = " + id,
                    HttpStatus.OK,
                    coursTitreMapper.deCoursTitre(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(CoursTitreDto coursTitreDto) {
        try {
            CoursTitre CoursTitre = coursTitreMapper.deCoursTitreDto(coursTitreDto);
            CleCoursTitre cleCoursTitre=new CleCoursTitre();
            cleCoursTitre.setIdTitre(coursTitreDto.getTitre().getIdTitre());
            cleCoursTitre.setDateCours(coursTitreDto.getDateCours());
            CoursTitre.setIdCoursTitre(cleCoursTitre);

            CoursTitre = coursTitreDao.save(CoursTitre);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    coursTitreMapper.deCoursTitre(CoursTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(CoursTitreDto CoursTitreDto) {
        try {
            CoursTitre CoursTitre = coursTitreMapper.deCoursTitreDto(CoursTitreDto);
            CleCoursTitre cleCoursTitre=new CleCoursTitre();
            cleCoursTitre.setIdTitre(CoursTitreDto.getTitre().getIdTitre());
            cleCoursTitre.setDateCours(CoursTitreDto.getDateCours());
            CoursTitre.setIdCoursTitre(cleCoursTitre);
            CoursTitre = coursTitreDao.save(CoursTitre);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    coursTitreMapper.deCoursTitre(CoursTitre));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(CleCoursTitre id) {
        try {
            coursTitreDao.deleteById(id);
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

    @Override
    public ResponseEntity<Object> getLastCoursTitre(Long idTitre) {
        try {
            Long cours = coursTitreDao.lastCoursTitre(idTitre).orElse(0L);
            return ResponseHandler.generateResponse(
                    "Dernier cours du titre dont ID = " + idTitre.toString(),
                    HttpStatus.OK,
                    cours);
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
    public ResponseEntity<Object> getAllDateCours(
            String codePlace,
            TableRequest tableRequest) {
        try {
            DatatableParameters parameters = tableRequest.getDatatableParameters();
            BeginEndDateParameter beginEndDateParameter = tableRequest.getBeginEndDateParameter();
            Pageable pageable = PageRequest.of(parameters.getStart()/parameters.getLength(),
                    tableRequest.getDatatableParameters().getLength());
            Page<DateCoursDto> dateCoursDtoPage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                dateCoursDtoPage = null;
            }
            else
            {
                dateCoursDtoPage = coursTitreDao.findByDateCoursAndPlace(codePlace, beginEndDateParameter.getStartDate(),
                        beginEndDateParameter.getEndDate(), pageable);
            }
            DataTablesResponse<DateCoursDto> tablesResponse = new DataTablesResponse<>();
            if(dateCoursDtoPage != null) {
                List<DateCoursDto> content = dateCoursDtoPage.getContent();
                tablesResponse.setDraw(parameters.getDraw());
                tablesResponse.setRecordsFiltered((int)dateCoursDtoPage.getTotalElements());
                tablesResponse.setRecordsTotal((int)dateCoursDtoPage.getTotalElements());
                tablesResponse.setData(content);
            }
            return ResponseHandler.generateResponse(
                    "Liste filtrée...",
                    HttpStatus.OK,
                    tablesResponse);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> getAllCoursTitreMaj(
            String codePlace,
            TableRequest tableRequest
    ) {
        try {
            DatatableParameters parameters = tableRequest.getDatatableParameters();
            BeginEndDateParameter beginEndDateParameter = tableRequest.getBeginEndDateParameter();
            Pageable pageable = PageRequest.of(parameters.getStart()/parameters.getLength(),
                    tableRequest.getDatatableParameters().getLength());
            List<CoursTitreMajDto> coursTitreMajDtoPage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                coursTitreMajDtoPage = null;
            }
            else
            {
                coursTitreMajDtoPage = coursTitreDao.findByDateCoursAndPlaceMaj(codePlace, beginEndDateParameter.getStartDate()).stream().toList();
                if(coursTitreMajDtoPage.size() == 0)
                {
                    System.out.println("Date cours " + beginEndDateParameter.getStartDate().toString());
                    coursTitreMajDtoPage = coursTitreDao.findByDateCoursAndPlaceMaj1(codePlace, beginEndDateParameter.getStartDate()).stream().toList();
                }
            }
            DataTablesResponse<CoursTitreMajDto> tablesResponse = new DataTablesResponse<>();
            if(coursTitreMajDtoPage != null) {
                tablesResponse.setDraw(parameters.getDraw());
                tablesResponse.setRecordsFiltered(coursTitreMajDtoPage.size());
                tablesResponse.setRecordsTotal(coursTitreMajDtoPage.size());
                tablesResponse.setData(coursTitreMajDtoPage);
            }
            return ResponseHandler.generateResponse(
                    "Cours à mettre à jour...",
                    HttpStatus.OK,
                    tablesResponse);
        }
        catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> addAll(List<CoursTitreDto> coursTitreDtos, String codePlace) {
        try {
            String s = Optional.ofNullable(codePlace).orElse("");
            List<CoursTitre> coursTitres = coursTitreDtos.stream().map(coursTitreMapper::deCoursTitreDto).toList();
            if(coursTitres.size() > 0) {
                if(!s.trim().isEmpty()) {
                    CoursTitre firstCours = coursTitres.get(0);
                    if(firstCours.getTitre() != null && firstCours.getTitre().getPlace() != null) {
                        coursTitreDao.deleteAllCoursTitreByDate(
                                firstCours.getTitre().getPlace().getCodePlace(), firstCours.getIdCoursTitre().getDateCours());
                    }
                }
                coursTitreDtos = coursTitreDao.saveAll(coursTitres).stream().map(coursTitreMapper::deCoursTitre).collect(Collectors.toList());
            }
            return ResponseHandler.generateResponse(
                    "Collection de cours enregistrée avec succès.",
                    HttpStatus.OK,
                    coursTitreDtos);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
