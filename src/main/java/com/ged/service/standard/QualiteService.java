package com.ged.service.standard;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.QualiteDto;
import com.ged.entity.standard.Qualite;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface QualiteService{

    ResponseEntity<Object> afficherTous(Boolean phOrPm);

    Boolean existByLibelle(String libelle);
    DataTablesResponse<QualiteDto> afficherTous(DatatableParameters parameters);

    ResponseEntity<Object> afficherTousPh(Long idPersonne);

    ResponseEntity<Object> afficherTousPm(Long idPersonne);

    Page<QualiteDto> afficherQualites(int page, int size);
    QualiteDto afficherSelonLibelle(String keyword);
    QualiteDto rechercherQualiteParLibelle(String libelle);
    Qualite afficherQualiteSelonId(long idQualite);
    QualiteDto afficherQualite(long idQualite);
    QualiteDto creerQualite(QualiteDto qualiteDto);
    QualiteDto modifierQualite(QualiteDto qualiteDto);
    void supprimerQualite(long idQualite);
}
