USE [DB_OPCCIEL2]
GO
/****** Object:  UserDefinedFunction [Impressions].[FT_CalculVolatilite]    Script Date: 07/08/2024 19:49:31 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Romaric B. TOHOUNKPIN
-- Create date: 16/04/2024
-- Description:
-- =============================================
    CREATE FUNCTION [Impressions].[FT_CalculVolatilite]
    (
        @idOpcvm D_N_SEQ = NULL
        )
    RETURNS @table TABLE (idOpcvm D_N_SEQ,
                          sigleOpcvm D_A_LIB,
                          denominationOpcvm D_A_LIB,
                          anneeN1 D_N_SEQ,
                          idSeanceN1 D_N_SEQ,
                          vlN1 D_N_DECIMALX,
                          anneeN2 D_N_SEQ,
                          idSeanceN2 D_N_SEQ,
                          vlN2 D_N_DECIMALX,
                          performanceN1 D_N_DECIMALX,
                          performanceN2 D_N_DECIMALX,
                          rentabiliteN2 D_N_DECIMALX,
                          variance D_N_DECIMALX,
                          ecarttype1 D_N_DECIMALX,
                          ecarttype2 D_N_DECIMALX
                         )
    AS
    BEGIN
        INSERT @table
        SELECT r.idOpcvm,
               op.sigleOpcvm,
               op.denominationOpcvm,
               anneeN1,
               idSeanceN1,
               ISNULL(vl1.valeurLiquidative, 0) vlN1,
               anneeN2,
               idSeanceN2,
               ISNULL(vl2.valeurLiquidative, 0) vlN2,
               0,
               CASE WHEN idSeanceN1 <> -1 THEN
                            (ISNULL(vl2.valeurLiquidative, 0)/ISNULL(vl1.valeurLiquidative, 0))-1
                    ELSE 0 END,
               0,
               0,
               0,
               0
        FROM (
                 SELECT YEAR(so.dateFermeture)-1 anneeN1,
                        YEAR(so.dateFermeture) anneeN2,
                        ISNULL(sN1.idSeance, -1) idSeanceN1,
                        ISNULL(sN2.idSeance, -1) idSeanceN2,
                        so.idOpcvm idOpcvm
                 FROM [DB_OPCCIEL].[Parametre].[TJ_SeanceOpcvm] so
                          LEFT OUTER JOIN (
                     SELECT YEAR(so1.dateFermeture) annee, MAX(so1.idSeance) idSeance
                     FROM [DB_OPCCIEL].[Parametre].[TJ_SeanceOpcvm] so1
                     GROUP BY YEAR(so1.dateFermeture)
                 ) sN1 ON sN1.annee = YEAR(so.dateFermeture)-1
                          LEFT OUTER JOIN (
                     SELECT YEAR(so1.dateFermeture) annee, MAX(so1.idSeance) idSeance
                     FROM [DB_OPCCIEL].[Parametre].[TJ_SeanceOpcvm] so1
                     GROUP BY YEAR(so1.dateFermeture)
                 ) sN2 ON sN2.annee = YEAR(so.dateFermeture)
                 GROUP BY so.idOpcvm, YEAR(so.dateFermeture)-1, YEAR(so.dateFermeture), sN1.idSeance, sN2.idSeance
                 --ORDER BY YEAR(so.dateFermeture)-1 ASC
             ) R
                 LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[TJ_SeanceOpcvm] vl1
                                 ON vl1.idSeance = R.idSeanceN1 AND vl1.idOpcvm = R.idOpcvm
                 LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[TJ_SeanceOpcvm] vl2
                                 ON vl2.idSeance = R.idSeanceN2 AND vl2.idOpcvm = R.idOpcvm
                 INNER JOIN [DB_OPCCIEL].[Parametre].[T_Opcvm] op
                            ON op.idOpcvm = R.idOpcvm
        WHERE (R.idOpcvm = @idOpcvm OR @idOpcvm IS NULL)

        UPDATE t
        SET performanceN1 = t1.performanceN2
        FROM @table t
                 INNER JOIN @table t1
                            ON t1.anneeN2 = t.anneeN2 - 1

        UPDATE @table
        SET rentabiliteN2 = (performanceN2 + performanceN1)/2

        UPDATE @table
        SET variance = POWER((performanceN2 - rentabiliteN2), 2)/2

        UPDATE @table
        SET ecarttype1 = SQRT(variance)

        UPDATE @table
        SET ecarttype2 = ecarttype1*(1+variance)

        -- Valeur en %
        UPDATE @table
        SET performanceN1 = performanceN1*100,
            performanceN2 = performanceN2*100,
            rentabiliteN2 = rentabiliteN2*100,
            ecarttype1 = ecarttype1*100,
            ecarttype2 = ecarttype2*100

        RETURN
    END


/*

select * from [Impressions].[FT_CalculVolatilite] (NULL);
select * from [Impressions].[FT_CalculVolatilite] (8);
select * from [Impressions].[FT_CalculVolatilite] (9);

*/