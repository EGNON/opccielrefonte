package com.ged.mapper.standard;

import com.ged.dto.standard.*;
import com.ged.entity.standard.*;
import com.ged.mapper.opcciel.OpcvmMapper;
import com.ged.mapper.titresciel.ClasseTitreMapper;
import com.ged.mapper.titresciel.DepositaireMapper;
import com.ged.mapper.titresciel.PlaceMapper;
import com.ged.mapper.titresciel.RegistraireMapper;
import com.ged.projection.TarificationProjection;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class TarificationOrdinaireMapper {
    private final PlaceMapper placeMapper;
    private final ClasseTitreMapper classeTitreMapper;
    private final PersonneMapper personneMapper;
    private final DepositaireMapper depositaireMapper;
    private final RegistraireMapper registraireMapper;
    private final OpcvmMapper opcvmMapper;

    public TarificationOrdinaireMapper(PlaceMapper placeMapper, ClasseTitreMapper classeTitreMapper, PersonneMapper personneMapper, DepositaireMapper depositaireMapper, RegistraireMapper registraireMapper, OpcvmMapper opcvmMapper) {
        this.placeMapper = placeMapper;
        this.classeTitreMapper = classeTitreMapper;
        this.personneMapper = personneMapper;
        this.depositaireMapper = depositaireMapper;
        this.registraireMapper = registraireMapper;
        this.opcvmMapper = opcvmMapper;
    }


    public TarificationOrdinaireDto deTarificationOrdinaire(TarificationOrdinaire TarificationOrdinaire)
    {
        if(TarificationOrdinaire == null)
            return null;
        TarificationOrdinaireDto TarificationOrdinaireDto = new TarificationOrdinaireDto();
        BeanUtils.copyProperties(TarificationOrdinaire, TarificationOrdinaireDto);
        if(TarificationOrdinaire.getPlace() != null) {
            TarificationOrdinaireDto.setPlace(placeMapper.dePlace(TarificationOrdinaire.getPlace()));
        }
        if(TarificationOrdinaire.getClasseTitre()!=null) {
            TarificationOrdinaireDto.setClasseTitre(classeTitreMapper.deClasseTitre(TarificationOrdinaire.getClasseTitre()));
        }
        /*if(TarificationOrdinaire.getDepositaire()!=null) {
            TarificationOrdinaireDto.setDepositaire(personneMapper.dePersonne(TarificationOrdinaire.getDepositaire()));
        }
        if(TarificationOrdinaire.getRegistraire()!=null) {
            TarificationOrdinaireDto.setRegistraire(personneMapper.dePersonne(TarificationOrdinaire.getRegistraire()));
        }*/
        if(TarificationOrdinaire.getDepositaire()!=null) {
            TarificationOrdinaireDto.setDepositaire(personneMapper.dePersonne(TarificationOrdinaire.getDepositaire()));
        }
        if(TarificationOrdinaire.getRegistraire()!=null) {
            TarificationOrdinaireDto.setRegistraire(personneMapper.dePersonne(TarificationOrdinaire.getRegistraire()));
        }
        if(TarificationOrdinaire.getOpcvm()!=null) {
            TarificationOrdinaireDto.setOpcvm(opcvmMapper.deOpcvm(TarificationOrdinaire.getOpcvm()));
        }
        return TarificationOrdinaireDto;
    }

    public TarificationOrdinaire deTarificationOrdinaireDto(TarificationOrdinaireDto TarificationOrdinaireDto)
    {
        if(TarificationOrdinaireDto == null)
            return null;
        TarificationOrdinaire TarificationOrdinaire = new TarificationOrdinaire();
        BeanUtils.copyProperties(TarificationOrdinaireDto, TarificationOrdinaire);

        return  TarificationOrdinaire;
    }
    public TarificationOrdinaireDto deTarificationProjection(TarificationProjection tarificationProjection)
    {
        if(tarificationProjection == null)
            return null;
        TarificationOrdinaireDto tarificationOrdinaireDto = new TarificationOrdinaireDto();
        BeanUtils.copyProperties(tarificationProjection, tarificationOrdinaireDto);

        if(tarificationProjection.getPlace() != null) {
            tarificationOrdinaireDto.setPlace(placeMapper.dePlace(tarificationProjection.getPlace()));
        }
        if(tarificationProjection.getClasseTitre()!=null) {
            tarificationOrdinaireDto.setClasseTitre(classeTitreMapper.deClasseTitre(tarificationProjection.getClasseTitre()));
        }
        if(tarificationProjection.getDepositaire()!=null) {
//            tarificationOrdinaireDto.setDepositaire(personneMapper.dePersonne(tarificationProjection.getDepositaire()));
        }
        if(tarificationProjection.getRegistraire()!=null) {
//            tarificationOrdinaireDto.setRegistraire(personneMapper.dePersonne(tarificationProjection.getRegistraire()));
        }
        if(tarificationProjection.getOpcvm()!=null) {
            tarificationOrdinaireDto.setOpcvm(opcvmMapper.deOpcvm(tarificationProjection.getOpcvm()));
        }
        return tarificationOrdinaireDto;
    }
}
