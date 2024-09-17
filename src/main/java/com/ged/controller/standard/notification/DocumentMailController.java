package com.ged.controller.standard.notification;

import com.ged.dto.standard.DocumentMailDto;
import com.ged.entity.standard.CleDocumentMail;
import com.ged.mapper.standard.DocumentMailMapper;
import com.ged.service.standard.DocumentMailService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/documentmails")
public class DocumentMailController {
    private final DocumentMailService documentMailService;
    private final DocumentMailMapper documentMailMapper;

    public DocumentMailController(DocumentMailService documentMailService, DocumentMailMapper documentMailMapper) {
        this.documentMailService = documentMailService;
        this.documentMailMapper = documentMailMapper;
    }

    @GetMapping
    public Page<DocumentMailDto> afficherTous(@RequestParam(value = "page", defaultValue = "0") int page,
                                             @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return documentMailService.afficherDocumentMails(page, size);
    }
    @GetMapping("/{id}")
    public List<DocumentMailDto> afficherDocumentMailSelonMail(@PathVariable long id)
    {
        return documentMailService.afficherDocumentMailSelonMail(id).stream().map(documentMailMapper::deDocumentMail).collect(Collectors.toList());
    }

    @PostMapping
    public DocumentMailDto ajouter(@RequestBody DocumentMailDto documentMailDto)
    {
        return documentMailService.creerDocumentMail(documentMailDto);
    }

    @PutMapping("/{idDocument}/{idMail}")
    public DocumentMailDto modifier(@PathVariable long idDocument,
                                    @PathVariable long idMail,
                                    @RequestBody DocumentMailDto documentMailDto)
    {
        documentMailDto.getDocumentDto().setIdDoc(idDocument);
        documentMailDto.getMailDto().setIdMail(idMail);
        return documentMailService.modifierDocumentMail(documentMailDto);
    }

    @DeleteMapping("/{idDocument}/{idMail}")
    public void supprimer(@PathVariable long idDocument,
                          @PathVariable long idMail)
    {
        CleDocumentMail cleDocumentMail=new CleDocumentMail();
        cleDocumentMail.setIdDoc(idDocument);
        cleDocumentMail.setIdMail(idMail);
        documentMailService.supprimerDocumentMail(cleDocumentMail);
    }
}
