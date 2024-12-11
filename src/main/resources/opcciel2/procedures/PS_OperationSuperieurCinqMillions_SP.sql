USE [DB_OPCCIEL2]
GO

/****** Object:  StoredProcedure [Impression].[PS_OperationSuperieurCinqMillions_SP]    Script Date: 18/04/2024 10:24:35 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [Impression].[PS_OperationSuperieurCinqMillions_SP](@annee D_A_LIB)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	declare @tableRetour table(IdOperation D_N_SEQ,
                codeNatureOperation D_A_LIB,
                dateOperation D_T_DATEHEURE,
                montant D_N_DECIMALX,
                denominationOpcvm D_A_LIB,
                idOpcvm D_N_SEQ,
                idActionnaire D_N_SEQ,
                nomPersonnePhysique D_A_LIB,
                prenomPersonnePhysique D_A_LIB,
                denomination D_A_LIB,                 
				Total D_N_DECIMALX)
		declare @IdOperation D_N_SEQ,
                @codeNatureOperation D_A_LIB,
                @dateOperation D_T_DATEHEURE,
                @montant D_N_DECIMALX,
                @denominationOpcvm D_A_LIB,
                @idOpcvm D_N_SEQ,
                @idActionnaire D_N_SEQ,
                @nomPersonnePhysique D_A_LIB,
                @prenomPersonnePhysique D_A_LIB,
                @denomination D_A_LIB, 
                @Total D_N_DECIMALX
		insert @tableRetour
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
                FROM DB_OPCCIEL.Parametre.T_DepotRachat d2 
                INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph2 ON ph2.idPersonne=d2.idActionnaire 
                WHERE d2.idActionnaire = d.idactionnaire 
                and d2.codeNatureOperation='DEP_SOUS' 
				and d2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')) AS Total 
                FROM DB_OPCCIEL.Parametre.T_DepotRachat d 
                INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=d.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=d.idOpcvm 
                and d.codeNatureOperation='DEP_SOUS' 
				and d.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
				and d.estGenere=0
                group by d.IdOperation,d.codeNatureOperation,d.dateOperation,d.montant,
                o.denominationOpcvm,d.idOpcvm,d.idActionnaire,
                ph.nomPersonnePhysique,ph.prenomPersonnePhysique 
           union all
		   SELECT op.IdOperation,
                op.codeNatureOperation,
                op.dateOperation,
                op.montantDepose as montant,
                o.denominationOpcvm,
                op.idOpcvm,
                op.idActionnaire,
                ph.nomPersonnePhysique,
                ph.prenomPersonnePhysique ,
                (ph.nomPersonnePhysique+' '+ph.prenomPersonnePhysique ) as denomination, 
                (SELECT sum(op2.montantDepose) 
                FROM DB_OPCCIEL.Operation.T_OperationSouscriptionRachat op2 
                INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph2 ON ph2.idPersonne=op2.idActionnaire 
                WHERE op2.idActionnaire = op.idactionnaire 
                and op2.codeNatureOperation='SOUS_PART' 
				and op2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')) AS Total 
                FROM DB_OPCCIEL.Operation.T_OperationSouscriptionRachat op 
                INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=op.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=op.idOpcvm 
                and op.codeNatureOperation='SOUS_PART' 
				and op.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                group by op.IdOperation,op.codeNatureOperation,op.dateOperation,op.montantDepose,
                o.denominationOpcvm,op.idOpcvm,op.idActionnaire,
                ph.nomPersonnePhysique,ph.prenomPersonnePhysique 
         

		declare curseur_Operation cursor for
		SELECT tr.IdOperation,
                tr.codeNatureOperation,
                tr.dateOperation,
                tr.montant,
                tr.denominationOpcvm,
                tr.idOpcvm,
                tr.idActionnaire,
                tr.nomPersonnePhysique,
                tr.prenomPersonnePhysique ,
                denomination, 
                (SELECT sum(tr2.montant) 
                FROM @tableRetour tr2                
                WHERE tr2.idActionnaire = tr.idactionnaire) AS Total 
                FROM @tableRetour tr
         --CLOSE curseur_Operation
		 open curseur_Operation
		 FETCH curseur_Operation INTO @IdOperation,
                @codeNatureOperation ,
                @dateOperation ,
                @montant ,
                @denominationOpcvm ,
                @idOpcvm ,
                @idActionnaire ,
                @nomPersonnePhysique ,
                @prenomPersonnePhysique ,
                @denomination , 
                @Total 
 
 delete from @tableRetour
WHILE @@FETCH_STATUS = 0
BEGIN
	if(@Total>=5000000)
	begin
		insert into @tableRetour(IdOperation,
			codeNatureOperation ,
			dateOperation ,
			montant ,
			denominationOpcvm ,
			idOpcvm ,
			idActionnaire ,
			nomPersonnePhysique ,
			prenomPersonnePhysique ,
			denomination , 
			Total )
		values(
			@IdOperation,
			@codeNatureOperation ,
			@dateOperation ,
			@montant ,
			@denominationOpcvm ,
			@idOpcvm ,
			@idActionnaire ,
			@nomPersonnePhysique ,
			@prenomPersonnePhysique ,
			@denomination , 
			@Total )
	end
	
    FETCH curseur_Operation INTO @IdOperation,
        @codeNatureOperation ,
        @dateOperation ,
        @montant ,
        @denominationOpcvm ,
        @idOpcvm ,
        @idActionnaire ,
        @nomPersonnePhysique ,
        @prenomPersonnePhysique ,
        @denomination , 
        @Total 
END
CLOSE curseur_Operation
DEALLOCATE curseur_Operation

	select IdOperation,
        codeNatureOperation ,
        dateOperation ,
        montant ,
        denominationOpcvm ,
        idOpcvm ,
        idActionnaire ,
        nomPersonnePhysique ,
        prenomPersonnePhysique ,
        denomination , 
        Total 
		from @tableRetour
		order by denomination asc,dateOperation asc
END
GO

