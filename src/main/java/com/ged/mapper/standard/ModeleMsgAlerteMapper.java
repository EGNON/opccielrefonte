package com.ged.mapper.standard;

import com.ged.dto.crm.RDVDto;
import com.ged.dto.standard.ModeleMsgAlerteDto;
import com.ged.entity.standard.ModeleMsgAlerte;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ModeleMsgAlerteMapper {
    private final TypeModeleMapper typeModeleMapper;
    private final ModelMapper modelMapper;
    private final PaysMapper paysMapper;
    private final QuartierMapper quartierMapper;
    private final PersonneMapper personneMapper;
    private final DocumentMapper documentMapper;
    private final CommuneMapper communeMapper;
    public ModeleMsgAlerteMapper(TypeModeleMapper typeModeleMapper, ModelMapper modelMapper, PaysMapper paysMapper, QuartierMapper quartierMapper, PersonneMapper personneMapper, DocumentMapper documentMapper, CommuneMapper communeMapper) {
        this.typeModeleMapper = typeModeleMapper;

        this.modelMapper = modelMapper;
        this.paysMapper = paysMapper;
        this.quartierMapper = quartierMapper;
        this.personneMapper = personneMapper;
        this.documentMapper = documentMapper;
        this.communeMapper = communeMapper;
    }

    public ModeleMsgAlerteDto deModeleMsgAlerte(ModeleMsgAlerte modeleMsgAlerte)
    {
        if(modeleMsgAlerte == null) {
            return null;
        }

        ModeleMsgAlerteDto modeleMsgAlerteDto = new ModeleMsgAlerteDto();
        BeanUtils.copyProperties(modeleMsgAlerte, modeleMsgAlerteDto);
        if(modeleMsgAlerte.getTypeModele()!=null)
            modeleMsgAlerteDto.setTypeModele(typeModeleMapper.deTypeModele(modeleMsgAlerte.getTypeModele()));

        if(modeleMsgAlerte.getRdvs() != null)
        {
            Set<RDVDto> rdvDtoSet=new HashSet<>();
            modeleMsgAlerte.getRdvs().forEach(rdv -> {
                RDVDto rdvDto = new RDVDto();
                BeanUtils.copyProperties(rdv, rdvDto);
                rdvDto.setPaysDto(paysMapper.dePays(rdv.getPays()));
                rdvDto.setPersonnePhysiqueMoraleDto(personneMapper.dePersonnePhysiqueMorale(rdv.getPersonne()));
                rdvDto.setQuartierDto(quartierMapper.deQuartier(rdv.getQuartier()));
                rdvDtoSet.add(rdvDto);
            });
            modeleMsgAlerteDto.setRdvs(rdvDtoSet);
        }
        return modeleMsgAlerteDto;
    }

    public ModeleMsgAlerte deModeleMsgAlerteDto(ModeleMsgAlerteDto modeleMsgAlerteDto)
    {
        if(modeleMsgAlerteDto == null) {
            return null;
        }
        
        ModeleMsgAlerte modeleMsgAlerte = new ModeleMsgAlerte();
        BeanUtils.copyProperties(modeleMsgAlerteDto, modeleMsgAlerte);
        return modeleMsgAlerte;
    }
}
