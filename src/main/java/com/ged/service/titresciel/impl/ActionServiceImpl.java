package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.standard.PaysDao;
import com.ged.dao.standard.PersonneMoraleDao;
import com.ged.dao.standard.SecteurDao;
import com.ged.dao.titresciel.*;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.PageRequestDto;
import com.ged.dto.RequestDto;
import com.ged.dto.SearchRequestDto;
import com.ged.dto.titresciel.ActionDto;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.PersonneMorale;
import com.ged.entity.standard.Secteur;
import com.ged.entity.titresciel.*;
import com.ged.mapper.titresciel.ActionMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.titresciel.ActionService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ActionServiceImpl implements ActionService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final ActionDao actionDao;
    private final ActionMapper actionMapper;
    private final PaysDao paysDao;
    private final TypeTitreDao typeTitreDao;
    private final TypeEmissionDao typeEmissionDao;
    private final SecteurDao secteurDao;
    private final CompartimentDao compartimentDao;
    private final PlaceDao placeDao;
    private final PersonneMoraleDao personneMoraleDao;
    private final FiltersSpecification<Action> titreFiltersSpecification;

    public ActionServiceImpl(ActionDao ActionDao, ActionMapper ActionMapper, PaysDao paysDao, TypeTitreDao typeTitreDao, TypeEmissionDao typeEmissionDao, SecteurDao secteurDao, CompartimentDao compartimentDao, PlaceDao placeDao, PersonneMoraleDao personneMoraleDao, FiltersSpecification<Action> titreFiltersSpecification){
        this.actionDao = ActionDao;

        this.actionMapper = ActionMapper;
        this.paysDao = paysDao;
        this.typeTitreDao = typeTitreDao;
        this.typeEmissionDao = typeEmissionDao;
        this.secteurDao = secteurDao;
        this.compartimentDao = compartimentDao;
        this.placeDao = placeDao;
        this.personneMoraleDao = personneMoraleDao;
        this.titreFiltersSpecification = titreFiltersSpecification;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomAction");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Action> ActionPage;
            if (parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue())) {
                RequestDto requestDto = new RequestDto();
                requestDto.setGlobalOperator(RequestDto.GlobalOperator.OR);
                //Création de l'objet PageDto
                PageRequestDto pageDto = new PageRequestDto();
                pageDto.setPageNo(parameters.getStart() / parameters.getLength());
                pageDto.setPageSize(parameters.getLength());
                pageDto.setSort(Sort.Direction.ASC);
                pageDto.setSortByColumn("symbolTitre");
                requestDto.setPageDto(pageDto);
                //Création de l'objet searchRequestDto
                List<SearchRequestDto> searchRequestDtos = new ArrayList<>();
                SearchRequestDto searchRequestDto = new SearchRequestDto();
                searchRequestDto.setColumn("symbolTitre");
                searchRequestDto.setValue(parameters.getSearch().getValue());
                searchRequestDto.setOperation(SearchRequestDto.Operation.LIKE);
                searchRequestDtos.add(searchRequestDto);
                SearchRequestDto searchRequestDto1 = new SearchRequestDto();
                searchRequestDto1.setColumn("designationTitre");
                searchRequestDto1.setValue(parameters.getSearch().getValue());
                searchRequestDto1.setOperation(SearchRequestDto.Operation.LIKE);
                searchRequestDtos.add(searchRequestDto1);
                requestDto.setSearchRequestDto(searchRequestDtos);

                Specification<Action> searchSpecification = titreFiltersSpecification
                        .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
                pageable = (new PageRequestDto()).getPageable(requestDto.getPageDto());
                ActionPage = actionDao.findAll(searchSpecification, pageable);
            }
            else {
                ActionPage = actionDao.findAll(pageable);
            }
            List<ActionDto> content = ActionPage.getContent().stream().map(actionMapper::deAction).collect(Collectors.toList());
            DataTablesResponse<ActionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)ActionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)ActionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Actions par page datatable",
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
    public Page<ActionDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            List<ActionDto> Actions = actionDao.findAll().stream().map(actionMapper::deAction).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Actions",
                    HttpStatus.OK,
                    Actions);
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
    public Action afficherSelonId(Long id) {
        return actionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Action.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Action dont ID = " + id,
                    HttpStatus.OK,
                    actionMapper.deAction(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(ActionDto ActionDto) {
        try {
            Action Action = actionMapper.deActionDto(ActionDto);
            if (ActionDto.getPays() != null && ActionDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(ActionDto.getPays().getIdPays()).orElse(null);
                Action.setPays(pays);
            }
            if(ActionDto.getTypeTitre() != null && ActionDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(ActionDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Action.setTypeTitre(typeTitre);
            }
            if(ActionDto.getTypeEmission() != null && ActionDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(ActionDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Action.setTypeEmission(typeEmission);
            }
            if(ActionDto.getSecteur() != null && ActionDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(ActionDto.getSecteur().getIdSecteur()).orElse(null);
                Action.setSecteur(secteur);
            }
            if(ActionDto.getCompartiment() != null && ActionDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(ActionDto.getCompartiment().getIdCompartiment()).orElse(null);
                Action.setCompartiment(compartiment);
            }
            if(ActionDto.getPlace() != null && ActionDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(ActionDto.getPlace().getCodePlace()).orElse(null);
                Action.setPlace(place);
            }
            if(ActionDto.getEmetteur() != null && ActionDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(ActionDto.getEmetteur().getIdPersonne()).orElse(null);
                Action.setEmetteur(emetteur);
            }
            if(ActionDto.getRegistraire() != null && ActionDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(ActionDto.getRegistraire().getIdPersonne()).orElse(null);
                Action.setRegistraire(registrateur);
            }
            if(ActionDto.getDepositaire() != null && ActionDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(ActionDto.getDepositaire().getIdPersonne()).orElse(null);
                Action.setDepositaire(depositaire);
            }
            Action = actionDao.save(Action);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    actionMapper.deAction(Action));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ActionDto ActionDto) {
        try {
            if(!actionDao.existsById(ActionDto.getIdTitre()))
                throw  new EntityNotFoundException(Action.class, "ID", ActionDto.getIdTitre().toString());
            Action Action = actionMapper.deActionDto(ActionDto);
            if (ActionDto.getPays() != null && ActionDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(ActionDto.getPays().getIdPays()).orElse(null);
                Action.setPays(pays);
            }
            if(ActionDto.getTypeTitre() != null && ActionDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(ActionDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Action.setTypeTitre(typeTitre);
            }
            if(ActionDto.getTypeEmission() != null && ActionDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(ActionDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Action.setTypeEmission(typeEmission);
            }
            if(ActionDto.getSecteur() != null && ActionDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(ActionDto.getSecteur().getIdSecteur()).orElse(null);
                Action.setSecteur(secteur);
            }
            if(ActionDto.getCompartiment() != null && ActionDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(ActionDto.getCompartiment().getIdCompartiment()).orElse(null);
                Action.setCompartiment(compartiment);
            }
            if(ActionDto.getPlace() != null && ActionDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(ActionDto.getPlace().getCodePlace()).orElse(null);
                Action.setPlace(place);
            }
            if(ActionDto.getEmetteur() != null && ActionDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(ActionDto.getEmetteur().getIdPersonne()).orElse(null);
                Action.setEmetteur(emetteur);
            }
            if(ActionDto.getRegistraire() != null && ActionDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(ActionDto.getRegistraire().getIdPersonne()).orElse(null);
                Action.setRegistraire(registrateur);
            }
            if(ActionDto.getDepositaire() != null && ActionDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(ActionDto.getDepositaire().getIdPersonne()).orElse(null);
                Action.setDepositaire(depositaire);
            }
            Action = actionDao.save(Action);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    actionMapper.deAction(Action));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        try {
            actionDao.deleteById(id);
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
