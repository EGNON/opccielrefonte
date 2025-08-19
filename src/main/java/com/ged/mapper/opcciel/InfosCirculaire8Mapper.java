package com.ged.mapper.opcciel;
import com.ged.dto.opcciel.InfosCirculaire8Dto;
import com.ged.entity.opcciel.InfosCirculaire8;
import com.ged.mapper.standard.PersonneMapper;
import com.ged.mapper.titresciel.TitreMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class InfosCirculaire8Mapper {
    private final OpcvmMapper opcvmMapper;
   

    public InfosCirculaire8Mapper(OpcvmMapper opcvmMapper) {
        this.opcvmMapper = opcvmMapper;
        
    }

    public InfosCirculaire8Dto deInfosCirculaire8(InfosCirculaire8 InfosCirculaire8)
    {
        InfosCirculaire8Dto InfosCirculaire8Dto = new InfosCirculaire8Dto();
        BeanUtils.copyProperties(InfosCirculaire8, InfosCirculaire8Dto);
        if(InfosCirculaire8.getOpcvm()!=null) {
            InfosCirculaire8Dto.setOpcvm(opcvmMapper.deOpcvm(InfosCirculaire8.getOpcvm()));
        }


        return InfosCirculaire8Dto;
    }

    public InfosCirculaire8 deInfosCirculaire8Dto(InfosCirculaire8Dto InfosCirculaire8DTO)
    {
        InfosCirculaire8 InfosCirculaire8 = new InfosCirculaire8();
        BeanUtils.copyProperties(InfosCirculaire8DTO, InfosCirculaire8);
        return InfosCirculaire8;
    }

}
