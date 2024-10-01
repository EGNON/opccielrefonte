package com.ged.service.crm;

import com.ged.dto.crm.CompteRenduDto;
import com.ged.dto.standard.CrStateRequest;
import com.ged.entity.crm.CompteRendu;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface CompteRenduService {
    Page<CompteRenduDto> afficherCompteRendus(int page, int size);
    List<CompteRenduDto> afficherTous();
    List<CompteRenduDto> afficherTousEtat();
    List<CompteRenduDto> afficherCompteRenduSelonUtilisateur(Long idUtilisateur);
    List<CompteRenduDto> afficherCompteRenduSelonRealisation(Long idUtilisateur, LocalDateTime dateDeb, LocalDateTime dateFin);
    CompteRenduDto afficherCompteRendu(Long id);
    CompteRendu afficherCompteRenduSelonId(Long idCompteRendu);
    CompteRenduDto creerCompteRendu(List<MultipartFile> files, CompteRenduDto compteRenduDto) throws Throwable;
    CompteRenduDto modifierCompteRendu(List<MultipartFile> files, CompteRenduDto compteRenduDto) throws Throwable;
    void supprimerCompteRendu(Long idCompteRendu);
    CompteRenduDto validateCR(Long id, CrStateRequest crStateRequest);
}
