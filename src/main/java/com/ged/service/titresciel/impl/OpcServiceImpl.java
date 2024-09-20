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
import com.ged.dto.titresciel.OpcDto;
import com.ged.entity.standard.Pays;
import com.ged.entity.standard.PersonneMorale;
import com.ged.entity.standard.Secteur;
import com.ged.entity.titresciel.*;
import com.ged.mapper.titresciel.OpcMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.titresciel.OpcService;
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
public class OpcServiceImpl implements OpcService {
    /*@Autowired
    @Qualifier("opccielEntityManagerFactory")
    private EntityManager emOpcciel;*/
    private final OpcDao OpcDao;
    private final OpcMapper OpcMapper;
    private final PaysDao paysDao;
    private final TypeTitreDao typeTitreDao;
    private final TypeEmissionDao typeEmissionDao;
    private final SecteurDao secteurDao;
    private final CompartimentDao compartimentDao;
    private final PlaceDao placeDao;
    private final PersonneMoraleDao personneMoraleDao;
    private final FormeJuridiqueOpcDao formeJuridiqueOpcDao;
    private final ClassificationOPCDao classificationOpcDao;
    private final TypeAffectationTitreDao typeAffectationTitreDao;
    private final FiltersSpecification<Opc> titreFiltersSpecification;

    public OpcServiceImpl(OpcDao OpcDao, OpcMapper OpcMapper, PaysDao paysDao, TypeTitreDao typeTitreDao, TypeEmissionDao typeEmissionDao, SecteurDao secteurDao, CompartimentDao compartimentDao, PlaceDao placeDao, PersonneMoraleDao personneMoraleDao, FormeJuridiqueOpcDao formeJuridiqueOpcDao, ClassificationOPCDao classificationOpcDao, TypeAffectationTitreDao typeAffectationTitreDao, FiltersSpecification<Opc> titreFiltersSpecification){
        this.OpcDao = OpcDao;

        this.OpcMapper = OpcMapper;
        this.paysDao = paysDao;
        this.typeTitreDao = typeTitreDao;
        this.typeEmissionDao = typeEmissionDao;
        this.secteurDao = secteurDao;
        this.compartimentDao = compartimentDao;
        this.placeDao = placeDao;
        this.personneMoraleDao = personneMoraleDao;
        this.formeJuridiqueOpcDao = formeJuridiqueOpcDao;
        this.classificationOpcDao = classificationOpcDao;
        this.typeAffectationTitreDao = typeAffectationTitreDao;
        this.titreFiltersSpecification = titreFiltersSpecification;
    }

    @Override
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomOpc");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Opc> OpcPage;
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

                Specification<Opc> searchSpecification = titreFiltersSpecification
                        .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
                pageable = (new PageRequestDto()).getPageable(requestDto.getPageDto());
                OpcPage = OpcDao.findAll(searchSpecification, pageable);
            }
            else {
                OpcPage = OpcDao.findAll(pageable);
            }
            List<OpcDto> content = OpcPage.getContent().stream().map(OpcMapper::deOpc).collect(Collectors.toList());
            DataTablesResponse<OpcDto> OpcaTablesResponse = new DataTablesResponse<>();
            OpcaTablesResponse.setDraw(parameters.getDraw());
            OpcaTablesResponse.setRecordsFiltered((int)OpcPage.getTotalElements());
            OpcaTablesResponse.setRecordsTotal((int)OpcPage.getTotalElements());
            OpcaTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des Opcs par page Opcatable",
                    HttpStatus.OK,
                    OpcaTablesResponse);
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
    public Page<OpcDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nomOpc");
            List<OpcDto> Opcs = OpcDao.findAll().stream().map(OpcMapper::deOpc).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des Opcs",
                    HttpStatus.OK,
                    Opcs);
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
    public Opc afficherSelonId(Long id) {
        return OpcDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Opc.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Opc dont ID = " + id,
                    HttpStatus.OK,
                    OpcMapper.deOpc(afficherSelonId(id)));
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
    public ResponseEntity<Object> creer(OpcDto OpcDto) {
        try {
            Opc Opc = OpcMapper.deOpcDto(OpcDto);
            if (OpcDto.getPays() != null && OpcDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(OpcDto.getPays().getIdPays()).orElse(null);
                Opc.setPays(pays);
            }
            if(OpcDto.getTypeTitre() != null && OpcDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(OpcDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Opc.setTypeTitre(typeTitre);
            }
            if(OpcDto.getTypeEmission() != null && OpcDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(OpcDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Opc.setTypeEmission(typeEmission);
            }
            if(OpcDto.getSecteur() != null && OpcDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(OpcDto.getSecteur().getIdSecteur()).orElse(null);
                Opc.setSecteur(secteur);
            }
            if(OpcDto.getCompartiment() != null && OpcDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(OpcDto.getCompartiment().getIdCompartiment()).orElse(null);
                Opc.setCompartiment(compartiment);
            }
            if(OpcDto.getPlace() != null && OpcDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(OpcDto.getPlace().getCodePlace()).orElse(null);
                Opc.setPlace(place);
            }
            if(OpcDto.getEmetteur() != null && OpcDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(OpcDto.getEmetteur().getIdPersonne()).orElse(null);
                Opc.setEmetteur(emetteur);
            }
            if(OpcDto.getRegistraire() != null && OpcDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(OpcDto.getRegistraire().getIdPersonne()).orElse(null);
                Opc.setRegistraire(registrateur);
            }
            if(OpcDto.getDepositaire() != null && OpcDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(OpcDto.getDepositaire().getIdPersonne()).orElse(null);
                Opc.setDepositaire(depositaire);
            }
            if(OpcDto.getFormeJuridiqueOpc() != null && OpcDto.getFormeJuridiqueOpc().getIdFormeJuridiqueOpc() != null) {
                FormeJuridiqueOpc formeJuridiqueOpc = formeJuridiqueOpcDao.findById(OpcDto.getFormeJuridiqueOpc().getIdFormeJuridiqueOpc()).orElse(null);
                Opc.setFormeJuridiqueOpc(formeJuridiqueOpc);
            }
            if(OpcDto.getClassificationOPC() != null && OpcDto.getClassificationOPC().getCodeClassification() != null) {
                ClassificationOPC classificationOPC = classificationOpcDao.findById(OpcDto.getClassificationOPC().getCodeClassification()).orElse(null);
                Opc.setClassificationOPC(classificationOPC);
            }
            if(OpcDto.getTypeAffectationTitre() != null && OpcDto.getTypeAffectationTitre().getIdTypeAffectation() != null) {
                TypeAffectationVL typeAffectationTitre = typeAffectationTitreDao.findById(OpcDto.getTypeAffectationTitre().getIdTypeAffectation()).orElse(null);
                Opc.setTypeAffectationTitre(typeAffectationTitre);
            }
            Opc = OpcDao.save(Opc);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    OpcMapper.deOpc(Opc));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(OpcDto OpcDto) {
        try {
            if(!OpcDao.existsById(OpcDto.getIdTitre()))
                throw  new EntityNotFoundException(Opc.class, "ID", OpcDto.getIdTitre().toString());
            Opc Opc = OpcMapper.deOpcDto(OpcDto);
            if (OpcDto.getPays() != null && OpcDto.getPays().getIdPays() != null) {
                Pays pays = paysDao.findById(OpcDto.getPays().getIdPays()).orElse(null);
                Opc.setPays(pays);
            }
            if(OpcDto.getTypeTitre() != null && OpcDto.getTypeTitre().getCodeTypeTitre() != null) {
                TypeTitre typeTitre = typeTitreDao.findById(OpcDto.getTypeTitre().getCodeTypeTitre()).orElse(null);
                Opc.setTypeTitre(typeTitre);
            }
            if(OpcDto.getTypeEmission() != null && OpcDto.getTypeEmission().getIdTypeEmission() != null) {
                TypeEmission typeEmission = typeEmissionDao.findById(OpcDto.getTypeEmission().getIdTypeEmission()).orElse(null);
                Opc.setTypeEmission(typeEmission);
            }
            if(OpcDto.getSecteur() != null && OpcDto.getSecteur().getIdSecteur() != null) {
                Secteur secteur = secteurDao.findById(OpcDto.getSecteur().getIdSecteur()).orElse(null);
                Opc.setSecteur(secteur);
            }
            if(OpcDto.getCompartiment() != null && OpcDto.getCompartiment().getIdCompartiment() != null) {
                Compartiment compartiment = compartimentDao.findById(OpcDto.getCompartiment().getIdCompartiment()).orElse(null);
                Opc.setCompartiment(compartiment);
            }
            if(OpcDto.getPlace() != null && OpcDto.getPlace().getCodePlace() != null) {
                Place place = placeDao.findById(OpcDto.getPlace().getCodePlace()).orElse(null);
                Opc.setPlace(place);
            }
            if(OpcDto.getEmetteur() != null && OpcDto.getEmetteur().getIdPersonne() != null) {
                PersonneMorale emetteur = personneMoraleDao.findById(OpcDto.getEmetteur().getIdPersonne()).orElse(null);
                Opc.setEmetteur(emetteur);
            }
            if(OpcDto.getRegistraire() != null && OpcDto.getRegistraire().getIdPersonne() != null) {
                PersonneMorale registrateur = personneMoraleDao.findById(OpcDto.getRegistraire().getIdPersonne()).orElse(null);
                Opc.setRegistraire(registrateur);
            }
            if(OpcDto.getDepositaire() != null && OpcDto.getDepositaire().getIdPersonne() != null) {
                PersonneMorale depositaire = personneMoraleDao.findById(OpcDto.getDepositaire().getIdPersonne()).orElse(null);
                Opc.setDepositaire(depositaire);
            }
            if(OpcDto.getFormeJuridiqueOpc() != null && OpcDto.getFormeJuridiqueOpc().getIdFormeJuridiqueOpc() != null) {
                FormeJuridiqueOpc formeJuridiqueOpc = formeJuridiqueOpcDao.findById(OpcDto.getFormeJuridiqueOpc().getIdFormeJuridiqueOpc()).orElse(null);
                Opc.setFormeJuridiqueOpc(formeJuridiqueOpc);
            }
            if(OpcDto.getClassificationOPC() != null && OpcDto.getClassificationOPC().getCodeClassification() != null) {
                ClassificationOPC classificationOPC = classificationOpcDao.findById(OpcDto.getClassificationOPC().getCodeClassification()).orElse(null);
                Opc.setClassificationOPC(classificationOPC);
            }
            if(OpcDto.getTypeAffectationTitre() != null && OpcDto.getTypeAffectationTitre().getIdTypeAffectation() != null) {
                TypeAffectationVL typeAffectationTitre = typeAffectationTitreDao.findById(OpcDto.getTypeAffectationTitre().getIdTypeAffectation()).orElse(null);
                Opc.setTypeAffectationTitre(typeAffectationTitre);
            }
            Opc = OpcDao.save(Opc);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    OpcMapper.deOpc(Opc));
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
            OpcDao.deleteById(idOperation);
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
