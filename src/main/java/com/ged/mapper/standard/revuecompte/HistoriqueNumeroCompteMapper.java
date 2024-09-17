package com.ged.mapper.standard.revuecompte;

import com.ged.dto.standard.revuecompte.HistoriqueNumeroCompteDto;
import com.ged.entity.standard.revuecompte.HistoriqueNumeroCompte;
import com.ged.mapper.standard.PersonneMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class HistoriqueNumeroCompteMapper {
    private final PersonneMapper personneMapper;

    public HistoriqueNumeroCompteMapper(PersonneMapper personneMapper){
        this.personneMapper = personneMapper;
    }

    public HistoriqueNumeroCompteDto deHistoriqueNumeroCompte(HistoriqueNumeroCompte HistoriqueNumeroCompte)
    {
        HistoriqueNumeroCompteDto HistoriqueNumeroCompteDTO = new HistoriqueNumeroCompteDto();
        BeanUtils.copyProperties(HistoriqueNumeroCompte, HistoriqueNumeroCompteDTO);
        HistoriqueNumeroCompteDTO.setPersonne(personneMapper.dePersonne(HistoriqueNumeroCompte.getPersonne()));
        return HistoriqueNumeroCompteDTO;
    }

    public HistoriqueNumeroCompte deHistoriqueNumeroCompteDto(HistoriqueNumeroCompteDto HistoriqueNumeroCompteDto)
    {
        HistoriqueNumeroCompte HistoriqueNumeroCompte = new HistoriqueNumeroCompte();
        BeanUtils.copyProperties(HistoriqueNumeroCompteDto, HistoriqueNumeroCompte);
        HistoriqueNumeroCompte.setPersonne(personneMapper.dePersonneDto(HistoriqueNumeroCompteDto.getPersonne()));
        return HistoriqueNumeroCompte;
    }
}
