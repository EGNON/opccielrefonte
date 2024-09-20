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
import com.ged.dto.titresciel.DroitDto;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.PersonneMorale;
import com.ged.entity.standard.Secteur;
import com.ged.entity.titresciel.*;
import com.ged.mapper.titresciel.DroitMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.titresciel.DroitService;
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
public class DroitServiceImpl implements DroitService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final DroitDao DroitDao;
    private final DroitMapper DroitMapper;
    private final PaysDao paysDao;
    private final TypeTitreDao typeTitreDao;
    private final TypeEmissionDao typeEmissionDao;
    private final SecteurDao secteurDao;
    private final CompartimentDao compartimentDao;
    private final PlaceDao placeDao;
    private final PersonneMoraleDao personneMoraleDao;
    private final ActionDao actionDao;
    private final FiltersSpecification<Droit> titreFiltersSpecification;

    public DroitServiceImpl(DroitDao DroitDao, DroitMapper DroitMapper, PaysDao paysDao, TypeTitreDao typeTitreDao, TypeEmissionDao typeEmissionDao, SecteurDao secteurDao, CompartimentDao compartimentDao, PlaceDao placeDao, PersonneMoraleDao personneMoraleDao, ActionDao actionDao, FiltersSpecification<Droit> titreFiltersSpecification){
        this.DroitDao = DroitDao;

        this.DroitMapper = DroitMapper;
        this.paysDao = paysDao;
        this.typeTitreDao = typeTitreDao;
        this.typeEmissionDao = typeEmissionDao;
        this.secteurDao = secteurDao;
        this.compartimentDao = compartimentDao;
        this.placeDao = placeDao;
        this.personneMoraleDao = personneMoraleDao;
        this.actionDao = actionDao;
        this.titreFiltersSpecification = titreFiltersSpecification;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomDroit");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Droit> DroitPage;
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

                Specification<Droit> searchSpecification = titreFiltersSpecification
                        .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
                pageable = (new PageRequestDto()).getPageable(requestDto.getPageDto());
                DroitPage = DroitDao.findAll(searchSpecification, pageable);
            }
            else {
                DroitPage = DroitDao.findAll(pageable);
            }
            List<DroitDto> content = DroitPage.getContent().stream().map(DroitMapper::deDroit).collect(Collectors.toList());
            DataTablesResponse<DroitDto> DroitaTablesResponse = new DataTablesResponse<>();
            DroitaTablesResponse.setDraw(parameters.getDraw());
            DroitaTablesResponse.setRecordsFiltered((int)DroitPage.getTotalElements());
            DroitaTablesResponse.setRecordsTotal((int)DroitPage.getTotalElements());
            DroitaTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Droits par page Droitatable",
                    HttpStatus.OK,
                    DroitaTablesResponse);
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
    public Page<DroitDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomDroit");
            List<DroitDto> Droits = DroitDao.findAll().stream().map(DroitMapper::deDroit).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Droits",
                    HttpStatus.OK,
                    Droits);
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
    public Droit afficherSelonId(Long id) {
        return DroitDao.findByIdTitre(id).orElseThrow(() -> new EntityNotFoundException(Droit.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Droit dont ID = " + id,
                    HttpStatus.OK,
                    DroitMapper.deDroit(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(DroitDto DroitDto) {
        try {
            Droit Droit = DroitMapper.deDroitDto(DroitDto);
            if (DroitDto.getPays() != null && DroitDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(DroitDto.getPays().getIdPays()).orElse(null);
                Droit.setPays(pays);
            }
            if(DroitDto.getTypeTitre() != null && DroitDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(DroitDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Droit.setTypeTitre(typeTitre);
            }
            if(DroitDto.getTypeEmission() != null && DroitDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(DroitDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Droit.setTypeEmission(typeEmission);
            }
            if(DroitDto.getSecteur() != null && DroitDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(DroitDto.getSecteur().getIdSecteur()).orElse(null);
                Droit.setSecteur(secteur);
            }
            if(DroitDto.getCompartiment() != null && DroitDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(DroitDto.getCompartiment().getIdCompartiment()).orElse(null);
                Droit.setCompartiment(compartiment);
            }
            if(DroitDto.getPlace() != null && DroitDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(DroitDto.getPlace().getCodePlace()).orElse(null);
                Droit.setPlace(place);
            }
            if(DroitDto.getEmetteur() != null && DroitDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(DroitDto.getEmetteur().getIdPersonne()).orElse(null);
                Droit.setEmetteur(emetteur);
            }
            if(DroitDto.getRegistraire() != null && DroitDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(DroitDto.getRegistraire().getIdPersonne()).orElse(null);
                Droit.setRegistraire(registrateur);
            }
            if(DroitDto.getDepositaire() != null && DroitDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(DroitDto.getDepositaire().getIdPersonne()).orElse(null);
                Droit.setDepositaire(depositaire);
            }
            if(DroitDto.getActionLiee() != null && DroitDto.getActionLiee().getIdTitre() != null) {
                Action actionLiee = actionDao.findById(DroitDto.getActionLiee().getIdTitre()).orElse(null);
                Droit.setActionLiee(actionLiee);
            }
            if(DroitDto.getNouvelleAction() != null && DroitDto.getNouvelleAction().getIdTitre() != null) {
                Action nouvelleAction = actionDao.findById(DroitDto.getNouvelleAction().getIdTitre()).orElse(null);
                Droit.setNouvelleAction(nouvelleAction);
            }
            Droit = DroitDao.save(Droit);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    DroitMapper.deDroit(Droit));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(DroitDto DroitDto) {
        try {
            if(!DroitDao.existsById(DroitDto.getIdTitre()))
                throw  new EntityNotFoundException(Droit.class, "ID", DroitDto.getIdTitre().toString());
            Droit Droit = DroitMapper.deDroitDto(DroitDto);
            if (DroitDto.getPays() != null && DroitDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(DroitDto.getPays().getIdPays()).orElse(null);
                Droit.setPays(pays);
            }
            if(DroitDto.getTypeTitre() != null && DroitDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(DroitDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Droit.setTypeTitre(typeTitre);
            }
            if(DroitDto.getTypeEmission() != null && DroitDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(DroitDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Droit.setTypeEmission(typeEmission);
            }
            if(DroitDto.getSecteur() != null && DroitDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(DroitDto.getSecteur().getIdSecteur()).orElse(null);
                Droit.setSecteur(secteur);
            }
            if(DroitDto.getCompartiment() != null && DroitDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(DroitDto.getCompartiment().getIdCompartiment()).orElse(null);
                Droit.setCompartiment(compartiment);
            }
            if(DroitDto.getPlace() != null && DroitDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(DroitDto.getPlace().getCodePlace()).orElse(null);
                Droit.setPlace(place);
            }
            if(DroitDto.getEmetteur() != null && DroitDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(DroitDto.getEmetteur().getIdPersonne()).orElse(null);
                Droit.setEmetteur(emetteur);
            }
            if(DroitDto.getRegistraire() != null && DroitDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(DroitDto.getRegistraire().getIdPersonne()).orElse(null);
                Droit.setRegistraire(registrateur);
            }
            if(DroitDto.getDepositaire() != null && DroitDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(DroitDto.getDepositaire().getIdPersonne()).orElse(null);
                Droit.setDepositaire(depositaire);
            }
            if(DroitDto.getActionLiee() != null && DroitDto.getActionLiee().getIdTitre() != null) {
                Action actionLiee = actionDao.findById(DroitDto.getActionLiee().getIdTitre()).orElse(null);
                Droit.setActionLiee(actionLiee);
            }
            if(DroitDto.getNouvelleAction() != null && DroitDto.getNouvelleAction().getIdTitre() != null) {
                Action nouvelleAction = actionDao.findById(DroitDto.getNouvelleAction().getIdTitre()).orElse(null);
                Droit.setNouvelleAction(nouvelleAction);
            }
            Droit = DroitDao.save(Droit);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    DroitMapper.deDroit(Droit));
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
            DroitDao.deleteById(idOperation);
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
