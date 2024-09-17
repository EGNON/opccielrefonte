package com.ged.service.standard;

import com.ged.dto.standard.DepartementDto;
import com.ged.entity.standard.Departement;
import org.springframework.data.domain.Page;

public interface DepartementService{
    Page<DepartementDto> afficherDepartements(int page, int size);
    Departement afficherDepartementSelonId(long idDepartement);
    DepartementDto creerDepartement(DepartementDto departementDto);
    DepartementDto modifierDepartement(DepartementDto departementDto);
    void supprimerDepartement(Long id);
}
