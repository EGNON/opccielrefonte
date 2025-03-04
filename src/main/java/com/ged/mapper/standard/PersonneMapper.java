package com.ged.mapper.standard;

import com.ged.dto.standard.*;
import com.ged.entity.crm.Degre;
import com.ged.entity.lab.GelDegel;
import com.ged.entity.standard.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonneMapper {
    private final PaysMapper paysMapper;
    private final ModeEtablissementMapper modeEtablissementMapper;

    public PersonneMapper(PaysMapper paysMapper, ModeEtablissementMapper modeEtablissementMapper) {
        this.paysMapper = paysMapper;
        this.modeEtablissementMapper = modeEtablissementMapper;
    }

    public PersonneDtoEJ dePersonneEJ(Personne personne)
    {
        if(personne == null)
            return null;
        PersonneDtoEJ personneDto = new PersonneDtoEJ();
        BeanUtils.copyProperties(personne, personneDto);
        return personneDto;
    }

    public Personne dePersonneDtoEJ(PersonneDtoEJ personneDto)
    {
        if(personneDto == null)
            return null;
        Personne personne = new Personne();
        BeanUtils.copyProperties(personneDto, personne);
        return personne;
    }

    public PersonneDto dePersonne(Personne personne)
    {
        if(personne == null)
            return null;
        PersonneDto personneDto = new PersonneDto();
        BeanUtils.copyProperties(personne, personneDto);
        if(personne.getPaysResidence()!=null)
            personneDto.setPaysResidence(paysMapper.dePays(personne.getPaysResidence()));
        if(personne.getModeEtablissement2()!=null)
            personneDto.setModeEtablissementDto(modeEtablissementMapper.deModeEtablissement(
                    personne.getModeEtablissement2()));
        /*if(personne.getStatutPersonnes() != null)
        {
            personneDto.setStatutPersonnes(personne.getStatutPersonnes().stream()
                    .map(statutPersonne -> {
                        StatutPersonneDto statutPersonneDto = new StatutPersonneDto();
                        if(statutPersonne.getQualite() != null) {
                            QualiteDto qualiteDto = new QualiteDto();
                            qualiteDto.setIdQualite(statutPersonne.getQualite().getIdQualite());
                            qualiteDto.setLibelleQualite(statutPersonne.getQualite().getLibelleQualite());
                            qualiteDto.setEstPH(statutPersonne.getQualite().getEstPH());
                            qualiteDto.setEstPM(statutPersonne.getQualite().getEstPM());
                            statutPersonneDto.setQualite(qualiteDto);
                        }

                        if(statutPersonne.getPersonnel() != null) {
                            PersonnelDto personnelDto = new PersonnelDto();
                            personnelDto.setIdPersonne(statutPersonne.getPersonnel().getIdPersonne());
                            personnelDto.setMatricule(statutPersonne.getPersonnel().getMatricule());
                            personnelDto.setEstCommercial(statutPersonne.getPersonnel().getEstCommercial());
                            personnelDto.setDenomination(statutPersonne.getPersonnel().getDenomination());
                            personnelDto.setNom(statutPersonne.getPersonnel().getNom());
                            personnelDto.setPrenom(statutPersonne.getPersonnel().getPrenom());
                            statutPersonneDto.setPersonnel(personnelDto);
                        }

                        return statutPersonneDto;
                    }).collect(Collectors.toSet()));
        }*/
        return personneDto;
    }

    public Personne dePersonneDto(PersonneDto personneDto)
    {
        if(personneDto == null)
            return null;
        Personne personne = new Personne();
        BeanUtils.copyProperties(personneDto, personne);
        if(personneDto.getPaysResidence()!=null)
            personne.setPaysResidence(paysMapper.dePaysDto(personneDto.getPaysResidence()));

        if(personneDto.getModeEtablissementDto()!=null)
            personne.setModeEtablissement2(modeEtablissementMapper.deModeEtablissementDto(
                    personneDto.getModeEtablissementDto()));
        return personne;
    }

    public PersonnePhysiqueMoraleDto dePersonnePhysiqueMorale(Personne personne)
    {
        if(personne == null)
            return null;
       PersonnePhysiqueMoraleDto personnePhysiqueMoraleDto = new PersonnePhysiqueMoraleDto();
       BeanUtils.copyProperties(personne, personnePhysiqueMoraleDto);
       return personnePhysiqueMoraleDto;
    }

    public Personne dePersonnePhysiqueMoraleDto(PersonnePhysiqueMoraleDto personnePhysiqueMoraleDto)
    {
        if(personnePhysiqueMoraleDto == null)
            return null;
        Personne personne = new Personne() {
            @Override
            public boolean equals(Object o) {
                return super.equals(o);
            }

            @Override
            public int hashCode() {
                return super.hashCode();
            }

            @Override
            public Quartier getQuartier() {
                return super.getQuartier();
            }

            @Override
            public void setQuartier(Quartier quartier) {
                super.setQuartier(quartier);
            }

            @Override
            public Set<StatutPersonne> getStatutPersonnes() {
                return super.getStatutPersonnes();
            }

            @Override
            public void setStatutPersonnes(Set<StatutPersonne> statutPersonnes) {
                super.setStatutPersonnes(statutPersonnes);
            }

            @Override
            public Long getIdPersonne() {
                return super.getIdPersonne();
            }

            @Override
            public void setIdPersonne(Long idPersonne) {
                super.setIdPersonne(idPersonne);
            }

            @Override
            public String getIfu() {
                return super.getIfu();
            }

            @Override
            public void setIfu(String ifu) {
                super.setIfu(ifu);
            }

            @Override
            public String getMobile1() {
                return super.getMobile1();
            }

            @Override
            public void setMobile1(String mobile1) {
                super.setMobile1(mobile1);
            }

            @Override
            public String getMobile2() {
                return super.getMobile2();
            }

            @Override
            public void setMobile2(String mobile2) {
                super.setMobile2(mobile2);
            }

            @Override
            public String getFixe1() {
                return super.getFixe1();
            }

            @Override
            public void setFixe1(String fixe1) {
                super.setFixe1(fixe1);
            }

            @Override
            public String getFixe2() {
                return super.getFixe2();
            }

            @Override
            public void setFixe2(String fixe2) {
                super.setFixe2(fixe2);
            }

            @Override
            public String getBp() {
                return super.getBp();
            }

            @Override
            public void setBp(String bp) {
                super.setBp(bp);
            }

            @Override
            public String getEmailPerso() {
                return super.getEmailPerso();
            }

            @Override
            public String getDenomination() {
                return super.getDenomination();
            }

            @Override
            public void setDenomination(String denomination) {
                super.setDenomination(denomination);
            }

            @Override
            public void setEmailPerso(String emailPerso) {
                super.setEmailPerso(emailPerso);
            }

            @Override
            public String getEmailPro() {
                return super.getEmailPro();
            }

            @Override
            public void setEmailPro(String emailPro) {
                super.setEmailPro(emailPro);
            }

            @Override
            public String getDomicile() {
                return super.getDomicile();
            }

            @Override
            public void setDomicile(String domicile) {
                super.setDomicile(domicile);
            }

            @Override
            public String getNumeroPiece() {
                return super.getNumeroPiece();
            }

            @Override
            public void setNumeroPiece(String numeroPiece) {
                super.setNumeroPiece(numeroPiece);
            }

            @Override
            public String getTypePiece() {
                return super.getTypePiece();
            }

            @Override
            public void setTypePiece(String typePiece) {
                super.setTypePiece(typePiece);
            }

            @Override
            public LocalDateTime getDateExpirationPiece() {
                return super.getDateExpirationPiece();
            }

            @Override
            public void setDateExpirationPiece(LocalDateTime dateExpirationPiece) {
                super.setDateExpirationPiece(dateExpirationPiece);
            }

            @Override
            public String getModeEtablissement() {
                return super.getModeEtablissement();
            }

            @Override
            public void setModeEtablissement(String modeEtablissement) {
                super.setModeEtablissement(modeEtablissement);
            }

//            @Override
//            public LocalDateTime getDateCreationServeur() {
//                return super.getDateCreationServeur();
//            }
//
//            @Override
//            public void setDateCreationServeur(LocalDateTime dateCreationServeur) {
//                super.setDateCreationServeur(dateCreationServeur);
//            }
//
//            @Override
//            public LocalDateTime getDateDernModifServeur() {
//                return super.getDateDernModifServeur();
//            }
//
//            @Override
//            public void setDateDernModifServeur(LocalDateTime dateDernModifServeur) {
//                super.setDateDernModifServeur(dateDernModifServeur);
//            }
//
//            @Override
//            public LocalDateTime getDateDernModifClient() {
//                return super.getDateDernModifClient();
//            }
//
//            @Override
//            public void setDateDernModifClient(LocalDateTime dateDernModifClient) {
//                super.setDateDernModifClient(dateDernModifClient);
//            }
//
//            @Override
//            public long getNumLigne() {
//                return super.getNumLigne();
//            }
//
//            @Override
//            public void setNumLigne(long numLigne) {
//                super.setNumLigne(numLigne);
//            }
//
//            @Override
//            public boolean isSupprimer() {
//                return super.isSupprimer();
//            }
//
//            @Override
//            public void setSupprimer(boolean supprimer) {
//                super.setSupprimer(supprimer);
//            }
//
//            @Override
//            public LocalDateTime getRowvers() {
//                return super.getRowvers();
//            }
//
//            @Override
//            public void setRowvers(LocalDateTime rowvers) {
//                super.setRowvers(rowvers);
//            }
//
//            @Override
//            public String getUserLogin() {
//                return super.getUserLogin();
//            }
//
//            @Override
//            public void setUserLogin(String userLogin) {
//                super.setUserLogin(userLogin);
//            }

            @Override
            public Secteur getSecteur() {
                return super.getSecteur();
            }

            @Override
            public void setSecteur(Secteur secteur) {
                super.setSecteur(secteur);
            }

            @Override
            public Degre getDegre() {
                return super.getDegre();
            }

            @Override
            public void setDegre(Degre degre) {
                super.setDegre(degre);
            }

            @Override
            public Set<Document> getDocuments() {
                return super.getDocuments();
            }

            @Override
            public void setDocuments(Set<Document> documents) {
                super.setDocuments(documents);
            }

            @Override
            public Set<EnvoiMail> getEnvoiMails() {
                return super.getEnvoiMails();
            }

            @Override
            public void setEnvoiMails(Set<EnvoiMail> envoiMails) {
                super.setEnvoiMails(envoiMails);
            }

            @Override
            public Set<GelDegel> getGelDegels() {
                return super.getGelDegels();
            }

            @Override
            public void setGelDegels(Set<GelDegel> gelDegels) {
                super.setGelDegels(gelDegels);
            }

            @Override
            public String toString() {
                return super.toString();
            }
        };//= new Personne();
        BeanUtils.copyProperties(personnePhysiqueMoraleDto, personne);
        return personne;
    }
}
