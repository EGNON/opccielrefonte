package com.ged.service.opcciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.opcciel.comptabilite.JournalDao;
import com.ged.dao.opcciel.comptabilite.PlanDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.JournalDto;
import com.ged.entity.opcciel.comptabilite.Journal;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.mapper.opcciel.JournalMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.JournalService;
import jakarta.persistence.EntityManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JournalServiceImpl implements JournalService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final JournalDao journalDao;
    private final PlanDao planDao;
    private final JournalMapper journalMapper;

    public JournalServiceImpl(JournalDao JournalDao, PlanDao planDao, JournalMapper JournalMapper){
        this.journalDao = JournalDao;
        this.planDao = planDao;

        this.journalMapper = JournalMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleJournal");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Journal> JournalPage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                JournalPage = journalDao.rechercher(
                        parameters.getSearch().getValue(),
                        pageable);
            }
            else {
                JournalPage = journalDao.findBySupprimer(false,pageable);
            }

            List<JournalDto> content = JournalPage.getContent().stream().map(journalMapper::deJournal).collect(Collectors.toList());
            DataTablesResponse<JournalDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)JournalPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)JournalPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des journaux par page datatable",
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
    public Page<JournalDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libelleJournal");
            List<JournalDto> Journals = journalDao.findBySupprimerOrderByLibelleJournalAsc(false).stream().map(journalMapper::deJournal).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des journaux ",
                    HttpStatus.OK,
                    Journals);
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
    public Journal afficherSelonId(String codeJournal) {
        return journalDao.findById(codeJournal).orElseThrow(() -> new EntityNotFoundException(Journal.class, "code",codeJournal));
    }

    @Override
    public ResponseEntity<Object> afficher(String codeJournal) {
        try {
            return ResponseHandler.generateResponse(
                    "Journal dont CODE = " + codeJournal,
                    HttpStatus.OK,
                    journalMapper.deJournal(afficherSelonId(codeJournal)));
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
    public ResponseEntity<Object> creer(JournalDto JournalDto) {
        try {
            JournalDto.setSupprimer(false);

            Journal Journal = journalMapper.deJournalDto(JournalDto);
            if(JournalDto.getPlan()!=null) {
                Plan plan = planDao.findById(JournalDto.getPlan().getCodePlan())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Plan.class, JournalDto.getPlan().getCodePlan().toString()));
                Journal.setPlan(plan);
            }
            Journal = journalDao.save(Journal);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    journalMapper.deJournal(Journal));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(JournalDto JournalDto) {
        try {
            if(!journalDao.existsById(JournalDto.getCodeJournal()))
                throw  new EntityNotFoundException(Journal.class, "code", JournalDto.getCodeJournal().toString());
            JournalDto.setSupprimer(false);

            Journal Journal = journalMapper.deJournalDto(JournalDto);
            if(JournalDto.getPlan()!=null) {
                Plan plan = planDao.findById(JournalDto.getPlan().getCodePlan())
                        .orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Plan.class, JournalDto.getPlan().getCodePlan().toString()));
                Journal.setPlan(plan);
            }
            Journal = journalDao.save(Journal);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    journalMapper.deJournal(Journal));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(String codeJournal) {
        try {
            journalDao.deleteById(codeJournal);
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
