package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.security.PermissionDto;
import com.ged.entity.security.Permission;
import org.springframework.http.ResponseEntity;

public interface PermissionService {
    ResponseEntity<Object> afficherTous();

    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Permission afficherSelonId(Long id);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> creer(PermissionDto permissionDto);
    ResponseEntity<Object> modifier(PermissionDto permissionDto);
    ResponseEntity<Object> supprimer(Long id);
}
