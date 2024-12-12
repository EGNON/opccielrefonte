USE [DB_OPCCIEL2]
GO

/****** Object:  StoredProcedure [Impression].[PS_DepotSurAnnee_SP]    Script Date: 18/04/2024 10:23:05 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [Impression].[PS_DepotSurAnnee_SP](@annee D_A_LIB)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	--declare @tableRetour table(IdOperation D_N_SEQ,
 --               codeNatureOperation D_A_LIB,
 --               dateOperation D_T_DATEHEURE,
 --               montant D_N_DECIMALX,
 --               denominationOpcvm D_A_LIB,
 --               idOpcvm D_N_SEQ,
 --               idActionnaire D_N_SEQ,
 --               nomPersonnePhysique D_A_LIB,
 --               prenomPersonnePhysique D_A_LIB,
 --               denomination D_A_LIB
	--			)
		
	--	insert @tableRetour
		 SELECT d.IdOperation,
                d.codeNatureOperation,
                d.dateOperation,
                d.montant,
                o.denominationOpcvm,
                d.idOpcvm,
                d.idActionnaire,
                ph.nomPersonnePhysique,
                ph.prenomPersonnePhysique ,
                (ph.nomPersonnePhysique+' '+ph.prenomPersonnePhysique ) as denomination,
                (SELECT sum(d2.montant) 
                FROM DB_OPCCIEL.Parametre.T_DepotRachat AS d2 
                WHERE d2.idActionnaire = d.idactionnaire 
                and d2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and d2.codeNatureOperation='DEP_SOUS' ) AS Total
                FROM DB_OPCCIEL.Parametre.T_DepotRachat d 
                INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=d.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=d.idOpcvm                 
                where d.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and d.codeNatureOperation='DEP_SOUS'
                order by denomination asc,d.dateOperation asc

END
GO

