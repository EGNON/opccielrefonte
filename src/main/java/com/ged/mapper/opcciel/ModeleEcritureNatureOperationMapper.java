package com.ged.mapper.opcciel;

import com.ged.dto.opcciel.comptabilite.ModeleEcritureNatureOperationDto;
import com.ged.entity.opcciel.comptabilite.ModeleEcritureNatureOperation;
import com.ged.mapper.titresciel.TypeTitreMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ModeleEcritureNatureOperationMapper {
    private final ModeleEcritureMapper modeleEcritureMapper;
    private final NatureOperationMapper NatureOperationMapper;
    private final TypeTitreMapper typeTitreMapper;

    public ModeleEcritureNatureOperationMapper(ModeleEcritureMapper modeleEcritureMapper, NatureOperationMapper NatureOperationMapper, TypeTitreMapper typeTitreMapper) {
        this.modeleEcritureMapper = modeleEcritureMapper;
        this.NatureOperationMapper = NatureOperationMapper;
        this.typeTitreMapper = typeTitreMapper;
    }

    public ModeleEcritureNatureOperationDto deModeleEcritureNatureOperation(ModeleEcritureNatureOperation ModeleEcritureNatureOperation)
    {
        if(ModeleEcritureNatureOperation == null)
            return null;
        ModeleEcritureNatureOperationDto ModeleEcritureNatureOperationDto = new ModeleEcritureNatureOperationDto();
        BeanUtils.copyProperties(ModeleEcritureNatureOperation, ModeleEcritureNatureOperationDto);
        if(ModeleEcritureNatureOperation.getModeleEcriture()!=null){
            ModeleEcritureNatureOperationDto.setModeleEcriture(modeleEcritureMapper.deModeleEcriture(ModeleEcritureNatureOperation.getModeleEcriture()));
        }
        if(ModeleEcritureNatureOperation.getNatureOperation()!=null){
            ModeleEcritureNatureOperationDto.setNatureOperation(NatureOperationMapper.deNatureOperation(ModeleEcritureNatureOperation.getNatureOperation()));
        }
        /*if(ModeleEcritureNatureOperation.getTypeTitre()!=null){
            ModeleEcritureNatureOperationDto.setTypeTitre(typeTitreMapper.deTypeTitre(ModeleEcritureNatureOperation.getTypeTitre()));
        }*/
        return ModeleEcritureNatureOperationDto;
    }

    public ModeleEcritureNatureOperation deModeleEcritureNatureOperationDto(ModeleEcritureNatureOperationDto ModeleEcritureNatureOperationDto)
    {
        ModeleEcritureNatureOperation ModeleEcritureNatureOperation = new ModeleEcritureNatureOperation();
        BeanUtils.copyProperties(ModeleEcritureNatureOperationDto, ModeleEcritureNatureOperation);
        return ModeleEcritureNatureOperation;
    }
}
