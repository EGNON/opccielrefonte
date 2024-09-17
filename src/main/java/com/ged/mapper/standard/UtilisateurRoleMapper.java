package com.ged.mapper.standard;

import com.ged.dto.security.UtilisateurRoleDto;
import com.ged.entity.security.Utilisateur;
import com.ged.entity.security.UtilisateurRole;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurRoleMapper {
    private final RoleMapper roleMapper;

    public UtilisateurRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    public UtilisateurRoleDto deUtilisateurRole(UtilisateurRole utilisateurRole)
    {
        UtilisateurRoleDto utilisateurRoleDto = new UtilisateurRoleDto();
        BeanUtils.copyProperties(utilisateurRole, utilisateurRoleDto);
        utilisateurRoleDto.setRole(roleMapper.deRole(utilisateurRole.getRole()));
        return utilisateurRoleDto;
    }

    public UtilisateurRole deUtilisateurRoleDto(UtilisateurRoleDto utilisateurRoleDto)
    {
        UtilisateurRole utilisateurRole = new UtilisateurRole();
        BeanUtils.copyProperties(utilisateurRoleDto, utilisateurRole);
        Utilisateur u = new Utilisateur();
        BeanUtils.copyProperties(utilisateurRoleDto.getUtilisateur(), u);
        utilisateurRole.setUtilisateur(u);
        utilisateurRole.setRole(roleMapper.deRoleDto(utilisateurRoleDto.getRole()));
        return utilisateurRole;
    }
}
