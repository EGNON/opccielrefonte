package com.ged.service.standard;

import com.ged.datatable.DataTablesResponse;
import com.ged.datatable.DatatableParameters;
import com.ged.dto.standard.PersonneDto;
import com.ged.entity.standard.Personne;
import com.ged.projection.PersonneProjection;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PersonneService  {
    Boolean existsByNumeroCpteDeposit(String numero);

    Page<PersonneDto> afficherPersonnes(int page, int size);
    DataTablesResponse<PersonneDto> afficherCompteGele(DatatableParameters parameters);
    DataTablesResponse<PersonneDto> afficherCompteNonGele(DatatableParameters parameters);
    List<PersonneProjection> afficherPersonnePhysiqueMorales();
    List<PersonneProjection> afficherPersonnePhysiqueMoralesListe();
    List<PersonneProjection> afficherPersonneNotInOpcvm(Long idOpcvm);
    List<PersonneProjection> afficherPersonneInOpcvm(Long idOpcvm);
    List<PersonneProjection> afficherPersonneInOpcvmEtStatutCompte(Long idOpcvm);
    List<PersonneDto> afficherSelonQualite(String qualite);
    List<PersonneDto> afficherSelonQualite();
    List<PersonneDto> recherherNumeroCompteDepositaire(String numero);
    boolean verifierNumeroCompteDepositaire(String numero);
    DataTablesResponse<PersonneDto> afficherSelonQualite(DatatableParameters parameters);
    List<PersonneDto> afficherPersonneTous();
    List<PersonneDto> afficherCompteGeleNonGele();
    List<PersonneDto> afficherCompteGeleNonGele(boolean estGele);
    PersonneProjection afficherPersonnePhysiqueMoraleSelonId(long id);
    Personne afficherPersonneSelonId(long idPersonne);
    PersonneDto creerPersonne(PersonneDto personneDto);
    PersonneDto modifierPersonne(PersonneDto personneDto);

    ResponseEntity<?> searchBySigleIgnoreCase(String sigle);

    void supprimerPersonne(long idPersonne);
}
