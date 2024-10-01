package com.ged.service.standard;

import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PersonnelDto;
import com.ged.entity.standard.Personnel;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonnelService {
    ResponseEntity<Object> afficherTous(DatatableParameters parameters);
    Page<PersonnelDto> afficherPersonnels(int page, int size);
    List<PersonnelDto> afficherPersonnelListe();
    List<PersonnelDto> afficherPersonnelSelonEstCommercil(boolean estCommercial);
    Personnel afficherPersonnelSelonId(long idPersonne);
    ResponseEntity<Object> afficherPersonnel(long idPersonne);
    PersonnelDto creerPersonnel(PersonnelDto personnelDto);
    PersonnelDto modifierPersonnel(PersonnelDto personnelDto);
    void supprimerPersonnel(long idPersonne);
}
