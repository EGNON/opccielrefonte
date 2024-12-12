USE [DB_OPCCIEL2]
GO
/****** Object:  StoredProcedure [Impression].[PS_SuiviClientSanctionne]    Script Date: 12/04/2024 14:42:38 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Romaric B. TOHOUNKPIN
-- Create date: 09/04/2024
-- Description:	Suivi de clients sanctionnés
-- =============================================
CREATE PROCEDURE [Impression].[PS_SuiviClientSanctionne]
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    SELECT p.IdPersonne,
           CASE WHEN p.typePersonne = 'PH' THEN 'PERSONNE PHYSIQUE' ELSE 'PERSONNE MORALE' END AS typePersonne,
           ISNULL(pp.NomPersonnePhysique + ' ' + pp.PrenomPersonnePhysique, pm.RaisonSociale) AS actionnaire,
           CASE WHEN pa.libelleFr IS NULL THEN 'AUCUN' ELSE pa.libelleFr END AS pays
    FROM [Parametre].[T_Personne] p
             LEFT OUTER JOIN [Parametre].[T_PersonnePhysique] pp
                             ON pp.IdPersonne = p.IdPersonne
             LEFT OUTER JOIN [Parametre].[T_PersonneMorale] pm
                             ON pm.IdPersonne = p.IdPersonne
             LEFT OUTER JOIN [Parametre].[T_Pays] pa
                             ON pa.idPays = p.idPaysResidence
    WHERE p.estSanctionNationale = 1

    --update [Parametre].[T_Personne]
    --SET idPaysResidence = pa.idPays
    --FROM [Parametre].[T_Personne] p
    --INNER JOIN [Parametre].[T_Pays] pa
    --	ON pa.codePays = p.CodePays
END

/*
	EXEC [Impression].[PS_SuiviClientSanctionne]
*/
