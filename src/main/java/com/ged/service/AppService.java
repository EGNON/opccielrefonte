package com.ged.service;

import com.ged.dao.LibraryDao;
import com.ged.dao.opcciel.comptabilite.LigneMvtClotureExoDao;
import com.ged.dao.opcciel.comptabilite.MouvementDao;
import com.ged.dto.opcciel.comptabilite.OperationDto;
import com.ged.dto.request.ChargerLigneMvtRequest;
import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.opcciel.SeanceOpcvm;
import com.ged.entity.opcciel.comptabilite.Ib;
import com.ged.entity.opcciel.comptabilite.Mouvement;
import com.ged.entity.opcciel.comptabilite.Plan;
import com.ged.projection.LigneMvtClotureProjection;
import com.ged.service.opcciel.IbService;
import com.ged.service.opcciel.OpcvmService;
import com.ged.service.opcciel.PlanService;
import com.ged.service.opcciel.TypeFormuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppService {
    private final LibraryDao libraryDao;
    private final LigneMvtClotureExoDao ligneMvtClotureExoDao;
    private final PlanService planService;
    private final OpcvmService opcvmService;
    private final IbService ibService;
    private final TypeFormuleService typeFormuleService;
    private final MouvementDao mouvementDao;

    public AppService(
            LibraryDao libraryDao,
            LigneMvtClotureExoDao ligneMvtClotureExoDao,
            PlanService planService, OpcvmService opcvmService, IbService ibService, TypeFormuleService typeFormuleService, MouvementDao mouvementDao) {
        this.libraryDao = libraryDao;
        this.ligneMvtClotureExoDao = ligneMvtClotureExoDao;
        this.planService = planService;
        this.opcvmService = opcvmService;
        this.ibService = ibService;
        this.typeFormuleService = typeFormuleService;
        this.mouvementDao = mouvementDao;
    }

    public SeanceOpcvm currentSeance(Long idOpcvm) {
        return libraryDao.currentSeance(idOpcvm);
    }

    public List<Mouvement> chargerLigneMvt(ChargerLigneMvtRequest chargerLigneMvtRequest) {
        List<LigneMvtClotureProjection> ligneMvtClotureProjections = libraryDao.chargerLigneMvt(
                chargerLigneMvtRequest.getCodeNatureOperation(),
                chargerLigneMvtRequest.getValeurCodeAnalytique(),
                chargerLigneMvtRequest.getValeurFormule(),
                chargerLigneMvtRequest.getIdOpcvm(),
                chargerLigneMvtRequest.getIdActionnaire(),
                chargerLigneMvtRequest.getIdTitre()
        );
        return ligneMvtClotureProjections.stream().map(l -> {
            Mouvement mvt = new Mouvement();
            mvt.setOperation(chargerLigneMvtRequest.getOperation());
            if(l.getCodePlan() != null && !l.getCodePlan().isEmpty()) {
                Plan plan = planService.afficherSelonId(l.getCodePlan());
                mvt.setPlan(plan);
            }
            if(l.getIdOpcvm() != null) {
                Opcvm opcvm = opcvmService.afficherSelonId(l.getIdOpcvm());
                mvt.setOpcvm(opcvm);
            }
            String codeIb = l.getCodeIb();
            if(codeIb != null && !codeIb.isEmpty()) {
                Ib ib = ibService.afficherSelonId(codeIb.trim());
                mvt.setIb(ib);
                mvt.setiB(codeIb.trim());
            }
            mvt.setPosition(l.getCodePosition().trim());
            mvt.setCodeModeleEcriture(l.getCodeModeleEcriture().trim());
            mvt.setNumCompteComptable(l.getNumCompteComptable().trim());
            mvt.setNumeroOdreModele(l.getNumeroOrdreModele());
            mvt.setNumeroOdreLigneMvt(l.getNumeroOrdreLigneMvt());
            mvt.setSensMvt(l.getSens().trim());
            mvt.setValeur(l.getValeur());
            mvt.setRubrique(l.getCodeRubrique().trim());
            if(l.getCodeTypeFormule() != null && !l.getCodeTypeFormule().isEmpty()) {
//                TypeFormule typeFormule = typeFormuleService.afficherSelonId(l.getCodeTypeFormule().trim());
                mvt.setTypeValeur(l.getCodeTypeFormule().trim());
//                mvt.setTypeFormule(typeFormule);
            }
//            mvt = mouvementDao.save(mvt);
            return mvt;
        }).toList();
    }

    public OperationDto genererEcriture(OperationDto op) {
        //Récupérer les mouvements
        ChargerLigneMvtRequest chargerLigneMvtRequest = new ChargerLigneMvtRequest(
                op.getNatureOperation() != null ? op.getNatureOperation().getCodeNatureOperation() : null,
                op.getValeurCodeAnalytique(),
                op.getValeurFormule(),
                op.getOpcvm() != null ? op.getOpcvm().getIdOpcvm() : null,
                op.getActionnaire() != null ? op.getActionnaire().getIdPersonne() : null,
                op.getTitre() != null ? op.getTitre().getIdTitre() : null
        );
        List<Mouvement> mouvements = chargerLigneMvt(chargerLigneMvtRequest);

        return null;
    }
}
