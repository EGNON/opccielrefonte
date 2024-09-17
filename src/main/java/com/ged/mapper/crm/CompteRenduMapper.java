package com.ged.mapper.crm;

import com.ged.dto.standard.DocumentDto;
import com.ged.entity.standard.Document;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.mapper.standard.DocumentMapper;
import com.ged.mapper.standard.UtilisateurMapper;
import com.ged.dto.crm.CompteRenduDto;
import com.ged.entity.crm.CompteRendu;
import com.ged.projection.CompteRenduProjection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CompteRenduMapper {
    private final OpcvmMapper opcvmMapper;
    private final RDVMapper rdvMapper;
    private final UtilisateurMapper utilisateurMapper;
    private final DocumentMapper documentMapper;

    public CompteRenduMapper(OpcvmMapper opcvmMapper, RDVMapper rdvMapper, UtilisateurMapper utilisateurMapper, DocumentMapper documentMapper) {
        this.opcvmMapper = opcvmMapper;
        this.rdvMapper = rdvMapper;
        this.utilisateurMapper = utilisateurMapper;
        this.documentMapper = documentMapper;
    }
    public CompteRenduDto deCompteRenduProjection(CompteRenduProjection compteRendu)
    {
        CompteRenduDto compteRenduDto = new CompteRenduDto();
        BeanUtils.copyProperties(compteRendu, compteRenduDto);
        if(compteRendu.getOpcvmASouscrire() != null)
        {
            compteRenduDto.setOpcvmASouscrire(opcvmMapper.deOpcvm(compteRendu.getOpcvmASouscrire()));
        }
        if(compteRendu.getOpcvmSouscrit() != null)
        {
            compteRenduDto.setOpcvmSouscrit(opcvmMapper.deOpcvm(compteRendu.getOpcvmSouscrit()));
        }
        if(compteRendu.getRdv() != null)
        {
            compteRenduDto.setRdv(rdvMapper.deRDV(compteRendu.getRdv()));
        }

        return compteRenduDto;
    }
    public CompteRenduDto deCompteRendu(CompteRendu compteRendu)
    {
        CompteRenduDto compteRenduDto = new CompteRenduDto();
        BeanUtils.copyProperties(compteRendu, compteRenduDto);
        if(compteRendu.getOpcvmASouscrire() != null)
        {
            compteRenduDto.setOpcvmASouscrire(opcvmMapper.deOpcvm(compteRendu.getOpcvmASouscrire()));
        }
        if(compteRendu.getOpcvmSouscrit() != null)
        {
            compteRenduDto.setOpcvmSouscrit(opcvmMapper.deOpcvm(compteRendu.getOpcvmSouscrit()));
        }
        if(compteRendu.getRdv() != null)
        {
            compteRenduDto.setRdv(rdvMapper.deRDV(compteRendu.getRdv()));
        }
        if(compteRendu.getCreateur() != null)
        {
            compteRenduDto.setCreateur(utilisateurMapper.deUtilisateur2(compteRendu.getCreateur()));
        }
        if(compteRendu.getDocuments() != null)
        {
            Set<DocumentDto> documents=new HashSet<>();
            compteRendu.getDocuments().forEach(document -> {
                documents.add(documentMapper.deDocument(document));

            });
            compteRenduDto.setDocuments(documents);
        }
        return compteRenduDto;
    }

    public CompteRendu deCompteRenduDto(CompteRenduDto compteRenduDto)
    {
        CompteRendu compteRendu = new CompteRendu();
        BeanUtils.copyProperties(compteRenduDto, compteRendu);
        if(compteRenduDto.getOpcvmASouscrire() != null)
        {
            compteRendu.setOpcvmASouscrire(opcvmMapper.deOpcvmDto(compteRenduDto.getOpcvmASouscrire()));
        }
        if(compteRenduDto.getOpcvmSouscrit() != null)
        {
            compteRendu.setOpcvmSouscrit(opcvmMapper.deOpcvmDto(compteRenduDto.getOpcvmSouscrit()));
        }
        if(compteRenduDto.getRdv() != null)
        {
            compteRendu.setRdv(rdvMapper.deRDVDto(compteRenduDto.getRdv()));
        }
        if(compteRenduDto.getCreateur() != null)
        {
            compteRendu.setCreateur(utilisateurMapper.deUtilisateur2Dto(compteRenduDto.getCreateur()));
        }
        if(compteRenduDto.getDocuments() != null)
        {
            Set<Document> documents=new HashSet<>();
            compteRenduDto.getDocuments().forEach(document -> {
                documents.add(documentMapper.deDocumentDto(document));

            });
            compteRendu.setDocuments(documents);
        }
        return compteRendu;
    }
}
