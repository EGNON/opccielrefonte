package com.ged.service.crm;

import com.ged.dto.crm.RDVDto;
import com.ged.dto.crm.RDVEtatDto;
import com.ged.entity.crm.RDV;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface RDVService{
    Page<RDVDto> afficherRDVs(int page, int size);
    List<RDVDto> afficherRDVs();
    List<RDVDto> afficherRDVsSurCR();
    Page<RDVDto> afficherRDVs2(int page, int size) ;
    RDV afficherRDVSelonId(long idRDV);
    List<RDVDto> afficherRDVSelonModeleMsgAlerte(long idModele);
    List<RDVDto> afficherRDVListe();
    List<RDVEtatDto> afficherRDVEtat(String etat, HttpServletResponse response) throws IOException, JRException;
    List<RDVDto> afficherRDVSelonPersonnel(Long idPersonnel);
    RDVDto afficherRDVSelonIdParProjection(long idRDV);
    RDVDto afficherRDV(long idRDV);
    RDVDto creerRDV(RDVDto rdvDto);
    RDVDto modifierRDV(RDVDto rdvDto);
    int modifierUnePartieDeRDV(RDVDto rdvDto);
    void supprimerRDV(long idRDV);
}
