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
import com.ged.dto.titresciel.TcnDto;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.PersonneMorale;
import com.ged.entity.standard.Secteur;
import com.ged.entity.titresciel.*;
import com.ged.mapper.titresciel.TcnMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.titresciel.TcnService;
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
public class TcnServiceImpl implements TcnService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final TcnDao TcnDao;
    private final TcnMapper TcnMapper;
    private final PaysDao paysDao;
    private final TypeTitreDao typeTitreDao;
    private final ModeAmortissementDao modeAmortissementDao;
    private final TypeAmortissementDao typeAmortissementDao;
    private final TypeEmissionDao typeEmissionDao;
    private final SecteurDao secteurDao;
    private final CompartimentDao compartimentDao;
    private final PlaceDao placeDao;
    private final PersonneMoraleDao personneMoraleDao;
    private final NatureTcnDao natureTcnDao;
    private final ModeCalculInteretDao modeCalculInteretDao;
    private final FiltersSpecification<Tcn> titreFiltersSpecification;

    public TcnServiceImpl(TcnDao TcnDao, TcnMapper TcnMapper, PaysDao paysDao, TypeTitreDao typeTitreDao, ModeAmortissementDao modeAmortissementDao, TypeAmortissementDao typeAmortissementDao, TypeEmissionDao typeEmissionDao, SecteurDao secteurDao, CompartimentDao compartimentDao, PlaceDao placeDao, PersonneMoraleDao personneMoraleDao, NatureTcnDao natureTcnDao, ModeCalculInteretDao modeCalculInteretDao, FiltersSpecification<Tcn> titreFiltersSpecification){
        this.TcnDao = TcnDao;

        this.TcnMapper = TcnMapper;
        this.paysDao = paysDao;
        this.typeTitreDao = typeTitreDao;
        this.modeAmortissementDao = modeAmortissementDao;
        this.typeAmortissementDao = typeAmortissementDao;
        this.typeEmissionDao = typeEmissionDao;
        this.secteurDao = secteurDao;
        this.compartimentDao = compartimentDao;
        this.placeDao = placeDao;
        this.personneMoraleDao = personneMoraleDao;
        this.natureTcnDao = natureTcnDao;
        this.modeCalculInteretDao = modeCalculInteretDao;
        this.titreFiltersSpecification = titreFiltersSpecification;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomTcn");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Tcn> TcnPage;
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

                Specification<Tcn> searchSpecification = titreFiltersSpecification
                        .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
                pageable = (new PageRequestDto()).getPageable(requestDto.getPageDto());
                TcnPage = TcnDao.findAll(searchSpecification, pageable);
            }
            else {
                TcnPage = TcnDao.findAll(pageable);
            }
            List<TcnDto> content = TcnPage.getContent().stream().map(TcnMapper::deTcn).collect(Collectors.toList());
            DataTablesResponse<TcnDto> TcnaTablesResponse = new DataTablesResponse<>();
            TcnaTablesResponse.setDraw(parameters.getDraw());
            TcnaTablesResponse.setRecordsFiltered((int)TcnPage.getTotalElements());
            TcnaTablesResponse.setRecordsTotal((int)TcnPage.getTotalElements());
            TcnaTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Tcns par page Tcnatable",
                    HttpStatus.OK,
                    TcnaTablesResponse);
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
    public Page<TcnDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomTcn");
            List<TcnDto> Tcns = TcnDao.findAll().stream().map(TcnMapper::deTcn).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Tcns",
                    HttpStatus.OK,
                    Tcns);
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
    public Tcn afficherSelonId(Long id) {
        return TcnDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Tcn.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Tcn dont ID = " + id,
                    HttpStatus.OK,
                    TcnMapper.deTcn(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(TcnDto TcnDto) {
        try {
            Tcn Tcn = TcnMapper.deTcnDto(TcnDto);
            if (TcnDto.getPays() != null && TcnDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(TcnDto.getPays().getIdPays()).orElse(null);
                Tcn.setPays(pays);
            }
            if(TcnDto.getTypeTitre() != null && TcnDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(TcnDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Tcn.setTypeTitre(typeTitre);
            }
            if(TcnDto.getModeAmortissement() != null && TcnDto.getModeAmortissement().getIdModeAmortissement() != null) {
                ModeAmortissement modeAmortissement = modeAmortissementDao.findById(TcnDto.getModeAmortissement().getIdModeAmortissement()).orElse(null);
                Tcn.setModeAmortissement(modeAmortissement);
            }
            if(TcnDto.getTypeAmortissement() != null && TcnDto.getTypeAmortissement().getIdTypeAmortissement() != null) {
                TypeAmortissement typeAmortissement = typeAmortissementDao.findById(TcnDto.getTypeAmortissement().getIdTypeAmortissement()).orElse(null);
                Tcn.setTypeAmortissement(typeAmortissement);
            }
            if(TcnDto.getTypeEmission() != null && TcnDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(TcnDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Tcn.setTypeEmission(typeEmission);
            }
            if(TcnDto.getSecteur() != null && TcnDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(TcnDto.getSecteur().getIdSecteur()).orElse(null);
                Tcn.setSecteur(secteur);
            }
            if(TcnDto.getCompartiment() != null && TcnDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(TcnDto.getCompartiment().getIdCompartiment()).orElse(null);
                Tcn.setCompartiment(compartiment);
            }
            if(TcnDto.getPlace() != null && TcnDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(TcnDto.getPlace().getCodePlace()).orElse(null);
                Tcn.setPlace(place);
            }
            if(TcnDto.getEmetteur() != null && TcnDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(TcnDto.getEmetteur().getIdPersonne()).orElse(null);
                Tcn.setEmetteur(emetteur);
            }
            if(TcnDto.getRegistraire() != null && TcnDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(TcnDto.getRegistraire().getIdPersonne()).orElse(null);
                Tcn.setRegistraire(registrateur);
            }
            if(TcnDto.getDepositaire() != null && TcnDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(TcnDto.getDepositaire().getIdPersonne()).orElse(null);
                Tcn.setDepositaire(depositaire);
            }
            if(TcnDto.getNatureTcn() != null && TcnDto.getNatureTcn().getIdNatureTcn() != null) {
                NatureTcn natureTcn = natureTcnDao.findById(TcnDto.getNatureTcn().getIdNatureTcn()).orElse(null);
                Tcn.setNatureTcn(natureTcn);
            }
            if(TcnDto.getFormulePrecomptee() != null && TcnDto.getFormulePrecomptee().getIdModeCalculInteret() != null) {
                ModeCalculInteret formulePrecomptee = modeCalculInteretDao.findById(TcnDto.getFormulePrecomptee().getIdModeCalculInteret()).orElse(null);
                Tcn.setFormulePrecomptee(formulePrecomptee);
            }
            Tcn = TcnDao.save(Tcn);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    TcnMapper.deTcn(Tcn));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(TcnDto TcnDto) {
        try {
            if(!TcnDao.existsById(TcnDto.getIdTitre()))
                throw  new EntityNotFoundException(Tcn.class, "ID", TcnDto.getIdTitre().toString());
            Tcn Tcn = TcnMapper.deTcnDto(TcnDto);
            if (TcnDto.getPays() != null && TcnDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(TcnDto.getPays().getIdPays()).orElse(null);
                Tcn.setPays(pays);
            }
            if(TcnDto.getTypeTitre() != null && TcnDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(TcnDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Tcn.setTypeTitre(typeTitre);
            }
            if(TcnDto.getModeAmortissement() != null && TcnDto.getModeAmortissement().getIdModeAmortissement() != null) {
                ModeAmortissement modeAmortissement = modeAmortissementDao.findById(TcnDto.getModeAmortissement().getIdModeAmortissement()).orElse(null);
                Tcn.setModeAmortissement(modeAmortissement);
            }
            if(TcnDto.getTypeAmortissement() != null && TcnDto.getTypeAmortissement().getIdTypeAmortissement() != null) {
                TypeAmortissement typeAmortissement = typeAmortissementDao.findById(TcnDto.getTypeAmortissement().getIdTypeAmortissement()).orElse(null);
                Tcn.setTypeAmortissement(typeAmortissement);
            }
            if(TcnDto.getTypeEmission() != null && TcnDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(TcnDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Tcn.setTypeEmission(typeEmission);
            }
            if(TcnDto.getSecteur() != null && TcnDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(TcnDto.getSecteur().getIdSecteur()).orElse(null);
                Tcn.setSecteur(secteur);
            }
            if(TcnDto.getCompartiment() != null && TcnDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(TcnDto.getCompartiment().getIdCompartiment()).orElse(null);
                Tcn.setCompartiment(compartiment);
            }
            if(TcnDto.getPlace() != null && TcnDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(TcnDto.getPlace().getCodePlace()).orElse(null);
                Tcn.setPlace(place);
            }
            if(TcnDto.getEmetteur() != null && TcnDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(TcnDto.getEmetteur().getIdPersonne()).orElse(null);
                Tcn.setEmetteur(emetteur);
            }
            if(TcnDto.getRegistraire() != null && TcnDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(TcnDto.getRegistraire().getIdPersonne()).orElse(null);
                Tcn.setRegistraire(registrateur);
            }
            if(TcnDto.getDepositaire() != null && TcnDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(TcnDto.getDepositaire().getIdPersonne()).orElse(null);
                Tcn.setDepositaire(depositaire);
            }
            if(TcnDto.getNatureTcn() != null && TcnDto.getNatureTcn().getIdNatureTcn() != null) {
                NatureTcn natureTcn = natureTcnDao.findById(TcnDto.getNatureTcn().getIdNatureTcn()).orElse(null);
                Tcn.setNatureTcn(natureTcn);
            }
            if(TcnDto.getFormulePrecomptee() != null && TcnDto.getFormulePrecomptee().getIdModeCalculInteret() != null) {
                ModeCalculInteret formulePrecomptee = modeCalculInteretDao.findById(TcnDto.getFormulePrecomptee().getIdModeCalculInteret()).orElse(null);
                Tcn.setFormulePrecomptee(formulePrecomptee);
            }
            Tcn = TcnDao.save(Tcn);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    TcnMapper.deTcn(Tcn));
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
            TcnDao.deleteById(idOperation);
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
