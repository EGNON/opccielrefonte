package com.ged.controller.standard.parametre;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.RDVEtatDto;
import com.ged.dto.standard.PersonnePhysiqueDto;
import com.ged.dto.standard.PersonnePhysiqueDtoEJ;
import com.ged.projection.FicheKYCProjection;
import com.ged.projection.PersonnePhysiqueProjection;
import com.ged.service.standard.PersonnePhysiqueService;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/personnephysiques")
//@PreAuthorize("hasAuthority('ROLE_PHYSIQUE')")
public class PersonnePhysiqueController {
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads";
    private final PersonnePhysiqueService personnePhysiqueService;
    private final ObjectMapper objectMapper;

    public PersonnePhysiqueController(PersonnePhysiqueService personnePhysiqueService, ObjectMapper objectMapper) {
        this.personnePhysiqueService = personnePhysiqueService;
        this.objectMapper = objectMapper;
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    @GetMapping("tous")
    public ResponseEntity<Object> afficherTous() {
        return personnePhysiqueService.afficherTous();
    }

    @GetMapping
    public Page<PersonnePhysiqueDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return personnePhysiqueService.afficherPersonnePhysiques(page,size);
    }

    @GetMapping("qualite/{qualite}")
    public List<PersonnePhysiqueDto> afficherSelonQualite(@PathVariable("qualite") String qualite)
    {
        return personnePhysiqueService.afficherSelonQualite(qualite);
    }
    @GetMapping("qualitelab/{qualite}")
    public List<PersonnePhysiqueDto> afficherSelonQualiteLab(@PathVariable("qualite") String qualite)
    {
        return personnePhysiqueService.afficherSelonQualiteLab(qualite);
    }
    @GetMapping("/qualite/etat/{qualite}")
    public List<PersonnePhysiqueProjection> afficherSelonQualiteEtat(@PathVariable String qualite, HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+qualite.toLowerCase()+"_physique"+  currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        return personnePhysiqueService.afficherSelonQualiteEtat(qualite,response);
    }
    @GetMapping("/fichekyc/{idPersonne}")
        public List<FicheKYCProjection> afficherFicheKYC(@PathVariable Long idPersonne, HttpServletResponse response) throws JRException, IOException {
            response.setContentType("application/pdf");
            DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
            String currentDateTime = dateFormatter.format(new Date());

            String headerKey = "Content-Disposition";
            String headerValue = "attachment; filename=ficheKYC"+  currentDateTime + ".pdf";
            response.setHeader(headerKey, headerValue);
            return personnePhysiqueService.afficherFicheKYC(idPersonne,response);
        }

    @GetMapping("investietat/{qualite}/{dateDebut}/{dateFin}")
    public List<PersonnePhysiqueProjection> afficherPersonnePhysiqueNayantPasInvestiEtat(@PathVariable("qualite") String qualite,
                                                                          @PathVariable("dateDebut") LocalDateTime dateDebut,
                                                                          @PathVariable("dateFin") LocalDateTime dateFin
                                                                          , HttpServletResponse response) throws JRException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("ddMMyyyy:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=client_Physique_Nayant_pas_investi"+  currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
        return personnePhysiqueService.afficherPersonnePhysiqueNayantPasInvestiEtat(qualite,dateDebut,dateFin,response);
    }
    @GetMapping("investi/{qualite}/{dateDebut}/{dateFin}")
    public List<PersonnePhysiqueDto> afficherPersonnePhysiqueNayantPasInvesti(@PathVariable("qualite") String qualite,
                                                                          @PathVariable("dateDebut") LocalDateTime dateDebut,
                                                                          @PathVariable("dateFin") LocalDateTime dateFin)
    {
        return personnePhysiqueService.afficherPersonnePhysiqueNayantPasInvesti(qualite,dateDebut,dateFin);
    }

    @GetMapping("statut/{qualite}-{id}")
    public PersonnePhysiqueDto afficherSelonIdQualite(
            @PathVariable("id") Long id,
            @PathVariable("qualite") String qualite)
    {
        return personnePhysiqueService.afficherSelonIdQualite(id, qualite);
    }

    @GetMapping("{id}")
    public PersonnePhysiqueDto afficher(@PathVariable("id") Long id)
    {
        return personnePhysiqueService.afficherPersonnePhysique(id);
    }

    @PostMapping("/datatable-{qualite}/list")
    public DataTablesResponse<PersonnePhysiqueDto> datatableList(
            @RequestBody DatatableParameters params,
            @PathVariable String qualite)
    {
        return personnePhysiqueService.afficherTous(qualite, params);
    }

    @PostMapping("/datatable/physiqueexpose")
    public DataTablesResponse<PersonnePhysiqueDto> datatableList_Expose(
            @RequestBody DatatableParameters params)
    {
        return personnePhysiqueService.afficherPersonneExpose(params);
    }
    @PostMapping("/datatable/physiquejuge")
    public DataTablesResponse<PersonnePhysiqueDto> datatableList_Juge(
            @RequestBody DatatableParameters params)
    {
        return personnePhysiqueService.afficherPersonneJuge(params);
    }

    @PostMapping("/datatable/juge/list-{qualite}")
    public DataTablesResponse<PersonnePhysiqueDto> datatableListJuge(
            @RequestBody DatatableParameters params,
            @PathVariable String qualite)
    {
        return personnePhysiqueService.afficherPersonnePhysiqueJuge(qualite, params);
    }

    @PostMapping("/datatable/expose/list-{qualite}")
    public DataTablesResponse<PersonnePhysiqueDto> datatableListExpose(
            @RequestBody DatatableParameters params,
            @PathVariable String qualite)
    {
        return personnePhysiqueService.afficherPersonnePhysiquePolitiquementExpose(qualite, params);
    }

    @PostMapping
    public PersonnePhysiqueDto ajouter(@RequestBody PersonnePhysiqueDto personnePhysiqueDto)
    {
        return personnePhysiqueService.creerPersonnePhysique(null, personnePhysiqueDto);
    }

    @PostMapping(value = "/uploads/file", consumes = {"*/*"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonnePhysiqueDto addUploadedFile(
            @RequestParam(value = "files",required = false) List<MultipartFile> files,
            @RequestParam(value = "data", required = false) String data) throws IOException {
        PersonnePhysiqueDto personnePhysiqueDto = objectMapper.readValue(data, PersonnePhysiqueDto.class);
        try {
            personnePhysiqueDto = personnePhysiqueService.creerPersonnePhysique(files, personnePhysiqueDto);
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return personnePhysiqueDto;
    }

    @PutMapping("/{id}")
    public PersonnePhysiqueDto modifier(@PathVariable long id, @RequestBody PersonnePhysiqueDto personnePhysiqueDto) throws Throwable {
        personnePhysiqueDto.setIdPersonne(id);
        return personnePhysiqueService.modifierPersonnePhysique(null, personnePhysiqueDto);
    }

    @PutMapping(value = "/uploads/file-{id}", consumes = {"*/*"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonnePhysiqueDto updateUploadedFile(
            @PathVariable Long id,
            @RequestParam(value = "files",required = false) List<MultipartFile> files,
            @RequestParam(value = "data", required = false) String data) throws IOException {
        PersonnePhysiqueDto personnePhysiqueDto = objectMapper.readValue(data, PersonnePhysiqueDto.class);
        try {
                personnePhysiqueDto.setIdPersonne(id);
                personnePhysiqueDto = personnePhysiqueService.modifierPersonnePhysique(files, personnePhysiqueDto);

        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return personnePhysiqueDto;
    }
    @PutMapping(value = "/uploads/filelab-{id}", consumes = {"*/*"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonnePhysiqueDto updateUploadedFileLab(
            @PathVariable Long id,
            @RequestParam(value = "files",required = false) List<MultipartFile> files,
            @RequestParam(value = "data", required = false) String data) throws IOException {
        PersonnePhysiqueDto personnePhysiqueDto = objectMapper.readValue(data, PersonnePhysiqueDto.class);
        try {
                personnePhysiqueDto.setIdPersonne(id);
                personnePhysiqueDto = personnePhysiqueService.modifierPersonnePhysiqueLab(files, personnePhysiqueDto);

        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return personnePhysiqueDto;
    }

    /*@PostConstruct
    @EventListener(ApplicationReadyEvent.class)
    public List<Object> createPHFromOpcciel1() throws JsonProcessingException {
        return personnePhysiqueService.createPHFromOppciel1();
    }*/

    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        personnePhysiqueService.supprimerPersonnePhysique(id);
    }

    @DeleteMapping("/{idPersonne}/{idQualite}")
    public void Supprimer(@PathVariable long idPersonne,@PathVariable long idQualite){
        personnePhysiqueService.supprimerPersonnePhysique(idPersonne,idQualite);
    }

    @PostMapping("/EJ")
    public PersonnePhysiqueDtoEJ ajouter(@RequestBody PersonnePhysiqueDtoEJ personnePhysiqueDto)
    {
        return personnePhysiqueService.creerPersonnePhysiqueEJ(null, personnePhysiqueDto);
    }

    @PostMapping(value = "EJ/uploads/file", consumes = {"*/*"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonnePhysiqueDtoEJ addUploadedFileEJ(
            @RequestParam(value = "files",required = false) List<MultipartFile> files,
            @RequestParam(value = "data", required = false) String data) throws IOException {
        PersonnePhysiqueDtoEJ personnePhysiqueDto = objectMapper.readValue(data, PersonnePhysiqueDtoEJ.class);
        try {
            personnePhysiqueDto = personnePhysiqueService.modifierPersonnePhysiqueEJ(files, personnePhysiqueDto);
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return personnePhysiqueDto;
    }

    @PutMapping("EJ/{id}")
    public PersonnePhysiqueDtoEJ modifier(@PathVariable long id, @RequestBody PersonnePhysiqueDtoEJ personnePhysiqueDto) throws Throwable {
        personnePhysiqueDto.setIdPersonne(id);
        return personnePhysiqueService.modifierPersonnePhysiqueEJ(null, personnePhysiqueDto);
    }

    @PutMapping(value = "EJ/uploads/file-{id}", consumes = {"*/*"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public PersonnePhysiqueDtoEJ updateUploadedFileEJ(
            @PathVariable Long id,
            @RequestParam(value = "files",required = false) List<MultipartFile> files,
            @RequestParam(value = "data", required = false) String data) throws IOException {
        PersonnePhysiqueDtoEJ personnePhysiqueDto = objectMapper.readValue(data, PersonnePhysiqueDtoEJ.class);
        try {
            personnePhysiqueDto.setIdPersonne(id);
            personnePhysiqueDto = personnePhysiqueService.modifierPersonnePhysiqueEJ(files, personnePhysiqueDto);

        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return personnePhysiqueDto;
    }
}
