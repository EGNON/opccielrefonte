package com.ged.mapper.titresciel;

import com.ged.dto.titresciel.BanqueDto;
import com.ged.entity.titresciel.Banque;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BanqueMapper {
    public BanqueDto deBanque(Banque Banque)
    {
        BanqueDto BanqueDto = new BanqueDto();
        BeanUtils.copyProperties(Banque, BanqueDto);
        return BanqueDto;
    }

    public Banque deBanqueDto(BanqueDto BanqueDTO)
    {
        Banque Banque = new Banque();
        BeanUtils.copyProperties(BanqueDTO, Banque);
        return Banque;
    }
}
