package com.ged.service.standard.impl;

import com.ged.dao.security.RoleDao;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.PageRequestDto;
import com.ged.dto.RequestDto;
import com.ged.dto.security.RoleDto;
import com.ged.entity.security.Role;
import com.ged.mapper.standard.RoleMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.FiltersSpecification;
import com.ged.service.standard.RoleService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final RoleDao roleDao;
    private final RoleMapper roleMapper;
    private final FiltersSpecification<Role> studentFiltersSpecification;

    public RoleServiceImpl(
            RoleDao roleDao,
            RoleMapper roleMapper,
            FiltersSpecification<Role> studentFiltersSpecification) {
        this.roleDao = roleDao;
        this.roleMapper = roleMapper;
        this.studentFiltersSpecification = studentFiltersSpecification;
    }

    @Override
    public Boolean existByNom(String nom) {
        return roleDao.existsByNom(nom);
    }

    @Override
    public ResponseEntity<Object> afficher(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Dégré dont ID = " + id.toString(),
                    HttpStatus.OK,
                    roleMapper.deRole(afficherRoleSelonId(id)));
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
    public ResponseEntity<Object> afficherTous(DatatableParameters parameters) {
        try {
            Pageable pageable = PageRequest.of(
                    parameters.getStart()/ parameters.getLength(), parameters.getLength());
            Page<Role> rolePage = roleDao.findAll(pageable);

            List<RoleDto> content = rolePage.getContent().stream().map(roleMapper::deRole).collect(Collectors.toList());
            DataTablesResponse<RoleDto> dataTablesResponse = new DataTablesResponse<>();
            dataTablesResponse.setDraw(parameters.getDraw());
            dataTablesResponse.setRecordsFiltered((int)rolePage.getTotalElements());
            dataTablesResponse.setRecordsTotal((int)rolePage.getTotalElements());
            dataTablesResponse.setData(content);

            return ResponseHandler.generateResponse(
                    "Liste des roles par page datatable",
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
    public ResponseEntity<Object> afficherTous() {
        try {
            return ResponseHandler.generateResponse(
                    "Liste de tous les roles",
                    HttpStatus.OK,
                    roleDao.findAll(Sort.by(Sort.Direction.ASC, "nom")).stream().map(roleMapper::deRole).collect(Collectors.toList()));
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
    public ResponseEntity<Object> afficherRoles(int page, int size) {
        try {
            Sort sort = Sort.by(Sort.Direction.ASC,"nom");
            PageRequest pageRequest = PageRequest.of(page, size,sort);
            Page<Role> rolePage = roleDao.findAll(pageRequest);
            return ResponseHandler.generateResponse(
                    "Liste des roles de " + page + " à " + size,
                    HttpStatus.OK,
                    new PageImpl<>(rolePage.getContent().stream().map(roleMapper::deRole).collect(Collectors.toList()),
                            pageRequest, rolePage.getTotalElements()));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> searchByKeyword(String keyword, int page, int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<Role> rolePage = roleDao.findByNomContainingIgnoreCase(keyword, pageable);
            List<RoleDto> content = rolePage.getContent().stream().map(roleMapper::deRole).collect(Collectors.toList());

            return ResponseHandler.generateResponse(
                    "Liste des roles par page datatable",
                    HttpStatus.OK,
                    content);
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
    public ResponseEntity<Object> pagination(RequestDto requestDto) {
        try {
            Specification<Role> searchSpecification = studentFiltersSpecification
                    .getSearchSpecification(requestDto.getSearchRequestDto(), requestDto.getGlobalOperator());
            Pageable pageable = (new PageRequestDto()).getPageable(requestDto.getPageDto());
            Page<Role> rolePage = roleDao.findAll(searchSpecification, pageable);
            PageImpl<RoleDto> pages = new PageImpl<>(rolePage.getContent().stream().map(roleMapper::deRole).collect(Collectors.toList()),
                    pageable, rolePage.getTotalElements());
            return ResponseHandler.generateResponse(
                    "Liste des roles par page",
                    HttpStatus.OK,
                    pages);
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
    public Role afficherRoleSelonId(Long idRole) {
        return roleDao.findById(idRole).orElseThrow(()-> new EntityNotFoundException("Role avec ID "+idRole+" introuvable"));
    }

    @Override
    public ResponseEntity<Object> afficherSelonNom(String nom) {
        try {
            return ResponseHandler.generateResponse(
                    "Liste des roles de suivant le nom",
                    HttpStatus.OK,
                    roleMapper.deRole(roleDao.findByNomEquals(nom).orElse(null)));
        }
        catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> creerRole(RoleDto roleDto) {
        try {
            Role role = roleMapper.deRoleDto(roleDto);
            Role roleSave = roleDao.save(role);
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    roleMapper.deRole(roleSave));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> modifierRole(RoleDto roleDto) {
        try {
            if(!roleDao.existsById(roleDto.getIdRole()))
                throw  new com.ged.advice.EntityNotFoundException(Role.class, "id", roleDto.getIdRole().toString());
            Role role = roleMapper.deRoleDto(roleDto);
            role = roleDao.save(role);
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    roleMapper.deRole(role));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public ResponseEntity<Object> supprimerRole(Long idRole) {
        try {
            roleDao.deleteById(idRole);
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
