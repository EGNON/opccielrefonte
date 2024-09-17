package com.ged.mapper.standard;

import com.ged.dto.security.*;
import com.ged.dto.standard.*;
import com.ged.entity.security.*;
import com.ged.entity.standard.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RoleMapper {
    private final ProfessionMapper professionMapper;
    private final PaysMapper paysMapper;

    public RoleMapper(ProfessionMapper professionMapper, PaysMapper paysMapper) {
        this.professionMapper = professionMapper;
        this.paysMapper = paysMapper;
    }

    public RoleDto deRole(Role role)
    {
        if(role == null)
            return null;
        RoleDto roleDto = new RoleDto();
        BeanUtils.copyProperties(role, roleDto);
        if(role.getUtilisateurs1() != null)
        {
            for (UtilisateurRole utilisateurRole :role.getUtilisateurs1()) {
                UtilisateurRoleDto utilisateurRoleDto = new UtilisateurRoleDto();
                CleUtilisateurRoleDto cleUtilisateurRoleDto = new CleUtilisateurRoleDto();
                cleUtilisateurRoleDto.setIdUtilisateur(utilisateurRole.getUtilisateur().getIdPersonne());
                cleUtilisateurRoleDto.setIdRole(utilisateurRole.getRole().getIdRole());
                utilisateurRoleDto.setId(cleUtilisateurRoleDto);

                Utilisateur utilisateur = utilisateurRole.getUtilisateur();
                UtilisateurDto utilisateurDto = new UtilisateurDto();
                utilisateurDto.setEstCommercial(false);

                if(utilisateur != null) {
                    if( utilisateur.getProfession() != null)
                        utilisateurDto.setProfession(professionMapper.deProfession(utilisateur.getProfession()));
                }
                if(utilisateur != null && utilisateur.getPaysResidence() != null) {
                    utilisateurDto.setPaysResidence(paysMapper.dePays(utilisateur.getPaysResidence()));
                }
                if(utilisateur != null && utilisateur.getPaysNationalite() != null) {
                    utilisateurDto.setPaysNationalite(paysMapper.dePays(utilisateur.getPaysNationalite()));
                }
                utilisateurRoleDto.setUtilisateur(utilisateurDto);

                //Ajouter à roleDto.getUtilisateurRoles()
                roleDto.getUtilisateurs1().add(utilisateurRoleDto);
            }
        }
        if(role.getPermissions() != null) {
//            System.out.println(role.getNom() + " = " + role.getPermissions().size());
            roleDto.setPermissions(role.getPermissions().stream().map(rolePermission -> {
                RolePermissionDto rolePermissionDto = new RolePermissionDto();
                CleRolePermissionDto cleRolePermissionDto = new CleRolePermissionDto();
                cleRolePermissionDto.setIdPermis(rolePermission.getPermission().getIdPermis());
                cleRolePermissionDto.setIdRole(rolePermission.getRole().getIdRole());
                rolePermissionDto.setId(cleRolePermissionDto);

                rolePermissionDto.setRole(roleDto);
                PermissionDto permissionDto = new PermissionDto();
                permissionDto.setIdPermis(rolePermission.getPermission().getIdPermis());
                permissionDto.setLibellePermis(rolePermission.getPermission().getLibellePermis());
                permissionDto.setEstPrincipale(rolePermission.getPermission().getEstPrincipale());
                rolePermissionDto.setPermission(permissionDto);

                return rolePermissionDto;
            }).collect(Collectors.toSet()));
        }
        return roleDto;
    }

    public Role deRoleDto(RoleDto roleDto)
    {
        Role role = new Role();
        BeanUtils.copyProperties(roleDto, role);
        if(roleDto.getPermissions() != null) {
            role.setPermissions(roleDto.getPermissions().stream().map(rolePermissionDto -> {
                RolePermission rolePermission = new RolePermission();
                if(rolePermissionDto.getId() != null) {
                    CleRolePermission cleRolePermission = new CleRolePermission();
                    cleRolePermission.setIdPermis(rolePermissionDto.getId().getIdPermis());
                    cleRolePermission.setIdRole(rolePermissionDto.getId().getIdRole());
                    rolePermission.setId(cleRolePermission);

                    Role newRole = new Role();
                    newRole.setIdRole(rolePermissionDto.getId().getIdRole());
                    rolePermission.setRole(newRole);
                }
                else
                {
                    rolePermission.setRole(role);
                }

                Permission permission = new Permission();
                permission.setIdPermis(rolePermissionDto.getPermission().getIdPermis());
                permission.setLibellePermis(rolePermissionDto.getPermission().getLibellePermis());
                permission.setEstPrincipale(rolePermissionDto.getPermission().getEstPrincipale());
                rolePermission.setPermission(permission);

                return rolePermission;
            }).collect(Collectors.toSet()));
        }
        return role;
    }
}
