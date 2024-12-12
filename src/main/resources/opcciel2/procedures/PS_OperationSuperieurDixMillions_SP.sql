USE [DB_OPCCIEL2]
GO

/****** Object:  StoredProcedure [Impression].[PS_OperationSuperieurDixMillions_SP]    Script Date: 18/04/2024 10:24:53 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [Impression].[PS_OperationSuperieurDixMillions_SP](@annee D_A_LIB)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	declare @tableRetour table(IdOperation D_N_SEQ,
                codeNatureOperation D_A_LIB,
                dateOperation D_T_DATEHEURE,
                montantDepose D_N_DECIMALX,
                denominationOpcvm D_A_LIB,
                idOpcvm D_N_SEQ,
                idActionnaire D_N_SEQ,
                nomPersonnePhysique D_A_LIB,
                prenomPersonnePhysique D_A_LIB,
                denomination D_A_LIB,
                Total D_N_DECIMALX,
                datePremiereSouscription D_T_DATEHEURE)
	declare @IdOperation D_N_SEQ,
                @codeNatureOperation D_A_LIB,
                @dateOperation D_T_DATEHEURE,
                @montantDepose D_N_DECIMALX,
                @denominationOpcvm D_A_LIB,
                @idOpcvm D_N_SEQ,
                @idActionnaire D_N_SEQ,
                @nomPersonnePhysique D_A_LIB,
                @prenomPersonnePhysique D_A_LIB,
                @denomination D_A_LIB,
                @Total D_N_DECIMALX,
                @datePremiereSouscription D_T_DATEHEURE

	insert @tableRetour
	SELECT dr.IdOperation,
                dr.codeNatureOperation,
                dr.dateOperation,
                dr.montant as montantDepose,
                o.denominationOpcvm,
                dr.idOpcvm,
                dr.idActionnaire,
                ph.nomPersonnePhysique,
                ph.prenomPersonnePhysique ,
                (ph.nomPersonnePhysique+' '+ph.prenomPersonnePhysique ) as denomination,
                (SELECT sum(dr2.montant) 
                FROM DB_OPCCIEL.Parametre.T_DepotRachat AS dr2 
                WHERE dr2.idActionnaire = dr.idactionnaire 
                and dr2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and dr2.codeNatureOperation='DEP_SOUS' ) AS Total,
                (SELECT 
                min(dr2.dateOperation) as dateOperation2 
                FROM DB_OPCCIEL.Parametre.T_DepotRachat dr2 
                INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph2 ON ph2.idPersonne=dr2.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o2 ON o2.idOpcvm=dr2.idOpcvm 
                where dr2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and dr2.codeNatureOperation='DEP_SOUS' 
                and dr2.idOpcvm=dr.idopcvm 
                and dr2.idActionnaire=dr.idActionnaire 
                group by dr2.idActionnaire ) as datePremiereSouscription 
                FROM DB_OPCCIEL.Parametre.T_DepotRachat dr 
                iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=dr.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=dr.idOpcvm 
                where dr.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and dr.codeNatureOperation='DEP_SOUS' 
				and dr.estGenere=0
                group by dr.IdOperation,dr.codeNatureOperation,dr.dateOperation,dr.montant,
                o.denominationOpcvm,dr.idOpcvm,dr.idActionnaire, ph.nomPersonnePhysique,ph.prenomPersonnePhysique                
				union all
	SELECT op.IdOperation,
                op.codeNatureOperation,
                op.dateOperation,
                op.montantDepose,
                o.denominationOpcvm,
                op.idOpcvm,
                op.idActionnaire,
                ph.nomPersonnePhysique,
                ph.prenomPersonnePhysique ,
                (ph.nomPersonnePhysique+' '+ph.prenomPersonnePhysique ) as denomination,
                (SELECT sum(op2.montantDepose) 
                FROM DB_OPCCIEL.Operation.T_OperationSouscriptionRachat AS op2 
                WHERE Op2.idActionnaire = op.idactionnaire 
                and op2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and op2.codeNatureOperation='SOUS_PART' ) AS Total,
                (SELECT 
                min(op.dateOperation) as dateOperation2 
                FROM DB_OPCCIEL.Operation.T_OperationSouscriptionRachat op2 
                INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph2 ON ph2.idPersonne=op2.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o2 ON o2.idOpcvm=op2.idOpcvm 
                where op2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and op2.codeNatureOperation='SOUS_PART' 
                and op2.idOpcvm=op.idopcvm 
                and op2.idActionnaire=op.idActionnaire 
                group by op2.idActionnaire ) as datePremiereSouscription 
                FROM DB_OPCCIEL.Operation.T_OperationSouscriptionRachat op 
                iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=op.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=op.idOpcvm 
                where op.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and op.codeNatureOperation='SOUS_PART' 
                group by op.IdOperation,op.codeNatureOperation,op.dateOperation,op.montantDepose,
                o.denominationOpcvm,op.idOpcvm,op.idActionnaire, ph.nomPersonnePhysique,ph.prenomPersonnePhysique                 

	declare cursor_Operation cursor for
	select tr.IdOperation,
                tr.codeNatureOperation,
                tr.dateOperation,
                tr.montantDepose,
                tr.denominationOpcvm,
                tr.idOpcvm,
                tr.idActionnaire,
                tr.nomPersonnePhysique,
                tr.prenomPersonnePhysique ,
                tr.denomination,
                (SELECT sum(tr2.montantDepose) 
                FROM @tableRetour AS tr2 
                WHERE tr2.idActionnaire = tr.idactionnaire 
                and tr2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                 ) AS Total,
                (SELECT 
                min(tr2.dateOperation) as dateOperation2 
                FROM @tableRetour tr2 
                INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph2 ON ph2.idPersonne=tr2.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o2 ON o2.idOpcvm=tr2.idOpcvm 
                where tr2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and tr2.idOpcvm=tr.idopcvm 
                and tr2.idActionnaire=tr.idActionnaire 
                group by tr2.idActionnaire ) as datePremiereSouscription 
     FROM @tableRetour tr 

	 OPEN cursor_Operation
 
FETCH cursor_Operation INTO @IdOperation,
                @codeNatureOperation,
                @dateOperation,
                @montantDepose,
                @denominationOpcvm,
                @idOpcvm,
                @idActionnaire,
                @nomPersonnePhysique,
                @prenomPersonnePhysique,
                @denomination,
                @Total,
                @datePremiereSouscription
 
delete from @tableRetour
WHILE @@FETCH_STATUS = 0
BEGIN
	if(@Total>=10000000)
	begin
		insert into @tableRetour(IdOperation,
                codeNatureOperation,
                dateOperation,
                montantDepose,
                denominationOpcvm,
                idOpcvm,
                idActionnaire,
                nomPersonnePhysique,
                prenomPersonnePhysique,
                denomination,
                Total,
                datePremiereSouscription)
		values(@IdOperation,
                @codeNatureOperation,
                @dateOperation,
                @montantDepose,
                @denominationOpcvm,
                @idOpcvm,
                @idActionnaire,
                @nomPersonnePhysique,
                @prenomPersonnePhysique,
                @denomination,
                @Total,
                @datePremiereSouscription)
	end
		
FETCH cursor_Operation INTO @IdOperation,
                @codeNatureOperation,
                @dateOperation,
                @montantDepose,
                @denominationOpcvm,
                @idOpcvm,
                @idActionnaire,
                @nomPersonnePhysique,
                @prenomPersonnePhysique,
                @denomination,
                @Total,
                @datePremiereSouscription

END
CLOSE cursor_Operation
DEALLOCATE cursor_Operation

select tr.IdOperation,
                tr.codeNatureOperation,
                tr.dateOperation,
                tr.montantDepose,
                tr.denominationOpcvm,
                tr.idOpcvm,
                tr.idActionnaire,
                tr.nomPersonnePhysique,
                tr.prenomPersonnePhysique ,
                tr.denomination,
                tr.Total,
                tr.datePremiereSouscription 
     FROM @tableRetour tr 
	 order by denomination asc,dateOperation asc
END
GO

