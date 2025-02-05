package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.OperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.request.ConsultationEcritureRequest;
import com.ged.entity.opcciel.comptabilite.Operation;
import com.ged.mapper.opcciel.OperationMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OperationComptableServiceImpl implements OperationService {
    private final OperationDao operationDao;
    private final OperationMapper operationMapper;

    public OperationComptableServiceImpl(OperationDao operationDao, OperationMapper operationMapper) {
        this.operationDao = operationDao;
        this.operationMapper = operationMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous(ConsultationEcritureRequest request) {
        try {
            DatatableParameters parameters = request.getDatatableParameters();
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Operation> operationPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                operationPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                operationPage = operationDao.listeOperationsFiltree(
                        request.getIdOpcvm(),
                        request.getNatureOperation().getCodeNatureOperation(),
                        request.getDateDebut(),
                        request.getDateFin(),
                        pageable);
            }
            List<OperationDto> content = operationPage.getContent().stream().map(operationMapper::deOperation).toList();
            DataTablesResponse<OperationDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)operationPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)operationPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des opérations",
                    HttpStatus.OK,
                    dataTablesResponse);
        }
        catch(Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
