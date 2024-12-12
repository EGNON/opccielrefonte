USE [DB_OPCCIEL]
GO

/****** Object:  UserDefinedFunction [Impressions].[FT_DividendeDistribuee1]    Script Date: 25/04/2024 09:15:05 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
create FUNCTION [Impressions].[FT_DividendeDistribuee1]
(	
	@idOpcvm D_A_CODE_LONG=null,@dateDebut D_T_DATEHEURE,@dateFin D_T_DATEHEURE
)
RETURNS  @table  TABLE (sigleOpcvm D_A_LIB,
			            denominationOpcvm D_A_LIB,
			            dividende D_N_DECIMALX,
			            dateDividende D_A_CODE_LONG,
			            vl D_N_DECIMALX,
			            rendement as (dividende/vl)*100
			            )
			            
AS
BEGIN

if(@idOpcvm='TOUS')
set @idOpcvm=null

insert into @table
select opcvm.sigleOpcvm,
	   opcvm.denominationOpcvm,
	   isnull(detach.couponUnitaire,0),
	   ISNULL(CONVERT(varchar,r5.dateOp,103),''),
	   so.valeurLiquidative
from Parametre.T_Opcvm opcvm
	left outer join Dividende.T_DetachementCoupon detach
		on detach.idOpcvm=opcvm.idOpcvm	
	inner join Parametre.TJ_SeanceOpcvm so
		on so.idOpcvm = detach.idOpcvm
		and detach.idSeance = so.idSeance				
	inner join (select distinct s.idOpcvm, Mouchard.FS_FormaterDate(s.dateOp) as dateOp,s.idDetachement
											from Dividende.TJ_DividendeActionnaire s
											where  (s.idOpcvm = @idOpcvm or @idOpcvm is null)																
									
							 )R5
				on detach.idOpcvm = r5.idOpcvm
				and detach.idDetachement=r5.idDetachement
where opcvm.idOpcvm = cast(@idOpcvm as int) or @idOpcvm is null
and (     Mouchard.FS_FormaterDate(r5.dateOp)>=Mouchard.FS_FormaterDate(@dateDebut)
	  AND Mouchard.FS_FormaterDate(r5.dateOp)<=Mouchard.FS_FormaterDate(@dateFin)
	)
and detach.estPaye=1

				  
RETURN 
END



/*

select * from Impressions.FT_DividendeDistribuee1
(	
	 null,'01/01/2020','31/12/2020'
)

*/
GO

