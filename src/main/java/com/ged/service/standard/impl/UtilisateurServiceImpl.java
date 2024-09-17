package com.ged.service.standard.impl;

import com.ged.dao.security.RolePermissionDao;
import com.ged.dao.security.UtilisateurDao;
import com.ged.dao.security.UtilisateurRoleDao;
import com.ged.dao.security.UtilisateurRolePermissionDao;
import com.ged.dao.standard.*;
import com.ged.dto.security.UtilisateurDto;
import com.ged.entity.security.RolePermission;
import com.ged.entity.security.Utilisateur;
import com.ged.entity.security.UtilisateurRole;
import com.ged.entity.security.UtilisateurRolePermission;
import com.ged.entity.standard.*;
import com.ged.mapper.standard.UtilisateurMapper;
import com.ged.dto.security.LoginRequest;
import com.ged.service.standard.UtilisateurService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional("refonteTransactionManager")
public class UtilisateurServiceImpl implements UtilisateurService {
    @PersistenceContext
    EntityManager em;
    @Value("${application.security.jwt.secret-key}")
    private String secretKey;
    private final UtilisateurDao utilisateurDao;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final UtilisateurMapper utilisateurMapper;
    private final ProfessionDao professionDao;
    private final PaysDao paysDao;
    private final UtilisateurRoleDao utilisateurRoleDao;
    private final RolePermissionDao rolePermissionDao;
    private final UtilisateurRolePermissionDao utilisateurRolePermissionDao;

    public UtilisateurServiceImpl(UtilisateurDao utilisateurDao, PasswordEncoder passwordEncoder, ModelMapper modelMapper, UtilisateurMapper utilisateurMapper, ProfessionDao professionDao, PaysDao paysDao, UtilisateurRoleDao utilisateurRoleDao, RolePermissionDao rolePermissionDao, UtilisateurRolePermissionDao utilisateurRolePermissionDao) {
        this.utilisateurDao = utilisateurDao;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.utilisateurMapper = utilisateurMapper;
        this.professionDao = professionDao;
        this.paysDao = paysDao;
        this.utilisateurRoleDao = utilisateurRoleDao;
        this.rolePermissionDao = rolePermissionDao;
        this.utilisateurRolePermissionDao = utilisateurRolePermissionDao;
        this.modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    @Override
    public UtilisateurDto login(LoginRequest loginRequest) {
        Utilisateur utilisateur = utilisateurDao.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        return modelMapper.map(utilisateur, UtilisateurDto.class);
    }

    @Override
    public UtilisateurDto findByUsername(String username) {
        return null;
    }

    @Override
    public Page<UtilisateurDto> afficherUtilisateurs(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Utilisateur> utilisateurPage = utilisateurDao.findAll(pageRequest);
        return new PageImpl<>(utilisateurPage.getContent().stream().map(utilisateurMapper::deUtilisateur).collect(Collectors.toList()), pageRequest, utilisateurPage.getTotalElements());
    }

    @Override
    public List<UtilisateurDto> afficherTous() {
        return utilisateurDao.findAll().stream().map(utilisateurMapper::deUtilisateur).collect(Collectors.toList());
    }

    @Override
    public UtilisateurDto afficherUtilisateur(Long id) {
        return utilisateurMapper.deUtilisateur(afficherUtilisateurSelonId(id));
    }

    @Override
    public Utilisateur afficherUtilisateurSelonId(Long idUtilisateur) {
        return utilisateurDao.findById(idUtilisateur).orElseThrow(() -> new EntityNotFoundException("Utilisateur avec ID " + idUtilisateur + " introuvable"));
    }

    @Override
    public UtilisateurDto creerUtilisateur(UtilisateurDto utilisateurDto) {
        Utilisateur utilisateur = utilisateurMapper.deUtilisateurDto(utilisateurDto);
        try {
            utilisateur.setDenomination(utilisateurDto.getNom() + " " + utilisateurDto.getPrenom());
            utilisateur.setPassword(passwordEncoder.encode(utilisateurDto.getPassword()));

            Base64.Encoder encoder = Base64.getEncoder();
            //String originalString = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxys0123456789";
            String encodedString = encoder.encodeToString(secretKey.getBytes());

            utilisateur.setMotDePasse(encodedString);

            if (utilisateurDto.getProfession() != null && utilisateurDto.getProfession().getIdProf() != null) {
                Profession profession = professionDao.findById(utilisateurDto.getProfession().getIdProf()).orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Profession.class, utilisateurDto.getProfession().getIdProf().toString()));
                utilisateur.setProfession(profession);
            }

            if (utilisateurDto.getPaysNationalite() != null && utilisateurDto.getPaysNationalite().getIdPays() != null) {
                Pays paysNat = paysDao.findById(utilisateurDto.getPaysNationalite().getIdPays()).orElse(null);
                utilisateur.setPaysNationalite(paysNat);
            }

            if (utilisateurDto.getPaysResidence() != null && utilisateurDto.getPaysResidence().getIdPays() != null) {
                Pays paysRed = paysDao.findById(utilisateurDto.getPaysResidence().getIdPays()).orElse(null);
                utilisateur.setPaysResidence(paysRed);
            }

            em.merge(utilisateur);
        } finally {
            em.close();
        }
        //Utilisateur utilisateurSave = em.merge(utilisateur);
        return utilisateurMapper.deUtilisateur(utilisateur);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UtilisateurDto modifierUtilisateur(UtilisateurDto utilisateurDto) {
        if (utilisateurDto.getIdPersonne() != null) {
            utilisateurRoleDao.deleteUtilisateurRolesByUtilisateurIdPersonne(utilisateurDto.getIdPersonne());
        }
        Utilisateur utilisateur = utilisateurMapper.deUtilisateurDto(utilisateurDto);
        utilisateur.setDenomination(utilisateurDto.getNom() + " " + utilisateurDto.getPrenom());
        utilisateur.setPassword(passwordEncoder.encode(utilisateurDto.getPassword()));
        if (utilisateurDto.getProfession() != null && utilisateurDto.getProfession().getIdProf() != null) {
            Profession profession = professionDao.findById(utilisateurDto.getProfession().getIdProf()).orElseThrow(() -> new com.ged.advice.EntityNotFoundException(Profession.class, utilisateurDto.getProfession().getIdProf().toString()));
            utilisateur.setProfession(profession);
        }
        if (utilisateurDto.getPaysNationalite() != null && utilisateurDto.getPaysNationalite().getIdPays() != null) {
            Pays paysNat = paysDao.findById(utilisateurDto.getPaysNationalite().getIdPays()).orElse(null);
            utilisateur.setPaysNationalite(paysNat);
        }
        if (utilisateurDto.getPaysResidence() != null && utilisateurDto.getPaysResidence().getIdPays() != null) {
            Pays paysRed = paysDao.findById(utilisateurDto.getPaysResidence().getIdPays()).orElse(null);
            utilisateur.setPaysResidence(paysRed);
        }
        Utilisateur utilisateurSave = utilisateurDao.save(utilisateur);
        return utilisateurMapper.deUtilisateur(utilisateurSave);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void registerDefaultUsers() {
        String[] usernames = {"admin", "user"};
        int cpt = 0;
        for (String username : usernames) {
            try {
                Utilisateur utilisateur = utilisateurDao.findByUsernameIgnoreCase(username).orElse(new Utilisateur());
                utilisateur.setNom("GED");
                utilisateur.setPrenom(("Utilisateur" + (cpt + 1)).toUpperCase());
                utilisateur.setDenomination(utilisateur.getNom() + " " + utilisateur.getPrenom());
                utilisateur.setEmailPro(username + "@soft.com");
                utilisateur.setEmailPerso(username + "@gmail.com");
                utilisateur.setUsername(username);
                utilisateur.setPassword1(username + "1");
                utilisateur.setPassword(username + "1");
                utilisateur.setEstCommercial(false);
                utilisateur.setEstActif(true);
                utilisateur.setDenomination(utilisateur.getNom() + " " + utilisateur.getPrenom());
                utilisateur.setPassword1(passwordEncoder.encode(utilisateur.getPassword1()));
                utilisateur.setPassword(passwordEncoder.encode(utilisateur.getPassword()));
                utilisateur = utilisateurDao.save(utilisateur);
                utilisateur.getPermissions().clear();
                for (RolePermission rolePermission : rolePermissionDao.findAll()) {
                    //Enregistrement des roles de l'utilisateur
                    UtilisateurRole utilisateurRole = new UtilisateurRole();
                    CleUtilisateurRole cleUtilisateurRole = new CleUtilisateurRole();
                    cleUtilisateurRole.setIdUtilisateur(utilisateur.getIdPersonne());
                    cleUtilisateurRole.setIdRole(rolePermission.getRole().getIdRole());
                    utilisateurRole.setId(cleUtilisateurRole);
                    utilisateurRole.setUtilisateur(utilisateur);
                    utilisateurRole.setSupprimer(false);
                    utilisateurRole.setRole(rolePermission.getRole());
                    utilisateurRoleDao.save(utilisateurRole);

                    //Enregistrement des permissions de l'utilisateur
                    UtilisateurRolePermission utilisateurRolePermission = new UtilisateurRolePermission();
                    CleUtilisateurRolePermission cleUtilisateurRolePermission = new CleUtilisateurRolePermission();
                    cleUtilisateurRolePermission.setIdUtilisateur(utilisateur.getIdPersonne());
                    cleUtilisateurRolePermission.setIdRole(rolePermission.getRole().getIdRole());
                    cleUtilisateurRolePermission.setIdPermis(rolePermission.getPermission().getIdPermis());
                    if(utilisateurRolePermissionDao.existsById(cleUtilisateurRolePermission)) {
                        utilisateurRolePermissionDao.deleteById(cleUtilisateurRolePermission);
                    }
                    utilisateurRolePermission.setIdUtilisateurRolePermission(cleUtilisateurRolePermission);
                    utilisateurRolePermission.setUtilisateur(utilisateur);
                    utilisateurRolePermission.setSupprimer(false);
                    utilisateurRolePermission.setRole(rolePermission.getRole());
                    utilisateurRolePermission.setPermission(rolePermission.getPermission());
                    utilisateurRolePermissionDao.save(utilisateurRolePermission);
                }
                cpt++;
            } catch (Exception e) {
                System.out.println("Erreur lors de l'enregistrement des utilisateurs par défaut..." + e.getMessage());
            }
        }
    }

    @Override
    public void supprimerUtilisateur(Long idUtilisateur) {
        utilisateurDao.deleteById(idUtilisateur);
    }

//    @Override
//    public UserDetailsService userDetailsService() {
//        return new UserDetailsService() {
//            @Override
//            public UserDetails loadUserByUsername(String username) throws com.ged.advice.EntityNotFoundException {
//                return utilisateurDao.findByUsername(username);
//            }
//        };
//    }
}
