package com.ged.service.standard.impl;

import com.ged.dao.standard.ProtoAlerteDao;
import com.ged.dto.standard.DiffusionAlerteDto;
import com.ged.dto.standard.PersonnelDto;
import com.ged.dto.standard.ProtoAlerteDto;
import com.ged.entity.standard.CleProtoAlerte;
import com.ged.entity.standard.ProtoAlerte;
import com.ged.service.standard.DiffusionAlerteService;
import com.ged.service.standard.ProtoAlerteService;
import com.ged.mapper.standard.AlerteMapper;
import com.ged.mapper.standard.DiffusionAlerteMapper;
import com.ged.mapper.standard.ModeleMsgAlerteMapper;
import com.ged.mapper.standard.ProtoAlerteMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProtoAlerteServiceImpl implements ProtoAlerteService {
    private final ProtoAlerteDao protoAlerteDao;
    private final ProtoAlerteMapper protoAlerteMapper;
    private final AlerteMapper alerteMapper;
    private final ModeleMsgAlerteMapper modeleMsgAlerteMapper;
    private final DiffusionAlerteService diffusionAlerteService;
    private final DiffusionAlerteMapper diffusionAlerteMapper;

    public ProtoAlerteServiceImpl(ProtoAlerteDao protoAlerteDao, ProtoAlerteMapper protoAlerteMapper, AlerteMapper alerteMapper, ModeleMsgAlerteMapper modeleMsgAlerteMapper, DiffusionAlerteService diffusionAlerteService, DiffusionAlerteMapper diffusionAlerteMapper) {
        this.protoAlerteDao = protoAlerteDao;
        this.protoAlerteMapper = protoAlerteMapper;
        this.alerteMapper = alerteMapper;
        this.modeleMsgAlerteMapper = modeleMsgAlerteMapper;
        this.diffusionAlerteService = diffusionAlerteService;
        this.diffusionAlerteMapper = diffusionAlerteMapper;
    }

    @Override
    public Page<ProtoAlerteDto> afficherProtoAlertes(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        Page<ProtoAlerte> protoAlertePage=protoAlerteDao.findAll(pageRequest);
        return new PageImpl<>(protoAlertePage.getContent().stream().map(protoAlerteMapper::deProtoAlerte).collect(Collectors.toList()),pageRequest,protoAlertePage.getTotalElements());
    }

    @Override
    public ProtoAlerte afficherProtoAlerteSelonId(CleProtoAlerte idProtoAlerte) {
        return protoAlerteDao.findById(idProtoAlerte).orElseThrow(()-> new EntityNotFoundException("Periodicité avec ID "+idProtoAlerte+" est introuvable"));
    }

    @Override
    public ProtoAlerteDto afficherProtoAlerte(CleProtoAlerte idProtoAlerte) {
        return protoAlerteMapper.deProtoAlerte(afficherProtoAlerteSelonId(idProtoAlerte));
    }

    @Override
    public ProtoAlerteDto creerProtoAlerte(ProtoAlerteDto protoAlerteDto) {
        ProtoAlerte protoAlerte = protoAlerteMapper.deProtoAlerteDto(protoAlerteDto);
        if(protoAlerteDto.getAlerte() != null && protoAlerteDto.getModeleMsgAlerte() != null)
        {
            CleProtoAlerte cleProtoAlerte = new CleProtoAlerte();
            cleProtoAlerte.setIdAlerte(protoAlerteDto.getAlerte().getIdAlerte());
            cleProtoAlerte.setIdModele(protoAlerteDto.getModeleMsgAlerte().getIdModele());
            protoAlerte.setIdProtoAlerte(cleProtoAlerte);

            protoAlerte.setAlerte(alerteMapper.deAlerteDto(protoAlerteDto.getAlerte()));
            protoAlerte.setModeleMsgAlerte(modeleMsgAlerteMapper.deModeleMsgAlerteDto(protoAlerteDto.getModeleMsgAlerte()));
            protoAlerte = protoAlerteDao.save(protoAlerte);
            Set<PersonnelDto> personnelDtos = protoAlerteDto.getPersonnels();
            for (PersonnelDto personnelDto : personnelDtos) {
                DiffusionAlerteDto diffusionAlerteDto = new DiffusionAlerteDto();
//                System.out.println("IdAlerte " + protoAlerteDto.getAlerte().getIdAlerte().toString());
//                System.out.println("IdModele " + protoAlerteDto.getModeleMsgAlerte().getIdModele().toString());
//                System.out.println("IdPersonnel " + personnelDto.getIdPersonne().toString());
                diffusionAlerteDto.setProtoAlerte(protoAlerteDto);
                diffusionAlerteDto.setPersonne(personnelDto);
                diffusionAlerteDto = diffusionAlerteService.creerDiffusionAlerte(diffusionAlerteDto);
//                protoAlerte.ajouterDiffusionAlerte(diffusionAlerteMapper.deDiffusionAlerteDto(diffusionAlerteDto));
            }
        }
        return protoAlerteMapper.deProtoAlerte(protoAlerte);
    }

    @Override
    public ProtoAlerteDto modifierProtoAlerte(ProtoAlerteDto protoAlerteDto) {
        ProtoAlerte protoAlerte = protoAlerteMapper.deProtoAlerteDto(protoAlerteDto);
        if(protoAlerteDto.getAlerte() != null && protoAlerteDto.getModeleMsgAlerte() != null)
        {
            CleProtoAlerte cleProtoAlerte = new CleProtoAlerte();
            cleProtoAlerte.setIdAlerte(protoAlerteDto.getAlerte().getIdAlerte());
            cleProtoAlerte.setIdModele(protoAlerteDto.getModeleMsgAlerte().getIdModele());
            protoAlerte.setIdProtoAlerte(cleProtoAlerte);

            protoAlerte.setAlerte(alerteMapper.deAlerteDto(protoAlerteDto.getAlerte()));
            protoAlerte.setModeleMsgAlerte(modeleMsgAlerteMapper.deModeleMsgAlerteDto(protoAlerteDto.getModeleMsgAlerte()));
//            protoAlerte = protoAlerteDao.save(protoAlerte);
            Set<PersonnelDto> personnelDtos = protoAlerteDto.getPersonnels();
            for (PersonnelDto personnelDto : personnelDtos) {
                DiffusionAlerteDto diffusionAlerteDto = new DiffusionAlerteDto();

//                diffusionAlerteDto.setAlerte(protoAlerteDto.getAlerte());
//                diffusionAlerteDto.setModeleMsgAlerte(protoAlerteDto.getModeleMsgAlerte());
                diffusionAlerteDto.setProtoAlerte(protoAlerteDto);
                diffusionAlerteDto.setPersonne(personnelDto);
//                diffusionAlerteDto = diffusionAlerteService.creerDiffusionAlerte(diffusionAlerteDto);
//                protoAlerte.ajouterDiffusionAlerte(diffusionAlerteMapper.deDiffusionAlerteDto(diffusionAlerteDto));
            }
        }
        return protoAlerteMapper.deProtoAlerte(protoAlerte);
    }

    @Override
    public void supprimerProtoAlerte(CleProtoAlerte idProtoAlerte) {
        protoAlerteDao.deleteById(idProtoAlerte);
    }
}
