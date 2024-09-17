package com.ged.controller.standard.notification;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.MailDto;
import com.ged.mapper.standard.MailMapper;
import com.ged.service.standard.MailService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/mails")
public class MailController {
    private final MailService mailService;
    private final MailMapper mailMapper;

    public MailController(MailService MailService, MailMapper mailMapper) {
        this.mailService = MailService;
        this.mailMapper = mailMapper;
    }

    @GetMapping
    public Page<MailDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return mailService.afficherMails(page,size);
    }
    @GetMapping("/{id}")
    public MailDto afficherMail(@PathVariable long id){
        return mailMapper.deMail(mailService.afficherMailSelonId(id));
    }
    @PostMapping("/datatable/list")
    public DataTablesResponse<MailDto> datatableList(@RequestBody DatatableParameters datatableParameters)
    {
        Page<MailDto> mailPage = mailService.afficherMails(datatableParameters.getStart()/datatableParameters.getLength(), datatableParameters.getLength());
        List<MailDto> content = mailPage.getContent().stream().collect(Collectors.toList());
        DataTablesResponse<MailDto> dataTablesResponse = new DataTablesResponse<>();
        dataTablesResponse.setDraw(datatableParameters.getDraw());
        dataTablesResponse.setRecordsFiltered(((int) mailPage.getTotalElements()));
        dataTablesResponse.setRecordsTotal(((int) mailPage.getTotalElements()));
        dataTablesResponse.setData(content);

        return dataTablesResponse;
    }
    @PostMapping
    public MailDto ajouter(@RequestBody MailDto mailDto) throws Throwable {
        return mailService.creerMail(mailDto);
    }
    @PutMapping("/{id}")
    public MailDto modifier(@PathVariable long id, @RequestBody MailDto mailDto) throws Throwable {
        mailDto.setIdMail(id);
        return mailService.modifierMail(mailDto);
    }
    @DeleteMapping("/{id}")
    public void Supprimer(@PathVariable long id){
        mailService.supprimerMail(id);
    }
}
