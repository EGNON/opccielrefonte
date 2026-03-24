package com.ged.controller.opcciel;

import com.ged.dao.LibraryDao;
import com.ged.dto.opcciel.comptabilite.PosteComptableSeanceOpcvmDto;
import com.ged.dto.request.DifferenceEstimationRequest;
import com.ged.projection.CodePosteComptableProjection;
import com.ged.response.ResponseHandler;
import com.ged.service.opcciel.OperationService;
import com.ged.service.opcciel.PosteComptableSeanceOpcvmService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/postecomptableseanceopcvms")
public class PosteComptableSeanceOpcvmController {
    private final PosteComptableSeanceOpcvmService PosteComptableSeanceOpcvmService;
    private final OperationService operationService;
    private final LibraryDao libraryDao;

    public PosteComptableSeanceOpcvmController(PosteComptableSeanceOpcvmService PosteComptableSeanceOpcvmService, OperationService operationService, LibraryDao libraryDao) {

        this.PosteComptableSeanceOpcvmService = PosteComptableSeanceOpcvmService;
        this.operationService = operationService;
        this.libraryDao = libraryDao;
    }

    @GetMapping
    public ResponseEntity<Object> afficherTous()
    {
        return PosteComptableSeanceOpcvmService.afficherTous();
    }

     @GetMapping("/{codePosteComptableSeanceOpcvm}")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> afficherSelonCodePosteComptableSeanceOpcvm(
                                           @PathVariable String codePosteComptableSeanceOpcvm)
    {
        return PosteComptableSeanceOpcvmService.afficherSelonCodePosteComptableSeanceOpcvm(codePosteComptableSeanceOpcvm);
    }
    @GetMapping("/jasperpdf/codepostecomptable/{idOpcvm}/{idSeance}/{estVerifie1}/{estVerifie2}/{niveau}/{niv}")
    public ResponseEntity<Object> ordreDeBourseApercu(@PathVariable Long idOpcvm,
                                                      @PathVariable Long idSeance,
                                                      @PathVariable Boolean estVerifie1,
                                                      @PathVariable Boolean estVerifie2,
                                                      @PathVariable Long niveau,
                                                      @PathVariable Long niv) throws JRException, IOException {
        if(idSeance==0){
            String etapes=operationService.verifierEtape(niveau==1?6L:7L, idOpcvm);

            if (!etapes.equals("")) {

                byte[] messageBytes = (
                        "Les étapes suivantes n'ont pas encore été faites: " + etapes
                ).getBytes(StandardCharsets.UTF_8);

                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(messageBytes);
            }

            byte[] pdfBytes =
                    PosteComptableSeanceOpcvmService.jaspertReportCodePoste(idOpcvm, idSeance, estVerifie1, estVerifie2, niveau);

            String fileName = "verificationDe_Niveau" + niv + "_" +
                    new SimpleDateFormat("ddMMyyyy_HHmmss")
                            .format(new Date()) + ".pdf";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + fileName)
                    .body(pdfBytes);
//
        }
        else {
            String etapes = operationService.verifierEtape(niv, idOpcvm);
            if (!etapes.equals("")) {

                byte[] messageBytes = (
                        "Les étapes suivantes n'ont pas encore été faites: " + etapes
                ).getBytes(StandardCharsets.UTF_8);

                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(messageBytes);
            }

            byte[] pdfBytes =
                    PosteComptableSeanceOpcvmService.jaspertReportCodePoste(idOpcvm, idSeance, estVerifie1, estVerifie2, niveau);

            String fileName = "verificationDe_Niveau" + niv + "_" +
                    new SimpleDateFormat("ddMMyyyy_HHmmss")
                            .format(new Date()) + ".pdf";

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=" + fileName)
                    .body(pdfBytes);

        }

    }
    @PostMapping("/liste")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> datatableList(@RequestBody DifferenceEstimationRequest params) {
        List<CodePosteComptableProjection> list=libraryDao.afficherCodePosteComptable(params.getIdOpcvm(), params.getIdSeance()+1, null, null);
        if(list.size()!=0)
            return ResponseHandler.generateResponse(
                    "Liste des postes comptables enregistrés",
                    HttpStatus.OK,
                    list);
        else
            return PosteComptableSeanceOpcvmService.valoriser(params);
    }
    @PostMapping("/validerniveau")
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> validerNiveau(@RequestBody DifferenceEstimationRequest obj) {
        return PosteComptableSeanceOpcvmService.validerNiveau(obj.getIdOpcvm(), obj.getIdSeance(), obj.getNiveau(),obj.getUserLogin1());
    }
    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_DEGRE')")
    public ResponseEntity<Object> ajouter(@RequestBody List<PosteComptableSeanceOpcvmDto> PosteComptableSeanceOpcvmDto)
    {
        return PosteComptableSeanceOpcvmService.creer(PosteComptableSeanceOpcvmDto);
    }

}
