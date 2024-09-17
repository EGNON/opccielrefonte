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
import com.ged.dto.titresciel.DatDto;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.PersonneMorale;
import com.ged.entity.standard.Secteur;
import com.ged.entity.titresciel.*;
import com.ged.mapper.titresciel.DatMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.titresciel.DatService;
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
public class DatServiceImpl implements DatService {
    @Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;
    private final DatDao DatDao;
    private final DatMapper DatMapper;
    private final PaysDao paysDao;
    private final TypeTitreDao typeTitreDao;
    private final TypeEmissionDao typeEmissionDao;
    private final SecteurDao secteurDao;
    private final CompartimentDao compartimentDao;
    private final PlaceDao placeDao;
    private final PersonneMoraleDao personneMoraleDao;
    private final FiltersSpecification<Dat> titreFiltersSpecification;

    public DatServiceImpl(DatDao DatDao, DatMapper DatMapper, PaysDao paysDao, TypeTitreDao typeTitreDao, TypeEmissionDao typeEmissionDao, SecteurDao secteurDao, CompartimentDao compartimentDao, PlaceDao placeDao, PersonneMoraleDao personneMoraleDao, FiltersSpecification<Dat> titreFiltersSpecification){
        this.DatDao = DatDao;

        this.DatMapper = DatMapper;
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
            Sort sort = Sort.by(Sort.Direction.ASC,"nomDat");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Dat> DatPage;
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

                Specification<Dat> searchSpecification = titreFiltersSpecification
                        .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
                pageable = (new PageRequestDto()).getPageable(requestDto.getPageDto());
                DatPage = DatDao.findAll(searchSpecification, pageable);
            }
            else {
                DatPage = DatDao.findAll(pageable);
            }
            List<DatDto> content = DatPage.getContent().stream().map(DatMapper::deDat).collect(Collectors.toList());
            DataTablesResponse<DatDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)DatPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)DatPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Dats par page datatable",
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
    public Page<DatDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomDat");
            List<DatDto> Dats = DatDao.findAll().stream().map(DatMapper::deDat).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Dats",
                    HttpStatus.OK,
                    Dats);
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
    public Dat afficherSelonId(Long id) {
        return DatDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Dat.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Dat dont ID = " + id,
                    HttpStatus.OK,
                    DatMapper.deDat(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(DatDto DatDto) {
        try {
            Dat Dat = DatMapper.deDatDto(DatDto);
            if (DatDto.getPays() != null && DatDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(DatDto.getPays().getIdPays()).orElse(null);
                Dat.setPays(pays);
            }
            if(DatDto.getTypeTitre() != null && DatDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(DatDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Dat.setTypeTitre(typeTitre);
            }
            if(DatDto.getTypeEmission() != null && DatDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(DatDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Dat.setTypeEmission(typeEmission);
            }
            if(DatDto.getSecteur() != null && DatDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(DatDto.getSecteur().getIdSecteur()).orElse(null);
                Dat.setSecteur(secteur);
            }
            if(DatDto.getCompartiment() != null && DatDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(DatDto.getCompartiment().getIdCompartiment()).orElse(null);
                Dat.setCompartiment(compartiment);
            }
            if(DatDto.getPlace() != null && DatDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(DatDto.getPlace().getCodePlace()).orElse(null);
                Dat.setPlace(place);
            }
            if(DatDto.getEmetteur() != null && DatDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(DatDto.getEmetteur().getIdPersonne()).orElse(null);
                Dat.setEmetteur(emetteur);
            }
            if(DatDto.getRegistraire() != null && DatDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(DatDto.getRegistraire().getIdPersonne()).orElse(null);
                Dat.setRegistraire(registrateur);
            }
            if(DatDto.getDepositaire() != null && DatDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(DatDto.getDepositaire().getIdPersonne()).orElse(null);
                Dat.setDepositaire(depositaire);
            }
            if(DatDto.getBanque() != null && DatDto.getBanque().getIdPersonne() != null) {
                PersonneMorale banque = personneMoraleDao.findById(DatDto.getBanque().getIdPersonne()).orElse(null);
                Dat.setBanque(banque);
            }
            Dat = DatDao.save(Dat);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    DatMapper.deDat(Dat));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(DatDto DatDto) {
        try {
            if(!DatDao.existsById(DatDto.getIdTitre()))
                throw  new EntityNotFoundException(Dat.class, "ID", DatDto.getIdTitre().toString());
            Dat Dat = DatMapper.deDatDto(DatDto);
            if (DatDto.getPays() != null && DatDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(DatDto.getPays().getIdPays()).orElse(null);
                Dat.setPays(pays);
            }
            if(DatDto.getTypeTitre() != null && DatDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(DatDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Dat.setTypeTitre(typeTitre);
            }
            if(DatDto.getTypeEmission() != null && DatDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(DatDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Dat.setTypeEmission(typeEmission);
            }
            if(DatDto.getSecteur() != null && DatDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(DatDto.getSecteur().getIdSecteur()).orElse(null);
                Dat.setSecteur(secteur);
            }
            if(DatDto.getCompartiment() != null && DatDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(DatDto.getCompartiment().getIdCompartiment()).orElse(null);
                Dat.setCompartiment(compartiment);
            }
            if(DatDto.getPlace() != null && DatDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(DatDto.getPlace().getCodePlace()).orElse(null);
                Dat.setPlace(place);
            }
            if(DatDto.getEmetteur() != null && DatDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(DatDto.getEmetteur().getIdPersonne()).orElse(null);
                Dat.setEmetteur(emetteur);
            }
            if(DatDto.getRegistraire() != null && DatDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(DatDto.getRegistraire().getIdPersonne()).orElse(null);
                Dat.setRegistraire(registrateur);
            }
            if(DatDto.getDepositaire() != null && DatDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(DatDto.getDepositaire().getIdPersonne()).orElse(null);
                Dat.setDepositaire(depositaire);
            }
            if(DatDto.getBanque() != null && DatDto.getBanque().getIdPersonne() != null) {
                PersonneMorale banque = personneMoraleDao.findById(DatDto.getBanque().getIdPersonne()).orElse(null);
                Dat.setBanque(banque);
            }
            Dat = DatDao.save(Dat);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    DatMapper.deDat(Dat));
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
            DatDao.deleteById(idOperation);
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
