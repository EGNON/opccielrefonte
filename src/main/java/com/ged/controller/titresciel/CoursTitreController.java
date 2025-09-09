package com.ged.controller.titresciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.TableRequest;
import com.ged.dto.request.CoursTitreRequest;
import com.ged.dto.request.ReleveTitreFCPRequest;
import com.ged.dto.titresciel.CleCoursTitreDto;
import com.ged.dto.titresciel.CoursTitreDto;
import com.ged.entity.titresciel.CleCoursTitre;
import com.ged.service.AppService;
import com.ged.service.titresciel.CoursTitreService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import net.sf.jasperreports.engine.JRException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/courstitres")
public class CoursTitreController {
    private final CoursTitreService CoursTitreService;
    private final AppService librairieService;

    public CoursTitreController(CoursTitreService CoursTitreService, AppService librairieService) {
        this.CoursTitreService = CoursTitreService;
        this.librairieService = librairieService;
    }

    @PostMapping("/dernier/cours/{idTitre}")
    public ResponseEntity<Object> getLastCoursTitre(@PathVariable Long idTitre) {
        return CoursTitreService.getLastCoursTitre(idTitre);
    }

    @PostMapping("/place/{codePlace}")
    public ResponseEntity<Object> getAllDateCours(
            @PathVariable String codePlace,
            @RequestBody TableRequest tableRequest) {
        return CoursTitreService.getAllDateCours(codePlace, tableRequest);
    }
    @PostMapping("/place")
    public ResponseEntity<?> getAllDateCours(
            @RequestBody CoursTitreRequest tableRequest) {
        return librairieService.placeCoursTitre(tableRequest);
    }

    @PostMapping("/maj/cours/{codePlace}")
    public ResponseEntity<Object> majCoursTitrePlace(
            @PathVariable("codePlace") String codePlace,
            @RequestBody TableRequest tableRequest
    ) {
        return CoursTitreService.getAllCoursTitreMaj(codePlace, tableRequest);
    }
    @PostMapping("/maj/cours")
    public ResponseEntity<?> majCoursTitrePlace(
            @RequestBody CoursTitreRequest tableRequest
    ) {
        return librairieService.coursTitre(tableRequest);
    }
    @PostMapping("/verificationcours")
    public ResponseEntity<Object> verificationCours(@RequestBody @Valid CoursTitreRequest request, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=verificationCoursNiveau_"+request.getNiveau()+"_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        return librairieService.coursTitre(request,response);
    }
    @PostMapping("/validationverificationcours")
    public ResponseEntity<?> validerCours(@RequestBody @Valid CoursTitreRequest request) throws JRException, IOException {

        return librairieService.verifCours(request);
    }
    @GetMapping("/{idTitre}/{dateCours}")
    public ResponseEntity<Object> afficher(@PathVariable Long idTitre,
                                           @PathVariable LocalDateTime dateCours)
    {
        CleCoursTitre cleCoursTitre=new CleCoursTitre();
        cleCoursTitre.setDateCours(dateCours);
        cleCoursTitre.setIdTitre(idTitre);
        return CoursTitreService.afficher(cleCoursTitre);
    }

    @PostMapping("/datatable/list")
//    @PreAuthorize("hasAuthority('ROLE_CoursTitre')")
    public ResponseEntity<Object> datatableList(@RequestBody DatatableParameters params) {
        return CoursTitreService.afficherTous(params);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_CoursTitre')")
    public ResponseEntity<Object> creer(@Valid @RequestBody CoursTitreDto CoursTitreDto)
    {
        return CoursTitreService.creer(CoursTitreDto);
    }

    @PostMapping("/add/collection/all{codePlace}")
    public ResponseEntity<Object> addAll(
            @Valid @RequestBody List<CoursTitreDto> coursTitreDtos,
            @PathVariable(name = "codePlace") String codePlace) {
        return CoursTitreService.addAll(coursTitreDtos, codePlace);
    }

    @PutMapping("/{idTitre}/{dateCours}")
//    @PreAuthorize("hasAuthority('ROLE_CoursTitre')")
    public ResponseEntity<Object> modifier(
            @Valid @RequestBody CoursTitreDto CoursTitreDto,
            @Positive @PathVariable Long idTitre,
            @PathVariable LocalDateTime dateCours)
    {
        CleCoursTitreDto cleCoursTitre=new CleCoursTitreDto();
        cleCoursTitre.setIdTitre(idTitre);
        cleCoursTitre.setDateCours(dateCours);
        CoursTitreDto.setIdCoursTitre(cleCoursTitre);
        return CoursTitreService.modifier(CoursTitreDto);
    }

    @DeleteMapping("/{idTitre}/{dateCours}")
//    @PreAuthorize("hasAuthority('ROLE_CoursTitre')")
    public ResponseEntity<Object> supprimer(@Positive @PathVariable Long idTitre,
                                            @PathVariable LocalDateTime dateCours)
    {
        CleCoursTitre cleCoursTitre=new CleCoursTitre();
        cleCoursTitre.setDateCours(dateCours);
        cleCoursTitre.setIdTitre(idTitre);
        return CoursTitreService.supprimer(cleCoursTitre);
    }
}
