package com.ged.entity.standard;

import com.fasterxml.jackson.annotation.*;
import com.ged.entity.Base;
import com.ged.entity.security.Role;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "T_Menu", schema = "Parametre")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "idMenu")
public class Menu extends Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMenu;
    private String icon;
    private String dataLink;
    private String page;
    private String title;
    private Boolean estActif;
    private String translate;
    private Long ordreAffichage;
    @ManyToOne
    @JoinColumn(name = "idRole")
//    @JsonBackReference
    @JsonIgnore
    private Role role;
    @ManyToOne
    @JoinColumn(name = "idParentMenu", referencedColumnName = "idMenu")
//    @JsonBackReference
    @JsonIgnore
    private Menu parentMenu;
    @OneToMany(mappedBy = "parentMenu")
    //@JsonManagedReference
    private Set<Menu> children = new HashSet<>();

    public Menu() {
    }

    public Menu(Long idMenu, String icon, String dataLink, String page, String title, Role role) {
        this.idMenu = idMenu;
        this.icon = icon;
        this.dataLink = dataLink;
        this.page = page;
        this.title = title;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return Objects.equals(idMenu, menu.idMenu) && Objects.equals(icon, menu.icon) && Objects.equals(dataLink, menu.dataLink) && Objects.equals(page, menu.page) && Objects.equals(title, menu.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMenu, icon, dataLink, page, title);
    }

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //@JsonBackReference
    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    //@JsonManagedReference
    public Set<Menu> getChildren() {
        return children;
    }

    public void setChildren(Set<Menu> children) {
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
        return "Menu{" +
                "idMenu=" + idMenu +
                ", icon='" + icon + '\'' +
                ", dataLink='" + dataLink + '\'' +
                ", page='" + page + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
