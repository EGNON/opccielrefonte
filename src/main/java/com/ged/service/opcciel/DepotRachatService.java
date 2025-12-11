
package com.ged.service.opcciel;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.OperationSouscriptionRachatDto;
import com.ged.dto.opcciel.comptabilite.VerifDepSouscriptionIntRachatDto;
import com.ged.dto.request.PrecalculSouscriptionRequest;
import com.ged.dto.request.VerificationListeDepotRequest;
import com.ged.dto.standard.PhForm;
import com.ged.dto.standard.PmForm;
import com.ged.entity.opcciel.DepotRachat;
import com.ged.projection.FT_DepotRachatProjection;
import com.ged.projection.NbrePartProjection;
import com.ged.projection.PrecalculRachatProjection;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface DepotRachatService {
    ResponseEntity<Object> afficherTousLesDepots(DatatableParameters parameters, Long idOpcvm, Long idSeance);
    ResponseEntity<Object> afficherDepotRachatTransfert(DatatableParameters parameters, Long idOpcvm, Long idSeance);

    ResponseEntity<Object> listeDepotAVerifier(VerificationListeDepotRequest verificationListeDepotRequest);
    ResponseEntity<Object> importDepotPH(List<PhForm> phForm);
    ResponseEntity<Object> importDepotPM(List<PmForm> pmForm);

    ResponseEntity<Object> afficherTous(DatatableParameters parameters, long idOpcvm, long idSeance, String codeNatureOperation);
    ResponseEntity<Object> afficherTous(long idOpcvm,
                                        long idSeance,
                                        String codeNatureOperation,
                                        Boolean estVerifier,
                                        Boolean estVerifie1,
                                        Boolean estVerifie2);
    Page<DepotRachatDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    List<NbrePartProjection> afficherNbrePart(Long idOpcvm, Long idActionnaire);
    List<PrecalculRachatProjection> afficherPrecalculRachat(Long idSeance,Long idOpcvm, Long idPersonne);
    List<FT_DepotRachatProjection> afficherFT_DepotRachat(Long IdOpcvm,boolean niveau1,boolean niveau2);
    List<FT_DepotRachatProjection> afficherDepotRachatTransfert(Long IdOpcvm,boolean niveau1,boolean niveau2);
    List<FT_DepotRachatProjection> verifIntentionRachat(Long IdOpcvm,boolean niveau1,boolean niveau2, HttpServletResponse response) throws IOException, JRException;
    List<FT_DepotRachatProjection> verifIntentionRachatN1N2(Long IdOpcvm,boolean niveau1,boolean niveau2, HttpServletResponse response) throws IOException, JRException;
    DepotRachat afficherSelonId(Long IdOperation);
    List<FT_DepotRachatProjection> verifSouscriptionTRansfertTitre(Long IdOpcvm,boolean niveau1,boolean niveau2, HttpServletResponse response) throws IOException, JRException;
    List<FT_DepotRachatProjection> verifSouscriptionTRansfertTitreVerifNiv1(Long IdOpcvm,boolean niveau1,boolean niveau2, HttpServletResponse response) throws IOException, JRException;
    List<FT_DepotRachatProjection> verifSouscriptionTRansfertTitreVerifNiv2(Long IdOpcvm,boolean niveau1,boolean niveau2, HttpServletResponse response) throws IOException, JRException;
    ResponseEntity<Object> afficher(Long IdOperation);
    ResponseEntity<Object> calculer(DepotRachatDto depotRachatDto);

    ResponseEntity<Object> creer(DepotRachatDto DepotRachatDto, String type);

    ResponseEntity<Object> creer(DepotRachatDto depotRachatDto);
    ResponseEntity<Object> creerDepotRachatTransfert(DepotRachatDto depotRachatDto);
    ResponseEntity<Object> modifier(VerifDepSouscriptionIntRachatDto verifDepSouscriptionIntRachatDto);
    ResponseEntity<Object> creer(Long[] ids,String userLogin);

    ResponseEntity<Object> modifier(DepotRachatDto depotRachatDto, String type, Long id);

    ResponseEntity<Object> modifier(DepotRachatDto depotRachatDto);
    ResponseEntity<Object> modifier(Long[] id,String userLogin);

    ResponseEntity<Object> confirmerListeVerifDepot(List<DepotRachatDto> depotRachatDtos);

    ResponseEntity<Object> confirmerListeVerifNiv2Depot(List<DepotRachatDto> depotRachatDtos);

    ResponseEntity<Object> precalculSouscription(PrecalculSouscriptionRequest precalcul);
    ResponseEntity<Object> precalculSouscriptionListe(PrecalculSouscriptionRequest precalcul);

    ResponseEntity<Object> genererSouscription(List<OperationSouscriptionRachatDto> souscriptionRachatDtos);

    ResponseEntity<Object> supprimer(Long IdOperation);
}
