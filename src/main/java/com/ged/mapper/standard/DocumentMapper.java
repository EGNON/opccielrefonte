package com.ged.mapper.standard;

import com.ged.dto.crm.CompteRenduDto;
import com.ged.dto.standard.DocumentDto;
import com.ged.dto.crm.RDVDto;
import com.ged.entity.crm.CompteRendu;
import com.ged.entity.standard.Document;
import com.ged.entity.crm.RDV;
import com.ged.mapper.crm.CompteRenduMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class DocumentMapper {
    private final TypeDocumentMapper typeDocumentMapper;
    private final PersonneMapper personneMapper;
    private final PaysMapper paysMapper;
//    private final CompteRenduMapper compteRenduMapper;
    private final QuartierMapper quartierMapper;

    public DocumentMapper(TypeDocumentMapper typeDocumentMapper, PersonneMapper personneMapper, PaysMapper paysMapper,  QuartierMapper quartierMapper) {
        this.typeDocumentMapper = typeDocumentMapper;
        this.personneMapper = personneMapper;
        this.paysMapper = paysMapper;
//        this.compteRenduMapper = compteRenduMapper;
        this.quartierMapper = quartierMapper;
    }

    public DocumentDto deDocument(Document document)
    {
        if(document == null)
            return null;
        DocumentDto documentDto = new DocumentDto();
        BeanUtils.copyProperties(document, documentDto);
        documentDto.setTypeDocument(typeDocumentMapper.deTypeDocument(document.getTypeDocument()));
        documentDto.setPersonne(personneMapper.dePersonne(document.getPersonne()));
        if(document.getRdv() != null)
        {
            RDV rdv=new RDV();
            rdv=document.getRdv();
            RDVDto rdvDto=new RDVDto();
            BeanUtils.copyProperties(rdv, rdvDto);
            rdvDto.setPaysDto(paysMapper.dePays(rdv.getPays()));
            rdvDto.setPersonnePhysiqueMoraleDto(personneMapper.dePersonnePhysiqueMorale(rdv.getPersonne()));
            rdvDto.setQuartierDto(quartierMapper.deQuartier(rdv.getQuartier()));
            documentDto.setRdv(rdvDto);
        }
//        if(document.getCompteRendu() != null)
//        {
//            documentDto.setCompteRendu(compteRenduMapper.deCompteRendu(document.getCompteRendu()));
//        }
        return documentDto;
    }

    public Document deDocumentDto(DocumentDto documentDto)
    {
        if(documentDto == null) {
            return null;
        }
        Document document = new Document();
        BeanUtils.copyProperties(documentDto, document);
        if(documentDto.getTypeDocument() != null)
        {
            document.setTypeDocument(typeDocumentMapper.deTypeDocumentDto(documentDto.getTypeDocument()));
        }
        if(documentDto.getPersonne() != null)
        {
            document.setPersonne(personneMapper.dePersonneDto(documentDto.getPersonne()));
        }
        if(documentDto.getRdv() != null)
        {
           RDVDto rdvDto=new RDVDto();
           rdvDto=documentDto.getRdv();
           RDV rdv=new RDV();

           BeanUtils.copyProperties(rdvDto, rdv);
           if(rdvDto.getPaysDto()!=null) {
               rdv.setPays(paysMapper.dePaysDto(rdvDto.getPaysDto()));
           }
           if(rdvDto.getPersonnePhysiqueMoraleDto()!=null) {
               rdv.setPersonne(personneMapper.dePersonnePhysiqueMoraleDto(rdvDto.getPersonnePhysiqueMoraleDto()));
           }
           if(rdvDto.getQuartierDto()!=null) {
               rdv.setQuartier(quartierMapper.deQuartierDto(rdvDto.getQuartierDto()));
           }
           document.setRdv(rdv);
        }
//        if(documentDto.getCompteRendu() != null)
//        {
//            document.setCompteRendu(compteRenduMapper.deCompteRenduDto(documentDto.getCompteRendu()));
//        }
        if(documentDto.getCompteRendu() != null)
        {
            CompteRenduDto compteRenduDto=new CompteRenduDto();
            compteRenduDto=documentDto.getCompteRendu();
            CompteRendu compteRendu=new CompteRendu();

            BeanUtils.copyProperties(compteRenduDto, compteRendu);
//            if(rdvDto.getPaysDto()!=null) {
//                rdv.setPays(paysMapper.dePaysDto(rdvDto.getPaysDto()));
//            }
//            if(rdvDto.getPersonnePhysiqueMoraleDto()!=null) {
//                rdv.setPersonne(personneMapper.dePersonnePhysiqueMoraleDto(rdvDto.getPersonnePhysiqueMoraleDto()));
//            }
//            if(rdvDto.getQuartierDto()!=null) {
//                rdv.setQuartier(quartierMapper.deQuartierDto(rdvDto.getQuartierDto()));
//            }
            document.setCompteRendu(compteRendu);
        }
        return document;
    }
}
