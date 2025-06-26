package com.ged.dao.titresciel;

import com.ged.entity.titresciel.Titre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface TitreDao extends JpaRepository<Titre,Long>, JpaSpecificationExecutor<Titre> {
    Optional<Titre> findByIdOcc(Long id);
    List<Titre> findBySupprimerOrderBySymbolTitreAsc(boolean supprimer);
    Optional<Titre> findByTypeVMAndIdOcc(String type, Long id);
    Optional<Titre> findBySymbolTitreIgnoreCase(String symbolTitre);
    @Query(value = "select ph from Titre as ph inner join StatutTitre as sp " +
            "on sp.idStatutTitre.idTitre = ph.idTitre inner join QualiteTitre as q " +
            "on q.idQualite = sp.qualiteTitre.idQualite where q.libelleQualite = :qualite " +
            "and (ph.symbolTitre like %:valeur% " +
            "and ph.designationTitre like %:valeur% " +
            "or ph.typeEmission.libelleTypeEmission like %:valeur% " +
            "or ph.secteur.libelleSecteur like %:valeur% " +
            "or ph.typeTitre.libelleTypeTitre like %:valeur%) " +
            "order by ph.symbolTitre asc, ph.designationTitre asc")
    Page<Titre> rechercherSelonQualite(@Param("qualite") String qualite, @Param("valeur") String valeur, Pageable pageable);
    @Query(value = "select ph from Titre as ph inner join StatutTitre as sp " +
            "on sp.idStatutTitre.idTitre = ph.idTitre inner join Qualite as q " +
            "on q.idQualite = sp.idStatutTitre.idQualite where q.libelleQualite = :qualite " +
            "order by ph.symbolTitre asc, ph.designationTitre asc")
    Page<Titre> afficherTousSelonQualite(@Param("qualite") String qualite, Pageable pageable);
    @Query(value = "select t from Titre as t " +
            " where t.typeTitre.codeTypeTitre = :code " +
            "order by t.symbolTitre asc, t.designationTitre asc")
    List<Titre> afficherSelonTypeTitre(String code);
}
