package com.ged.service.standard.impl;

import com.ged.dao.standard.PaysDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PaysDto;
import com.ged.entity.standard.Pays;
import com.ged.mapper.standard.PaysMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.PaysService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PaysServiceImpl implements PaysService {
    private final PaysDao paysDao;
    private final PaysMapper paysMapper;

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            return ResponseHandler.generateResponse(
                    "Liste de tous les pays",
                    HttpStatus.OK,
                    paysDao.findAll().stream().map(paysMapper::dePays).collect(Collectors.toList()));
        }
        catch(Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public Page<PaysDto> afficherPays(int page, int size) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleFr");
        PageRequest pageRequest=PageRequest.of(page,size,sort);
        Page<Pays> paysPage=paysDao.findAll(pageRequest);
        return new PageImpl<>(paysPage.getContent().stream().map(paysMapper::dePays).collect(Collectors.toList()),pageRequest,paysPage.getTotalElements());
    }

    @Override
    public DataTablesResponse<PaysDto> afficherPaysSelonEstGafi(boolean estGafi, DatatableParameters parameters) {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleFr");
        Pageable pageable = PageRequest.of(
                parameters.getStart()/parameters.getLength(), parameters.getLength(),sort);
        Page<Pays> paysPage = paysDao.findByEstGafi(estGafi, pageable);

        List<PaysDto> content = paysPage.getContent().stream().map(paysMapper::dePays).collect(Collectors.toList());
        DataTablesResponse<PaysDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(parameters.getDraw());
        dataTablesResponse.setRecordsFiltered((int)paysPage.getTotalElements());
        dataTablesResponse.setRecordsTotal((int)paysPage.getTotalElements());
        dataTablesResponse.setData(content);
        return dataTablesResponse;
    }

    @Override
    public List<PaysDto> afficherPaysSelonEstGafi(boolean estGafi) {
//        Sort sort=Sort.by(Sort.Direction.ASC,"libelleFr");
        List<Pays> paysList = paysDao.findByEstGafiOrderByLibelleFrAsc(estGafi);
        return paysList.stream().map(paysMapper::dePays).collect(Collectors.toList());
    }

    @Override
    public List<PaysDto> afficherListePays() {
        Sort sort=Sort.by(Sort.Direction.ASC,"libelleFr");
        return paysDao.findAll(sort).stream().map(paysMapper::dePays).collect(Collectors.toList());
    }

    @Override
    public Pays afficherPaysSelonId(long idPays) {
        return paysDao.findById(idPays).orElseThrow(()-> new EntityNotFoundException("Pays avec ID "+idPays+" est introuvable"));
    }

    @Override
    public PaysDto rechercherPaysParLibelleFr(String libelleFr) {
        Pays pays=paysDao.findByLibelleFrIgnoreCase(libelleFr).orElse(null);
        if(pays==null)
            return null;
        else
            return paysMapper.dePays(pays);
    }

    @Override
    public PaysDto afficherPays(long idPays) {
        return paysMapper.dePays(afficherPaysSelonId(idPays));
    }

    @Override
    public PaysDto creerPays(PaysDto paysDto) {
        Pays pays=paysMapper.dePaysDto(paysDto);
        Pays paysSave=paysDao.save(pays);
        return paysMapper.dePays(paysSave);
    }

    @Override
    public PaysDto modifierPays(PaysDto paysDto) {
        Pays pays=paysMapper.dePaysDto(paysDto);
        Pays paysMaj=paysDao.save(pays);
        return paysMapper.dePays(paysMaj);
    }

    @Override
    public  void supprimerPays(long idPays) {
        paysDao.deleteById(idPays);
    }
}
