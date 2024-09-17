package com.ged.service.standard.impl;

import com.ged.advice.EntityNotFoundException;
import com.ged.dao.security.PermissionDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.security.PermissionDto;
import com.ged.entity.security.Permission;
import com.ged.mapper.standard.PermissionMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.PermissionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    private final PermissionDao permissionDao;
    private final PermissionMapper permissionMapper;

    public PermissionServiceImpl(PermissionDao permissionDao, PermissionMapper permissionMapper) {
        this.permissionDao = permissionDao;
        this.permissionMapper = permissionMapper;
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            return ResponseHandler.generateResponse(
                    "Liste de toutes les permissions",
                    HttpStatus.OK,
                    permissionDao.findAll(Sort.by(Sort.Direction.ASC, "libellePermis")).stream()
                            .map(permissionMapper::dePermission).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"libellePermis");
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength(),sort);
            Page<Permission> permissionPage;
            if(parameters.getSearch() != null && !StringUtils.isEmpty(parameters.getSearch().getValue()))
            {
                permissionPage = permissionDao.findByLibellePermisContainsIgnoreCase(parameters.getSearch().getValue(), pageable);
            }
            else {
                permissionPage = permissionDao.findAll(pageable);
            }
            List<PermissionDto> content = permissionPage.getContent().stream().map(permissionMapper::dePermission).collect(Collectors.toList());
            DataTablesResponse<PermissionDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)permissionPage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)permissionPage.getTotalElements());
            dataTablesResponse.setData(content);
            return ResponseHandler.generateResponse(
                    "Liste des permissions par page datatable",
                    HttpStatus.OK,
                    dataTablesResponse);
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
    public Permission afficherSelonId(Long id) {
        return permissionDao.findById(id).orElseThrow(() -> new EntityNotFoundException(Permission.class, "id", id.toString()));
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Permission dont ID = " + id.toString(),
                    HttpStatus.OK,
                    permissionMapper.dePermission(afficherSelonId(id)));
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creer(PermissionDto permissionDto) {
        try {
            Permission permission = permissionMapper.dePermissionDto(permissionDto);
            permission = permissionDao.save(permission);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    permissionMapper.dePermission(permission));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifier(PermissionDto permissionDto) {
        try {
            if(!permissionDao.existsById(permissionDto.getIdPermis()))
                throw  new EntityNotFoundException(Permission.class, "id", permissionDto.getIdPermis().toString());
            Permission permission = permissionMapper.dePermissionDto(permissionDto);
            permission = permissionDao.save(permission);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    permissionMapper.dePermission(permission));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimer(Long id) {
        try {
            permissionDao.deleteById(id);
            return ResponseHandler.generateResponse(
                    "Suppression effectuée avec succès",
                    HttpStatus.OK,
                    null);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }
}
