package com.ged.service.opcciel.impl;

import com.ged.dao.opcciel.comptabilite.OperationDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.request.ConsultationEcritureRequest;
import com.ged.dto.response.ConsultattionEcritureRes;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OperationComptableServiceImpl implements OperationService {
    private final OperationDao operationDao;

    public OperationComptableServiceImpl(OperationDao operationDao) {
        this.operationDao = operationDao;
    }

    @Override
    public ResponseEntity<Object> afficherTous(ConsultationEcritureRequest request) {
        try {
            DatatableParameters parameters = request.getDatatableParameters();
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<ConsultattionEcritureRes> operationPage;
            if(parameters.getSearch() != null && !parameters.getSearch().getValue().isEmpty()) {
                operationPage = new PageImpl<>(new ArrayList<>());
            }
            else {
                operationPage = operationDao.listeOperationsFiltree(
                    request.getIdOpcvm() == 0L ? null : request.getIdOpcvm(),
                    request.getIdOperation() == 0L ? null : request.getIdOperation(),
                    request.getIdTransaction() == 0L ? null :  request.getIdTransaction(),
                    request.getNatureOperation() != null ? request.getNatureOperation().getCodeNatureOperation().trim() : null,
                    request.getDateDebut(),
                    request.getDateFin(),
                    pageable
                );
            }
            List<ConsultattionEcritureRes> content = operationPage.getContent().stream().toList();
            DataTablesResponse<ConsultattionEcritureRes> dataTablesResponse = new DataTablesResponse<>();
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
