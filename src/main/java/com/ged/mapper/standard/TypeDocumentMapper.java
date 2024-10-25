package com.ged.mapper.standard;

import com.ged.dto.standard.TypeDocumentDto;
import com.ged.entity.standard.TypeDocument;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TypeDocumentMapper {
    public TypeDocumentDto deTypeDocument(TypeDocument typeDocument)
    {
        if(typeDocument == null)
            return null;
        TypeDocumentDto typeDocumentDto = new TypeDocumentDto();
        BeanUtils.copyProperties(typeDocument, typeDocumentDto);
        return typeDocumentDto;
    }

    public TypeDocument deTypeDocumentDto(TypeDocumentDto typeDocumentDto)
    {
        if(typeDocumentDto == null) {
            return null;
        }
        TypeDocument typeDocument = new TypeDocument();
        BeanUtils.copyProperties(typeDocumentDto, typeDocument);
        return typeDocument;
    }
}
