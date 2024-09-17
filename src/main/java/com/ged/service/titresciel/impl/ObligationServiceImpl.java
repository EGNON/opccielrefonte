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
import com.ged.dto.titresciel.ObligationDto;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.PersonneMorale;
import com.ged.entity.standard.Secteur;
import com.ged.entity.titresciel.*;
import com.ged.mapper.titresciel.ObligationMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.titresciel.ObligationService;
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
//@Transactional
public class ObligationServiceImpl implements ObligationService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final ObligationDao ObligationDao;
    private final ObligationMapper ObligationMapper;
    private final PaysDao paysDao;
    private final TypeTitreDao typeTitreDao;
    private final ModeAmortissementDao modeAmortissementDao;
    private final TypeAmortissementDao typeAmortissementDao;
    private final TypeEmissionDao typeEmissionDao;
    private final SecteurDao secteurDao;
    private final CompartimentDao compartimentDao;
    private final PlaceDao placeDao;
    private final PersonneMoraleDao personneMoraleDao;
    private final TypeObligationDao typeObligationDao;
    private final FiltersSpecification<Obligation> titreFiltersSpecification;

    public ObligationServiceImpl(ObligationDao ObligationDao, ObligationMapper ObligationMapper, PaysDao paysDao, TypeTitreDao typeTitreDao, ModeAmortissementDao modeAmortissementDao, TypeAmortissementDao typeAmortissementDao, TypeEmissionDao typeEmissionDao, SecteurDao secteurDao, CompartimentDao compartimentDao, PlaceDao placeDao, PersonneMoraleDao personneMoraleDao, TypeObligationDao typeObligationDao, FiltersSpecification<Obligation> titreFiltersSpecification){
        this.ObligationDao = ObligationDao;

        this.ObligationMapper = ObligationMapper;
        this.paysDao = paysDao;
        this.typeTitreDao = typeTitreDao;
        this.modeAmortissementDao = modeAmortissementDao;
        this.typeAmortissementDao = typeAmortissementDao;
        this.typeEmissionDao = typeEmissionDao;
        this.secteurDao = secteurDao;
        this.compartimentDao = compartimentDao;
        this.placeDao = placeDao;
        this.personneMoraleDao = personneMoraleDao;
        this.typeObligationDao = typeObligationDao;
        this.titreFiltersSpecification = titreFiltersSpecification;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomObligation");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Obligation> ObligationPage;
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

                Specification<Obligation> searchSpecification = titreFiltersSpecification
                        .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
                pageable = (new PageRequestDto()).getPageable(requestDto.getPageDto());
                ObligationPage = ObligationDao.findAll(searchSpecification, pageable);
            }
            else {
                ObligationPage = ObligationDao.findAll(pageable);
            }
            List<ObligationDto> content = ObligationPage.getContent().stream().map(ObligationMapper::deObligation).collect(Collectors.toList());
            DataTablesResponse<ObligationDto> ObligationaTablesResponse = new DataTablesResponse<>();
            ObligationaTablesResponse.setDraw(parameters.getDraw());
            ObligationaTablesResponse.setRecordsFiltered((int)ObligationPage.getTotalElements());
            ObligationaTablesResponse.setRecordsTotal((int)ObligationPage.getTotalElements());
            ObligationaTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Obligations par page Obligationatable",
                    HttpStatus.OK,
                    ObligationaTablesResponse);
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
    public Page<ObligationDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomObligation");
            List<ObligationDto> Obligations = ObligationDao.findAll().stream().map(ObligationMapper::deObligation).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Obligations",
                    HttpStatus.OK,
                    Obligations);
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
    public Obligation afficherSelonId(Long id) {
        return ObligationDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Obligation.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Obligation dont ID = " + id,
                    HttpStatus.OK,
                    ObligationMapper.deObligation(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(ObligationDto ObligationDto) {
        try {
            Obligation Obligation = ObligationMapper.deObligationDto(ObligationDto);
            if (ObligationDto.getPays() != null && ObligationDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(ObligationDto.getPays().getIdPays()).orElse(null);
                Obligation.setPays(pays);
            }
            if(ObligationDto.getTypeTitre() != null && ObligationDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(ObligationDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Obligation.setTypeTitre(typeTitre);
            }
            if(ObligationDto.getModeAmortissement() != null && ObligationDto.getModeAmortissement().getIdModeAmortissement() != null) {
                ModeAmortissement modeAmortissement = modeAmortissementDao.findById(ObligationDto.getModeAmortissement().getIdModeAmortissement()).orElse(null);
                Obligation.setModeAmortissement(modeAmortissement);
            }
            if(ObligationDto.getTypeAmortissement() != null && ObligationDto.getTypeAmortissement().getIdTypeAmortissement() != null) {
                TypeAmortissement typeAmortissement = typeAmortissementDao.findById(ObligationDto.getTypeAmortissement().getIdTypeAmortissement()).orElse(null);
                Obligation.setTypeAmortissement(typeAmortissement);
            }
            if(ObligationDto.getTypeEmission() != null && ObligationDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(ObligationDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Obligation.setTypeEmission(typeEmission);
            }
            if(ObligationDto.getSecteur() != null && ObligationDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(ObligationDto.getSecteur().getIdSecteur()).orElse(null);
                Obligation.setSecteur(secteur);
            }
            if(ObligationDto.getCompartiment() != null && ObligationDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(ObligationDto.getCompartiment().getIdCompartiment()).orElse(null);
                Obligation.setCompartiment(compartiment);
            }
            if(ObligationDto.getPlace() != null && ObligationDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(ObligationDto.getPlace().getCodePlace()).orElse(null);
                Obligation.setPlace(place);
            }
            if(ObligationDto.getEmetteur() != null && ObligationDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(ObligationDto.getEmetteur().getIdPersonne()).orElse(null);
                Obligation.setEmetteur(emetteur);
            }
            if(ObligationDto.getRegistraire() != null && ObligationDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(ObligationDto.getRegistraire().getIdPersonne()).orElse(null);
                Obligation.setRegistraire(registrateur);
            }
            if(ObligationDto.getDepositaire() != null && ObligationDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(ObligationDto.getDepositaire().getIdPersonne()).orElse(null);
                Obligation.setDepositaire(depositaire);
            }
            if(ObligationDto.getTypeObligation() != null && ObligationDto.getTypeObligation().getIdTypeObligation() != null) {
                TypeObligation typeObligation = typeObligationDao.findById(ObligationDto.getTypeObligation().getIdTypeObligation()).orElse(null);
                Obligation.setTypeObligation(typeObligation);
            }
            Obligation = ObligationDao.save(Obligation);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    ObligationMapper.deObligation(Obligation));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        } finally {
            emOpcciel.close();
        }
    }

    @Override
    public ResponseEntity<Object> modifier(ObligationDto ObligationDto) {
        try {
            if(!ObligationDao.existsById(ObligationDto.getIdTitre()))
                throw  new EntityNotFoundException(Obligation.class, "ID", ObligationDto.getIdTitre().toString());
            Obligation Obligation = ObligationMapper.deObligationDto(ObligationDto);
            if (ObligationDto.getPays() != null && ObligationDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(ObligationDto.getPays().getIdPays()).orElse(null);
                Obligation.setPays(pays);
            }
            if(ObligationDto.getTypeTitre() != null && ObligationDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(ObligationDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Obligation.setTypeTitre(typeTitre);
            }
            if(ObligationDto.getModeAmortissement() != null && ObligationDto.getModeAmortissement().getIdModeAmortissement() != null) {
                ModeAmortissement modeAmortissement = modeAmortissementDao.findById(ObligationDto.getModeAmortissement().getIdModeAmortissement()).orElse(null);
                Obligation.setModeAmortissement(modeAmortissement);
            }
            if(ObligationDto.getTypeAmortissement() != null && ObligationDto.getTypeAmortissement().getIdTypeAmortissement() != null) {
                TypeAmortissement typeAmortissement = typeAmortissementDao.findById(ObligationDto.getTypeAmortissement().getIdTypeAmortissement()).orElse(null);
                Obligation.setTypeAmortissement(typeAmortissement);
            }
            if(ObligationDto.getTypeEmission() != null && ObligationDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(ObligationDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Obligation.setTypeEmission(typeEmission);
            }
            if(ObligationDto.getSecteur() != null && ObligationDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(ObligationDto.getSecteur().getIdSecteur()).orElse(null);
                Obligation.setSecteur(secteur);
            }
            if(ObligationDto.getCompartiment() != null && ObligationDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(ObligationDto.getCompartiment().getIdCompartiment()).orElse(null);
                Obligation.setCompartiment(compartiment);
            }
            if(ObligationDto.getPlace() != null && ObligationDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(ObligationDto.getPlace().getCodePlace()).orElse(null);
                Obligation.setPlace(place);
            }
            if(ObligationDto.getEmetteur() != null && ObligationDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(ObligationDto.getEmetteur().getIdPersonne()).orElse(null);
                Obligation.setEmetteur(emetteur);
            }
            if(ObligationDto.getRegistraire() != null && ObligationDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(ObligationDto.getRegistraire().getIdPersonne()).orElse(null);
                Obligation.setRegistraire(registrateur);
            }
            if(ObligationDto.getDepositaire() != null && ObligationDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(ObligationDto.getDepositaire().getIdPersonne()).orElse(null);
                Obligation.setDepositaire(depositaire);
            }
            if(ObligationDto.getTypeObligation() != null && ObligationDto.getTypeObligation().getIdTypeObligation() != null) {
                TypeObligation typeObligation = typeObligationDao.findById(ObligationDto.getTypeObligation().getIdTypeObligation()).orElse(null);
                Obligation.setTypeObligation(typeObligation);
            }
            System.out.println("Taille tab === " + Obligation.getTabAmortissements());
            Obligation = ObligationDao.save(Obligation);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    ObligationMapper.deObligation(Obligation));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long idOperation) {
        try {
            ObligationDao.deleteById(idOperation);
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
