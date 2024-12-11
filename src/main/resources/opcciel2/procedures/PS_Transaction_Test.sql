USE [DB_OPCCIEL2]
GO

/****** Object:  StoredProcedure [Parametre].[PS_Transaction_Test]    Script Date: 25/04/2024 11:00:02 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE Procedure [Parametre].[PS_Transaction_Test]

AS
BEGIN
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
	declare @sql nvarchar(max),@description nvarchar(max),@IdCritereAlerte D_N_SEQ,
	@IdOperation D_N_SEQ,@IdPersonne D_N_SEQ,@DateAlerte D_T_DATEHEURE
	
	declare curseur_utilisateur cursor for
	select u.idPersonne
	from Parametre.T_Utilisateur u

	declare curseur_alerte cursor for
	select c.IdCritereAlerte,c.description,c.sql,c.dateAlerte
	from Notification.T_CritereAlerte c
	where c.etat='Actif'

	OPEN curseur_alerte
 
FETCH curseur_alerte INTO @IdCritereAlerte , @description, @sql,@DateAlerte
 
WHILE @@FETCH_STATUS = 0
BEGIN
	delete from @tableRetour
	insert @tableRetour
	exec Parametre.PS_Transaction @sql,null,null

	select @Nbre=count(tr.idOperation)
	from @tableRetour tr

	if(@Nbre>0)
		begin
			update Notification.T_CritereAlerte
			set etat='Inactif'
			where idCritereAlerte=@IdCritereAlerte

			select @route=c.route
			from Notification.T_CritereAlerte c
			where idCritereAlerte=@IdCritereAlerte

			OPEN curseur_utilisateur
 
			FETCH curseur_utilisateur INTO @IdPersonne
 
			WHILE @@FETCH_STATUS = 0
			BEGIN
				INSERT INTO [Notification].[T_MessageBox]
				   ([contenu]
				   ,[dateEnvoiMsg]
				   ,[objet]
				   ,[idPersonne]
				   )
			 VALUES
				   (@description+' <a href="#'+@route+'?critere='+cast(@IdCritereAlerte as varchar)+'">Consulter</a>'
				   ,getDate(),
				   'Point des transactions'
				   ,@IdPersonne)
				FETCH curseur_utilisateur INTO @IdPersonne
			end
			CLOSE curseur_utilisateur
			
		end
    FETCH curseur_alerte INTO @IdCritereAlerte , @description, @sql,@DateAlerte
END
DEALLOCATE curseur_utilisateur
CLOSE curseur_alerte
DEALLOCATE curseur_alerte

	RETURN 
END
GO

