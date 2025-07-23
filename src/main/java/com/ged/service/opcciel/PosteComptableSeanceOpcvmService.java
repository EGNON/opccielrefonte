package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.comptabilite.PosteComptableSeanceOpcvmDto;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.entity.opcciel.comptabilite.ClePosteComptableSeanceOpcvm;
import com.ged.entity.opcciel.comptabilite.PosteComptableSeanceOpcvm;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.List;

public interface PosteComptableSeanceOpcvmService {
    ResponseEntity<Object> afficherTous(Long idOpcvm,Long idSeance);
    Page<PosteComptableSeanceOpcvmDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object>  afficherTous();
    PosteComptableSeanceOpcvm afficherSelonId(ClePosteComptableSeanceOpcvm clePosteComptableSeanceOpcvm);
    ResponseEntity<Object>  afficher(ClePosteComptableSeanceOpcvm clePosteComptableSeanceOpcvm);
    ResponseEntity<Object>  afficherSelonCodePosteComptableSeanceOpcvm(String codePosteComptableSeanceOpcvm);
    ResponseEntity<Object>  valoriser(DifferenceEstimationRequest posteComptableSeanceOpcvm);
    ResponseEntity<Object>  creer(List<PosteComptableSeanceOpcvmDto> PosteComptableSeanceOpcvmDto);
    ResponseEntity<Object> jaspertReportCodePoste(Long idOpcvm,Long idSeance,Boolean estVerifie1,Boolean estVerifie2,Long niveau, HttpServletResponse response) throws IOException, JRException;
    ResponseEntity<Object> validerNiveau(Long idOpcvm,Long idSeance,Long niveau,String userLogin) ;
    ResponseEntity<Object>  modifier(PosteComptableSeanceOpcvmDto PosteComptableSeanceOpcvmDto);
    ResponseEntity<Object>  supprimer(ClePosteComptableSeanceOpcvm clePosteComptableSeanceOpcvm);
}
