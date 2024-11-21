
package com.ged.service.opcciel;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.opcciel.DepotRachatDto;
import com.ged.dto.opcciel.comptabilite.VerifDepSouscriptionIntRachatDto;
import com.ged.entity.opcciel.DepotRachat;
import com.ged.projection.FT_DepotRachatProjection;
import com.ged.projection.NbrePartProjection;
import com.ged.projection.PrecalculRachatProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface DepotRachatService {
    ResponseEntity<Object> afficherTousLesDepots(DatatableParameters parameters, Long idOpcvm, Long idSeance);

    ResponseEntity<Object> afficherTous(DatatableParameters parameters, long idOpcvm, long idSeance, String codeNatureOperation);
    ResponseEntity<Object> afficherTous(long idOpcvm,
                                        long idSeance,
                                        String codeNatureOperation,
                                        boolean estVerifier,
                                        boolean estVerifie1,
                                        boolean estVerifie2);
    Page<DepotRachatDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    List<NbrePartProjection> afficherNbrePart(Long idOpcvm, Long idActionnaire);
    List<PrecalculRachatProjection> afficherPrecalculRachat(Long idSeance,Long idOpcvm, Long idPersonne);
    List<FT_DepotRachatProjection> afficherFT_DepotRachat(Long IdOpcvm,boolean niveau1,boolean niveau2);
    DepotRachat afficherSelonId(Long IdOperation);
    ResponseEntity<Object> afficher(Long IdOperation);
    ResponseEntity<Object> creer(DepotRachatDto depotRachatDto);
    ResponseEntity<Object> creer(VerifDepSouscriptionIntRachatDto verifDepSouscriptionIntRachatDto);
    ResponseEntity<Object> creer(Long[] ids,String userLogin);
    ResponseEntity<Object> modifier(DepotRachatDto depotRachatDto);
    ResponseEntity<Object> modifier(Long[] id,String userLogin);
    ResponseEntity<Object> supprimer(Long IdOperation);
}
