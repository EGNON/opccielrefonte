package com.ged.dao.standard;

public interface TarificationOrdinaireDao {
//public interface TarificationOrdinaireDao extends JpaRepository<TarificationOrdinaire,Long> {
    /*Page<TarificationOrdinaire> findByOpcvmAndRegistraireIsNotNull(Opcvm opcvm, Pageable pageable);
    Page<TarificationOrdinaire> findByOpcvmAndDepositaireIsNotNull(Opcvm opcvm, Pageable pageable);
    Page<TarificationOrdinaire> findByOpcvmAndPlaceIsNotNull(Opcvm opcvm, Pageable pageable);
    @Query(value = "select t.idTarificationOrdinaire as idTarificationOrdinaire, " +
                    "t.registraire as registraire," +
                    "t.borneInferieur as borneInferieur," +
                    "t.borneSuperieur as borneSuperieur," +
                    "t.classeTitre as classeTitre," +
                    "t.codeRole as codeRole," +
                    "t.forfait as forfait," +
                    "t.taux as taux," +
                    "t.opcvm as opcvm "+
                    "from TarificationOrdinaire t " +
                    "where t.idTarificationOrdinaire=:id")
    TarificationProjection rechercherRegistraireSelonId(Long id);
    @Modifying()
    @Query(value = "update TarificationOrdinaire t set  " +
                    "t.registraire.idPersonne=:idPersonne," +
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
                    "t.depositaire as depositaire," +
                    "t.borneInferieur as borneInferieur," +
                    "t.borneSuperieur as borneSuperieur," +
                    "t.classeTitre as classeTitre," +
                    "t.codeRole as codeRole," +
                    "t.forfait as forfait," +
                    "t.taux as taux," +
                    "t.opcvm as opcvm "+
                    "from TarificationOrdinaire t " +
                    "where t.idTarificationOrdinaire=:id")
    TarificationProjection rechercherDeposiatireSelonId(Long id);
    @Modifying()
    @Query(value = "update TarificationOrdinaire t set  " +
                    "t.depositaire.idPersonne=:idPersonne," +
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
                    "t.place as place," +
                    "t.borneInferieur as borneInferieur," +
                    "t.borneSuperieur as borneSuperieur," +
                    "t.classeTitre as classeTitre," +
                    "t.codeRole as codeRole," +
                    "t.forfait as forfait," +
                    "t.taux as taux," +
                    "t.opcvm as opcvm "+
                    "from TarificationOrdinaire t " +
                    "where t.idTarificationOrdinaire=:id")
    TarificationProjection rechercherPlaceSelonId(Long id);
    @Modifying()
    @Query(value = "update TarificationOrdinaire t set  " +
                    "t.place.codePlace=:codePlace," +
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
    void supprimerPlace(Long id);*/
}
