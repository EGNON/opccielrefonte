package com.ged.service.crm;

import com.ged.dto.crm.DetailObjectifDto;
import com.ged.entity.crm.CleDetailObjectif;
import com.ged.entity.crm.DetailObjectif;
import org.springframework.data.domain.Page;

public interface DetailObjectifService{
    Page<DetailObjectifDto> afficherDetailObjectifs(int page, int size);
    DetailObjectif afficherDetailObjectifSelonId(CleDetailObjectif idDetailObjectif);
    DetailObjectifDto creerDetailObjectif(DetailObjectifDto detailObjectifDto);
    DetailObjectifDto modifierDetailObjectif(DetailObjectifDto detailObjectifDto);
    void supprimerDetailObjectif(CleDetailObjectif cleDetailObjectif);
}
