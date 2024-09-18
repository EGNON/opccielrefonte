package com.ged.controller.security;

import com.ged.dao.security.PermissionDao;
import com.ged.dao.security.RoleDao;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.security.PermissionDto;
import com.ged.entity.standard.CleRolePermission;
import com.ged.entity.security.Permission;
import com.ged.entity.security.Role;
import com.ged.entity.security.RolePermission;
import com.ged.service.standard.PermissionService;
import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionService permissionService;
    private final PermissionDao permissionDao;
    private final RoleDao roleDao;

    public PermissionController(PermissionService permissionService, PermissionDao permissionDao, RoleDao roleDao) {
        this.permissionService = permissionService;
        this.permissionDao = permissionDao;
        this.roleDao = roleDao;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous(){
        return permissionService.afficherTous();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PERMISSION')")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return permissionService.afficher(id);
    }

    @PostMapping("/datatable/list")
    @PreAuthorize("hasAuthority('ROLE_PERMISSION')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return permissionService.afficherTous(params);
    }

    @PostConstruct
    @EventListener(ApplicationReadyEvent.class)
    public void generatePermissions() {
        String[] permissions = {
                "ADD;Ajout d'un élément;Permet d'accéder au contrôle d'ajout;0;0",
                "EDIT;Modification d'un élément;Permet d'accéder au contrôle de modification;0;0",
                "DELETE;Suppression d'un élément;Permet d'accéder au contrôle de suppression;0;0",
                "VIEW;Consultation d'un élément;Permet de visualiser un élément;1;0",
                "LIST;Consultation de liste;Permet d'accéder aux listes;0;1",
        };
        List<Role> roles = roleDao.findAll();
        Set<RolePermission> rolePermissions = new HashSet<>();
        for (Role role:roles) {
            for (String permissionStr: permissions) {
                String code = permissionStr.split(";")[0];
                String libelle = permissionStr.split(";")[1];
                String description = permissionStr.split(";")[2];
                String estParDefaut = permissionStr.split(";")[3];
                String estPrincipale = permissionStr.split(";")[4];

                Permission permission = permissionDao.findByLibellePermisContainsIgnoreCase(libelle).orElse(new Permission());
                permission.setCodePermis(code);
                permission.setLibellePermis(libelle);
                permission.setDescription(description);
                permission.setEstParDefaut(Objects.equals(estParDefaut, "1"));
                permission.setEstPrincipale(Objects.equals(estPrincipale, "1"));
                permission = permissionDao.save(permission);

                if(permission.getEstParDefaut()) {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRole(role);
                    rolePermission.setPermission(permission);
                    CleRolePermission cleRolePermission = new CleRolePermission();
                    cleRolePermission.setIdRole(role.getIdRole());
                    cleRolePermission.setIdPermis(permission.getIdPermis());
                    rolePermission.setId(cleRolePermission);
                    rolePermissions.add(rolePermission);
                }
            }
            role.setPermissions(rolePermissions);
            roleDao.save(role);
        }
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_PERMISSION')")
    public ResponseEntity<Object> creer(@Valid @RequestBody PermissionDto permissionDto)
    {
        return permissionService.creer(permissionDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PERMISSION')")
    public ResponseEntity<Object> modifier(@Valid @RequestBody PermissionDto permissionDto, @Positive @PathVariable("id") Long id)
    {
        permissionDto.setIdPermis(id);
        return permissionService.modifier(permissionDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_PERMISSION')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable("id") Long id)
    {
        return permissionService.supprimer(id);
    }
}
