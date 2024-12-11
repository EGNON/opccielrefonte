USE [DB_OPCCIEL2]
GO

/****** Object:  StoredProcedure [Impression].[PS_ALPHA_SP]    Script Date: 18/04/2024 10:22:24 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [Impression].[PS_ALPHA_SP](@IdOpcvm D_N_SEQ,@AnneeDebut D_A_LIB,@AnneeFin D_A_LIB)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    -- Insert statements for procedure here
	declare @tableRetour table(DateOuverture D_T_DATE,
						VL D_N_DECIMALX,
						DividendeDistribue D_N_DECIMALX,
						PerformanceAnnuelle D_N_DECIMALX,
						IndiceReference D_N_DECIMALX,
						Alpha D_N_DECIMALX)

	declare @tableValeur table(DateOuverture D_T_DATE,
						VL D_N_DECIMALX,
						DividendeDistribue D_N_DECIMALX,
						PerformanceAnnuelle D_N_DECIMALX,
						IndiceReference D_N_DECIMALX,
						Alpha D_N_DECIMALX)
	declare @tableVL table(Annee D_A_LIB,
						dateFermeture D_T_DATE,
						VL D_N_DECIMALX,						
						Nbre D_N_SEQ
						)

	declare @tableDividende table(sigleOpcvm D_A_LIB,
						denominationOpcvm D_A_LIB,
						dividende D_N_DECIMALX,
						dateDividende D_T_DATE,
						vl D_N_DECIMALX,
						rendement D_N_DECIMALX
						)
	insert @tableVL	
	select year(so.dateFermeture) as Annee,
			so.dateFermeture,
			valeurLiquidative as VL,
			so.idSeance as Nbre
	from DB_OPCCIEL.Parametre.TJ_SeanceOpcvm so
	where so.idOpcvm=@IdOpcvm
	and so.dateFermeture between convert(datetime,'01/01/'+@AnneeDebut) and convert(datetime,'31/12/'+@AnneeFin+' 23:59:59')
	and so.supprimer=0
	group by year(so.dateFermeture),idOpcvm,dateFermeture,valeurLiquidative,idSeance
	

	insert @tableDividende
	select d.sigleOpcvm,
			d.denominationOpcvm,
			d.dividende,
			d.dateDividende,
			d.vl,
			d.rendement
	from DB_OPCCIEL.Impressions.FT_DividendeDistribuee1(@IdOpcvm,'01/01/'+@AnneeDebut,'31/12/'+@AnneeFin+' 23:59:59') d

	declare @l int=@AnneeDebut
	declare @k int=1
	declare @vlAvant D_N_DECIMALX

	declare @DateOuverture D_T_DATEHEURE,@VL D_N_DECIMALX,@Nbre D_N_SEQ,@Annee D_A_LIB,@Dividende D_N_DECIMALX
	,@Performance D_N_DECIMALX,@tauxRendement D_N_DECIMALX,@Alpha D_N_DECIMALX,@NbreDividende D_N_SEQ

	select @tauxRendement=o.tauxRendement
	from DB_OPCCIEL.Parametre.T_Opcvm o
	where o.idOpcvm=@IdOpcvm

	while @l<=@AnneeFin
	begin
		declare curseur_Affichage cursor for
		select top 1 tvl.DateOuverture,tvl.VL,tvl.Nbre,tvl.Annee
		from @tableVL tvl
		where tvl.Annee=@l
		order by Nbre desc

		OPEN curseur_Affichage
 
		FETCH curseur_Affichage INTO @DateOuverture,
									@VL,
									@Nbre,
									@Annee		
		WHILE @@FETCH_STATUS = 0
		BEGIN		
			select @NbreDividende=count(td.dividende)
			from @tableDividende td
			where year(td.dateDividende)=@l

			set @Dividende=0
			if(@NbreDividende!=0)
			begin
				select @Dividende=isnull(td.dividende,0)
				from @tableDividende td
				where year(td.dateDividende)=@l
			end

			if(@k=1)
			begin
				select top 1 @vlAvant=so.valeurLiquidative
				from DB_OPCCIEL.Parametre.TJ_SeanceOpcvm so
				where year(so.dateFermeture)=@l-1
				and idOpcvm=@IdOpcvm
				and so.supprimer=0
				order by idSeance desc
			end	
			if(@vlAvant is  not null)
			begin
				set @Performance=(((@VL+@Dividende)/@vlAvant)-1)*100
			end
			else
			begin
				set @Performance=0
			end

			set @Alpha=@Performance-@tauxRendement

			insert into @tableRetour(DateOuverture,
						VL,
						DividendeDistribue,
						PerformanceAnnuelle,
						IndiceReference,
						Alpha)
						values(
						@DateOuverture,
						@VL,
						@Dividende,
						@Performance,
						@tauxRendement,
						@Alpha)
		FETCH curseur_Affichage INTO @DateOuverture,
								@VL,
								@Nbre,
								@Annee

		END
		CLOSE curseur_Affichage
		DEALLOCATE curseur_Affichage
		set @vlAvant=@VL
		set @l+=1
		set @k+=1
	end

	select tr.DateOuverture,
			tr.VL,
			tr.DividendeDistribue,
			round(tr.PerformanceAnnuelle,2) as PerformanceAnnuelle,
			tr.IndiceReference,
			round(tr.Alpha,2) as Alpha
	from @tableRetour tr
END
GO

