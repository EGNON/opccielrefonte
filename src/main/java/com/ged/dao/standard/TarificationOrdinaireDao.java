package com.ged.dao.standard;

import com.ged.entity.opcciel.Opcvm;
import com.ged.entity.standard.TarificationOrdinaire;
import com.ged.projection.TarificationProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

//public interface TarificationOrdinaireDao {
public interface TarificationOrdinaireDao extends JpaRepository<TarificationOrdinaire,Long> {
    @Query(value = "select t.idTarificationOrdinaire as idTarificationOrdinaire, " +
            "r as registraire," +
            "t.borneInferieur as borneInferieur," +
            "t.borneSuperieur as borneSuperieur," +
            "t.classeTitre as classeTitre," +
            "t.codeRole as codeRole," +
            "t.forfait as forfait," +
            "t.taux as taux," +
            "t.opcvm as opcvm "+
            "from TarificationOrdinaire t " +
            "inner join Registraire r on r.idRegistraire=t.idRegistraire  " +
            "where t.opcvm.idOpcvm=:id")
    Page<TarificationProjection> afficherRegistraire(Long id,Pageable pageable);
    //Page<TarificationOrdinaire> findByOpcvmAndIdRegistraireIsNotNull(Opcvm opcvm, Pageable pageable);
    @Query(value = "select t.idTarificationOrdinaire as idTarificationOrdinaire, " +
            "d as depositaire," +
            "t.borneInferieur as borneInferieur," +
            "t.borneSuperieur as borneSuperieur," +
            "t.classeTitre as classeTitre," +
            "t.codeRole as codeRole," +
            "t.forfait as forfait," +
            "t.taux as taux," +
            "t.opcvm as opcvm "+
            "from TarificationOrdinaire t " +
            "inner join Depositaire d on d.idDepositaire=t.idDepositaire  " +
            "where t.opcvm.idOpcvm=:id")
    Page<TarificationProjection> afficherDepositaire(Long id,Pageable pageable);
    //Page<TarificationOrdinaire> findByOpcvmAndIdDepositaireIsNotNull(Opcvm opcvm, Pageable pageable);
    @Query(value = "select t.idTarificationOrdinaire as idTarificationOrdinaire, " +
            "p as place," +
            "t.borneInferieur as borneInferieur," +
            "t.borneSuperieur as borneSuperieur," +
            "t.classeTitre as classeTitre," +
            "t.codeRole as codeRole," +
            "t.forfait as forfait," +
            "t.taux as taux," +
            "t.opcvm as opcvm "+
            "from TarificationOrdinaire t " +
            "inner join Place p on p.codePlace=t.codePlace " +
            "where t.opcvm.idOpcvm=:id")
    Page<TarificationProjection> afficherPlace(Long id,Pageable pageable);

    //Page<TarificationOrdinaire> findByOpcvmAndCodePlaceIsNotNull(Opcvm opcvm, Pageable pageable);
    @Query(value = "select t.idTarificationOrdinaire as idTarificationOrdinaire, " +
                    "r as registraire," +
                    "t.borneInferieur as borneInferieur," +
                    "t.borneSuperieur as borneSuperieur," +
                    "t.classeTitre as classeTitre," +
                    "t.codeRole as codeRole," +
                    "t.forfait as forfait," +
                    "t.taux as taux," +
                    "t.opcvm as opcvm "+
                    "from TarificationOrdinaire t " +
                    "inner join Registraire r on r.idRegistraire=t.idRegistraire  " +
                    "where t.idTarificationOrdinaire=:id ")
    TarificationProjection rechercherRegistraireSelonId(Long id);
    @Modifying()
    @Query(value = "update TarificationOrdinaire t set  " +
                    "t.idRegistraire=:idPersonne," +
                    "t.borneInferieur=:borneInferieur," +
                    "t.borneSuperieur=:borneSuperieur," +
                    "t.classeTitre.codeClasseTitre=:codeClasseTitre," +
                    "t.codeRole=:codeRole," +
                    "t.forfait=:forfait," +
                    "t.taux=:taux," +
                    "t.opcvm.idOpcvm=:idOpcvm "+
                    "where t.idTarificationOrdinaire=:id")
    int modifierRegistraire(Long id,Long idPersonne,double borneInferieur,double borneSuperieur
    ,String codeClasseTitre,String codeRole,double forfait,double taux,Long idOpcvm);
    @Query(value = "select t.idTarificationOrdinaire as idTarificationOrdinaire, " +
                    "d as depositaire," +
                    "t.borneInferieur as borneInferieur," +
                    "t.borneSuperieur as borneSuperieur," +
                    "t.classeTitre as classeTitre," +
                    "t.codeRole as codeRole," +
                    "t.forfait as forfait," +
                    "t.taux as taux," +
                    "t.opcvm as opcvm "+
                    "from TarificationOrdinaire t " +
                    "inner join Depositaire d on d.idDepositaire=t.idDepositaire " +
                    "where t.idTarificationOrdinaire=:id")
    TarificationProjection rechercherDeposiatireSelonId(Long id);
    @Modifying()
    @Query(value = "update TarificationOrdinaire t set  " +
                    "t.idDepositaire=:idPersonne," +
                    "t.borneInferieur=:borneInferieur," +
                    "t.borneSuperieur=:borneSuperieur," +
                    "t.classeTitre.codeClasseTitre=:codeClasseTitre," +
                    "t.codeRole=:codeRole," +
                    "t.forfait=:forfait," +
                    "t.taux=:taux," +
                    "t.opcvm.idOpcvm=:idOpcvm "+
                    "where t.idTarificationOrdinaire=:id")
    int modifierDepositaire(Long id,Long idPersonne,double borneInferieur,double borneSuperieur
    ,String codeClasseTitre,String codeRole,double forfait,double taux,Long idOpcvm);
    @Query(value = "select t.idTarificationOrdinaire as idTarificationOrdinaire, " +
                    "p as place," +
                    "t.borneInferieur as borneInferieur," +
                    "t.borneSuperieur as borneSuperieur," +
                    "t.classeTitre as classeTitre," +
                    "t.codeRole as codeRole," +
                    "t.forfait as forfait," +
                    "t.taux as taux," +
                    "t.opcvm as opcvm "+
                    "from TarificationOrdinaire t " +
                    "inner join Place p on p.codePlace=t.codePlace " +
                    "where t.idTarificationOrdinaire=:id")
    TarificationProjection rechercherPlaceSelonId(Long id);
    @Modifying()
    @Query(value = "update TarificationOrdinaire t set  " +
                    "t.codePlace=:codePlace," +
                    "t.borneInferieur=:borneInferieur," +
                    "t.borneSuperieur=:borneSuperieur," +
                    "t.classeTitre.codeClasseTitre=:codeClasseTitre," +
                    "t.codeRole=:codeRole," +
                    "t.forfait=:forfait," +
                    "t.taux=:taux," +
                    "t.opcvm.idOpcvm=:idOpcvm "+
                    "where t.idTarificationOrdinaire=:id")
    int modifierPlace(Long id,String codePlace,double borneInferieur,double borneSuperieur
    ,String codeClasseTitre,String codeRole,double forfait,double taux,Long idOpcvm);

    @Modifying()
    @Query(value = "delete from TarificationOrdinaire t "+
                    "where t.idTarificationOrdinaire=:id")
    void supprimerPlace(Long id);
}
