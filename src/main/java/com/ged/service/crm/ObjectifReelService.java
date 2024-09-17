package com.ged.service.crm;

import com.ged.dto.crm.ObjectifReelDto;
import com.ged.entity.crm.ObjectifReel;
import org.springframework.data.domain.Page;

public interface ObjectifReelService  {
    Page<ObjectifReelDto> afficherObjectifReels(int page, int size);
    ObjectifReel afficherObjectifReelSelonId(long idObjectifReel);
    ObjectifReelDto creerObjectifReel(ObjectifReelDto objectifReelDto);
    ObjectifReelDto modifierObjectifReel(ObjectifReelDto objectifReelDto);
    void supprimerObjectifReel(long idObjectifReel);

}
