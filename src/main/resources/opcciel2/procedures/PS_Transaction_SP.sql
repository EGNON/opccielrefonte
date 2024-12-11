USE [DB_OPCCIEL2]
GO

/****** Object:  StoredProcedure [Parametre].[PS_Transaction_SP]    Script Date: 25/04/2024 10:54:38 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO



-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE Procedure [Parametre].[PS_Transaction_SP](@IdCritereAlerte D_N_SEQ)

AS
BEGIN
	SET NOCOUNT ON

	declare @nbre D_N_SEQ
	declare @critere nvarchar(max)
	declare @route nvarchar(max)
	declare @tableRetour table (IdOperation D_N_SEQ,
			sigleOpcvm D_A_LIB,
			denominationOpcvm D_A_LIB,
			denomination D_A_LIB,
			codeNatureOperation D_A_LIB,
			montant D_N_DECIMALX,			
			QtePart D_N_DECIMALX,
			nomPays D_A_LIB
							)
	declare @sql nvarchar(max)
	
	select @sql=c.sql
	from Notification.T_CritereAlerte c
	where c.idCritereAlerte=@IdCritereAlerte
	
 
	insert @tableRetour
	exec Parametre.PS_Transaction @sql,null,null

	SELECT IdOperation,
			sigleOpcvm,
			denominationOpcvm,
			denomination,
			codeNatureOperation,
			montant,			
			QtePart,
			nomPays
    FROM @tableRetour
	order by denomination asc
	RETURN 
END
GO

