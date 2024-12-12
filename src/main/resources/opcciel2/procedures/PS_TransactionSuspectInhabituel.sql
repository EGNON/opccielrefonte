USE [DB_OPCCIEL2]
GO
/****** Object:  StoredProcedure [Impression].[PS_TransactionSuspectInhabituel]    Script Date: 18/04/2024 12:58:12 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Romaric B. TOHOUNKPIN
-- Create date: 05/04/2024
-- Description:	Identification des transactions à caractère suspect ou inhabituel
-- =============================================
ALTER PROCEDURE [Impression].[PS_TransactionSuspectInhabituel] (
    @typePersonne VARCHAR(4),
    @opMontantTransac VARCHAR(2),
    @montantTransac D_N_DECIMAL,
    @opQtePart VARCHAR(2),
    @qtePart D_N_DECIMAL
)
AS
BEGIN
    -- SET NOCOUNT ON added to prevent extra result sets from
    -- interfering with SELECT statements.
    SET NOCOUNT ON;

    DECLARE @tous VARCHAR(10)
    IF @typePersonne = 'tous'
        BEGIN
            SET @tous = '''PH'',' + '''PM'''
        END
    ELSE IF @typePersonne = 'ph'
        BEGIN
            SET @tous = '''ph'''
        END
    ELSE
        BEGIN
            SET @tous = '''pm'''
        END
    -- Transaction inhabituelle
    DECLARE @frequenceTransacInhabituelle TransacType

    -- Récupérer les montants de transactions inhabituelles
    INSERT @frequenceTransacInhabituelle
    SELECT idActionnaire, montant, [nbr] = COUNT(*)
    FROM [DB_OPCCIEL].[Parametre].[T_DepotRachat] dr
    WHERE dr.codeNatureOperation = 'DEP_SOUS' AND dr.montant > 0
    GROUP BY dr.montant, dr.IdActionnaire
    HAVING COUNT(*) < 5

    --Transaction suspecte
    DECLARE @frequenceTransacSuspect TABLE(montant D_N_DECIMAL, nbr D_N_SEQ)

    DECLARE @sql NVARCHAR(MAX)
    SET @sql = 'SELECT osr.IdActionnaire,
		   osr.dateOperation,
		   osr.IdOpcvm,
		   osr.montantDepose,
		   (ISNULL(pp.NomPersonnePhysique + '' '' + pp.PrenomPersonnePhysique, pm.RaisonSociale +
		   CASE WHEN LTRIM(RTRIM(pm.SiglePersonneMorale)) != '''' THEN '' ['' + LTRIM(RTRIM(pm.SiglePersonneMorale)) + '']'' ELSE '''' END)) AS actionnaire,
		   op.denominationOpcvm AS opcvm,
		   CASE WHEN LTRIM(RTRIM(p.LibelleTypePersonne)) = ''PH'' THEN ''PERSONNE PHYSIQUE'' ELSE ''PERSONNE MORALE'' END AS LibelleTypePersonne,
		   osr.nombrePartSousRachat AS nbrePart
	FROM [DB_OPCCIEL].[Operation].[T_OperationSouscriptionRachat] osr
	INNER JOIN @frequenceTransacInhabituelle t
		ON t.idActionnaire = osr.IdActionnaire
	INNER JOIN [DB_OPCCIEL].[Parametre].[T_Personne] p
		ON p.IdPersonne = osr.IdActionnaire
	LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonnePhysique] pp
		ON pp.IdPersonne = p.IdPersonne
	LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[T_PersonneMorale] pm
		ON pm.IdPersonne = p.IdPersonne
	INNER JOIN [DB_OPCCIEL].[Parametre].[T_Opcvm] op
		ON op.idOpcvm = osr.IdOpcvm
	WHERE LOWER(p.LibelleTypePersonne) IN (' + @tous + ') ' +
               'AND osr.codeNatureOperation = ''SOUS_PART''
               AND osr.montantDepose = t.montant'

    IF @montantTransac IS NOT NULL
        BEGIN
            SET @sql = @sql + ' AND osr.montantDepose ' + @opMontantTransac + ' ' + CAST(@montantTransac AS VARCHAR(100))
        END

    IF @qtePart IS NOT NULL
        BEGIN
            SET @sql = @sql + ' AND osr.nombrePartSousRachat ' + @opQtePart + ' ' + CAST(@qtePart AS VARCHAR(100))
        END

    SET @sql = @sql + ' ORDER BY osr.IdActionnaire ASC'

    print @sql

    --EXEC(@sql)
    EXEC sp_executesql @sql, N'@frequenceTransacInhabituelle TransacType readonly', @frequenceTransacInhabituelle
END

/*
if exists (select * from sys.types where name = 'TransacType')
		drop type TransacType

	create type TransacType AS TABLE(idActionnaire D_N_SEQ, montant D_N_DECIMAL, nbr D_N_SEQ)
	EXEC [Impression].[PS_TransactionSuspectInhabituel] 'pm', '<=', 1000000, '<=', 200

	EXEC [Impression].[PS_TransactionSuspectInhabituel] 'pm', '>=', null, '<=', null
*/
