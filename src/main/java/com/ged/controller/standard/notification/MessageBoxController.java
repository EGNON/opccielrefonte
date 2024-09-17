package com.ged.controller.standard.notification;

import com.ged.dto.standard.MessageBoxDto;
import com.ged.service.standard.MessageBoxService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/messagesbox")
public class MessageBoxController {
    private final MessageBoxService messageBoxService;

    public MessageBoxController(MessageBoxService messageBoxService) {
        this.messageBoxService = messageBoxService;
    }

    @GetMapping
    public List<MessageBoxDto> afficherTous()
    {
        return messageBoxService.afficherTous();
    }

    @PostMapping
    public MessageBoxDto ajouter(@RequestBody MessageBoxDto messageBoxDto)
    {
        return messageBoxService.modifierMsg(messageBoxDto);
    }

    @PutMapping("{id}")
    public MessageBoxDto modifier(@RequestBody MessageBoxDto messageBoxDto, @PathVariable("id") Long id)
    {
        messageBoxDto.setIdMsgBox(id);
        return messageBoxService.modifierMsg(messageBoxDto);
    }

    @DeleteMapping("{id}")
    public void supprimer(@PathVariable("id") Long id)
    {
        messageBoxService.supprimerMsg(id);
    }
}
