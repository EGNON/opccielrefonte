package com.ged.mapper.standard;

import com.ged.dto.security.*;
import com.ged.dto.standard.*;
import com.ged.entity.security.*;
import com.ged.entity.standard.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UtilisateurMapper {
    @Autowired
    private ModelMapper modelMapper;
    private final TokenMapper tokenMapper;
    private final RoleMapper roleMapper;
    private final PermissionMapper permissionMapper;

    public UtilisateurMapper(TokenMapper tokenMapper, RoleMapper roleMapper, PermissionMapper permissionMapper) {
        this.tokenMapper = tokenMapper;
        this.roleMapper = roleMapper;
        this.permissionMapper = permissionMapper;
    }

    public UtilisateurDto deUtilisateur(Utilisateur utilisateur)
    {
        if(utilisateur == null)
            return null;
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        BeanUtils.copyProperties(utilisateur, utilisateurDto);
        utilisateurDto.setTokens(utilisateur.getTokens().stream().map(tokenMapper::deToken).collect(Collectors.toSet()));
        if(utilisateur.getProfession() != null) {
            utilisateurDto.setProfession(modelMapper.map(utilisateur.getProfession(), ProfessionDto.class));
        }
        if(utilisateur.getPaysResidence()!=null) {
            utilisateurDto.setPaysResidence(modelMapper.map(utilisateur.getPaysResidence(), PaysDto.class));
        }
        if(utilisateur.getPaysNationalite()!=null) {
            utilisateurDto.setPaysNationalite(modelMapper.map(utilisateur.getPaysNationalite(), PaysDto.class));
        }
        utilisateurDto.getRoles1().clear();
        utilisateurDto.setRoles1(utilisateur.getRoles1().stream().map(utilisateurRole -> {
            UtilisateurRoleDto r = new UtilisateurRoleDto();
            r.setUtilisateur(utilisateurDto);
            RoleDto roleDto = new RoleDto();
            roleDto.setIdRole(utilisateurRole.getRole().getIdRole());
            roleDto.setNom(utilisateurRole.getRole().getNom());
            if(utilisateurRole.getRole() != null && utilisateurRole.getRole().getPermissions() != null
                    && utilisateurRole.getRole().getPermissions().size() > 0) {
                roleDto.setPermissions(utilisateurRole.getRole().getPermissions().stream().map(rolePermission -> {
                    RolePermissionDto rolePermissionDto = new RolePermissionDto();
                    rolePermissionDto.setRole(roleDto);

                    PermissionDto permissionDto = new PermissionDto();
                    permissionDto.setIdPermis(rolePermission.getPermission().getIdPermis());
                    permissionDto.setLibellePermis(rolePermission.getPermission().getLibellePermis());
                    permissionDto.setEstPrincipale(rolePermission.getPermission().getEstPrincipale());
                    rolePermissionDto.setPermission(permissionDto);

                    if(rolePermission.getRole().getIdRole() != null && rolePermission.getPermission().getIdPermis() != null) {
                        CleRolePermissionDto cleRolePermissionDto = new CleRolePermissionDto();
                        cleRolePermissionDto.setIdPermis(rolePermission.getPermission().getIdPermis());
                        cleRolePermissionDto.setIdRole(rolePermission.getRole().getIdRole());
                        rolePermissionDto.setId(cleRolePermissionDto);
                    }
                    return rolePermissionDto;
                }).collect(Collectors.toSet()));
            }
            r.setRole(roleDto);

            if(utilisateurDto.getIdPersonne() != null && roleDto.getIdRole() != null)
            {
                CleUtilisateurRoleDto cleUtilisateurRole = new CleUtilisateurRoleDto();
                cleUtilisateurRole.setIdRole(roleDto.getIdRole());
                cleUtilisateurRole.setIdUtilisateur(utilisateurDto.getIdPersonne());
                r.setId(cleUtilisateurRole);
            }
            return r;
        }).collect(Collectors.toSet()));

        utilisateurDto.getPermissions().clear();
        utilisateurDto.setPermissions(utilisateur.getPermissions().stream().map(utilisateurRolePermission -> {
            UtilisateurRolePermissionDto utilisateurRolePermissionDto = new UtilisateurRolePermissionDto();
            utilisateurRolePermissionDto.setUtilisateur(utilisateurDto);

            RoleDto roleDto = new RoleDto();
            roleDto.setIdRole(utilisateurRolePermission.getRole().getIdRole());
            roleDto.setNom(utilisateurRolePermission.getRole().getNom());
            if(utilisateurRolePermission.getRole() != null && utilisateurRolePermission.getRole().getPermissions() != null
                    && utilisateurRolePermission.getRole().getPermissions().size() > 0) {
                roleDto.setPermissions(utilisateurRolePermission.getRole().getPermissions().stream().map(rolePermission -> {
                    RolePermissionDto rolePermissionDto = new RolePermissionDto();
                    rolePermissionDto.setRole(roleDto);

                    PermissionDto permissionDto = new PermissionDto();
                    permissionDto.setIdPermis(rolePermission.getPermission().getIdPermis());
                    permissionDto.setLibellePermis(rolePermission.getPermission().getLibellePermis());
                    permissionDto.setEstPrincipale(rolePermission.getPermission().getEstPrincipale());
                    rolePermissionDto.setPermission(permissionDto);

                    if(rolePermission.getRole().getIdRole() != null && rolePermission.getPermission().getIdPermis() != null) {
                        CleRolePermissionDto cleRolePermissionDto = new CleRolePermissionDto();
                        cleRolePermissionDto.setIdPermis(rolePermission.getPermission().getIdPermis());
                        cleRolePermissionDto.setIdRole(rolePermission.getRole().getIdRole());
                        rolePermissionDto.setId(cleRolePermissionDto);
                    }
                    return rolePermissionDto;
                }).collect(Collectors.toSet()));
            }
            utilisateurRolePermissionDto.setRole(roleDto);

            PermissionDto permissionDto = new PermissionDto();
            permissionDto.setIdPermis(utilisateurRolePermission.getPermission().getIdPermis());
            permissionDto.setLibellePermis(utilisateurRolePermission.getPermission().getLibellePermis());
            permissionDto.setEstPrincipale(utilisateurRolePermission.getPermission().getEstPrincipale());
            permissionDto.setCodePermis(utilisateurRolePermission.getPermission().getCodePermis());
            utilisateurRolePermissionDto.setPermission(permissionDto);

            if(utilisateurDto.getIdPersonne() != null && roleDto.getIdRole() != null && permissionDto.getIdPermis() != null)
            {
                CleUtilisateurRolePermissionDto cleUtilisateurRolePermissionDto = new CleUtilisateurRolePermissionDto();
                cleUtilisateurRolePermissionDto.setIdRole(roleDto.getIdRole());
                cleUtilisateurRolePermissionDto.setIdUtilisateur(utilisateurDto.getIdPersonne());
                cleUtilisateurRolePermissionDto.setIdPermis(permissionDto.getIdPermis());
                utilisateurRolePermissionDto.setIdUtilisateurRolePermission(cleUtilisateurRolePermissionDto);
            }
            return utilisateurRolePermissionDto;
        }).collect(Collectors.toSet()));
        return utilisateurDto;
    }
    public Utilisateur2Dto deUtilisateur2(Utilisateur utilisateur)
    {
        if(utilisateur == null)
            return null;
        Utilisateur2Dto utilisateurDto = new Utilisateur2Dto();
        BeanUtils.copyProperties(utilisateur, utilisateurDto);

        if(utilisateur.getProfession() != null) {
            utilisateurDto.setProfession(modelMapper.map(utilisateur.getProfession(), ProfessionDto.class));
        }
        if(utilisateur.getPaysResidence()!=null) {
            utilisateurDto.setPaysResidence(modelMapper.map(utilisateur.getPaysResidence(), PaysDto.class));
        }
        if(utilisateur.getPaysNationalite()!=null) {
            utilisateurDto.setPaysNationalite(modelMapper.map(utilisateur.getPaysNationalite(), PaysDto.class));
        }
        return utilisateurDto;
    }

    public Utilisateur deUtilisateur2Dto(Utilisateur2Dto utilisateur2Dto)
    {
        if(utilisateur2Dto == null)
            return null;
        Utilisateur utilisateur = new Utilisateur();
        BeanUtils.copyProperties(utilisateur2Dto, utilisateur);

        if(utilisateur2Dto.getProfession() != null) {
            utilisateur.setProfession(modelMapper.map(utilisateur2Dto.getProfession(), Profession.class));
        }
        if(utilisateur2Dto.getPaysResidence()!=null) {
            utilisateur.setPaysResidence(modelMapper.map(utilisateur2Dto.getPaysResidence(), Pays.class));
        }
        if(utilisateur2Dto.getPaysNationalite()!=null) {
            utilisateur.setPaysNationalite(modelMapper.map(utilisateur2Dto.getPaysNationalite(), Pays.class));
        }
        return utilisateur;
    }

    public Utilisateur deUtilisateurDto(UtilisateurDto utilisateurDto)
    {
        if(utilisateurDto == null)
            return null;
        Utilisateur utilisateur = new Utilisateur();
        BeanUtils.copyProperties(utilisateurDto, utilisateur);
        utilisateur.setRoles1(utilisateurDto.getRoles1().stream().map(utilisateurRoleDto -> {
            UtilisateurRole r = new UtilisateurRole();
            r.setUtilisateur(utilisateur);
            Role role = roleMapper.deRoleDto(utilisateurRoleDto.getRole());
            role.setIdRole(utilisateurRoleDto.getRole().getIdRole());
            role.setNom(utilisateurRoleDto.getRole().getNom());
            r.setRole(role);
            CleUtilisateurRole cleUtilisateurRole = new CleUtilisateurRole(utilisateur.getIdPersonne(), role.getIdRole());
            r.setId(cleUtilisateurRole);
            return r;
        }).collect(Collectors.toSet()));

        utilisateur.setPermissions(utilisateurDto.getPermissions().stream().map(utilisateurRolePermissionDto -> {
            UtilisateurRolePermission r = new UtilisateurRolePermission();
            r.setUtilisateur(utilisateur);
            Role role = roleMapper.deRoleDto(utilisateurRolePermissionDto.getRole());
            r.setRole(role);
            Permission permission = permissionMapper.dePermissionDto(utilisateurRolePermissionDto.getPermission());
            r.setPermission(permission);
            CleUtilisateurRolePermission cleUtilisateurRole = new CleUtilisateurRolePermission(
                    utilisateur.getIdPersonne(), role.getIdRole(), permission.getIdPermis());
            r.setIdUtilisateurRolePermission(cleUtilisateurRole);
            r.setSupprimer(false);
            return r;
        }).collect(Collectors.toSet()));
        //utilisateur.setUtilisateurRoles(utilisateurDto.getUtilisateurRoles().stream().map(utilisateurRoleMapper::deUtilisateurRoleDto).collect(Collectors.toSet()));
        return  utilisateur;
    }
}
