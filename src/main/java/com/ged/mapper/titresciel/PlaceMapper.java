package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.PlaceDto;
import com.ged.entity.titresciel.Place;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class PlaceMapper {
    public PlaceDto dePlace(Place place)
    {
        if(place == null)
            return null;
        PlaceDto placeDto = new PlaceDto();
        BeanUtils.copyProperties(place, placeDto);
        return placeDto;
    }

    public Place dePlaceDto(PlaceDto placeDto)
    {
        if(placeDto == null)
        {
            return null;
        }
        Place place = new Place();
        BeanUtils.copyProperties(placeDto, place);
        return place;
    }
}
