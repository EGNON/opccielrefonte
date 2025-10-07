package com.ged.service.standard.impl;

import com.ged.dao.security.RolePermissionDao;
import com.ged.dao.security.UtilisateurDao;
import com.ged.dao.security.UtilisateurRoleDao;
import com.ged.dao.security.UtilisateurRolePermissionDao;
import com.ged.dao.standard.*;
import com.ged.dto.security.Utilisateur2Dto;
import com.ged.dto.security.UtilisateurDto;
import com.ged.dto.security.UtilisateurRoleDto;
import com.ged.entity.opcciel.comptabilite.TypeIb;
import com.ged.entity.security.*;
import com.ged.entity.standard.*;
import com.ged.mapper.standard.UtilisateurMapper;
import com.ged.dto.security.LoginRequest;
import com.ged.mapper.standard.UtilisateurRoleMapper;
import com.ged.response.ResponseHandler;
import com.ged.service.standard.UtilisateurService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNullApi;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES)
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
    private final UtilisateurRoleMapper utilisateurRoleMapper;
    private final ProfessionDao professionDao;
    private final PaysDao paysDao;
    private final UtilisateurRoleDao utilisateurRoleDao;
    private final RolePermissionDao rolePermissionDao;
    private final UtilisateurRolePermissionDao utilisateurRolePermissionDao;

    public UtilisateurServiceImpl(UtilisateurDao utilisateurDao, PasswordEncoder passwordEncoder, ModelMapper modelMapper, UtilisateurMapper utilisateurMapper, UtilisateurRoleMapper utilisateurRoleMapper, ProfessionDao professionDao, PaysDao paysDao, UtilisateurRoleDao utilisateurRoleDao, RolePermissionDao rolePermissionDao, UtilisateurRolePermissionDao utilisateurRolePermissionDao) {
        this.utilisateurDao = utilisateurDao;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.utilisateurMapper = utilisateurMapper;
        this.utilisateurRoleMapper = utilisateurRoleMapper;
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
    public Page<Utilisateur2Dto> afficherUsers(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Utilisateur> utilisateurPage = utilisateurDao.findAll(pageRequest);
        return new PageImpl<>(utilisateurPage.getContent().stream().map(utilisateurMapper::deUtilisateur2).collect(Collectors.toList()), pageRequest, utilisateurPage.getTotalElements());
    }

    @Override
    public Page<UtilisateurDto> afficherUtilisateurs(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Utilisateur> utilisateurPage = utilisateurDao.findAll(pageRequest);
        return new PageImpl<>(utilisateurPage.getContent().stream().map(utilisateurMapper::deUtilisateur).collect(Collectors.toList()), pageRequest, utilisateurPage.getTotalElements());
    }

    @Override
    public ResponseEntity<Object> afficherTous() {
        try {
            List<UtilisateurDto> utilisateurDtos = utilisateurDao.findAll().stream().map(utilisateurMapper::deUtilisateur).toList();
            return ResponseHandler.generateResponse(
                    "Liste des utilisateurs",
                    HttpStatus.OK,
                    utilisateurDtos);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    /**
     * @return
     */
    @Override
    public ResponseEntity<Object> afficherTousListe() {
        try {
            List<UtilisateurDto> utilisateurDtos = utilisateurDao.afficherTous().stream().map(utilisateurMapper::deUtilisateur).toList();
            return ResponseHandler.generateResponse(
                    "Liste des utilisateurs",
                    HttpStatus.OK,
                    utilisateurDtos);
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    /**
     * @return
     */
    @Override
    public Workbook createExcel() throws IOException {
        Workbook workbook = new XSSFWorkbook();

        // --- Feuille 1 ---
        List<UtilisateurDto> utilisateurDtos=utilisateurDao.afficherTous().stream().map(utilisateurMapper::deUtilisateur).toList();
        int k=1;
        Sheet sheet1 = workbook.createSheet("UTILISATEURS");
        Row row1 = sheet1.createRow(0);
        row1.createCell(0).setCellValue("Nom");
        row1.createCell(1).setCellValue("Fonction");
        row1.createCell(2).setCellValue("Est Actif");
        for(UtilisateurDto o:utilisateurDtos) {

            Row row2 = sheet1.createRow(k);
            row2.createCell(0).setCellValue(o.getDenomination());
            row2.createCell(1).setCellValue(o.getProfession().getLibelleProfession());
            row2.createCell(2).setCellValue(o.getEstActif()==true?"OUI":"NON");
            k+=1;
        }
        for(UtilisateurDto o:utilisateurDtos){
            List<UtilisateurRoleDto> utilisateurRoleDtos=utilisateurRoleDao.findByUtilisateur(utilisateurMapper.deUtilisateurDto(o)).stream().map(utilisateurRoleMapper::deUtilisateurRole).toList();
            Sheet sheet2 = workbook.createSheet(o.getDenomination());
            Row row2 = sheet2.createRow(0);
            row2.createCell(0).setCellValue("UTILISATEUR");
            row2.createCell(1).setCellValue("DROITS");
            int i=1;
            for(UtilisateurRoleDto u:utilisateurRoleDtos){
              // Exemple de données
                Row rowData1 = sheet2.createRow(i);
                rowData1.createCell(0).setCellValue(o.getDenomination());
                rowData1.createCell(1).setCellValue(u.getRole().getDescription());
                i+=1;
            }

        }


        // --- Enregistrer le fichier ---
//        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
//            workbook.write(fileOut);
//        }

        // Fermer le workbook
        //workbook.close();
        return workbook;
    }

    @Override
    public ResponseEntity<Object> afficherUtilisateur(Long id) {
        try {
            return ResponseHandler.generateResponse(
                    "Utilisateur dont ID = " + id.toString(),
                    HttpStatus.OK,
                    utilisateurMapper.deUtilisateur(afficherUtilisateurSelonId(id))
            );
        }
        catch (Exception e)
        {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
    public Utilisateur afficherUtilisateurSelonId(Long idUtilisateur) {
        return utilisateurDao.findByIdPersonne(idUtilisateur).orElseThrow(() -> new EntityNotFoundException("Utilisateur avec ID " + idUtilisateur + " introuvable"));
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> creerUtilisateur(UtilisateurDto utilisateurDto) {
        try {
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

                utilisateurDao.save(utilisateur);
                //em.merge(utilisateur);
            } finally {
                //em.close();
            }
            return ResponseHandler.generateResponse(
                    "Enregistrement effectué avec succès !",
                    HttpStatus.OK,
                    utilisateurMapper.deUtilisateur(utilisateur));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResponseEntity<Object> modifierUtilisateur(UtilisateurDto utilisateurDto) {
        try {
            /*if (utilisateurDto.getIdPersonne() != null) {
                utilisateurRoleDao.deleteUtilisateurRolesByUtilisateurIdPersonne(utilisateurDto.getIdPersonne());
            }*/
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
            return ResponseHandler.generateResponse(
                    "Modification effectuée avec succès !",
                    HttpStatus.OK,
                    utilisateurMapper.deUtilisateur(utilisateurSave));
        } catch (Exception e) {
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.MULTI_STATUS,
                    e);
        }
    }

    @Override
//    @Transactional(propagation = Propagation.REQUIRES_NEW)
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
                utilisateur.setSupprimer(false);
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
