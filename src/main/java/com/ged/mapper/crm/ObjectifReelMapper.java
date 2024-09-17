package com.ged.mapper.crm;

import com.ged.dto.crm.ObjectifReelDto;
import com.ged.entity.crm.ObjectifReel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ObjectifReelMapper {
    public ObjectifReelDto deObjectifReel(ObjectifReel objectifReel)
    {
        ObjectifReelDto objectifReelDto = new ObjectifReelDto();
        BeanUtils.copyProperties(objectifReel, objectifReelDto);
        return objectifReelDto;
    }

    public ObjectifReel deObjectifReelDto(ObjectifReelDto objectifReelDto)
    {
        ObjectifReel objectifReel= new ObjectifReel();
        BeanUtils.copyProperties(objectifReelDto, objectifReel);
        return objectifReel;
    }

}
