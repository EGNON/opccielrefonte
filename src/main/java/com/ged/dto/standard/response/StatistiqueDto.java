package com.ged.dto.standard.response;

public class StatistiqueDto {
    private int mois;
    private String nomMois;
    private long nbrRdv;

    public long getNbrRdv() {
        return nbrRdv;
    }

    public void setNbrRdv(long nbrRdv) {
        this.nbrRdv = nbrRdv;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public String getNomMois() {
        return nomMois;
    }

    public void setNomMois(String nomMois) {
        this.nomMois = nomMois;
    }

    @Override
    public String toString() {
        return "StatistiqueDto{" +
                ", mois=" + mois +
                ", nomMois='" + nomMois + '\'' +
                ", nbrRdv=" + nbrRdv +
                '}';
    }
}
