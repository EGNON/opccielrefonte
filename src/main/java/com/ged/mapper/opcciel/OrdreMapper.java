package com.ged.mapper.opcciel;
import com.ged.dto.opcciel.OrdreDto;
import com.ged.entity.opcciel.Ordre;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.mapper.titresciel.TitreMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class OrdreMapper {
    private final OpcvmMapper opcvmMapper;
    private final NatureOperationMapper natureOperationMapper;
    private final PersonneMapper personneMapper;
    private final TitreMapper titreMapper;
    private final TypeOrdreMapper typeOrdreMapper;

    public OrdreMapper(OpcvmMapper opcvmMapper, NatureOperationMapper natureOperationMapper, PersonneMapper personneMapper, TitreMapper titreMapper, TypeOrdreMapper typeOrdreMapper) {
        this.opcvmMapper = opcvmMapper;
        this.natureOperationMapper = natureOperationMapper;
        this.personneMapper = personneMapper;
        this.titreMapper = titreMapper;
        this.typeOrdreMapper = typeOrdreMapper;
    }

    public OrdreDto deOrdre(Ordre Ordre)
    {
        OrdreDto OrdreDto = new OrdreDto();
        BeanUtils.copyProperties(Ordre, OrdreDto);
        if(Ordre.getOpcvm()!=null) {
            OrdreDto.setOpcvm(opcvmMapper.deOpcvm(Ordre.getOpcvm()));
        }

        if(Ordre.getNatureOperation()!=null) {
            OrdreDto.setNatureOperation(natureOperationMapper.deNatureOperation(Ordre.getNatureOperation()));
        }
        if(Ordre.getTitre()!=null) {
            OrdreDto.setTitre(titreMapper.deTitre(Ordre.getTitre()));
        }
        if(Ordre.getPersonne()!=null) {
            OrdreDto.setPersonne(personneMapper.dePersonne(Ordre.getPersonne()));
        }
        if(Ordre.getTypeOrdre()!=null) {
            OrdreDto.setTypeOrdre(typeOrdreMapper.deTypeOrdre(Ordre.getTypeOrdre()));
        }
        return OrdreDto;
    }

    public Ordre deOrdreDto(OrdreDto OrdreDTO)
    {
        Ordre Ordre = new Ordre();
        BeanUtils.copyProperties(OrdreDTO, Ordre);
        return Ordre;
    }
//    public OrdreDto deOrdreProjection(OrdreProjection OrdreProjection)
//    {
//        if(OrdreProjection == null)
//            return null;
//        OrdreDto OrdreDto = new OrdreDto();
//        BeanUtils.copyProperties(OrdreProjection, OrdreDto);
//
//        if(OrdreProjection.getNatureOperation() != null) {
//            OrdreDto.setNatureOperation(natureOperationMapper.deNatureOperation(OrdreProjection.getNatureOperation()));
//        }
//
//        if(OrdreProjection.getOpcvm()!=null) {
//            OrdreDto.setOpcvm(opcvmMapper.deOpcvm(OrdreProjection.getOpcvm()));
//        }
//        return OrdreDto;
//    }
}
