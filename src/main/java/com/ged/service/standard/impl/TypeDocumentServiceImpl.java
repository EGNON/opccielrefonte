package com.ged.service.standard.impl;

import com.ged.dao.standard.TypeDocumentDao;
import com.ged.dto.standard.TypeDocumentDto;
import com.ged.entity.standard.TypeDocument;
import com.ged.mapper.standard.TypeDocumentMapper;
import com.ged.service.standard.TypeDocumentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TypeDocumentServiceImpl implements TypeDocumentService {
    private final TypeDocumentDao typeDocumentDao;
    private final TypeDocumentMapper typeDocumentMapper;

    public TypeDocumentServiceImpl(TypeDocumentDao typeDocumentDao, TypeDocumentMapper typeDocumentMapper) {
        this.typeDocumentDao = typeDocumentDao;
        this.typeDocumentMapper = typeDocumentMapper;
    }

    @Override
    public Page<TypeDocumentDto> afficherTypeDocuments(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleTypeDoc");
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<TypeDocument> typeDocumentPage=typeDocumentDao.findAll(pageRequest);
        return new PageImpl<>(typeDocumentPage.getContent().stream().map(typeDocumentMapper::deTypeDocument).collect(Collectors.toList()),pageRequest,typeDocumentPage.getTotalElements());
    }

    @Override
    public List<TypeDocumentDto> afficherTypeDocumentsTous() {
        return typeDocumentDao.findAll().stream().map(typeDocumentMapper::deTypeDocument).collect(Collectors.toList());
    }

    @Override
    public TypeDocument afficherTypeDocumentSelonId(long idTypeDocument) {
        return typeDocumentDao.findById(idTypeDocument).orElseThrow(()-> new EntityNotFoundException("Type document avec ID"+idTypeDocument+" introuvable"));
    }

    @Override
    public TypeDocumentDto afficherTypeDocument(long idTypeDocument) {
        return typeDocumentMapper.deTypeDocument(afficherTypeDocumentSelonId(idTypeDocument));
    }

    @Override
    public TypeDocumentDto rechercherTypeDocumentParLibelle(String libelle) {
        TypeDocument typeDocument=typeDocumentDao.findByLibelleTypeDoc(libelle);
        if(typeDocument!=null)
            return typeDocumentMapper.deTypeDocument(typeDocument);
        else
            return null;
    }

    @Override
    public TypeDocumentDto creerTypeDocument(TypeDocumentDto typeDocumentDto) {
        TypeDocument typeDocument=typeDocumentMapper.deTypeDocumentDto(typeDocumentDto);
        TypeDocument typeDocumentSave=typeDocumentDao.save(typeDocument);
        return typeDocumentMapper.deTypeDocument(typeDocumentSave);
    }

    @Override
    public TypeDocumentDto modifierTypeDocument(TypeDocumentDto typeDocumentDto) {
        TypeDocument typeDocument= typeDocumentMapper.deTypeDocumentDto(typeDocumentDto);
        TypeDocument typeDocumentMaj=typeDocumentDao.save(typeDocument);
        return typeDocumentMapper.deTypeDocument(typeDocumentMaj);
    }

    @Override
    public void supprimerTypeDocument(long idTypeDocument) {
        typeDocumentDao.deleteById(idTypeDocument);
    }
}
