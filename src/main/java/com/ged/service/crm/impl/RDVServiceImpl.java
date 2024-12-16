package com.ged.service.crm.impl;

import com.ged.dao.crm.RDVDao;
import com.ged.dao.standard.ModeleMsgAlerteDao;
import com.ged.dto.crm.RDVDto;
import com.ged.entity.crm.RDV;
import com.ged.entity.standard.ModeleMsgAlerte;
import com.ged.mapper.crm.RDVMapper;
import com.ged.projection.RDVProjection;
import com.ged.service.crm.AgentConcerneService;
import com.ged.service.crm.RDVService;
import com.ged.service.standard.DocumentService;
import com.ged.service.standard.EnvoiMailService;
import com.ged.service.standard.MailService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RDVServiceImpl implements RDVService {
    private final RDVDao rdvDao;
    private final ModeleMsgAlerteDao modeleMsgAlerteDao;
    private final MailService mailService;
    private final EnvoiMailService envoiMailService;
    private final RDVMapper rdvMapper;
    private final AgentConcerneService agentConcerneService;
    private final DocumentService documentService;

    public RDVServiceImpl(RDVDao rdvDao, ModeleMsgAlerteDao modeleMsgAlerteDao, MailService mailService, EnvoiMailService envoiMailService, RDVMapper rdvMapper, AgentConcerneService agentConcerneService, DocumentService documentService) {
        this.rdvDao = rdvDao;
        this.modeleMsgAlerteDao = modeleMsgAlerteDao;
        this.mailService = mailService;
        this.envoiMailService = envoiMailService;
        this.rdvMapper = rdvMapper;

        this.agentConcerneService = agentConcerneService;
        this.documentService = documentService;
    }

    @Override
    public Page<RDVDto> afficherRDVs(int page, int size) {
        PageRequest pageRequest=PageRequest.of(page,size);
        Page<RDV> rdvPage=rdvDao.findAll(pageRequest);
        return new PageImpl<>(rdvPage.getContent().stream().map(rdvMapper::deRDV).collect(Collectors.toList()),pageRequest,rdvPage.getTotalElements());
    }

    @Override
    public List<RDVDto> afficherRDVs() {
        return rdvDao.findAll().stream().map(rdvMapper::deRDV).collect(Collectors.toList());
    }

    @Override
    public List<RDVDto> afficherRDVsSurCR() {
        return rdvDao.afficherRDV().stream().map(rdvMapper::deRDVProjection).collect(Collectors.toList());
    }

    @Override
    public Page<RDVDto> afficherRDVs2(int page, int size) {
        Pageable pageRequest=PageRequest.of(page,size);
        Page<RDVProjection> rdvPage=rdvDao.afficherRDV(pageRequest);
        return new PageImpl<>(rdvPage.stream().map(rdvMapper::deRDVProjection).collect(Collectors.toList()),pageRequest,rdvPage.getTotalElements());
    }

    @Override
    public RDV afficherRDVSelonId(long idRDV) {
        return rdvDao.findById(idRDV);//.orElseThrow(()->new EntityNotFoundException("Rendez-vous avec ID "+idRDV+" est introuvable"));
    }

    @Override
    public List<RDVDto> afficherRDVSelonModeleMsgAlerte(long idModele) {
        ModeleMsgAlerte modeleMsgAlerte=new ModeleMsgAlerte();
        modeleMsgAlerte=modeleMsgAlerteDao.findById(idModele).orElseThrow();
        return rdvDao.findByModeleMsgAlerte(modeleMsgAlerte).stream().map(rdvMapper::deRDV).collect(Collectors.toList());
    }

    @Override
    public RDVDto afficherRDVSelonIdParProjection(long idRDV) {
        RDVProjection rdvProjection=rdvDao.afficherRDVSelonId(idRDV);
        if(rdvProjection==null)
            rdvProjection=rdvDao.afficherRDVSelonId2(idRDV);

        RDVDto rdvDto=rdvMapper.deRDVProjection(rdvProjection);

        return rdvDto;
    }

    @Override
    public RDVDto afficherRDV(long idRDV) {
        return rdvMapper.deRDV(afficherRDVSelonId(idRDV));
    }

    @Override
    public RDVDto creerRDV(RDVDto rdvDto) {
        RDV rdv=rdvMapper.deRDVDto(rdvDto);
        RDV rdvSave=rdvDao.save(rdv);
        return rdvMapper.deRDV(rdvSave);
    }

    @Override
    public RDVDto modifierRDV(RDVDto rdvDto) {
        RDV rdv=rdvMapper.deRDVDto(rdvDto);
        RDV rdvMaj=rdvDao.save(rdv);
        return rdvMapper.deRDV(rdvMaj);
    }

    @Override
    public int modifierUnePartieDeRDV(RDVDto rdvDto) {
        return rdvDao.updateRDV(rdvDto.getIdRdv(),
                                                rdvDto.getDateDebReelle(),
                                                rdvDto.getHeureDebReelle(),
                                                rdvDto.getDateFinReelle(),
                                                rdvDto.getHeureFinReelle());
    }

    @Override
    public void supprimerRDV(long idRDV) {
//        agentConcerneService.supprimerAgentConcerneSelonRDV(idRDV);
//        documentService.supprimerDocumentSelonRDV(idRDV);
//        mailService.supprimerMailSelonRDV(idRDV);
        rdvDao.deleteById(idRDV);
    }
}
