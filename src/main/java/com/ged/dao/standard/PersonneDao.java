package com.ged.dao.standard;

import com.ged.entity.standard.Personne;
import com.ged.projection.PersonneProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PersonneDao extends JpaRepository<Personne, Long> {
    boolean existsByNumeroCpteDeposit(String numero);
    List<Personne> findByNumeroCpteDeposit(String numero);
    @Query(value = "SELECT  p.idPersonne as idPersonne, " +
            "p.denomination AS denomination," +
            "p.ifu as ifu,p.mobile1 as mobile1,p.mobile2 as mobile2,p.fixe1 as fixe1," +
            "p.fixe2 as fixe2,p.bp as bp,p.emailPerso as emailPerso" +
            ",p.emailPro as emailPro,p.domicile as domicile,p.numeroPiece as numeroPiece," +
            "p.typePiece as typePiece,p.dateExpirationPiece as dateExpirationPiece" +
            ",p.modeEtablissement as modeEtablissement " +
            "from Personne as p " +
            "inner join StatutPersonne st on st.personne.idPersonne=p.idPersonne "+
            "inner join Qualite q on q.idQualite=st.qualite.idQualite "+
            "where (q.libelleQualite='actionnaires' or q.libelleQualite='prospect') " +
            "and ((p.emailPerso is not null) and(p.emailPerso!='')) "+
            "order by p.denomination asc")
    List<PersonneProjection> afficherPersonnePhysiqueMorale();
    @Query(value = "SELECT  p.idPersonne as idPersonne, " +
            "p.denomination AS denomination," +
            "p.ifu as ifu,p.mobile1 as mobile1,p.mobile2 as mobile2,p.fixe1 as fixe1," +
            "p.fixe2 as fixe2,p.bp as bp,p.emailPerso as emailPerso" +
            ",p.emailPro as emailPro,p.domicile as domicile,p.numeroPiece as numeroPiece," +
            "p.typePiece as typePiece,p.dateExpirationPiece as dateExpirationPiece" +
            ",p.modeEtablissement as modeEtablissement " +
            "from Personne as p " +
            "inner join StatutPersonne st on st.personne.idPersonne=p.idPersonne "+
            "inner join Qualite q on q.idQualite=st.qualite.idQualite "+
            "where (q.libelleQualite='actionnaires' or q.libelleQualite='prospect') " +
            "order by p.denomination asc")
    List<PersonneProjection> afficherPersonnePhysiqueMoraleListe();
    @Query(value = "SELECT  p "+
            "from Personne as p " +
            "where p.idPersonne=:idPersonne")
    Personne afficherPersonneSelonId(Long idPersonne);
    @Query(value = "SELECT distinct p.idPersonne as idPersonne, " +
            "COALESCE(pm.raisonSociale, CONCAT(pp.nom, ' ', pp.prenom)) AS denomination," +
            "p.ifu as ifu,p.mobile1 as mobile1,p.mobile2 as mobile2,p.fixe1 as fixe1," +
            "p.fixe2 as fixe2,p.bp as bp,p.emailPerso as emailPerso" +
            ",p.emailPro as emailPro,p.domicile as domicile,p.numeroPiece as numeroPiece," +
            "p.typePiece as typePiece,p.dateExpirationPiece as dateExpirationPiece" +
            ",p.modeEtablissement as modeEtablissement " +
            "from Personne as p " +
            "left outer join PersonnePhysique pp ON p.idPersonne=pp.idPersonne " +
            "left outer join PersonneMorale pm on p.idPersonne=pm.idPersonne " +
            "inner join StatutPersonne st on st.personne.idPersonne=p.idPersonne "+
            "inner join Qualite q on q.idQualite=st.qualite.idQualite "+
            "where q.libelleQualite='ACTIONNAIRES' and p.idPersonne not in(" +
            " select a.personne.idPersonne from ActionnaireOpcvm a" +
            " where a.opcvm.idOpcvm=:idOpcvm) "+
            "order by COALESCE(pm.raisonSociale, CONCAT(pp.nom, ' ', pp.prenom)) asc")
    List<PersonneProjection> afficherPersonne(Long idOpcvm);
    @Query(value = "SELECT distinct p.idPersonne as idPersonne, " +
            "COALESCE(pm.raisonSociale, CONCAT(pp.nom, ' ', pp.prenom)) AS denomination," +
            "p.ifu as ifu,p.mobile1 as mobile1,p.mobile2 as mobile2,p.fixe1 as fixe1," +
            "p.fixe2 as fixe2,p.bp as bp,p.emailPerso as emailPerso" +
            ",p.emailPro as emailPro,p.domicile as domicile,p.numeroPiece as numeroPiece," +
            "p.typePiece as typePiece,p.dateExpirationPiece as dateExpirationPiece" +
            ",p.modeEtablissement as modeEtablissement " +
            "from Personne as p " +
            "left outer join PersonnePhysique pp ON p.idPersonne=pp.idPersonne " +
            "left outer join PersonneMorale pm on p.idPersonne=pm.idPersonne " +
            "inner join StatutPersonne st on st.personne.idPersonne=p.idPersonne "+
            "inner join Qualite q on q.idQualite=st.qualite.idQualite "+
            "where q.libelleQualite='ACTIONNAIRES' and p.idPersonne  in(" +
            " select a.personne.idPersonne from ActionnaireOpcvm a" +
            " where a.opcvm.idOpcvm=:idOpcvm) "+
            "order by COALESCE(pm.raisonSociale, CONCAT(pp.nom, ' ', pp.prenom)) asc")
    List<PersonneProjection> afficherPersonneInOpcvm(Long idOpcvm);

    @Query(value = "SELECT distinct p.idPersonne as idPersonne, " +
            "COALESCE(pm.raisonSociale, CONCAT(pp.nom, ' ', pp.prenom)) AS denomination," +
            "p.ifu as ifu,p.mobile1 as mobile1,p.mobile2 as mobile2,p.fixe1 as fixe1," +
            "p.fixe2 as fixe2,p.bp as bp,p.emailPerso as emailPerso" +
            ",p.emailPro as emailPro,p.domicile as domicile,p.numeroPiece as numeroPiece," +
            "p.typePiece as typePiece,p.dateExpirationPiece as dateExpirationPiece" +
            ",p.modeEtablissement as modeEtablissement " +
            "from Personne as p " +
            "left outer join PersonnePhysique pp ON p.idPersonne=pp.idPersonne " +
            "left outer join PersonneMorale pm on p.idPersonne=pm.idPersonne " +
            "inner join StatutPersonne st on st.personne.idPersonne=p.idPersonne "+
            "inner join Qualite q on q.idQualite=st.qualite.idQualite "+
            "where q.libelleQualite='ACTIONNAIRES' and p.idPersonne  in(" +
            " select a.personne.idPersonne from ActionnaireOpcvm a" +
            " where a.opcvm.idOpcvm=:idOpcvm ) and p.statutCompte='OUVERT' "+
            "order by COALESCE(pm.raisonSociale, CONCAT(pp.nom, ' ', pp.prenom)) asc")
    List<PersonneProjection> afficherPersonneInOpcvmEtStatutCompte(Long idOpcvm);

    @Query(value = "SELECT  p.idPersonne as idPersonne, " +
            "COALESCE(pm.raisonSociale, CONCAT(pp.nom, ' ', pp.prenom)) AS denomination," +
            "p.ifu as ifu,p.mobile1 as mobile1,p.mobile2 as mobile2,p.fixe1 as fixe1," +
            "p.fixe2 as fixe2,p.bp as bp,p.emailPerso as emailPerso" +
            ",p.emailPro as emailPro,p.domicile as domicile,p.numeroPiece as numeroPiece," +
            "p.typePiece as typePiece,p.dateExpirationPiece as dateExpirationPiece" +
            ",p.modeEtablissement as modeEtablissement " +
            "from Personne as p " +
            "left outer join PersonnePhysique pp ON p.idPersonne=pp.idPersonne " +
            "left outer join PersonneMorale pm on p.idPersonne=pm.idPersonne "+
            "where p.idPersonne=:id")
    PersonneProjection afficherPersonnePhysiqueMoraleSelonId(long id);
    @Query(value = "select p from Personne as p inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = p.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite where lower(q.libelleQualite) = lower(:qualite) " +
            "order by p.denomination asc")
    List<Personne> afficherTousSelonQualite(@Param("qualite") String qualite);
    @Query(value = "select p from Personne as p inner join StatutPersonne as sp " +
            "on sp.idStatutPersonne.idPersonne = p.idPersonne inner join Qualite as q " +
            "on q.idQualite = sp.idStatutPersonne.idQualite " +
            "where (q.libelleQualite = 'juge' or q.libelleQualite='expose') " +
            //"and (p.idPersonne not in( from GelDegel g where g.estGele=true)) " +
            "order by p.denomination asc")
    List<Personne> afficherTousSelonQualite();
    @Query(value = "select p from Personne as p  inner join GelDegel as g " +
            "on g.personne.idPersonne = p.idPersonne " +
            "where (g.estGele = true) " +
            //"and (p.idPersonne not in( from GelDegel g where g.estGele=true)) " +
            "order by p.denomination asc")
    Page<Personne> afficherCompteGele(Pageable pageable);
    @Query(value = "select p from Personne as p  inner join GelDegel as g " +
            "on g.personne.idPersonne = p.idPersonne " +
            "where  (p.idPersonne not in( from GelDegel g where g.estGele=true)) " +
            "order by p.denomination asc")
    Page<Personne> afficherCompteNonGele(Pageable pageable);
    @Query(value = "select p from Personne as p "+
            "where (p.estExpose=true or p.estJuge=true) " +
            "order by p.denomination asc")
    Page<Personne> afficherTousSelonQualite(Pageable pageable);
    @Query(value = "select p from Personne as p "+
            "where (p.estExpose=true or p.estJuge=true) " +
            "order by p.denomination asc")
    List<Personne> afficherTousSelonQualiteListe();
    @Query(value = "select p from Personne as p "+
            "where (p.estExpose=true or p.estJuge=true) " +
            "and p.denomination like %:search% " +
            "order by p.denomination asc")
    Page<Personne> rechercherGele(String search,Pageable pageable);
    @Query(value = "select p from Personne as p "+
            "where (p.estExpose=true or p.estJuge=true) " +
            "and p.estGele=:search " +
            "order by p.denomination asc")
    Page<Personne> compteEstGele(boolean search,Pageable pageable);
    @Query(value = "select p from Personne as p "+
            "where (p.estExpose=true or p.estJuge=true) " +
            "and p.estGele=:search " +
            "order by p.denomination asc")
    List<Personne> compteEstGeleListe(boolean search);
    Boolean existsByNumeroCpteDepositIgnoreCase(String numero);
    @Query(value = "select p from Personne p inner join PersonneMorale pm " +
            "on pm.idPersonne = p.idPersonne where lower(trim(pm.siglePersonneMorale)) = :sigle")
    Optional<Personne> searchBySigleIgnoreCase(@Param("sigle") String sigle);
}
