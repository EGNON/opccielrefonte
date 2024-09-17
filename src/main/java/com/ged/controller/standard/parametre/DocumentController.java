package com.ged.controller.standard.parametre;

import com.ged.dto.standard.DocumentDto;
import com.ged.mapper.standard.DocumentMapper;
import com.ged.service.standard.DocumentService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/documents")
public class DocumentController {
    private final DocumentService documentService;
    private final DocumentMapper documentMapper;

    public DocumentController(DocumentService documentService, DocumentMapper documentMapper) {
        this.documentService = documentService;
        this.documentMapper = documentMapper;
    }

    @GetMapping
    public Page<DocumentDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return documentService.afficherDocuments(page, size);
    }

    @GetMapping("/tous")
    public List<DocumentDto> afficherDocumentsTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                                   @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return documentService.afficherTous();
    }
    @GetMapping("/{id}")
    public DocumentDto afficherDocumentSelonId(@PathVariable long id)
    {
        return documentMapper.deDocument(documentService.afficherDocumentSelonId(id));
    }
    @GetMapping("/upload/{id}")
    public byte[] uploadDocument(@PathVariable long id) throws IOException {
        return documentService.uploadDocument(id);
    }
    @GetMapping("/rdv/{idRdv}")
    public List<DocumentDto> afficherDocumentSelonRDV(@PathVariable long idRdv)
    {
        return documentService.afficherDocumentSelonRDV(idRdv);
    }
    @GetMapping("/compterendu/{idCr}")
    public List<DocumentDto> afficherDocumentSelonCR(@PathVariable long idCr)
    {
        return documentService.afficherDocumentSelonCR(idCr);
    }
    @GetMapping("/personne/{idPersonne}")
    public List<DocumentDto> afficherDocumentSelonPersonne(@PathVariable long idPersonne)
    {
        return documentService.afficherDocumentSelonPersonne(idPersonne);
    }

    @PostMapping
    public DocumentDto ajouter(@RequestBody DocumentDto documentDto) throws Throwable {
        return documentService.creerDocument(documentDto);
    }
    @PostMapping("/{id}")
    public void ajouter(@RequestBody DocumentDto[] documentDto,
                               @PathVariable Long id) throws Throwable {
         documentService.creerDocument(documentDto,id);
    }

    @PutMapping("/{id}")
    public DocumentDto modifier(@RequestBody DocumentDto documentDto, @PathVariable long id)
    {
        documentDto.setIdDoc(id);
        return documentService.modifierDocument(documentDto);
    }

    @DeleteMapping("/{id}")
    public void supprimer(@PathVariable long id)
    {
        documentService.supprimerDocument(id);
    }

    @DeleteMapping("/rdv/{id}")
    public void supprimerDocumentselonRDV(@PathVariable long id)
    {
        documentService.supprimerDocumentSelonRDV(id);
    }
}
