USE [DB_OPCCIEL2]
GO

/****** Object:  StoredProcedure [Impression].[PS_OperationConditionAnormale_SP]    Script Date: 18/04/2024 10:23:41 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO


-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [Impression].[PS_OperationConditionAnormale_SP](@annee D_A_LIB)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;
	declare @tableRetour table (nbre D_N_SEQ,
							montantDepose D_N_DECIMALX,
							IdActionnaire D_N_SEQ,
							nomPersonnePhysique D_A_LIB,
							prenomPersonnePhysique D_A_LIB,
							denomination D_A_LIB
							)
	declare @tableRetour2 table (IdOperation D_N_SEQ,
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
                datePremiereSouscription D_T_DATEHEURE 
							)
	declare @nbre D_N_SEQ,@montantDepose D_N_DECIMALX,@IdActionnaire D_N_SEQ,
			@nomPersonnePhysique D_A_LIB,@prenomPersonnePhysique D_A_LIB,@denomination D_A_LIB
	declare @nbre2 D_N_SEQ = 0
    declare @nbreAvant D_N_SEQ= 0
    declare @montant D_N_DECIMALX= 0
    declare @montantAvant D_N_DECIMALX= 0
    declare @client D_A_LIB= ''
	declare @clause D_A_LIB = ''

	declare @table1 table(idActionnaire D_N_SEQ)
	insert @tableRetour
	exec [Impression].PS_FrequenceOperation_SP @annee

	declare cursor_FrequenceOperation cursor for
	select nbre,
        montantDepose,
        IdActionnaire,
        nomPersonnePhysique,
        prenomPersonnePhysique ,
        denomination 
    FROM @tableRetour

	OPEN cursor_FrequenceOperation
 
FETCH cursor_FrequenceOperation INTO @nbre ,@montantDepose,@IdActionnaire,@nomPersonnePhysique,@prenomPersonnePhysique ,@denomination
 
WHILE @@FETCH_STATUS = 0
BEGIN
            set @nbre2 = @nbre
            set @montant =@montantDepose
   --         if (@client =@denomination) 
			--begin
   --             if (@nbre2 < @nbreAvant) 
			--	begin
   --                 if (@montant >= 10000000) 
			--		begin
   --                     if (@clause = '')
			--			begin
			--					set @clause = cast(@IdActionnaire as varchar)
			--			end
   --                     else 
			--			begin
   --                         set @clause += ';' + cast(@IdActionnaire as varchar)
   --                     end
   --                 end
   --             end
   --         end
		if (@montant >= 10000000) 
			begin
            if (@nbre2 >= 1) --<
			begin
                    if (@clause = '')
					begin
							set @clause = cast(@IdActionnaire as varchar)
					end
                    else 
					begin
                        set @clause += ';' + cast(@IdActionnaire as varchar)
                    end
             end
         end
        
        
		set @client = @denomination
        set @nbreAvant = @nbre2
        set @montantAvant = @montant
		
    FETCH cursor_FrequenceOperation INTO @nbre ,@montantDepose,@IdActionnaire,@nomPersonnePhysique,@prenomPersonnePhysique ,@denomination
END
CLOSE cursor_FrequenceOperation
DEALLOCATE cursor_FrequenceOperation
			
			insert into @table1 (idActionnaire)
				select items from dbo.Split(ltrim(rtrim(@clause)),';')

			insert @tableRetour2
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
                iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph2 ON ph2.idPersonne=dr2.idActionnaire 
                WHERE dr2.idActionnaire = dr.idactionnaire 
                and dr2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and dr2.idActionnaire in(select t.idActionnaire from @table1 t)
				and dr2.montant>=10000000
                and dr2.codeNatureOperation='DEP_SOUS' ) AS Total,
                (SELECT 
                min(dr3.dateOperation) as dateOperation2 
                FROM DB_OPCCIEL.Parametre.T_DepotRachat dr3 
                INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph3 ON ph3.idPersonne=dr3.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o3 ON o3.idOpcvm=dr3.idOpcvm 
                where dr3.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and dr3.codeNatureOperation='DEP_SOUS' 
                and dr3.idOpcvm=dr.idopcvm 
                and dr3.idActionnaire=dr.idActionnaire 
				and dr3.idActionnaire in(select t.idActionnaire from @table1 t)
				and dr3.montant>=10000000
                group by dr3.idActionnaire ) as datePremiereSouscription 
                FROM DB_OPCCIEL.Parametre.T_DepotRachat dr 
                iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=dr.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=dr.idOpcvm 
                where dr.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and dr.codeNatureOperation='DEP_SOUS' 
				and dr.idActionnaire in(select t.idActionnaire from @table1 t)
				and dr.montant>=10000000
				and dr.estGenere=0
                group by dr.IdOperation,dr.codeNatureOperation,dr.dateOperation,dr.montant,
                o.denominationOpcvm,dr.idOpcvm,dr.idActionnaire, ph.nomPersonnePhysique,
                ph.prenomPersonnePhysique
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
                iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph2 ON ph2.idPersonne=op2.idActionnaire 
                WHERE Op2.idActionnaire = op.idactionnaire 
                and op2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and op2.idActionnaire in(select t.idActionnaire from @table1 t)
				and op2.montantDepose>=10000000
                and op2.codeNatureOperation='SOUS_PART' ) AS Total,
                (SELECT 
                min(op.dateOperation) as dateOperation2 
                FROM DB_OPCCIEL.Operation.T_OperationSouscriptionRachat op3 
                INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph3 ON ph3.idPersonne=op3.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o3 ON o3.idOpcvm=op3.idOpcvm 
                where op3.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and op3.codeNatureOperation='SOUS_PART' 
                and op3.idOpcvm=op.idopcvm 
                and op3.idActionnaire=op.idActionnaire 
				and op3.idActionnaire in(select t.idActionnaire from @table1 t)
				and op3.montantDepose>=10000000
                group by op3.idActionnaire ) as datePremiereSouscription 
                FROM DB_OPCCIEL.Operation.T_OperationSouscriptionRachat op 
                iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=op.idActionnaire 
                INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=op.idOpcvm 
                where op.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
                and op.codeNatureOperation='SOUS_PART' 
				and op.idActionnaire in(select t.idActionnaire from @table1 t)
				and op.montantDepose>=10000000
                group by op.IdOperation,op.codeNatureOperation,op.dateOperation,op.montantDepose,
                o.denominationOpcvm,op.idOpcvm,op.idActionnaire, ph.nomPersonnePhysique,
                ph.prenomPersonnePhysique
                --order by denomination asc,op.dateOperation asc

				--select * from @table1
				SELECT IdOperation,
					codeNatureOperation,
					dateOperation,
					montantDepose,
					denominationOpcvm,
					idOpcvm,
					idActionnaire,
					nomPersonnePhysique,
					prenomPersonnePhysique ,
					denomination,
					(SELECT sum(montantDepose) 
					FROM @tableRetour2 tr2 
					iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph2 ON ph2.idPersonne=tr2.idActionnaire 
					WHERE tr2.idActionnaire =tr.idactionnaire) AS Total,
					(SELECT 
					min(tr3.dateOperation) as dateOperation2 
					FROM @tableRetour2 tr3 
					INNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph3 ON ph3.idPersonne=tr3.idActionnaire 
					INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o3 ON o3.idOpcvm=tr3.idOpcvm 
					where tr3.idOpcvm=tr.idopcvm 
					and tr3.idActionnaire=tr.idActionnaire 
					group by tr3.idActionnaire ) as datePremiereSouscription 
                FROM @tableRetour2 tr 
				order by denomination asc,dateOperation asc
END
GO

