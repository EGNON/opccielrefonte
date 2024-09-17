package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.RequestDto;
import com.ged.dto.security.RoleDto;
import com.ged.entity.security.Role;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    Boolean existByNom(String nom);
    ResponseEntity<Object> afficher(Long id);
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    ResponseEntity<Object> afficherTous();
    ResponseEntity<Object> afficherRoles(int page, int size);

    ResponseEntity<Object> searchByKeyword(String keyword, int page, int size);

    ResponseEntity<Object> pagination(RequestDto requestDto);

    Role afficherRoleSelonId(Long idRole);
    ResponseEntity<Object> afficherSelonNom(String nom);
    ResponseEntity<Object> creerRole(RoleDto roleDto);
    ResponseEntity<Object> modifierRole(RoleDto roleDto);
    ResponseEntity<Object> supprimerRole(Long idRole);
}
