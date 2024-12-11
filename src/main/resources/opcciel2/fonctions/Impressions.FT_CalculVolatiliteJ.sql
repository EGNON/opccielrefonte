USE [DB_OPCCIEL2]
GO
/****** Object:  UserDefinedFunction [Impressions].[FT_CalculVolatiliteJ]    Script Date: 07/08/2024 16:57:13 ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
-- =============================================
-- Author:		Romaric B. TOHOUNKPIN
-- Create date: 16/04/2024
-- Description:
-- =============================================
    CREATE FUNCTION [Impressions].[FT_CalculVolatiliteJ]
    (
        @idOpcvm D_N_SEQ = NULL,
        @dateDebut D_T_DATEHEURE,
        @dateFin D_T_DATEHEURE
        )
    RETURNS @table TABLE (id INT IDENTITY(1,1),
                          idOpcvm D_N_SEQ,
                          sigleOpcvm D_A_LIB,
                          denominationOpcvm D_A_LIB,
                          dateN1 D_T_DATEHEURE,
                          anneeN1 D_N_SEQ,
                          idSeanceN1 D_N_SEQ,
                          vlN1 D_N_DECIMALX,
                          dateN2 D_T_DATEHEURE,
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
        SELECT so.idOpcvm,
               op.sigleOpcvm,
               op.denominationOpcvm,
               so1.dateFermeture,
               YEAR(so1.dateFermeture),
               so1.idSeance,
               so1.valeurLiquidative,
               so.dateFermeture,
               YEAR(so.dateFermeture),
               so.idSeance,
               so.valeurLiquidative,
               0,
               CASE WHEN so1.idSeance IS NOT NULL THEN
                            (ISNULL(so.valeurLiquidative, 0)/ISNULL(so1.valeurLiquidative, 0))-1
                    ELSE 0 END,
               0,
               0,
               0,
               0
        FROM [DB_OPCCIEL].[Parametre].[TJ_SeanceOpcvm] so
                 LEFT OUTER JOIN [DB_OPCCIEL].[Parametre].[TJ_SeanceOpcvm] so1
                                 ON so1.idOpcvm = so.idOpcvm AND so.idSeance = so1.idSeance+1
                 INNER JOIN [DB_OPCCIEL].[Parametre].[T_Opcvm] op
                            ON op.idOpcvm = so.idOpcvm
        WHERE (so.idOpcvm = @idOpcvm OR @idOpcvm IS NULL)
          AND so.supprimer = 0
          AND so1.supprimer = 0
          AND (CAST(so.dateFermeture AS DATE) BETWEEN CAST(@dateDebut AS DATE) AND CAST(@dateFin AS DATE))
          AND (CAST(so1.dateFermeture AS DATE) BETWEEN CAST(@dateDebut AS DATE) AND CAST(@dateFin AS DATE))
        ORDER BY so1.dateFermeture ASC

        UPDATE t
        SET performanceN1 = t1.performanceN2
        FROM @table t
                 INNER JOIN @table t1
                            ON t1.idSeanceN2 = t.idSeanceN1 - 1

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

SELECT * FROM [Impressions].[FT_CalculVolatiliteJ] (NULL, '01/01/2024', '17/04/2024') ORDER BY anneeN1 ASC;
SELECT * FROM [Impressions].[FT_CalculVolatiliteJ] (8) ORDER BY anneeN1 ASC;
SELECT * FROM [Impressions].[FT_CalculVolatiliteJ] (9, '01/01/2024', '17/04/2024') ORDER BY anneeN1 ASC;

*/