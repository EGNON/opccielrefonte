package com.ged.mapper.opcciel;
import com.ged.dto.opcciel.OrdreSignataireDto;
import com.ged.entity.opcciel.OrdreSignataire;
import com.ged.mapper.standard.PersonneMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OrdreSignataireMapper {
    private final OrdreMapper OrdreMapper;
    private final PersonneMapper personneMapper;

    public OrdreSignataireMapper(OrdreMapper OrdreMapper, PersonneMapper personneMapper) {
        this.OrdreMapper = OrdreMapper;

        this.personneMapper = personneMapper;
    }

    public OrdreSignataireDto deOrdreSignataire(OrdreSignataire OrdreSignataire)
    {
        OrdreSignataireDto OrdreSignataireDto = new OrdreSignataireDto();
        BeanUtils.copyProperties(OrdreSignataire, OrdreSignataireDto);
        if(OrdreSignataire.getOrdre()!=null) {
            OrdreSignataireDto.setOrdre(OrdreMapper.deOrdre(OrdreSignataire.getOrdre()));
        }
        if(OrdreSignataire.getPersonne()!=null) {
            OrdreSignataireDto.setPersonne(personneMapper.dePersonne(OrdreSignataire.getPersonne()));
        }
        return OrdreSignataireDto;
    }

    public OrdreSignataire deOrdreSignataireDto(OrdreSignataireDto OrdreSignataireDTO)
    {
        OrdreSignataire OrdreSignataire = new OrdreSignataire();
        BeanUtils.copyProperties(OrdreSignataireDTO, OrdreSignataire);
        return OrdreSignataire;
    }
//    public OrdreSignataireDto deOrdreSignataireProjection(OrdreSignataireProjection OrdreSignataireProjection)
//    {
//        if(OrdreSignataireProjection == null)
//            return null;
//        OrdreSignataireDto OrdreSignataireDto = new OrdreSignataireDto();
//        BeanUtils.copyProperties(OrdreSignataireProjection, OrdreSignataireDto);
//
//        if(OrdreSignataireProjection.getNatureOperation() != null) {
//            OrdreSignataireDto.setNatureOperation(natureOperationMapper.deNatureOperation(OrdreSignataireProjection.getNatureOperation()));
//        }
//
//        if(OrdreSignataireProjection.getOpcvm()!=null) {
//            OrdreSignataireDto.setOpcvm(opcvmMapper.deOpcvm(OrdreSignataireProjection.getOpcvm()));
//        }
//        return OrdreSignataireDto;
//    }
}
