USE [DB_OPCCIEL2]
GO

/****** Object:  StoredProcedure [Impression].[PS_OperationConditionNormale_SP]    Script Date: 18/04/2024 10:23:57 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

-- =============================================
-- Author:		<Author,,Name>
-- Create date: <Create Date,,>
-- Description:	<Description,,>
-- =============================================
CREATE PROCEDURE [Impression].[PS_OperationConditionNormale_SP](@annee D_A_LIB)
AS
BEGIN
	-- SET NOCOUNT ON added to prevent extra result sets from
	-- interfering with SELECT statements.
	SET NOCOUNT ON;

    declare @tableRetour table(IdOperation D_N_SEQ,
                codeNatureOperation D_A_LIB,
                dateOperation D_T_DATEHEURE,
                montantDepose D_N_DECIMALX,
                denominationOpcvm D_A_LIB,
                idOpcvm D_N_SEQ,
                idActionnaire D_N_SEQ,
                nomPersonnePhysique D_A_LIB,
                prenomPersonnePhysique D_A_LIB ,
                denomination D_A_LIB,
                Total D_N_DECIMALX)
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
                @Total D_N_DECIMALX

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
                 iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph2 ON ph2.idPersonne=dr2.idActionnaire
        WHERE dr2.idActionnaire = dr.idactionnaire
          and dr2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
          and dr2.codeNatureOperation='DEP_SOUS' ) AS Total
FROM DB_OPCCIEL.Parametre.T_DepotRachat dr
         iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=dr.idActionnaire
         iNNER JOIN DB_OPCCIEL.Parametre.T_Personne p ON p.idPersonne=ph.IdPersonne
         INNER JOIN Parametre.T_Personne p2 on p2.numeroCpteDeposit=ph.numCompteDepositaire
         INNER JOIN Parametre.T_PersonnePhysique ph2 on ph2.idPersonne=p2.idPersonne
         INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=dr.idOpcvm
where dr.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
  and (dr.codeNatureOperation='DEP_SOUS' )
  and (dr.estGenere=0)
  and ((ph2.nom is not null and ph2.prenom is not null) or ph2.nomDeJeuneFille is not null)
  and (ph2.Sexe is not null)
  and (ph2.idPaysNationalite is not null)
  and (p2.idPaysResidence is not null)
  and (ph2.teint is not null)
  and (ph2.ExposeMotif is not null)
  and (p2.idPersonne in(select d.idPersonne from Parametre.T_Document d where d.numeroPiece is not null))
  and (p2.CodeLangue is not null)
  and ((ph2.nomConjoint is not null) or (ph2.nomPere is not null) or(ph2.nomMere is not null) or(ph2.nomDeJeuneFille is not null))
  and (ph2.idProf is not null )
  and (ph2.LieuNaissance is not null)
  and	(ph2.adresseComplete is not null)
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
        WHERE op2.idActionnaire = op.idactionnaire
          and op2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
          and op2.codeNatureOperation='SOUS_PART' ) AS Total
FROM DB_OPCCIEL.Operation.T_OperationSouscriptionRachat op
         iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=op.idActionnaire
         iNNER JOIN DB_OPCCIEL.Parametre.T_Personne p ON p.idPersonne=ph.IdPersonne
         INNER JOIN Parametre.T_Personne p2 on p2.numeroCpteDeposit=ph.numCompteDepositaire
         INNER JOIN Parametre.T_PersonnePhysique ph2 on ph2.idPersonne=p2.idPersonne
         INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=op.idOpcvm
where op.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
  and (op.codeNatureOperation='SOUS_PART' )
  and ((ph2.nom is not null and ph2.prenom is not null) or ph2.nomDeJeuneFille is not null)
  and (ph2.Sexe is not null)
  and (ph2.idPaysNationalite is not null)
  and (p2.idPaysResidence is not null)
  and (ph2.teint is not null)
  and (ph2.ExposeMotif is not null)
  and (p2.idPersonne in(select d.idPersonne from Parametre.T_Document d where d.numeroPiece is not null))
  and (p2.CodeLangue is not null)
  and ((ph2.nomConjoint is not null) or (ph2.nomPere is not null) or(ph2.nomMere is not null) or(ph2.nomDeJeuneFille is not null))
  and (ph2.idProf is not null )
  and (ph2.LieuNaissance is not null)
  and	(ph2.adresseComplete is not null)
group by op.IdOperation,op.codeNatureOperation,op.dateOperation,op.montantDepose,
         o.denominationOpcvm,op.idOpcvm,op.idActionnaire, ph.nomPersonnePhysique,
         ph.prenomPersonnePhysique

declare curseur_Operation cursor for
SELECT tr.IdOperation,
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
                 iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph2 ON ph2.idPersonne=tr2.idActionnaire
        WHERE tr2.idActionnaire = tr.idactionnaire
          and tr2.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
       ) AS Total
FROM @tableRetour tr
         iNNER JOIN DB_OPCCIEL.Parametre.T_PersonnePhysique ph ON ph.idPersonne=tr.idActionnaire
         INNER JOIN DB_OPCCIEL.Parametre.T_Opcvm o ON o.idOpcvm=tr.idOpcvm
where tr.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')

    OPEN curseur_Operation

FETCH curseur_Operation INTO @IdOperation,
    @codeNatureOperation,
    @dateOperation,
    @montantDepose,
    @denominationOpcvm,
    @idOpcvm,
    @idActionnaire,
    @nomPersonnePhysique,
    @prenomPersonnePhysique,
    @denomination,
    @Total

delete from @tableRetour
                WHILE @@FETCH_STATUS = 0
BEGIN
	if(@Total>=50000000)
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
                         Total)
values(
          @IdOperation,
          @codeNatureOperation,
          @dateOperation,
          @montantDepose,
          @denominationOpcvm,
          @idOpcvm,
          @idActionnaire,
          @nomPersonnePhysique,
          @prenomPersonnePhysique,
          @denomination,
          @Total)
end
FETCH curseur_Operation INTO @IdOperation,
    @codeNatureOperation,
    @dateOperation,
    @montantDepose,
    @denominationOpcvm,
    @idOpcvm,
    @idActionnaire,
    @nomPersonnePhysique,
    @prenomPersonnePhysique,
    @denomination,
    @Total

END
CLOSE curseur_Operation
    DEALLOCATE curseur_Operation

SELECT tr.IdOperation,
       tr.codeNatureOperation,
       tr.dateOperation,
       tr.montantDepose,
       tr.denominationOpcvm,
       tr.idOpcvm,
       tr.idActionnaire,
       tr.nomPersonnePhysique,
       tr.prenomPersonnePhysique ,
       tr.denomination,
       tr.Total
FROM @tableRetour tr
where tr.dateOperation between convert(date,'01/01/'+@annee) and convert(datetime,'31/12/'+@annee+' 23:59:59')
order by denomination asc,dateOperation asc
END
GO

