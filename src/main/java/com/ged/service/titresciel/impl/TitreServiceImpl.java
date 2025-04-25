package com.ged.service.titresciel.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.titresciel.TitreDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.PageRequestDto;
import com.ged.dto.RequestDto;
import com.ged.dto.SearchRequestDto;
import com.ged.dto.titresciel.TitreDto;
import com.ged.entity.titresciel.CoursTitre;
import com.ged.entity.titresciel.Titre;
import com.ged.mapper.titresciel.TitreMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.titresciel.TitreService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitreServiceImpl implements TitreService {
    private final TitreDao titreDao;
    private final TitreMapper titreMapper;
    private final FiltersSpecification<Titre> titreFiltersSpecification;

    public TitreServiceImpl(TitreDao titreDao, TitreMapper titreMapper, FiltersSpecification<Titre> titreFiltersSpecification) {
        this.titreDao = titreDao;
        this.titreMapper = titreMapper;
        this.titreFiltersSpecification = titreFiltersSpecification;
    }

    @Override
    public ResponseEntity<Object> findBySymbolTitre(String symbolTitre) {
        try {
            Titre titre = titreDao.findBySymbolTitreIgnoreCase(symbolTitre).orElse(null);
            return ResponseHandler.generateResponse(
                    "Titre dont Symbole = " + symbolTitre,
                    HttpStatus.OK,
                    titreMapper.deTitre(titre));
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
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        return null;
    }

    @Override
    public Page<TitreDto> afficherTousParPage(int page, int size) {
        return null;
    }

    @Override
    public DataTablesResponse<TitreDto> afficherTous(String qualite, DatatableParameters parameters) {
        Page<Titre> titrePage;

        Pageable pageable = PageRequest.of(
                parameters.getStart() / parameters.getLength(), parameters.getLength());
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
            requestDto.setSearchRequestDto(searchRequestDtos);

            Specification<Titre> searchSpecification = titreFiltersSpecification
                    .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
            pageable = (new PageRequestDto()).getPageable(requestDto.getPageDto());
            titrePage = titreDao.findAll(searchSpecification, pageable);

            /*titrePage = titreDao.rechercherSelonQualite(
                    qualite,
                    parameters.getSearch().getValue(),
                    pageable);*/
        } else {
            titrePage = titreDao.afficherTousSelonQualite(
                    qualite,
                    pageable);
        }
        List<TitreDto> content = titrePage.getContent().stream().map(titreMapper::deTitre).collect(Collectors.toList());

        DataTablesResponse<TitreDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int) titrePage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int) titrePage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            List<TitreDto> titre = titreDao.findBySupprimerOrderBySymbolTitreAsc(false).stream().map(titreMapper::deTitre).collect(Collectors.toList());
            return ResponseHandler.generateResponse(
                    "Liste des titres",
                    HttpStatus.OK,
                    titre);
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
    public Titre afficherSelonId(Long id) {
        return titreDao.findById(id).orElseThrow(() -> new EntityNotFoundException(CoursTitre.class, "id",id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            TitreDto titre =titreMapper.deTitre(afficherSelonId(id));
            return ResponseHandler.generateResponse(
                    "titre selon ID",
                    HttpStatus.OK,
                    titre);
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
    public ResponseEntity<Object> creer(TitreDto TitreDto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> modifier(TitreDto TitreDto) {
        return null;
    }

    @Override
    public void supprimer(Long id) {

    }
}
