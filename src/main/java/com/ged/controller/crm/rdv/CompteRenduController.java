package com.ged.controller.crm.rdv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.crm.CompteRenduDto;
import com.ged.dto.standard.CrStateRequest;
import com.ged.service.crm.CompteRenduService;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/compterendus")
public class CompteRenduController {
    private final CompteRenduService compteRenduService;
    private final ObjectMapper objectMapper;

    public CompteRenduController(CompteRenduService compteRenduService, ObjectMapper objectMapper) {
        this.compteRenduService = compteRenduService;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_COMPTE_RENDU')")
    public List<CompteRenduDto> afficherTous()
    {
        return compteRenduService.afficherTous();
    }

    @GetMapping("/etats/tous")
    @PreAuthorize("hasAuthority('ROLE_COMPTE_RENDU')")
    public List<CompteRenduDto> afficherTousEtat()
    {
        return compteRenduService.afficherTousEtat();
    }

    @GetMapping("/liste/{id}")
    @PreAuthorize("hasAuthority('ROLE_COMPTE_RENDU')")
    public List<CompteRenduDto> afficherCompteRenduSelonUtilisateur(@PathVariable("id") long id)
    {
        return compteRenduService.afficherCompteRenduSelonUtilisateur(id);
    }

    @GetMapping("/realisation/{id}/{dateDeb}/{dateFin}")
    @PreAuthorize("hasAuthority('ROLE_COMPTE_RENDU')")
    public List<CompteRenduDto> afficherCompteRenduSelonRealisation(@PathVariable("id") long id,
                                                                    @PathVariable("dateDeb")LocalDateTime dateDeb,
                                                                    @PathVariable("dateFin") LocalDateTime dateFin)
    {
        return compteRenduService.afficherCompteRenduSelonRealisation(id,dateDeb,dateFin);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_COMPTE_RENDU')")
    public CompteRenduDto afficherCompteRendu(@PathVariable(name = "id") Long id)
    {
        return compteRenduService.afficherCompteRendu(id);
    }

    @PostMapping("/validate-{id}")
    @PreAuthorize("hasAuthority('ROLE_COMPTE_RENDU')")
    public CompteRenduDto validateCR(@PathVariable Long id, @RequestBody CrStateRequest crStateRequest)
    {
        return compteRenduService.validateCR(id, crStateRequest);
    }

    @PostMapping("/datatable/list")
    @PreAuthorize("hasAuthority('ROLE_COMPTE_RENDU')")
    public DataTablesResponse<CompteRenduDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<CompteRenduDto> compteRenduDtoPage = compteRenduService.afficherCompteRendus(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength());
        List<CompteRenduDto> content = compteRenduDtoPage.getContent().stream().collect(Collectors.toList());
        DataTablesResponse<CompteRenduDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) compteRenduDtoPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) compteRenduDtoPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_COMPTE_RENDU')")
    public CompteRenduDto ajouter(@RequestBody CompteRenduDto compteRenduDto) throws Throwable {
        return compteRenduService.creerCompteRendu(null, compteRenduDto);
    }

    @PostMapping(value = "/uploads/file", consumes = {"*/*"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public CompteRenduDto addUploadedFile(
            @RequestParam(value = "files",required = false) List<MultipartFile> files,
            @RequestParam(value = "data", required = false) String data) throws IOException {
        CompteRenduDto compteRenduDto = objectMapper.readValue(data, CompteRenduDto.class);
        try {
            compteRenduDto = compteRenduService.modifierCompteRendu(files, compteRenduDto);
        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return compteRenduDto;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_COMPTE_RENDU')")
    public CompteRenduDto modifier(@RequestBody CompteRenduDto compteRenduDto, @PathVariable Long id) throws Throwable {
        compteRenduDto.setIdCR(id);
        return compteRenduService.modifierCompteRendu(null, compteRenduDto);
    }

    @PutMapping(value = "/uploads/file-{id}", consumes = {"*/*"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public CompteRenduDto updateUploadedFile(
            @PathVariable Long id,
            @RequestParam(value = "files",required = false) List<MultipartFile> files,
            @RequestParam(value = "data", required = false) String data) throws IOException {
        CompteRenduDto compteRenduDto = objectMapper.readValue(data, CompteRenduDto.class);
        try {
            compteRenduDto.setIdCR(id);
            compteRenduDto = compteRenduService.modifierCompteRendu(files, compteRenduDto);

        }
        catch (Throwable e) {
            throw new RuntimeException(e);
        }

        return compteRenduDto;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_COMPTE_RENDU')")
    public void supprimer(@PathVariable Long id)
    {
        compteRenduService.supprimerCompteRendu(id);
    }
}
