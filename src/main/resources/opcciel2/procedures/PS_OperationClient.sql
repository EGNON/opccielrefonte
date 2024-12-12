USE [DB_OPCCIEL2]
GO
/****** Object:  StoredProcedure [Impression].[PS_OperationClient]    Script Date: 12/04/2024 14:40:36 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Romaric B. TOHOUNKPIN
-- Create date: 05/04/2024
-- Description:	Récupérer les transactions effectuées par un même
--				client qu'il soit accasionnel ou habituel
-- =============================================
ALTER PROCEDURE [Impression].[PS_OperationClient]
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DECLARE @table TABLE(
                            idActionnaire D_N_SEQ,
                            typeOperation NVARCHAR(100),
                            dateOperation D_T_DATEHEURE,
                            actionnaire NVARCHAR(MAX),
                            idOpcvm D_N_SEQ,
                            montant D_N_DECIMAL,
                            transfere D_N_DECIMAL
                        )

    -- Insert statements for procedure here
    INSERT @table
    SELECT [idActionnaire],
           CASE WHEN [codeNatureOperation] = 'SOUS_PART' OR [codeNatureOperation] = 'DEP_SOUS' OR [codeNatureOperation] = 'DEP_CAPIT'
                    THEN 'SOUSCRIPTION'
                WHEN [codeNatureOperation] = 'RACH_PART' THEN 'RACHAT'
                ELSE
                    'OPERATION'
               END AS typeOperation,
           [dateOperation],
           ISNULL(pp.NomPersonnePhysique + ' ' + pp.PrenomPersonnePhysique, pm.RaisonSociale + ' [' + pm.SiglePersonneMorale + ']') AS actionnaire,
           [idOpcvm],
           (CASE WHEN codeNatureOperation='SOUS_PART' THEN [montantDepose] ELSE [montantSousALiquider] END) AS montant,
           0
    FROM [DB_OPCCIEL].[Operation].[T_OperationSouscriptionRachat] osr
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_Personne] p
                        ON p.IdPersonne = osr.idActionnaire
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonnePhysique] pp
                             ON pp.IdPersonne = p.IdPersonne
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonneMorale] pm
                             ON pm.IdPersonne = p.IdPersonne
    WHERE osr.supprimer = 0

    UNION

    SELECT [idActionnaire],
           CASE WHEN [codeNatureOperation] = 'SOUS_PART' OR [codeNatureOperation] = 'DEP_SOUS' OR [codeNatureOperation] = 'DEP_CAPIT'
                    THEN 'SOUSCRIPTION'
                WHEN [codeNatureOperation] = 'RACH_PART' THEN 'RACHAT'
                ELSE
                    'OPERATION'
               END AS typeOperation,
           [dateOperation],
           ISNULL(pp.NomPersonnePhysique + ' ' + pp.PrenomPersonnePhysique, pm.RaisonSociale + ' [' + pm.SiglePersonneMorale + ']') AS actionnaire,
           [idOpcvm],
           [montant],
           0
    FROM [DB_OPCCIEL].[Operation].[T_OperationConstitutionCapital] occ
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_Personne] p
                        ON p.IdPersonne = occ.idActionnaire
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonnePhysique] pp
                             ON pp.IdPersonne = p.IdPersonne
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonneMorale] pm
                             ON pm.IdPersonne = p.IdPersonne
    WHERE occ.supprimer = 0

    UNION

    SELECT [idActionnaire],
           CASE WHEN [codeNatureOperation] = 'SOUS_PART' OR [codeNatureOperation] = 'DEP_SOUS' OR [codeNatureOperation] = 'DEP_CAPIT'
                    THEN 'SOUSCRIPTION'
                WHEN [codeNatureOperation] = 'RACH_PART' THEN 'RACHAT'
                ELSE
                    'OPERATION'
               END AS typeOperation,
           [dateOperation],
           ISNULL(pp.NomPersonnePhysique + ' ' + pp.PrenomPersonnePhysique, pm.RaisonSociale + ' [' + pm.SiglePersonneMorale + ']') AS actionnaire,
           [idOpcvm],
           [montant],
           0
    FROM [DB_OPCCIEL].[Parametre].[T_DepotRachat] dr
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_Personne] p
                        ON p.IdPersonne = dr.idActionnaire
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonnePhysique] pp
                             ON pp.IdPersonne = p.IdPersonne
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonneMorale] pm
                             ON pm.IdPersonne = p.IdPersonne
    WHERE dr.supprimer = 0 AND estGenere = 0

    UNION

    SELECT [idDemandeur] AS idActionnaire,
           'TRANSFERT DE PART' AS typeOperation,
           [dateOperation],
           ISNULL(pp.NomPersonnePhysique + ' ' + pp.PrenomPersonnePhysique, pm.RaisonSociale + ' [' + pm.SiglePersonneMorale + ']') AS actionnaire,
           [idOpcvm],
           0 as montant,
           qteTransfert AS transfere
    FROM [DB_OPCCIEL].[Operation].[T_OperationTransfertDePart] otp
             INNER JOIN [DB_OPCCIEL].[Parametre].[T_Personne] p
                        ON p.IdPersonne = otp.idDemandeur
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonnePhysique] pp
                             ON pp.IdPersonne = p.IdPersonne
             LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonneMorale] pm
                             ON pm.IdPersonne = p.IdPersonne
    WHERE otp.supprimer = 0

    SELECT idActionnaire, typeOperation, dateOperation, actionnaire, idOpcvm, montant, transfere
    FROM @table
    ORDER BY idActionnaire ASC
END

/*
	EXEC [Impression].[PS_OperationClient]
*/