package com.ged.controller.standard.notification;

import com.ged.dto.standard.ProtoAlerteDto;
import com.ged.entity.standard.CleProtoAlerte;
import com.ged.service.standard.ProtoAlerteService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/protoAlertes")
public class ProtoAlerteController {
    private final ProtoAlerteService protoAlerteService;

    public ProtoAlerteController(ProtoAlerteService protoAlerteService) {
        this.protoAlerteService = protoAlerteService;
    }

    @GetMapping
    public Page<ProtoAlerteDto> afficherTous(@RequestParam(value = "page",defaultValue = "0") int page,
                                            @RequestParam(value = "size",defaultValue = "10") int size){
        return protoAlerteService.afficherProtoAlertes(page,size);
    }
    @GetMapping("/{idAlerte}/{idModele}")
    public ProtoAlerteDto afficher(@PathVariable("idAlerte") long idAlerte,
                                   @PathVariable("idModele") long idModele) {
        CleProtoAlerte cleProtoAlerte=new CleProtoAlerte();
        cleProtoAlerte.setIdModele(idModele);
        cleProtoAlerte.setIdAlerte(idAlerte);
        return protoAlerteService.afficherProtoAlerte(cleProtoAlerte);
    }
    @PostMapping
    public ProtoAlerteDto ajouter(@RequestBody ProtoAlerteDto protoAlerteDto)
    {
        return protoAlerteService.creerProtoAlerte(protoAlerteDto);
    }
    @PutMapping("/{idAlerte}/{idModele}")
    public ProtoAlerteDto modifier(@PathVariable long idAlerte,
                                   @PathVariable long idModele,
                                   @RequestBody ProtoAlerteDto protoAlerteDto){
        protoAlerteDto.getAlerte().setIdAlerte(idAlerte);
        protoAlerteDto.getModeleMsgAlerte().setIdModele(idModele);
        return protoAlerteService.modifierProtoAlerte(protoAlerteDto);
    }
    @DeleteMapping("/{idAlerte}/{idModele}")
    public void Supprimer(@PathVariable long idAlerte,
                          @PathVariable long idModele){
        CleProtoAlerte cleProtoAlerte=new CleProtoAlerte();
        cleProtoAlerte.setIdModele(idModele);
        cleProtoAlerte.setIdAlerte(idAlerte);
        protoAlerteService.supprimerProtoAlerte(cleProtoAlerte);
    }
}
