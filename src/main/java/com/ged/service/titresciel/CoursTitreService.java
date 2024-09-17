package com.ged.service.titresciel;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.TableRequest;
import com.ged.dto.lab.reportings.BeginEndDateParameter;
import com.ged.dto.titresciel.CoursTitreDto;
import com.ged.dto.titresciel.CoursTitreDto;
import com.ged.dto.titresciel.DateIntervalRequest;
import com.ged.entity.titresciel.CoursTitre;
import com.ged.entity.titresciel.CleCoursTitre;
import com.ged.entity.titresciel.CoursTitre;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;

public interface CoursTitreService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<CoursTitreDto> afficherTousParPage(int page, int size);
    ResponseEntity<Object> afficherTous();
    CoursTitre afficherSelonId(CleCoursTitre id);
    ResponseEntity<Object> afficher(CleCoursTitre id);
    ResponseEntity<Object> creer(CoursTitreDto CoursTitreDto);
    ResponseEntity<Object> modifier(CoursTitreDto CoursTitreDto);
    ResponseEntity<Object> supprimer(CleCoursTitre id);

    ResponseEntity<Object> getLastCoursTitre(Long idTitre);

    ResponseEntity<Object> getAllDateCours(String codePlace, TableRequest tableRequest);

    ResponseEntity<Object> getAllCoursTitreMaj(
            String codePlace,
            TableRequest tableRequest
    );

    ResponseEntity<Object> addAll(List<CoursTitreDto> coursTitreDtos, String codePlace);
}
