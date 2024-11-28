package com.ged.controller.security;

import com.ged.dao.security.RoleDao;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.RequestDto;
import com.ged.dto.security.RoleDto;
import com.ged.entity.security.Role;
import com.ged.service.FiltersSpecification;
import com.ged.service.standard.RoleService;
import jakarta.annotation.PostConstruct;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;
    private final RoleDao roleDao;
    private final FiltersSpecification<Role> roleFiltersSpecification;

    public RoleController(RoleService roleService, RoleDao roleDao, FiltersSpecification<Role> roleFiltersSpecification) {
        this.roleService = roleService;
        this.roleDao = roleDao;
        this.roleFiltersSpecification = roleFiltersSpecification;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous(){
        return roleService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> afficher(@PathVariable(name = "id") Long id)
    {
        return roleService.afficher(id);
    }

    @GetMapping("/nom/{keyword}")
    public ResponseEntity<Object> afficherSelonNom(@PathVariable String keyword)
    {
        return roleService.afficherSelonNom(keyword);
    }

    @GetMapping("search")
    public ResponseEntity<Object> search(
            @RequestBody String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size)
    {
        return roleService.searchByKeyword(keyword, page, size);
    }

    @PostMapping("/filter/specification/pagination")
    public ResponseEntity<Object> getRoles(@RequestBody RequestDto requestDto) {
        return roleService.pagination(requestDto);
    }

    @PostMapping
    public ResponseEntity<Object> ajouter(@RequestBody RoleDto roleDto)
    {
        return roleService.creerRole(roleDto);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_ACCESS')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params)
    {
        return roleService.afficherTous(params);
    }

    @Order(1)
    @PostConstruct
    @EventListener(ApplicationReadyEvent.class)
    public void generate()
    {
        String[] roles = {
           "ROLE_DASHBOARD", "ROLE_BUILDER", "ROLE_DEGRE",
           "ROLE_MENU", "ROLE_COMMUNE", "ROLE_DEPARTEMENT",
           "ROLE_ARRONDISSEMENT", "ROLE_QUARTIER", "ROLE_CATEGORIE",
           "ROLE_INDICATEUR", "ROLE_MONNAIE", "ROLE_PAYS",
           "ROLE_PERIODICITE", "ROLE_PROFESSION", "ROLE_PRODUIT",
           "ROLE_TYPEDOCUMENT", "ROLE_QUALITE", "ROLE_SECTEUR",
           "ROLE_MODELE_MSG_ALERTE", "ROLE_MAIL", "ROLE_ALERTE",
           "ROLE_UTILISATEUR", "ROLE_ACCESS", "ROLE_PERMISSION",
           "ROLE_LISTE_CR", "ROLE_RDV_PLAN", "ROLE_LISTE_PROSPECT",
           "ROLE_FICHE_KYC", "ROLE_CLIENT_PAS_INVESTI", "ROLE_CLIENT_PORTEFEUILLE",
           "ROLE_POINT_OBJECTIF_AFFECTE", "ROLE_POINT_SOUSCRIPTION", "ROLE_AFFECTATION_OBJECTIF",
           "ROLE_OBJECTIF_A_AFFECTER", "ROLE_OBJECTIF_ATTEINT", "ROLE_PLANIFICATION_RDV",
           "ROLE_COMPTE_RENDU", "ROLE_AUTORISATION_CR", "ROLE_PHYSIQUE_PROSPECT",
           "ROLE_PHYSIQUE_ACTIONNAIRE", "ROLE_MORALE_PROSPECT", "ROLE_MORALE_ACTIONNAIRE",
           "ROLE_SUPER_ADMIN", "ROLE_SOCIETE_GESTION", "ROLE_PAYS_GAFI","ROLE_MORALE_DISTRIBUTEUR",
           "ROLE_PHYSIQUE_DISTRIBUTEUR", "ROLE_PERSONNEL","ROLE_MODE_ETABLISSEMENT", "ROLE_DEPOT_RECENSE_ESPECE",
           "ROLE_DEPOT_SUPERIEUR_A_CINQ_MILLIONS", "ROLE_TYPEAFFECTATION_TITRE",
           "ROLE_CLASSIFICATION","ROLE_DEPOT_RECENSE_ANNEE","ROLE_OPERATION_NOUVELLE_RELATION","ROLE_GELDEGEL",
           "ROLE_OPERATION_CLIENT_OCCASIONNEL", "ROLE_TRANSACTION_SUSPECTE_INHABITUELLE", "ROLE_SUIVI_CLIENT_SANCTION",
           "ROLE_REGISTRE_CONFIDENTIEL", "ROLE_ALERTE_LAB","ROLE_OPERATION_CONDITION_INHABITUELLE",
           "ROLE_OPERATION_CONDITION_NORMALE", "ROLE_ETAT_VOLATILITE","ROLE_ETAT_ALPHA","ROLE_ETAT_OPCVM",
           "ROLE_OPCVM_DASHBOARD","ROLE_SOUSCATEGORIE","ROLE_FORMEJURIDIQUE","ROLE_SYSTEMEDINFORMATION","ROLE_COMPARTIMENT",
           "ROLE_NATUREEVENEMENT","ROLE_SOUSTYPEACTION","ROLE_TYPEACTION","ROLE_TYPEEMETTEUR","ROLE_TYPEEMISSION","ROLE_OPCVM",
           "ROLE_TYPEEVENEMENT","ROLE_TYPEGARANT","ROLE_TYPEOBLIGATION","ROLE_SECTEURBOURSIER", "ROLE_PHYSIQUE", "ROLE_MORALE",
                "ROLE_PLACE","ROLE_BANQUE","ROLE_PHYSIQUE_SANCTIONNEE","ROLE_MORALE_SANCTIONNEE","ROLE_TYPEOPERATION",
                "ROLE_JOURNAL","ROLE_NATUREOPERATION","ROLE_FORMULE","ROLE_MODELEFORMULE","ROLE_MODELEECRITURE","ROLE_PLAN",
                "ROLE_COMPTECOMPTABLE","ROLE_POSTECOMPTABLE","ROLE_IBRUBRIQUEPOSITION","ROLE_CORRESPONDANCE",
                "ROLE_LANGUE","ROLE_TYPECOMPTE","ROLE_TYPECLIENT","ROLE_SOUSTYPECOMPTE","ROLE_SOUSTYPECLIENT",
                "ROLE_CATEGORIECLIENT", "ROLE_TITRES", "ROLE_ACTIONNAIRE_OPCVM", "ROLE_MAJ_TITRES","ROLE_PROFIL_OPC","ROLE_TARIFICATION_ACTIONNAIRE",
                "ROLE_TARIFICATION_OPC","ROLE_ETAT_RATIO_SHARP","ROLE_ETAT_RATIOTREYNOR","ROLE_CHARGE_A_ETALER","ROLE_TYPEMODELE"
        };
        for (String role: roles) {
            if(!roleService.existByNom(role))
            {
                RoleDto roleDto = new RoleDto(role);
                roleDto.setSupprimer(false);
//                roleDto.setNumLigne(0L);
                roleService.creerRole(roleDto);
            }
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> modifier(@PathVariable Long id, @RequestBody RoleDto roleDto){
        roleDto.setIdRole(id);
        return roleService.modifierRole(roleDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Supprimer(@PathVariable Long id){
        return roleService.supprimerRole(id);
    }
}
