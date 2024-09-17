package com.ged.service.standard;

import com.ged.dto.standard.ProtoAlerteDto;
import com.ged.entity.standard.CleProtoAlerte;
import com.ged.entity.standard.ProtoAlerte;
import org.springframework.data.domain.Page;

public interface ProtoAlerteService {
    Page<ProtoAlerteDto> afficherProtoAlertes(int page, int size);
    ProtoAlerte afficherProtoAlerteSelonId(CleProtoAlerte idProtoAlerte);
    ProtoAlerteDto afficherProtoAlerte(CleProtoAlerte idProtoAlerte);
    ProtoAlerteDto creerProtoAlerte(ProtoAlerteDto protoAlerteDto);
    ProtoAlerteDto modifierProtoAlerte(ProtoAlerteDto protoAlerteDto);
    void supprimerProtoAlerte(CleProtoAlerte idProtoAlerte);
}
