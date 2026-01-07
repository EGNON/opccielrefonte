package com.ged.service.opcciel;

import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.request.ConsultationEcritureRequest;
import com.ged.dto.request.OperationRequest;
import com.ged.dto.request.VerificationEcritureRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public interface OperationService {
    ResponseEntity<Object> afficherTous(ConsultationEcritureRequest request);
    ResponseEntity<Object> afficherOperationResultat(ConsultationEcritureRequest request);
    ResponseEntity<Object> afficherPaiementCommissionInvestissement(ConsultationEcritureRequest request);
    ResponseEntity<Object> creer(OperationRequest request);
    String verifierEtape(Long niveau,Long idOpcvm) ;
    ResponseEntity<Object> apercuVerificationDE1(Long idOpcvm,Long idSeance,Boolean estVerifie1,Boolean estVerifie2,Long niv, HttpServletResponse response) throws IOException, JRException;
    ResponseEntity<Object> creerTout(OperationRequest request);
    ResponseEntity<Object> actionnaireBanque(Long idOpcvm,String code);
    ResponseEntity<Object> listeVerificationEcriturePage(VerificationEcritureRequest verificationEcritureRequest);
    ResponseEntity<Object> listeVerificationEcritureListe(VerificationEcritureRequest verificationEcritureRequest);
    ResponseEntity<Object> validerVerificationEcritureN1(Long[] list,String userLogin,String codeTypeOperation,String form,Long idOpcvm);
    ResponseEntity<Object> validerVerificationEcritureN2(Long[] list, String userLogin, String codeTypeOperation, String form, Long idOpcvm);
    ResponseEntity<Object> verificationEcritureNiveau1(VerificationEcritureRequest verificationEcritureRequest,String codeTypeOperation);
    ResponseEntity<Object> verificationEcritureNiveau2(VerificationEcritureRequest verificationEcritureRequest,String codeTypeOperation);
    ResponseEntity<Object> verificationeEcritureNiveauPrint(VerificationEcritureRequest verificationEcritureRequest,
                                                            HttpServletResponse response,
                                                            String niveau,
                                                            String codeTypeOperation) throws IOException, JRException;

}
