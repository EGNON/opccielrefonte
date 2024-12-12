USE [DB_OPCCIEL2]
GO
/****** Object:  StoredProcedure [Impression].[PS_RegistreConfidentiel]    Script Date: 05/07/2024 11:50:19 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Romaric B. TOHOUNKPIN
-- Create date: 09/04/2024
-- Description:	Suivi de clients sanctionnés
-- =============================================
ALTER PROCEDURE [Impression].[PS_RegistreConfidentiel]
(
    @dateDebut D_T_DATEHEURE,
    @dateFin D_T_DATEHEURE
)
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DECLARE @table TABLE (idOpcvm D_N_SEQ,
                          denominationOpcvm D_A_LIB,
                          idActionnaire D_N_SEQ,
                          personne NVARCHAR(150),
                          dateOperation D_T_DATEHEURE,
                          montantOp D_N_DECIMAL,
                          partOp D_N_DECIMAL,
                          valeurLiquidative D_N_DECIMAL,
                          prixSouscription D_N_DECIMAL,
                          titre D_A_LIB,
                          totalParFCP D_N_DECIMAL)

    INSERT @table
    SELECT opcvm.idOpcvm
         ,opcvm.denominationOpcvm
         ,opSousRach.idActionnaire
         ,ISNULL(NomPersonnePhysique +' '+PrenomPersonnePhysique,pm.SiglePersonneMorale+' '+pm.RaisonSociale) AS personne
         ,[dateOperation]
         ,CASE WHEN opSousRach.codeNatureOperation='SOUS_PART' THEN [montantDepose] ELSE [montantSousALiquider] END AS montant
         ,opSousRach.nombrePartSousRachat AS part
         ,so.valeurLiquidative
         ,so.valeurLiquidative + CASE WHEN opSousRach.codeNatureOperation='SOUS_PART' THEN (opSousRach.commisiionSousRachat/opSousRach.nombrePartSousRachat)
                                      ELSE - (opSousRach.commisiionSousRachat/opSousRach.nombrePartSousRachat) END AS prixSouscription
         ,CASE WHEN opSousRach.codeNatureOperation='SOUS_PART' THEN 'POINT DES SOUSCRIPTIONS' ELSE 'POINT DES RACHATS' END AS titre,
        0
    FROM [DB_OPCCIEL].[Operation].[T_OperationSouscriptionRachat] opSousRach
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_Personne] personne
                        ON personne.IdPersonne=opSousRach.IdActionnaire
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonnePhysique] ph
                             ON ph.IdPersonne = personne.IdPersonne
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonneMorale] pm
                             ON pm.IdPersonne = personne.IdPersonne
             INNER JOIN [DB_OPCCIEL].[Parametre].[TJ_SeanceOpcvm] so
                        ON so.idOpcvm=opSousRach.IdOpcvm AND so.idSeance=opSousRach.IdSeance
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_Opcvm] opcvm
                        ON opcvm.idOpcvm=opSousRach.IdOpcvm
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_PersonneMorale] distributeur
                        ON distributeur.IdPersonne=opSousRach.idPersonne
    WHERE opSousRach.supprimer=0
      AND so.supprimer=0
      AND opcvm.supprimer=0
      AND distributeur.supprimer=0
      AND CAST(opSousRach.dateOperation AS DATE) >= CAST(@dateDebut AS DATE)
      AND CAST(opSousRach.dateOperation AS DATE) <= CAST(@dateFin AS DATE)
      AND personne.LibelleTypePersonne = 'PH'
      AND opSousRach.codeNatureOperation = 'SOUS_PART'
      AND (opSousRach.montantDepose >= 50000000 OR opSousRach.montantDepose >= 10000000 OR opSousRach.montantDepose >= 15000000)

    UNION ALL

    SELECT opcvm.idOpcvm
         ,opcvm.denominationOpcvm
         ,opSousRach.[idActionnaire]
         ,ISNULL(NomPersonnePhysique +' '+PrenomPersonnePhysique,pm.SiglePersonneMorale+' '+pm.RaisonSociale) as personne
         ,opSousRach.[dateOperation]
         ,opSousRach.[montant]
         ,[part]
         ,so.valeurLiquidative
         , so.valeurLiquidative as prixSouscription
         ,'POINT DES SOUSCRIPTIONS' as titre,
        0
    FROM [DB_OPCCIEL].[Operation].[T_OperationConstitutionCapital] opSousRach
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_Personne] personne
                        ON personne.IdPersonne=opSousRach.IdActionnaire
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonnePhysique] ph
                             ON ph.IdPersonne = personne.IdPersonne
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonneMorale] pm
                             ON pm.IdPersonne = personne.IdPersonne
             INNER JOIN [DB_OPCCIEL].[Parametre].[TJ_SeanceOpcvm] so
                        ON so.idOpcvm=opSousRach.IdOpcvm AND so.idSeance=opSousRach.IdSeance
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_Opcvm] opcvm
                        ON opcvm.idOpcvm=opSousRach.IdOpcvm
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_DepotRachat] DepotRachat
                        ON DepotRachat.IdActionnaire=opSousRach.idActionnaire
                            AND DepotRachat.IdSeance=0 AND DepotRachat.IdOpcvm=so.idOpcvm
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_PersonneMorale] distributeur
                        ON distributeur.IdPersonne=DepotRachat.IdPersonne
    WHERE opSousRach.supprimer=0
      AND so.supprimer=0
      AND opcvm.supprimer=0
      AND distributeur.supprimer=0
      AND CAST(opSousRach.dateOperation AS DATE) >= CAST(@dateDebut AS DATE)
      AND CAST(opSousRach.dateOperation AS DATE) <= CAST(@dateFin AS DATE)
      AND opSousRach.montant!=0
      AND personne.LibelleTypePersonne = 'PH'
      AND (opSousRach.montant >= 50000000 OR opSousRach.montant >= 10000000 OR opSousRach.montant >= 15000000)

    UPDATE @table
    SET totalParFCP = t2.mt
    FROM @table t1
             INNER JOIN (
        SELECT idOpcvm, titre, SUM(montantOp) AS mt
        FROM @table
        GROUP BY idOpcvm, titre
    ) t2 ON t2.idOpcvm = t1.idOpcvm AND t2.titre = t1.titre

    SELECT *
    FROM @table
END

/*
EXEC [Impression].[PS_RegistreConfidentiel] '01/03/2024', '09/04/2024'
*/
