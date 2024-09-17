package com.ged.dao.titresciel;

import com.ged.dto.titresciel.cours.CoursTitreMajDto;
import com.ged.dto.titresciel.cours.DateCoursDto;
import com.ged.entity.titresciel.CleCoursTitre;
import com.ged.entity.titresciel.CoursTitre;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CoursTitreDao extends JpaRepository<CoursTitre, CleCoursTitre> {
    @Query(value = "SELECT DISTINCT new com.ged.dto.titresciel.cours.DateCoursDto(c.titre.place.codePlace, c.titre.place.libellePlace, c.idCoursTitre.dateCours) " +
            "FROM CoursTitre c WHERE LTRIM(RTRIM(c.titre.place.codePlace)) = :codePlace AND c.idCoursTitre.dateCours BETWEEN :start AND :end")
    Page<DateCoursDto> findByDateCoursAndPlace(
            @Param("codePlace") String codePlace,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end, Pageable pageable);

   /*@Query(value = "SELECT DISTINCT new com.ged.dto.titresciel.cours.CoursTitreMajDto(" +
           "t.idTitre, t.symbolTitre, t.designationTitre, t.place.codePlace, " +
           "t.place.libellePlace, c.idCoursTitre.dateCours, " +
           "c.coursVeille, c.ouverture, c.haut, c.bas, c.coursSeance, c.variation, c.nbreTrans, " +
           "c.volTransiger, c.valTransiger, c.resteOffre, c.resteDemande, c.coursReference, c.estValider) " +
           "FROM Titre t LEFT JOIN t.cours c ON CAST(COALESCE(c.idCoursTitre.dateCours, :start) AS LocalDate) = CAST(:start AS LocalDate)" +
           "WHERE LTRIM(RTRIM(t.place.codePlace)) = :codePlace AND t.supprimer = false")
    List<CoursTitreMajDto> findByDateCoursAndPlaceMaj(
            @Param("codePlace") String codePlace,
            @Param("start") LocalDateTime start);*/

    @Query(value = "SELECT DISTINCT new com.ged.dto.titresciel.cours.CoursTitreMajDto(" +
            "t.idTitre, t.symbolTitre, t.designationTitre, t.place.codePlace, " +
            "t.place.libellePlace, c.idCoursTitre.dateCours, " +
            "c.coursVeille, c.ouverture, c.haut, c.bas, c.coursSeance, c.variation, c.nbreTrans, " +
            "c.volTransiger, c.valTransiger, c.resteOffre, c.resteDemande, c.coursReference, c.estValider) " +
            "FROM Titre t INNER JOIN t.cours c ON CAST(COALESCE(c.idCoursTitre.dateCours, :start) AS LocalDate) = CAST(:start AS LocalDate)" +
            "WHERE LTRIM(RTRIM(t.place.codePlace)) = :codePlace AND t.supprimer = false ORDER BY t.symbolTitre DESC")
    List<CoursTitreMajDto> findByDateCoursAndPlaceMaj(
            @Param("codePlace") String codePlace,
            @Param("start") LocalDateTime start);

    @Query(value = "SELECT DISTINCT new com.ged.dto.titresciel.cours.CoursTitreMajDto(" +
            "t.idTitre, t.symbolTitre, t.designationTitre, t.place.codePlace, " +
            "t.place.libellePlace, cast(:start as localdatetime), coalesce(cast(0 as bigdecimal), 0), coalesce(cast(0 as bigdecimal), 0), " +
            "coalesce(cast(0 as bigdecimal), 0), coalesce(cast(0 as bigdecimal), 0), coalesce(cast(0 as bigdecimal), 0), " +
            "coalesce(cast(0 as bigdecimal), 0), coalesce(cast(0 as integer), 0), coalesce(cast(0 as integer), 0), " +
            "coalesce(cast(0 as bigdecimal), 0), coalesce(cast(0 as integer), 0), coalesce(cast(0 as integer), 0), " +
            "coalesce(cast(0 as bigdecimal), 0), false) FROM Titre t WHERE LTRIM(RTRIM(t.place.codePlace)) = :codePlace AND t.supprimer = false " +
            "ORDER BY t.symbolTitre DESC")
    List<CoursTitreMajDto> findByDateCoursAndPlaceMaj1(@Param("codePlace") String codePlace,
                                                       @Param("start") LocalDateTime start);

    @Query(value = "SELECT MAX(c.coursSeance) FROM CoursTitre c WHERE c.supprimer = false AND c.idCoursTitre.dateCours = " +
            "(SELECT MAX(c1.idCoursTitre.dateCours) FROM CoursTitre c1 WHERE c1.supprimer = false) AND c.idCoursTitre.idTitre = :idTitre")
    Optional<Long> lastCoursTitre(@Param("idTitre") Long idTitre);

    @Modifying(flushAutomatically = true)
    @Query(value = "DELETE FROM CoursTitre c WHERE CAST(c.idCoursTitre.dateCours AS LocalDate) = CAST(:dateCours AS LocalDate) " +
            "AND c.titre.place.codePlace = :codePlace")
    void deleteAllCoursTitreByDate(@Param("codePlace") String codePlace, @Param("dateCours") LocalDateTime dateCours);
}
