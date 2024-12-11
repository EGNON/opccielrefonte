USE [DB_OPCCIEL2]
GO

/****** Object:  StoredProcedure [Parametre].[PS_Transaction]    Script Date: 25/04/2024 10:51:09 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE Procedure [Parametre].[PS_Transaction](
											@critere nvarchar(max),
											@dateDebut D_T_DATEHEURE,
											@dateFin D_T_DATEHEURE)

AS
BEGIN
	declare @sql nvarchar(MAx)='declare @tableRetour table (idOperation D_N_SEQ,
							sigleOpcvm D_A_LIB,
							denominationOpcvm D_A_LIB,
							denomination D_A_LIB,														
							montant D_N_DECIMALX,
							montantAliquider D_N_DECIMALX,
							QtePart D_N_DECIMALX,
							nomPays D_A_LIB,
							codeNatureOperation D_A_LIB,
							LibelleTypepersonne D_A_LIB,
							codeMonnaie D_A_LIB
							)
	insert @tableRetour
	select IdOperation,
			sigleOpcvm,
			denominationOpcvm,
			ISNULL(ph.NomPersonnePhysique+'' ''+ph.PrenomPersonnePhysique,pm.RaisonSociale) as denomination,
			montant,
			0 as montantAliquider,
			0 as QtePart,
			nomPays,
			codeNatureOperation,
			p.LibelleTypePersonne,
			m.codeMonnaie
	from DB_OPCCIEL.Parametre.T_DepotRachat dr
	inner join DB_OPCCIEL.Parametre.T_Personne p on p.IdPersonne=dr.IdActionnaire
	left outer join DB_OPCCIEL.Parametre.T_PersonnePhysique ph on ph.IdPersonne=p.IdPersonne
	left outer join DB_OPCCIEL.Parametre.T_PersonneMorale pm on pm.IdPersonne=p.IdPersonne
	inner join DB_TITRESCIEL.Titre.T_Pays py on py.codePays=p.CodePays
	inner join DB_TITRESCIEL.Titre.T_Monnaie m on m.codeMonnaie=py.codeMonnaie
	inner join DB_OPCCIEL.Parametre.T_Opcvm o on o.idOpcvm=dr.IdOpcvm
	union all
	select IdOperation,
			sigleOpcvm,
			denominationOpcvm,
			ISNULL(ph.NomPersonnePhysique+'' ''+ ph.PrenomPersonnePhysique,pm.RaisonSociale) as denomination,
			montantDepose as montant,
			montantSousALiquider as montantAliquider,
			nombrePartSousRachat as QtePart,
			nomPays,
			codeNatureOperation,
			p.LibelleTypePersonne,
			m.codeMonnaie
	from DB_OPCCIEL.Operation.T_OperationSouscriptionRachat sr
	inner join DB_OPCCIEL.Parametre.T_Personne p on p.IdPersonne=sr.IdActionnaire
	left outer join DB_OPCCIEL.Parametre.T_PersonnePhysique ph on ph.IdPersonne=p.IdPersonne
	left outer join DB_OPCCIEL.Parametre.T_PersonneMorale pm on pm.IdPersonne=p.IdPersonne
	inner join DB_TITRESCIEL.Titre.T_Pays py on py.codePays=p.CodePays
	inner join DB_TITRESCIEL.Titre.T_Monnaie m on m.codeMonnaie=py.codeMonnaie
	inner join DB_OPCCIEL.Parametre.T_Opcvm o on o.idOpcvm=sr.IdOpcvm
	  
	SELECT IdOperation,
			sigleOpcvm,
			denominationOpcvm,
			denomination,
			codeNatureOperation,
			(case when LTRIM(rtrim(codeNatureOperation)) = ''RACH_PART'' then montantAliquider else montant end) as montant,			
			QtePart,
			nomPays
    FROM @tableRetour
    WHERE ' + @critere 

	EXEC(@sql)

	RETURN 
END
GO

