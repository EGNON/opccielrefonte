USE [DB_OPCCIEL2]
GO

/****** Object:  StoredProcedure [Impression].[PS_FrequenceOperation_SP]    Script Date: 18/04/2024 10:23:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [Impression].[PS_FrequenceOperation_SP](@annee D_A_LIB)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	declare @tableRetour table (--nbre D_N_SEQ,
							montantDepose D_N_DECIMALX,
							IdActionnaire D_N_SEQ,
							nomPersonnePhysique D_A_LIB,
							prenomPersonnePhysique D_A_LIB,
							denomination D_A_LIB
							)
	insert @tableRetour
	SELECT --count(dr.montant) as nbre,
        dr.montant as montanDepose,
        dr.IdActionnaire,
        ph.nomPersonnePhysique,
        ph.prenomPersonnePhysique ,
        (ph.nomPersonnePhysique+' '+ph.prenomPersonnePhysique ) as denomination 
        FROM DB_OPCCIEL.Parametre.T_DepotRachat dr 
        INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=dr.idActionnaire 
        INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=dr.idOpcvm 
        where dr.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
        and dr.codeNatureOperation='DEP_SOUS' 
        --group by 
        --dr.IdActionnaire, ph.nomPersonnePhysique,
        --ph.prenomPersonnePhysique,dr.montant  
        --order by denomination asc,nbre desc
	union all
   SELECT --count(op.montantDepose) as nbre,
        op.montantDepose,
        op.IdActionnaire,
        ph.nomPersonnePhysique,
        ph.prenomPersonnePhysique ,
        (ph.nomPersonnePhysique+' '+ph.prenomPersonnePhysique ) as denomination 
        FROM DB_OPCCIEL.Operation.T_OperationSouscriptionRachat op 
        INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=op.idActionnaire 
        INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=op.idOpcvm 
        where op.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
        and op.codeNatureOperation='SOUS_PART' 
        --group by 
        --op.IdActionnaire, ph.nomPersonnePhysique,
        --ph.prenomPersonnePhysique,op.montantDepose  
        --order by denomination asc,nbre desc

	select  count(montantDepose) as nbre,
        montantDepose,
        IdActionnaire,
        nomPersonnePhysique,
        prenomPersonnePhysique ,
        denomination 
    FROM @tableRetour 
	group by 
        IdActionnaire, nomPersonnePhysique,
        prenomPersonnePhysique,denomination,montantDepose  
	order by denomination asc,nbre desc,montantDepose asc
END
GO

