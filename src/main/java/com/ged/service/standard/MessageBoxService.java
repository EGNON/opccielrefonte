package com.ged.service.standard;

import com.ged.dto.standard.MessageBoxDto;
import com.ged.entity.standard.MessageBox;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MessageBoxService {
    Page<MessageBoxDto> afficherMsg(int page, int size);
    List<MessageBoxDto> afficherTous();
    MessageBox afficherMsgSelonId(Long idMsg);
    MessageBoxDto creerMsg(MessageBoxDto messageBoxDto);
    MessageBoxDto modifierMsg(MessageBoxDto messageBoxDto);
    void supprimerMsg(Long idMsg);
}
