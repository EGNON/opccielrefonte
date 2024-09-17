package com.ged.controller.standard.parametre;
import com.ged.dao.standard.TypeDocumentDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.TypeDocumentDto;
import com.ged.entity.standard.TypeDocument;
import com.ged.mapper.standard.TypeDocumentMapper;
import com.ged.service.standard.TypeDocumentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/typedocuments")
public class TypeDocumentController {
    private final TypeDocumentService typeDocumentService;
    private final TypeDocumentDao typeDocumentDao;
    private final TypeDocumentMapper typeDocumentMapper;

    public TypeDocumentController(TypeDocumentService typeDocumentService, TypeDocumentDao typeDocumentDao, TypeDocumentMapper typeDocumentMapper) {
        this.typeDocumentService = typeDocumentService;
        this.typeDocumentDao = typeDocumentDao;
        this.typeDocumentMapper = typeDocumentMapper;
    }

    @GetMapping
    public Page<TypeDocumentDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                              @RequestParam(value = "size",defaultValue = "10") int size){
        return typeDocumentService.afficherTypeDocuments(page,size);
    }

    @GetMapping("/tous")
    public List<TypeDocumentDto> afficherTypeDocumentsTous(){
        return typeDocumentService.afficherTypeDocumentsTous();
    }

    @GetMapping("/{idTypeDocument}")
    public TypeDocumentDto afficherTypeDocument(@PathVariable("idTypeDocument") long idTypeDocument){
        return typeDocumentService.afficherTypeDocument(idTypeDocument);
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<TypeDocumentDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<TypeDocument> typeDocumentDtoPage;
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleTypeDoc");
        Pageable pageable= PageRequest.of(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength(),sort);
        if(datatableParameters.getSearch() != null && !StringUtils.isEmpty(datatableParameters.getSearch().getValue()))
        {
            typeDocumentDtoPage = typeDocumentDao.findByLibelleTypeDocContainsIgnoreCase(datatableParameters.getSearch().getValue(), pageable);
        }
        else {
            typeDocumentDtoPage = typeDocumentDao.findAll(pageable);
        }
        List<TypeDocumentDto> content = typeDocumentDtoPage.getContent().stream().map(typeDocumentMapper::deTypeDocument).collect(Collectors.toList());
        DataTablesResponse<TypeDocumentDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) typeDocumentDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) typeDocumentDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PostMapping
    public TypeDocumentDto ajouter(@RequestBody TypeDocumentDto typeDocumentDto)
    {
        return typeDocumentService.creerTypeDocument(typeDocumentDto);
    }

    @PutMapping("/{id}")
    public TypeDocumentDto modifier(@PathVariable long id, @RequestBody TypeDocumentDto typeDocumentDto){
        typeDocumentDto.setIdTypeDoc(id);
        return typeDocumentService.modifierTypeDocument(typeDocumentDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        typeDocumentService.supprimerTypeDocument(id);
    }
}
