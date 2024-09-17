package com.ged.dto.standard;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ged.dto.security.RoleDto;

import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MenuDto {
    private Long idMenu;
    private String icon;
    private String dataLink;
    private String page;
    private String title;
    private Boolean estActif;
    private String translate;
    private Long ordreAffichage;
    @JsonIgnore
    private RoleDto role;
    @JsonIgnore
    private MenuDto parentMenu;
    private Set<MenuDto> children = new HashSet<>();

    public Long getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Long idMenu) {
        this.idMenu = idMenu;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getDataLink() {
        return dataLink;
    }

    public void setDataLink(String dataLink) {
        this.dataLink = dataLink;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
    }

    public MenuDto getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(MenuDto parentMenu) {
        this.parentMenu = parentMenu;
    }

    public Set<MenuDto> getChildren() {
        return children;
    }

    public void setChildren(Set<MenuDto> children) {
        this.children = children;
    }

    public Boolean getEstActif() {
        return estActif;
    }

    public void setEstActif(Boolean estActif) {
        this.estActif = estActif;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public Long getOrdreAffichage() {
        return ordreAffichage;
    }

    public void setOrdreAffichage(Long ordreAffichage) {
        this.ordreAffichage = ordreAffichage;
    }

    @Override
    public String toString() {
        return "MenuDto{" +
                "title='" + title + '\'' +
                ", role=" + role +
                '}';
    }
}
